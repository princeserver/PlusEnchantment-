package com.github.majisyou.plusenchantment.System;

import com.github.majisyou.plusenchantment.Config.EnchantmentManager;
import com.github.majisyou.plusenchantment.Gui.GuiItem;
import com.github.majisyou.plusenchantment.NBT.ItemNBTLoader;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class EnchantSystem {

    private static final PlusEnchantment plugin = PlusEnchantment.getInstance();

    public static ItemStack addItem(ItemStack leftItem, ItemStack rightItem) {
        ItemStack resultItem = leftItem.clone();
        if(!leftItem.getType().equals(rightItem.getType())){
            if(!rightItem.getType().equals(Material.ENCHANTED_BOOK)){
                return GuiItem.Failed();
            }
        }

        if(leftItem.getType().equals(Material.BOOK)){
            return GuiItem.Failed();
        }

        ItemMeta left = leftItem.getItemMeta();
        ItemMeta right = rightItem.getItemMeta();
        ItemMeta result = resultItem.getItemMeta();

        if(left instanceof EnchantmentStorageMeta && right instanceof EnchantmentStorageMeta right_meta){
            //どっちも本
            Map<Enchantment,Integer> rightEnch = right_meta.getStoredEnchants();
            EnchantmentStorageMeta result_meta = (EnchantmentStorageMeta) result;

            for (Enchantment enchantment:rightEnch.keySet()){
                if(result_meta.hasStoredEnchant(enchantment)){
                    Integer maxLevel = judgeMaxLevel(enchantment);
                    int lv1 = right_meta.getStoredEnchantLevel(enchantment);
                    int lv2 = result_meta.getStoredEnchantLevel(enchantment);

                    if(lv1 == maxLevel){
                        continue;
                    }

                    if(lv1 > lv2){
                        removeEnchant(result_meta,enchantment);
                        addEnchant(result_meta,enchantment,lv1);
                    }

                    if(lv1 == lv2){
                        removeEnchant(result_meta,enchantment);
                        addEnchant(result_meta,enchantment,lv1+1);
                    }
                }else {
                    if(isConflict(result_meta.getStoredEnchants(),enchantment)){
                        continue;
                    }

                    if(!isPossible(resultItem.getType(),enchantment)){
                        continue;
                    }

                    addEnchant(result_meta,enchantment,right_meta.getStoredEnchantLevel(enchantment));
                }
            }

        }else if(right instanceof EnchantmentStorageMeta right_meta) {
            //右が本
            Map<Enchantment,Integer> rightEnch = right_meta.getStoredEnchants();

            for (Enchantment enchantment:rightEnch.keySet()){
                if(result.hasEnchant(enchantment)){
                    Integer maxLevel = judgeMaxLevel(enchantment);
                    int lv1 = right_meta.getStoredEnchantLevel(enchantment);
                    int lv2 = result.getEnchantLevel(enchantment);

                    if(lv1 >= maxLevel){
                        continue;
                    }

                    if(lv1 == lv2){
                        removeEnchant(result,enchantment);
                        addEnchant(result,enchantment,lv1+1);
                    }

                    if(lv1 > lv2){
                        removeEnchant(result,enchantment);
                        addEnchant(result,enchantment,lv1);
                    }
                }else {
                    if(isConflict(result.getEnchants(),enchantment)){
                        continue;
                    }

                    if(!isPossible(resultItem.getType(),enchantment)){
                        continue;
                    }

                    addEnchant(result,enchantment,right_meta.getStoredEnchantLevel(enchantment));
                }
            }

        }else {
            //同じ道具
            Map<Enchantment,Integer> rightEnch = right.getEnchants();
            for (Enchantment enchantment:rightEnch.keySet()){
                if(result.hasEnchant(enchantment)){
                    Integer maxLevel = judgeMaxLevel(enchantment);
                    int lv1 = right.getEnchantLevel(enchantment);
                    int lv2 = result.getEnchantLevel(enchantment);

                    if(lv1 >= maxLevel){
                        continue;
                    }

                    if(lv1 == lv2){
                        removeEnchant(result,enchantment);
                        addEnchant(result,enchantment,lv1+1);
                    }

                    if(lv1 > lv2){
                        removeEnchant(result,enchantment);
                        addEnchant(result,enchantment,lv1);
                    }
                }else {
                    if(isConflict(result.getEnchants(),enchantment)){
                        continue;
                    }

                    if(!isPossible(resultItem.getType(),enchantment)){
                        continue;
                    }

                    addEnchant(result,enchantment,right.getEnchantLevel(enchantment));
                }
            }
        }
        resultItem.setItemMeta(result);
        if(resultItem.equals(leftItem)){
            return GuiItem.Failed();
        }
        return resultItem;

        //leftItemとrightItemがエンチャント本であった時の場合
//        if (leftItem.getType().equals(Material.ENCHANTED_BOOK) && rightItem.getType().equals(Material.ENCHANTED_BOOK)) {
//            Map<Enchantment, Integer> leftEnchant = ((EnchantmentStorageMeta) leftItem.getItemMeta()).getStoredEnchants();
//            Map<Enchantment, Integer> rightEnchant = ((EnchantmentStorageMeta) rightItem.getItemMeta()).getStoredEnchants();
//
//            Map<Enchantment, Integer> resultEnchant = addEnchant(leftEnchant, rightEnchant);
//
//            EnchantmentStorageMeta resultEnchantmentStorageMeta = (EnchantmentStorageMeta) resultMeta;
//            for (Enchantment enchantment : resultEnchant.keySet()) {
//                if (resultEnchantmentStorageMeta.hasStoredEnchant(enchantment)) {
//                    removeEnchant(resultEnchantmentStorageMeta,enchantment);
////                    resultEnchantmentStorageMeta.removeStoredEnchant(enchantment);
//                }
//                addEnchant(resultEnchantmentStorageMeta,enchantment,resultEnchant.get(enchantment));
////                resultEnchantmentStorageMeta.addStoredEnchant(enchantment, resultEnchant.get(enchantment), true);
//            }
//            resultItem.setItemMeta(resultEnchantmentStorageMeta);
//            return resultItem;
//        }
//
//        //leftItemがItemでrightItemがエンチャント本であった場合
//        if (rightItem.getType().equals(Material.ENCHANTED_BOOK)) {
//            Map<Enchantment, Integer> leftEnchant = leftItem.getItemMeta().getEnchants();
//            Map<Enchantment, Integer> rightEnchant = ((EnchantmentStorageMeta) rightItem.getItemMeta()).getStoredEnchants();
//            Map<Enchantment, Integer> resultEnchant = addEnchant(leftEnchant, rightEnchant);
//
//            for (Enchantment enchantment : resultEnchant.keySet()) {
//                if (resultMeta.hasEnchant(enchantment)) {
//                    resultMeta.removeEnchant(enchantment);
//                }
//                resultMeta.addEnchant(enchantment, resultEnchant.get(enchantment), true);
//            }
//
//            resultItem.setItemMeta(resultMeta);
//            return resultItem;
//        }
//
//        //leftItemがItemかつrightItemがItemであった場合
//        if (leftItem.getType().equals(rightItem.getType())) {
//            if (leftItem.getItemMeta() instanceof Damageable leftDamage) {
//                // Itemに耐久値がある場合、耐久値の小さいほうに設定する
//                Damageable rightDamage = (Damageable) rightItem.getItemMeta();
//                Damageable resultDamage = (Damageable) resultItem.getItemMeta();
//
//                if (leftDamage.getDamage() > rightDamage.getDamage()) {
//                    resultDamage.setDamage(rightDamage.getDamage());
//                }
//
//                Map<Enchantment, Integer> leftEnchant = leftDamage.getEnchants();
//                Map<Enchantment, Integer> rightEnchant = rightDamage.getEnchants();
//                Map<Enchantment, Integer> resultEnchant = addEnchant(leftEnchant, rightEnchant);
//                for (Enchantment enchantment : resultEnchant.keySet()) {
//                    if (resultDamage.hasEnchant(enchantment)) {
//                        resultDamage.removeEnchant(enchantment);
//                    }
//                    resultDamage.addEnchant(enchantment, resultEnchant.get(enchantment), true);
//                }
//
//                resultItem.setItemMeta(resultDamage);
//                return resultItem;
//            } else {
//                // Itemに耐久値が無い場合、エンチャントだけを結合させる
//                Map<Enchantment, Integer> leftEnchant = leftItem.getItemMeta().getEnchants();
//                Map<Enchantment, Integer> rightEnchant = rightItem.getItemMeta().getEnchants();
//                Map<Enchantment, Integer> resultEnchant = addEnchant(leftEnchant, rightEnchant);
//
//                for (Enchantment enchantment : resultEnchant.keySet()) {
//                    if (resultMeta.hasEnchant(enchantment)) {
//                        resultMeta.removeEnchant(enchantment);
//                    }
//                    addEnchant(resultMeta, enchantment, resultEnchant.get(enchantment));
//                }
//
//                resultItem.setItemMeta(resultMeta);
//                return resultItem;
//            }
//        }
//
//        //leftItemとrightItemが異なるアイテムかつどちらもエンチャント本出ない場合の出力はFalseを返す
//        return GuiItem.Failed();
    }


//    public static Map<Enchantment, Integer> addEnchantment(Map<Enchantment, Integer> left, Map<Enchantment, Integer> right) {
//        Map<Enchantment, Integer> result = new HashMap<>();
//        for (Enchantment enchantment : left.keySet()) {
//            if (right.containsKey(enchantment)) {
//                Integer maxLevel = judgeMaxLevel(enchantment);
//                if (maxLevel > left.get(enchantment)) {
//                    result.put(enchantment, addLevel(left.get(enchantment), right.get(enchantment)));
//                }
//            } else {
//                result.put(enchantment, left.get(enchantment));
//            }
//        }
//        for (Enchantment enchantment : right.keySet()) {
//            if (!result.containsKey(enchantment)) {
//                if (!isConflict(result, enchantment)) {
//                    result.put(enchantment, right.get(enchantment));
//                }
//            }
//        }
//        return result;
//    }

//    public static Integer addLevel(Integer left_lv, Integer right_lv) {
//        if (left_lv.equals(right_lv)) {
//            return left_lv + 1;
//        }
//        return Math.max(left_lv, right_lv);
//    }

    public static Integer judgeMaxLevel(Enchantment enchantment) {
        EnchantmentManager.loadEnchantment(enchantment.getName());
        return EnchantmentManager.getMaxLevel();
    }

    public static boolean isConflict(Map<Enchantment, Integer> enchantmentIntegerMap, Enchantment enchantment) {
        EnchantmentManager.loadEnchantment(enchantment.getName());
        for (Enchantment encha : enchantmentIntegerMap.keySet()){
            if (EnchantmentManager.getConflictEnchant().contains(encha.getName())){
                return true;
            }
        }
        return false;
    }

    public static boolean isPossible(Material material,Enchantment enchantment){
        EnchantmentManager.loadEnchantment(enchantment.getName());
        return EnchantmentManager.getPossibleItem().contains(material.name());
    }

//    public static void addEnchant(ItemMeta meta, Enchantment enchantment, Integer level) {
//        meta.addEnchant(enchantment, level, true);
//    }

    public static ItemStack clearEnchant(ItemStack itemStack) {
        ItemStack item = itemStack.clone();
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta instanceof EnchantmentStorageMeta) {
            ItemStack book = new ItemStack(Material.BOOK);
            book.setItemMeta(itemMeta);
            return book;
        } else {
            Map<Enchantment, Integer> enchantmentIntegerMap = itemMeta.getEnchants();
            for (Enchantment enchantment : enchantmentIntegerMap.keySet()) {
                removeEnchant(itemMeta,enchantment);
            }
            item.setItemMeta(itemMeta);
            return item;
        }
    }

    public static ItemStack clearEnchant(ItemStack itemStack, ItemStack Book) {
        ItemStack item = itemStack.clone();
        ItemMeta itemMeta = item.getItemMeta();
        Map<Enchantment, Integer> enchantsBook = ((EnchantmentStorageMeta) Book.getItemMeta()).getStoredEnchants();
        if (itemMeta instanceof EnchantmentStorageMeta meta) {
            for (Enchantment enchantment : enchantsBook.keySet()) {
                meta.removeStoredEnchant(enchantment);
            }
            item.setItemMeta(meta);
            if (meta.getStoredEnchants().isEmpty()) {
                ItemStack returnBook = new ItemStack(Material.BOOK);
                returnBook.setItemMeta(itemMeta);
                return returnBook;
            } else {
                return item;
            }
        } else {
            for (Enchantment enchantment : enchantsBook.keySet()) {
                if (itemMeta.hasEnchant(enchantment)) {
                    removeEnchant(itemMeta,enchantment);
                }
            }
            item.setItemMeta(itemMeta);
            return item;
        }
    }

    public static int cal_ExpCostAddItem(ItemStack itemStack) {
        if (itemStack.equals(GuiItem.Failed())) {
            return 0;
        }
        int expCost = 0;
        Map<Enchantment, Integer> enchantmentIntegerMap = itemStack.getItemMeta().getEnchants();
        if (itemStack.getType().equals(Material.ENCHANTED_BOOK)) {
            enchantmentIntegerMap = ((EnchantmentStorageMeta) itemStack.getItemMeta()).getStoredEnchants();
        }
        for (Enchantment enchantment : enchantmentIntegerMap.keySet()) {
            EnchantmentManager.loadEnchantment(enchantment.getName());
            int addC = EnchantmentManager.getAddCoefficient();
            int ench_lv = enchantmentIntegerMap.get(enchantment);
            expCost += addC * ench_lv * ench_lv;
            if(addC == 0){
                plugin.getLogger().info("(PE)"+enchantment.getName()+"のaddCoeffiecintが設定されていない");
            }

        }
        return Math.max(1, Math.min((expCost / 100), (expCost / 125) + 40));
    }

    public static ItemStack repairItem(ItemStack itemStack, int num) {
        ItemStack resultItem = itemStack.clone();
        ItemMeta resultItemMeta = resultItem.getItemMeta();
        if (resultItemMeta instanceof Damageable toolMeta) {
            int ScrapAmount = needScrapAmount(itemStack, num);
            int damage = itemStack.getDurability();
            int CureDurability = CureScrapDurability(itemStack);
            damage -= ScrapAmount * CureDurability;
            toolMeta.setDamage(Math.max(damage, 0));
            resultItem.setItemMeta(resultItemMeta);
        }
        return resultItem;
    }


    public static Integer needScrapAmount(ItemStack leftItem, int num) {
        int damage = leftItem.getDurability();
        int CureDurability = CureScrapDurability(leftItem);
        if (CureDurability == 0)
            return 0;
        int scrapCost = damage / CureDurability;
        return Math.min(scrapCost + 1, num);
    }

    public static Integer CureScrapDurability(ItemStack itemStack) {
        int MaxDurability = itemStack.getType().getMaxDurability();
        int scrapRepairRate = EnchantmentManager.getScrapCoefficient(itemStack.getType().name());
        Map<Enchantment, Integer> Enchantments = itemStack.getEnchantments();
        List<Enchantment> EnchantmentList = new ArrayList<>(Enchantments.keySet());
        int SumEnchantLevel = 0;
        for (Enchantment enchantment : EnchantmentList) {
            EnchantmentManager.loadEnchantment(enchantment.getName());
            Integer AddCoefficient = EnchantmentManager.getAddCoefficient();
            Integer EnchantLevel = Enchantments.get(enchantment);
            SumEnchantLevel += AddCoefficient * EnchantLevel * EnchantLevel;
            if (AddCoefficient == 0)
                plugin.getLogger().info("(PE)" + enchantment.getName() + "のAddCoefficientが設定されてないかも");
        }
        return MaxDurability / (scrapRepairRate + Math.max(SumEnchantLevel / 150, 0)) + 1;
    }

    public static int cal_repairItem(ItemStack itemStack, int num) {
        if (itemStack.equals(GuiItem.Failed())) {
            return 0;
        }
        int needScrapAmount = needScrapAmount(itemStack, num);

        int NeedExp = (needScrapAmount + 6) / 7;
        if (NeedExp == 0) {
            NeedExp = 1;
        }
        return NeedExp;
    }

    public static boolean EnchantItemJudge(ItemStack item) {
        EnchantmentManager.loadEnchantment();
        return EnchantmentManager.getItemList().contains(item.getType().name());
    }

    public static ItemStack ChangeRedShirt(ItemStack itemStack) {
        ItemStack item = itemStack.clone();
        ItemMeta itemMeta = itemStack.getItemMeta();
        for (NamespacedKey key : itemStack.getItemMeta().getPersistentDataContainer().getKeys()) {
            if (key.getNamespace().equals("growthsurvival")) {
                itemMeta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
                List<String> Lore = itemMeta.getLore();
                itemMeta.setLore(new ArrayList<>());
                if (itemMeta instanceof EnchantmentStorageMeta meta) {
                    if (Lore != null) {
                        for (String lore : Lore) {
                            String[] Enchant = lore.split(" ");
                            if (Enchant.length == 2) {
                                if (SearchEnchant(Enchant[0]) != null) {
                                    meta.addStoredEnchant(SearchEnchant(Enchant[0]), SearchLevel(Enchant[1]), true);
                                } else {
                                    plugin.getLogger().info("(PE)" + Enchant[0] + "がエンチャントとして読み込まれなかった");
                                }
                            }
                            if (Enchant.length == 1) {
                                if (SearchEnchant(Enchant[0]) != null) {
                                    meta.addStoredEnchant(SearchEnchant(Enchant[0]), SearchLevel(null), true);
                                } else {
                                    plugin.getLogger().info("(PE)" + Enchant[0] + "がエンチャントとして読み込まれなかった");
                                }
                            }
                        }
                    }
                }
                break;
            }
        }
        for (NamespacedKey key2 : itemMeta.getPersistentDataContainer().getKeys()) {
            if (key2.getNamespace().equals("growthsurvival")) {
                itemMeta.getPersistentDataContainer().remove(key2);
            }
        }

        item.setItemMeta(itemMeta);
        return item;
    }


    public static Enchantment SearchEnchant(String Name) {
        switch (Name) {
            case "§7耐久力" -> {
                return Enchantment.DURABILITY;
            }
            case "§7射撃ダメージ増加" -> {
                return Enchantment.ARROW_DAMAGE;
            }
            case "§7フレイム" -> {
                return Enchantment.ARROW_FIRE;
            }
            case "§7無限" -> {
                return Enchantment.ARROW_INFINITE;
            }
            case "§7パンチ" -> {
                return Enchantment.ARROW_KNOCKBACK;
            }
            case "§7束縛の呪い" -> {
                return Enchantment.BINDING_CURSE;
            }
            case "§7召雷" -> {
                return Enchantment.CHANNELING;
            }
            case "§7ダメージ増加" -> {
                return Enchantment.DAMAGE_ALL;
            }
            case "§7虫特攻" -> {
                return Enchantment.DAMAGE_ARTHROPODS;
            }
            case "§7アンデッド特攻" -> {
                return Enchantment.DAMAGE_UNDEAD;
            }
            case "§7水中採掘" -> {
                return Enchantment.DEPTH_STRIDER;
            }
            case "§7効率強化" -> {
                return Enchantment.DIG_SPEED;
            }
            case "§7火属性" -> {
                return Enchantment.FIRE_ASPECT;
            }
            case "§7氷渡り" -> {
                return Enchantment.FROST_WALKER;
            }
            case "§7水生特攻" -> {
                return Enchantment.IMPALING;
            }
            case "§7ノックバック" -> {
                return Enchantment.KNOCKBACK;
            }
            case "§7幸運" -> {
                return Enchantment.LOOT_BONUS_BLOCKS;
            }
            case "§7ドロップ増加" -> {
                return Enchantment.LOOT_BONUS_MOBS;
            }
            case "§7宝釣り" -> {
                return Enchantment.LUCK;
            }
            case "§7入れ食い" -> {
                return Enchantment.LURE;
            }
            case "§7修繕" -> {
                return Enchantment.MENDING;
            }
            case "§7拡散" -> {
                return Enchantment.MULTISHOT;
            }
            case "§7水中呼吸" -> {
                return Enchantment.OXYGEN;
            }
            case "§7貫通" -> {
                return Enchantment.PIERCING;
            }
            case "§7ダメージ軽減" -> {
                return Enchantment.PROTECTION_ENVIRONMENTAL;
            }
            case "§7爆発耐性" -> {
                return Enchantment.PROTECTION_EXPLOSIONS;
            }
            case "§7落下耐性" -> {
                return Enchantment.PROTECTION_FALL;
            }
            case "§7火炎耐性" -> {
                return Enchantment.PROTECTION_FIRE;
            }
            case "§7飛び道具耐性" -> {
                return Enchantment.PROTECTION_PROJECTILE;
            }
            case "§7高速装填" -> {
                return Enchantment.QUICK_CHARGE;
            }
            case "§7激流" -> {
                return Enchantment.RIPTIDE;
            }
            case "§7シルクタッチ" -> {
                return Enchantment.SILK_TOUCH;
            }
            case "§7ソウルスピード" -> {
                return Enchantment.SOUL_SPEED;
            }
            case "§7範囲ダメージ" -> {
                return Enchantment.SWEEPING_EDGE;
            }
            case "§7棘の鎧" -> {
                return Enchantment.THORNS;
            }
            case "§7消滅の呪い" -> {
                return Enchantment.VANISHING_CURSE;
            }
            case "§7水中歩行" -> {
                return Enchantment.WATER_WORKER;
            }
            case "§7忠誠" -> {
                return Enchantment.LOYALTY;
            }

        }

        return null;
    }
    public static Integer SearchLevel(String level) {
        if (level == null) {
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
    public static void addItemToInventory(ItemStack itemStack, Player player) {
        if (player.getInventory().firstEmpty() == -1) {
            Item item = player.getWorld().dropItem(player.getLocation(), itemStack);
            item.setCustomNameVisible(true);
            item.setCustomName(player.getName() + "の落とし物");
            return;
        }
        player.getInventory().addItem(itemStack);
    }

    public static void addItemToInventory(ItemStack itemStack, PlayerInventory inventory, Location loc, String name) {
        if (inventory.firstEmpty() == -1) {
            Item item = loc.getWorld().dropItem(loc, itemStack);
            item.setCustomNameVisible(true);
            item.customName(Component.text(name + "の落とし物"));
            return;
        }
        inventory.addItem(itemStack);
    }

    public static boolean BrokenItemJudge(String Tool) {
        EnchantmentManager.loadEnchantment();
        return EnchantmentManager.getBrokenItemList().contains(Tool);
    }

    public static boolean BrokenItemIs(ItemStack brokenItem) {
        if (brokenItem.getType().name().equals("CHAIN_COMMAND_BLOCK")) {
            ItemNBTLoader.ItemNBTLoad(brokenItem);
            return ItemNBTLoader.hasString("BrokenItem");
        }
        return false;
    }

    /////////////////////////////////////////////////////

    public static ItemMeta addEnchant(ItemMeta itemMeta, Enchantment enchantment, Integer level) {
        if(CustomEnchants.isCustom(enchantment)){
            List<Component> lore = itemMeta.lore();
            if(lore == null){
                lore = new ArrayList<>();
            }
            if(itemMeta instanceof EnchantmentStorageMeta meta){
                meta.addStoredEnchant(enchantment,level,true);
                lore.add(makeEnchantLore(enchantment,level));
                meta.lore(lore);
                return meta;
            }else {
                itemMeta.addEnchant(enchantment,level,true);
                lore.add(makeEnchantLore(enchantment,level));
                itemMeta.lore(lore);
                return itemMeta;
            }
        }else {
            if(itemMeta instanceof EnchantmentStorageMeta meta){
                meta.addStoredEnchant(enchantment,level,true);
                return meta;
            }else {
                itemMeta.addEnchant(enchantment,level,true);
                return itemMeta;
            }
        }
    }
    public static ItemMeta removeEnchant(ItemMeta itemMeta,Enchantment enchantment) {
        if(CustomEnchants.isCustom(enchantment)){
            if(itemMeta instanceof EnchantmentStorageMeta meta){
                Component lore = makeEnchantLore(enchantment,meta.getStoredEnchantLevel(enchantment));
                if(meta.lore() != null){
                    List<Component> Lore = itemMeta.lore();
                    Lore.remove(lore);
                    meta.lore(Lore);
                }else {
                    plugin.getLogger().info("(PE)"+"loreがnullであった。");
                }
                meta.removeStoredEnchant(enchantment);
                return meta;
            }else {
                Component lore = makeEnchantLore(enchantment,itemMeta.getEnchantLevel(enchantment));
                if(itemMeta.lore() != null){
                    List<Component> Lore = itemMeta.lore();
                    Lore.remove(lore);
                    itemMeta.lore(Lore);
                }else {
                    plugin.getLogger().info("(PE)"+"loreがnullであった。");
                }
                itemMeta.removeEnchant(enchantment);
                return itemMeta;
            }
        }else {
            if(itemMeta instanceof EnchantmentStorageMeta meta){
                meta.removeStoredEnchant(enchantment);
                return meta;
            }else {
                itemMeta.removeEnchant(enchantment);
                return itemMeta;
            }
        }
    }
    public static Component makeEnchantLore(Enchantment enchantment, Integer level) {
        EnchantmentManager.loadEnchantment(enchantment.getName());
        Component text;
        if(EnchantmentManager.getMaxLevel() != 1){
            text = Component.translatable(enchantment.getKey().getKey()).asComponent().color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC,false).append(Component.text(" "+getRoma(level)));
        }else {
            text = Component.translatable(enchantment.getKey().getKey()).asComponent().color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC,false);
        }
        if(enchantment.isCursed()){
            text = text.color(NamedTextColor.RED);
        }
        return text;
    }
    public static String getRoma(Integer lv){
        switch (lv){
            case 1 ->{
                return "I";
            }
            case 2 ->{
                return "II";
            }
            case 3 ->{
                return "III";
            }
            case 4 ->{
                return "IV";
            }
            case 5 ->{
                return "V";
            }
            case 6 ->{
                return "VI";
            }
            case 7 ->{
                return "VII";
            }
            case 8 ->{
                return "VIII";
            }
            case 9 ->{
                return "IX";
            }
            case 10 ->{
                return "X";
            }
            default -> {
                return lv.toString();
            }
        }
    }
}

