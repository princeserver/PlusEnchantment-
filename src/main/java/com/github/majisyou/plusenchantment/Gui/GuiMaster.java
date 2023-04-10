package com.github.majisyou.plusenchantment.Gui;

import com.github.majisyou.plusenchantment.Config.EnchantmentManager;
import com.github.majisyou.plusenchantment.Config.ItemConfig;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import com.github.majisyou.plusenchantment.System.CustomEnchantManager;
import com.github.majisyou.plusenchantment.System.EnchantSystem;
import com.github.majisyou.plusenchantment.System.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.units.qual.N;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class GuiMaster {
    private static final PlusEnchantment plugin = PlusEnchantment.getInstance();

    public static @NotNull Inventory getAnvilGui(){
        Inventory AddEnchantGui = Bukkit.getServer().createInventory(null, 27, "エンチャント合成モード");
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
        return AddEnchantGui;
    }
    public static @NotNull Inventory getGrindstoneGui(){
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

        return RemoveEnchantGui;
    }
    public static @NotNull Inventory getScrapModeGui(PlayerInventory playerInventory){
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

        setScrapGuiAll(playerInventory,Iron,Gold,Diamond,NetherQuartz,Coal,Netherite,RedStone,Lapis,Amethyst,Copper);

        GuiContainer = new  ItemStack[]{RemoveEnchantMode,BackGround,BackGround,Iron,Diamond,Gold,NetherQuartz,Copper,BackGround,
                RepairMode,BackGround,BackGround,Coal,Netherite,RedStone,Lapis,Amethyst,BackGround,
                ScrapMode,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,};


        ScrapGui.setContents(GuiContainer);
        return ScrapGui;
    }
    public static @NotNull Inventory getRepairModeGui(){
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
        return RepairGui;
    }
    public static void setScrapGui(@NotNull PlayerInventory playerInventory, ItemStack item, Material type){
        ItemStack[] Contents = playerInventory.getContents();
        String lore1 = ChatColor.GREEN+"左クリック"+ChatColor.WHITE+"で1個加工する";
        String lore2 =ChatColor.GREEN+"右クリック"+ChatColor.WHITE+"で一括加工する";
        String lore0 = ChatColor.RED+ "<鉱石が足りません>";
        List<String> Lore = new ArrayList<>();
        int count = 0;
        for (ItemStack itemStack: Contents){
            if(itemStack != null && itemStack.getType().equals(type)){
                count += itemStack.getAmount();
            }
        }

        Integer itemAmount = EnchantmentManager.getNeedItems(type.name());
        if(count > itemAmount){
            Lore.add(lore1);
            Lore.add(lore2);
        }else {
            Lore.add(lore0);
        }
        item.setLore(Lore);
    }
    public static void setScrapGuiAll(PlayerInventory playerinventory,ItemStack Iron,ItemStack Gold,ItemStack Diamond,ItemStack NetherQuartz,ItemStack Coal,ItemStack Netherite,ItemStack RedStone,ItemStack Lapis,ItemStack Amethyst,ItemStack Copper){
        setScrapGui(playerinventory,Iron,Material.IRON_INGOT);
        setScrapGui(playerinventory,Gold,Material.GOLD_INGOT);
        setScrapGui(playerinventory,Diamond,Material.DIAMOND);
        setScrapGui(playerinventory,NetherQuartz,Material.QUARTZ);
        setScrapGui(playerinventory,Coal,Material.COAL);
        setScrapGui(playerinventory,Netherite,Material.NETHERITE_SCRAP);
        setScrapGui(playerinventory,RedStone,Material.REDSTONE);
        setScrapGui(playerinventory,Lapis,Material.LAPIS_LAZULI);
        setScrapGui(playerinventory,Amethyst,Material.AMETHYST_SHARD);
        setScrapGui(playerinventory,Copper,Material.COPPER_INGOT);
    }

    public static void addEnchantInventory(Inventory inventory, ItemStack left,ItemStack right,Player player){
        if(!EnchantSystem.EnchantItemJudge(left) || !EnchantSystem.EnchantItemJudge(right)){
            inventory.setItem(7,GuiItem.Failed());
            NeedEXPInventory(0,inventory,player);
            return;
        }

        if(left.getItemMeta().hasCustomModelData()){
            Integer customModelData = left.getItemMeta().getCustomModelData();
            List<Integer> configCustomModel = EnchantmentManager.getCustoms();
            if(!configCustomModel.contains(customModelData)){
                inventory.setItem(7,GuiItem.Failed());
                NeedEXPInventory(0,inventory,player);
                return;
            }
        }

        if(right.getItemMeta().hasCustomModelData()){
            Integer customModelData = right.getItemMeta().getCustomModelData();
            List<Integer> configCustomModel = EnchantmentManager.getCustoms();
            if(!configCustomModel.contains(customModelData)){
                inventory.setItem(7,GuiItem.Failed());
                NeedEXPInventory(0,inventory,player);
                return;
            }
        }

        left = EnchantSystem.ChangeRedShirt(left);
        right = EnchantSystem.ChangeRedShirt(right);

        ItemStack result = EnchantSystem.addItem(left,right);
        inventory.setItem(7,result);
        int needExp = EnchantSystem.cal_ExpCostAddItem(result);
        NeedEXPInventory(needExp,inventory,player);
    }

    public static void removeEnchantInventory(Inventory inventory, ItemStack left,ItemStack right,Player player){
        if(!EnchantSystem.EnchantItemJudge(left)){
            inventory.setItem(7,GuiItem.Failed());
            NeedEXPInventory(0,inventory,player);
            return;
        }

        if(right.getType().equals(Material.getMaterial(ItemConfig.getItemType("RemoveEnchantEmpty")))){
            ItemStack result = EnchantSystem.clearEnchant(left);
            inventory.setItem(7,result);
            NeedEXPInventory(1,inventory,player);
            return;
        }

        if(!right.getType().equals(Material.ENCHANTED_BOOK)){
            inventory.setItem(7,GuiItem.Failed());
            NeedEXPInventory(0,inventory,player);
            return;
        }

        if(left.getItemMeta().hasCustomModelData()){
            Integer customModelData = left.getItemMeta().getCustomModelData();
            List<Integer> configCustomModel = EnchantmentManager.getCustoms();
            if(!configCustomModel.contains(customModelData)){
                inventory.setItem(7,GuiItem.Failed());
                NeedEXPInventory(0,inventory,player);
                return;
            }
        }

        if(right.getItemMeta().hasCustomModelData()){
            Integer customModelData = right.getItemMeta().getCustomModelData();
            List<Integer> configCustomModel = EnchantmentManager.getCustoms();
            if(!configCustomModel.contains(customModelData)){
                inventory.setItem(7,GuiItem.Failed());
                NeedEXPInventory(0,inventory,player);
                return;
            }
        }

        left = EnchantSystem.ChangeRedShirt(left);
        right = EnchantSystem.ChangeRedShirt(right);

        ItemStack result = EnchantSystem.clearEnchant(left,right);
        inventory.setItem(7,result);
        NeedEXPInventory(1,inventory,player);
    }

    public static void repairItemInventory(Inventory inventory, ItemStack left,int num,Player player){
        if(!EnchantSystem.EnchantItemJudge(left) && !left.getType().equals(Material.CHAIN_COMMAND_BLOCK)){
            inventory.setItem(7,GuiItem.Failed());
            NeedEXPInventory(0,inventory,player);
            return;
        }

        if(num == 0){
            inventory.setItem(7,GuiItem.Failed());
            NeedEXPInventory(0,inventory,player);
            return;
        }

        if(left.getType().equals(Material.CHAIN_COMMAND_BLOCK)){
            left = left.clone();
            left = ItemBuilder.returnTool(left);
        }

        if(left.getItemMeta().hasCustomModelData()){
            Integer customModelData = left.getItemMeta().getCustomModelData();
            List<Integer> configCustomModel = EnchantmentManager.getCustoms();
            if(!configCustomModel.contains(customModelData)){
                inventory.setItem(7,GuiItem.Failed());
                NeedEXPInventory(0,inventory,player);
                return;
            }
        }

        if(!(left.getItemMeta() instanceof Damageable)){
            inventory.setItem(7,GuiItem.Failed());
            NeedEXPInventory(0,inventory,player);
            return;
        }

        if(left.getType().equals(Material.ENCHANTED_BOOK)){
            inventory.setItem(7,GuiItem.Failed());
            NeedEXPInventory(0,inventory,player);
            return;
        }

        left = EnchantSystem.ChangeRedShirt(left);

        ItemStack result = EnchantSystem.repairItem(left,num);
        int needExp = EnchantSystem.cal_repairItem(left,num);
        inventory.setItem(7,result);
        NeedEXPInventory(needExp,inventory,player);
    }
    public static void ChangeScrap(String Name, Player player, ClickType click, int NeedItemRate, int scrapRate){
        Material material = Material.getMaterial(Name);
        if(material == null){
            return;
        }
        if(!player.getInventory().contains(material,NeedItemRate)){
            return;
        }

        ItemStack NeedItem = new ItemStack(material);
        ItemStack Scrap = GuiItem.Scrap();

        if(click.equals(ClickType.LEFT)){
            NeedItem.setAmount(NeedItemRate);
            PlayerInventory inventory = player.getInventory();
            HashMap<Integer,ItemStack> items  = inventory.removeItem(NeedItem);
            for (Integer integer:items.keySet()){
                ItemStack itemStack = items.get(integer);
                int count2 = itemStack.getAmount();
                for (ItemStack itemStack2 : inventory.getContents()){
                    if(itemStack2==null) continue;

                    if(itemStack2.getType().equals(itemStack.getType())){
                        ItemStack itemStack3 = itemStack2.clone();
                        itemStack3.setAmount(count2);

                        HashMap<Integer,ItemStack> items2 = inventory.removeItem(itemStack3);
                        if(items2.isEmpty()){
                            break;
                        }

                        for (Integer integer2:items2.keySet()){
                            count2 -= items2.get(integer2).getAmount();
                        }
                    }
                }
            }

            Scrap.setAmount(scrapRate);
            EnchantSystem.addItemToInventory(Scrap,player);
            return;
        }

        if(click.equals(ClickType.RIGHT)){
            int count = 0;
            PlayerInventory inventory = player.getInventory();
            for (int i=0 ; i < inventory.getSize(); i++){
                ItemStack itemStack = inventory.getItem(i);
                if(itemStack != null && !itemStack.getType().equals(Material.AIR)){
                    if(itemStack.getType().equals(material)){
                        count += itemStack.getAmount();
                    }
                }
            }

            int needCount = count/NeedItemRate;
            NeedItem.setAmount(needCount*NeedItemRate);
            HashMap<Integer,ItemStack> items1  =  inventory.removeItem(NeedItem);
            for (Integer integer:items1.keySet()){
                ItemStack itemStack = items1.get(integer);
                int count2 = itemStack.getAmount();
                for (ItemStack itemStack2 : inventory.getContents()){
                    if(itemStack2==null) continue;

                    if(itemStack2.getType().equals(itemStack.getType())){
                        ItemStack itemStack3 = itemStack2.clone();
                        itemStack3.setAmount(count2);

                        HashMap<Integer,ItemStack> items2 = inventory.removeItem(itemStack3);
                        if(items2.isEmpty()){
                            break;
                        }

                        for (Integer integer2:items2.keySet()){
                            count2 -= items2.get(integer2).getAmount();
                        }
                    }
                }
            }


            int scrapAmount = needCount * scrapRate;
            Scrap.setAmount(scrapAmount);

            HashMap<Integer,ItemStack> items = inventory.addItem(Scrap);

            if(items.isEmpty()){return;}

            for (Integer integer:items.keySet()){
                ItemStack itemStack = items.get(integer);
                int count2 = itemStack.getAmount();
                itemStack.setAmount(64);
                while (count2 > 64){
                    EnchantSystem.addItemToInventory(itemStack,player);
                    count2 -= 64;
                }
                itemStack.setAmount(count2);
                EnchantSystem.addItemToInventory(itemStack,player);
            }
        }

    }
    public static void NeedEXPInventory(int cost,Inventory inventory,Player player){
        int num = 0;
        int index = 15;
        int exp = player.getLevel();
        ItemStack numberPlate = GuiItem.number();
        ItemMeta numberPlateMeta = numberPlate.getItemMeta();
        ItemStack judgePlate;
        if(cost <= exp){
            judgePlate = GuiItem.GreenCheck();
        }else {
            judgePlate = GuiItem.RedCheck();
        }
        ItemMeta judgeMeta = judgePlate.getItemMeta();
        judgeMeta.setDisplayName(ChatColor.WHITE+"必要経験値："+ChatColor.YELLOW+cost+"Lv");
        numberPlateMeta.setDisplayName(ChatColor.WHITE+"必要経験値："+ChatColor.YELLOW+cost+"Lv");

        judgePlate.setItemMeta(judgeMeta);
        inventory.setItem(12,judgePlate);

        while (cost > 0){
            num = cost % 10;
            numberPlateMeta.setCustomModelData(ItemConfig.getNumberPlate(num));
            numberPlate.setItemMeta(numberPlateMeta);

            inventory.setItem(index,numberPlate);
            cost /= 10;
            index -= 1;

            if(index < 12){
                numberPlateMeta.setCustomModelData(ItemConfig.getNumberPlate(9));
                numberPlate.setItemMeta(numberPlateMeta);
                inventory.setItem(15,numberPlate);
                inventory.setItem(14,numberPlate);
                inventory.setItem(13,numberPlate);
                break;
            }
        }


        if(index > 14){
            numberPlateMeta.setCustomModelData(ItemConfig.getNumberPlate(-1));
            numberPlate.setItemMeta(numberPlateMeta);
            inventory.setItem(15,numberPlate);
            inventory.setItem(14,numberPlate);
            inventory.setItem(13,numberPlate);

            judgePlate = GuiItem.RedCheck();
            inventory.setItem(12,judgePlate);

        }

        if(index > 13){
            numberPlateMeta.setCustomModelData(ItemConfig.getNumberPlate(-1));
            numberPlate.setItemMeta(numberPlateMeta);
            inventory.setItem(14,numberPlate);
            inventory.setItem(13,numberPlate);
        }

        if(index > 12){
            numberPlateMeta.setCustomModelData(ItemConfig.getNumberPlate(-1));
            numberPlate.setItemMeta(numberPlateMeta);
            inventory.setItem(13,numberPlate);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    public static Inventory getCustomEnchantTable(Location loc){
//        Inventory CustomEnchantTableGui = Bukkit.getServer().createInventory(null, 27, "カスタムエンチャントテーブル");
//
//        ItemStack BackGround = GuiItem.BackGroundItem();
//        ItemStack SoulEmpty = GuiItem.SoulEmpty();
//        ItemStack EnchantMode = GuiItem.EnchantMode();
//        ItemStack SoulMode = GuiItem.SoulMode();
//
//        ItemStack ExclamationMark = GuiItem.ExclamationMark();
//        ItemStack BaseEmpty = GuiItem.BaseEmpty();
//        ItemStack number = GuiItem.number();
//        ItemStack lv = GuiItem.lv();
//
//        ItemMeta lvMeta = lv.getItemMeta();
//        ItemMeta EnchMeta = EnchantMode.getItemMeta();
//
//        NamespacedKey key = new NamespacedKey(plugin,"ShelfCount");
//        int shelf = count_Shelf(loc);
//        shelf = Math.min(shelf,20);
//        PersistentDataContainer pdc = lvMeta.getPersistentDataContainer();
//        pdc.set(key, PersistentDataType.INTEGER,shelf);
//        lvMeta.setDisplayName(ChatColor.WHITE+"本棚を"+shelf+"個認識");
//        lv.setItemMeta(lvMeta);
//
//        key = new NamespacedKey(plugin,"location");
//        String locStr = loc.getWorld().getName()+":"+loc.getBlockX()+":"+loc.getBlockY()+":"+loc.getBlockZ();
//        pdc = EnchMeta.getPersistentDataContainer();
//        pdc.set(key, PersistentDataType.STRING,locStr);
//        EnchMeta.addEnchant(Enchantment.DURABILITY,1,true);
//        EnchantMode.setItemMeta(EnchMeta);
//        EnchantMode.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//
//        ItemStack[] GuiContainer = new  ItemStack[]{EnchantMode,BackGround,SoulEmpty,SoulEmpty,SoulEmpty,BackGround,BackGround,BackGround,ExclamationMark,
//                                                    SoulMode,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,
//                                                    BackGround,BackGround,BackGround,BaseEmpty,BackGround,BackGround,number,number,lv};
//        CustomEnchantTableGui.setContents(GuiContainer);
//        return CustomEnchantTableGui;
//    }

//    public static Location getLoc(ItemStack itemStack){
//        NamespacedKey key = new NamespacedKey(plugin,"location");
//        PersistentDataContainer pdc = itemStack.getItemMeta().getPersistentDataContainer();
//        if(pdc.has(key)){
//            if(pdc.get(key,PersistentDataType.STRING) != null){
//                String[] shugou = Objects.requireNonNull(pdc.get(key, PersistentDataType.STRING)).split(":");
//                return getLoc(shugou);
//            }
//        }
//        plugin.getLogger().info("(PE)"+"ロケーションを読み取る時にエラー");
//        return null;
//    }

//    private static Location getLoc(String[] shugou){
//        if(shugou.length != 4){
//            plugin.getLogger().info("(PE)"+ Arrays.toString(shugou) +"は場所を表していない");
//            return null;
//        }
//
//        String world = shugou[0];
//        int x = strToInt(shugou[1]);
//        int y = strToInt(shugou[2]);
//        int z = strToInt(shugou[3]);
//
//        return new Location(plugin.getServer().getWorld(world),x,y,z);
//    }
//
//    private static int strToInt(String str){
//        if(str.matches("[+-]?\\d*(\\.\\d+)?")){
//            return Integer.parseInt(str);
//        }
//        return 0;
//    }

//    public static int get_Shelf(ItemStack itemStack){
//        NamespacedKey key = new NamespacedKey(plugin,"ShelfCount");
//        PersistentDataContainer pdc = itemStack.getItemMeta().getPersistentDataContainer();
//        if(pdc.has(key)){
//            if(pdc.get(key,PersistentDataType.INTEGER) != null){
//                return pdc.get(key,PersistentDataType.INTEGER);
//            }
//        }
//        plugin.getLogger().info("(PE)"+"本棚カウントを読み取る時にエラー");
//        return 0;
//    }

    public static int count_Shelf(Location tableLoc){
        int count = 0;
        for (int x = tableLoc.getBlockX()-2;x <= tableLoc.getBlockX()+2;x++){
            for (int z = tableLoc.getBlockZ()-2;z <= tableLoc.getBlockZ()+2;z++){
                for (int y = tableLoc.getBlockY(); y<= tableLoc.getBlockY()+1;y++){
                    Block block = tableLoc.getWorld().getBlockAt(x,y,z);
                    if(block.getState().getType().equals(Material.BOOKSHELF)){
                        count += 1;
                    }
                }
            }
        }
        return count;
    }

//    public static void customEnchant(Inventory inventory,ItemStack soul1,ItemStack soul2,ItemStack soul3,ItemStack book,int shelf,boolean isStart,Player player){
//        int soul = countSoul(soul1,soul2,soul3);
//        if(soul == 0){
//            NeedCustomExpInventory(inventory,0,player);
//            return;
//        }
//        if(!book.getType().equals(Material.BOOK)){
//            NeedCustomExpInventory(inventory,0,player);
//            return;
//        }
//        NeedCustomExpInventory(inventory,CustomEnchantManager.calExpCost(soul,shelf),player);
//        if(isStart){
//            Map<Enchantment,Integer> enchants = CustomEnchantManager.getEnchantmentMap(soul,shelf);
//            ItemStack result = new ItemStack(Material.ENCHANTED_BOOK);
//            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) result.getItemMeta();
//            for (Enchantment enchantment:enchants.keySet()){
//                meta.addStoredEnchant(enchantment,enchants.get(enchantment),true);
//            }
//            result.setItemMeta(meta);
//            inventory.setItem(21,result);
//        }
//    }

//    public static void NeedCustomExpInventory(Inventory inventory,int cost,Player player){
//        int num = 0;
//        int index = 25;
//        int exp = player.getLevel();
//        ItemStack numberPlate = GuiItem.number();
//        ItemMeta numberPlateMeta = numberPlate.getItemMeta();
//        ItemStack judgePlate;
//        if(cost < exp){
//            judgePlate = GuiItem.GreenCheck();
//        }else {
//            judgePlate = GuiItem.RedCheck();
//        }
//        ItemMeta judgeMeta = judgePlate.getItemMeta();
//        judgeMeta.displayName(Component.text("必要経験値").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC,false).append(Component.text(cost).color(NamedTextColor.YELLOW)).append(Component.text("Lv").color(NamedTextColor.WHITE)));
//        numberPlateMeta.displayName(Component.text("必要経験値").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC,false).append(Component.text(cost).color(NamedTextColor.YELLOW)).append(Component.text("Lv").color(NamedTextColor.WHITE)));
//
//        judgePlate.setItemMeta(judgeMeta);
//        inventory.setItem(8,judgePlate);
//
//        while (cost > 0){
//            num = cost % 10;
//            numberPlateMeta.setCustomModelData(ItemConfig.getNumberPlate(num));
//            numberPlate.setItemMeta(numberPlateMeta);
//
//            inventory.setItem(index,numberPlate);
//            cost /= 10;
//            index -= 1;
//
//            if(index < 24){
//                numberPlateMeta.setCustomModelData(ItemConfig.getNumberPlate(9));
//                numberPlate.setItemMeta(numberPlateMeta);
//                inventory.setItem(25,numberPlate);
//                inventory.setItem(24,numberPlate);
//                break;
//            }
//        }
//
//
//        if(index > 24){
//            numberPlateMeta.setCustomModelData(ItemConfig.getNumberPlate(-1));
//            numberPlate.setItemMeta(numberPlateMeta);
//            inventory.setItem(25,numberPlate);
//            inventory.setItem(24,numberPlate);
//
//            judgePlate = GuiItem.RedCheck();
//            inventory.setItem(8,judgePlate);
//
//        }
//
//        if(index > 23){
//            numberPlateMeta.setCustomModelData(ItemConfig.getNumberPlate(-1));
//            numberPlate.setItemMeta(numberPlateMeta);
//            inventory.setItem(24,numberPlate);
//        }
//    }
//
//    public static int countSoul(ItemStack soul1,ItemStack soul2,ItemStack soul3){
//        int soul = 0;
//        if(soul1.getType().equals(Material.getMaterial(ItemConfig.getItemType("Soul")))){
//            if(soul1.getItemMeta().getCustomModelData() == ItemConfig.getCustomModelData("Soul")){
//                soul += 1;
//            }
//        }
//        if(soul2.getType().equals(Material.getMaterial(ItemConfig.getItemType("Soul")))){
//            if(soul2.getItemMeta().getCustomModelData() == ItemConfig.getCustomModelData("Soul")){
//                soul += 1;
//            }
//        }
//        if(soul3.getType().equals(Material.getMaterial(ItemConfig.getItemType("Soul")))){
//            if(soul3.getItemMeta().getCustomModelData() == ItemConfig.getCustomModelData("Soul")){
//                soul += 1;
//            }
//        }
//        return soul;
//    }

//    public static @NotNull Inventory getSoulModeGui(PlayerInventory playerInventory,Location loc){
//        Inventory ScrapGui = Bukkit.getServer().createInventory(null,27,"ドラゴンソウルモード");
//        ItemStack[] GuiContainer = ScrapGui.getContents();
//        ItemStack BackGround = GuiItem.BackGroundItem();
//        ItemStack WitherSkeleton = GuiItem.WitherSkeleton();
//
//        ItemStack EnchantMode = GuiItem.EnchantMode();
//        ItemStack SoulMode = GuiItem.SoulMode();
//
//        ItemMeta EnchMeta = EnchantMode.getItemMeta();
//        ItemMeta SoulMeta = SoulMode.getItemMeta();
//
//
//        NamespacedKey key = new NamespacedKey(plugin,"location");
//        String locStr = loc.getWorld().getName()+":"+loc.getBlockX()+":"+loc.getBlockY()+":"+loc.getBlockZ();
//        PersistentDataContainer pdc = EnchMeta.getPersistentDataContainer();
//        pdc.set(key, PersistentDataType.STRING,locStr);
//        EnchantMode.setItemMeta(EnchMeta);
//
//        SoulMeta.addEnchant(Enchantment.DURABILITY,1,true);
//        SoulMode.setItemMeta(SoulMeta);
//        SoulMode.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//
//
//        setSoulGuiAll(playerInventory,WitherSkeleton);
//
//        GuiContainer = new  ItemStack[]{
//                EnchantMode,BackGround,BackGround,WitherSkeleton,BackGround,BackGround,BackGround,BackGround,BackGround,
//                SoulMode,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,
//                BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,BackGround,};
//
//
//        ScrapGui.setContents(GuiContainer);
//        return ScrapGui;
//    }
//    public static void setSoulGui(@NotNull PlayerInventory playerInventory, ItemStack item, Material type){
//        ItemStack[] Contents = playerInventory.getContents();
//        String lore1 = ChatColor.GREEN+"左クリック"+ChatColor.WHITE+"で1個加工する";
//        String lore2 =ChatColor.GREEN+"右クリック"+ChatColor.WHITE+"で一括加工する";
//        String lore0 = ChatColor.RED+ "<アイテムが足りません>";
//        List<String> Lore = new ArrayList<>();
//        int count = 0;
//        for (ItemStack itemStack: Contents){
//            if(itemStack != null && itemStack.getType().equals(type)){
//                count += itemStack.getAmount();
//            }
//        }
//
//        Integer itemAmount = EnchantmentManager.getNeedSoulItems(type.name());
//        if(count > itemAmount){
//            Lore.add(lore1);
//            Lore.add(lore2);
//        }else {
//            Lore.add(lore0);
//        }
//        item.setLore(Lore);
//    }
//    public static void setSoulGuiAll(PlayerInventory playerinventory,ItemStack WitherSkeleton){
//        setSoulGui(playerinventory,WitherSkeleton,Material.WITHER_SKELETON_SKULL);
//
//    }
//    public static void ChangeSoul(String Name, Player player, ClickType click, int NeedItemRate, int soulRate){
//        Material material = Material.getMaterial(Name);
//        if(material == null){
//            return;
//        }
//        if(!player.getInventory().contains(material,NeedItemRate)){
//            return;
//        }
//
//        ItemStack NeedItem = new ItemStack(material);
//        ItemStack Soul = GuiItem.Soul();
//
//        if(click.equals(ClickType.LEFT)){
//            NeedItem.setAmount(NeedItemRate);
//            PlayerInventory inventory = player.getInventory();
//            HashMap<Integer,ItemStack> items  = inventory.removeItem(NeedItem);
//            for (Integer integer:items.keySet()){
//                ItemStack itemStack = items.get(integer);
//                int count2 = itemStack.getAmount();
//                for (ItemStack itemStack2 : inventory.getContents()){
//                    if(itemStack2==null) continue;
//
//                    if(itemStack2.getType().equals(itemStack.getType())){
//                        ItemStack itemStack3 = itemStack2.clone();
//                        itemStack3.setAmount(count2);
//
//                        HashMap<Integer,ItemStack> items2 = inventory.removeItem(itemStack3);
//                        if(items2.isEmpty()){
//                            break;
//                        }
//
//                        for (Integer integer2:items2.keySet()){
//                            count2 -= items2.get(integer2).getAmount();
//                        }
//                    }
//                }
//            }
//            Soul.setAmount(soulRate);
//            EnchantSystem.addItemToInventory(Soul,player);
//            return;
//        }
//
//        if(click.equals(ClickType.RIGHT)){
//            int count = 0;
//            PlayerInventory inventory = player.getInventory();
//            for (int i=0 ; i < inventory.getSize(); i++){
//                ItemStack itemStack = inventory.getItem(i);
//                if(itemStack != null && !itemStack.getType().equals(Material.AIR)){
//                    if(itemStack.getType().equals(material)){
//                        count += itemStack.getAmount();
//                    }
//                }
//            }
//
//            int needCount = count/NeedItemRate;
//            NeedItem.setAmount(needCount*NeedItemRate);
//            HashMap<Integer,ItemStack> items1  = inventory.removeItem(NeedItem);
//            for (Integer integer:items1.keySet()){
//                ItemStack itemStack = items1.get(integer);
//                int count2 = itemStack.getAmount();
//                for (ItemStack itemStack2 : inventory.getContents()){
//                    if(itemStack2==null) continue;
//
//                    if(itemStack2.getType().equals(itemStack.getType())){
//                        ItemStack itemStack3 = itemStack2.clone();
//                        itemStack3.setAmount(count2);
//
//                        HashMap<Integer,ItemStack> items2 = inventory.removeItem(itemStack3);
//                        if(items2.isEmpty()){
//                            break;
//                        }
//
//                        for (Integer integer2:items2.keySet()){
//                            count2 -= items2.get(integer2).getAmount();
//                        }
//                    }
//                }
//            }
//            int scrapAmount = needCount * soulRate;
//            Soul.setAmount(scrapAmount);
//
//            HashMap<Integer,ItemStack> items = inventory.addItem(Soul);
//
//            if(items.isEmpty()){return;}
//
//            for (Integer integer:items.keySet()){
//                ItemStack itemStack = items.get(integer);
//                int count2 = itemStack.getAmount();
//                itemStack.setAmount(64);
//                while (count2 > 64){
//                    player.sendMessage(count2+"");
//                    EnchantSystem.addItemToInventory(itemStack,player);
//                    count2 -= 64;
//                }
//                player.sendMessage(count2+"");
//                itemStack.setAmount(count2);
//                EnchantSystem.addItemToInventory(itemStack,player);
//            }
//        }
//
//    }

}


//        if(!(material==null)){
//            ItemStack NeedItem = new ItemStack();
//            ItemStack Scrap = GuiItem.Scrap();
//            Inventory playerinventory = player.getInventory();
//            int counter = 0;
//            if(playerinventory.contains(Material.getMaterial(Name),NeedItem)){
//                if(click==ClickType.LEFT){
//                    for (int i=0;i<playerinventory.getSize();i++){
//                        if(!(playerinventory.getItem(i)==null)){
//                            if(!(playerinventory.getItem(i).getType().equals(Material.AIR))){
//                                if(playerinventory.getItem(i).getType().equals(Material.getMaterial(Name))){
//                                    ItemStack item = playerinventory.getItem(i);
//                                    if(item.getAmount() < EnchantmentManager.getNeedItems(Name)){
//                                        continue;
//                                    }
//                                    counter=1;
//                                    if((item.getAmount() - EnchantmentManager.getScrapRate(Name))<0){
//                                        playerinventory.clear(i);
//                                        break;
//                                    }
//                                    item.setAmount(item.getAmount()-NeedItem);
//                                    playerinventory.setItem(i,item);
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                    if(counter==0){
//                        return;
//                    }
//                    Scrap.setAmount(scrapRate);
//                    if(!(playerinventory.firstEmpty()==-1)){
//                        playerinventory.addItem(Scrap);
//                    }else {
//                        for (int i=0; i < playerinventory.getSize();i++){
//                            if(!(playerinventory.getItem(i)==null)){
//                                if(playerinventory.getItem(i).getType().equals(GuiItem.Scrap().getType())){
//                                    if(playerinventory.getItem(i).getAmount()<(64-scrapRate)){
//                                        playerinventory.addItem(Scrap);
//                                        break;
//                                    }
//                                }
//                            }
//                            if(i+1 == playerinventory.getSize()){
//                                player.getWorld().dropItem(player.getLocation(),Scrap);
//                            }
//                        }
//                    }
//                }
//
//                if(click==ClickType.RIGHT){
//                    int Amount = 0;
//                    Scrap.setAmount(64);
//                    for (int i=0;i<playerinventory.getSize();i++){
//                        if(!(playerinventory.getItem(i)==null)){
//                            if(!(playerinventory.getItem(i).getType().equals(Material.AIR))){
//                                if(playerinventory.getItem(i).getType().equals(Material.getMaterial(Name))){
//                                    if(playerinventory.contains(Material.getMaterial(Name),NeedItem)){
//                                        Amount += playerinventory.getItem(i).getAmount();
//                                        playerinventory.clear(i);
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//                    Amount /= NeedItem;
//                    Amount *= scrapRate;
//
//                    //Amount - 64
//                    //NeedItem 16
//                    //scrapRate 1
//                    //4
//                    int scrapCount = 0;
//                    while (Amount>0) {
//                        if((Amount / 64) == 0){
//                            Amount -= NeedItem;
//                            scrapCount += 1;
//                            player.getInventory().remove();
//                        }
//
//                        if(Amount>=64){
//                            EnchantSystem.addItemToInventory(Scrap,player);
//
//                        }else {
//                            Scrap.setAmount(Amount);
//                            EnchantSystem.addItemToInventory(Scrap,player);
//                            break;
//                        }
//                        Amount -= 64;
//                    }
//                }
//            }
//        }
