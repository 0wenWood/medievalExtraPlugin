package com.DaTreeBros.medievalExtraPlugin.commands;

import com.DaTreeBros.medievalExtraPlugin.enchantments.CustomEnchants;
import com.DaTreeBros.medievalExtraPlugin.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MedievalExtraCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if(sender instanceof Player p){
            if (cmd.getName().equalsIgnoreCase("enchantHaste")) {
                if (p.getInventory().getItemInMainHand().getType() != Material.AIR) {
                    var hand = p.getInventory().getItemInMainHand();
                    hand.addUnsafeEnchantment(CustomEnchants.HASTE, 1);
                    p.sendMessage("Added Haste to Item!");
                    return true;
                }
            }
        }
        sender.sendMessage(ChatColor.RED + "Only Players can use this command");
        return true;
    }
}
