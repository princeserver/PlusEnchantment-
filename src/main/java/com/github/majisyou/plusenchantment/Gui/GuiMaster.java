package com.github.majisyou.plusenchantment.Gui;

import com.github.majisyou.plusenchantment.Config.EnchantmentManager;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import com.github.majisyou.plusenchantment.System.EnchantSystem;
import com.github.majisyou.plusenchantment.System.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.units.qual.N;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiMaster {

    private static final PlusEnchantment plugin = PlusEnchantment.getInstance();

    public static void OpenAnvil(Player player){
        Inventory AddEnchantGui = Bukkit.getServer().createInventory(null,27,"エンチャント合成モード");
        ItemStack NameMode = GuiItem.ChangeNameModeItem();
        ItemStack BackGround = GuiItem.BackGroundItem();
        ItemStack AddEnchantMode = GuiItem.AddEnchantMode();
        ItemStack BaseEmpty = GuiItem.BaseEmpty();
        ItemStack AddEmpty = GuiItem.AddEmpty();
        ItemStack Plus = GuiItem.Plus();
        ItemStack Arrow = GuiItem.Arrow();
        ItemStack Failed = GuiItem.Failed();
        ItemStack ExclamationMark = GuiItem.ExclamationMark();
        ItemStack number = GuiItem.number();
        ItemStack lv = GuiItem.lv();
        ItemMeta itemMeta = AddEnchantMode.getItemMeta();
        itemMeta.addEnchant(Enchantment.DURABILITY,1,true);
        AddEnchantMode.setItemMeta(itemMeta);
        AddEnchantMode.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ItemStack[] GuiContainer = new  ItemStack[]{AddEnchantMode,BackGround,BackGround,BaseEmpty,Plus,AddEmpty,Arrow,Failed,BackGround,
                                                    NameMode,BackGround,BackGround,ExclamationMark,number,number,number,lv,BackGround,
                                                    BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,};
        AddEnchantGui.setContents(GuiContainer);
        player.openInventory(AddEnchantGui);
    }

    public static void OpenGrindstone(Player player){
        Inventory RemoveEnchantGui = Bukkit.getServer().createInventory(null,27,"エンチャント消去モード");
        ItemStack RepairMode = GuiItem.RepairMode();
        ItemStack BackGround = GuiItem.BackGroundItem();
        ItemStack RemoveEnchantMode = GuiItem.RemoveEnchantMode();
        ItemStack ScrapMode = GuiItem.ScrapMode();
        ItemStack BaseEmpty = GuiItem.BaseEmpty();
        ItemStack RemoveEmpty = GuiItem.RemoveEnchantEmpty();
        ItemStack Plus = GuiItem.Plus();
        ItemStack Arrow = GuiItem.Arrow();
        ItemStack Failed = GuiItem.Failed();
        ItemStack ExclamationMark = GuiItem.ExclamationMark();
        ItemStack number = GuiItem.number();
        ItemStack lv = GuiItem.lv();

        ItemMeta itemMeta = RemoveEnchantMode.getItemMeta();
        itemMeta.addEnchant(Enchantment.DURABILITY,1,true);
        RemoveEnchantMode.setItemMeta(itemMeta);
        RemoveEnchantMode.addItemFlags(ItemFlag.HIDE_ENCHANTS);


        ItemStack[] GuiContainer = new  ItemStack[]{RemoveEnchantMode,BackGround,BackGround,BaseEmpty,Plus,RemoveEmpty,Arrow,Failed,BackGround,
                                                    RepairMode,BackGround,BackGround,ExclamationMark,number,number,number,lv,BackGround,
                                                    ScrapMode,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,};
        RemoveEnchantGui.setContents(GuiContainer);
        player.openInventory(RemoveEnchantGui);
    }

    public static void OpenScrapMode(Player player){
        Inventory ScrapGui = Bukkit.getServer().createInventory(null,27,"スクラップモード");
        ItemStack[] GuiContainer = ScrapGui.getContents();
        ItemStack BackGround = GuiItem.BackGroundItem();
        ItemStack Iron = GuiItem.Iron();
        ItemStack Gold = GuiItem.Gold();
        ItemStack Diamond = GuiItem.Diamond();
        ItemStack NetherQuartz = GuiItem.NetherQuartz();
        ItemStack Coal = GuiItem.Coal();
        ItemStack Netherite = GuiItem.Netherite();
        ItemStack RedStone = GuiItem.RedStone();
        ItemStack Lapis = GuiItem.Lapis();
        ItemStack Amethyst = GuiItem.Amethyst();
        ItemStack Copper = GuiItem.Copper();

        ItemStack RepairMode = GuiItem.RepairMode();
        ItemStack RemoveEnchantMode = GuiItem.RemoveEnchantMode();
        ItemStack ScrapMode = GuiItem.ScrapMode();

        ItemMeta itemMeta = ScrapMode.getItemMeta();
        itemMeta.addEnchant(Enchantment.DURABILITY,1,true);
        ScrapMode.setItemMeta(itemMeta);
        ScrapMode.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        GuiContainer = new  ItemStack[]{RemoveEnchantMode,BackGround,BackGround,Iron,Diamond,Gold,NetherQuartz,Copper,BackGround,
                                        RepairMode,BackGround,BackGround,Coal,Netherite,RedStone,Lapis,Amethyst,BackGround,
                                        ScrapMode,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,};


        ScrapGui.setContents(GuiContainer);
        player.openInventory(ScrapGui);
    }

    public static void OpenRepairMode(Player player){
        Inventory RepairGui = Bukkit.getServer().createInventory(null,27,"-修理モード-");
        ItemStack RepairMode = GuiItem.RepairMode();
        ItemStack BackGround = GuiItem.BackGroundItem();
        ItemStack RemoveEnchantMode = GuiItem.RemoveEnchantMode();
        ItemStack ScrapMode = GuiItem.ScrapMode();
        ItemStack BaseEmpty = GuiItem.BaseEmpty();
        ItemStack ScrapEmpty = GuiItem.ScrapEmpty();
        ItemStack Plus = GuiItem.Plus();
        ItemStack Arrow = GuiItem.Arrow();
        ItemStack Failed = GuiItem.Failed();
        ItemStack ExclamationMark = GuiItem.ExclamationMark();
        ItemStack number = GuiItem.number();
        ItemStack lv = GuiItem.lv();

        ItemMeta itemMeta = RepairMode.getItemMeta();
        itemMeta.addEnchant(Enchantment.DURABILITY,1,true);
        RepairMode.setItemMeta(itemMeta);
        RepairMode.addItemFlags(ItemFlag.HIDE_ENCHANTS);


        ItemStack[] GuiContainer = new  ItemStack[]{RemoveEnchantMode,BackGround,BackGround,BaseEmpty,Plus,ScrapEmpty,Arrow,Failed,BackGround,
                                                    RepairMode,BackGround,BackGround,ExclamationMark,number,number,number,lv,BackGround,
                                                    ScrapMode,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,};
        RepairGui.setContents(GuiContainer);
        player.openInventory(RepairGui);
    }

    public static void AddEnchantInventory(Inventory inventory,InventoryClickEvent event){
        if(inventory.getSize()==27){
            ItemStack leftItem = inventory.getItem(3);
            ItemStack rightItem = inventory.getItem(5);
            ItemStack AddItem = event.getCursor();
            int SlotNumber = event.getSlot();
            if(SlotNumber==3){
                leftItem = AddItem;
            }
            if(SlotNumber==5){
                rightItem = AddItem;
            }
            if(EnchantSystem.EnchantItemJudge(leftItem.getType().toString()) && EnchantSystem.EnchantItemJudge(rightItem.getType().toString())){
                ItemStack resultItem = EnchantSystem.AddItem(leftItem,rightItem);
                Integer NeedEXP = EnchantSystem.CalculateEnchant(resultItem);
                plugin.getLogger().info(NeedEXP+"のEXPが必要");
                inventory.setItem(7,resultItem);
            }else{
                inventory.setItem(7,GuiItem.Failed());
            }
        }else {
            plugin.getLogger().info(inventory.getSize()+"の大きさ");
            plugin.getLogger().info("インベントリがエンチャント合成モードでは無い");
        }
    }

    public static void ChangeScrap(String Name, Player player, ClickType click, int NeedItem,int scrapRate){
        if(!(Material.getMaterial(Name)==null)){
            ItemStack Scrap = GuiItem.Scrap();
            Inventory playerinventory = player.getInventory();
            if(playerinventory.contains(Material.getMaterial(Name),NeedItem)){
                if(click==ClickType.LEFT){
                    for (int i=0;i<playerinventory.getSize();i++){
                        if(!(playerinventory.getItem(i)==null)){
                            if(!(playerinventory.getItem(i).getType().equals(Material.AIR))){
                                if(playerinventory.getItem(i).getType().equals(Material.getMaterial(Name))){
                                    ItemStack item = playerinventory.getItem(i);
                                    if((item.getAmount() - EnchantmentManager.getScrapRate(Name))<0){
                                        playerinventory.clear(i);
                                        break;
                                    }
                                    item.setAmount(item.getAmount()-NeedItem);
                                    playerinventory.setItem(i,item);
                                    break;
                                }
                            }
                        }
                    }
                    Scrap.setAmount(scrapRate);
                    if(!(playerinventory.firstEmpty()==-1)){
                        playerinventory.addItem(Scrap);
                    }else {
                        for (int i=0; i < playerinventory.getSize();i++){
                            if(!(playerinventory.getItem(i)==null)){
                                if(playerinventory.getItem(i).getType().equals(GuiItem.Scrap().getType())){
                                    if(playerinventory.getItem(i).getAmount()<(64-scrapRate)){
                                        playerinventory.addItem(Scrap);
                                        break;
                                    }
                                }
                            }
                            if(i+1 == playerinventory.getSize()){
                                player.getWorld().dropItem(player.getLocation(),Scrap);
                            }
                        }
                    }
                }

                if(click==ClickType.RIGHT){
                    int Amount = 0;

                    Scrap.setAmount(64);
                    for (int i=0;i<playerinventory.getSize();i++){
                        if(!(playerinventory.getItem(i)==null)){
                            if(!(playerinventory.getItem(i).getType().equals(Material.AIR))){
                                if(playerinventory.getItem(i).getType().equals(Material.getMaterial(Name))){
                                    if(playerinventory.contains(Material.getMaterial(Name),NeedItem)){
                                        Amount += playerinventory.getItem(i).getAmount();
                                        playerinventory.clear(i);
                                    }
                                }
                            }
                        }
                    }

                    Amount /= NeedItem;
                    Amount *= scrapRate;

                    while (Amount>0) {
                        if(Amount>=64){
                            if(!(playerinventory.firstEmpty()==-1)){
                                playerinventory.addItem(Scrap);
                            }else {
                                player.getWorld().dropItem(player.getLocation(),Scrap);
                            }
                        }else {
                            Scrap.setAmount(Amount);
                            player.getWorld().dropItem(player.getLocation(),Scrap);
                            break;
                        }
                        Amount -= 64;
                    }
                }
            }
        }
    }

    public static void RepairItemInventory(Inventory inventory,InventoryClickEvent event){
        if(inventory.getSize()==27){
            ItemStack leftItem = inventory.getItem(3);
            ItemStack scrap = inventory.getItem(5);
            Player player = (Player) event.getWhoClicked();
            ItemStack AddItem = event.getCursor();
            int SlotNumber = event.getSlot();
            if(SlotNumber==3){
                leftItem = AddItem;
            }
            if(SlotNumber==5){
                scrap = AddItem;
            }
            if(!(scrap==null||scrap.getType().equals(Material.AIR))){
                if(scrap.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"スクラップ")){
                    if(EnchantSystem.EnchantItemJudge(leftItem.getType().toString())){
                        ItemStack resultItem = EnchantSystem.RepairItem(leftItem,scrap);
                        inventory.setItem(7,resultItem);
                    }else if(leftItem.getType().equals(Material.COMMAND_BLOCK)){
                        if (EnchantSystem.BrokenItemIs(leftItem)) {
                            ItemStack resultItem = ItemBuilder.returnTool(leftItem);
                            resultItem = EnchantSystem.RepairItem(resultItem,scrap);
                            inventory.setItem(7,resultItem);
                        }else {
                            inventory.setItem(7,GuiItem.Failed());
                        }
                    }else {
                        inventory.setItem(7,GuiItem.Failed());
                    }
                }
            }else {
                inventory.setItem(7,GuiItem.Failed());
            }
        }else {
            plugin.getLogger().info(inventory.getSize()+"の大きさ");
            plugin.getLogger().info("インベントリがエンチャント合成モードでは無い");
        }
    }

}
