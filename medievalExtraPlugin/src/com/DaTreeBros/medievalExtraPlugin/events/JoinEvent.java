package com.DaTreeBros.medievalExtraPlugin.events;

import org.bukkit.event.EventHandler;
import org.jetbrains.annotations.NotNull;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent e) {
        final var dropbox = "https://www.dropbox.com/s/47f50fr219cwzvm/Medieval_Extra_Textures.zip?dl=1";
        e.getPlayer().setResourcePack(dropbox);
    }
}
