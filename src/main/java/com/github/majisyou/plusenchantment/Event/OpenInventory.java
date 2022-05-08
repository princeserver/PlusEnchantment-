package com.github.majisyou.plusenchantment.Event;

import com.github.majisyou.plusenchantment.Gui.GuiMaster;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import com.github.majisyou.plusenchantment.System.SoundSystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

public class OpenInventory implements Listener {

    private static final PlusEnchantment plugin = PlusEnchantment.getInstance();
    public OpenInventory(PlusEnchantment plugin){ //mainメソッド内でイベントリスナを呼び出すためのコマンド
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }


    @EventHandler
    public static void OpenAnvil(InventoryOpenEvent event){
        Player player = (Player) event.getPlayer();
        if(event.getInventory().getType().equals(InventoryType.ANVIL) && !player.isSneaking()){
            event.setCancelled(true);
            SoundSystem.OpenSound(player);
            GuiMaster.OpenAnvil(player);
        }else if(event.getInventory().getType().equals(InventoryType.ANVIL)){
            return;
        }

        if(event.getInventory().getType().equals(InventoryType.GRINDSTONE)){
            event.setCancelled(true);
            SoundSystem.OpenSound(player);
            GuiMaster.OpenRepairMode(player);
        }

    }

}
