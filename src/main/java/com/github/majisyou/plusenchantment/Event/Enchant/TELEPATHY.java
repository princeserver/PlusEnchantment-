package com.github.majisyou.plusenchantment.Event.Enchant;

import com.github.majisyou.plusenchantment.PlusEnchantment;
import com.github.majisyou.plusenchantment.System.CustomEnchants;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class TELEPATHY implements Listener {
    private static final PlusEnchantment plugin = PlusEnchantment.getInstance();
    public TELEPATHY(PlusEnchantment plugin){ //mainメソッド内でイベントリスナを呼び出すためのコマンド
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public static void onBlockBreak(BlockBreakEvent event){
        if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR))
            return;
        if(!event.getPlayer().getInventory().getItemInMainHand().hasItemMeta())
            return;
        if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.TELEPATHY))
            return;
        if(event.getPlayer().getGameMode() == GameMode.CREATIVE || event.getPlayer().getGameMode() == GameMode.SPECTATOR)
            return;
        if(event.getPlayer().getInventory().firstEmpty() == -1)
            return;
        if(event.getBlock().getState() instanceof Container)
            return;
        if(!event.isDropItems())
            return;
        event.setDropItems(false);
        Player player = event.getPlayer();
        Collection<ItemStack> drops = event.getBlock().getDrops(event.getPlayer().getInventory().getItemInMainHand());
        //ここだけメソッドがない;
        if(drops.isEmpty())
            return;
        player.getInventory().addItem(drops.iterator().next());
    }

}
