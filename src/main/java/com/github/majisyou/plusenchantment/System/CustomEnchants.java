package com.github.majisyou.plusenchantment.System;

import com.github.majisyou.plusenchantment.Config.EnchantmentManager;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CustomEnchants {

    private static final PlusEnchantment plugin = PlusEnchantment.getInstance();
    private static List<Enchantment> enchantments = new ArrayList<>();
    private static List<Enchantment> curseEnchants = new ArrayList<>();
    public static final Enchantment TELEKINESIS = new EnchantmentWrapper("telekinesis","テレキネシス",5,false);
    public static final Enchantment DOUBLE_EDGE_CURSE = new EnchantmentWrapper("double_edge_curse","諸刃の呪い",1,true);
//    public static final Enchantment FLY_MINING = new EnchantmentWrapper("fly_mining","FLY_MINING",1);
    public static final Enchantment DESTROY_CURSE = new EnchantmentWrapper("destroy_curse","自壊の呪い",1,true);
    public static final Enchantment RAINBOW_SHAVE = new EnchantmentWrapper("rainbow_shave","虹の剪毛",1,false);
    public static final Enchantment AUTO_SMELT = new EnchantmentWrapper("auto_smelt","自動精錬",5,false);
    public static final Enchantment HEAD_CUT = new EnchantmentWrapper("head_cut","頭蓋崇拝",10,false);
    public static final Enchantment DURABILITY_PLUS = new EnchantmentWrapper("durability_plus","強･耐久力",10,false);
    public static final Enchantment SOUL_BIND = new EnchantmentWrapper("soul_bind","ソウルバインド",10,false);
    public static final Enchantment PRESENTIMENT = new EnchantmentWrapper("presentiment","虫の知らせ",1,false);
    public static final Enchantment HARVEST_DANCE = new EnchantmentWrapper("harvest_dance","豊穣の舞",10,false);
    public static final Enchantment OFFER_ENCHANT = new EnchantmentWrapper("offer_enchant","§kOffer_Enchant",1,false);

    public static final Enchantment Re_Plant = new EnchantmentWrapper("re_plant","再配置",1,false);
    public static List<Enchantment> customTable = new ArrayList<>();

    public static void register(){
        enchantments.clear();
        enchantments.add(TELEKINESIS);
        enchantments.add(DOUBLE_EDGE_CURSE);
        enchantments.add(DESTROY_CURSE);
        enchantments.add(RAINBOW_SHAVE);
        enchantments.add(AUTO_SMELT);
        enchantments.add(HEAD_CUT);
        enchantments.add(PRESENTIMENT);
        enchantments.add(HARVEST_DANCE);
        enchantments.add(Re_Plant);
//        enchantments.add(DURABILITY_PLUS);
        enchantments.add(SOUL_BIND);

        for (Enchantment ench : enchantments){
            boolean registered = Arrays.stream(Enchantment.values()).toList().contains(ench);
            if (!registered)
                registerEnchantment(ench);
        }

        boolean registered = Arrays.stream(Enchantment.values()).toList().contains(OFFER_ENCHANT);
        if (!registered)
            registerEnchantment(OFFER_ENCHANT);

        for (String name : EnchantmentManager.getCustomNewEnchantList()){
            addEnchantTableList(name);
        }
    }

    public static List<Enchantment> getEnchantmentList(){
        return enchantments;
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
            plugin.getLogger().info("(PE)"+enchantment.getName()+"のエンチャントを追加しました");
            //send message to console
        }
    }

    public static void addEnchantTableList(String name){
        switch (name){
            case "テレキネシス" ->{
                customTable.add(TELEKINESIS);
            }
            case "諸刃の呪い" ->{
                customTable.add(DOUBLE_EDGE_CURSE);
            }
            case "自壊の呪い" ->{
                customTable.add(DESTROY_CURSE);
            }
            case "頭蓋崇拝" ->{
                customTable.add(HEAD_CUT);
            }
            case "強･耐久力" ->{

            }
            case "ソウルバインド" ->{
                customTable.add(SOUL_BIND);
            }
            case "虫の知らせ" ->{
                customTable.add(PRESENTIMENT);
            }
            case "豊穣の舞" ->{
                customTable.add(HARVEST_DANCE);
            }
            case "自動精錬" ->{
                customTable.add(AUTO_SMELT);
            }
            case "SOUL_SPEED" ->{
                customTable.add(Enchantment.SOUL_SPEED);
            }
            case "BINDING_CURSE" ->{
                customTable.add(Enchantment.BINDING_CURSE);
            }
            case "VANISHING_CURSE" ->{
                customTable.add(Enchantment.VANISHING_CURSE);
            }
            case "FROST_WALKER" ->{
                customTable.add(Enchantment.FROST_WALKER);
            }
            case "SWIFT_SNEAK" ->{
                customTable.add(Enchantment.SWIFT_SNEAK);
            }
//            case "虹の剪毛" ->{
//                return RAINBOW_SHAVE;
//            }
            default -> {
                plugin.getLogger().info("(PE)"+name+"はカスタムエンチャントに登録されていない");
            }

        }
    }

//    public static List<Enchantment> getCuresEnchants(){
//        return curseEnchants;
//    }

    public static boolean isCustom(Enchantment enchantment){
        return enchantments.contains(enchantment);
    }
    public static boolean isCurse(Enchantment enchantment){
        return curseEnchants.contains(enchantment);
    }

    public static Enchantment getEnchant(){
        int random = new Random().nextInt(customTable.size());
        return customTable.get(random);
    }

    public static Enchantment getEnchant(String name){
        switch (name){
            case "テレキネシス" ->{
                return TELEKINESIS;
            }
            case "諸刃の剣" ->{
                return DOUBLE_EDGE_CURSE;
            }
            case "破壊の呪い" ->{
                return DESTROY_CURSE;
            }
            case "頭蓋崇拝" ->{
                return HEAD_CUT;
            }
            case "強･耐久力" ->{
                return DURABILITY_PLUS;
            }
            case "ソウルバインド" ->{
                return SOUL_BIND;
            }
            case "虫の知らせ" ->{
                return PRESENTIMENT;
            }
            case "豊穣の舞" ->{
                return HARVEST_DANCE;
            }
//            case "虹の剪毛" ->{
//                return RAINBOW_SHAVE;
//            }
            default -> {
                plugin.getLogger().info("(PE)"+name+"はカスタムエンチャントに登録されていない");
                return null;
            }

        }

    }


}
