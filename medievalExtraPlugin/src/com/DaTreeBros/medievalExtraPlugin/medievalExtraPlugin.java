package com.DaTreeBros.medievalExtraPlugin;

import com.DaTreeBros.medievalExtraPlugin.events.*;
import com.DaTreeBros.medievalExtraPlugin.items.CustomMaterial;
import com.DaTreeBros.medievalExtraPlugin.items.ItemCrafting;
import com.DaTreeBros.medievalExtraPlugin.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class medievalExtraPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        new ItemManager().init();
        registerEvents();
        new ItemCrafting(this).init();

/*
        getCommand("enchantHaste").setExecutor(new MedievalExtraCommands());
        getServer().getPluginManager().registerEvents(new EnchantmentTableEvent(), this);
        CustomEnchants.register();

        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            if (!(Bukkit.getOnlinePlayers().isEmpty())) {
                for (var p : Bukkit.getOnlinePlayers()) {
                    if (p.getInventory().getItemInMainHand().getItemMeta() != null) {
                        var handMeta = p.getInventory().getItemInMainHand().getItemMeta();
                        if (handMeta.hasEnchant(CustomEnchants.HASTE)) {
                            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING,
                                    40,
                                    handMeta.getEnchantLevel(CustomEnchants.HASTE) - 1,
                                    false,
                                    true));
                            getServer().getConsoleSender().sendMessage("Lore: " + handMeta.getLore());
                            if (handMeta.getLore() != null) {
                                if (!(handMeta.getLore().contains(ChatColor.GRAY + "Haste I"))) {
                                    var lore = handMeta.getLore();
                                    lore.add(0, ChatColor.GRAY + "Haste I");
                                    handMeta.setLore(lore);
                                    p.getInventory().getItemInMainHand().setItemMeta(handMeta);
                                }
                            } else {
                                handMeta.setLore(List.of(ChatColor.GRAY + "Haste I"));
                                p.getInventory().getItemInMainHand().setItemMeta(handMeta);
                            }
                        }
                    }
                }
            }
        }, 0, 20); */

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Loaded medieval extras plugin");

        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            if (!getServer().getOnlinePlayers().isEmpty()) {
                for (var entity : getServer().getWorld("world").getEntities()) {
                    if (entity instanceof Item item) {
                        var entities = item.getNearbyEntities(0.5, 0.5, 0.5);
                        var meta = item.getItemStack().getItemMeta();
                        if (!entities.isEmpty()
                                && meta != null
                                && meta.hasCustomModelData()
                                && meta.getCustomModelData() == CustomMaterial.IRON_DAGGER.getId()) {
                            entities.forEach((e) -> {
                                if (e instanceof Damageable d) {
                                    if (item.getThrower() != null && !d.getUniqueId().equals(item.getThrower())) {
                                        d.damage(5, getServer().getPlayer(item.getThrower()));
                                        item.setThrower(null);
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }, 0L, 5L);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Closed medieval extras plugin successfully");
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new AnvilInteractEvent(this), this);
        getServer().getPluginManager().registerEvents(new SmithingTableInteractionEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerPickUpEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractionEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerBlockBreakEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerKillEntityEvent(), this);
    }
}
