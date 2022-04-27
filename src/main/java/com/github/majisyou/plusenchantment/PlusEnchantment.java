package com.github.majisyou.plusenchantment;

import com.github.majisyou.plusenchantment.Command.Cmd_test;
import com.github.majisyou.plusenchantment.Config.CustomConfig;
import com.github.majisyou.plusenchantment.Event.OpenInventory;
import com.github.majisyou.plusenchantment.Event.PlayerBrokenItem;
import com.github.majisyou.plusenchantment.Event.PlayerClick;
import com.github.majisyou.plusenchantment.Event.PlayerRightClick;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlusEnchantment extends JavaPlugin {

    private static PlusEnchantment instance;
    public PlusEnchantment(){instance = this;}
    public static PlusEnchantment getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

        //commands
        new Cmd_test(this);

        //Config系の設定
        saveDefaultConfig();
        new CustomConfig(this,"Item.yml").saveDefaultConfig();

        //event
        new OpenInventory(this);
        new PlayerClick(this);
        new PlayerBrokenItem(this);
        new PlayerRightClick(this);


        getLogger().info("PlusEnchantmentを読み込む！");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        getLogger().info("PlusEnchantmentを一旦閉じる！");
    }
}
