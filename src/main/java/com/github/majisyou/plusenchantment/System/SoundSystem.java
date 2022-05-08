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



}
