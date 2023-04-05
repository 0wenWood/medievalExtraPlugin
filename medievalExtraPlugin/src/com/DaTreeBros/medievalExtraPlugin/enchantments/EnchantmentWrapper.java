package com.DaTreeBros.medievalExtraPlugin.enchantments;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class EnchantmentWrapper extends Enchantment {

    private final String name;
    private final int maxLevel;

    public EnchantmentWrapper(String namespace, String name, int maxLvl) {
        super(NamespacedKey.minecraft(namespace));
        this.name = name;
        this.maxLevel = maxLvl;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(@NotNull Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack itemStack) {
        return Arrays.asList(
                Material.WOODEN_SWORD,
                Material.WOODEN_SHOVEL,
                Material.WOODEN_HOE,
                Material.WOODEN_PICKAXE,
                Material.WOODEN_AXE,
                Material.STONE_SWORD,
                Material.STONE_SHOVEL,
                Material.STONE_HOE,
                Material.STONE_PICKAXE,
                Material.STONE_AXE,
                Material.GOLDEN_SWORD,
                Material.GOLDEN_SHOVEL,
                Material.GOLDEN_HOE,
                Material.GOLDEN_PICKAXE,
                Material.GOLDEN_AXE,
                Material.IRON_SWORD,
                Material.IRON_SHOVEL,
                Material.IRON_HOE,
                Material.IRON_PICKAXE,
                Material.IRON_AXE,
                Material.DIAMOND_SWORD,
                Material.DIAMOND_SHOVEL,
                Material.DIAMOND_HOE,
                Material.DIAMOND_PICKAXE,
                Material.DIAMOND_AXE,
                Material.NETHERITE_SWORD,
                Material.NETHERITE_SHOVEL,
                Material.NETHERITE_HOE,
                Material.NETHERITE_PICKAXE,
                Material.NETHERITE_AXE,
                Material.BOOK
        ).contains(itemStack.getType());
    }
}
