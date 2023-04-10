package com.github.majisyou.plusenchantment.Event;

import com.github.majisyou.plusenchantment.Config.EnchantmentManager;
import com.github.majisyou.plusenchantment.Config.ItemConfig;
import com.github.majisyou.plusenchantment.Gui.GuiItem;
import com.github.majisyou.plusenchantment.Gui.GuiMaster;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import com.github.majisyou.plusenchantment.System.CustomEnchantManager;
import com.github.majisyou.plusenchantment.System.EnchantSystem;
import com.github.majisyou.plusenchantment.System.ItemBuilder;
import com.github.majisyou.plusenchantment.System.SoundSystem;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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

import java.util.Objects;

public class Ev_inventoryClick implements Listener {
    private static final PlusEnchantment plugin = PlusEnchantment.getInstance();
    public Ev_inventoryClick(PlusEnchantment plugin){ //mainメソッド内でイベントリスナを呼び出すためのコマンド
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public static void InventoryClick(InventoryClickEvent event){
        if(event.getView().getTitle().equals("エンチャント合成モード")){
            if(!(event.getClick().equals(ClickType.LEFT)||event.getClick().equals(ClickType.SHIFT_LEFT))){
                event.setCancelled(true);
                return;
            }
            ItemStack clickedItem = event.getCurrentItem();
            if(clickedItem == null){
                return;
            }
            if(clickedItem.getType().equals(Material.AIR)){
                return;
            }
            if(event.getClick().equals(ClickType.LEFT)){
                if(event.getClickedInventory().getType().equals(InventoryType.PLAYER)){
                    return;
                }
                Inventory inventory = event.getClickedInventory();
                ItemStack cursor = event.getCursor();

                if(clickedItem.getType().equals(Material.getMaterial(ItemConfig.getItemType("BackGround")))){
                    if(clickedItem.getItemMeta().getCustomModelData() == ItemConfig.getCustomModelData("BackGround")){
                        event.setCancelled(true);
                        SoundSystem.FailedSound((Player) event.getWhoClicked());
                        return;
                    }
                }
                if(event.getSlot() == 3){
                    if(cursor == null || cursor.getType().equals(Material.AIR)){
                        if(EnchantSystem.EnchantItemJudge(clickedItem)){
                            event.setCursor(GuiItem.BaseEmpty());
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                            GuiMaster.addEnchantInventory(inventory,event.getCursor(),inventory.getItem(5),(Player) event.getWhoClicked());
                            return;
                        }
                        event.setCancelled(true);
                        GuiMaster.addEnchantInventory(inventory,inventory.getItem(3),inventory.getItem(5),(Player) event.getWhoClicked());
                        SoundSystem.FailedSound((Player) event.getWhoClicked());
                        return;
                    }
                    if(EnchantSystem.EnchantItemJudge(cursor)){
                        if(clickedItem.getType().equals(Material.getMaterial(ItemConfig.getItemType("BaseEmpty")))){
                            inventory.setItem(3,null);
                            GuiMaster.addEnchantInventory(inventory,cursor,inventory.getItem(5),(Player) event.getWhoClicked());
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                            return;
                        }
                        GuiMaster.addEnchantInventory(inventory,cursor,inventory.getItem(5),(Player) event.getWhoClicked());
                        SoundSystem.ClickSound((Player) event.getWhoClicked());
                        return;
                    }
                    GuiMaster.addEnchantInventory(inventory,inventory.getItem(3),inventory.getItem(5),(Player) event.getWhoClicked());
                    SoundSystem.FailedSound((Player) event.getWhoClicked());
                    event.setCancelled(true);
                    return;
                }

                if(event.getSlot() == 5){
                    if(cursor == null || cursor.getType().equals(Material.AIR)){
                        if(EnchantSystem.EnchantItemJudge(clickedItem)){
                            event.setCursor(GuiItem.AddEmpty());
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                            GuiMaster.addEnchantInventory(inventory,inventory.getItem(3),event.getCursor(),(Player) event.getWhoClicked());
                            return;
                        }
                        event.setCancelled(true);
                        GuiMaster.addEnchantInventory(inventory,inventory.getItem(3),inventory.getItem(5),(Player) event.getWhoClicked());
                        SoundSystem.FailedSound((Player) event.getWhoClicked());
                        return;
                    }
                    if(EnchantSystem.EnchantItemJudge(cursor)){
                        if(clickedItem.getType().equals(Material.getMaterial(ItemConfig.getItemType("AddEmpty")))){
                            inventory.setItem(5,null);
                            GuiMaster.addEnchantInventory(inventory,inventory.getItem(3),cursor,(Player) event.getWhoClicked());
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                            return;
                        }
                        GuiMaster.addEnchantInventory(inventory,inventory.getItem(3),cursor,(Player) event.getWhoClicked());
                        SoundSystem.ClickSound((Player) event.getWhoClicked());
                        return;
                    }
                    GuiMaster.addEnchantInventory(inventory,inventory.getItem(3),inventory.getItem(5),(Player) event.getWhoClicked());
                    SoundSystem.FailedSound((Player) event.getWhoClicked());
                    event.setCancelled(true);
                    return;
                }

                if(event.getSlot() == 7){
                    if(cursor != null){
                        if(!cursor.getType().equals(Material.AIR)){
                            event.setCancelled(true);
                            SoundSystem.FailedSound((Player) event.getWhoClicked());
                            return;
                        }
                    }

                    if(EnchantSystem.EnchantItemJudge(clickedItem)){
                        int needExp = EnchantSystem.cal_ExpCostAddItem(clickedItem);
                        int plExp = ((Player) event.getWhoClicked()).getLevel();
                        if(needExp > plExp){
                            event.setCancelled(true);
                            SoundSystem.FailedSound((Player) event.getWhoClicked());
                            return;
                        }

                        event.setCursor(GuiItem.Failed());
                        inventory.setItem(3,GuiItem.BaseEmpty());
                        inventory.setItem(5,GuiItem.AddEmpty());
                        GuiMaster.NeedEXPInventory(0,inventory,(Player) event.getWhoClicked());
                        ((Player) event.getWhoClicked()).setLevel(plExp - needExp);
                        SoundSystem.SuccessSound((Player) event.getWhoClicked());
                        return;
                    }
                    event.setCancelled(true);
                    SoundSystem.FailedSound((Player) event.getWhoClicked());
                    return;
                }

                event.setCancelled(true);
                SoundSystem.FailedSound((Player) event.getWhoClicked());
                return;
            }
            if(event.getClick().equals(ClickType.SHIFT_LEFT)){
                event.setCancelled(true);
                Inventory inventory = event.getClickedInventory();
                if(!EnchantSystem.EnchantItemJudge(clickedItem)){
                    SoundSystem.FailedSound((Player) event.getWhoClicked());
                    return;
                }
                if(inventory.getType().equals(InventoryType.PLAYER)){
                    Inventory guiInventory = event.getInventory();
                    if(!EnchantSystem.EnchantItemJudge(Objects.requireNonNull(Objects.requireNonNull(guiInventory).getItem(3)))){
                        guiInventory.setItem(3,clickedItem);
                        inventory.setItem(event.getSlot(),null);
                        SoundSystem.ClickSound((Player) event.getWhoClicked());
                        GuiMaster.addEnchantInventory(guiInventory,guiInventory.getItem(3),guiInventory.getItem(5),(Player) event.getWhoClicked());
                        return;
                    }
                    if(!EnchantSystem.EnchantItemJudge(Objects.requireNonNull(Objects.requireNonNull(guiInventory).getItem(5)))){
                        guiInventory.setItem(5,clickedItem);
                        inventory.setItem(event.getSlot(),null);
                        SoundSystem.ClickSound((Player) event.getWhoClicked());
                        GuiMaster.addEnchantInventory(guiInventory,guiInventory.getItem(3),guiInventory.getItem(5),(Player) event.getWhoClicked());
                        return;
                    }

                    SoundSystem.FailedSound((Player) event.getWhoClicked());
                    GuiMaster.addEnchantInventory(guiInventory,guiInventory.getItem(3),guiInventory.getItem(5),(Player) event.getWhoClicked());
                    return;
                }

                if(event.getSlot() == 3) {
                    if (EnchantSystem.EnchantItemJudge(clickedItem)) {
                        inventory.setItem(3, GuiItem.BaseEmpty());
                        SoundSystem.ClickSound((Player) event.getWhoClicked());
                        EnchantSystem.addItemToInventory(clickedItem, (Player) event.getWhoClicked());
                        GuiMaster.addEnchantInventory(inventory,inventory.getItem(3),inventory.getItem(5),(Player) event.getWhoClicked());
                        return;
                    }
                }

                if(event.getSlot() == 5){
                    if(EnchantSystem.EnchantItemJudge(clickedItem)){
                        inventory.setItem(5,GuiItem.AddEmpty());
                        SoundSystem.ClickSound((Player) event.getWhoClicked());
                        EnchantSystem.addItemToInventory(clickedItem,(Player) event.getWhoClicked());
                        GuiMaster.addEnchantInventory(inventory,inventory.getItem(3),inventory.getItem(5),(Player) event.getWhoClicked());
                        return;
                    }
                }

                if(event.getSlot() == 7){
                    if(EnchantSystem.EnchantItemJudge(clickedItem)){
                        int needExp = EnchantSystem.cal_ExpCostAddItem(clickedItem);
                        int plExp = ((Player) event.getWhoClicked()).getLevel();
                        if(needExp > plExp){
                            SoundSystem.FailedSound((Player) event.getWhoClicked());
                            return;
                        }

                        inventory.setItem(3,GuiItem.BaseEmpty());
                        inventory.setItem(5,GuiItem.AddEmpty());
                        EnchantSystem.addItemToInventory(clickedItem,(Player) event.getWhoClicked());
                        GuiMaster.addEnchantInventory(inventory,inventory.getItem(3),inventory.getItem(5),(Player) event.getWhoClicked());
                        ((Player) event.getWhoClicked()).setLevel(plExp - needExp);
                        SoundSystem.SuccessSound((Player) event.getWhoClicked());
                        return;
                    }

                    GuiMaster.addEnchantInventory(inventory,inventory.getItem(3),inventory.getItem(5),(Player) event.getWhoClicked());
                    SoundSystem.FailedSound((Player) event.getWhoClicked());
                    return;
                }

                GuiMaster.addEnchantInventory(inventory,inventory.getItem(3),inventory.getItem(5),(Player) event.getWhoClicked());
                SoundSystem.FailedSound((Player) event.getWhoClicked());
                return;

            }
        }

        if(event.getView().getTitle().equals("エンチャント消去モード")){
            if(event.getClick().equals(ClickType.DOUBLE_CLICK)){
                event.setCancelled(true);
                return;
            }

            ItemStack clickedItem = event.getCurrentItem();
            if(clickedItem == null){
                return;
            }


            if(!(event.getClickedInventory().getType().equals(InventoryType.PLAYER))){
                if(event.getSlot()==9){
                    event.setCancelled(true);
                    event.getWhoClicked().openInventory(GuiMaster.getRepairModeGui());
                    SoundSystem.OpenSound((Player) event.getWhoClicked());
                    return;
                }
                if(event.getSlot()==18){
                    event.setCancelled(true);
                    event.getWhoClicked().openInventory(GuiMaster.getScrapModeGui((event.getWhoClicked().getInventory())));
                    SoundSystem.OpenSound((Player) event.getWhoClicked());
                    return;
                }
            }
            if(!(event.getClick().equals(ClickType.LEFT)||event.getClick().equals(ClickType.SHIFT_LEFT))){
                event.setCancelled(true);
                return;
            }
            if(clickedItem.getType().equals(Material.AIR)){
                return;
            }
            if(event.getClick().equals(ClickType.LEFT)){
                if(event.getClickedInventory().getType().equals(InventoryType.PLAYER)){
                    return;
                }
                Inventory inventory = event.getClickedInventory();
                ItemStack cursor = event.getCursor();

                if(clickedItem.getType().equals(Material.getMaterial(ItemConfig.getItemType("BackGround")))){
                    if(clickedItem.getItemMeta().getCustomModelData() == ItemConfig.getCustomModelData("BackGround")){
                        event.setCancelled(true);
                        SoundSystem.FailedSound((Player) event.getWhoClicked());
                        return;
                    }
                }
                if(event.getSlot() == 3){
                    if(cursor == null || cursor.getType().equals(Material.AIR)){
                        if(EnchantSystem.EnchantItemJudge(clickedItem)){
                            event.setCursor(GuiItem.BaseEmpty());
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                            GuiMaster.removeEnchantInventory(inventory,event.getCursor(),inventory.getItem(5),(Player) event.getWhoClicked());
                            return;
                        }
                        event.setCancelled(true);
                        GuiMaster.removeEnchantInventory(inventory,inventory.getItem(3),inventory.getItem(5),(Player) event.getWhoClicked());
                        SoundSystem.FailedSound((Player) event.getWhoClicked());
                        return;
                    }
                    if(EnchantSystem.EnchantItemJudge(cursor)){
                        if(!cursor.hasItemMeta()){
                            event.setCancelled(true);
                            return;
                        }
                        if(!cursor.getType().equals(Material.ENCHANTED_BOOK) && !cursor.getItemMeta().hasEnchants()){
                            event.setCancelled(true);
                            return;
                        }
                        if(clickedItem.getType().equals(Material.getMaterial(ItemConfig.getItemType("BaseEmpty")))){
                            inventory.setItem(3,null);
                            GuiMaster.removeEnchantInventory(inventory,cursor,inventory.getItem(5),(Player) event.getWhoClicked());
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                            return;
                        }
                        GuiMaster.removeEnchantInventory(inventory,cursor,inventory.getItem(5),(Player) event.getWhoClicked());
                        SoundSystem.ClickSound((Player) event.getWhoClicked());
                        return;
                    }
                    GuiMaster.removeEnchantInventory(inventory,inventory.getItem(3),inventory.getItem(5),(Player) event.getWhoClicked());
                    SoundSystem.FailedSound((Player) event.getWhoClicked());
                    event.setCancelled(true);
                    return;
                }
                if(event.getSlot() == 5){
                    if(cursor == null || cursor.getType().equals(Material.AIR)){
                        if(EnchantSystem.EnchantItemJudge(clickedItem)){
                            event.setCursor(GuiItem.RemoveEnchantEmpty());
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                            GuiMaster.removeEnchantInventory(inventory,inventory.getItem(3),event.getCursor(),(Player) event.getWhoClicked());
                            return;
                        }
                        event.setCancelled(true);
                        GuiMaster.removeEnchantInventory(inventory,inventory.getItem(3),inventory.getItem(5),(Player) event.getWhoClicked());
                        SoundSystem.FailedSound((Player) event.getWhoClicked());
                        return;
                    }
                    if(EnchantSystem.EnchantItemJudge(cursor)){
                        if(cursor.getType().equals(Material.ENCHANTED_BOOK)){
                            if(clickedItem.getType().equals(Material.getMaterial(ItemConfig.getItemType("RemoveEnchantEmpty")))){
                                inventory.setItem(5,null);
                                GuiMaster.removeEnchantInventory(inventory,inventory.getItem(3),cursor,(Player) event.getWhoClicked());
                                SoundSystem.ClickSound((Player) event.getWhoClicked());
                                return;
                            }
                            GuiMaster.removeEnchantInventory(inventory,inventory.getItem(3),cursor,(Player) event.getWhoClicked());
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                            return;
                        }
                    }
                    GuiMaster.removeEnchantInventory(inventory,inventory.getItem(3),inventory.getItem(5),(Player) event.getWhoClicked());
                    SoundSystem.FailedSound((Player) event.getWhoClicked());
                    event.setCancelled(true);
                    return;
                }
                if(event.getSlot() == 7){
                    if(cursor != null){
                        if(!cursor.getType().equals(Material.AIR)){
                            event.setCancelled(true);
                            SoundSystem.FailedSound((Player) event.getWhoClicked());
                            return;
                        }
                    }
                    if(EnchantSystem.EnchantItemJudge(clickedItem)){
                        int needExp = 1;
                        int plExp = ((Player) event.getWhoClicked()).getLevel();
                        if(needExp > plExp){
                            event.setCancelled(true);
                            SoundSystem.FailedSound((Player) event.getWhoClicked());
                            return;
                        }

                        event.setCursor(GuiItem.Failed());
                        inventory.setItem(3,GuiItem.BaseEmpty());
                        inventory.setItem(5,GuiItem.RemoveEnchantEmpty());
                        GuiMaster.NeedEXPInventory(0,inventory,(Player) event.getWhoClicked());
                        ((Player) event.getWhoClicked()).setLevel(plExp - needExp);
                        SoundSystem.SuccessSound((Player) event.getWhoClicked());
                        return;
                    }
                    event.setCancelled(true);
                    SoundSystem.FailedSound((Player) event.getWhoClicked());
                    return;
                }

                event.setCancelled(true);
                SoundSystem.FailedSound((Player) event.getWhoClicked());
                return;
            }
            if(event.getClick().equals(ClickType.SHIFT_LEFT)){
                event.setCancelled(true);
                Inventory inventory = event.getClickedInventory();
                if(!EnchantSystem.EnchantItemJudge(clickedItem)){
                    SoundSystem.FailedSound((Player) event.getWhoClicked());
                    return;
                }
                if(inventory.getType().equals(InventoryType.PLAYER)){
                    Inventory guiInventory = event.getInventory();
                    if(!clickedItem.hasItemMeta()){
                        event.setCancelled(true);
                        return;
                    }
                    if(!clickedItem.getType().equals(Material.ENCHANTED_BOOK) && !clickedItem.getItemMeta().hasEnchants()){
                        event.setCancelled(true);
                        return;
                    }
                    if(!EnchantSystem.EnchantItemJudge(Objects.requireNonNull(Objects.requireNonNull(guiInventory).getItem(3)))){
                        guiInventory.setItem(3,clickedItem);
                        inventory.setItem(event.getSlot(),null);
                        SoundSystem.ClickSound((Player) event.getWhoClicked());
                        GuiMaster.removeEnchantInventory(guiInventory,guiInventory.getItem(3),guiInventory.getItem(5),(Player) event.getWhoClicked());
                        return;
                    }
                    if(!EnchantSystem.EnchantItemJudge(Objects.requireNonNull(Objects.requireNonNull(guiInventory).getItem(5)))){
                        if(clickedItem.getType().equals(Material.ENCHANTED_BOOK)){
                            guiInventory.setItem(5,clickedItem);
                            inventory.setItem(event.getSlot(),null);
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                            GuiMaster.removeEnchantInventory(guiInventory,guiInventory.getItem(3),guiInventory.getItem(5),(Player) event.getWhoClicked());
                            return;
                        }
                    }

                    SoundSystem.FailedSound((Player) event.getWhoClicked());
                    GuiMaster.removeEnchantInventory(guiInventory,guiInventory.getItem(3),guiInventory.getItem(5),(Player) event.getWhoClicked());
                    return;
                }

                if(event.getSlot() == 3) {
                    if (EnchantSystem.EnchantItemJudge(clickedItem)) {
                        inventory.setItem(3, GuiItem.BaseEmpty());
                        SoundSystem.ClickSound((Player) event.getWhoClicked());
                        EnchantSystem.addItemToInventory(clickedItem, (Player) event.getWhoClicked());
                        GuiMaster.removeEnchantInventory(inventory,inventory.getItem(3),inventory.getItem(5),(Player) event.getWhoClicked());
                        return;
                    }
                }

                if(event.getSlot() == 5){
                    if(EnchantSystem.EnchantItemJudge(clickedItem)){
                        inventory.setItem(5,GuiItem.RemoveEnchantEmpty());
                        SoundSystem.ClickSound((Player) event.getWhoClicked());
                        EnchantSystem.addItemToInventory(clickedItem,(Player) event.getWhoClicked());
                        GuiMaster.removeEnchantInventory(inventory,inventory.getItem(3),inventory.getItem(5),(Player) event.getWhoClicked());
                        return;
                    }
                }

                if(event.getSlot() == 7){
                    if(EnchantSystem.EnchantItemJudge(clickedItem)){
                        int needExp = 1;
                        int plExp = ((Player) event.getWhoClicked()).getLevel();
                        if(needExp > plExp){
                            SoundSystem.FailedSound((Player) event.getWhoClicked());
                            return;
                        }

                        inventory.setItem(3,GuiItem.BaseEmpty());
                        inventory.setItem(5,GuiItem.RemoveEnchantEmpty());
                        EnchantSystem.addItemToInventory(clickedItem,(Player) event.getWhoClicked());
                        GuiMaster.removeEnchantInventory(inventory,inventory.getItem(3),inventory.getItem(5),(Player) event.getWhoClicked());
                        ((Player) event.getWhoClicked()).setLevel(plExp - needExp);
                        SoundSystem.SuccessSound((Player) event.getWhoClicked());
                        return;
                    }

                    GuiMaster.removeEnchantInventory(inventory,inventory.getItem(3),inventory.getItem(5),(Player) event.getWhoClicked());
                    SoundSystem.FailedSound((Player) event.getWhoClicked());
                    return;
                }

                GuiMaster.removeEnchantInventory(inventory,inventory.getItem(3),inventory.getItem(5),(Player) event.getWhoClicked());
                SoundSystem.FailedSound((Player) event.getWhoClicked());
                return;
            }
        }

        if(event.getView().getTitle().equals("-修理モード-")){
            if(event.getClick().equals(ClickType.DOUBLE_CLICK)){
                event.setCancelled(true);
                return;
            }

            ItemStack clickedItem = event.getCurrentItem();
            if(clickedItem == null){
                return;
            }

            if(!(event.getClickedInventory().getType().equals(InventoryType.PLAYER))){
                if(event.getSlot()==0){
                    event.setCancelled(true);
                    event.getWhoClicked().openInventory(GuiMaster.getGrindstoneGui());
                    SoundSystem.OpenSound((Player) event.getWhoClicked());
                    return;
                }
                if(event.getSlot()==18){
                    event.setCancelled(true);
                    event.getWhoClicked().openInventory(GuiMaster.getScrapModeGui((event.getWhoClicked().getInventory())));
                    SoundSystem.OpenSound((Player) event.getWhoClicked());
                    return;
                }
            }
            if(!(event.getClick().equals(ClickType.LEFT)||event.getClick().equals(ClickType.SHIFT_LEFT))){
                event.setCancelled(true);
                return;
            }

            if(clickedItem.getType().equals(Material.AIR)){
                return;
            }
            if(event.getClick().equals(ClickType.LEFT)){
                if(event.getClickedInventory().getType().equals(InventoryType.PLAYER)){
                    return;
                }
                Inventory inventory = event.getClickedInventory();
                ItemStack cursor = event.getCursor();

                if(clickedItem.getType().equals(Material.getMaterial(ItemConfig.getItemType("BackGround")))){
                    if(clickedItem.getItemMeta().getCustomModelData() == ItemConfig.getCustomModelData("BackGround")){
                        event.setCancelled(true);
                        SoundSystem.FailedSound((Player) event.getWhoClicked());
                        return;
                    }
                }
                if(event.getSlot() == 3){
                    if(cursor == null || cursor.getType().equals(Material.AIR)){
                        if(EnchantSystem.EnchantItemJudge(clickedItem) || clickedItem.getType().equals(Material.CHAIN_COMMAND_BLOCK)){
                            event.setCursor(GuiItem.BaseEmpty());
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                            GuiMaster.repairItemInventory(inventory,event.getCursor(),inventory.getItem(5).getType().equals(Material.getMaterial(ItemConfig.getItemType("Scrap"))) ? inventory.getItem(5).getAmount():0,(Player) event.getWhoClicked());
                            return;
                        }
                        event.setCancelled(true);
                        GuiMaster.repairItemInventory(inventory,inventory.getItem(3),inventory.getItem(5).getType().equals(Material.getMaterial(ItemConfig.getItemType("Scrap"))) ? inventory.getItem(5).getAmount():0,(Player) event.getWhoClicked());
                        SoundSystem.FailedSound((Player) event.getWhoClicked());
                        return;
                    }
                    if(EnchantSystem.EnchantItemJudge(cursor) || cursor.getType().equals(Material.CHAIN_COMMAND_BLOCK)){
                        if(clickedItem.getType().equals(Material.getMaterial(ItemConfig.getItemType("BaseEmpty")))){
                            inventory.setItem(3,null);
                            GuiMaster.repairItemInventory(inventory,cursor,inventory.getItem(5).getType().equals(Material.getMaterial(ItemConfig.getItemType("Scrap"))) ? inventory.getItem(5).getAmount():0,(Player) event.getWhoClicked());
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                            return;
                        }
                        GuiMaster.repairItemInventory(inventory,cursor,inventory.getItem(5).getType().equals(Material.getMaterial(ItemConfig.getItemType("Scrap"))) ? inventory.getItem(5).getAmount():0,(Player) event.getWhoClicked());
                        SoundSystem.ClickSound((Player) event.getWhoClicked());
                        return;
                    }
                    GuiMaster.repairItemInventory(inventory,inventory.getItem(3),inventory.getItem(5).getType().equals(Material.getMaterial(ItemConfig.getItemType("Scrap"))) ? inventory.getItem(5).getAmount():0,(Player) event.getWhoClicked());
                    SoundSystem.FailedSound((Player) event.getWhoClicked());
                    event.setCancelled(true);
                    return;
                }

                if(event.getSlot() == 5){
                    if(cursor == null || cursor.getType().equals(Material.AIR)){
                        if(clickedItem.getType().equals(Material.getMaterial(ItemConfig.getItemType("Scrap")))){
                            if(clickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"スクラップ")){
                                event.setCursor(GuiItem.ScrapEmpty());
                                SoundSystem.ClickSound((Player) event.getWhoClicked());
                                GuiMaster.repairItemInventory(inventory,inventory.getItem(3),0,(Player) event.getWhoClicked());
                                return;
                            }
                        }
                        event.setCancelled(true);
                        GuiMaster.repairItemInventory(inventory,inventory.getItem(3),inventory.getItem(5).getType().equals(Material.getMaterial(ItemConfig.getItemType("Scrap"))) ? inventory.getItem(5).getAmount():0,(Player) event.getWhoClicked());
                        SoundSystem.FailedSound((Player) event.getWhoClicked());
                        return;
                    }
                    if(EnchantSystem.EnchantItemJudge(cursor) || cursor.getType().equals(Material.COMMAND_BLOCK)){
                        if(cursor.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"スクラップ")){
                            if(clickedItem.getType().equals(Material.getMaterial(ItemConfig.getItemType("ScrapEmpty")))){
                                inventory.setItem(5,null);
                                GuiMaster.repairItemInventory(inventory,inventory.getItem(3),cursor.getAmount(),(Player) event.getWhoClicked());
                                SoundSystem.ClickSound((Player) event.getWhoClicked());
                                return;
                            }
                            GuiMaster.repairItemInventory(inventory,inventory.getItem(3),Math.min(clickedItem.getAmount()+cursor.getAmount(),64),(Player) event.getWhoClicked());
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                            return;
                        }
                    }
                    GuiMaster.repairItemInventory(inventory,inventory.getItem(3),inventory.getItem(5).getType().equals(Material.getMaterial(ItemConfig.getItemType("Scrap"))) ? inventory.getItem(5).getAmount():0,(Player) event.getWhoClicked());
                    SoundSystem.FailedSound((Player) event.getWhoClicked());
                    event.setCancelled(true);
                    return;
                }

                if(event.getSlot() == 7){
                    if(cursor != null){
                        if(!cursor.getType().equals(Material.AIR)){
                            event.setCancelled(true);
                            SoundSystem.FailedSound((Player) event.getWhoClicked());
                            return;
                        }
                    }

                    if(EnchantSystem.EnchantItemJudge(clickedItem)){
                        int needExp;
                        if(inventory.getItem(3).getType().equals(Material.CHAIN_COMMAND_BLOCK)){
                            needExp = EnchantSystem.cal_repairItem(ItemBuilder.returnTool(inventory.getItem(3)),inventory.getItem(5).getAmount());
                        }else {
                            needExp = EnchantSystem.cal_repairItem(inventory.getItem(3),inventory.getItem(5).getAmount());
                        }
                        int plExp = ((Player) event.getWhoClicked()).getLevel();
                        if(needExp > plExp){
                            event.setCancelled(true);
                            SoundSystem.FailedSound((Player) event.getWhoClicked());
                            return;
                        }
                        event.setCursor(GuiItem.Failed());
                        int needScrapAmount;
                        if(inventory.getItem(3).getType().equals(Material.CHAIN_COMMAND_BLOCK)){
                            needScrapAmount = EnchantSystem.needScrapAmount(ItemBuilder.returnTool(inventory.getItem(3)),inventory.getItem(5).getAmount());
                        }else {
                            needScrapAmount = EnchantSystem.needScrapAmount(inventory.getItem(3),inventory.getItem(5).getAmount());
                        }
                        if(needScrapAmount == inventory.getItem(5).getAmount()){
                            inventory.setItem(5,GuiItem.ScrapEmpty());
                        }else {
                            inventory.getItem(5).setAmount(inventory.getItem(5).getAmount()-needScrapAmount);
                        }
                        inventory.setItem(3,GuiItem.BaseEmpty());
                        GuiMaster.NeedEXPInventory(0,inventory,(Player) event.getWhoClicked());
                        ((Player) event.getWhoClicked()).setLevel(plExp - needExp);
                        SoundSystem.SuccessSound((Player) event.getWhoClicked());
                        return;
                    }
                    event.setCancelled(true);
                    SoundSystem.FailedSound((Player) event.getWhoClicked());
                    return;
                }

                event.setCancelled(true);
                SoundSystem.FailedSound((Player) event.getWhoClicked());
                return;
            }
            if(event.getClick().equals(ClickType.SHIFT_LEFT)){
                event.setCancelled(true);
                Inventory inventory = event.getClickedInventory();
                if(!EnchantSystem.EnchantItemJudge(clickedItem) && !clickedItem.getType().equals(Material.getMaterial(ItemConfig.getItemType("Scrap"))) && !clickedItem.getType().equals(Material.CHAIN_COMMAND_BLOCK) && !clickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"スクラップ")){
                    SoundSystem.FailedSound((Player) event.getWhoClicked());
                    return;

                }
                if(inventory.getType().equals(InventoryType.PLAYER)){
                    Inventory guiInventory = event.getInventory();
                    if(!EnchantSystem.EnchantItemJudge(Objects.requireNonNull(Objects.requireNonNull(guiInventory).getItem(3))) && !guiInventory.getItem(3).getType().equals(Material.CHAIN_COMMAND_BLOCK)){
                        if(EnchantSystem.EnchantItemJudge(clickedItem) || clickedItem.getType().equals(Material.CHAIN_COMMAND_BLOCK)){
                            guiInventory.setItem(3,clickedItem);
                            inventory.setItem(event.getSlot(),null);
                            SoundSystem.ClickSound((Player) event.getWhoClicked());
                            GuiMaster.repairItemInventory(guiInventory,guiInventory.getItem(3),guiInventory.getItem(5).getType().equals(Material.getMaterial(ItemConfig.getItemType("Scrap"))) ? guiInventory.getItem(5).getAmount():0,(Player) event.getWhoClicked());
                            return;
                        }
                    }
                    if(!guiInventory.getItem(5).getType().equals(Material.getMaterial(ItemConfig.getItemType("Scrap")))){
                        if(clickedItem.getType().equals(Material.getMaterial(ItemConfig.getItemType("Scrap")))){
                            if(clickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"スクラップ")){
                                guiInventory.setItem(5,clickedItem);
                                inventory.setItem(event.getSlot(),null);
                                SoundSystem.ClickSound((Player) event.getWhoClicked());
                                GuiMaster.repairItemInventory(guiInventory,guiInventory.getItem(3),guiInventory.getItem(5).getType().equals(Material.getMaterial(ItemConfig.getItemType("Scrap"))) ? guiInventory.getItem(5).getAmount():0,(Player) event.getWhoClicked());
                                return;
                            }
                        }
                    }

                    SoundSystem.FailedSound((Player) event.getWhoClicked());
                    GuiMaster.repairItemInventory(guiInventory,guiInventory.getItem(3),guiInventory.getItem(5).getType().equals(Material.getMaterial(ItemConfig.getItemType("Scrap"))) ? guiInventory.getItem(5).getAmount():0,(Player) event.getWhoClicked());
                    return;
                }

