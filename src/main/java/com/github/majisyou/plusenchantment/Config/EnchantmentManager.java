package com.github.majisyou.plusenchantment.Config;

import com.github.majisyou.plusenchantment.PlusEnchantment;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnchantmentManager {
    private static PlusEnchantment plugin = PlusEnchantment.getInstance();
    private static FileConfiguration config = plugin.getConfig();

    private static Integer AddCoefficient;
    private static Integer ScrapCoefficient;
    private static Integer MaxLevel;
    private static List<String> ConflictEnchant;
    private static List<String> PossibleItem;
    private static List<String> ItemList;
    private static List<String> BrokenItemList;
    private static Integer ScrapRate;
    private static Integer NeedItems;
    private static Map<String,Integer> ScrapRepair = new HashMap<>();
    private static List<Integer> customs = new ArrayList<>();
    private static List<String> customNewEnchantList = new ArrayList<>();

    public static void loadEnchantment(){
        ItemList = config.getStringList("ItemList");
        BrokenItemList = config.getStringList("BrokenItem");
        customs = config.getIntegerList("CustomModel");
        customNewEnchantList = config.getStringList("CustomEnchantTable.NewEnchantmentList");
    }

    public static void loadEnchantment(String EnchantName){
        String path = "Enchantment."+EnchantName;
        AddCoefficient = config.getInt(path+".AddCoefficient");
        MaxLevel = config.getInt(path+".MaxLevel");
        ConflictEnchant = config.getStringList(path+".ConflictEnchant");
        PossibleItem = config.getStringList(path+".PossibleItem");
        ScrapRepair = new HashMap<>();
        Integer scrap = config.getInt("ScrapRepairCost.scrap");
        ScrapRepair.put("scrap",scrap);
        Integer Repair = config.getInt("ScrapRepairCost.Repair");
        ScrapRepair.put("Repair",Repair);
    }

    public static Integer getAddCoefficient(){return AddCoefficient;}
    public static Integer getMaxLevel(){return MaxLevel;}
    public static List<String> getConflictEnchant(){return ConflictEnchant;}
    public static List<String> getPossibleItem(){return PossibleItem;}
    public static List<String> getItemList(){return ItemList;}
    public static List<String> getBrokenItemList(){return BrokenItemList;}
    public static Integer getScrapRate(String ItemType){
        ScrapRate = config.getInt("ScrapRate."+ItemType+".scrap");
        return ScrapRate;
    }
    public static Integer getNeedItems(String ItemType){
        NeedItems = config.getInt("ScrapRate."+ItemType+".Need");
        return NeedItems;
    }
    public static Integer getScrapCoefficient(String ItemType){
        ScrapCoefficient = config.getInt("ScrapRepairRate."+ItemType);
        return ScrapCoefficient;
    }
    public static Map<String,Integer> getScrapRepair(){
        return ScrapRepair;
    }
    public static List<Integer> getCustoms(){return customs;}
    public static void reloadConfig(){
        config = plugin.getConfig();
    }
    public static List<String> getCustomNewEnchantList(){return customNewEnchantList;}


    public static Integer getSoulRate(String ItemType){
        return config.getInt("SoulRate."+ItemType+".soul");
    }
    public static Integer getNeedSoulItems(String ItemType){
        return config.getInt("SoulRate."+ItemType+".Need");
    }


    /*
  - TELEKINESIS
  - DOUBLE_EDGE_CURSE
  - DESTROY_CURSE
  - RAINBOW_SHAVE
  - AUTO_SMELT
  - HEAD_CUT
  - DURABILITY_PLUS
  - SOUL_BIND
     */
}
