package com.github.majisyou.plusenchantment.System;

import com.github.majisyou.plusenchantment.Config.EnchantmentManager;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import org.bukkit.enchantments.Enchantment;

import java.util.*;

public class CustomEnchantManager {
//    private static List<Enchantment> enchantments = new ArrayList<>();
//    private static List<Enchantment> getEnchantmentList = new ArrayList<>();

//    private static Map<Enchantment,Integer> enchantmentLvList = new HashMap<>();
//    private static Integer lv;
//    private static PlusEnchantment plugin = PlusEnchantment.getInstance();
//    ////////
//    // 本棚＋ソウルの数でエンチャントが決まる
//    // lv が決まる。
//    // lvの範囲にあるエンチャントを取得する
//    //
//
//
//
//    public static int calcCustomEnchantLv(int soul,int shelf){
//        shelf = Math.min(shelf,20);
//        int Base;
//        if(shelf==0){
//            Base = new Random().nextInt(9);
//        }else {
//            Base = new Random().nextInt(9)+ shelf/2 + new Random().nextInt(shelf);
//
//        }
//
//        lv = 0;
//        switch (soul){
//            case 1 ->{
//                lv = Math.max(Base/3,1);
//            }
//            case 2 ->{
//                lv = (Base*2)/3+1;
//            }
//            case 3 ->{
//                lv = Math.max(Base,shelf*2);
//            }
//            default -> {
//                plugin.getLogger().info("(ES)"+"カスタムの必要エンチャントレベルを計算する時にエラーが出た");
//            }
//        }
//        double random = new Random().nextDouble(2)+1;
//        lv = (int) (lv * (random));
////        plugin.getLogger().info(lv+"の値");
//        return Math.min(lv,100);
//    }
//
//    public static int calExpCost(int soul,int shelf){
//        return Math.max(shelf*soul/4,soul);
//    }
//
////    private static void loadList(int lv){
////        enchantmentLvList.clear();
////        for (String enchantName: EnchantmentManager.getCustomEnchantList()){
////            Enchantment enchantment = Enchantment.getByName(enchantName);
////            if(enchantment == null){
////                plugin.getLogger().info("(PE)"+enchantName+"のエンチャントがエンチャントとして認識されなかった");
////                continue;
////            }
////            AddList(enchantment,lv);
////        }
////    }
//
//    private static Enchantment getEnchant(){
//        int index = new Random().nextInt(enchantmentLvList.size());
//        List<Enchantment> keys = new ArrayList<>(enchantmentLvList.keySet());
////        if(CustomEnchants.isCustom(keys.get(index))){
////            plugin.getLogger().info(keys.get(index).getName()+keys.get(index).getKey().getKey());
////        }
//        return keys.get(index);
//    }
//
//    private static int getEnchantLevel(Enchantment enchantment){
//        return enchantmentLvList.get(enchantment);
//    }
//
////    public static Map<Enchantment,Integer> getEnchantmentMap(int soul,int shelf){
////        int lv = calcCustomEnchantLv(soul,shelf);
////        Map<Enchantment,Integer> result = new HashMap<>();
////        loadList(lv);
////        Enchantment enchantment = getEnchant();
////        result.put(enchantment,getEnchantLevel(enchantment));
////        removeConflict(enchantment);
////        while (new Random().nextDouble() < ((double) lv)/50){
////            enchantment = getEnchant();
////            result.put(enchantment,getEnchantLevel(enchantment));
////            removeConflict(enchantment);
////            lv /= 2;
////        }
////        return result;
////    }
//
//    private static void removeConflict(Enchantment enchantment){
//        EnchantmentManager.loadEnchantment(enchantment.getName());
//        List<Enchantment> enchantments = new ArrayList<>();
//        for (Enchantment ench:enchantmentLvList.keySet()){
//            if(EnchantmentManager.getConflictEnchant().contains(ench.getName())){
//                enchantments.add(ench);
//            }
//        }
//        for (Enchantment ench:enchantments){
//            enchantmentLvList.remove(ench);
//        }
//    }
//
//
//    public static void AddList(Enchantment enchantment,Integer lv){
////        plugin.getLogger().info(enchantment.getKey().getKey());
//        switch (enchantment.getKey().getKey()) {
//            case "unbreaking" -> {
//                if (lv >= 90 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,4);
//                }
//                if (lv >= 21 && lv <= 71) {
//                    enchantmentLvList.put(enchantment,3);
//                }
//                if (lv >= 13 && lv <= 63) {
//                    enchantmentLvList.put(enchantment,2);
//                }
//                if (lv >= 5 && lv <= 55) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//            case "power" -> {
//                if (lv >= 90 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,6);
//                }
//                if (lv >= 41 && lv <= 56) {
//                    enchantmentLvList.put(enchantment,5);
//                }
//                if (lv >= 31 && lv <= 46) {
//                    enchantmentLvList.put(enchantment,4);
//                }
//                if (lv >= 21 && lv <= 36) {
//                    enchantmentLvList.put(enchantment,3);
//                }
//                if (lv >= 11 && lv <= 26) {
//                    enchantmentLvList.put(enchantment,2);
//                }
//                if (lv >= 1 && lv <= 16) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//            case "flame", "infinity", "channeling", "loyalty", "multishot", "piercing" -> {
//                if (lv >= 20 && lv <= 70) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//            case "punch" -> {
//                if (lv >= 90 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,3);
//                }
//                if (lv >= 32 && lv <= 57) {
//                    enchantmentLvList.put(enchantment,2);
//                }
//                if (lv >= 12 && lv <= 37) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//            case "binding_curse", "vanishing_curse" -> {
//                if (lv >= 25 && lv <= 90) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//
//            case "sharpness", "sweeping" -> {
//                if (lv >= 90 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,6);
//                }
//                if (lv >= 45 && lv <= 65) {
//                    enchantmentLvList.put(enchantment,5);
//                }
//                if (lv >= 34 && lv <= 54) {
//                    enchantmentLvList.put(enchantment,4);
//                }
//                if (lv >= 23 && lv <= 43) {
//                    enchantmentLvList.put(enchantment,3);
//                }
//                if (lv >= 12 && lv <= 32) {
//                    enchantmentLvList.put(enchantment,2);
//                }
//                if (lv >= 1 && lv <= 21) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//
//            }
//            case "bane_of_arthropods", "smite", "impaling" -> {
//                if (lv >= 90 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,6);
//                }
//                if (lv >= 37 && lv <= 57) {
//                    enchantmentLvList.put(enchantment,5);
//                }
//                if (lv >= 29 && lv <= 49) {
//                    enchantmentLvList.put(enchantment,4);
//                }
//                if (lv >= 21 && lv <= 41) {
//                    enchantmentLvList.put(enchantment,3);
//                }
//                if (lv >= 13 && lv <= 33) {
//                    enchantmentLvList.put(enchantment,2);
//                }
//                if (lv >= 5 && lv <= 25) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//            case "aqua_affinity" -> {
//                if (lv >= 90 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,4);
//                }
//                if (lv >= 30 && lv <= 45) {
//                    enchantmentLvList.put(enchantment,3);
//                }
//                if (lv >= 20 && lv <= 35) {
//                    enchantmentLvList.put(enchantment,2);
//                }
//                if (lv >= 10 && lv <= 25) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//
//            }
//            case "efficiency" -> {
//                if (lv >= 90 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,6);
//                }
//                if (lv >= 41 && lv <= 91) {
//                    enchantmentLvList.put(enchantment,5);
//                }
//                if (lv >= 31 && lv <= 81) {
//                    enchantmentLvList.put(enchantment,4);
//                }
//                if (lv >= 21 && lv <= 71) {
//                    enchantmentLvList.put(enchantment,3);
//                }
//                if (lv >= 11 && lv <= 61) {
//                    enchantmentLvList.put(enchantment,2);
//                }
//                if (lv >= 1 && lv <= 51) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//
//            }
//            case "fire_aspect" -> {
//                if (lv >= 90 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,3);
//                }
//                if (lv >= 30 && lv <= 80) {
//                    enchantmentLvList.put(enchantment,2);
//                }
//                if (lv >= 10 && lv <= 60) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//            case "frost_walker" -> {
//                if (lv >= 90 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,3);
//                }
//                if (lv >= 20 && lv <= 35) {
//                    enchantmentLvList.put(enchantment,2);
//                }
//                if (lv >= 10 && lv <= 25) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//            case "knockback", "soul_speed" -> {
//                if (lv >= 90 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,3);
//                }
//                if (lv >= 25 && lv <= 75) {
//                    enchantmentLvList.put(enchantment,2);
//                }
//                if (lv >= 5 && lv <= 55) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//            case "fortune", "luck_of_the_sea", "lure", "quick_charge", "riptide" -> {
//                if (lv >= 90 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,4);
//                }
//                if (lv >= 33 && lv <= 83) {
//                    enchantmentLvList.put(enchantment,3);
//                }
//                if (lv >= 24 && lv <= 74) {
//                    enchantmentLvList.put(enchantment,2);
//                }
//                if (lv >= 15 && lv <= 65) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//            case "looting","swift_sneak" -> {
//                if (lv >= 90 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,4);
//                }
//                if (lv >= 23 && lv <= 38) {
//                    enchantmentLvList.put(enchantment,3);
//                }
//                if (lv >= 14 && lv <= 29) {
//                    enchantmentLvList.put(enchantment,2);
//                }
//                if (lv >= 5 && lv <= 20) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//
//            }
//            case "mending" -> {
//                if (lv >= 1000 && lv <= 10001) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//
//            }
//            case "respiration" -> {
//                if (lv >= 90 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,4);
//                }
//                if (lv >= 30 && lv <= 60) {
//                    enchantmentLvList.put(enchantment,3);
//                }
//                if (lv >= 20 && lv <= 50) {
//                    enchantmentLvList.put(enchantment,2);
//                }
//                if (lv >= 10 && lv <= 60) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//
//            }
//            case "protection" -> {
//                if (lv >= 90 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,5);
//                }
//                if (lv >= 34 && lv <= 54) {
//                    enchantmentLvList.put(enchantment,4);
//                }
//                if (lv >= 23 && lv <= 43) {
//                    enchantmentLvList.put(enchantment,3);
//                }
//                if (lv >= 12 && lv <= 32) {
//                    enchantmentLvList.put(enchantment,2);
//                }
//                if (lv >= 1 && lv <= 21) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//
//            }
//            case "blast_protection" -> {
//                if (lv >= 90 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,5);
//                }
//                if (lv >= 29 && lv <= 41) {
//                    enchantmentLvList.put(enchantment,4);
//                }
//                if (lv >= 21 && lv <= 33) {
//                    enchantmentLvList.put(enchantment,3);
//                }
//                if (lv >= 13 && lv <= 25) {
//                    enchantmentLvList.put(enchantment,2);
//                }
//                if (lv >= 5 && lv <= 17) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//
//            }
//            case "feather_falling" -> {
//                if (lv >= 90 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,5);
//                }
//                if (lv >= 23 && lv <= 33) {
//                    enchantmentLvList.put(enchantment,4);
//                }
//                if (lv >= 17 && lv <= 27) {
//                    enchantmentLvList.put(enchantment,3);
//                }
//                if (lv >= 11 && lv <= 21) {
//                    enchantmentLvList.put(enchantment,2);
//                }
//                if (lv >= 5 && lv <= 15) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//
//            }
//            case "fire_protection" -> {
//                if (lv >= 90 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,5);
//                }
//                if (lv >= 34 && lv <= 46) {
//                    enchantmentLvList.put(enchantment,4);
//                }
//                if (lv >= 26 && lv <= 38) {
//                    enchantmentLvList.put(enchantment,3);
//                }
//                if (lv >= 18 && lv <= 30) {
//                    enchantmentLvList.put(enchantment,2);
//                }
//                if (lv >= 10 && lv <= 20) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//
//            }
//            case "projectile_protection" -> {
//                if (lv >= 90 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,5);
//                }
//                if (lv >= 21 && lv <= 36) {
//                    enchantmentLvList.put(enchantment,4);
//                }
//                if (lv >= 15 && lv <= 30) {
//                    enchantmentLvList.put(enchantment,3);
//                }
//                if (lv >= 9 && lv <= 24) {
//                    enchantmentLvList.put(enchantment,2);
//                }
//                if (lv >= 3 && lv <= 18) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//            case "silk_touch" -> {
//                if (lv >= 15 && lv <= 65) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//            case "thorns" -> {
//                if (lv >= 90 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,4);
//                }
//                if (lv >= 50 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,3);
//                }
//                if (lv >= 30 && lv <= 80) {
//                    enchantmentLvList.put(enchantment,2);
//                }
//                if (lv >= 10 && lv <= 60) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//            case "depth_strider" -> {
//                if (lv >= 1 && lv <= 41) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//
//            case "telekinesis" -> {
//                if (lv >= 1 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//            case "double_edge_curse" -> {
//                if (lv >= 1 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//            case "destroy_curse" -> {
//                if (lv >= 1 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//            case "rainbow_shave" -> {
//                if (lv >= 1 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//            case "auto_smelt" -> {
//                if (lv >= 1 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//
//            case "head_cut" -> {
//                if (lv >= 1 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//            case "durability_plus" -> {
//                if (lv >= 1 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//            case "soul_bind" -> {
//                if (lv >= 1 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//            case "presentiment" -> {
//                if (lv >= 1 && lv <= 100) {
//                    enchantmentLvList.put(enchantment,1);
//                }
//                return;
//            }
//
//
//        }
//
//        plugin.getLogger().info("(PS)"+enchantment.getKey().getKey()+"のエンチャントがカスタムエンチャント時に間違えている");
//    }

















































//    public static void adaptiveEnchant(int soul,int shelf){
//        double base = calcCustomEnchantLv(soul,shelf);
//        double adaptive = base + new Random().nextDouble(0,1.0/4.0) + new Random().nextDouble(0,1.0/4.0) + 1;
//    }


//    public static void AddList(Enchantment enchantment,Integer lv){
//        switch (enchantment.getKey().getKey()) {
//            case "unbreaking" -> {
//                if (lv >= 21 && lv <= 71) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 13 && lv <= 63) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 5 && lv <= 55) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//            }
//            case "power" -> {
//                if (lv >= 41 && lv <= 56) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 31 && lv <= 46) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 21 && lv <= 36) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 11 && lv <= 26) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 1 && lv <= 16) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//            }
//            case "flame", "infinity", "channeling", "loyalty", "multishot", "piercing" -> {
//                if (lv >= 20 && lv <= 50) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//            }
//            case "punch" -> {
//                if (lv >= 32 && lv <= 57) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 12 && lv <= 37) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//            }
//            case "binding_curse", "vanishing_curse" -> {
//                if (lv >= 25 && lv <= 50) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//            }
//
//            case "sharpness", "sweeping" -> {
//                if (lv >= 45 && lv <= 65) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 34 && lv <= 54) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 23 && lv <= 43) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 12 && lv <= 32) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 1 && lv <= 21) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//
//            }
//            case "bane_of_arthropods", "smite", "impaling" -> {
//                if (lv >= 37 && lv <= 57) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 29 && lv <= 49) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 21 && lv <= 41) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 13 && lv <= 33) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 5 && lv <= 25) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//            }
//            case "aqua_affinity" -> {
//                if (lv >= 30 && lv <= 45) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 20 && lv <= 35) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 10 && lv <= 25) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//
//            }
//            case "efficiency" -> {
//                if (lv >= 41 && lv <= 91) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 31 && lv <= 81) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 21 && lv <= 71) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 11 && lv <= 61) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 1 && lv <= 51) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//
//            }
//            case "fire_aspect" -> {
//                if (lv >= 30 && lv <= 80) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 10 && lv <= 60) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//
//            }
//            case "frost_walker" -> {
//                if (lv >= 20 && lv <= 35) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 10 && lv <= 25) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//            }
//            case "knockback", "soul_speed" -> {
//                if (lv >= 25 && lv <= 75) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 5 && lv <= 55) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//            }
//            case "fortune", "luck_of_the_sea", "lure", "quick_charge", "riptide" -> {
//                if (lv >= 33 && lv <= 83) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 24 && lv <= 74) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 15 && lv <= 65) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//            }
//            case "looting","swift_sneak" -> {
//                if (lv >= 23 && lv <= 38) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 14 && lv <= 29) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 5 && lv <= 20) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//
//            }
//            case "mending" -> {
//                if (lv >= 1000 && lv <= 10001) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//
//            }
//            case "respiration" -> {
//                if (lv >= 30 && lv <= 60) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 20 && lv <= 50) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 10 && lv <= 60) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//
//            }
//            case "protection" -> {
//                if (lv >= 34 && lv <= 54) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 23 && lv <= 43) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 12 && lv <= 32) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 1 && lv <= 21) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//
//            }
//            case "blast_protection" -> {
//                if (lv >= 29 && lv <= 41) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 21 && lv <= 33) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 13 && lv <= 25) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 5 && lv <= 17) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//
//            }
//            case "feather_falling" -> {
//                if (lv >= 23 && lv <= 33) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 17 && lv <= 27) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 11 && lv <= 21) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 5 && lv <= 15) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//
//            }
//            case "fire_protection" -> {
//                if (lv >= 34 && lv <= 46) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 26 && lv <= 38) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 18 && lv <= 30) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 10 && lv <= 20) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//
//            }
//            case "projectile_protection" -> {
//                if (lv >= 21 && lv <= 36) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 15 && lv <= 30) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 9 && lv <= 24) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 3 && lv <= 18) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//
//
//            }
//            case "silk_touch" -> {
//                if (lv >= 15 && lv <= 65) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//            }
//            case "thorns" -> {
//                if (lv >= 50 && lv <= 100) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 30 && lv <= 80) {
//                    getEnchantmentList.add(enchantment);
//                }
//                if (lv >= 10 && lv <= 60) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//            }
//            case "depth_strider" -> {
//                if (lv >= 1 && lv <= 41) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//            }
//
//            case "telekinesis" -> {
//                if (lv >= 1 && lv <= 100) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//            }
//            case "double_edge_curse" -> {
//                if (lv >= 1 && lv <= 100) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//            }
//            case "destroy_curse" -> {
//                if (lv >= 1 && lv <= 100) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//            }
//            case "rainbow_shave" -> {
//                if (lv >= 1 && lv <= 100) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//            }
//            case "auto_smelt" -> {
//                if (lv >= 1 && lv <= 100) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//            }
//
//            case "head_cut" -> {
//                if (lv >= 1 && lv <= 100) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//            }
//            case "durability_plus" -> {
//                if (lv >= 1 && lv <= 100) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//            }
//            case "soul_bind" -> {
//                if (lv >= 1 && lv <= 100) {
//                    getEnchantmentList.add(enchantment);
//                }
//                return;
//            }
//        }
//
//        plugin.getLogger().info("(PS)"+enchantment.getKey().getKey()+"のエンチャントがカスタムエンチャント時に間違えている");
//    }

//    public static int getEnchantLv(Enchantment enchantment) {
//        switch (enchantment.getKey().getKey()) {
//            case "unbreaking" -> {
//                if (lv >= 21 && lv <= 71) {
//                    return 3;
//                }
//                if (lv >= 13 && lv <= 63) {
//                    return 2;
//                }
//                if (lv >= 5 && lv <= 55) {
//                    return 1;
//                }
//            }
//            case "silk_touch" -> {
//                if (lv >= 15 && lv <= 65) {
//                    return 1;
//                }
//            }
//            case "THORNS" -> {
//                if (lv >= 50 && lv <= 100) {
//                    return 3;
//                }
//                if (lv >= 30 && lv <= 80) {
//                    return 2;
//                }
//                if (lv >= 10 && lv <= 60) {
//                    return 1;
//                }
//            }
//            case "depth_strider" -> {
//                if (lv >= 1 && lv <= 41) {
//                    return 1;
//                }
//            }
//
//            case "power" -> {
//                if (lv >= 41 && lv <= 56) {
//                    return 5;
//                }
//                if (lv >= 31 && lv <= 46) {
//                    return 4;
//                }
//                if (lv >= 21 && lv <= 36) {
//                    return 3;
//                }
//                if (lv >= 11 && lv <= 26) {
//                    return 2;
//                }
//                if (lv >= 1 && lv <= 16) {
//                    return 1;
//                }
//            }
//            case "flame", "infinity", "channeling", "loyalty", "multishot", "piercing" -> {
//                if (lv >= 20 && lv <= 50) {
//                    return 1;
//                }
//            }
//            case "punch" -> {
//                if (lv >= 32 && lv <= 57) {
//                    return 2;
//                }
//                if (lv >= 12 && lv <= 37) {
//                    return 1;
//                }
//            }
//            case "binding_curse", "vanishing_curse" -> {
//                if (lv >= 25 && lv <= 50) {
//                    return 1;
//                }
//            }
//
//            case "sharpness", "sweeping" -> {
//                if (lv >= 45 && lv <= 65) {
//                    return 5;
//                }
//                if (lv >= 34 && lv <= 54) {
//                    return 4;
//                }
//                if (lv >= 23 && lv <= 43) {
//                    return 3;
//                }
//                if (lv >= 12 && lv <= 32) {
//                    return 2;
//                }
//                if (lv >= 1 && lv <= 21) {
//                    return 1;
//                }
//
//            }
//            case "bane_of_arthropods", "smite", "impaling" -> {
//                if (lv >= 37 && lv <= 57) {
//                    return 5;
//                }
//                if (lv >= 29 && lv <= 49) {
//                    return 4;
//                }
//                if (lv >= 21 && lv <= 41) {
//                    return 3;
//                }
//                if (lv >= 13 && lv <= 33) {
//                    return 2;
//                }
//                if (lv >= 5 && lv <= 25) {
//                    return 1;
//                }
//            }
//            case "aqua_affinity" -> {
//                if (lv >= 30 && lv <= 45) {
//                    return 3;
//                }
//                if (lv >= 20 && lv <= 35) {
//                    return 2;
//                }
//                if (lv >= 10 && lv <= 25) {
//                    return 1;
//                }
//
//            }
//            case "efficiency" -> {
//                if (lv >= 41 && lv <= 91) {
//                    return 5;
//                }
//                if (lv >= 31 && lv <= 81) {
//                    return 4;
//                }
//                if (lv >= 21 && lv <= 71) {
//                    return 3;
//                }
//                if (lv >= 11 && lv <= 61) {
//                    return 2;
//                }
//                if (lv >= 1 && lv <= 51) {
//                    return 1;
//                }
//
//            }
//            case "fire_aspect" -> {
//                if (lv >= 30 && lv <= 80) {
//                    return 2;
//                }
//                if (lv >= 10 && lv <= 60) {
//                    return 1;
//                }
//
//            }
//            case "frost_walker" -> {
//                if (lv >= 20 && lv <= 35) {
//                    return 2;
//                }
//                if (lv >= 10 && lv <= 25) {
//                    return 1;
//                }
//            }
//            case "knockback", "soul_speed" -> {
//                if (lv >= 25 && lv <= 75) {
//                    return 2;
//                }
//                if (lv >= 5 && lv <= 55) {
//                    return 1;
//                }
//            }
//            case "fortune", "luck_of_the_sea", "lure", "quick_charge", "riptide" -> {
//                if (lv >= 33 && lv <= 83) {
//                    return 3;
//                }
//                if (lv >= 24 && lv <= 74) {
//                    return 2;
//                }
//                if (lv >= 15 && lv <= 65) {
//                    return 1;
//                }
//            }
//            case "looting","swift_sneak" -> {
//                if (lv >= 23 && lv <= 38) {
//                    return 3;
//                }
//                if (lv >= 14 && lv <= 29) {
//                    return 2;
//                }
//                if (lv >= 5 && lv <= 20) {
//                    return 1;
//                }
//            }
//            case "mending" -> {
//                if (lv >= 1000 && lv <= 10001) {
//                    return 1;
//                }
//            }
//            case "respiration" -> {
//                if (lv >= 30 && lv <= 60) {
//                    return 3;
//                }
//                if (lv >= 20 && lv <= 50) {
//                    return 2;
//                }
//                if (lv >= 10 && lv <= 60) {
//                    return 1;
//                }
//
//            }
//            case "protection" -> {
//                if (lv >= 34 && lv <= 54) {
//                    return 4;
//                }
//                if (lv >= 23 && lv <= 43) {
//                    return 3;
//                }
//                if (lv >= 12 && lv <= 32) {
//                    return 2;
//                }
//                if (lv >= 1 && lv <= 21) {
//                    return 1;
//                }
//
//            }
//            case "blast_protection" -> {
//                if (lv >= 29 && lv <= 41) {
//                    return 4;
//                }
//                if (lv >= 21 && lv <= 33) {
//                    return 3;
//                }
//                if (lv >= 13 && lv <= 25) {
//                    return 2;
//                }
//                if (lv >= 5 && lv <= 17) {
//                    return 1;
//                }
//            }
//            case "feather_falling" -> {
//                if (lv >= 23 && lv <= 33) {
//                    return 4;
//                }
//                if (lv >= 17 && lv <= 27) {
//                    return 3;                }
//                if (lv >= 11 && lv <= 21) {
//                    return 2;
//                }
//                if (lv >= 5 && lv <= 15) {
//                    return 1;
//                }
//
//            }
//            case "fire_protection" -> {
//                if (lv >= 34 && lv <= 46) {
//                    return 4;
//                }
//                if (lv >= 26 && lv <= 38) {
//                    return 3;
//                }
//                if (lv >= 18 && lv <= 30) {
//                    return 2;
//                }
//                if (lv >= 10 && lv <= 20) {
//                    return 1;
//                }
//
//            }
//            case "projectile_protection" -> {
//                if (lv >= 21 && lv <= 36) {
//                    return 4;
//                }
//                if (lv >= 15 && lv <= 30) {
//                    return 3;
//                }
//                if (lv >= 9 && lv <= 24) {
//                    return 2;
//                }
//                if (lv >= 3 && lv <= 18) {
//                    return 1;
//                }
//
//            }
//            case "thorns" -> {
//                if (lv >= 50 && lv <= 100) {
//                    return 3;
//                }
//                if (lv >= 30 && lv <= 80) {
//                    return 2;
//                }
//                if (lv >= 10 && lv <= 60) {
//                    return 1;
//                }
//            }
//
//            case "soul_bind", "durability_plus", "head_cut", "auto_smelt", "rainbow_shave", "destroy_curse", "double_edge_curse", "telekinesis" -> {
//                if (lv >= 1 && lv <= 100) {
//                    return 1;
//                }
//            }
//
//        }
//
//        plugin.getLogger().info("(PS)"+enchantment.getKey().getKey()+"はエンチャントとして設定されていない");
//        return 1;
//
//
//    }



}
