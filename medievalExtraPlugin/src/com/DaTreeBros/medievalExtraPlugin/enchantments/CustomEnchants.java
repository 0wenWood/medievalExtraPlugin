package com.DaTreeBros.medievalExtraPlugin.enchantments;

import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;

public class CustomEnchants {

    public static final Enchantment HASTE = new EnchantmentWrapper("haste", "Haste", 1);

    public static void register() {
        boolean registered = Arrays.stream(Enchantment.values()).toList().contains(CustomEnchants.HASTE);
        if(!registered) {
            registerEnchantment(HASTE);
        }
    }

    public static boolean registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
        return registered;
    }
}
