package com.github.majisyou.plusenchantment.Command;

import com.github.majisyou.plusenchantment.Gui.GuiMaster;
import com.github.majisyou.plusenchantment.NBT.ItemNBTLoader;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import com.github.majisyou.plusenchantment.System.EnchantSystem;
import com.github.majisyou.plusenchantment.System.ItemBuilder;
import net.kyori.adventure.text.NBTComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.Map;

public class Cmd_test implements CommandExecutor {

    PlusEnchantment plugin = PlusEnchantment.getInstance();
    public Cmd_test(PlusEnchantment plugin){plugin.getCommand("test").setExecutor(this);}
    private final static String Data_key = "test";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
           ItemStack leftItem = player.getInventory().getItemInMainHand();
           ItemMeta leftItemMeta = leftItem.getItemMeta();

           player.getWorld().dropItem(player.getLocation(),ItemBuilder.returnTool(leftItem));



//           ItemStack Scrap = player.getInventory().getItemInOffHand();
//           player.getWorld().dropItem(player.getLocation(),EnchantSystem.RepairItem(leftItem,Scrap));
//            ItemNBTLoader.ItemNBTLoad(leftItem);
//            plugin.getLogger().info(ItemNBTLoader.getItemStack()+"のstack");
//            plugin.getLogger().info(ItemNBTLoader.getItemMeta()+"のmeta");
//            plugin.getLogger().info(ItemNBTLoader.getPersistentDataContainer()+"のdata");
//            plugin.getLogger().info(ItemNBTLoader.getALLKey()+"のKEY");
//            plugin.getLogger().info(ItemNBTLoader.hasString("test")+"の結果");
//            plugin.getLogger().info(ItemNBTLoader.readString("test")+"の出力");
//            ItemNBTLoader.writeString("test","result");
//            plugin.getLogger().info(ItemNBTLoader.hasString("test")+"の結果");
//            plugin.getLogger().info(ItemNBTLoader.readString("test")+"の出力");
//            plugin.getLogger().info(ItemNBTLoader.getItemStack()+"のstack");
//            plugin.getLogger().info(ItemNBTLoader.getItemMeta()+"のmeta");
//            plugin.getLogger().info(ItemNBTLoader.getPersistentDataContainer()+"のdata");
//            plugin.getLogger().info(ItemNBTLoader.getALLKey()+"のKEY");
//
//            ByteArrayOutputStream io = new ByteArrayOutputStream();
//            String encodeObject;
////
//            try {
//                BukkitObjectOutputStream os = new BukkitObjectOutputStream(io);
//                os.writeObject(leftItem);
//                os.flush();
//
//                byte[] serializeObject = io.toByteArray();
//
//                encodeObject = Base64.getEncoder().encodeToString(serializeObject);
//                player.sendMessage(encodeObject);
//                byte[] DecodeObject = Base64.getDecoder().decode(encodeObject);
//
//                ByteArrayInputStream in = new ByteArrayInputStream(DecodeObject);
//                BukkitObjectInputStream is = new BukkitObjectInputStream(in);
//
//                ItemStack newItem = (ItemStack) is.readObject();
//
//                player.getWorld().dropItem(player.getLocation(),newItem);
//
//            } catch (IOException e) {
//                plugin.getLogger().info(e.toString());
//                plugin.getLogger().info("アイテムのシリアライズに失敗");
//            } catch (ClassNotFoundException e) {
//                plugin.getLogger().info(e.toString());
//                plugin.getLogger().info("アイテムのデシリアライズに失敗した");
//            }

        }
        return true;
    }
}
