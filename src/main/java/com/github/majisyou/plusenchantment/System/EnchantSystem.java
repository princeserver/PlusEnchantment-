package com.github.majisyou.plusenchantment.System;

import com.github.majisyou.plusenchantment.Config.EnchantmentManager;
import com.github.majisyou.plusenchantment.Gui.GuiItem;
import com.github.majisyou.plusenchantment.NBT.ItemNBTLoader;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class EnchantSystem {

    private static final PlusEnchantment plugin = PlusEnchantment.getInstance();

    public static ItemStack AddItem(ItemStack leftItem, ItemStack rightItem){
        if(!(EnchantItemJudge(leftItem.getType().toString()) && EnchantItemJudge(rightItem.getType().toString()))){
            return GuiItem.Failed();
        }

        if(leftItem.getType().equals(rightItem.getType()) || rightItem.getType().equals(Material.ENCHANTED_BOOK)){
            Map<Enchantment,Integer> leftEnchant = leftItem.getEnchantments();
            Map<Enchantment,Integer> rightEnchant = rightItem.getEnchantments();
            ItemStack AddEnchantItem = new ItemStack(leftItem.getType(),1);
            ItemMeta AddEnchantItemMeta = AddEnchantItem.getItemMeta();
            EnchantmentStorageMeta AddEnchantBookMeta = null;

            if(leftItem.getType().equals(Material.ENCHANTED_BOOK)){
                leftEnchant =((EnchantmentStorageMeta) leftItem.getItemMeta()).getStoredEnchants();
                AddEnchantBookMeta = (EnchantmentStorageMeta) AddEnchantItemMeta;
            }
            if(rightItem.getType().equals(Material.ENCHANTED_BOOK)){
                rightEnchant = ((EnchantmentStorageMeta) rightItem.getItemMeta()).getStoredEnchants();
            }
            if(rightItem.getType().equals(Material.ENCHANTED_BOOK)){
                rightEnchant = ((EnchantmentStorageMeta) rightItem.getItemMeta()).getStoredEnchants();
            }
            if(leftItem.getType().equals(Material.BOOK)){
                if(leftItem.getAmount()==1){
                    AddEnchantItem = new ItemStack(Material.ENCHANTED_BOOK,1);
                    AddEnchantBookMeta = (EnchantmentStorageMeta) AddEnchantItem.getItemMeta();
                }
            }
            List<Enchantment> rightEnchantList = new ArrayList<>(rightEnchant.keySet());
            List<Enchantment> leftEnchantList = new ArrayList<>(leftEnchant.keySet());
            Integer AddEnchantValue;
            Enchantment AddEnchantType;

            for (Enchantment enchantment : leftEnchantList) {
                AddEnchantType = enchantment;
                EnchantmentManager.loadEnchantment(AddEnchantType.getName());//getNameをエンチャントリストクラスに持って行って変換するでも良いかも
                Integer MaxLevel = EnchantmentManager.getMaxLevel();
                if(MaxLevel==0){
                    plugin.getLogger().info(AddEnchantType.getName()+"のエンチャントの上限が設定されてないね");
                }
                boolean enchant_judge = rightEnchant.containsKey(AddEnchantType)
                        && Objects.equals(leftEnchant.get(enchantment), rightEnchant.get(enchantment))
                        && leftEnchant.get(enchantment) < MaxLevel;
                if (enchant_judge) {
                    AddEnchantValue = leftEnchant.get(enchantment) + 1;
                }else{
                    AddEnchantValue = leftEnchant.get(enchantment);
                }
                if(AddEnchantItem.getType().equals(Material.ENCHANTED_BOOK)){
                    AddEnchantBookMeta.addStoredEnchant(AddEnchantType,AddEnchantValue,true);
                }else {
                    AddEnchantItemMeta.addEnchant(AddEnchantType,AddEnchantValue,true);
                }
            }
            List<Enchantment> AddEnchantList;
            if(AddEnchantItem.getType().equals(Material.ENCHANTED_BOOK)&& !(AddEnchantBookMeta ==null)){
                AddEnchantList  = new ArrayList<>(AddEnchantBookMeta.getStoredEnchants().keySet());
            }else {
                AddEnchantList = new ArrayList<>(AddEnchantItemMeta.getEnchants().keySet());
            }
            for (Enchantment enchantment2 : rightEnchantList) {
                //conflictエンチャントのシステムを入れる
                if (!leftEnchant.containsKey(enchantment2)
                        && !AddEnchantItemMeta.getEnchants().containsKey(enchantment2)
                        && ConflictEnchant(AddEnchantList,enchantment2)
                        && PossibleItem(AddEnchantItem.getType().name(),enchantment2)) {

                    AddEnchantType = enchantment2;
                    AddEnchantValue = rightEnchant.get(enchantment2);

                    if(AddEnchantItem.getType().equals(Material.ENCHANTED_BOOK) && !(AddEnchantBookMeta ==null)){
                        AddEnchantBookMeta.addStoredEnchant(AddEnchantType,AddEnchantValue,true);
                    }else {
                        AddEnchantItemMeta.addEnchant(AddEnchantType,AddEnchantValue,true);
                    }
                }
            }
            Map<Enchantment,Integer> AddEnchant;
            if(AddEnchantItem.getType().equals(Material.ENCHANTED_BOOK)&& !(AddEnchantBookMeta ==null)){
                AddEnchant = AddEnchantBookMeta.getStoredEnchants();
            }else {
                AddEnchant = AddEnchantItemMeta.getEnchants();
            }
            if(leftEnchant.equals(AddEnchant)){
                return GuiItem.Failed();
            }
            if(AddEnchantItem.getType().equals(Material.ENCHANTED_BOOK)){
                AddEnchantItem.setItemMeta(AddEnchantBookMeta);
                return AddEnchantItem;
            }
            if(leftItem.getAmount()>1){
             return GuiItem.Failed();
            }
            AddEnchantItem.setItemMeta(AddEnchantItemMeta);
            return AddEnchantItem;
        }
        return GuiItem.Failed();
    }

    public static ItemStack RepairItem(ItemStack leftItem,ItemStack Scrap){
        ItemStack resultItem = leftItem.clone();
        ItemMeta resultItemMeta = resultItem.getItemMeta();
        if(resultItemMeta instanceof Damageable toolMeta){
            int ScrapAmount = ScrapCost(leftItem,Scrap);
            int damage =  leftItem.getDurability();
            int CureDurability = CureScrapDurability(leftItem);
            damage -= ScrapAmount*CureDurability;
            toolMeta.setDamage(Math.max(damage, 0));
            resultItem.setItemMeta(resultItemMeta);
            return resultItem;
        }
        return new ItemStack(Material.AIR);
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
        int CureDurability = CureScrapDurability(leftItem);
        if(CureDurability == 0)
            return 0;
        int scrapCost = damage / CureDurability;
        return Math.min(scrapCost+1, ScrapAmount);
    }

    public static boolean EnchantItemJudge(String Tool){
        EnchantmentManager.loadEnchantment();
        return EnchantmentManager.getItemList().contains(Tool);
    }

    public static boolean ConflictEnchant(List<Enchantment> leftEnchantmentList,Enchantment AddEnchant){
//        ここは事前にコンフィグロードが事前にされていることが必要
//        競合するエンチャントがあればfalseを返す
        EnchantmentManager.loadEnchantment(AddEnchant.getName());
        List<String> ConflictEnchant = EnchantmentManager.getConflictEnchant();
        if(ConflictEnchant.size()==0){
            plugin.getLogger().info(AddEnchant.getName()+"のConflictEnchantに何も設定されていない");
            return false;
        }
        if(ConflictEnchant.get(0).equals("None")){
            return true;
        }
        for (Enchantment enchantment:leftEnchantmentList){
            if(ConflictEnchant.contains(enchantment.getName()))
                return false;
        }
        return true;
    }

    public static boolean PossibleItem(String resultItem,Enchantment AddEnchant){
        EnchantmentManager.loadEnchantment(AddEnchant.getName());
        List<String> PossibleItem = EnchantmentManager.getPossibleItem();
        if(PossibleItem.size()==0){
            plugin.getLogger().info(AddEnchant.getName()+"のPossibleItemに何も設定されていない");
            return false;
        }
        if(PossibleItem.get(0).equals("None")){
            return false;
        }
        return PossibleItem.contains(resultItem);
    }

    public static Integer CalculateEnchant(ItemStack ResultEnchantItem){
        int NeedExp = 0;
        if(EnchantItemJudge(ResultEnchantItem.getType().name())){
            Map<Enchantment,Integer> ResultEnchant  = ResultEnchantItem.getItemMeta().getEnchants();
            if(ResultEnchantItem.getType().equals(Material.ENCHANTED_BOOK)){
                ResultEnchant  = ((EnchantmentStorageMeta)ResultEnchantItem.getItemMeta()).getStoredEnchants();
            }
            List<Enchantment> ResultEnchantList = new ArrayList<>(ResultEnchant.keySet());
            for (Enchantment enchantment:ResultEnchantList){
                EnchantmentManager.loadEnchantment(enchantment.getName());
                Integer AddCoefficient = EnchantmentManager.getAddCoefficient();
                Integer EnchantLevel = ResultEnchant.get(enchantment);
                NeedExp += AddCoefficient * EnchantLevel;
                if(AddCoefficient==0 && !(enchantment.isCursed()))
                    plugin.getLogger().info(enchantment.getName()+"のAddCoefficientが設定されてないかも");
            }
        }
        return NeedExp;
    }

    public static Integer CalculateRepair(Integer NeedScrapAmount){
        int NeedExp = NeedScrapAmount/5;
        if(!(NeedScrapAmount%5==0)){
            NeedExp+=1;
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

    public static ItemStack RemoveEnchantItem(ItemStack leftItem, ItemStack EnchantBook){
        ItemStack resultItem = leftItem.clone();
        Set<Enchantment> BookEnchant = ((EnchantmentStorageMeta) EnchantBook.getItemMeta()).getStoredEnchants().keySet();
        Set<Enchantment> leftEnchant;
        if(leftItem.getType().equals(Material.ENCHANTED_BOOK)){
            EnchantmentStorageMeta resultMeta = (EnchantmentStorageMeta) leftItem.getItemMeta();
            leftEnchant = resultMeta.getStoredEnchants().keySet();
            for (Enchantment enchantment:BookEnchant){
                if(leftEnchant.contains(enchantment)){
                    resultMeta.removeStoredEnchant(enchantment);
                }
            }
            if(resultMeta.getStoredEnchants().keySet().size()==0){
                return new ItemStack(Material.BOOK,1);
            }
            resultItem.setItemMeta(resultMeta);
        }else {
            leftEnchant = leftItem.getEnchantments().keySet();
            for (Enchantment enchantment:BookEnchant){
                if(leftEnchant.contains(enchantment)){
                    resultItem.removeEnchantment(enchantment);
                }
            }
        }
        if(leftItem.getAmount()>1 || leftEnchant.size()==0 || leftItem.equals(resultItem)){
            return GuiItem.Failed();
        }
        return resultItem;
    }
}
