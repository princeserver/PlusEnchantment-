package com.github.majisyou.plusenchantment.System;

import com.github.majisyou.plusenchantment.Config.EnchantmentManager;
import com.github.majisyou.plusenchantment.NBT.ItemNBTLoader;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class EnchantSystem {

    private static final PlusEnchantment plugin = PlusEnchantment.getInstance();

    public static ItemStack AddItem(ItemStack leftItem, ItemStack rightItem){
        if(!(EnchantItemJudge(leftItem.getType().toString()) && EnchantItemJudge(leftItem.getType().toString()))){
            return new ItemStack(Material.AIR,1);
        }
        Map<Enchantment,Integer> leftEnchant = leftItem.getEnchantments();
        Map<Enchantment,Integer> rightEnchant = rightItem.getEnchantments();
        ItemStack AddEnchantItem = new ItemStack(leftItem.getType(),1);
        ItemMeta AddEnchantItemMeta = AddEnchantItem.getItemMeta();
        List<Enchantment> rightEnchantList = new ArrayList<>(rightEnchant.keySet());
        List<Enchantment> leftEnchantList = new ArrayList<>(leftEnchant.keySet());
        Integer AddEnchantValue;
        Enchantment AddEnchantType;

        if(leftItem.getType().equals(rightItem.getType())){
            for (Enchantment enchantment : leftEnchantList) {
                AddEnchantType = enchantment;
                EnchantmentManager.loadEnchantment(AddEnchantType.getName());//getNameをエンチャントリストクラスに持って行って変換するでも良いかも
                Integer MaxLevel = EnchantmentManager.getMaxLevel();
                if(MaxLevel==0){
                    plugin.getLogger().info(AddEnchantType.getName()+"のエンチャントの上限が設定されてないね");
                }
                boolean enchant_judge = rightEnchant.containsKey(AddEnchantType) && Objects.equals(leftEnchant.get(enchantment), rightEnchant.get(enchantment)) || Objects.equals(leftEnchant.get(enchantment), MaxLevel);
                if (enchant_judge) {
                    AddEnchantValue = leftEnchant.get(enchantment) + 1;
                }else{
                    AddEnchantValue = leftEnchant.get(enchantment);
                }
                AddEnchantItemMeta.addEnchant(AddEnchantType,AddEnchantValue,true);
            }

            for (Enchantment enchantment2 : rightEnchantList) {
                if (!leftEnchant.containsKey(enchantment2) && !AddEnchantItemMeta.getEnchants().containsKey(enchantment2) && !AddEnchantItemMeta.hasConflictingEnchant(enchantment2)) {
                    AddEnchantType = enchantment2;
                    AddEnchantValue = rightEnchant.get(enchantment2);
                    AddEnchantItemMeta.addEnchant(AddEnchantType,AddEnchantValue,true);
                }
            }

        }else if(rightItem.getType().equals(Material.ENCHANTED_BOOK)){
            for (Enchantment enchantment : leftEnchantList) {
                AddEnchantType = enchantment;
                EnchantmentManager.loadEnchantment(AddEnchantType.getName());//getNameをエンチャントリストクラスに持って行って変換するでも良いかも
                Integer MaxLevel = EnchantmentManager.getMaxLevel();
                if(MaxLevel==0){
                    plugin.getLogger().info(AddEnchantType.getName()+"のエンチャントの上限が設定されてないね");
                }
                boolean enchant_judge = rightEnchant.containsKey(AddEnchantType) && Objects.equals(leftEnchant.get(enchantment), rightEnchant.get(enchantment)) || Objects.equals(leftEnchant.get(enchantment), MaxLevel);

                if (enchant_judge) {
                    AddEnchantValue = leftEnchant.get(enchantment) + 1;
                }else{
                    AddEnchantValue = leftEnchant.get(enchantment);
                }
                AddEnchantItemMeta.addEnchant(AddEnchantType,AddEnchantValue,true);
            }

            for (Enchantment enchantment2 : rightEnchantList) {
                EnchantmentManager.loadEnchantment(enchantment2.getName());
                boolean bookEnchantJudge = !leftEnchant.containsKey(enchantment2) && !AddEnchantItemMeta.getEnchants().containsKey(enchantment2) && !EnchantSystem.ConflictEnchant(leftEnchantList) && EnchantmentManager.getPossibleItem().contains(enchantment2.getName());
                if (bookEnchantJudge) {
                    AddEnchantType = enchantment2;
                    AddEnchantValue = rightEnchant.get(enchantment2);
                    AddEnchantItemMeta.addEnchant(AddEnchantType,AddEnchantValue,true);
                }
            }
        }
        AddEnchantItem.setItemMeta(AddEnchantItemMeta);
        return AddEnchantItem;
    }

    public static ItemStack RepairItem(ItemStack leftItem,ItemStack Scrap){
        int ScrapAmount = ScrapCost(leftItem,Scrap);
        int damage =  leftItem.getDurability();
        int CureDurability = CureScrapDurability(leftItem);
        ItemStack resultItem = leftItem.clone();
        damage -= ScrapAmount*CureDurability;
        if(damage>0){
            resultItem.setDurability((short) damage);
        }else {
            resultItem.setDurability((short) 0);
        }
        return resultItem;
    }

    public static Integer CureScrapDurability(ItemStack itemStack){
        int MaxDurability = itemStack.getType().getMaxDurability();
        int scrapRepairRate = EnchantmentManager.getScrapCoefficient(itemStack.getType().toString());
        Map<Enchantment,Integer> Enchantments = itemStack.getEnchantments();
        List<Enchantment> EnchantmentList = new ArrayList<>(Enchantments.keySet());
        int SumEnchantLevel = 0;
        for (Enchantment enchantment:EnchantmentList){
            EnchantmentManager.loadEnchantment(enchantment.getName());
            Integer AddCoefficient = EnchantmentManager.getAddCoefficient();
            Integer EnchantLevel = Enchantments.get(enchantment);
            SumEnchantLevel += AddCoefficient * EnchantLevel;
            if(AddCoefficient==0)
                plugin.getLogger().info(enchantment.getName()+"のAddCoefficientが設定されてないかも");
        }
        return MaxDurability/(scrapRepairRate+SumEnchantLevel/5);
    }

    public static Integer ScrapCost(ItemStack leftItem,ItemStack Scrap){
        int damage =  leftItem.getDurability();
        int ScrapAmount = Scrap.getAmount();
        Map<Enchantment,Integer> enchantments = leftItem.getEnchantments();
        int CureDurability = CureScrapDurability(leftItem);
        int scrapCost = damage / CureDurability;
        return Math.min(scrapCost+1, ScrapAmount);
    }

    public static boolean EnchantItemJudge(String Tool){
        EnchantmentManager.loadEnchantment();
        return EnchantmentManager.getItemList().contains(Tool);
    }

    public static boolean ConflictEnchant(List<Enchantment> EnchantmentInItem){
//        EnchantmentManager.loadEnchantment(Enchantment.getName());
//        ここは事前にコンフィグロードが事前にされていることが必要
        List<String> ConflictEnchantment = EnchantmentManager.getConflictEnchant();
        for (Enchantment enchantment:EnchantmentInItem){
            if(ConflictEnchantment.contains(enchantment.getName()))
                return true;
        }
        return true;
    }

    public static Integer CalculateEnchant(ItemStack ResultEnchantItem){
        Map<Enchantment,Integer> ResultEnchant  = ResultEnchantItem.getItemMeta().getEnchants();
        List<Enchantment> ResultEnchantList = new ArrayList<>(ResultEnchant.keySet());
        int NeedExp = 0;
        for (Enchantment enchantment:ResultEnchantList){
            EnchantmentManager.loadEnchantment(enchantment.getName());
            Integer AddCoefficient = EnchantmentManager.getAddCoefficient();
            Integer EnchantLevel = ResultEnchant.get(enchantment);
            NeedExp += AddCoefficient * EnchantLevel;
            if(AddCoefficient==0)
                plugin.getLogger().info(enchantment.getName()+"のAddCoefficientが設定されてないかも");
        }
        return NeedExp;
    }

    public static boolean BrokenItemJudge(String Tool){
        EnchantmentManager.loadEnchantment();
        return EnchantmentManager.getBrokenItemList().contains(Tool);
    }

    public static boolean BrokenItemIs(ItemStack brokenItem){
        ItemNBTLoader.ItemNBTLoad(brokenItem);
        return ItemNBTLoader.hasString("BrokenItem");
    }
}
