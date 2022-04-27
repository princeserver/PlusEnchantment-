package com.github.majisyou.plusenchantment.Gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class GuiItem {

    public static ItemStack BackGroundItem (){
        ItemStack BackGroundItem = new ItemStack(Material.WHITE_STAINED_GLASS_PANE,1);
        ItemMeta BackGroundItemMeta = BackGroundItem.getItemMeta();
        BackGroundItemMeta.setDisplayName("  ");
        BackGroundItemMeta.setCustomModelData(1);
        BackGroundItem.setItemMeta(BackGroundItemMeta);
        return BackGroundItem;
    }

    public static ItemStack ChangeNameModeItem (){
        ItemStack ChangeNameModeItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
        ItemMeta ChangeNameModeItemMeta = ChangeNameModeItem.getItemMeta();
        List<String> Lore = new ArrayList<>();
        ChangeNameModeItemMeta.setDisplayName("-アイテム名編集モード-");
        Lore.add(ChatColor.WHITE+"アイテムに名前を付けることができます。");
        Lore.add(ChatColor.WHITE+"このモードは素手の状態で設置された金床を");
        Lore.add(ChatColor.GREEN+ "スニークしながら右クリック"+ChatColor.WHITE+"すると開くことができます。");
        ChangeNameModeItemMeta.setCustomModelData(1);
        ChangeNameModeItemMeta.setLore(Lore);
        ChangeNameModeItem.setItemMeta(ChangeNameModeItemMeta);
        return ChangeNameModeItem;
    }

    public static ItemStack ChangeScrapModeItem (){
        ItemStack ChangeNameModeItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
        ItemMeta ChangeNameModeItemMeta = ChangeNameModeItem.getItemMeta();
        List<String> Lore = new ArrayList<>();
        ChangeNameModeItemMeta.setDisplayName("-アイテム名編集モード-");
        Lore.add(ChatColor.WHITE+"アイテムに名前を付けることができます。");
        Lore.add(ChatColor.WHITE+"このモードは素手の状態で設置された金床を");
        Lore.add(ChatColor.GREEN+ "スニークしながら右クリック"+ChatColor.WHITE+"すると開くことができます。");
        ChangeNameModeItemMeta.setCustomModelData(1);
        ChangeNameModeItemMeta.setLore(Lore);
        ChangeNameModeItem.setItemMeta(ChangeNameModeItemMeta);
        return ChangeNameModeItem;
    }

    public static ItemStack ExclamationMark(){
        ItemStack ExclamationMarkItem = new ItemStack(Material.WHITE_STAINED_GLASS_PANE,1);
        ItemMeta ExclamationMarkItemMeta = ExclamationMarkItem.getItemMeta();
        ExclamationMarkItemMeta.setDisplayName("適切なアイテムを入れてください");
        ExclamationMarkItemMeta.setCustomModelData(1);
        ExclamationMarkItem.setItemMeta(ExclamationMarkItemMeta);
        return ExclamationMarkItem;
    }

    public static ItemStack Plus(){
        ItemStack PlusItem = new ItemStack(Material.WHITE_STAINED_GLASS_PANE,1);
        ItemMeta PlusItemMeta = PlusItem.getItemMeta();
        PlusItemMeta.setDisplayName("  ");
        PlusItemMeta.setCustomModelData(1);
        PlusItem.setItemMeta(PlusItemMeta);
        return PlusItem;
    }

    public static ItemStack Arrow(){
        ItemStack ArrowItem = new ItemStack(Material.WHITE_STAINED_GLASS_PANE,1);
        ItemMeta ArrowItemMeta = ArrowItem.getItemMeta();
        ArrowItemMeta.setDisplayName("  ");
        ArrowItemMeta.setCustomModelData(1);
        ArrowItem.setItemMeta(ArrowItemMeta);
        return ArrowItem;
    }

    public static ItemStack Check(){
        ItemStack CheckItem = new ItemStack(Material.WHITE_STAINED_GLASS_PANE,1);
        ItemMeta CheckItemMeta = CheckItem.getItemMeta();
        CheckItemMeta.setDisplayName("　");
        CheckItemMeta.setCustomModelData(1);
        CheckItem.setItemMeta(CheckItemMeta);
        return CheckItem;
    }

    public static ItemStack lv(){
        ItemStack lvItem = new ItemStack(Material.WHITE_STAINED_GLASS_PANE,1);
        ItemMeta lvItemMeta = lvItem.getItemMeta();
        lvItemMeta.setDisplayName("ここは可変される");
        lvItemMeta.setCustomModelData(1);
        lvItem.setItemMeta(lvItemMeta);
        return lvItem;
    }

    public static ItemStack number(){
        //ここlvとまとめても良いかもね
        ItemStack numberItem = new ItemStack(Material.WHITE_STAINED_GLASS_PANE,1);
        ItemMeta numberItemMeta = numberItem.getItemMeta();
        numberItemMeta.setDisplayName("ここは可変される");
        numberItemMeta.setCustomModelData(1);
        numberItem.setItemMeta(numberItemMeta);
        return numberItem;
    }



    public static ItemStack BaseEmpty(){
        ItemStack EmptyItem = new ItemStack(Material.WHITE_STAINED_GLASS_PANE,1);
        ItemMeta EmptyItemMeta = EmptyItem.getItemMeta();
        EmptyItemMeta.setDisplayName("元のアイテムスロット");
        EmptyItemMeta.setCustomModelData(1);
        EmptyItem.setItemMeta(EmptyItemMeta);
        return EmptyItem;
    }

    public static ItemStack AddEmpty(){
        ItemStack EmptyItem = new ItemStack(Material.WHITE_STAINED_GLASS_PANE,1);
        ItemMeta EmptyItemMeta = EmptyItem.getItemMeta();
        EmptyItemMeta.setDisplayName("追加するアイテムスロット");
        EmptyItemMeta.setCustomModelData(1);
        EmptyItem.setItemMeta(EmptyItemMeta);
        return EmptyItem;
    }

    public static ItemStack ScrapEmpty(){
        ItemStack EmptyItem = new ItemStack(Material.WHITE_STAINED_GLASS_PANE,1);
        ItemMeta EmptyItemMeta = EmptyItem.getItemMeta();
        EmptyItemMeta.setDisplayName("スクラップスロット");
        EmptyItemMeta.setCustomModelData(1);
        EmptyItem.setItemMeta(EmptyItemMeta);
        return EmptyItem;
    }

    public static ItemStack RemoveEnchantEmpty(){
        ItemStack EmptyItem = new ItemStack(Material.WHITE_STAINED_GLASS_PANE,1);
        ItemMeta EmptyItemMeta = EmptyItem.getItemMeta();
        EmptyItemMeta.setDisplayName("消去するエンチャント本のスロット");
        EmptyItemMeta.setCustomModelData(1);
        EmptyItem.setItemMeta(EmptyItemMeta);
        return EmptyItem;
    }

    public static ItemStack Failed(){
        ItemStack FailedItem = new ItemStack(Material.WHITE_STAINED_GLASS_PANE,1);
        ItemMeta FailedItemMeta = FailedItem.getItemMeta();
        FailedItemMeta.setDisplayName(ChatColor.RED+"スロットにアイテムを入れて下さい");
        FailedItemMeta.setCustomModelData(1);
        FailedItem.setItemMeta(FailedItemMeta);
        return FailedItem;
    }

    public static ItemStack AddEnchantMode(){
        ItemStack AddEnchantModeItem = new ItemStack(Material.WHITE_STAINED_GLASS_PANE,1);
        ItemMeta AddEnchantModeItemMeta = AddEnchantModeItem.getItemMeta();
        List<String> Lore = new ArrayList<>();
        Lore.add(ChatColor.WHITE+"ツールとエンチャント本を合成することができます。");
        Lore.add("合成にかかるエンチャントコストは");
        Lore.add("合成後のエンチャントの内容で決まります");
        AddEnchantModeItemMeta.setDisplayName(ChatColor.WHITE+"-エンチャント合成モード-"+ChatColor.YELLOW+"<選択中>");
        AddEnchantModeItemMeta.setLore(Lore);
        AddEnchantModeItemMeta.setCustomModelData(1);
        AddEnchantModeItem.setItemMeta(AddEnchantModeItemMeta);
        return AddEnchantModeItem;
    }

    public static ItemStack RemoveEnchantMode(){
        ItemStack RemoveEnchantModeItem = new ItemStack(Material.WHITE_STAINED_GLASS_PANE,1);
        ItemMeta RemoveEnchantModeItemMeta = RemoveEnchantModeItem.getItemMeta();
        List<String> Lore = new ArrayList<>();
        RemoveEnchantModeItemMeta.setDisplayName(ChatColor.WHITE+"-エンチャント消去モード-");
        Lore.add(ChatColor.WHITE+"ツールからエンチャントを消去できます。");
        Lore.add(ChatColor.GREEN+"クリック"+ChatColor.WHITE+"でエンチャント消去モードに切り替えます");
        RemoveEnchantModeItemMeta.setLore(Lore);
        RemoveEnchantModeItemMeta.setCustomModelData(1);
        RemoveEnchantModeItem.setItemMeta(RemoveEnchantModeItemMeta);
        return RemoveEnchantModeItem;
    }

    public static ItemStack RepairMode(){
        ItemStack RepairModeItem = new ItemStack(Material.WHITE_STAINED_GLASS_PANE,1);
        ItemMeta RepairModeItemMeta = RepairModeItem.getItemMeta();
        List<String> Lore = new ArrayList<>();
        RepairModeItemMeta.setDisplayName(ChatColor.WHITE+"-修理モード-");
        Lore.add(ChatColor.WHITE+"スクラップでツールを修理することができます。");
        Lore.add(ChatColor.GREEN+"クリック"+ChatColor.WHITE+"で修理モードに切り替えます");
        RepairModeItemMeta.setLore(Lore);
        RepairModeItemMeta.setCustomModelData(1);
        RepairModeItem.setItemMeta(RepairModeItemMeta);
        return RepairModeItem;
    }

    public static ItemStack ScrapMode(){
        ItemStack ScrapModeItem = new ItemStack(Material.WHITE_STAINED_GLASS_PANE,1);
        ItemMeta ScrapModeItemMeta = ScrapModeItem.getItemMeta();
        List<String> Lore = new ArrayList<>();
        ScrapModeItemMeta.setDisplayName(ChatColor.WHITE+"-スクラップモード-");
        Lore.add(ChatColor.WHITE+"鉱石をスクラップに加工することができます。");
        Lore.add(ChatColor.GREEN+"クリック"+ChatColor.WHITE+"でスクラップモードに切り替えます");
        ScrapModeItemMeta.setLore(Lore);
        ScrapModeItemMeta.setCustomModelData(1);
        ScrapModeItem.setItemMeta(ScrapModeItemMeta);
        return ScrapModeItem;
    }

    public static ItemStack Iron(){
        ItemStack Iron = new ItemStack(Material.IRON_INGOT,1);
        ItemMeta IronMeta = Iron.getItemMeta();
        List<String> Lore = new ArrayList<>();
        IronMeta.setDisplayName(ChatColor.WHITE+"鉄インゴット"+ChatColor.LIGHT_PURPLE +"8個"+ChatColor.WHITE+" ➡ スクラップ"+ChatColor.LIGHT_PURPLE+"1個");
        Lore.add(ChatColor.RED+"<鉱石が足りません>");
        IronMeta.setLore(Lore);
        Iron.setItemMeta(IronMeta);
        return Iron;
    }

    public static ItemStack Gold(){
        ItemStack Gold = new ItemStack(Material.GOLD_INGOT,1);
        ItemMeta GoldMeta = Gold.getItemMeta();
        List<String> Lore = new ArrayList<>();
        GoldMeta.setDisplayName(ChatColor.WHITE+"金インゴット"+ChatColor.LIGHT_PURPLE +"2個"+ChatColor.WHITE+" ➡ スクラップ"+ChatColor.LIGHT_PURPLE+"1個");
        Lore.add(ChatColor.RED+"<鉱石が足りません>");
        GoldMeta.setLore(Lore);
        Gold.setItemMeta(GoldMeta);
        return Gold;
    }

    public static ItemStack Diamond(){
        ItemStack Diamond = new ItemStack(Material.DIAMOND,1);
        ItemMeta DiamondMeta = Diamond.getItemMeta();
        List<String> Lore = new ArrayList<>();
        DiamondMeta.setDisplayName(ChatColor.WHITE+"ダイヤモンド"+ChatColor.LIGHT_PURPLE +"1個"+ChatColor.WHITE+" ➡ スクラップ"+ChatColor.LIGHT_PURPLE+"4個");
        Lore.add(ChatColor.RED+"<鉱石が足りません>");
        DiamondMeta.setLore(Lore);
        Diamond.setItemMeta(DiamondMeta);
        return Diamond;
    }

    public static ItemStack NetherQuartz(){
        ItemStack NetherQuartz = new ItemStack(Material.QUARTZ,1);
        ItemMeta NetherQuartzMeta = NetherQuartz.getItemMeta();
        List<String> Lore = new ArrayList<>();
        NetherQuartzMeta.setDisplayName(ChatColor.WHITE+"ネザークォーツ"+ChatColor.LIGHT_PURPLE +"8個"+ChatColor.WHITE+" ➡ スクラップ"+ChatColor.LIGHT_PURPLE+"1個");
        Lore.add(ChatColor.RED+"<鉱石が足りません>");
        NetherQuartzMeta.setLore(Lore);
        NetherQuartz.setItemMeta(NetherQuartzMeta);
        return NetherQuartz;
    }

    public static ItemStack Copper(){
        ItemStack Copper = new ItemStack(Material.COPPER_INGOT,1);
        ItemMeta CopperMeta = Copper.getItemMeta();
        List<String> Lore = new ArrayList<>();
        CopperMeta.setDisplayName(ChatColor.WHITE+"銅インゴット"+ChatColor.LIGHT_PURPLE +"16個"+ChatColor.WHITE+" ➡ スクラップ"+ChatColor.LIGHT_PURPLE+"1個");
        Lore.add(ChatColor.RED+"<鉱石が足りません>");
        CopperMeta.setLore(Lore);
        Copper.setItemMeta(CopperMeta);
        return Copper;
    }

    public static ItemStack Coal(){
        ItemStack Coal = new ItemStack(Material.COAL,1);
        ItemMeta CoalMeta = Coal.getItemMeta();
        List<String> Lore = new ArrayList<>();
        CoalMeta.setDisplayName(ChatColor.WHITE+"石炭"+ChatColor.LIGHT_PURPLE +"16個"+ChatColor.WHITE+" ➡ スクラップ"+ChatColor.LIGHT_PURPLE+"1個");
        Lore.add(ChatColor.RED+"<鉱石が足りません>");
        CoalMeta.setLore(Lore);
        Coal.setItemMeta(CoalMeta);
        return Coal;
    }

    public static ItemStack Netherite(){
        ItemStack Netherite = new ItemStack(Material.NETHERITE_SCRAP,1);
        ItemMeta NetheriteMeta = Netherite.getItemMeta();
        List<String> Lore = new ArrayList<>();
        NetheriteMeta.setDisplayName(ChatColor.WHITE+"ネザライトインゴットの欠片"+ChatColor.LIGHT_PURPLE +"1個"+ChatColor.WHITE+" ➡ スクラップ"+ChatColor.LIGHT_PURPLE+"10個");
        Lore.add(ChatColor.RED+"<鉱石が足りません>");
        NetheriteMeta.setLore(Lore);
        Netherite.setItemMeta(NetheriteMeta);
        return Netherite;
    }

    public static ItemStack RedStone(){
        ItemStack RedStone = new ItemStack(Material.REDSTONE,1);
        ItemMeta RedStoneMeta = RedStone.getItemMeta();
        List<String> Lore = new ArrayList<>();
        RedStoneMeta.setDisplayName(ChatColor.WHITE+"レッドストーンダスト"+ChatColor.LIGHT_PURPLE +"8個"+ChatColor.WHITE+" ➡ スクラップ"+ChatColor.LIGHT_PURPLE+"1個");
        Lore.add(ChatColor.RED+"<鉱石が足りません>");
        RedStoneMeta.setLore(Lore);
        RedStone.setItemMeta(RedStoneMeta);
        return RedStone;
    }

    public static ItemStack Lapis(){
        ItemStack Lapis = new ItemStack(Material.LAPIS_LAZULI,1);
        ItemMeta LapisMeta = Lapis.getItemMeta();
        List<String> Lore = new ArrayList<>();
        LapisMeta.setDisplayName(ChatColor.WHITE+"ラピスラズリ"+ChatColor.LIGHT_PURPLE +"8個"+ChatColor.WHITE+" ➡ スクラップ"+ChatColor.LIGHT_PURPLE+"1個");
        Lore.add(ChatColor.RED+"<鉱石が足りません>");
        LapisMeta.setLore(Lore);
        Lapis.setItemMeta(LapisMeta);
        return Lapis;
    }

    public static ItemStack Amethyst(){
        ItemStack Amethyst = new ItemStack(Material.AMETHYST_SHARD,1);
        ItemMeta AmethystMeta = Amethyst.getItemMeta();
        List<String> Lore = new ArrayList<>();
        AmethystMeta.setDisplayName(ChatColor.WHITE+"アメジストの欠片"+ChatColor.LIGHT_PURPLE +"8個"+ChatColor.WHITE+" ➡ スクラップ"+ChatColor.LIGHT_PURPLE+"1個");
        Lore.add(ChatColor.RED+"<鉱石が足りません>");
        AmethystMeta.setLore(Lore);
        Amethyst.setItemMeta(AmethystMeta);
        return Amethyst;
    }

    public static ItemStack Scrap(){
        ItemStack scrap = new ItemStack(Material.COMMAND_BLOCK,1);
        ItemMeta scrapMeta = scrap.getItemMeta();
        List<String> Lore = new ArrayList<>();
        scrapMeta.setDisplayName(ChatColor.WHITE+"スクラップ");
        scrapMeta.setCustomModelData(1);

        scrap.setItemMeta(scrapMeta);
        return scrap;
    }







}
