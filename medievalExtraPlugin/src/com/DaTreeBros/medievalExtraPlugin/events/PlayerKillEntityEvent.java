package com.DaTreeBros.medievalExtraPlugin.events;

import com.DaTreeBros.medievalExtraPlugin.items.CustomMaterial;
import org.bukkit.entity.*;
import org.bukkit.potion.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerKillEntityEvent implements Listener {

    @EventHandler
    public void onPlayerKillEvent(@NotNull EntityDeathEvent event) {
        var player = event.getEntity().getKiller();
        if (player != null) {
            var offhand = player.getInventory().getItemInOffHand();
            if (offhand.getItemMeta() != null && offhand.getItemMeta().hasCustomModelData()) {
                if (offhand.getItemMeta().getCustomModelData() == CustomMaterial.VAMPIRE_EMBLEM.getId()) {
                    int health = getHealth(event.getEntity());
                    if (health != -1) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST,
                                400,
                                health,
                                false,
                                false)
                        );
                    }
                }
            }
        }
    }

    private int getHealth(LivingEntity entity) {
        if (entity != null) {
            if (entity instanceof Boss) {
                return 6;
            }

            if (entity instanceof WitherSkeleton
                    || entity instanceof Blaze
                    || entity instanceof Ghast
                    || entity instanceof IronGolem) {
                return 3;
            }

            if (entity instanceof Monster
                    || entity instanceof Slime
                    || entity instanceof Snowman) {
                return 2;
            }

            return 1;
        }
        return -1;
    }
}
