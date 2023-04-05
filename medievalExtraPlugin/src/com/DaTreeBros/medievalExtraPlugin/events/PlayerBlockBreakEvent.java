package com.DaTreeBros.medievalExtraPlugin.events;

import com.DaTreeBros.medievalExtraPlugin.items.CustomMaterial;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class PlayerBlockBreakEvent implements Listener {

    @EventHandler
    public void onBreakBlock(@NotNull BlockBreakEvent event) {
        var player = event.getPlayer();
        var offhand = player.getInventory().getItemInOffHand();
        var meta = offhand.getItemMeta();

        if (offhand.getType() == Material.AMETHYST_SHARD) {
            if (meta != null && meta.hasCustomModelData()) {
                if (meta.getCustomModelData() == CustomMaterial.HASTE_EMBLEM.getId()) {
                    player.addPotionEffect(new PotionEffect(
                            PotionEffectType.FAST_DIGGING,
                            80,
                            1,
                            false,
                            false)
                    );
                }
            }
        }
    }
}
