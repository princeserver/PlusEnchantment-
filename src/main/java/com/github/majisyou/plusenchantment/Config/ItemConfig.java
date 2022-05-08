package com.github.majisyou.plusenchantment.Config;

import com.github.majisyou.plusenchantment.PlusEnchantment;
import org.bukkit.configuration.file.FileConfiguration;

public class ItemConfig {
    private static PlusEnchantment plugin = PlusEnchantment.getInstance();
    private static FileConfiguration config = new CustomConfig(plugin,"Item.yml").getConfig();


    public static String getItemType(String name){return config.getString("Item."+name+".ItemType");}
    public static Integer getCustomModelData(String name){return config.getInt("Item."+name+".CustomModelData");}
    public static Integer getNumberPlate(Integer number){
        return config.getInt("Item.number.CustomModelData."+number);
    }

}
