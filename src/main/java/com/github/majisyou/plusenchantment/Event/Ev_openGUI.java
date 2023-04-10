package com.github.majisyou.plusenchantment.Event;

import com.github.majisyou.plusenchantment.Gui.GuiMaster;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import com.github.majisyou.plusenchantment.System.SoundSystem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;

import static org.bukkit.event.block.Action.RIGHT_CLICK_AIR;
import static org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK;

public class Ev_openGUI implements Listener {
    private static final PlusEnchantment plugin = PlusEnchantment.getInstance();

    public Ev_openGUI(PlusEnchantment plugin){ //mainメソッド内でイベントリスナを呼び出すためのコマンド
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public static void PlayerInteractEvent(PlayerInteractEvent event){
        if(event.getAction() == RIGHT_CLICK_AIR || event.getAction() == RIGHT_CLICK_BLOCK) {
            if(event.getAction() == RIGHT_CLICK_BLOCK){
                if(event.getPlayer().isSneaking()){
                    if(event.getClickedBlock().getType().equals(Material.ANVIL))
                        if(!event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR))
                            event.getPlayer().sendMessage(ChatColor.GOLD+"【 重要 】"+ChatColor.WHITE+" アイテム名編集モードは素手の状態で"+ChatColor.GREEN+"スニークしながら右クリック"+ChatColor.WHITE+"すると開くことができます");
                    return;
                }
                if(event.getClickedBlock().getType().equals(Material.ENDER_CHEST)){
                    return;
                }
            }
            if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.ANVIL)){
                event.setCancelled(true);
                SoundSystem.OpenSound(event.getPlayer());
                event.getPlayer().openInventory(GuiMaster.getAnvilGui());
                return;
            }
            if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.DAMAGED_ANVIL)){
                event.getPlayer().openInventory(GuiMaster.getAnvilGui());
                SoundSystem.OpenSound(event.getPlayer());
                event.setCancelled(true);
                return;
            }
            if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.CHIPPED_ANVIL)){
                event.getPlayer().openInventory(GuiMaster.getAnvilGui());
                SoundSystem.OpenSound(event.getPlayer());
                event.setCancelled(true);
                return;
            }

            if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.GRINDSTONE)){
                event.getPlayer().openInventory(GuiMaster.getRepairModeGui());
                SoundSystem.OpenSound(event.getPlayer());
                event.setCancelled(true);
            }

        }
    }

    @EventHandler
    public static void OpenAnvil(InventoryOpenEvent event){
        Player player = (Player) event.getPlayer();
        if(event.getInventory().getType().equals(InventoryType.ANVIL) && !player.isSneaking()){
            event.setCancelled(true);
            SoundSystem.OpenSound(player);
            player.openInventory(GuiMaster.getAnvilGui());
        }else if(event.getInventory().getType().equals(InventoryType.ANVIL)){
            return;
        }

        if(event.getInventory().getType().equals(InventoryType.GRINDSTONE)){
            event.setCancelled(true);
            SoundSystem.OpenSound(player);
            player.openInventory(GuiMaster.getRepairModeGui());
        }

//        if(event.getInventory().getType().equals(InventoryType.ENCHANTING)){
//            if(event.getPlayer().getInventory().getItemInOffHand().getType().equals(Material.STICK)){
//                event.setCancelled(true);
//                SoundSystem.OpenSound(player);
//                player.openInventory(GuiMaster.getCustomEnchantTable(event.getInventory().getLocation()));
//
//            }
//        }

    }

}
