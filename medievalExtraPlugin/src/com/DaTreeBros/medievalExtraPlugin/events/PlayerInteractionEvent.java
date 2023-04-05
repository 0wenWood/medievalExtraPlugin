package com.DaTreeBros.medievalExtraPlugin.events;

import com.DaTreeBros.medievalExtraPlugin.items.CustomMaterial;
import com.DaTreeBros.medievalExtraPlugin.items.ItemManager;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.bukkit.Bukkit.getServer;

public class PlayerInteractionEvent implements Listener {

    HashMap<Player, Long> cooldown = new HashMap<>();
    int seconds = 1200; // 625 = 0.625 seconds. This is based on milliseconds.

    @EventHandler
    public void onPlayerInteraction(@NotNull PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getAttackCooldown() < 1.0) { // Dual Wielding Swords
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                var offhand = player.getInventory().getItemInOffHand();
                Map<Enchantment, Integer> enchantments = null;
                if (offhand.getItemMeta() != null) {
                    enchantments = offhand.getItemMeta().getEnchants();
                }
                if (ItemManager.isDualWieldingWeapon(offhand.getType()) && !hasCooldown(player)) {
                    if (!(player.getNearbyEntities(3, 3, 3).isEmpty())) {
                        double d = 0.0;
                        for (var entity : player.getNearbyEntities(3, 3, 3)) {
                            if (entity instanceof LivingEntity le) {
                                var v = le.getEyeLocation().toVector()
                                        .subtract(player.getEyeLocation().toVector());
                                d = Math.max(v.normalize().dot(player.getEyeLocation().getDirection()), d);
                                if (d > 0.7) {
                                    var ticks = le.getNoDamageTicks();
                                    le.setNoDamageTicks(0);
                                    le.damage(getDamageValue(offhand, le), le);
                                    le.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR,
                                            le.getLocation(),
                                            (int)getDamageValue(offhand, le));

                                    damageItem(offhand, enchantments, 1);
                                    activateCooldown(player);
                                    le.setNoDamageTicks(ticks);

                                    if (enchantments != null) {
                                        if (enchantments.containsKey(Enchantment.FIRE_ASPECT)) {
                                            le.setFireTicks(80 * enchantments.get(Enchantment.FIRE_ASPECT));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) { // Warhammer Special Attack
            var hand = player.getInventory().getItemInMainHand();
            if (hand.getItemMeta() != null && hand.getItemMeta().hasCustomModelData()) {
                if (hand.getItemMeta().getCustomModelData() >= CustomMaterial.WOODEN_WARHAMMER.getId()
                        && hand.getItemMeta().getCustomModelData() <= CustomMaterial.NETHERITE_WARHAMMER.getId()) {
                    double range = ItemManager.getWarhammerAbilityRange(hand.getType());
                    if (!(player.getNearbyEntities(range, range, range).isEmpty())) {
                        for (var entities : player.getNearbyEntities(range, range, range)) {
                            if (entities instanceof LivingEntity le) {
                                var v = le.getLocation()
                                        .subtract(player.getLocation())
                                        .add(new Vector(0, 0.5, 0))
                                        .multiply(range / 6.5).toVector();
                                le.setVelocity(v);
                            }
                        }

                        if (event.getClickedBlock() != null) {
                            player.getWorld().spawnParticle(Particle.BLOCK_DUST,
                                    event.getClickedBlock().getLocation().add(new Vector(0, 1, 0)),
                                    200,
                                    getWarhammerParticleEffect(hand.getType()));
                        }
                        damageItem(hand, null, 2);
                    }
                }
            }
        }

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            var hand = player.getInventory().getItemInMainHand();
            if (hand.getItemMeta() != null
                    && hand.getItemMeta().hasCustomModelData()
                    && hand.getItemMeta().getCustomModelData() == CustomMaterial.IRON_DAGGER.getId()) {
                damageItem(hand, hand.getEnchantments(), 1);
                var projectile = player.getWorld().dropItemNaturally(player.getLocation(), hand);
                projectile.setThrower(player.getUniqueId());
                projectile.setVelocity(player.getEyeLocation().getDirection().multiply(4));
                hand.setAmount(0);
            }
        }
    }

    private double getDamageValue(@NotNull ItemStack offhand, LivingEntity le) {
        var material = offhand.getType();

        if(offhand.getItemMeta() != null && offhand.getItemMeta().hasCustomModelData()) {
            var meta = offhand.getItemMeta();
            if(meta.getCustomModelData() > 3200) return 0;
        }

        var damage = 0.0;
        if (material.equals(Material.WOODEN_SWORD)) {
            damage = 5.0;
        } else if (material.equals(Material.STONE_SWORD) || material.equals(Material.GOLDEN_SWORD)) {
            damage = 6.0;
        } else if (material.equals(Material.IRON_SWORD)) {
            damage = 7.0;
        } else if (material.equals(Material.DIAMOND_SWORD)) {
            damage = 8.0;
        } else if (material.equals(Material.NETHERITE_SWORD)) {
            damage = 9.0;
        }

        if(le instanceof Zombie
                || le instanceof Skeleton
                || le instanceof WitherSkeleton
                || le instanceof ZombieHorse
                || le instanceof SkeletonHorse
                || le instanceof Phantom
                || le instanceof Stray
                || le instanceof Zoglin) {
            if (offhand.getEnchantments().containsKey(Enchantment.DAMAGE_UNDEAD)) {
                var level = offhand.getEnchantments().get(Enchantment.DAMAGE_UNDEAD);
                damage += 2.5 * level;
            }
        } else if (le instanceof Spider) {
            if (offhand.getEnchantments().containsKey(Enchantment.DAMAGE_ARTHROPODS)) {
                var level = offhand.getEnchantments().get(Enchantment.DAMAGE_ARTHROPODS);
                damage += 2.5 * level;
            }
        } else {
            if (offhand.getEnchantments().containsKey(Enchantment.DAMAGE_ALL)) {
                var level = offhand.getEnchantments().get(Enchantment.DAMAGE_ALL);
                damage += 0.5 * Math.max(0, level - 1) + 1.0;
            }
        }

        return damage / 2;
    }

    private BlockData getWarhammerParticleEffect(@NotNull Material mat) {
        BlockData data = null;
        switch (mat) {
            case WOODEN_SHOVEL -> data = Material.OAK_PLANKS.createBlockData();
            case STONE_SHOVEL -> data = Material.COBBLESTONE.createBlockData();
            case GOLDEN_SHOVEL -> data = Material.GOLD_BLOCK.createBlockData();
            case IRON_SHOVEL -> data = Material.IRON_BLOCK.createBlockData();
            case DIAMOND_SHOVEL -> data = Material.DIAMOND_BLOCK.createBlockData();
            case NETHERITE_SHOVEL -> data = Material.NETHERITE_BLOCK.createBlockData();
        }

        return data;
    }

    private void damageItem(@NotNull ItemStack item, Map<Enchantment, Integer> enchantments, int amount) {
        var damageable = (Damageable) (item.getItemMeta());
        if (damageable != null) {
            if (enchantments != null) {
                if (enchantments.containsKey(Enchantment.DURABILITY)) {
                    int random = new Random().nextInt(enchantments.get(Enchantment.DURABILITY));
                    if (random == 0) {
                        damageable.setDamage(damageable.getDamage() + amount);
                    }
                } else {
                    damageable.setDamage(damageable.getDamage() + amount);
                }
            } else {
                damageable.setDamage(damageable.getDamage() + amount);
            }
            item.setItemMeta(damageable);
        }
    }

    private void activateCooldown(Player player) {
        cooldown.put(player, System.currentTimeMillis());
    }

    private boolean hasCooldown(Player player) {
        if (cooldown == null || !(cooldown.containsKey(player))) {
            return false;
        }
        return cooldown.get(player) >= System.currentTimeMillis() - seconds;
    }
}
