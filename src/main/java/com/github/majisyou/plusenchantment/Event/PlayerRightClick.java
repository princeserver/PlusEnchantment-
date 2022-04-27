package com.github.majisyou.plusenchantment.Event;

import com.github.majisyou.plusenchantment.Gui.GuiMaster;
import com.github.majisyou.plusenchantment.PlusEnchantment;
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
            if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.ANVIL)||event.getPlayer().getInventory().getItemInOffHand().getType().equals(Material.ANVIL)){
                GuiMaster.OpenAnvil(event.getPlayer());
                event.setCancelled(true);
                return;
            }
            if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.GRINDSTONE)||event.getPlayer().getInventory().getItemInOffHand().getType().equals(Material.GRINDSTONE)){
                GuiMaster.OpenRepairMode(event.getPlayer());
                event.setCancelled(true);
            }
        }
    }

}
