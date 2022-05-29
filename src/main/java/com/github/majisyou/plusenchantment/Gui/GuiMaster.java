package com.github.majisyou.plusenchantment.Gui;

import com.github.majisyou.plusenchantment.Config.EnchantmentManager;
import com.github.majisyou.plusenchantment.Config.ItemConfig;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import com.github.majisyou.plusenchantment.System.EnchantSystem;
import com.github.majisyou.plusenchantment.System.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

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
        ItemStack Minus = GuiItem.Minus();
        ItemStack Arrow = GuiItem.Arrow();
        ItemStack Failed = GuiItem.Failed();
        ItemStack ExclamationMark = GuiItem.ExclamationMark();
        ItemStack number = GuiItem.number();
        ItemStack lv = GuiItem.lv();

        ItemMeta itemMeta = RemoveEnchantMode.getItemMeta();
        itemMeta.addEnchant(Enchantment.DURABILITY,1,true);
        RemoveEnchantMode.setItemMeta(itemMeta);
        RemoveEnchantMode.addItemFlags(ItemFlag.HIDE_ENCHANTS);


        ItemStack[] GuiContainer = new  ItemStack[]{RemoveEnchantMode,BackGround,BackGround,BaseEmpty,Minus,RemoveEmpty,Arrow,Failed,BackGround,
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

        InventoryOreScrap(player.getInventory(),Iron,Gold,Diamond,NetherQuartz,Coal,Netherite,RedStone,Lapis,Amethyst,Copper);

        GuiContainer = new  ItemStack[]{RemoveEnchantMode,BackGround,BackGround,Iron,Diamond,Gold,NetherQuartz,Copper,BackGround,
                                        RepairMode,BackGround,BackGround,Coal,Netherite,RedStone,Lapis,Amethyst,BackGround,
                                        ScrapMode,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,};


        ScrapGui.setContents(GuiContainer);
        player.openInventory(ScrapGui);
    }

    public static void InventoryOreScrap(Inventory playerinventory,ItemStack Iron,ItemStack Gold,ItemStack Diamond,ItemStack NetherQuartz,ItemStack Coal,ItemStack Netherite,ItemStack RedStone,ItemStack Lapis,ItemStack Amethyst,ItemStack Copper){
        ItemStack[] Contents = playerinventory.getContents();
        int IronAmount = 0;
        int GoldAmount = 0;
        int DiamondAmount = 0;
        int NetherQuartzAmount = 0;
        int CoalAmount= 0;
        int NetheriteAmount= 0;
        int RedStoneAmount= 0;
        int LapisAmount= 0;
        int AmethystAmount= 0;
        int CopperAmount= 0;

        String lore1 =ChatColor.GREEN+"左クリック"+ChatColor.WHITE+"で1個加工する";
        String lore2 =ChatColor.GREEN+"右クリック"+ChatColor.WHITE+"で一括加工する";
        String lore0 = ChatColor.RED+ "<鉱石が足りません>";
        List<String> Lore = new ArrayList<>();

        for (ItemStack item : Contents){
            if(item != null){
                if(item.getType().equals(Material.IRON_INGOT))
                    IronAmount += item.getAmount();

                if(item.getType().equals(Material.GOLD_INGOT))
                    GoldAmount += item.getAmount();

                if(item.getType().equals(Material.DIAMOND))
                    DiamondAmount += item.getAmount();

                if(item.getType().equals(Material.COAL))
                    CoalAmount += item.getAmount();

                if(item.getType().equals(Material.QUARTZ))
                    NetherQuartzAmount += item.getAmount();

                if(item.getType().equals(Material.NETHERITE_SCRAP))
                    NetheriteAmount += item.getAmount();

                if(item.getType().equals(Material.REDSTONE))
                    RedStoneAmount += item.getAmount();

                if(item.getType().equals(Material.LAPIS_LAZULI))
                    LapisAmount += item.getAmount();

                if(item.getType().equals(Material.AMETHYST_SHARD))
                    AmethystAmount += item.getAmount();

                if(item.getType().equals(Material.COPPER_INGOT))
                    CopperAmount += item.getAmount();
            }
        }

        if(IronAmount > 8){
            Lore.add(lore1);
            Lore.add(lore2);
        }else {
            Lore.add(lore0);
        }
        Iron.setLore(Lore);
        Lore.clear();
        if(GoldAmount > 2){
            Lore.add(lore1);
            Lore.add(lore2);
        }else {
            Lore.add(lore0);
        }
        Gold.setLore(Lore);
        Lore.clear();
        if(DiamondAmount > 1){
            Lore.add(lore1);
            Lore.add(lore2);
        }else {
            Lore.add(lore0);
        }
        Diamond.setLore(Lore);
        Lore.clear();
        if(NetherQuartzAmount > 8){
            Lore.add(lore1);
            Lore.add(lore2);
        }else {
            Lore.add(lore0);
        }
        NetherQuartz.setLore(Lore);
        Lore.clear();
        if(CoalAmount > 16){
            Lore.add(lore1);
            Lore.add(lore2);
        }else {
            Lore.add(lore0);
        }
        Coal.setLore(Lore);
        Lore.clear();
        if(NetheriteAmount > 1){
            Lore.add(lore1);
            Lore.add(lore2);
        }else {
            Lore.add(lore0);
        }
        Netherite.setLore(Lore);
        Lore.clear();
        if(RedStoneAmount > 8){
            Lore.add(lore1);
            Lore.add(lore2);
        }else {
            Lore.add(lore0);
        }
        RedStone.setLore(Lore);
        Lore.clear();
        if(LapisAmount > 8){
            Lore.add(lore1);
            Lore.add(lore2);
        }else {
            Lore.add(lore0);
        }
        Lapis.setLore(Lore);
        Lore.clear();
        if(AmethystAmount > 8){
            Lore.add(lore1);
            Lore.add(lore2);
        }else {
            Lore.add(lore0);
        }
        Amethyst.setLore(Lore);
        Lore.clear();
        if(CopperAmount > 16){
            Lore.add(lore1);
            Lore.add(lore2);
        }else {
            Lore.add(lore0);
        }
        Copper.setLore(Lore);
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
            if(!(event.getClickedInventory().getType().equals(InventoryType.PLAYER))){
                int SlotNumber = event.getSlot();
                if(SlotNumber==3){
                    leftItem = AddItem;
                }
                if(SlotNumber==5){
                    rightItem = AddItem;
                }
            }

            if(EnchantSystem.EnchantItemJudge(leftItem.getType().toString()) && EnchantSystem.EnchantItemJudge(rightItem.getType().toString())){
                for (NamespacedKey key: leftItem.getItemMeta().getPersistentDataContainer().getKeys()){
                    if(key.getNamespace().equals("growthsurvival")){
                        if(EnchantSystem.ChangeRedShirt(leftItem)!=null){
                            leftItem = EnchantSystem.ChangeRedShirt(leftItem);
                            break;
                        }
                    }
                }
                for (NamespacedKey key: rightItem.getItemMeta().getPersistentDataContainer().getKeys()){
                    if(key.getNamespace().equals("growthsurvival")){
                        if(EnchantSystem.ChangeRedShirt(rightItem)!=null){
                            rightItem = EnchantSystem.ChangeRedShirt(rightItem);
                            break;
                        }
                    }
                }
                if(leftItem.getType().equals(rightItem.getType()) || rightItem.getType().equals(Material.ENCHANTED_BOOK)){
                    ItemStack resultItem = EnchantSystem.AddItem(leftItem,rightItem);
                    Integer NeedEXP = EnchantSystem.CalculateEnchant(resultItem);

                    if(!leftItem.getType().equals(Material.ENCHANTED_BOOK)&&rightItem.getType().equals(Material.ENCHANTED_BOOK)){
                        if(resultItem.getItemMeta().getEnchants().keySet().size()==0){
                            inventory.setItem(7,GuiItem.Failed());
                            NeedEXPInventory(NeedEXP,inventory,(Player) event.getWhoClicked());
                            return;
                        }
                    }
                    inventory.setItem(7,resultItem);
                    NeedEXPInventory(NeedEXP,inventory,(Player) event.getWhoClicked());
                }else {
                    inventory.setItem(7,GuiItem.Failed());
                    NeedEXPInventory(0,inventory,(Player) event.getWhoClicked());
                }
            }else{
                inventory.setItem(7,GuiItem.Failed());
                NeedEXPInventory(0,inventory,(Player) event.getWhoClicked());
            }
        }else {
            plugin.getLogger().info("(PE)"+inventory.getSize()+"の大きさ");
            plugin.getLogger().info("(PE)"+"インベントリがエンチャント合成モードでは無い");
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
                            if(playerinventory.firstEmpty()==1){
                                player.getWorld().dropItem(player.getLocation(),Scrap);
                            }else {
                                playerinventory.addItem(Scrap);
                            }
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
            ItemStack AddItem = event.getCursor();
            if(!(event.getClickedInventory().getType().equals(InventoryType.PLAYER))) {
                int SlotNumber = event.getSlot();
                if (SlotNumber == 3) {
                    leftItem = AddItem;
                }
                if (SlotNumber == 5) {
                    scrap = AddItem;
                }
            }
            if(!(scrap==null||scrap.getType().equals(Material.AIR))){
                if(scrap.getType().equals(Material.CHAIN_COMMAND_BLOCK)){
                    NeedEXPInventory(0,inventory,(Player) event.getWhoClicked());
                    inventory.setItem(7,GuiItem.Failed());
                    return;
                }
                if(scrap.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"スクラップ")){
                    if(EnchantSystem.EnchantItemJudge(leftItem.getType().toString()) && !leftItem.getType().equals(Material.ENCHANTED_BOOK)){
                        for (NamespacedKey key: leftItem.getItemMeta().getPersistentDataContainer().getKeys()){
                            if(key.getNamespace().equals("growthsurvival")){
                                if(EnchantSystem.ChangeRedShirt(leftItem)!=null){
                                    leftItem = EnchantSystem.ChangeRedShirt(leftItem);
                                    break;
                                }
                            }
                        }
                        ItemStack resultItem = EnchantSystem.RepairItem(leftItem,scrap);
                        inventory.setItem(7,resultItem);
                        Integer NeedEXP = EnchantSystem.CalculateRepair(EnchantSystem.ScrapCost(leftItem,scrap));
                        NeedEXPInventory(NeedEXP,inventory,(Player) event.getWhoClicked());
                    }else if(leftItem.getType().equals(Material.CHAIN_COMMAND_BLOCK)){
                        if (EnchantSystem.BrokenItemIs(leftItem)) {
                            ItemStack resultItem = ItemBuilder.returnTool(leftItem);
                            Integer NeedEXP = EnchantSystem.CalculateRepair(EnchantSystem.ScrapCost(resultItem,scrap));
                            resultItem = EnchantSystem.RepairItem(resultItem,scrap);
                            inventory.setItem(7,resultItem);
                            NeedEXPInventory(NeedEXP,inventory,(Player) event.getWhoClicked());
                        }else {
                            NeedEXPInventory(0,inventory,(Player) event.getWhoClicked());
                            inventory.setItem(7,GuiItem.Failed());
                        }
                    }else {
                        inventory.setItem(7,GuiItem.Failed());
                        NeedEXPInventory(0,inventory,(Player) event.getWhoClicked());
                    }
                }else{
                    inventory.setItem(7,GuiItem.Failed());
                    NeedEXPInventory(0,inventory,(Player) event.getWhoClicked());
                }
            }else {
                inventory.setItem(7,GuiItem.Failed());
                NeedEXPInventory(0,inventory,(Player) event.getWhoClicked());
            }
        }else {
            plugin.getLogger().info("(PE)"+inventory.getSize()+"の大きさ");
            plugin.getLogger().info("(PE)"+"インベントリがエンチャント合成モードでは無い");
        }
    }

    public static void RemoveEnchantInventory(Inventory inventory,InventoryClickEvent event){
        if(inventory.getSize()==27){
            ItemStack leftItem = inventory.getItem(3);
            ItemStack rightItem = inventory.getItem(5);
            ItemStack AddItem = event.getCursor();
            if(!(event.getClickedInventory().getType().equals(InventoryType.PLAYER)) && event.getClick().equals(ClickType.LEFT)){
                int SlotNumber = event.getSlot();
                if(SlotNumber==3){
                    leftItem = AddItem;
                }
                if(SlotNumber==5){
                    if(AddItem.getType().equals(Material.AIR)){
                        rightItem = GuiItem.RemoveEnchantEmpty();
                    }else {
                        rightItem = AddItem;
                    }
                }
            }
            if(EnchantSystem.EnchantItemJudge(leftItem.getType().name()) && rightItem.getType().equals(Material.ENCHANTED_BOOK)){
                for (NamespacedKey key: leftItem.getItemMeta().getPersistentDataContainer().getKeys()){
                    if(key.getNamespace().equals("growthsurvival")){
                        if(EnchantSystem.ChangeRedShirt(leftItem)!=null){
                            leftItem = EnchantSystem.ChangeRedShirt(leftItem);
                            break;
                        }
                    }
                }
                for (NamespacedKey key: rightItem.getItemMeta().getPersistentDataContainer().getKeys()){
                    if(key.getNamespace().equals("growthsurvival")){
                        if(EnchantSystem.ChangeRedShirt(rightItem)!=null){
                            rightItem = EnchantSystem.ChangeRedShirt(rightItem);
                            break;
                        }
                    }
                }

                ItemStack resultItem = EnchantSystem.RemoveEnchantItem(leftItem,rightItem);
                Integer NeedEXP = 1;
                if(!resultItem.getType().equals(Material.AIR)){
                    inventory.setItem(7,resultItem);
                    NeedEXPInventory(NeedEXP,inventory,(Player) event.getWhoClicked());
                }else {
                    NeedEXPInventory(0,inventory,(Player) event.getWhoClicked());
                    inventory.setItem(7,GuiItem.Failed());
                }
            }else if(EnchantSystem.EnchantItemJudge(leftItem.getType().name()) && (rightItem.getType().equals(Material.WHITE_STAINED_GLASS_PANE))){
                for (NamespacedKey key: leftItem.getItemMeta().getPersistentDataContainer().getKeys()){
                    if(key.getNamespace().equals("growthsurvival")){
                        if(EnchantSystem.ChangeRedShirt(leftItem)!=null){
                            leftItem = EnchantSystem.ChangeRedShirt(leftItem);
                            break;
                        }
                    }
                }
                if(rightItem.getItemMeta().hasCustomModelData()){
                    inventory.setItem(7,EnchantSystem.RemoveAllEnchantItem(leftItem));
                    NeedEXPInventory(1,inventory,(Player) event.getWhoClicked());
                }else {
                    inventory.setItem(7, GuiItem.Failed());
                    NeedEXPInventory(0, inventory, (Player) event.getWhoClicked());
                }
            }else {
                inventory.setItem(7, GuiItem.Failed());
                NeedEXPInventory(0, inventory, (Player) event.getWhoClicked());
            }
        }else {
            plugin.getLogger().info("(PE)"+inventory.getSize()+"の大きさ");
            plugin.getLogger().info("(PE)"+"インベントリがエンチャント消去モードでは無い");
        }
    }

    public static void NeedEXPInventory(Integer needExp,Inventory inventory,Player player){

        ItemStack hundredNumber = GuiItem.number();
        ItemStack tenNumber = GuiItem.number();
        ItemStack oneNumber = GuiItem.number();
        ItemStack lvNumber = GuiItem.lv();
        ItemStack Judge = player.getLevel() >= needExp ? GuiItem.GreenCheck():GuiItem.RedCheck();
        ItemMeta hundredNumberMeta = hundredNumber.getItemMeta();
        ItemMeta tenNumberMeta = tenNumber.getItemMeta();
        ItemMeta oneNumberMeta = oneNumber.getItemMeta();
        ItemMeta lvNumberMeta = lvNumber.getItemMeta();
        ItemMeta JudgeMeta = Judge.getItemMeta();
        if(EnchantSystem.EnchantItemJudge(inventory.getItem(7).getType().name())){
            hundredNumberMeta.setDisplayName(ChatColor.WHITE+"必要経験値："+ChatColor.YELLOW+needExp+"Lv");
            tenNumberMeta.setDisplayName(ChatColor.WHITE+"必要経験値："+ChatColor.YELLOW+needExp+"Lv");
            oneNumberMeta.setDisplayName(ChatColor.WHITE+"必要経験値："+ChatColor.YELLOW+needExp+"Lv");
            lvNumberMeta.setDisplayName(ChatColor.WHITE+"必要経験値："+ChatColor.YELLOW+needExp+"Lv");
        }
        if(player.getLevel() < needExp){
            JudgeMeta.setDisplayName(ChatColor.WHITE+JudgeMeta.getDisplayName()+"(現在："+ChatColor.GOLD+player.getLevel()+"Lv"+ChatColor.WHITE+"/必要"+ChatColor.GOLD+needExp+"Lv"+ChatColor.WHITE+")");
        }

        if(needExp/100 == 0){
            hundredNumberMeta.setCustomModelData(ItemConfig.getNumberPlate(-1));
            needExp -= (needExp/100)*100;
            if(needExp/10 == 0){
                hundredNumberMeta.setCustomModelData(ItemConfig.getNumberPlate(-1));
            }else {
                tenNumberMeta.setCustomModelData(ItemConfig.getNumberPlate(needExp/10));
            }
        }else {
            hundredNumberMeta.setCustomModelData(ItemConfig.getNumberPlate(needExp/100));
            needExp -= (needExp/100)*100;
            tenNumberMeta.setCustomModelData(ItemConfig.getNumberPlate(needExp/10));
        }
        needExp -= (needExp/10)*10;
        oneNumberMeta.setCustomModelData(ItemConfig.getNumberPlate(needExp));

        inventory.setItem(12,Judge);
        if(!EnchantSystem.EnchantItemJudge(inventory.getItem(7).getType().name())){
            inventory.setItem(12,GuiItem.ExclamationMark());
            hundredNumberMeta.setCustomModelData(ItemConfig.getNumberPlate(-1));
            tenNumberMeta.setCustomModelData(ItemConfig.getNumberPlate(-1));
            oneNumberMeta.setCustomModelData(ItemConfig.getNumberPlate(-1));
        }
        hundredNumber.setItemMeta(hundredNumberMeta);
        tenNumber.setItemMeta(tenNumberMeta);
        oneNumber.setItemMeta(oneNumberMeta);
        lvNumber.setItemMeta(lvNumberMeta);
        Judge.setItemMeta(JudgeMeta);
        inventory.setItem(13,hundredNumber);
        inventory.setItem(14,tenNumber);
        inventory.setItem(15,oneNumber);
        inventory.setItem(16,lvNumber);
    }
}
