package com.DaTreeBros.medievalExtraPlugin.events;

import com.DaTreeBros.medievalExtraPlugin.items.CustomMaterial;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SmithingTableInteractionEvent implements Listener {

    @EventHandler
    public void onSmithingTableInteract(@NotNull PrepareSmithingEvent event) {
        var item = event.getInventory().getItem(0);
        var item2 = event.getInventory().getItem(1);
        if (item != null && item2 != null) {
            if (item.getItemMeta() != null && item2.equals(new ItemStack(Material.NETHERITE_INGOT))) {
                var meta = item.getItemMeta();
                if (meta.hasCustomModelData()) {
                    ItemStack newItem = null;
                    if (meta.getCustomModelData() == CustomMaterial.DIAMOND_SCYTHE.getId()) {
                        newItem = CustomMaterial.NETHERITE_SCYTHE.getItem();
                    } else if (meta.getCustomModelData() == CustomMaterial.NETHERITE_WARHAMMER.getId()) {
                        newItem = CustomMaterial.NETHERITE_WARHAMMER.getItem();
                    }
                    if (newItem != null) {
                        newItem.setItemMeta(meta.clone());
                        event.setResult(newItem);
                    }
                }
            }
        }
    }
}
