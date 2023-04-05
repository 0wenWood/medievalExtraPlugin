package com.DaTreeBros.medievalExtraPlugin.events;

import com.DaTreeBros.medievalExtraPlugin.items.CustomMaterial;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PlayerPickUpEvent implements Listener {

    @EventHandler
    public static void onPlayerPickUp(@NotNull PlayerPickupArrowEvent event) {
        Arrow arrow = (Arrow)event.getArrow();
        if (Objects.equals(arrow.getCustomName(), "Dagger")) {
            event.getPlayer().getInventory().addItem(CustomMaterial.IRON_DAGGER.getItem());
            if (event.getPlayer().getInventory().containsAtLeast(new ItemStack(Material.ARROW), 0)) {
                for (int i = 0; i < event.getPlayer().getInventory().getSize(); i++) {
                    var slot = event.getPlayer().getInventory().getItem(i);
                    if (slot != null) {
                        if (slot.getType().equals(Material.ARROW)) {
                            slot.setAmount(slot.getAmount() - 1);
                        }
                    }
                }

            }
        }
    }
}
