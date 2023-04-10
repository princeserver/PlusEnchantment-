package com.github.majisyou.plusenchantment.Gui;

import com.github.majisyou.plusenchantment.Config.EnchantmentManager;
import com.github.majisyou.plusenchantment.Config.ItemConfig;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class GuiItem {
    private static final PlusEnchantment plugin = PlusEnchantment.getInstance();
    public static ItemStack BackGroundItem (){
        ItemStack BackGroundItem = new ItemStack(Material.getMaterial(ItemConfig.getItemType("BackGround")),1);
        ItemMeta BackGroundItemMeta = BackGroundItem.getItemMeta();
        BackGroundItemMeta.setDisplayName("  ");
        BackGroundItemMeta.setCustomModelData(ItemConfig.getCustomModelData("BackGround"));
        BackGroundItem.setItemMeta(BackGroundItemMeta);
        return BackGroundItem;
    }

    public static ItemStack ChangeNameModeItem (){
        ItemStack ChangeNameModeItem = new ItemStack(Material.getMaterial(ItemConfig.getItemType("ChangeNameModeItem")),1);
        ItemMeta ChangeNameModeItemMeta = ChangeNameModeItem.getItemMeta();
        List<String> Lore = new ArrayList<>();
        ChangeNameModeItemMeta.setDisplayName(ChatColor.WHITE+"-アイテム名編集モード-");
        Lore.add(ChatColor.WHITE+"アイテムに名前を付けることができます。");
        Lore.add(ChatColor.WHITE+"このモードは素手の状態で設置された金床を");
        Lore.add(ChatColor.GREEN+ "スニークしながら右クリック"+ChatColor.WHITE+"すると開くことができます。");
        ChangeNameModeItemMeta.setCustomModelData(ItemConfig.getCustomModelData("ChangeNameModeItem"));
        ChangeNameModeItemMeta.setLore(Lore);
        ChangeNameModeItem.setItemMeta(ChangeNameModeItemMeta);
        return ChangeNameModeItem;
    }

    public static ItemStack ExclamationMark(){
        ItemStack ExclamationMarkItem = new ItemStack(Material.getMaterial(ItemConfig.getItemType("ExclamationMark")),1);
        ItemMeta ExclamationMarkItemMeta = ExclamationMarkItem.getItemMeta();
        ExclamationMarkItemMeta.setDisplayName(ChatColor.WHITE+"適切なアイテムを入れてください");
        ExclamationMarkItemMeta.setCustomModelData(ItemConfig.getCustomModelData("ExclamationMark"));
        ExclamationMarkItem.setItemMeta(ExclamationMarkItemMeta);
        return ExclamationMarkItem;
    }

    public static ItemStack Plus(){
        ItemStack PlusItem = new ItemStack(Material.getMaterial(ItemConfig.getItemType("Plus")),1);
        ItemMeta PlusItemMeta = PlusItem.getItemMeta();
        PlusItemMeta.setDisplayName("  ");
        PlusItemMeta.setCustomModelData(ItemConfig.getCustomModelData("Plus"));
        PlusItem.setItemMeta(PlusItemMeta);
        return PlusItem;
    }

    public static ItemStack Minus(){
        ItemStack PlusItem = new ItemStack(Material.getMaterial(ItemConfig.getItemType("Minus")),1);
        ItemMeta PlusItemMeta = PlusItem.getItemMeta();
        PlusItemMeta.setDisplayName("  ");
        PlusItemMeta.setCustomModelData(ItemConfig.getCustomModelData("Minus"));
        PlusItem.setItemMeta(PlusItemMeta);
        return PlusItem;
    }

    public static ItemStack Arrow(){
        ItemStack ArrowItem = new ItemStack(Material.getMaterial(ItemConfig.getItemType("Arrow")),1);
        ItemMeta ArrowItemMeta = ArrowItem.getItemMeta();
        ArrowItemMeta.setDisplayName("  ");
        ArrowItemMeta.setCustomModelData(ItemConfig.getCustomModelData("Arrow"));
        ArrowItem.setItemMeta(ArrowItemMeta);
        return ArrowItem;
    }

    public static ItemStack RedCheck(){
        ItemStack CheckItem = new ItemStack(Material.getMaterial(ItemConfig.getItemType("RedCheck")),1);
        ItemMeta CheckItemMeta = CheckItem.getItemMeta();
        CheckItemMeta.setDisplayName(ChatColor.WHITE+"経験値が足りません");
        CheckItemMeta.setCustomModelData(ItemConfig.getCustomModelData("RedCheck"));
        CheckItem.setItemMeta(CheckItemMeta);
        return CheckItem;
    }

    public static ItemStack GreenCheck(){
        ItemStack CheckItem = new ItemStack(Material.getMaterial(ItemConfig.getItemType("GreenCheck")),1);
        ItemMeta CheckItemMeta = CheckItem.getItemMeta();
        CheckItemMeta.setDisplayName(ChatColor.GREEN+"クリックで決定");
        CheckItemMeta.setCustomModelData(ItemConfig.getCustomModelData("GreenCheck"));
        CheckItem.setItemMeta(CheckItemMeta);
        return CheckItem;
    }

    public static ItemStack lv(){
        ItemStack lvItem = new ItemStack(Material.getMaterial(ItemConfig.getItemType("lv")),1);
        ItemMeta lvItemMeta = lvItem.getItemMeta();
        lvItemMeta.setDisplayName(" ");
        lvItemMeta.setCustomModelData(ItemConfig.getCustomModelData("lv"));
        lvItem.setItemMeta(lvItemMeta);
        return lvItem;
    }

    public static ItemStack number(){
        //ここlvとまとめても良いかもね
        ItemStack numberItem = new ItemStack(Material.getMaterial(ItemConfig.getItemType("number")),1);
        ItemMeta numberItemMeta = numberItem.getItemMeta();
        numberItemMeta.setDisplayName(" ");
        numberItemMeta.setCustomModelData(ItemConfig.getNumberPlate(-1));
        numberItem.setItemMeta(numberItemMeta);
        return numberItem;
    }

    public static ItemStack BaseEmpty(){
        ItemStack EmptyItem = new ItemStack(Material.getMaterial(ItemConfig.getItemType("BaseEmpty")),1);
        ItemMeta EmptyItemMeta = EmptyItem.getItemMeta();
        EmptyItemMeta.setDisplayName(ChatColor.WHITE+"元のアイテムスロット");
        EmptyItemMeta.setCustomModelData(ItemConfig.getCustomModelData("BaseEmpty"));
        PersistentDataContainer pdc = EmptyItemMeta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin,"emptySlot");
        pdc.set(key, PersistentDataType.STRING,"base");
        EmptyItem.setItemMeta(EmptyItemMeta);
        return EmptyItem;
    }

    public static ItemStack SoulEmpty(){
        ItemStack EmptyItem = new ItemStack(Material.getMaterial(ItemConfig.getItemType("SoulEmpty")),1);
        ItemMeta EmptyItemMeta = EmptyItem.getItemMeta();
        EmptyItemMeta.setDisplayName(ChatColor.WHITE+"ソウルスロット");
        EmptyItemMeta.setCustomModelData(ItemConfig.getCustomModelData("SoulEmpty"));
        PersistentDataContainer pdc = EmptyItemMeta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin,"emptySlot");
        pdc.set(key, PersistentDataType.STRING,"soul");
        EmptyItem.setItemMeta(EmptyItemMeta);
        return EmptyItem;
    }
    public static ItemStack AddEmpty(){
        ItemStack EmptyItem = new ItemStack(Material.getMaterial(ItemConfig.getItemType("AddEmpty")),1);
        ItemMeta EmptyItemMeta = EmptyItem.getItemMeta();
        EmptyItemMeta.setDisplayName(ChatColor.WHITE+"追加するアイテムスロット");
        EmptyItemMeta.setCustomModelData(ItemConfig.getCustomModelData("AddEmpty"));
        PersistentDataContainer pdc = EmptyItemMeta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin,"emptySlot");
        pdc.set(key, PersistentDataType.STRING,"add");
        EmptyItem.setItemMeta(EmptyItemMeta);
        return EmptyItem;
    }

    public static ItemStack ScrapEmpty(){
        ItemStack EmptyItem = new ItemStack(Material.getMaterial(ItemConfig.getItemType("ScrapEmpty")),1);
        ItemMeta EmptyItemMeta = EmptyItem.getItemMeta();
        EmptyItemMeta.setDisplayName(ChatColor.WHITE+"スクラップスロット");
        EmptyItemMeta.setCustomModelData(ItemConfig.getCustomModelData("ScrapEmpty"));
        PersistentDataContainer pdc = EmptyItemMeta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin,"emptySlot");
        pdc.set(key, PersistentDataType.STRING,"scrap");
        EmptyItem.setItemMeta(EmptyItemMeta);
        return EmptyItem;
    }

    public static ItemStack RemoveEnchantEmpty(){
        ItemStack EmptyItem = new ItemStack(Material.getMaterial(ItemConfig.getItemType("RemoveEnchantEmpty")),1);
        ItemMeta EmptyItemMeta = EmptyItem.getItemMeta();
        EmptyItemMeta.setDisplayName(ChatColor.WHITE+"消去するエンチャント本のスロット");
        EmptyItemMeta.setCustomModelData(ItemConfig.getCustomModelData("RemoveEnchantEmpty"));
        PersistentDataContainer pdc = EmptyItemMeta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin,"emptySlot");
        pdc.set(key, PersistentDataType.STRING,"enchant");
        EmptyItem.setItemMeta(EmptyItemMeta);
        return EmptyItem;
    }

    public static ItemStack Failed(){
        ItemStack FailedItem = new ItemStack(Material.getMaterial(ItemConfig.getItemType("Failed")),1);
        ItemMeta FailedItemMeta = FailedItem.getItemMeta();
        FailedItemMeta.setDisplayName(ChatColor.RED+"スロットに適切なアイテムを入れて下さい");
        FailedItemMeta.setCustomModelData(ItemConfig.getCustomModelData("Failed"));
        FailedItem.setItemMeta(FailedItemMeta);
        return FailedItem;
    }

    public static ItemStack AddEnchantMode(){
        ItemStack AddEnchantModeItem = new ItemStack(Material.getMaterial(ItemConfig.getItemType("AddEnchantMode")),1);
        ItemMeta AddEnchantModeItemMeta = AddEnchantModeItem.getItemMeta();
        List<String> Lore = new ArrayList<>();
        Lore.add(ChatColor.WHITE+"ツールとエンチャント本を合成することができます。");
        Lore.add("合成にかかるエンチャントコストは");
        Lore.add("合成後のエンチャントの内容で決まります");
        AddEnchantModeItemMeta.setDisplayName(ChatColor.WHITE+"-エンチャント合成モード-"+ChatColor.YELLOW+"<選択中>");
        AddEnchantModeItemMeta.setLore(Lore);
        AddEnchantModeItemMeta.setCustomModelData(ItemConfig.getCustomModelData("AddEnchantMode"));
        AddEnchantModeItem.setItemMeta(AddEnchantModeItemMeta);
        return AddEnchantModeItem;
    }

    public static ItemStack RemoveEnchantMode(){
        ItemStack RemoveEnchantModeItem = new ItemStack(Material.getMaterial(ItemConfig.getItemType("RemoveEnchantMode")),1);
        ItemMeta RemoveEnchantModeItemMeta = RemoveEnchantModeItem.getItemMeta();
        List<String> Lore = new ArrayList<>();
        RemoveEnchantModeItemMeta.setDisplayName(ChatColor.WHITE+"-エンチャント消去モード-");
        Lore.add(ChatColor.WHITE+"ツールからエンチャントを消去できます。");
        Lore.add(ChatColor.GREEN+"クリック"+ChatColor.WHITE+"でエンチャント消去モードに切り替えます");
        RemoveEnchantModeItemMeta.setLore(Lore);
        RemoveEnchantModeItemMeta.setCustomModelData(ItemConfig.getCustomModelData("RemoveEnchantMode"));
        RemoveEnchantModeItem.setItemMeta(RemoveEnchantModeItemMeta);
        return RemoveEnchantModeItem;
    }

    public static ItemStack RepairMode(){
        ItemStack RepairModeItem = new ItemStack(Material.getMaterial(ItemConfig.getItemType("RepairMode")),1);
        ItemMeta RepairModeItemMeta = RepairModeItem.getItemMeta();
        List<String> Lore = new ArrayList<>();
        RepairModeItemMeta.setDisplayName(ChatColor.WHITE+"-修理モード-");
        Lore.add(ChatColor.WHITE+"スクラップでツールを修理することができます。");
        Lore.add(ChatColor.GREEN+"クリック"+ChatColor.WHITE+"で修理モードに切り替えます");
        RepairModeItemMeta.setLore(Lore);
        RepairModeItemMeta.setCustomModelData(ItemConfig.getCustomModelData("RepairMode"));
        RepairModeItem.setItemMeta(RepairModeItemMeta);
        return RepairModeItem;
    }
    public static ItemStack ScrapMode(){
        ItemStack ScrapModeItem = new ItemStack(Material.getMaterial(ItemConfig.getItemType("ScrapMode")),1);
        ItemMeta ScrapModeItemMeta = ScrapModeItem.getItemMeta();
        List<String> Lore = new ArrayList<>();
        ScrapModeItemMeta.setDisplayName(ChatColor.WHITE+"-スクラップモード-");
        Lore.add(ChatColor.WHITE+"鉱石をスクラップに加工することができます。");
        Lore.add(ChatColor.GREEN+"クリック"+ChatColor.WHITE+"でスクラップモードに切り替えます");
        ScrapModeItemMeta.setLore(Lore);
        ScrapModeItemMeta.setCustomModelData(ItemConfig.getCustomModelData("ScrapMode"));
        ScrapModeItem.setItemMeta(ScrapModeItemMeta);
        return ScrapModeItem;
    }

    public static ItemStack Iron(){
        ItemStack Iron = new ItemStack(Material.getMaterial(ItemConfig.getItemType("Iron")),1);
        ItemMeta IronMeta = Iron.getItemMeta();
        List<String> Lore = new ArrayList<>();
        String name = "IRON_INGOT";
        Integer scrapAmount = EnchantmentManager.getScrapRate(name);
        Integer itemAmount = EnchantmentManager.getNeedItems(name);
        IronMeta.setDisplayName(ChatColor.WHITE+"鉄インゴット"+ ChatColor.GOLD +itemAmount+"個"+ChatColor.WHITE+" ➡ スクラップ"+ChatColor.GOLD+scrapAmount+"個");
        IronMeta.setLore(Lore);
        Iron.setItemMeta(IronMeta);
        return Iron;
    }

    public static ItemStack Gold(){
        ItemStack Gold = new ItemStack(Material.getMaterial(ItemConfig.getItemType("Gold")),1);
        ItemMeta GoldMeta = Gold.getItemMeta();
        List<String> Lore = new ArrayList<>();
        String name = "GOLD_INGOT";
        Integer scrapAmount = EnchantmentManager.getScrapRate(name);
        Integer itemAmount = EnchantmentManager.getNeedItems(name);
        GoldMeta.setDisplayName(ChatColor.WHITE+"金インゴット"+ChatColor.GOLD +itemAmount+"個"+ChatColor.WHITE+" ➡ スクラップ"+ChatColor.GOLD+scrapAmount+"個");
        GoldMeta.setLore(Lore);
        Gold.setItemMeta(GoldMeta);
        return Gold;
    }

    public static ItemStack Diamond(){
        ItemStack Diamond = new ItemStack(Material.getMaterial(ItemConfig.getItemType("Diamond")),1);
        ItemMeta DiamondMeta = Diamond.getItemMeta();
        List<String> Lore = new ArrayList<>();
        String name = "DIAMOND";
        Integer scrapAmount = EnchantmentManager.getScrapRate(name);
        Integer itemAmount = EnchantmentManager.getNeedItems(name);
        DiamondMeta.setDisplayName(ChatColor.WHITE+"ダイヤモンド"+ChatColor.GOLD +itemAmount+"個"+ChatColor.WHITE+" ➡ スクラップ"+ChatColor.GOLD+scrapAmount+"個");
        DiamondMeta.setLore(Lore);
        Diamond.setItemMeta(DiamondMeta);
        return Diamond;
    }

    public static ItemStack NetherQuartz(){
        ItemStack NetherQuartz = new ItemStack(Material.getMaterial(ItemConfig.getItemType("NetherQuartz")),1);
        ItemMeta NetherQuartzMeta = NetherQuartz.getItemMeta();
        List<String> Lore = new ArrayList<>();
        String name = "QUARTZ";
        Integer scrapAmount = EnchantmentManager.getScrapRate(name);
        Integer itemAmount = EnchantmentManager.getNeedItems(name);
        NetherQuartzMeta.setDisplayName(ChatColor.WHITE+"ネザークォーツ"+ChatColor.GOLD +itemAmount+"個"+ChatColor.WHITE+" ➡ スクラップ"+ChatColor.GOLD+scrapAmount+"個");
        NetherQuartzMeta.setLore(Lore);
        NetherQuartz.setItemMeta(NetherQuartzMeta);
        return NetherQuartz;
    }

    public static ItemStack Copper(){
        ItemStack Copper = new ItemStack(Material.getMaterial(ItemConfig.getItemType("Copper")),1);
        ItemMeta CopperMeta = Copper.getItemMeta();
        List<String> Lore = new ArrayList<>();
        String name = "COPPER_INGOT";
        Integer scrapAmount = EnchantmentManager.getScrapRate(name);
        Integer itemAmount = EnchantmentManager.getNeedItems(name);
        CopperMeta.setDisplayName(ChatColor.WHITE+"銅インゴット"+ChatColor.GOLD +itemAmount+"個"+ChatColor.WHITE+" ➡ スクラップ"+ChatColor.GOLD+scrapAmount+"個");
        CopperMeta.setLore(Lore);
        Copper.setItemMeta(CopperMeta);
        return Copper;
    }

    public static ItemStack Coal(){
        ItemStack Coal = new ItemStack(Material.getMaterial(ItemConfig.getItemType("Coal")),1);
        ItemMeta CoalMeta = Coal.getItemMeta();
        List<String> Lore = new ArrayList<>();
        String name = "COAL";
        Integer scrapAmount = EnchantmentManager.getScrapRate(name);
        Integer itemAmount = EnchantmentManager.getNeedItems(name);
        CoalMeta.setDisplayName(ChatColor.WHITE+"石炭"+ChatColor.GOLD +itemAmount+"個"+ChatColor.WHITE+" ➡ スクラップ"+ChatColor.GOLD+scrapAmount+"個");
        CoalMeta.setLore(Lore);
        Coal.setItemMeta(CoalMeta);
        return Coal;
    }

    public static ItemStack Netherite(){
        ItemStack Netherite = new ItemStack(Material.getMaterial(ItemConfig.getItemType("Netherite")),1);
        ItemMeta NetheriteMeta = Netherite.getItemMeta();
        List<String> Lore = new ArrayList<>();
        String name = "NETHERITE_SCRAP";
        Integer scrapAmount = EnchantmentManager.getScrapRate(name);
        Integer itemAmount = EnchantmentManager.getNeedItems(name);
        NetheriteMeta.setDisplayName(ChatColor.WHITE+"ネザライトインゴットの欠片"+ChatColor.GOLD +itemAmount+"個"+ChatColor.WHITE+" ➡ スクラップ"+ChatColor.GOLD+scrapAmount+"個");
        NetheriteMeta.setLore(Lore);
        Netherite.setItemMeta(NetheriteMeta);
        return Netherite;
    }

    public static ItemStack RedStone(){
        ItemStack RedStone = new ItemStack(Material.getMaterial(ItemConfig.getItemType("RedStone")),1);
        ItemMeta RedStoneMeta = RedStone.getItemMeta();
        List<String> Lore = new ArrayList<>();
        String name = "REDSTONE";
        Integer scrapAmount = EnchantmentManager.getScrapRate(name);
        Integer itemAmount = EnchantmentManager.getNeedItems(name);
        RedStoneMeta.setDisplayName(ChatColor.WHITE+"レッドストーンダスト"+ChatColor.GOLD +itemAmount+"個"+ChatColor.WHITE+" ➡ スクラップ"+ChatColor.GOLD+scrapAmount+"個");
        RedStoneMeta.setLore(Lore);
        RedStone.setItemMeta(RedStoneMeta);
        return RedStone;
    }

    public static ItemStack Lapis(){
        ItemStack Lapis = new ItemStack(Material.getMaterial(ItemConfig.getItemType("Lapis")),1);
        ItemMeta LapisMeta = Lapis.getItemMeta();
        List<String> Lore = new ArrayList<>();
        String name = "LAPIS_LAZULI";
        Integer scrapAmount = EnchantmentManager.getScrapRate(name);
        Integer itemAmount = EnchantmentManager.getNeedItems(name);
        LapisMeta.setDisplayName(ChatColor.WHITE+"ラピスラズリ"+ChatColor.GOLD +itemAmount+"個"+ChatColor.WHITE+" ➡ スクラップ"+ChatColor.GOLD+scrapAmount+"個");
        LapisMeta.setLore(Lore);
        Lapis.setItemMeta(LapisMeta);
        return Lapis;
    }

    public static ItemStack Amethyst(){
        ItemStack Amethyst = new ItemStack(Material.getMaterial(ItemConfig.getItemType("Amethyst")),1);
        ItemMeta AmethystMeta = Amethyst.getItemMeta();
        List<String> Lore = new ArrayList<>();
        String name = "AMETHYST_SHARD";
        Integer scrapAmount = EnchantmentManager.getScrapRate(name);
        Integer itemAmount = EnchantmentManager.getNeedItems(name);
        AmethystMeta.setDisplayName(ChatColor.WHITE+"アメジストの欠片"+ChatColor.GOLD +itemAmount+"個"+ChatColor.WHITE+" ➡ スクラップ"+ChatColor.GOLD+scrapAmount+"個");
        AmethystMeta.setLore(Lore);
        Amethyst.setItemMeta(AmethystMeta);
        return Amethyst;
    }

    public static ItemStack Scrap(){
        ItemStack scrap = new ItemStack(Material.getMaterial(ItemConfig.getItemType("Scrap")),1);
        ItemMeta scrapMeta = scrap.getItemMeta();

        scrapMeta.setDisplayName(ChatColor.WHITE+"スクラップ");
        scrapMeta.setCustomModelData(ItemConfig.getCustomModelData("Scrap"));

        scrap.setItemMeta(scrapMeta);
        return scrap;
    }

    public static ItemStack Soul(){
        ItemStack scrap = new ItemStack(Material.getMaterial(ItemConfig.getItemType("Soul")),1);
        ItemMeta scrapMeta = scrap.getItemMeta();

        scrapMeta.setDisplayName(ChatColor.WHITE+"ドラゴンソウル");
        scrapMeta.setCustomModelData(ItemConfig.getCustomModelData("Soul"));

        scrap.setItemMeta(scrapMeta);
        return scrap;
    }

    public static ItemStack WitherSkeleton(){
        ItemStack Amethyst = new ItemStack(Material.getMaterial(ItemConfig.getItemType("WitherSkeleton")),1);
        ItemMeta AmethystMeta = Amethyst.getItemMeta();
        List<String> Lore = new ArrayList<>();
        String name = "WITHER_SKELETON_SKULL";
        Integer scrapAmount = EnchantmentManager.getSoulRate(name);
        Integer itemAmount = EnchantmentManager.getNeedSoulItems(name);
        AmethystMeta.setDisplayName(ChatColor.WHITE+"ウィザースケルトンの頭"+ChatColor.GOLD +itemAmount+"個"+ChatColor.WHITE+" ➡ ドラゴンソウル"+ChatColor.GOLD+scrapAmount+"個");
        AmethystMeta.setLore(Lore);
        Amethyst.setItemMeta(AmethystMeta);
        return Amethyst;
    }

    public static ItemStack EnchantMode(){
        ItemStack RemoveEnchantModeItem = new ItemStack(Material.getMaterial(ItemConfig.getItemType("EnchantMode")),1);
        ItemMeta RemoveEnchantModeItemMeta = RemoveEnchantModeItem.getItemMeta();
        List<String> Lore = new ArrayList<>();
        RemoveEnchantModeItemMeta.setDisplayName(ChatColor.WHITE+"-カスタムエンチャントモード-");
        Lore.add(ChatColor.WHITE+"本に特殊エンチャントが確率で付きます");
        Lore.add(ChatColor.GREEN+"クリック"+ChatColor.WHITE+"でカスタムエンチャントモードに切り替えます");
        RemoveEnchantModeItemMeta.setLore(Lore);
        RemoveEnchantModeItemMeta.setCustomModelData(ItemConfig.getCustomModelData("EnchantMode"));
        RemoveEnchantModeItem.setItemMeta(RemoveEnchantModeItemMeta);
        return RemoveEnchantModeItem;
    }

    public static ItemStack SoulMode(){
        ItemStack RemoveEnchantModeItem = new ItemStack(Material.getMaterial(ItemConfig.getItemType("SoulMode")),1);
        ItemMeta RemoveEnchantModeItemMeta = RemoveEnchantModeItem.getItemMeta();
        List<String> Lore = new ArrayList<>();
        RemoveEnchantModeItemMeta.setDisplayName(ChatColor.WHITE+"-ドラゴンソウルモード-");
        Lore.add(ChatColor.WHITE+"アイテムからドラゴンソウルが手に入ります");
        Lore.add(ChatColor.GREEN+"クリック"+ChatColor.WHITE+"でドラゴンソウルモードに切り替えます");
        RemoveEnchantModeItemMeta.setLore(Lore);
        RemoveEnchantModeItemMeta.setCustomModelData(ItemConfig.getCustomModelData("SoulMode"));
        RemoveEnchantModeItem.setItemMeta(RemoveEnchantModeItemMeta);
        return RemoveEnchantModeItem;
    }







}
