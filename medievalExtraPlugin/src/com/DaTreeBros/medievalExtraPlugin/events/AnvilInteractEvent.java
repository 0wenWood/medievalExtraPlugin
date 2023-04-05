package com.DaTreeBros.medievalExtraPlugin.events;

import com.DaTreeBros.medievalExtraPlugin.medievalExtraPlugin;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

public record AnvilInteractEvent(medievalExtraPlugin plugin) implements Listener {

    @EventHandler
    public void onAnvilInteract(@NotNull PrepareAnvilEvent event) {
        ItemStack item = event.getInventory().getItem(0);
        ItemStack secondary = event.getInventory().getItem(1);

        if (item != null && secondary != null) {
            ItemStack result = item.clone();

            if (result.getItemMeta() != null) {
                if (result.getItemMeta().hasCustomModelData() && result.getItemMeta().getCustomModelData() > 3200) {
                    if (secondary.getType().equals(Material.ENCHANTED_BOOK)) {
                        var enchantmentData = ((EnchantmentStorageMeta) (secondary.getItemMeta())).getStoredEnchants();
                        for (var enchantment : enchantmentData.keySet()) {
                            if (canEnchantCustomItem(enchantment, item)) {
                                result.addUnsafeEnchantment(enchantment, enchantmentData.get(enchantment));
                            }
                        }

                        event.setResult(result);
                        plugin.getServer().getScheduler().runTask(plugin,
                                () -> event.getInventory().setRepairCost(5));
                    }
                }
            }
        }
    }

    private boolean canEnchantCustomItem(@NotNull Enchantment enchantment, @NotNull ItemStack item) {
        var canEnchant = new AtomicBoolean(enchantment.canEnchantItem(item));

        if (enchantment.canEnchantItem(new ItemStack(Material.WOODEN_SWORD))) {
            canEnchant.set(true);
        }

        if (!(item.getEnchantments().isEmpty())) {
            for (var ench : item.getEnchantments().keySet()) {
                if (ench.equals(enchantment) || ench.conflictsWith(enchantment)) {
                    canEnchant.set(false);
                }
            }
        }

        return canEnchant.get();
    }

}
