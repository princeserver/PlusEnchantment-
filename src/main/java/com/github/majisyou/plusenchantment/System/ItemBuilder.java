package com.github.majisyou.plusenchantment.System;

import com.github.majisyou.plusenchantment.NBT.ItemNBTLoader;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import javax.naming.Name;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ItemBuilder {
    private Map<Enchantment,Integer> enchantList = new HashMap<>();
    private static final PlusEnchantment plugin = PlusEnchantment.getInstance();

    public static ItemStack BrokenItem(ItemStack item){
        ItemStack BrokenItem = new ItemStack(Material.CHAIN_COMMAND_BLOCK,1);
        ItemMeta BrokenItemMeta = BrokenItem.getItemMeta();
        BrokenItemMeta.setCustomModelData(1);
        String name = ChatColor.WHITE +"壊れたアイテム("+item.getItemMeta().getDisplayName()+"("+item.getType().name()+"))";
        BrokenItemMeta.setDisplayName(name);
        NamespacedKey key = new NamespacedKey(plugin,"time");
        BrokenItemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING,ZonedDateTime.now().toString());
        BrokenItem.setItemMeta(BrokenItemMeta);
        ItemNBTLoader.ItemNBTLoad(BrokenItem);
        ByteArrayOutputStream io = new ByteArrayOutputStream();
        String encodeObject;
        try {
            BukkitObjectOutputStream os = new BukkitObjectOutputStream(io);
            os.writeObject(item);
            os.flush();
            byte[] serializeObject = io.toByteArray();
            encodeObject = Base64.getEncoder().encodeToString(serializeObject);
            ItemNBTLoader.writeString("BrokenItem",encodeObject);
            return ItemNBTLoader.getItemStack();
        } catch (IOException e) {
            plugin.getLogger().info("(PE)"+e);
            plugin.getLogger().info("(PE)"+"アイテムのシリアライズに失敗");
            return null;
        }
    }

    public static ItemStack returnTool(ItemStack itemStack){
        ItemNBTLoader.ItemNBTLoad(itemStack);
        String serializeObject = ItemNBTLoader.readString("BrokenItem");
        byte[] DecodeObject = Base64.getDecoder().decode(serializeObject);
        ByteArrayInputStream in = new ByteArrayInputStream(DecodeObject);
        try {
            BukkitObjectInputStream is = new BukkitObjectInputStream(in);
            return (ItemStack) is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            if(ItemNBTLoader.hasString("BrokenItem")){
                plugin.getLogger().info("(PE)"+"エンコードしたやつが間違ってる");
            }else {
                plugin.getLogger().info("(PE)"+"そもそもこのアイテムのNBTにエンコードした文字列が入っていない");
            }
            plugin.getLogger().info(e.toString());
            plugin.getLogger().info("(PE)"+"アイテムのデコードに失敗した");
            return null;
        }
    }
}
