package com.DaTreeBros.medievalExtraPlugin.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;


public enum CustomMaterial {
    WOODEN_SCYTHE (ItemManager.createCustomWeaponItem("Wooden Scythe", Material.WOODEN_HOE, 3, 1.8), 3208),
    STONE_SCYTHE (ItemManager.createCustomWeaponItem("Stone Scythe", Material.STONE_HOE, 4, 1.8), 3209),
    IRON_SCYTHE (ItemManager.createCustomWeaponItem("Iron Scythe", Material.IRON_HOE, 5, 1.8), 3210),
    GOLDEN_SCYTHE (ItemManager.createCustomWeaponItem("Golden Scythe", Material.GOLDEN_HOE, 4, 1.8), 3211),
    DIAMOND_SCYTHE (ItemManager.createCustomWeaponItem("Diamond Scythe", Material.DIAMOND_HOE, 6, 1.8), 3212),
    NETHERITE_SCYTHE (ItemManager.createCustomWeaponItem("Netherite Scythe", Material.NETHERITE_HOE, 7, 1.8), 3213),
    WOODEN_WARHAMMER (ItemManager.createCustomWeaponItem("Wooden Warhammer", Material.WOODEN_SHOVEL, 8, 0.6), 3214),
    STONE_WARHAMMER (ItemManager.createCustomWeaponItem("Stone Warhammer", Material.STONE_SHOVEL, 9, 0.6), 3215),
    IRON_WARHAMMER (ItemManager.createCustomWeaponItem("Iron Warhammer", Material.IRON_SHOVEL, 9, 0.8), 3216),
    GOLDEN_WARHAMMER (ItemManager.createCustomWeaponItem("Golden Warhammer", Material.GOLDEN_SHOVEL, 9, 0.6), 3217),
    DIAMOND_WARHAMMER (ItemManager.createCustomWeaponItem("Diamond Warhammer", Material.DIAMOND_SHOVEL, 10, 0.6), 3218),
    NETHERITE_WARHAMMER (ItemManager.createCustomWeaponItem("Netherite Warhammer", Material.NETHERITE_SHOVEL, 11, 0.6), 3219),
    HASTE_EMBLEM (ItemManager.createCustomItem("Haste Emblem", Material.AMETHYST_SHARD), 3219),
    VAMPIRE_EMBLEM (ItemManager.createCustomItem("Vampire Emblem", Material.AMETHYST_SHARD), 3220),
    IRON_DAGGER (ItemManager.createCustomWeaponItem("Iron Dagger", Material.IRON_SWORD, 5, 1.6), 3222);

    private final ItemStack item;
    private final int id;

    CustomMaterial(@NotNull ItemStack item, int id) {
        this.item = item;
        this.id = id;
        var meta = item.getItemMeta();

        if (meta != null) {
            meta.setCustomModelData(id);
            item.setItemMeta(meta);
        }
    }

    public ItemStack getItem() {
        return item;
    }

    public int getId() {
        return id;
    }
}
