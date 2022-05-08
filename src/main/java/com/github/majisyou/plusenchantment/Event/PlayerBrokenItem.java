package com.github.majisyou.plusenchantment.Event;

import com.github.majisyou.plusenchantment.PlusEnchantment;
import com.github.majisyou.plusenchantment.System.EnchantSystem;
import com.github.majisyou.plusenchantment.System.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class PlayerBrokenItem implements Listener {
    private static final PlusEnchantment plugin = PlusEnchantment.getInstance();
    public PlayerBrokenItem(PlusEnchantment plugin){ //mainメソッド内でイベントリスナを呼び出すためのコマンド
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public static void PlayerBrokenItemEvent(PlayerItemBreakEvent event){
        if(EnchantSystem.BrokenItemJudge(event.getBrokenItem().getType().toString())){
            ItemStack BrokenItem = ItemBuilder.BrokenItem(event.getBrokenItem());
            if(!(BrokenItem==null)){
                Inventory playerInventory = event.getPlayer().getInventory();
                if(!(playerInventory.firstEmpty()==-1)){
                    playerInventory.addItem(BrokenItem);
                }else {
                    event.getPlayer().sendMessage(ChatColor.WHITE+"アイテムが壊れた。インベントリに空きが無かったからその場にドロップしたよ");
                    event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(),BrokenItem);
                }
            }
        }
    }

}
