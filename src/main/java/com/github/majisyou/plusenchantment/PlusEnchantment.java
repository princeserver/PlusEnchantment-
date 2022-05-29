package com.github.majisyou.plusenchantment;

import com.github.majisyou.plusenchantment.Command.Cmd_ChangeRedshirt;
import com.github.majisyou.plusenchantment.Command.Cmd_DebugEnchant;
import com.github.majisyou.plusenchantment.Command.Cmd_reloadConfig;
import com.github.majisyou.plusenchantment.Command.Cmd_test;
import com.github.majisyou.plusenchantment.Config.CustomConfig;
import com.github.majisyou.plusenchantment.Event.*;
import com.github.majisyou.plusenchantment.Event.Enchant.TELEPATHY;
import com.github.majisyou.plusenchantment.System.CustomEnchants;
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
//        new Cmd_test(this);
//        new Cmd_DebugEnchant(this);
        new Cmd_reloadConfig(this);
        new Cmd_ChangeRedshirt(this);

        //Config系の設定
        saveDefaultConfig();
        new CustomConfig(this,"Item.yml").saveDefaultConfig();

        //event
        new OpenInventory(this);
        new PlayerClick(this);
        new PlayerBrokenItem(this);
        new PlayerRightClick(this);
        new CloseInventory(this);


//        //customEnchant
//        CustomEnchants.register();

        getLogger().info("PlusEnchantmentを読み込む！");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        getLogger().info("PlusEnchantmentを一旦閉じる！");
    }
}
