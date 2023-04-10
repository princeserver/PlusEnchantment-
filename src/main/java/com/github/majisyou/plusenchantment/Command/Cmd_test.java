package com.github.majisyou.plusenchantment.Command;

import com.github.majisyou.plusenchantment.Config.EnchantmentManager;
import com.github.majisyou.plusenchantment.Gui.GuiItem;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import com.github.majisyou.plusenchantment.System.CustomEnchantManager;
import com.github.majisyou.plusenchantment.System.CustomEnchants;
import com.github.majisyou.plusenchantment.System.EnchantSystem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;


public class Cmd_test implements CommandExecutor {

    PlusEnchantment plugin = PlusEnchantment.getInstance();
    public Cmd_test(PlusEnchantment plugin){plugin.getCommand("test").setExecutor(this);}
    private final static String Data_key = "test";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
//        CustomEnchantManager.loadList(3,15);
//        Enchantment ench = CustomEnchantManager.getEnchant();
//        int lv = CustomEnchantManager.getEnchantLv(ench);
//        sender.sendMessage(ench.getName()+"と"+lv);

//        sender.sendMessage(Enchantment.ARROW_DAMAGE.translationKey());

        if(sender instanceof Player player){
            ItemStack itemStack = GuiItem.Soul();
            player.getInventory().addItem(itemStack);
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
//            ItemStack soul = new ItemStack(Material.COMMAND_BLOCK,3);
//            Map<Enchantment,Integer> result = CustomEnchantManager.getEnchantmentMap(3,20);
//            EnchantSystem.customEnchant(soul,item,15);
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
            Enchantment enchantment = CustomEnchants.getEnchantmentList().get(Integer.parseInt(args[0]));
            EnchantSystem.addEnchant(meta,enchantment,Integer.parseInt(args[1]));

//            plugin.getLogger().info(Enchantment.DURABILITY.translationKey());
//            plugin.getLogger().info(enchantment.translationKey());

//            plugin.getServer().getPluginManager().callEvent(new PlayerItemDamageEvent(player,player.getInventory().getItemInMainHand(),100,1000));
//            for (Enchantment enchantment:result.keySet()){
//                EnchantSystem.addEnchant(meta,enchantment,result.get(enchantment));
//                meta.addStoredEnchant(enchantment,result.get(enchantment),true);
//            }
            item.setItemMeta(meta);
//
//            ItemStack item2 = new ItemStack(Material.ENCHANTED_BOOK);
//            ItemMeta itemMeta = item2.getItemMeta();
//            item2.setItemMeta(itemMeta);
//
//            ItemStack item3 = item2.clone();
//            EnchantSystem.removeEnchant(itemMeta,enchantment);
//            item3.setItemMeta(itemMeta);

            player.getInventory().addItem(item);
//            player.getInventory().addItem(item2);
//            player.getInventory().addItem(GuiItem.Soul());
//            player.getInventory().addItem(item3);



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
