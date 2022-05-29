package com.github.majisyou.plusenchantment.Event;

import com.github.majisyou.plusenchantment.Gui.GuiMaster;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import com.github.majisyou.plusenchantment.System.SoundSystem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import static org.bukkit.event.block.Action.RIGHT_CLICK_AIR;
import static org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK;

public class PlayerRightClick implements Listener {
    private static final PlusEnchantment plugin = PlusEnchantment.getInstance();
    public PlayerRightClick(PlusEnchantment plugin){plugin.getServer().getPluginManager().registerEvents(this,plugin);}

    @EventHandler
    public static void PlayerInteractEvent(PlayerInteractEvent event){

        if(event.getAction() == RIGHT_CLICK_AIR || event.getAction() == RIGHT_CLICK_BLOCK) {
            if(event.getPlayer().isSneaking()){
                if(event.getAction() == RIGHT_CLICK_BLOCK){
                    if(event.getClickedBlock().getType().equals(Material.ANVIL))
                        if(!event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR))
                        event.getPlayer().sendMessage(ChatColor.GOLD+"【 重要 】"+ChatColor.WHITE+" アイテム名編集モードは素手の状態で"+ChatColor.GREEN+"スニークしながら右クリック"+ChatColor.WHITE+"すると開くことができます");
                }
                return;
            }

            if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.ANVIL)||event.getPlayer().getInventory().getItemInOffHand().getType().equals(Material.ANVIL)){
                GuiMaster.OpenAnvil(event.getPlayer());
                SoundSystem.OpenSound(event.getPlayer());
                event.setCancelled(true);
                return;
            }
            if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.DAMAGED_ANVIL)||event.getPlayer().getInventory().getItemInOffHand().getType().equals(Material.DAMAGED_ANVIL)){
                GuiMaster.OpenAnvil(event.getPlayer());
                SoundSystem.OpenSound(event.getPlayer());
                event.setCancelled(true);
                return;
            }
            if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.CHIPPED_ANVIL)||event.getPlayer().getInventory().getItemInOffHand().getType().equals(Material.CHIPPED_ANVIL)){
                GuiMaster.OpenAnvil(event.getPlayer());
                SoundSystem.OpenSound(event.getPlayer());
                event.setCancelled(true);
                return;
            }


            if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.GRINDSTONE)||event.getPlayer().getInventory().getItemInOffHand().getType().equals(Material.GRINDSTONE)){
                GuiMaster.OpenRepairMode(event.getPlayer());
                SoundSystem.OpenSound(event.getPlayer());
                event.setCancelled(true);
            }
        }
    }

}
