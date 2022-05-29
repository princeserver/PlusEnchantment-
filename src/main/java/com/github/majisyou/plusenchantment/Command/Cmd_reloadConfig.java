package com.github.majisyou.plusenchantment.Command;

import com.github.majisyou.plusenchantment.Config.CustomConfig;
import com.github.majisyou.plusenchantment.Config.EnchantmentManager;
import com.github.majisyou.plusenchantment.Config.ItemConfig;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Cmd_reloadConfig implements CommandExecutor {

    PlusEnchantment plugin = PlusEnchantment.getInstance();
    public Cmd_reloadConfig(PlusEnchantment plugin){plugin.getCommand("PlusEnchant_reloadConfig").setExecutor(this);}


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission("PSOP")){
            sender.sendMessage("You dont have permission");
            return true;
        }

        plugin.reloadConfig();
        new CustomConfig(plugin,"Item.yml").reloadConfig();
        EnchantmentManager.reloadConfig();
        EnchantmentManager.loadEnchantment();
        ItemConfig.reloadConfig();

        plugin.getLogger().info("(PE)"+"config is reloading");
        sender.sendMessage("リロードしたよ");
        return true;
    }
}
