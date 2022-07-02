package com.github.majisyou.plusenchantment.Event;

import com.github.majisyou.plusenchantment.Config.EnchantmentManager;
import com.github.majisyou.plusenchantment.Gui.GuiItem;
import com.github.majisyou.plusenchantment.Gui.GuiMaster;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import com.github.majisyou.plusenchantment.System.EnchantSystem;
import com.github.majisyou.plusenchantment.System.ItemBuilder;
import com.github.majisyou.plusenchantment.System.SoundSystem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.checkerframework.framework.qual.EnsuresQualifier;

public class PlayerClick implements Listener {

    private static final PlusEnchantment plugin = PlusEnchantment.getInstance();
    public PlayerClick(PlusEnchantment plugin){ //mainメソッド内でイベントリスナを呼び出すためのコマンド
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public static void InventoryClick(InventoryClickEvent event){
        if(event.getView().getTitle().equals("エンチャント合成モード")){
            ItemStack ClickedItem = event.getCurrentItem();
            ItemStack AIR = new ItemStack(Material.AIR,1);
            if(!(ClickedItem == null)){
                if(event.getClick().equals(ClickType.LEFT)){
                    if(!event.getClickedInventory().getType().equals(InventoryType.PLAYER)){
                        Inventory inventory = event.getClickedInventory();
                        if(ClickedItem.getType().equals(Material.WHITE_STAINED_GLASS_PANE)||EnchantSystem.EnchantItemJudge(ClickedItem.getType().toString()))
                            GuiMaster.AddEnchantInventory(inventory,event);
                    }


                    if(ClickedItem.getType().equals(Material.WHITE_STAINED_GLASS_PANE)){
                        if(ClickedItem.getItemMeta().hasCustomModelData()) {
                            if (ClickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"元のアイテムスロット")||ClickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"追加するアイテムスロット")) {
                                if (!(event.getCursor() == null)) {
                                    if (EnchantSystem.EnchantItemJudge(event.getCursor().getType().toString())) {
                                        event.setCurrentItem(AIR);
                                        SoundSystem.ClickSound((Player) event.getWhoClicked());
                                        return;
                                    }
                                }
                            }
                            event.setCancelled(true);
                            return;
                        }
                    }

                    if(EnchantSystem.EnchantItemJudge(ClickedItem.getType().toString())){
                        if(!event.getClickedInventory().getType().equals(InventoryType.PLAYER)){
                            if(event.getCursor()!=null && !event.getCursor().getType().equals(Material.AIR)){
                                if(!EnchantSystem.EnchantItemJudge(event.getCursor().getType().name())){
                                    event.setCancelled(true);
                                    return;
                                }else {
                                    if(event.getSlot()==3 || event.getSlot()==5){
                                        SoundSystem.ClickSound((Player) event.getWhoClicked());
                                        return;
                                    }
                                }
                            }
                            if(event.getSlot()==3){
                                event.setCancelled(true);
                                event.setCurrentItem(GuiItem.BaseEmpty());
                                event.setCursor(ClickedItem);
                                SoundSystem.ClickSound((Player) event.getWhoClicked());
                                return;
                            }
                            if(event.getSlot()==5){
                                event.setCancelled(true);
                                event.setCurrentItem(GuiItem.AddEmpty());
                                event.setCursor(ClickedItem);
                                SoundSystem.ClickSound((Player) event.getWhoClicked());
                                return;
                            }

                            if(event.getSlot()==7){
                                if(event.getCursor()!=null && !event.getCursor().getType().equals(Material.AIR)){
                                    event.setCancelled(true);
                                    return;
                                }
                                if(((Player) event.getWhoClicked()).getLevel() < EnchantSystem.CalculateEnchant(event.getClickedInventory().getItem(7))){
                                    event.setCancelled(true);
                                    SoundSystem.FailedSound((Player) event.getWhoClicked());
                                    return;
                                }
                                Player player = (Player) event.getWhoClicked();
                                player.setLevel(player.getLevel()-EnchantSystem.CalculateEnchant(event.getClickedInventory().getItem(7)));
                                event.setCancelled(true);
                                event.setCurrentItem(GuiItem.Failed());
                                event.setCursor(ClickedItem);
                                event.getClickedInventory().setItem(3, GuiItem.BaseEmpty());
                                event.getClickedInventory().setItem(5, GuiItem.AddEmpty());
                                GuiMaster.NeedEXPInventory(0,event.getClickedInventory(),player);
                                SoundSystem.SuccessSound((Player) event.getWhoClicked());
                                return;
                            }
                        }
                    }

                }

                if(event.getClick().equals(ClickType.SHIFT_LEFT)){
                    if(ClickedItem.getType().equals(Material.WHITE_STAINED_GLASS_PANE)){
                        if(ClickedItem.getItemMeta().hasCustomModelData()){
                            event.setCancelled(true);
                            return;
                        }
                    }

                    if(event.getClickedInventory().getType().equals(InventoryType.PLAYER)){
                        if(EnchantSystem.EnchantItemJudge(ClickedItem.getType().name())){
                            if(event.getInventory().getItem(3).getType().equals(Material.WHITE_STAINED_GLASS_PANE)){
                                event.setCancelled(true);
                                event.getInventory().setItem(3,ClickedItem);
                                event.getClickedInventory().setItem(event.getSlot(),new ItemStack(Material.AIR,1));
                                GuiMaster.AddEnchantInventory(event.getInventory(),event);
                                SoundSystem.ClickSound((Player) event.getWhoClicked());
                                return;
                            }
                            if(event.getInventory().getItem(5).getType().equals(Material.WHITE_STAINED_GLASS_PANE)){
                                event.setCancelled(true);
                                event.getInventory().setItem(5,ClickedItem);
                                event.getClickedInventory().setItem(event.getSlot(),new ItemStack(Material.AIR,1));
                                GuiMaster.AddEnchantInventory(event.getInventory(),event);
                                SoundSystem.ClickSound((Player) event.getWhoClicked());
                                return;
                            }
                            event.setCancelled(true);
                            return;
                        }
                    }else {
                        if(EnchantSystem.EnchantItemJudge(ClickedItem.getType().toString())){
                            if(event.getCursor()!=null && !event.getCursor().getType().equals(Material.AIR)){
                                SoundSystem.FailedSound((Player) event.getWhoClicked());
                                event.setCancelled(true);
                                return;
                            }

                            if(event.getWhoClicked().getInventory().firstEmpty()==-1){
                                SoundSystem.FailedSound((Player) event.getWhoClicked());
                                event.setCancelled(true);
                                return;
                            }
                            if(!event.getClickedInventory().getType().equals(InventoryType.PLAYER)){
                                if(event.getSlot()==3){
                                    event.setCancelled(true);
                                    event.setCurrentItem(GuiItem.BaseEmpty());
                                    event.getWhoClicked().getInventory().addItem(ClickedItem);
                                    GuiMaster.AddEnchantInventory(event.getInventory(),event);
                                    SoundSystem.ClickSound((Player) event.getWhoClicked());
                                    return;
                                }
                                if(event.getSlot()==5){
                                    event.setCancelled(true);
                                    event.setCurrentItem(GuiItem.AddEmpty());
                                    event.getWhoClicked().getInventory().addItem(ClickedItem);
                                    GuiMaster.AddEnchantInventory(event.getInventory(),event);
                                    SoundSystem.ClickSound((Player) event.getWhoClicked());
                                    return;
                                }

                                if(event.getSlot()==7){
                                    if(((Player) event.getWhoClicked()).getLevel() < EnchantSystem.CalculateEnchant(event.getClickedInventory().getItem(7))){
                                        event.setCancelled(true);
                                        SoundSystem.FailedSound((Player) event.getWhoClicked());
                                        return;
                                    }
                                    ((Player) event.getWhoClicked()).setLevel(((Player) event.getWhoClicked()).getLevel()-EnchantSystem.CalculateEnchant(event.getClickedInventory().getItem(7)));
                                    event.setCancelled(true);
                                    event.setCurrentItem(GuiItem.Failed());
                                    event.getWhoClicked().getInventory().addItem(ClickedItem);
                                    event.getClickedInventory().setItem(3, GuiItem.BaseEmpty());
                                    event.getClickedInventory().setItem(5, GuiItem.AddEmpty());
                                    GuiMaster.NeedEXPInventory(0,event.getClickedInventory(),(Player) event.getWhoClicked());
                                    SoundSystem.SuccessSound((Player) event.getWhoClicked());
                                    return;
                                }
                            }
                        }
                    }
                }

                if(!(event.getClickedInventory().getType().equals(InventoryType.PLAYER)) && !(event.getClick().equals(ClickType.LEFT)||event.getClick().equals(ClickType.SHIFT_LEFT))){
                    event.setCancelled(true);
                    return;
                }
            }
            return;
        }