                if(event.getSlot() == 3) {
                    if (EnchantSystem.EnchantItemJudge(clickedItem) || clickedItem.getType().equals(Material.CHAIN_COMMAND_BLOCK)) {
                        inventory.setItem(3, GuiItem.BaseEmpty());
                        SoundSystem.ClickSound((Player) event.getWhoClicked());
                        EnchantSystem.addItemToInventory(clickedItem, (Player) event.getWhoClicked());
                        GuiMaster.repairItemInventory(inventory,inventory.getItem(3),inventory.getItem(5).getType().equals(Material.getMaterial(ItemConfig.getItemType("Scrap"))) ? inventory.getItem(5).getAmount():0,(Player) event.getWhoClicked());
                        return;
                    }
                }

                if(event.getSlot() == 5){
                    if(clickedItem.getType().equals(Material.getMaterial(ItemConfig.getItemType("Scrap"))) && clickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"スクラップ")){
                        inventory.setItem(5,GuiItem.ScrapEmpty());
                        SoundSystem.ClickSound((Player) event.getWhoClicked());
                        EnchantSystem.addItemToInventory(clickedItem,(Player) event.getWhoClicked());
                        GuiMaster.repairItemInventory(inventory,inventory.getItem(3),inventory.getItem(5).getType().equals(Material.getMaterial(ItemConfig.getItemType("Scrap"))) ? inventory.getItem(5).getAmount():0,(Player) event.getWhoClicked());
                        return;
                    }
                }

                if(event.getSlot() == 7){
                    if(EnchantSystem.EnchantItemJudge(clickedItem)){
                        int needExp;
                        if(inventory.getItem(3).getType().equals(Material.CHAIN_COMMAND_BLOCK)){
                            needExp = EnchantSystem.cal_repairItem(ItemBuilder.returnTool(inventory.getItem(3)),inventory.getItem(5).getAmount());
                        }else {
                            needExp = EnchantSystem.cal_repairItem(inventory.getItem(3),inventory.getItem(5).getAmount());
                        }
                        int plExp = ((Player) event.getWhoClicked()).getLevel();
                        if(needExp > plExp){
                            SoundSystem.FailedSound((Player) event.getWhoClicked());
                            return;
                        }
                        int needScrapAmount;
                        if(inventory.getItem(3).getType().equals(Material.CHAIN_COMMAND_BLOCK)){
                            needScrapAmount = EnchantSystem.needScrapAmount(ItemBuilder.returnTool(inventory.getItem(3)),inventory.getItem(5).getAmount());
                        }else {
                            needScrapAmount = EnchantSystem.needScrapAmount(inventory.getItem(3),inventory.getItem(5).getAmount());
                        }
                        if(needScrapAmount == inventory.getItem(5).getAmount()){
                            inventory.setItem(5,GuiItem.ScrapEmpty());
                        }else {
                            inventory.getItem(5).setAmount(inventory.getItem(5).getAmount()-needScrapAmount);
                        }
                        inventory.setItem(3,GuiItem.BaseEmpty());
                        EnchantSystem.addItemToInventory(clickedItem,(Player) event.getWhoClicked());
                        GuiMaster.repairItemInventory(inventory,inventory.getItem(3),inventory.getItem(5).getType().equals(Material.getMaterial(ItemConfig.getItemType("Scrap"))) ? inventory.getItem(5).getAmount():0,(Player) event.getWhoClicked());
                        ((Player) event.getWhoClicked()).setLevel(plExp - needExp);
                        SoundSystem.SuccessSound((Player) event.getWhoClicked());
                        return;
                    }

                    GuiMaster.repairItemInventory(inventory,inventory.getItem(3),inventory.getItem(5).getType().equals(Material.getMaterial(ItemConfig.getItemType("Scrap"))) ? inventory.getItem(5).getAmount():0,(Player) event.getWhoClicked());
                    SoundSystem.FailedSound((Player) event.getWhoClicked());
                    return;
                }

                GuiMaster.removeEnchantInventory(inventory,inventory.getItem(3),inventory.getItem(5),(Player) event.getWhoClicked());
                SoundSystem.FailedSound((Player) event.getWhoClicked());
                return;
            }

        }

        if(event.getView().getTitle().equals("スクラップモード")){
            if(!(event.getCurrentItem() == null)){
                if(!(event.getClickedInventory().getType().equals(InventoryType.PLAYER))) {
                    switch (event.getSlot()) {
                        case 0 -> {
                            event.setCancelled(true);
                            event.getWhoClicked().openInventory(GuiMaster.getGrindstoneGui());
                            SoundSystem.OpenSound((Player) event.getWhoClicked());
                            return;
                        }
                        case 9 -> {
                            event.setCancelled(true);
                            event.getWhoClicked().openInventory(GuiMaster.getRepairModeGui());
                            SoundSystem.OpenSound((Player) event.getWhoClicked());
                            return;

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

//        if(event.getView().getTitle().equals("カスタムエンチャントテーブル")){
//            ItemStack clickedItem = event.getCurrentItem();
//            if(clickedItem == null){
//                return;
//            }
//
//            if(!(event.getClickedInventory().getType().equals(InventoryType.PLAYER))){
//                if(event.getSlot()==9){
//                    event.setCancelled(true);
//                    Location loc = GuiMaster.getLoc(event.getInventory().getItem(0));
//                    event.getWhoClicked().openInventory(GuiMaster.getSoulModeGui((event.getWhoClicked().getInventory()),loc));
//                    SoundSystem.OpenSound((Player) event.getWhoClicked());
//                    return;
//                }
//            }
//
//            if(!(event.getClick().equals(ClickType.LEFT)||event.getClick().equals(ClickType.SHIFT_LEFT))){
//                event.setCancelled(true);
//                return;
//            }
//            if(clickedItem.getType().equals(Material.AIR)){
//                return;
//            }
//            if(event.getClick().equals(ClickType.LEFT)){
//                if(event.getClickedInventory().getType().equals(InventoryType.PLAYER)){
//                    return;
//                }
//                Inventory inventory = event.getClickedInventory();
//                if(event.getSlot() == 2 || event.getSlot() == 3 || event.getSlot() == 4){
//                    ItemStack cursor = event.getCursor();
//                    if(cursor == null || cursor.getType().equals(Material.AIR)){
//                        if(clickedItem.getType().equals(Material.getMaterial(ItemConfig.getItemType("SoulEmpty")))){
//                           event.setCancelled(true);
//                            GuiMaster.customEnchant(inventory,inventory.getItem(2),inventory.getItem(3),inventory.getItem(4),inventory.getItem(21),GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                            SoundSystem.FailedSound((Player) event.getWhoClicked());
//                           return;
//                        }
//                        if(event.getSlot() == 2){
//                            GuiMaster.customEnchant(inventory,cursor,inventory.getItem(3),inventory.getItem(4),inventory.getItem(21),GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                        }
//                        if(event.getSlot() == 3){
//                            GuiMaster.customEnchant(inventory,inventory.getItem(2),cursor,inventory.getItem(4),inventory.getItem(21),GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                        }
//                        if(event.getSlot() == 4){
//                            GuiMaster.customEnchant(inventory,inventory.getItem(2),inventory.getItem(3),cursor,inventory.getItem(21),GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                        }
//                        event.setCursor(GuiItem.SoulEmpty());
//                        SoundSystem.ClickSound((Player) event.getWhoClicked());
//                        return;
//                    }
//
//                    if(cursor.getType().equals(Material.getMaterial(ItemConfig.getItemType("Soul")))){
//                        if(clickedItem.getType().equals(Material.getMaterial(ItemConfig.getItemType("SoulEmpty")))){
//                            if(cursor.getAmount() > 1){
//                                event.setCancelled(true);
//                                ItemStack soul = cursor.clone();
//                                cursor.setAmount(cursor.getAmount()-1);
//                                event.setCursor(cursor);
//                                soul.setAmount(1);
//                                event.getClickedInventory().setItem(event.getSlot(),soul);
//                            }else {
//                                event.getClickedInventory().setItem(event.getSlot(),null);
//                            }
//                            if(event.getSlot() == 2){
//                                GuiMaster.customEnchant(inventory,cursor,inventory.getItem(3),inventory.getItem(4),inventory.getItem(21),GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                            }
//                            if(event.getSlot() == 3){
//                                GuiMaster.customEnchant(inventory,inventory.getItem(2),cursor,inventory.getItem(4),inventory.getItem(21),GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                            }
//                            if(event.getSlot() == 4){
//                                GuiMaster.customEnchant(inventory,inventory.getItem(2),inventory.getItem(3),cursor,inventory.getItem(21),GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                            }
//                            SoundSystem.ClickSound((Player) event.getWhoClicked());
//                            return;
//                        }
//                        event.setCancelled(true);
//                        SoundSystem.ClickSound((Player) event.getWhoClicked());
//                        return;
//                    }
//                }
//
//                if(event.getSlot() == 21){
//                    ItemStack cursor = event.getCursor();
//                    if(cursor == null || cursor.getType().equals(Material.AIR)){
//                        if(clickedItem.getType().equals(Material.BOOK)){
//                            event.setCursor(GuiItem.BaseEmpty());
//                            GuiMaster.customEnchant(inventory,inventory.getItem(2),inventory.getItem(3),inventory.getItem(4),cursor,GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                            SoundSystem.ClickSound((Player) event.getWhoClicked());
//                            return;
//                        }
//
//                        if(clickedItem.getType().equals(Material.ENCHANTED_BOOK)){
//                            event.setCursor(GuiItem.BaseEmpty());
//                            GuiMaster.customEnchant(inventory,inventory.getItem(2),inventory.getItem(3),inventory.getItem(4),cursor,GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                            SoundSystem.ClickSound((Player) event.getWhoClicked());
//                            return;
//                        }
//
//                        GuiMaster.customEnchant(inventory,inventory.getItem(2),inventory.getItem(3),inventory.getItem(4),cursor,GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                        event.setCancelled(true);
//                        SoundSystem.FailedSound((Player) event.getWhoClicked());
//                        return;
//                    }
//
//                    if(cursor.getType().equals(Material.BOOK)){
//                        if(!clickedItem.getType().equals(Material.getMaterial(ItemConfig.getItemType("BaseEmpty")))){
//                            GuiMaster.customEnchant(inventory,inventory.getItem(2),inventory.getItem(3),inventory.getItem(4),cursor,GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                            SoundSystem.ClickSound((Player) event.getWhoClicked());
//                            return;
//                        }
//
//                        GuiMaster.customEnchant(inventory,inventory.getItem(2),inventory.getItem(3),inventory.getItem(4),cursor,GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                        SoundSystem.ClickSound((Player) event.getWhoClicked());
//                        event.getClickedInventory().setItem(20,null);
//                        return;
//                    }
//                }
//
//                if(event.getSlot() == 8){
//                    event.setCancelled(true);
//                    if(!inventory.getItem(21).getType().equals(Material.BOOK)){
//                        GuiMaster.customEnchant(inventory,inventory.getItem(2),inventory.getItem(3),inventory.getItem(4),inventory.getItem(21),GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                        SoundSystem.FailedSound((Player) event.getWhoClicked());
//                        return;
//                    }
//
//                    int plLv = ((Player) event.getWhoClicked()).getLevel();
//                    int cost = CustomEnchantManager.calExpCost(GuiMaster.countSoul(inventory.getItem(2),inventory.getItem(3),inventory.getItem(4)),GuiMaster.get_Shelf(inventory.getItem(26)));
//
//                    if(cost == 0){
//                        SoundSystem.FailedSound((Player) event.getWhoClicked());
//                        return;
//                    }
//
//                    if(plLv < cost){
//                        GuiMaster.customEnchant(inventory,inventory.getItem(2),inventory.getItem(3),inventory.getItem(4),inventory.getItem(21),GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                        SoundSystem.FailedSound((Player) event.getWhoClicked());
//                        return;
//                    }
//
//                    GuiMaster.customEnchant(inventory,inventory.getItem(2),inventory.getItem(3),inventory.getItem(4),inventory.getItem(21),GuiMaster.get_Shelf(inventory.getItem(26)),true,(Player) event.getWhoClicked());
//                    inventory.setItem(2,GuiItem.SoulEmpty());
//                    inventory.setItem(3,GuiItem.SoulEmpty());
//                    inventory.setItem(4,GuiItem.SoulEmpty());
//                    SoundSystem.SuccessSound((Player) event.getWhoClicked());
//                    ((Player) event.getWhoClicked()).setLevel(plLv - cost);
//                    GuiMaster.customEnchant(inventory,inventory.getItem(2),inventory.getItem(3),inventory.getItem(4),inventory.getItem(21),GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                    return;
//                }
//
//
//
//                event.setCancelled(true);
//                SoundSystem.FailedSound((Player) event.getWhoClicked());
//                return;
//            }
//            if(event.getClick().equals(ClickType.SHIFT_LEFT)){
//                event.setCancelled(true);
//                ItemStack clickItem = event.getCurrentItem();
//
//                if(clickItem == null){return;}
//
//                if(Objects.requireNonNull(Objects.requireNonNull(event.getClickedInventory())).getType().equals(InventoryType.PLAYER)){
//                    if(clickItem.getType().equals(Material.getMaterial(ItemConfig.getItemType("Soul")))){
//                        if(clickItem.getItemMeta().getCustomModelData() == ItemConfig.getCustomModelData("Soul")){
//                            Inventory inventory = event.getInventory();
//                            Inventory clickediv = event.getClickedInventory();
//
//                            ItemStack empty = inventory.getItem(2);
//                            if(empty.getType().equals(Material.getMaterial(ItemConfig.getItemType("SoulEmpty")))){
//                                ItemStack soul = clickItem.clone();
//                                if(clickItem.getAmount() > 1){
//                                    clickedItem.setAmount(clickItem.getAmount() - 1);
//                                    soul.setAmount(1);
//                                }else {
//                                    clickediv.setItem(event.getSlot(),null);
//                                }
//
//                                inventory.setItem(2,soul);
//                                GuiMaster.customEnchant(inventory,inventory.getItem(2),inventory.getItem(3),inventory.getItem(4),inventory.getItem(21),GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                                return;
//                            }
//
//                            empty = inventory.getItem(3);
//                            if(empty.getType().equals(Material.getMaterial(ItemConfig.getItemType("SoulEmpty")))){
//                                ItemStack soul = clickItem.clone();
//                                if(clickItem.getAmount() > 1){
//                                    clickedItem.setAmount(clickItem.getAmount() - 1);
//                                    soul.setAmount(1);
//                                }else {
//                                    clickediv.setItem(event.getSlot(),null);
//                                }
//                                inventory.setItem(3,soul);
//                                GuiMaster.customEnchant(inventory,inventory.getItem(2),inventory.getItem(3),inventory.getItem(4),inventory.getItem(21),GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                                return;
//                            }
//
//                            empty = inventory.getItem(4);
//                            if(empty.getType().equals(Material.getMaterial(ItemConfig.getItemType("SoulEmpty")))){
//                                ItemStack soul = clickItem.clone();
//                                if(clickItem.getAmount() > 1){
//                                    clickedItem.setAmount(clickItem.getAmount() - 1);
//                                    soul.setAmount(1);
//                                }else {
//                                    clickediv.setItem(event.getSlot(),null);
//                                }
//
//                                inventory.setItem(4,soul);
//                                GuiMaster.customEnchant(inventory,inventory.getItem(2),inventory.getItem(3),inventory.getItem(4),inventory.getItem(21),GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                                return;
//                            }
//                        }
//                    }
//                    if(clickItem.getType().equals(Material.BOOK)){
//                        Inventory inventory = event.getInventory();
//                        Inventory clickediv = event.getClickedInventory();
//                        ItemStack book = clickItem.clone();
//                        ItemStack empty = inventory.getItem(21);
//
//                        if(empty.getType().equals(Material.getMaterial(ItemConfig.getItemType("BaseEmpty")))){
//                            if(clickItem.getAmount() > 1){
//                                clickedItem.setAmount(clickItem.getAmount() - 1);
//                                book.setAmount(1);
//                            }else {
//                                clickediv.setItem(event.getSlot(),null);
//                            }
//                            inventory.setItem(21,book);
//
//                            GuiMaster.customEnchant(inventory,inventory.getItem(2),inventory.getItem(3),inventory.getItem(4),inventory.getItem(21),GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                            return;
//                        }
//                    }
//                    return;
//                }
//
//                Inventory inventory = event.getClickedInventory();
//                if((event.getSlot() == 2) || (event.getSlot() == 3) || (event.getSlot() == 4) ){
//                    if(clickItem.getType().equals(Material.getMaterial(ItemConfig.getItemType("Soul")))){
//                         EnchantSystem.addItemToInventory(clickedItem,(Player) event.getWhoClicked());
//                         inventory.setItem(event.getSlot(),GuiItem.SoulEmpty());
//                        SoundSystem.ClickSound((Player) event.getWhoClicked());
//
//                        GuiMaster.customEnchant(inventory,inventory.getItem(2),inventory.getItem(3),inventory.getItem(4),inventory.getItem(21),GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                        return;
//                    }
//                }
//
//                if(event.getSlot() == 21){
//                    if(!clickItem.getType().equals(Material.getMaterial(ItemConfig.getItemType("BaseEmpty")))){
//                        EnchantSystem.addItemToInventory(clickedItem,(Player) event.getWhoClicked());
//                        inventory.setItem(event.getSlot(),GuiItem.BaseEmpty());
//                        SoundSystem.ClickSound((Player) event.getWhoClicked());
//
//                        GuiMaster.customEnchant(inventory,inventory.getItem(2),inventory.getItem(3),inventory.getItem(4),inventory.getItem(21),GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                        return;
//                    }
//                }
//
//                if(event.getSlot() == 8){
//                    if(!inventory.getItem(21).getType().equals(Material.BOOK)){
//                        GuiMaster.customEnchant(inventory,inventory.getItem(2),inventory.getItem(3),inventory.getItem(4),inventory.getItem(21),GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                        SoundSystem.FailedSound((Player) event.getWhoClicked());
//                        return;
//                    }
//
//                    int plLv = ((Player) event.getWhoClicked()).getLevel();
//                    int cost = CustomEnchantManager.calExpCost(GuiMaster.countSoul(inventory.getItem(2),inventory.getItem(3),inventory.getItem(4)),GuiMaster.get_Shelf(inventory.getItem(26)));
//
//                    if(cost == 0){
//                        SoundSystem.FailedSound((Player) event.getWhoClicked());
//                        return;
//                    }
//
//                    if(plLv < cost){
//                        GuiMaster.customEnchant(inventory,inventory.getItem(2),inventory.getItem(3),inventory.getItem(4),inventory.getItem(21),GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                        SoundSystem.FailedSound((Player) event.getWhoClicked());
//                        return;
//                    }
//
//                    GuiMaster.customEnchant(inventory,inventory.getItem(2),inventory.getItem(3),inventory.getItem(4),inventory.getItem(21),GuiMaster.get_Shelf(inventory.getItem(26)),true,(Player) event.getWhoClicked());
//                    inventory.setItem(2,GuiItem.SoulEmpty());
//                    inventory.setItem(3,GuiItem.SoulEmpty());
//                    inventory.setItem(4,GuiItem.SoulEmpty());
//                    SoundSystem.SuccessSound((Player) event.getWhoClicked());
//                    ((Player) event.getWhoClicked()).setLevel(plLv - cost);
//                    GuiMaster.customEnchant(inventory,inventory.getItem(2),inventory.getItem(3),inventory.getItem(4),inventory.getItem(21),GuiMaster.get_Shelf(inventory.getItem(26)),false,(Player) event.getWhoClicked());
//                    return;
//                }
//
//
//            }
//
//        }

//        if(event.getView().getTitle().equals("ドラゴンソウルモード")){
//            if(!(event.getCurrentItem() == null)){
//                if(!(event.getClickedInventory().getType().equals(InventoryType.PLAYER))) {
//                    switch (event.getSlot()) {
//                        case 0 -> {
//                            event.setCancelled(true);
//                            Location loc = GuiMaster.getLoc(event.getInventory().getItem(0));
//                            event.getWhoClicked().openInventory(GuiMaster.getCustomEnchantTable(loc));
//                            SoundSystem.OpenSound((Player) event.getWhoClicked());
//                            return;
//                        }
//                        case 3 -> {
//                            String Name = "WITHER_SKELETON_SKULL";
//                            GuiMaster.ChangeSoul(Name, (Player) event.getWhoClicked(), event.getClick(), EnchantmentManager.getNeedSoulItems(Name), EnchantmentManager.getSoulRate(Name));
//                            SoundSystem.ClickSound((Player) event.getWhoClicked());
//                        }
//                    }
//                }
//            }
//            event.setCancelled(true);
//            return;
//        }

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
