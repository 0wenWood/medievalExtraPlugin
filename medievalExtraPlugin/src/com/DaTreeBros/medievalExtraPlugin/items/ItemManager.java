package com.DaTreeBros.medievalExtraPlugin.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.*;
import org.bukkit.inventory.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ItemManager {

    private ArrayList<CustomMaterial> items = new ArrayList<>();

    public void init() {
        items.add(CustomMaterial.WOODEN_SCYTHE);
        items.add(CustomMaterial.STONE_SCYTHE);
        items.add(CustomMaterial.GOLDEN_SCYTHE);
        items.add(CustomMaterial.IRON_SCYTHE);
        items.add(CustomMaterial.DIAMOND_SCYTHE);
        items.add(CustomMaterial.NETHERITE_SCYTHE);
        items.add(CustomMaterial.WOODEN_WARHAMMER);
        items.add(CustomMaterial.STONE_WARHAMMER);
        items.add(CustomMaterial.GOLDEN_WARHAMMER);
        items.add(CustomMaterial.IRON_WARHAMMER);
        items.add(CustomMaterial.DIAMOND_WARHAMMER);
        items.add(CustomMaterial.NETHERITE_WARHAMMER);
        items.add(CustomMaterial.IRON_DAGGER);
        items.add(CustomMaterial.HASTE_EMBLEM);
        items.add(CustomMaterial.VAMPIRE_EMBLEM);
    }

    @NotNull static ItemStack createCustomItem(@NotNull String name, Material baseMaterial) {
        var item = new ItemStack(baseMaterial);
        var meta = item.getItemMeta();
        if (meta != null) {
            meta.setLocalizedName(name.toLowerCase().replace(' ', '_'));
            meta.setDisplayName(ChatColor.RESET + name);
            item.setItemMeta(meta);
        }
        return item;
    }

    @NotNull static ItemStack createCustomWeaponItem(@NotNull String name, Material baseMaterial, int damage, double speed) {
        var item = new ItemStack(baseMaterial);
        var meta = item.getItemMeta();
        if (meta != null) {

            meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
            meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);

            var damage_attribute = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage",
                    damage + 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);

            var speed_attribute = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed",
                    speed - 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);

            var lore = new ArrayList<String>();
            lore.add("");
            lore.add(ChatColor.RESET + "§7When in Main Hand: ");
            lore.add(ChatColor.DARK_GREEN + " " + damage + " Attack Damage");
            lore.add(ChatColor.DARK_GREEN + " " + speed + " Attack Speed");

            meta.setLocalizedName(name.toLowerCase().replace(' ', '_'));
            meta.setDisplayName(ChatColor.RESET + name);
            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damage_attribute);
            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, speed_attribute);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return item;
    }

    public static boolean isDualWieldingWeapon(Material mat) {
        return mat == Material.WOODEN_SWORD
                || mat == Material.STONE_SWORD
                || mat == Material.IRON_SWORD
                || mat == Material.GOLDEN_SWORD
                || mat == Material.DIAMOND_SWORD
                || mat == Material.NETHERITE_SWORD;
    }

    public static double getWarhammerAbilityRange(@NotNull Material mat) {
        switch(mat) {
            case WOODEN_SHOVEL -> { return 1; }
            case STONE_SHOVEL, GOLDEN_SHOVEL -> { return 2; }
            case IRON_SHOVEL -> { return 3; }
            case DIAMOND_SHOVEL -> { return 4; }
            case NETHERITE_SHOVEL -> { return 5; }
        }
        return 0;
    }
}
