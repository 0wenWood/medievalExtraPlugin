package com.DaTreeBros.medievalExtraPlugin.items;

import com.DaTreeBros.medievalExtraPlugin.medievalExtraPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;

public record ItemCrafting(medievalExtraPlugin plugin) {

    public ItemCrafting(@NotNull medievalExtraPlugin plugin) {
        this.plugin = plugin;
    }

    public void init() {
        Bukkit.addRecipe(getWoodenScytheRecipe());
        Bukkit.addRecipe(getStoneScytheRecipe());
        Bukkit.addRecipe(getIronScytheRecipe());
        Bukkit.addRecipe(getGoldenScytheRecipe());
        Bukkit.addRecipe(getDiamondScytheRecipe());
        Bukkit.addRecipe(getNetheriteScytheRecipe());
        Bukkit.addRecipe(getWoodenWarhammerRecipe());
        Bukkit.addRecipe(getStoneWarhammerRecipe());
        Bukkit.addRecipe(getIronWarhammerRecipe());
        Bukkit.addRecipe(getGoldenWarhammerRecipe());
        Bukkit.addRecipe(getDiamondWarhammerRecipe());
        Bukkit.addRecipe(getNetheriteWarhammerRecipe());
        Bukkit.addRecipe(getHasteEmblemRecipe());
        Bukkit.addRecipe(getVampireEmblemRecipe());
        Bukkit.addRecipe(getIronDaggerRecipe());
    }