        if(event.getView().getTitle().equals("エンチャント消去モード")){
            ItemStack ClickedItem = event.getCurrentItem();
            ItemStack AIR = new ItemStack(Material.AIR,1);
            if(!(ClickedItem == null)) {
                if(!(event.getClickedInventory().getType().equals(InventoryType.PLAYER))){
                    if(event.getSlot()==9){
                        event.setCancelled(true);
                        GuiMaster.OpenRepairMode((Player) event.getWhoClicked());
                        SoundSystem.OpenSound((Player) event.getWhoClicked());
                        return;
                    }
                    if(event.getSlot()==18){
                        event.setCancelled(true);
                        GuiMaster.OpenScrapMode((Player) event.getWhoClicked());
                        SoundSystem.OpenSound((Player) event.getWhoClicked());
                        return;
                    }
                }

                if (event.getClick().equals(ClickType.LEFT)) {
                    if (!event.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
                        Inventory inventory = event.getClickedInventory();
                        GuiMaster.RemoveEnchantInventory(inventory,event);
                    }

                    if (ClickedItem.getType().equals(Material.WHITE_STAINED_GLASS_PANE)) {
                        if (ClickedItem.getItemMeta().hasCustomModelData()) {
                            if (ClickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"元のアイテムスロット")) {
                                if (!(event.getCursor() == null)) {
                                    if (EnchantSystem.EnchantItemJudge(event.getCursor().getType().name())) {
                                        event.setCurrentItem(AIR);
                                        SoundSystem.ClickSound((Player) event.getWhoClicked());
                                        return;
                                    }
                                }
                            }
                            if (ClickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"消去するエンチャント本のスロット")){
                                if (!(event.getCursor() == null)) {
                                    if (event.getCursor().getType().equals(Material.ENCHANTED_BOOK)) {
                                        event.setCurrentItem(AIR);
                                        SoundSystem.ClickSound((Player) event.getWhoClicked());
                                        return;
                                    }
                                }
                            }
                            event.setCancelled(true);
                            return;
                        }
                    }

                    if(!(ClickedItem.getType().equals(Material.AIR))){
                        if(EnchantSystem.EnchantItemJudge(ClickedItem.getType().toString())) {
                            if (!event.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
                                if(event.getCursor()!=null && !event.getCursor().getType().equals(Material.AIR)){
                                    if(!(EnchantSystem.EnchantItemJudge(event.getCursor().getType().name()))){
                                        event.setCancelled(true);
                                    }else {
                                        if(event.getSlot()==3){
                                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                                            return;
                                        }
                                        if(event.getSlot()==5 && event.getCursor().getType().equals(Material.ENCHANTED_BOOK)){
                                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                                        }else {
                                            event.setCancelled(true);
                                        }
                                    }
                                    return;
                                }

                                if (event.getSlot() == 3) {
                                    event.setCancelled(true);
                                    event.setCurrentItem(GuiItem.BaseEmpty());
                                    event.setCursor(ClickedItem);
                                    SoundSystem.ClickSound((Player) event.getWhoClicked());
                                    return;
                                }
                                if (event.getSlot() == 5) {
                                    if(event.getCursor()==null||event.getCursor().getType().equals(Material.AIR)){
                                        event.setCancelled(true);
                                        event.setCurrentItem(GuiItem.RemoveEnchantEmpty());
                                        event.setCursor(ClickedItem);
                                    }
                                    if(!(event.getCursor().getType().equals(Material.ENCHANTED_BOOK))){
                                        event.setCancelled(true);
                                    }
                                    SoundSystem.ClickSound((Player) event.getWhoClicked());
                                    return;
                                }

                                if (event.getSlot() == 7) {
                                    if(event.getCursor()!=null && !event.getCursor().getType().equals(Material.AIR)){
                                        event.setCancelled(true);
                                        return;
                                    }

                                    if(((Player) event.getWhoClicked()).getLevel() < 1){
                                        event.setCancelled(true);
                                        SoundSystem.FailedSound((Player) event.getWhoClicked());
                                        return;
                                    }
                                    event.setCancelled(true);
                                    ((Player) event.getWhoClicked()).setLevel(((Player) event.getWhoClicked()).getLevel()-1);
                                    event.setCurrentItem(GuiItem.Failed());
                                    event.setCursor(ClickedItem);
                                    event.getClickedInventory().setItem(7, GuiItem.Failed());
                                    event.getClickedInventory().setItem(5, GuiItem.RemoveEnchantEmpty());
                                    event.getClickedInventory().setItem(3, GuiItem.BaseEmpty());
                                    GuiMaster.NeedEXPInventory(0,event.getClickedInventory(),(Player) event.getWhoClicked());
                                    SoundSystem.SuccessSound((Player) event.getWhoClicked());
                                    return;
                                }
                            }
                        }
                    }
                }

