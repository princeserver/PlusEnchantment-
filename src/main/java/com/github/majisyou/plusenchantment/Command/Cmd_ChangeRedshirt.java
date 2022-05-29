package com.github.majisyou.plusenchantment.Command;

import com.github.majisyou.plusenchantment.PlusEnchantment;
import com.github.majisyou.plusenchantment.System.EnchantSystem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Cmd_ChangeRedshirt implements CommandExecutor {

    PlusEnchantment plugin = PlusEnchantment.getInstance();
    public Cmd_ChangeRedshirt(PlusEnchantment plugin){plugin.getCommand("ChangeItem").setExecutor(this);}

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(player.getInventory().getItemInMainHand().getType().equals(Material.AIR)){
                sender.sendMessage("メインハンドに直したいアイテムを入れてください");
                return true;
            }
            if(!EnchantSystem.EnchantItemJudge(player.getInventory().getItemInMainHand().getType().name())){
                sender.sendMessage("このアイテムは変換できません。壊れたアイテムは運営まで報告お願いします");
                return true;
            }
            ItemStack ChangeItem = EnchantSystem.ChangeRedShirt(player.getInventory().getItemInMainHand());
            if(ChangeItem ==null){
                sender.sendMessage("loreが設定されていないエンチャント本だよ");
                return true;
            }
            player.getInventory().setItemInMainHand(ChangeItem);
            sender.sendMessage("変換が完了した");
            return true;
        }
        sender.sendMessage("これはコンソールからは打てません");
        return true;
    }
}