    private ShapedRecipe getWoodenScytheRecipe() {
        var mat = CustomMaterial.WOODEN_SCYTHE;
        assert mat.getItem().getItemMeta() != null;
        var key = new NamespacedKey(plugin, mat.getItem().getItemMeta().getLocalizedName());
        var recipe = new ShapedRecipe(key, mat.getItem());

        recipe.shape("WWW", " S ", "S  ");

        recipe.setIngredient('W', new RecipeChoice
                        .MaterialChoice(Material.ACACIA_PLANKS, Material.BIRCH_PLANKS,
                        Material.CRIMSON_PLANKS, Material.OAK_PLANKS, Material.DARK_OAK_PLANKS,
                        Material.JUNGLE_PLANKS, Material.ACACIA_PLANKS, Material.SPRUCE_PLANKS,
                        Material.WARPED_PLANKS
                )
        );

        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    private ShapedRecipe getStoneScytheRecipe() {
        var mat = CustomMaterial.STONE_SCYTHE;
        assert mat.getItem().getItemMeta() != null;
        var key = new NamespacedKey(plugin, mat.getItem().getItemMeta().getLocalizedName());
        var recipe = new ShapedRecipe(key, mat.getItem());

        recipe.shape("TTT", " S ", "S  ");

        recipe.setIngredient('T', new RecipeChoice
                        .MaterialChoice(
                        Material.COBBLED_DEEPSLATE, Material.COBBLESTONE, Material.BASALT
                )
        );

        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    private ShapedRecipe getIronScytheRecipe() {
        var mat = CustomMaterial.IRON_SCYTHE;
        assert mat.getItem().getItemMeta() != null;
        var key = new NamespacedKey(plugin, mat.getItem().getItemMeta().getLocalizedName());
        var recipe = new ShapedRecipe(key, mat.getItem());

        recipe.shape("III", " S ", "S  ");
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    private ShapedRecipe getGoldenScytheRecipe() {
        var mat = CustomMaterial.GOLDEN_SCYTHE;
        assert mat.getItem().getItemMeta() != null;
        var key = new NamespacedKey(plugin, mat.getItem().getItemMeta().getLocalizedName());
        var recipe = new ShapedRecipe(key, mat.getItem());

        recipe.shape("GGG", " S ", "S  ");
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    private ShapedRecipe getDiamondScytheRecipe() {
        var mat = CustomMaterial.DIAMOND_SCYTHE;
        assert mat.getItem().getItemMeta() != null;
        var key = new NamespacedKey(plugin, mat.getItem().getItemMeta().getLocalizedName());
        var recipe = new ShapedRecipe(key, mat.getItem());

        recipe.shape("DDD", " S ", "S  ");
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    private ShapedRecipe getNetheriteScytheRecipe() {
        var mat = CustomMaterial.NETHERITE_SCYTHE;
        assert mat.getItem().getItemMeta() != null;
        var key = new NamespacedKey(plugin, mat.getItem().getItemMeta().getLocalizedName());
        var recipe = new ShapedRecipe(key, mat.getItem());

        recipe.shape("NNN", " S ", "S  ");
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    private ShapedRecipe getWoodenWarhammerRecipe() {
        var mat = CustomMaterial.WOODEN_WARHAMMER;
        assert mat.getItem().getItemMeta() != null;
        var key = new NamespacedKey(plugin, mat.getItem().getItemMeta().getLocalizedName());
        var recipe = new ShapedRecipe(key, mat.getItem());

        recipe.shape("WWW", "WSW", " S ");

        recipe.setIngredient('W', new RecipeChoice
                        .MaterialChoice(Material.ACACIA_PLANKS, Material.BIRCH_PLANKS,
                        Material.CRIMSON_PLANKS, Material.OAK_PLANKS, Material.DARK_OAK_PLANKS,
                        Material.JUNGLE_PLANKS, Material.ACACIA_PLANKS, Material.SPRUCE_PLANKS,
                        Material.WARPED_PLANKS
                )
        );

        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    private ShapedRecipe getStoneWarhammerRecipe() {
        var mat = CustomMaterial.STONE_WARHAMMER;
        assert mat.getItem().getItemMeta() != null;
        var key = new NamespacedKey(plugin, mat.getItem().getItemMeta().getLocalizedName());
        var recipe = new ShapedRecipe(key, mat.getItem());

        recipe.shape("TTT", "TST", " S ");

        recipe.setIngredient('T', new RecipeChoice
                        .MaterialChoice(
                        Material.COBBLED_DEEPSLATE, Material.COBBLESTONE, Material.BASALT
                )
        );

        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    private ShapedRecipe getIronWarhammerRecipe() {
        var mat = CustomMaterial.IRON_WARHAMMER;
        assert mat.getItem().getItemMeta() != null;
        var key = new NamespacedKey(plugin, mat.getItem().getItemMeta().getLocalizedName());
        var recipe = new ShapedRecipe(key, mat.getItem());

        recipe.shape("III", "ISI", " S ");
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    private ShapedRecipe getGoldenWarhammerRecipe() {
        var mat = CustomMaterial.GOLDEN_WARHAMMER;
        assert mat.getItem().getItemMeta() != null;
        var key = new NamespacedKey(plugin, mat.getItem().getItemMeta().getLocalizedName());
        var recipe = new ShapedRecipe(key, mat.getItem());

        recipe.shape("GGG", "GSG", " S ");
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    private ShapedRecipe getDiamondWarhammerRecipe() {
        var mat = CustomMaterial.DIAMOND_WARHAMMER;
        assert mat.getItem().getItemMeta() != null;
        var key = new NamespacedKey(plugin, mat.getItem().getItemMeta().getLocalizedName());
        var recipe = new ShapedRecipe(key, mat.getItem());

        recipe.shape("DDD", "DSD", " S ");
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    private ShapedRecipe getNetheriteWarhammerRecipe() {
        var mat = CustomMaterial.NETHERITE_WARHAMMER;
        assert mat.getItem().getItemMeta() != null;
        var key = new NamespacedKey(plugin, mat.getItem().getItemMeta().getLocalizedName());
        var recipe = new ShapedRecipe(key, mat.getItem());

        recipe.shape("NNN", "NSN", " S ");
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }
    
    private ShapedRecipe getHasteEmblemRecipe() {
        var mat = CustomMaterial.HASTE_EMBLEM;
        assert mat.getItem().getItemMeta() != null;
        var key = new NamespacedKey(plugin, mat.getItem().getItemMeta().getLocalizedName());
        var recipe = new ShapedRecipe(key, mat.getItem());

        recipe.shape("SIS", "IGI", "SIS");
        recipe.setIngredient('G', Material.GOLD_BLOCK);
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STONE);

        return recipe;
    }

    private ShapedRecipe getVampireEmblemRecipe() {
        var mat = CustomMaterial.VAMPIRE_EMBLEM;
        assert mat.getItem().getItemMeta() != null;
        var key = new NamespacedKey(plugin, mat.getItem().getItemMeta().getLocalizedName());
        var recipe = new ShapedRecipe(key, mat.getItem());

        recipe.shape("SRS", "RBR", "SRS");
        recipe.setIngredient('B', Material.BONE_BLOCK);
        recipe.setIngredient('R', Material.REDSTONE_BLOCK);
        recipe.setIngredient('S', Material.STONE);

        return recipe;
    }

    private ShapedRecipe getIronDaggerRecipe() {
        var mat = CustomMaterial.IRON_DAGGER;
        assert mat.getItem().getItemMeta() != null;
        var key = new NamespacedKey(plugin, mat.getItem().getItemMeta().getLocalizedName());
        var recipe = new ShapedRecipe(key, mat.getItem());

        recipe.shape("I ", " S");
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }
}