                if(event.getClick().equals(ClickType.SHIFT_LEFT)){
                    if(ClickedItem.getType().equals(Material.WHITE_STAINED_GLASS_PANE)){
                        if(ClickedItem.getItemMeta().hasCustomModelData()){
                            event.setCancelled(true);
                            return;
                        }
                    }

                    if(event.getClickedInventory().getType().equals(InventoryType.PLAYER)){
                        if(EnchantSystem.EnchantItemJudge(ClickedItem.getType().name())){
                            if(event.getInventory().getItem(3).getType().equals(Material.WHITE_STAINED_GLASS_PANE)){
                                event.setCancelled(true);
                                event.getInventory().setItem(3,ClickedItem);
                                event.getClickedInventory().setItem(event.getSlot(),new ItemStack(Material.AIR,1));
                                GuiMaster.RemoveEnchantInventory(event.getInventory(),event);
                                SoundSystem.ClickSound((Player) event.getWhoClicked());
                                return;
                            }
                            if(event.getInventory().getItem(5).getType().equals(Material.WHITE_STAINED_GLASS_PANE) && ClickedItem.getType().equals(Material.ENCHANTED_BOOK)){
                                event.setCancelled(true);
                                event.getInventory().setItem(5,ClickedItem);
                                event.getClickedInventory().setItem(event.getSlot(),new ItemStack(Material.AIR,1));
                                GuiMaster.RemoveEnchantInventory(event.getInventory(),event);
                                SoundSystem.ClickSound((Player) event.getWhoClicked());
                                return;
                            }
                            event.setCancelled(true);
                            return;
                        }
                    }else {
                        if(EnchantSystem.EnchantItemJudge(ClickedItem.getType().toString())){
                            if(event.getCursor()!=null && !event.getCursor().getType().equals(Material.AIR)){
                                SoundSystem.FailedSound((Player) event.getWhoClicked());
                                event.setCancelled(true);
                                return;
                            }
                            if(event.getWhoClicked().getInventory().firstEmpty()==-1){
                                event.setCancelled(true);
                                SoundSystem.FailedSound((Player) event.getWhoClicked());
                                return;
                            }
                            if(!event.getClickedInventory().getType().equals(InventoryType.PLAYER)){
                                if(event.getSlot()==3){
                                    event.setCancelled(true);
                                    event.setCurrentItem(GuiItem.BaseEmpty());
                                    event.getWhoClicked().getInventory().addItem(ClickedItem);
                                    GuiMaster.RemoveEnchantInventory(event.getInventory(),event);
                                    SoundSystem.ClickSound((Player) event.getWhoClicked());
                                    return;
                                }
                                if(event.getSlot()==5){
                                    event.setCancelled(true);
                                    event.setCurrentItem(GuiItem.RemoveEnchantEmpty());
                                    event.getWhoClicked().getInventory().addItem(ClickedItem);
                                    GuiMaster.RemoveEnchantInventory(event.getInventory(),event);
                                    SoundSystem.ClickSound((Player) event.getWhoClicked());
                                    return;
                                }

                                if(event.getSlot()==7){
                                    if(((Player) event.getWhoClicked()).getLevel() < 1){
                                        event.setCancelled(true);
                                        SoundSystem.FailedSound((Player) event.getWhoClicked());
                                        return;
                                    }

                                    event.setCancelled(true);
                                    ((Player) event.getWhoClicked()).setLevel(((Player) event.getWhoClicked()).getLevel()-1);
                                    event.setCurrentItem(GuiItem.Failed());
                                    event.getWhoClicked().getInventory().addItem(ClickedItem);
                                    event.getClickedInventory().setItem(7, GuiItem.Failed());
                                    event.getClickedInventory().setItem(5, GuiItem.RemoveEnchantEmpty());
                                    event.getClickedInventory().setItem(3, GuiItem.BaseEmpty());
                                    GuiMaster.NeedEXPInventory(0,event.getClickedInventory(),(Player) event.getWhoClicked());
                                    SoundSystem.SuccessSound((Player) event.getWhoClicked());
                                    return;
                                }
                            }
                        }
                    }
                }

