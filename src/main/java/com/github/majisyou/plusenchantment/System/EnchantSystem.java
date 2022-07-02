package com.github.majisyou.plusenchantment.System;

import com.github.majisyou.plusenchantment.Config.EnchantmentManager;
import com.github.majisyou.plusenchantment.Gui.GuiItem;
import com.github.majisyou.plusenchantment.NBT.ItemNBTLoader;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

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
            Map<Enchantment,Integer> AddEnchant_sub = new HashMap<>();
            ItemStack AddEnchantItem = leftItem.clone();
            if(leftItem.getType().equals(rightItem.getType())){
                if(leftItem.getItemMeta() instanceof Damageable){
                        AddEnchantItem = leftItem.getDurability() < rightItem.getDurability() ? leftItem.clone(): rightItem.clone();
                }
            }
            ItemMeta AddEnchantItemMeta = AddEnchantItem.getItemMeta();
            if(leftItem.getItemMeta().hasDisplayName()){
                AddEnchantItemMeta.setDisplayName(leftItem.getItemMeta().getDisplayName());
            }
            EnchantmentStorageMeta AddEnchantBookMeta = null;
            AddEnchant_sub = AddEnchantItemMeta.getEnchants();
            if(leftItem.getType().equals(Material.ENCHANTED_BOOK)){
                leftEnchant =((EnchantmentStorageMeta) leftItem.getItemMeta()).getStoredEnchants();
                AddEnchantBookMeta = (EnchantmentStorageMeta) AddEnchantItemMeta;
                AddEnchant_sub = AddEnchantBookMeta.getStoredEnchants();
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
                    AddEnchant_sub = AddEnchantBookMeta.getStoredEnchants();
                }
            }

            List<Enchantment> rightEnchantList = new ArrayList<>(rightEnchant.keySet());
            List<Enchantment> leftEnchantList = new ArrayList<>(leftEnchant.keySet());
            List<Enchantment> AddEnchantList_sub = new ArrayList<>(AddEnchant_sub.keySet());
            Integer AddEnchantValue;
            Enchantment AddEnchantType;

            for (Enchantment enchantment: AddEnchantList_sub){
                if(AddEnchantItem.getType().equals(Material.ENCHANTED_BOOK)){
                    AddEnchantBookMeta.removeStoredEnchant(enchantment);
                }else {
                    AddEnchantItemMeta.removeEnchant(enchantment);
                }
            }


            for (Enchantment enchantment : leftEnchantList) {
                AddEnchantType = enchantment;
                EnchantmentManager.loadEnchantment(AddEnchantType.getName());//getNameをエンチャントリストクラスに持って行って変換するでも良いかも
                Integer MaxLevel = EnchantmentManager.getMaxLevel();
                if(MaxLevel==0){
                    plugin.getLogger().info("(PE)"+AddEnchantType.getName()+"のエンチャントの上限が設定されてないね");
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
            damage -= ScrapAmount * CureDurability;
            toolMeta.setDamage(Math.max(damage, 0));
            resultItem.setItemMeta(resultItemMeta);
            return resultItem;
        }
        return new ItemStack(Material.AIR);
    }

    public static Integer CureScrapDurability(ItemStack itemStack){
        int MaxDurability = itemStack.getType().getMaxDurability();
        int scrapRepairRate = EnchantmentManager.getScrapCoefficient(itemStack.getType().name());
        Map<Enchantment,Integer> Enchantments = itemStack.getEnchantments();
        List<Enchantment> EnchantmentList = new ArrayList<>(Enchantments.keySet());
        int SumEnchantLevel = 0;
        for (Enchantment enchantment:EnchantmentList){
            EnchantmentManager.loadEnchantment(enchantment.getName());
            Integer AddCoefficient = EnchantmentManager.getAddCoefficient();
            Integer EnchantLevel = Enchantments.get(enchantment);
            SumEnchantLevel += AddCoefficient * EnchantLevel * EnchantLevel;
            if(AddCoefficient==0)
                plugin.getLogger().info("(PE)"+enchantment.getName()+"のAddCoefficientが設定されてないかも");
        }


        return MaxDurability/(scrapRepairRate+SumEnchantLevel/150)+1;
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
            plugin.getLogger().info("(PE)"+AddEnchant.getName()+"のConflictEnchantに何も設定されていない");
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
            plugin.getLogger().info("(PE)"+AddEnchant.getName()+"のPossibleItemに何も設定されていない");
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
                NeedExp += AddCoefficient * EnchantLevel * EnchantLevel;
                if(AddCoefficient==0 && !(enchantment.isCursed()))
                    plugin.getLogger().info("(PE)"+enchantment.getName()+"のAddCoefficientが設定されてないかも");
            }
            NeedExp /= 100;
        }
        return NeedExp;
    }

    public static Integer CalculateRepair(Integer NeedScrapAmount){
        int NeedExp = (NeedScrapAmount+6)/7;
        if(NeedExp == 0){
            NeedExp = 1;
        }
        return NeedExp;
    }

    public static boolean BrokenItemJudge(String Tool){
        EnchantmentManager.loadEnchantment();
        return EnchantmentManager.getBrokenItemList().contains(Tool);
    }

    public static boolean BrokenItemIs(ItemStack brokenItem){
        if(brokenItem.getType().name().equals("CHAIN_COMMAND_BLOCK")){
            ItemNBTLoader.ItemNBTLoad(brokenItem);
            return ItemNBTLoader.hasString("BrokenItem");
        }
        return false;
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

    public static ItemStack RemoveAllEnchantItem(ItemStack leftItem){
        ItemStack resultItem = leftItem.clone();
        Set<Enchantment> leftEnchant;
        if(leftItem.getType().equals(Material.ENCHANTED_BOOK)){
            EnchantmentStorageMeta resultMeta = (EnchantmentStorageMeta) leftItem.getItemMeta();
            leftEnchant = resultMeta.getStoredEnchants().keySet();
            for(Enchantment enchantment : leftEnchant){
                if(!enchantment.isCursed()){
                    resultMeta.removeStoredEnchant(enchantment);
                }
                if(resultMeta.getStoredEnchants().keySet().size()==0){
                    return new ItemStack(Material.BOOK,1);
                }
            }
            resultItem.setItemMeta(resultMeta);
        }else {
            ItemMeta resultMeta = resultItem.getItemMeta();
            leftEnchant = leftItem.getEnchantments().keySet();
            for(Enchantment enchantment : leftEnchant){
                if(!enchantment.isCursed()){
                    resultMeta.removeEnchant(enchantment);
                }
            }
            resultItem.setItemMeta(resultMeta);
        }
        if(leftItem.getAmount()>1 || leftEnchant.size()==0 || leftItem.equals(resultItem)){
            return GuiItem.Failed();
        }
        return resultItem;
    }

    public static ItemStack ChangeRedShirt(ItemStack Changeitem) {
        ItemStack itemStack = Changeitem.clone();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
        List<String> Lore = itemMeta.getLore();
        itemMeta.setLore(new ArrayList<>());
        for(NamespacedKey key : itemMeta.getPersistentDataContainer().getKeys()) {
            if(key.getNamespace().equals("growthsurvival"))
            itemMeta.getPersistentDataContainer().remove(key);
        }
        itemStack.setItemMeta(itemMeta);
        if(itemMeta instanceof EnchantmentStorageMeta bookMeta){
            if(Lore != null){
                for (String lore:Lore){
                    String[] Enchant = lore.split(" ");
                    if(Enchant.length == 2){
                        if(SearchEnchant(Enchant[0])!=null){
                            bookMeta.addStoredEnchant(SearchEnchant(Enchant[0]),SearchLevel(Enchant[1]),true);
                        }else {
                            plugin.getLogger().info("(PE)"+Enchant[0]+"がエンチャントとして読み込まれなかった");
                        }
                    }
                    if(Enchant.length == 1){
                        if(SearchEnchant(Enchant[0])!=null){
                            bookMeta.addStoredEnchant(SearchEnchant(Enchant[0]),SearchLevel(null),true);
                        }else {
                            plugin.getLogger().info("(PE)"+Enchant[0]+"がエンチャントとして読み込まれなかった");
                        }
                    }
                }
                itemStack.setItemMeta(bookMeta);
                return itemStack;
            }else {
//                plugin.getLogger().info("(PE)"+"これは消すやつ。そんで、Loreが無い");
            }
            return null;
        }
        return itemStack;
    }

    public static Enchantment SearchEnchant(String Name){
        switch (Name){
            case "§7耐久力" ->{
                return Enchantment.DURABILITY;
            }case "§7射撃ダメージ増加" ->{
                return Enchantment.ARROW_DAMAGE;
            }case "§7フレイム" ->{
                return Enchantment.ARROW_FIRE;
            }case "§7無限" ->{
                return Enchantment.ARROW_INFINITE;
            }case "§7パンチ" ->{
                return Enchantment.ARROW_KNOCKBACK;
            }case "§7束縛の呪い" ->{
                return Enchantment.BINDING_CURSE;
            }case "§7召雷" ->{
                return Enchantment.CHANNELING;
            }case "§7ダメージ増加" ->{
                return Enchantment.DAMAGE_ALL;
            }case "§7虫特攻" ->{
                return Enchantment.DAMAGE_ARTHROPODS;
            }case "§7アンデッド特攻" ->{
                return Enchantment.DAMAGE_UNDEAD;
            }case "§7水中採掘" ->{
                return Enchantment.DEPTH_STRIDER;
            }case "§7効率強化" ->{
                return Enchantment.DIG_SPEED;
            }case "§7火属性" ->{
                return Enchantment.FIRE_ASPECT;
            }case "§7氷渡り" ->{
                return Enchantment.FROST_WALKER;
            }
            case "§7水生特攻" ->{
                return Enchantment.IMPALING;
            }case "§7ノックバック" ->{
                return Enchantment.KNOCKBACK;
            }case "§7幸運" ->{
                return Enchantment.LOOT_BONUS_BLOCKS;
            }case "§7ドロップ増加" ->{
                return Enchantment.LOOT_BONUS_MOBS;
            }
            case "§7宝釣り" ->{
                return Enchantment.LUCK;
            }
            case "§7入れ食い" ->{
                return Enchantment.LURE;
            }
            case "§7修繕" ->{
                return Enchantment.MENDING;
            }
            case "§7拡散" ->{
                return Enchantment.MULTISHOT;
            }
            case "§7水中呼吸" ->{
                return Enchantment.OXYGEN;
            }
            case "§7貫通" ->{
                return Enchantment.PIERCING;
            }
            case "§7ダメージ軽減" ->{
                return Enchantment.PROTECTION_ENVIRONMENTAL;
            }
            case "§7爆発耐性" ->{
                return Enchantment.PROTECTION_EXPLOSIONS;
            }
            case "§7落下耐性" ->{
                return Enchantment.PROTECTION_FALL;
            }
            case "§7火炎耐性" ->{
                return Enchantment.PROTECTION_FIRE;
            }
            case "§7飛び道具耐性" ->{
                return Enchantment.PROTECTION_PROJECTILE;
            }
            case "§7高速装填" ->{
                return Enchantment.QUICK_CHARGE;
            }
            case "§7激流" ->{
                return Enchantment.RIPTIDE;
            }
            case "§7シルクタッチ" ->{
                return Enchantment.SILK_TOUCH;
            }
            case "§7ソウルスピード" ->{
                return Enchantment.SOUL_SPEED;
            }
            case "§7範囲ダメージ" ->{
                return Enchantment.SWEEPING_EDGE;
            }
            case "§7棘の鎧" ->{
                return Enchantment.THORNS;
            }
            case "§7消滅の呪い" ->{
                return Enchantment.VANISHING_CURSE;
            }
            case "§7水中歩行" ->{
                return Enchantment.WATER_WORKER;
            }
            case "§7忠誠" ->{
                return Enchantment.LOYALTY;
            }

        }

        return null;
    }

    public static Integer SearchLevel(String level){
        if(level == null){
            return 1;
        }
        switch (level) {
            case "I" -> {
                return 1;
            }
            case "II" -> {
                return 2;
            }
            case "III" -> {
                return 3;
            }
            case "IV" -> {
                return 4;
            }
            case "V" -> {
                return 5;
            }
            case "VI" -> {
                return 6;
            }
            case "VII" -> {
                return 7;
            }
            case "VIII" -> {
                return 8;
            }
            case "IX" -> {
                return 9;
            }
            case "X" -> {
                return 10;
            }
        }
        return 1;
    }


}
