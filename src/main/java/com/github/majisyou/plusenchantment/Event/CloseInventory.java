package com.github.majisyou.plusenchantment.Event;

import com.github.majisyou.plusenchantment.Config.ItemConfig;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import com.github.majisyou.plusenchantment.System.EnchantSystem;
import com.github.majisyou.plusenchantment.System.SoundSystem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
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

            if(EnchantSystem.EnchantItemJudge(leftItem)){
                EnchantSystem.addItemToInventory(leftItem,(Player) event.getPlayer());
            }

            if(EnchantSystem.EnchantItemJudge(rightItem)){
                EnchantSystem.addItemToInventory(rightItem,(Player) event.getPlayer());
            }

            if(rightItem.getType().equals(Material.getMaterial(ItemConfig.getItemType("Scrap")))){
                EnchantSystem.addItemToInventory(rightItem,(Player) event.getPlayer());
            }

            if(leftItem.getType().equals(Material.CHAIN_COMMAND_BLOCK)) {
                EnchantSystem.addItemToInventory(leftItem,(Player) event.getPlayer());
            }
        }


        if(event.getView().getTitle().equals("カスタムエンチャントテーブル")){
            SoundSystem.CloseSound((Player) event.getPlayer());
            Inventory inventory = event.getInventory();
            ItemStack soul1 = inventory.getItem(2);
            ItemStack soul2 = inventory.getItem(3);
            ItemStack soul3 = inventory.getItem(4);
            ItemStack book = inventory.getItem(21);


            if(soul1.getType().equals(Material.getMaterial(ItemConfig.getItemType("Soul")))){
                EnchantSystem.addItemToInventory(soul1,(Player) event.getPlayer());
            }
            if(soul2.getType().equals(Material.getMaterial(ItemConfig.getItemType("Soul")))){
                EnchantSystem.addItemToInventory(soul2,(Player) event.getPlayer());
            }if(soul3.getType().equals(Material.getMaterial(ItemConfig.getItemType("Soul")))){
                EnchantSystem.addItemToInventory(soul3,(Player) event.getPlayer());
            }

            if(book.getType().equals(Material.BOOK) || book.getType().equals(Material.ENCHANTED_BOOK)) {
                EnchantSystem.addItemToInventory(book,(Player) event.getPlayer());
            }
        }

//        if(event.getInventory().getType().equals(InventoryType.ANVIL)){
//            ((Player) event.getPlayer()).setLevel(((Player) event.getPlayer()).getLevel());
//        }
    }
}