                if(!(event.getClickedInventory().getType().equals(InventoryType.PLAYER)) && !(event.getClick().equals(ClickType.LEFT)||event.getClick().equals(ClickType.SHIFT_LEFT))){
                    event.setCancelled(true);
                    return;
                }
            }
            return;
        }

        if(event.getView().getTitle().equals("-修理モード-")){
            ItemStack ClickedItem = event.getCurrentItem();
            ItemStack AIR = new ItemStack(Material.AIR,1);

            if(event.getClick().equals(ClickType.DOUBLE_CLICK)){
                event.setCancelled(true);
                return;
            }

            if(!(ClickedItem == null)) {
                if(!(event.getClickedInventory().getType().equals(InventoryType.PLAYER))){
                    if(event.getSlot()==0){
                        event.setCancelled(true);
                        GuiMaster.OpenGrindstone((Player) event.getWhoClicked());
                        SoundSystem.OpenSound((Player) event.getWhoClicked());
                        return;
                    }
                    if(event.getSlot()==18){
                        event.setCancelled(true);
                        GuiMaster.OpenScrapMode((Player) event.getWhoClicked());
                        SoundSystem.OpenSound((Player) event.getWhoClicked());
                        return;
                    }

                }


                if (event.getClick().equals(ClickType.LEFT)) {
                    if (!event.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
                        if(!event.getCurrentItem().getType().equals(Material.COMMAND_BLOCK)){
                            Inventory inventory = event.getClickedInventory();
                            GuiMaster.RepairItemInventory(inventory,event);
                        }else {
                            if(event.getCursor()==null || event.getCursor().getType().equals(Material.AIR)){
                                Inventory inventory = event.getClickedInventory();
                                GuiMaster.RepairItemInventory(inventory,event);
                            }
                        }
                    }


                    if (ClickedItem.getType().equals(Material.WHITE_STAINED_GLASS_PANE)) {
                        if (ClickedItem.getItemMeta().hasCustomModelData()) {
                            if (ClickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"元のアイテムスロット")) {
                                if (!(event.getCursor() == null)) {
                                    if (EnchantSystem.EnchantItemJudge(event.getCursor().getType().toString()) || EnchantSystem.BrokenItemIs(event.getCursor())) {
                                        event.setCurrentItem(AIR);
                                        SoundSystem.ClickSound((Player) event.getWhoClicked());
                                        return;
                                    }
                                }
                            }
                            if (ClickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"スクラップスロット")){
                                if (!(event.getCursor() == null)) {
                                    if (event.getCursor().getType().equals(Material.COMMAND_BLOCK)) {
                                        if (event.getCursor().getItemMeta().hasCustomModelData()) {
                                            if (event.getCursor().getItemMeta().getDisplayName().equals(ChatColor.WHITE+"スクラップ")) {
                                                event.setCurrentItem(AIR);
                                                SoundSystem.ClickSound((Player) event.getWhoClicked());
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(!(ClickedItem.getType().equals(Material.AIR))){
                        if(EnchantSystem.EnchantItemJudge(ClickedItem.getType().toString())||ClickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"スクラップ")||EnchantSystem.BrokenItemIs(ClickedItem)) {
                            if (!event.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
                                if(event.getCursor()!=null && !event.getCursor().getType().equals(Material.AIR)){
                                    if(!(EnchantSystem.EnchantItemJudge(event.getCursor().getType().name()))){
                                        event.setCancelled(true);
                                        return;
                                    }else {
                                        if(event.getSlot()==3){
                                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                                            return;
                                        }
                                    }
                                }

                                if (event.getSlot() == 3) {
                                    event.setCancelled(true);
                                    event.setCurrentItem(GuiItem.BaseEmpty());
                                    event.setCursor(ClickedItem);
                                    SoundSystem.ClickSound((Player) event.getWhoClicked());
                                    return;
                                }
                                if (event.getSlot() == 5) {
                                    if(event.getCursor()==null||event.getCursor().getType().equals(Material.AIR)){
                                        event.setCancelled(true);
                                        event.setCurrentItem(GuiItem.ScrapEmpty());
                                        event.setCursor(ClickedItem);
                                    }
                                    if(!(event.getCursor().getItemMeta().getDisplayName().equals(ChatColor.WHITE+"スクラップ"))){
                                        event.setCancelled(true);
                                    }
                                    SoundSystem.ClickSound((Player) event.getWhoClicked());
                                    return;
                                }

                                if (event.getSlot() == 7) {
                                    if(event.getCursor()!=null && !event.getCursor().getType().equals(Material.AIR)){
                                        event.setCancelled(true);
                                        return;
                                    }

                                    if(event.getInventory().getItem(3).getType().equals(Material.CHAIN_COMMAND_BLOCK)){
                                        if(((Player) event.getWhoClicked()).getLevel() < EnchantSystem.CalculateRepair(EnchantSystem.ScrapCost(ItemBuilder.returnTool(event.getInventory().getItem(3)),event.getInventory().getItem(5)))){
                                            event.setCancelled(true);
                                            SoundSystem.FailedSound((Player) event.getWhoClicked());
                                            return;
                                        }
                                    }else {
                                        if(((Player) event.getWhoClicked()).getLevel() < EnchantSystem.CalculateRepair(EnchantSystem.ScrapCost(event.getInventory().getItem(3),event.getInventory().getItem(5)))){
                                            event.setCancelled(true);
                                            SoundSystem.FailedSound((Player) event.getWhoClicked());
                                            return;
                                        }
                                    }
                                    event.setCancelled(true);
                                    if(event.getInventory().getItem(3).getType().equals(Material.CHAIN_COMMAND_BLOCK)){
                                        ((Player) event.getWhoClicked()).setLevel(((Player) event.getWhoClicked()).getLevel()-EnchantSystem.CalculateRepair(EnchantSystem.ScrapCost(ItemBuilder.returnTool(event.getInventory().getItem(3)),event.getInventory().getItem(5))));
                                    }else {
                                        ((Player) event.getWhoClicked()).setLevel(((Player) event.getWhoClicked()).getLevel()-EnchantSystem.CalculateRepair(EnchantSystem.ScrapCost(event.getInventory().getItem(3),event.getInventory().getItem(5))));
                                    }
                                    event.setCurrentItem(GuiItem.Failed());
                                    event.setCursor(ClickedItem);
                                    int scrapCost = 1000000;
                                    if(event.getInventory().getItem(3).getType().equals(Material.CHAIN_COMMAND_BLOCK)){
                                       scrapCost = EnchantSystem.ScrapCost(ItemBuilder.returnTool(event.getInventory().getItem(3)),event.getInventory().getItem(5));
                                    }else {
                                        scrapCost = EnchantSystem.ScrapCost(event.getInventory().getItem(3),event.getInventory().getItem(5));
                                    }
                                    if(event.getInventory().getItem(5).getAmount() > scrapCost){
                                        event.getInventory().getItem(5).setAmount(event.getInventory().getItem(5).getAmount()-scrapCost);
                                    }else {
                                        event.getClickedInventory().setItem(5, GuiItem.ScrapEmpty());
                                    }
                                    event.getClickedInventory().setItem(3, GuiItem.BaseEmpty());
                                    GuiMaster.NeedEXPInventory(0,event.getClickedInventory(),(Player) event.getWhoClicked());
                                    SoundSystem.SuccessSound((Player) event.getWhoClicked());
                                    return;
                                }
                            }
                        }
                    }
                }

                if(event.getClick().equals(ClickType.SHIFT_LEFT)){
                    if(ClickedItem.getType().equals(Material.WHITE_STAINED_GLASS_PANE)){
                        if(ClickedItem.getItemMeta().hasCustomModelData()){
                            event.setCancelled(true);
                            return;
                        }
                    }

                    if(event.getClickedInventory().getType().equals(InventoryType.PLAYER)){
                        if(EnchantSystem.EnchantItemJudge(ClickedItem.getType().name()) || ClickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"スクラップ") || EnchantSystem.BrokenItemIs(ClickedItem)){
                            if(event.getInventory().getItem(3).getType().equals(Material.WHITE_STAINED_GLASS_PANE)){
                                event.setCancelled(true);
                                event.getInventory().setItem(3,ClickedItem);
                                event.getClickedInventory().setItem(event.getSlot(),new ItemStack(Material.AIR,1));
                                GuiMaster.RepairItemInventory(event.getInventory(),event);
                                SoundSystem.ClickSound((Player) event.getWhoClicked());
                                return;
                            }
                            if(event.getInventory().getItem(5).getType().equals(Material.WHITE_STAINED_GLASS_PANE) && ClickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"スクラップ")){
                                event.setCancelled(true);
                                event.getInventory().setItem(5,ClickedItem);
                                event.getClickedInventory().setItem(event.getSlot(),new ItemStack(Material.AIR,1));
                                GuiMaster.RepairItemInventory(event.getInventory(),event);
                                SoundSystem.ClickSound((Player) event.getWhoClicked());
                                return;
                            }
                            event.setCancelled(true);
                            return;
                        }
                    }else {
                        if(EnchantSystem.EnchantItemJudge(ClickedItem.getType().toString())||ClickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"スクラップ")||EnchantSystem.BrokenItemIs(ClickedItem)){
                            if(event.getCursor()!=null && !event.getCursor().getType().equals(Material.AIR)){
                                SoundSystem.FailedSound((Player) event.getWhoClicked());
                                event.setCancelled(true);
                                return;
                            }
                            if(event.getWhoClicked().getInventory().firstEmpty()==-1){
                                event.setCancelled(true);
                                return;
                            }
                            if(!event.getClickedInventory().getType().equals(InventoryType.PLAYER)){
                                if(event.getSlot()==3){
                                    event.setCancelled(true);
                                    event.setCurrentItem(GuiItem.BaseEmpty());
                                    event.getWhoClicked().getInventory().addItem(ClickedItem);
                                    GuiMaster.RepairItemInventory(event.getInventory(),event);
                                    SoundSystem.ClickSound((Player) event.getWhoClicked());
                                    return;
                                }
                                if(event.getSlot()==5){
                                    event.setCancelled(true);
                                    event.setCurrentItem(GuiItem.ScrapEmpty());
                                    event.getWhoClicked().getInventory().addItem(ClickedItem);
                                    GuiMaster.RepairItemInventory(event.getInventory(),event);
                                    SoundSystem.ClickSound((Player) event.getWhoClicked());
                                    return;
                                }

                                if(event.getSlot()==7){
                                    if(event.getInventory().getItem(3).getType().equals(Material.CHAIN_COMMAND_BLOCK)){
                                        if(((Player) event.getWhoClicked()).getLevel() < EnchantSystem.CalculateRepair(EnchantSystem.ScrapCost(ItemBuilder.returnTool(event.getInventory().getItem(3)),event.getInventory().getItem(5)))){
                                            event.setCancelled(true);
                                            SoundSystem.FailedSound((Player) event.getWhoClicked());
                                            return;
                                        }
                                    }else {
                                        if(((Player) event.getWhoClicked()).getLevel() < EnchantSystem.CalculateRepair(EnchantSystem.ScrapCost(event.getInventory().getItem(3),event.getInventory().getItem(5)))){
                                            event.setCancelled(true);
                                            SoundSystem.FailedSound((Player) event.getWhoClicked());
                                            return;
                                        }
                                    }
                                    event.setCancelled(true);
                                    if(event.getInventory().getItem(3).getType().equals(Material.CHAIN_COMMAND_BLOCK)){
                                        ((Player) event.getWhoClicked()).setLevel(((Player) event.getWhoClicked()).getLevel()-EnchantSystem.CalculateRepair(EnchantSystem.ScrapCost(ItemBuilder.returnTool(event.getInventory().getItem(3)),event.getInventory().getItem(5))));
                                    }else {
                                        ((Player) event.getWhoClicked()).setLevel(((Player) event.getWhoClicked()).getLevel()-EnchantSystem.CalculateRepair(EnchantSystem.ScrapCost(event.getInventory().getItem(3),event.getInventory().getItem(5))));
                                    }
                                    event.setCurrentItem(GuiItem.Failed());
                                    event.getWhoClicked().getInventory().addItem(ClickedItem);
                                    int scrapCost = 1000000;
                                    if(event.getInventory().getItem(3).getType().equals(Material.CHAIN_COMMAND_BLOCK)){
                                        scrapCost = EnchantSystem.ScrapCost(ItemBuilder.returnTool(event.getInventory().getItem(3)),event.getInventory().getItem(5));
                                    }else {
                                        scrapCost = EnchantSystem.ScrapCost(event.getInventory().getItem(3),event.getInventory().getItem(5));
                                    }
                                    if(event.getInventory().getItem(5).getAmount() > scrapCost){
                                        event.getInventory().getItem(5).setAmount(event.getInventory().getItem(5).getAmount()-scrapCost);
                                    }else {
                                        event.getClickedInventory().setItem(5, GuiItem.ScrapEmpty());
                                    }
                                    if(event.getInventory().getItem(5).getAmount() > scrapCost){
                                        event.getInventory().getItem(5).setAmount(event.getInventory().getItem(5).getAmount()-scrapCost);
                                    }else {
                                        event.getClickedInventory().setItem(5, GuiItem.ScrapEmpty());
                                    }
                                    event.getClickedInventory().setItem(3, GuiItem.BaseEmpty());
                                    GuiMaster.NeedEXPInventory(0,event.getClickedInventory(),(Player) event.getWhoClicked());
                                    SoundSystem.SuccessSound((Player) event.getWhoClicked());
                                    return;
                                }
                            }
                        }
                    }
                }

//                if(event.getClick().equals(ClickType.RIGHT)){
//                    if(ClickedItem.getType().equals(Material.COMMAND_BLOCK)){
//                        if(ClickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"スクラップ")){
//                            if(!(event.getClickedInventory().getType().equals(InventoryType.PLAYER))){
//                                if(event.getSlot() == 5){
//                                    if(ClickedItem.getAmount()==1){
//                                        if(event.getCursor()==null||event.getCursor().getType().equals(Material.AIR)){
//                                            event.setCursor(GuiItem.ScrapEmpty());
//                                        }
//                                    }
//                                }
//                            }
//                            return;
//                        }
//                    }
//                }

                if(!(event.getClickedInventory().getType().equals(InventoryType.PLAYER)) && !(event.getClick().equals(ClickType.LEFT)||event.getClick().equals(ClickType.SHIFT_LEFT))){
                    event.setCancelled(true);
                    return;
                }
            }
            return;
        }

        if(event.getView().getTitle().equals("スクラップモード")){
            if(!(event.getCurrentItem() == null)){
                if(!(event.getClickedInventory().getType().equals(InventoryType.PLAYER))) {
                    switch (event.getSlot()) {
                        case 0 -> {
                            GuiMaster.OpenGrindstone((Player) event.getWhoClicked());
                            SoundSystem.OpenSound((Player) event.getWhoClicked());
                        }
                        case 9 -> {
                            GuiMaster.OpenRepairMode((Player) event.getWhoClicked());
                            SoundSystem.OpenSound((Player) event.getWhoClicked());
                        }
                        case 3 -> {
                            String Name = "IRON_INGOT";
                            GuiMaster.ChangeScrap(Name, (Player) event.getWhoClicked(), event.getClick(), EnchantmentManager.getNeedItems(Name), EnchantmentManager.getScrapRate(Name));
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                        }
                        case 4 -> {
                            String Name = "DIAMOND";
                            GuiMaster.ChangeScrap(Name, (Player) event.getWhoClicked(), event.getClick(), EnchantmentManager.getNeedItems(Name), EnchantmentManager.getScrapRate(Name));
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                        }
                        case 5 -> {
                            String Name = "GOLD_INGOT";
                            GuiMaster.ChangeScrap(Name, (Player) event.getWhoClicked(), event.getClick(), EnchantmentManager.getNeedItems(Name), EnchantmentManager.getScrapRate(Name));
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                        }
                        case 6 -> {
                            String Name = "QUARTZ";
                            GuiMaster.ChangeScrap(Name, (Player) event.getWhoClicked(), event.getClick(), EnchantmentManager.getNeedItems(Name), EnchantmentManager.getScrapRate(Name));
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                        }
                        case 7 -> {
                            String Name = "COPPER_INGOT";
                            GuiMaster.ChangeScrap(Name, (Player) event.getWhoClicked(), event.getClick(), EnchantmentManager.getNeedItems(Name), EnchantmentManager.getScrapRate(Name));
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                        }
                        case 12 -> {
                            String Name = "COAL";
                            GuiMaster.ChangeScrap(Name, (Player) event.getWhoClicked(), event.getClick(), EnchantmentManager.getNeedItems(Name), EnchantmentManager.getScrapRate(Name));
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                        }
                        case 13 -> {
                            String Name = "NETHERITE_SCRAP";
                            GuiMaster.ChangeScrap(Name, (Player) event.getWhoClicked(), event.getClick(), EnchantmentManager.getNeedItems(Name), EnchantmentManager.getScrapRate(Name));
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                        }
                        case 14 -> {
                            String Name = "REDSTONE";
                            GuiMaster.ChangeScrap(Name, (Player) event.getWhoClicked(), event.getClick(), EnchantmentManager.getNeedItems(Name), EnchantmentManager.getScrapRate(Name));
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                        }
                        case 15 -> {
                            String Name = "LAPIS_LAZULI";
                            GuiMaster.ChangeScrap(Name, (Player) event.getWhoClicked(), event.getClick(), EnchantmentManager.getNeedItems(Name), EnchantmentManager.getScrapRate(Name));
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                        }
                        case 16 -> {
                            String Name = "AMETHYST_SHARD";
                            GuiMaster.ChangeScrap(Name, (Player) event.getWhoClicked(), event.getClick(), EnchantmentManager.getNeedItems(Name), EnchantmentManager.getScrapRate(Name));
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                        }
                    }
                }
            }
            event.setCancelled(true);
            return;
        }

        if(event.getInventory().getType().equals(InventoryType.ANVIL)){
            if(event.getClick().equals(ClickType.SHIFT_LEFT)){
                if(event.getCurrentItem()!=null){
                    if(event.getClickedInventory().getType().equals(InventoryType.ANVIL)){
                        return;
                    }
                    if(event.getInventory().getItem(0) == null ||event.getInventory().getItem(0).getType().equals(Material.AIR)){
                        if(!event.getCurrentItem().getType().equals(Material.AIR)){
                            ItemStack item = event.getCurrentItem();
                            ItemMeta itemMeta = item.getItemMeta();
                            if(itemMeta instanceof Repairable repairable){
                                repairable.setRepairCost(0);
                                item.setItemMeta(repairable);
                            }
                        }
                        return;
                    }

                }
            }
            event.setCancelled(true);
        }
    }
}
