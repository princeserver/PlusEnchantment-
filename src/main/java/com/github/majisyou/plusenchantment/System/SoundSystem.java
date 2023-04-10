package com.github.majisyou.plusenchantment.System;

import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

public class SoundSystem {

    public static void OpenSound(Player player){
        player.getWorld().playSound(player, Sound.BLOCK_ENDER_CHEST_OPEN, SoundCategory.BLOCKS,1F,1F);
    }
    public static void CloseSound(Player player){
        player.getWorld().playSound(player, Sound.BLOCK_ENDER_CHEST_CLOSE, SoundCategory.BLOCKS,1,1F);
    }
    public static void FailedSound(Player player){
        player.getWorld().playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.BLOCKS,1,0);
    }
    public static void SuccessSound(Player player){
        player.getWorld().playSound(player, Sound.BLOCK_ANVIL_USE, SoundCategory.BLOCKS,1,1F);
    }
    public static void ClickSound(Player player){
        player.getWorld().playSound(player, Sound.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS,1F,0);
    }
    public static void BrokenItem(Player player){
        player.playSound(player,Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR,SoundCategory.PLAYERS,1,1);
    }
    public static void warmItem(Player player){
        player.playSound(player,Sound.BLOCK_ANVIL_PLACE,SoundCategory.PLAYERS,(float) 0.05,1);
    }
    public static void EnchantItem(Player player){
        player.playSound(player,Sound.BLOCK_ENCHANTMENT_TABLE_USE,SoundCategory.PLAYERS,2,1);
    }



}
