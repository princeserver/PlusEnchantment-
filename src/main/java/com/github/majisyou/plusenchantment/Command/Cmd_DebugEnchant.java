package com.github.majisyou.plusenchantment.Command;

import com.github.majisyou.plusenchantment.Config.EnchantmentManager;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import com.github.majisyou.plusenchantment.System.CustomEnchants;
import com.github.majisyou.plusenchantment.System.EnchantSystem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Cmd_DebugEnchant implements CommandExecutor {
    PlusEnchantment plugin = PlusEnchantment.getInstance();
    public Cmd_DebugEnchant(PlusEnchantment plugin){plugin.getCommand("DebugEnchant").setExecutor(this);}

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
//        Player player = (Player) sender;
//        sender.sendMessage(player.getInventory().getItemInMainHand().getType().name()+EnchantmentManager.getScrapCoefficient(player.getInventory().getItemInMainHand().getType().name())+"の値");
        if(sender instanceof Player player){
            ItemStack item2 = new ItemStack(Material.DIAMOND_PICKAXE);
//            EnchantSystem.setCustomEnchant(item2,3);
            player.getInventory().addItem(item2);
            return true;
//            ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
//            item.addUnsafeEnchantment(CustomEnchants.TELEKINESIS,1);
//            item.addUnsafeEnchantment(Enchantment.DIG_SPEED,100);
//            List<String> lore = new ArrayList<>();
//            lore.add(ChatColor.GRAY+"Telepathy I");
//            ItemMeta itemMeta = item.getItemMeta();
//            if(itemMeta.hasLore())
//                for (String loreContain : itemMeta.getLore())
//                    lore.add(loreContain);
//            itemMeta.setLore(lore);
//            item.setItemMeta(itemMeta);
//
//            player.getInventory().addItem(item);
//            return true;
        }
        return true;
    }
}
