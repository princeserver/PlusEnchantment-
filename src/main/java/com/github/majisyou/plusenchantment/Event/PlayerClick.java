package com.github.majisyou.plusenchantment.Event;

import com.github.majisyou.plusenchantment.Config.EnchantmentManager;
import com.github.majisyou.plusenchantment.Gui.GuiItem;
import com.github.majisyou.plusenchantment.Gui.GuiMaster;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import com.github.majisyou.plusenchantment.System.EnchantList;
import com.github.majisyou.plusenchantment.System.EnchantSystem;
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
import org.bukkit.inventory.PlayerInventory;

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
            Player player = (Player) event.getWhoClicked();

            int ClickSlot = event.getSlot();
            if(!(ClickedItem == null)){
                if(event.getClick().equals(ClickType.LEFT)){
                    if(!event.getClickedInventory().getType().equals(InventoryType.PLAYER)){
                        Inventory inventory = event.getClickedInventory();
                        if(ClickedItem.getType().equals(Material.WHITE_STAINED_GLASS_PANE)||EnchantSystem.EnchantItemJudge(ClickedItem.getType().toString()))
                            GuiMaster.AddEnchantInventory(inventory,event);
                    }


                    if(ClickedItem.getType().equals(Material.WHITE_STAINED_GLASS_PANE)){
                        if(ClickedItem.getItemMeta().hasCustomModelData()) {
                            if (ClickedItem.getItemMeta().getDisplayName().equals("元のアイテムスロット")||ClickedItem.getItemMeta().getDisplayName().equals("追加するアイテムスロット")) {
                                if (!(event.getCursor() == null)) {
                                    if (EnchantSystem.EnchantItemJudge(event.getCursor().getType().toString())) {
                                        event.setCurrentItem(AIR);
                                        return;
                                    }
                                }
                            }
                            event.setCancelled(true);
                        }
                    }

                    if(EnchantSystem.EnchantItemJudge(ClickedItem.getType().toString())){
                        if(!event.getClickedInventory().getType().equals(InventoryType.PLAYER)){
                            if(event.getSlot()==3){
                                event.setCancelled(true);
                                event.setCurrentItem(GuiItem.BaseEmpty());
                                event.setCursor(ClickedItem);
                            }
                            if(event.getSlot()==5){
                                event.setCancelled(true);
                                event.setCurrentItem(GuiItem.AddEmpty());
                                event.setCursor(ClickedItem);
                            }

                            if(event.getSlot()==7){
                                event.setCancelled(true);
                                event.setCurrentItem(GuiItem.Failed());
                                event.setCursor(ClickedItem);
                                event.getClickedInventory().setItem(3, GuiItem.BaseEmpty());
                                event.getClickedInventory().setItem(5, GuiItem.AddEmpty());
                            }
                        }
                    }


                }
            }
            return;
        }

        if(event.getView().getTitle().equals("エンチャント消去モード")){
            if(event.getSlot()==9){
                GuiMaster.OpenRepairMode((Player) event.getWhoClicked());
            }
            if(event.getSlot()==18){
                GuiMaster.OpenScrapMode((Player) event.getWhoClicked());
            }
            event.setCancelled(true);
            return;
        }

        if(event.getView().getTitle().equals("-修理モード-")){
            ItemStack ClickedItem = event.getCurrentItem();
            ItemStack AIR = new ItemStack(Material.AIR,1);
            if(!(ClickedItem == null)) {
                if(!(event.getClickedInventory().getType().equals(InventoryType.PLAYER))){

                    if(event.getSlot()==0){
                        GuiMaster.OpenGrindstone((Player) event.getWhoClicked());
                    }
                    if(event.getSlot()==18){
                        GuiMaster.OpenScrapMode((Player) event.getWhoClicked());
                    }
                }


                if (event.getClick().equals(ClickType.LEFT)) {
                    if (!event.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
                        Inventory inventory = event.getClickedInventory();
                        GuiMaster.RepairItemInventory(inventory,event);
                    }


                    if (ClickedItem.getType().equals(Material.WHITE_STAINED_GLASS_PANE)) {
                        if (ClickedItem.getItemMeta().hasCustomModelData()) {
                            if (ClickedItem.getItemMeta().getDisplayName().equals("元のアイテムスロット")) {
                                if (!(event.getCursor() == null)) {
                                    if (EnchantSystem.EnchantItemJudge(event.getCursor().getType().toString())||EnchantSystem.BrokenItemIs(event.getCursor())) {
                                        event.setCurrentItem(AIR);
                                        return;
                                    }
                                }
                            }
                            if (ClickedItem.getItemMeta().getDisplayName().equals("スクラップスロット")){
                                if (!(event.getCursor() == null)) {
                                    if (event.getCursor().getType().equals(Material.COMMAND_BLOCK)) {
                                        if (event.getCursor().getItemMeta().hasCustomModelData()) {
                                            if (event.getCursor().getItemMeta().getDisplayName().equals(ChatColor.WHITE+"スクラップ")) {
                                                event.setCurrentItem(AIR);
                                                return;
                                            }
                                        }
                                    }
                                }
                            }

                            event.setCancelled(true);
                        }
                    }
                }
                if(!(ClickedItem.getType().equals(Material.AIR))){
                    if(EnchantSystem.EnchantItemJudge(ClickedItem.getType().toString())||ClickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"スクラップ")||EnchantSystem.BrokenItemIs(ClickedItem)) {
                        if (!event.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
                            if (event.getSlot() == 3) {
                                event.setCancelled(true);
                                event.setCurrentItem(GuiItem.BaseEmpty());
                                event.setCursor(ClickedItem);
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
                            }

                            if (event.getSlot() == 7) {
                                event.setCancelled(true);
                                event.setCurrentItem(GuiItem.Failed());
                                event.setCursor(ClickedItem);
                                int scrapCost = EnchantSystem.ScrapCost(event.getInventory().getItem(3),event.getInventory().getItem(5));
                                if(event.getInventory().getItem(5).getAmount() > scrapCost){
//                                    plugin.getLogger().info(scrapCost+"個のスクラップが必要で");
//                                    plugin.getLogger().info(event.getInventory().getItem(5).getAmount()+"個のスクラップがあり");
//                                    plugin.getLogger().info(event.getInventory().getItem(5).getAmount()-scrapCost+"個のスクラップが残るはず");
                                    event.getInventory().getItem(5).setAmount(event.getInventory().getItem(5).getAmount()-scrapCost);
                                }else {
                                    event.getClickedInventory().setItem(5, GuiItem.ScrapEmpty());
                                }
                                event.getClickedInventory().setItem(3, GuiItem.BaseEmpty());
                            }
                        }
                    }
                }
            }

            return;
        }

        if(event.getView().getTitle().equals("スクラップモード")){
            if(!(event.getCurrentItem() == null)){
                if(!(event.getClickedInventory().getType().equals(InventoryType.PLAYER))) {
                    switch (event.getSlot()) {
                        case 0: {
                            GuiMaster.OpenGrindstone((Player) event.getWhoClicked());
                            break;
                        }
                        case 9: {
                            GuiMaster.OpenRepairMode((Player) event.getWhoClicked());
                            break;
                        }
                        case 3: {
                            String Name = "IRON_INGOT";
                            GuiMaster.ChangeScrap(Name,(Player) event.getWhoClicked(),event.getClick(), EnchantmentManager.getNeedItems(Name),EnchantmentManager.getScrapRate(Name));
                            break;
                        }
                        case 4: {

                        }
                        case 5: {

                        }
                        case 6: {

                        }
                        case 7: {

                        }
                        case 12: {

                        }
                        case 13: {

                        }
                        case 14: {

                        }
                        case 15: {

                        }
                        case 16: {

                        }

                    }
                }
            }
            event.setCancelled(true);
            return;
        }

        if(!(event.getCurrentItem()==null)){
            if(event.getInventory().getType().equals(InventoryType.ANVIL)){

                if(!(event.getClick().equals(ClickType.LEFT))&&!(event.getInventory().getItem(0)==null)){
                    event.setCancelled(true);
                }

                if(event.getClickedInventory().getType().equals(InventoryType.ANVIL)){
                    if(event.getSlot()==1){
                        event.setCancelled(true);
                    }

                    if(event.getSlot()==2){
                        event.setCancelled(false);
                    }
                    if(event.getSlot()==0){
                        event.setCancelled(false);
                    }

                    if(!(event.getInventory().getItem(1)==null)){
                        event.getInventory().clear(1);
                    }
                }

            }
        }
    }
}
