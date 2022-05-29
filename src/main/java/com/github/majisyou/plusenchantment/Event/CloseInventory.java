package com.github.majisyou.plusenchantment.Event;

import com.github.majisyou.plusenchantment.PlusEnchantment;
import com.github.majisyou.plusenchantment.System.EnchantSystem;
import com.github.majisyou.plusenchantment.System.SoundSystem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CloseInventory implements Listener {

    private static final PlusEnchantment plugin = PlusEnchantment.getInstance();
    public CloseInventory(PlusEnchantment plugin){ //mainメソッド内でイベントリスナを呼び出すためのコマンド
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public static void PlayerCloseInventory(InventoryCloseEvent event){
        if(event.getView().getTitle().equals("エンチャント合成モード")||event.getView().getTitle().equals("エンチャント消去モード")||event.getView().getTitle().equals("-修理モード-")){
            SoundSystem.CloseSound((Player) event.getPlayer());
            Inventory inventory = event.getInventory();
            ItemStack leftItem = inventory.getItem(3);
            ItemStack rightItem = inventory.getItem(5);

            if(EnchantSystem.EnchantItemJudge(leftItem.getType().name())){
                if(event.getPlayer().getInventory().firstEmpty()==-1){
                    event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(),leftItem);
                }else {
                    event.getPlayer().getInventory().addItem(leftItem);
                }
            }

            if(EnchantSystem.EnchantItemJudge(rightItem.getType().name())){
                if(event.getPlayer().getInventory().firstEmpty()==-1){
                    event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(),rightItem);
                }else {
                    event.getPlayer().getInventory().addItem(rightItem);
                }
            }

            if(rightItem.getType().equals(Material.COMMAND_BLOCK)){
                if(event.getPlayer().getInventory().firstEmpty()==-1){
                    event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(),rightItem);
                }else {
                    event.getPlayer().getInventory().addItem(rightItem);
                }
            }

            if(leftItem.getType().equals(Material.COMMAND_BLOCK)){
                if(event.getPlayer().getInventory().firstEmpty()==-1){
                    event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(),leftItem);
                }else {
                    event.getPlayer().getInventory().addItem(leftItem);
                }
            }

            if(rightItem.getType().equals(Material.CHAIN_COMMAND_BLOCK)){
                if(event.getPlayer().getInventory().firstEmpty()==-1){
                    event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(),rightItem);
                }else {
                    event.getPlayer().getInventory().addItem(rightItem);
                }
            }

            if(leftItem.getType().equals(Material.CHAIN_COMMAND_BLOCK)){
                if(event.getPlayer().getInventory().firstEmpty()==-1){
                    event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(),leftItem);
                }else {
                    event.getPlayer().getInventory().addItem(leftItem);
                }
            }

        }

        if(event.getInventory().getType().equals(InventoryType.ANVIL)){
            ((Player) event.getPlayer()).setLevel(((Player) event.getPlayer()).getLevel());
        }
    }
}
