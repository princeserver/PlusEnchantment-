package com.github.majisyou.plusenchantment.System;

import com.github.majisyou.plusenchantment.PlusEnchantment;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;

public class CustomEnchants {

    private static final PlusEnchantment plugin = PlusEnchantment.getInstance();

    public static final Enchantment TELEPATHY = new EnchantmentWrapper("telepathy","TELEPATHY",1);

    public static void register(){
        boolean registered = Arrays.stream(Enchantment.values()).toList().contains(CustomEnchants.TELEPATHY);

        if (!registered)
            registerEnchantment(TELEPATHY);
    }

    public static void registerEnchantment(Enchantment enchantment){
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null,true);
            Enchantment.registerEnchantment(enchantment);
        }catch (Exception e){
            registered = false;
            e.printStackTrace();
        }
        if(registered){

            //send message to console
        }
    }

}
