package com.github.majisyou.plusenchantment.Event.Enchant;

import com.github.majisyou.plusenchantment.Config.EnchantmentManager;
import com.github.majisyou.plusenchantment.Config.ItemConfig;
import com.github.majisyou.plusenchantment.Gui.GuiMaster;
import com.github.majisyou.plusenchantment.PlusEnchantment;
import com.github.majisyou.plusenchantment.System.CustomEnchantManager;
import com.github.majisyou.plusenchantment.System.CustomEnchants;
import com.github.majisyou.plusenchantment.System.EnchantSystem;
import com.github.majisyou.plusenchantment.System.SoundSystem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class EnchantTable implements Listener {
    private static final PlusEnchantment plugin = PlusEnchantment.getInstance();
    public EnchantTable(PlusEnchantment plugin){ //mainメソッド内でイベントリスナを呼び出すためのコマンド
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler
    public static void CustomEnchantTable(EnchantItemEvent event){
        ItemStack itemStack = event.getItem();
        if(!itemStack.getType().equals(Material.BOOK)){
            return;
        }
        ItemStack item = event.getInventory().getItem(1);
        if(item == null){
            return;
        }
        if(item.getType().equals(Material.AIR)){
            return;
        }
        if(!item.getItemMeta().hasCustomModelData()){
            return;
        }
        if(item.getItemMeta().getCustomModelData() != ItemConfig.getCustomModelData("Soul")){
            return;
        }
        int count = event.whichButton();
        int shelf = GuiMaster.count_Shelf(event.getEnchantBlock().getLocation());
        shelf = Math.min(shelf,15);
        if(changeEnchantment(count,shelf, event.getEnchantsToAdd())){
            ItemStack result = event.getItem();
            result.setType(Material.ENCHANTED_BOOK);
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) result.getItemMeta();
            Enchantment newEnchant = CustomEnchants.getEnchant();
            EnchantSystem.addEnchant(meta,newEnchant,1);
            for (Enchantment enchantment: event.getEnchantsToAdd().keySet()){
                meta.addStoredEnchant(enchantment,event.getEnchantsToAdd().get(enchantment),true);
            }
            if(event.getEnchantsToAdd().keySet().size()==0){
                item.setAmount(item.getAmount()-1-count);
                SoundSystem.EnchantItem(event.getEnchanter());
            }
            result.setItemMeta(meta);
        }
    }

    @EventHandler
    public static void CustomEnchantTable(PrepareItemEnchantEvent event){
        Inventory inventory = event.getInventory();
        if(inventory.getItem(1)==null){
            return;
        }
        if(inventory.getItem(1).getType().equals(Material.AIR)){
            return;
        }
        if(!inventory.getItem(1).getItemMeta().hasCustomModelData()){
            return;
        }
        if(inventory.getItem(1).getItemMeta().getCustomModelData() != ItemConfig.getCustomModelData("Soul")){
            return;
        }
        if(event.getOffers()[0]==null){
            return;
        }
        if(event.getOffers()[0].getEnchantment().equals(CustomEnchants.OFFER_ENCHANT)){
            return;
        }

        int i = 0;
        EnchantmentOffer[] enchantmentOffers = event.getOffers().clone();
        for (EnchantmentOffer enchantmentOffer : event.getOffers()){
            enchantmentOffer.setEnchantment(CustomEnchants.OFFER_ENCHANT);
            enchantmentOffers[i] = enchantmentOffer;
            i++;
        }

        new PrepareItemEnchantEvent(event.getEnchanter(),event.getView(),event.getEnchantBlock(),event.getItem(),enchantmentOffers,event.getEnchantmentBonus());
    }

    public static boolean changeEnchantment(int soul, int shelf, Map<Enchantment,Integer> enchant){
        int max = 0;
        int random = 0;
        switch (soul){
            case 0 ->{
                max = 4*shelf + 180 + 180 + 180;
                random = new Random().nextInt(max);

                if(random < 180){
                    for (int i= 0 ; i < 2 ; i++){
                        addEnchant(enchant);
                    }
                    return false;
                }
                random -= 180;
                if(random < 180){
                    addEnchant(enchant);
                    return false;
                }
                random -= 180;
                if(random < 180){
                    return false;
                }
                changeEnchant(enchant);
                return true;
            }
            case 1 ->{
                max = 4*shelf + 35 + 35 + 35 + 35;
                random = new Random().nextInt(max);
                if(random < 35){
                    for (int i= 0 ; i < 3 ; i++){
                        addEnchant(enchant);
                    }
                    return false;
                }
                random -= 35;
                if(random < 35){
                    for (int i= 0 ; i < 2 ; i++){
                        addEnchant(enchant);
                    }
                    return false;
                }
                random -= 35;
                if(random < 35){
                    addEnchant(enchant);
                    return false;
                }
                random -= 35;
                if(random < 35){
                    return false;
                }
                changeEnchant(enchant);
                return true;
            }
            case 2 ->{
                max = 4*shelf + 10 + 10 + 10 + 10;
                random = new Random().nextInt(max);
                if(random < 10){
                    for (int i= 0 ; i < 4 ; i++){
                        addEnchant(enchant);
                    }
                    return false;
                }
                random -= 10;
                if(random < 10){
                    for (int i= 0 ; i < 3 ; i++){
                        addEnchant(enchant);
                    }
                    return false;
                }
                random -= 10;
                if(random < 10){
                    for (int i= 0 ; i < 2 ; i++){
                        addEnchant(enchant);
                    }
                    return false;
                }
                random -= 10;
                if(random < 10){
                    addEnchant(enchant);
                    return false;
                }
                changeEnchant(enchant);
                return true;
            }
        }
        return false;
    }

    public static void addEnchant(Map<Enchantment,Integer> map){
        int rand = new Random().nextInt(map.keySet().size());
        Enchantment enchantment = map.keySet().stream().toList().get(rand);
        EnchantmentManager.loadEnchantment(enchantment.getName());
        if(EnchantmentManager.getMaxLevel() > map.get(enchantment)){
            int level = map.get(enchantment)+1;
            map.remove(enchantment);
            map.put(enchantment,level);
        }
    }

    public static void changeEnchant(Map<Enchantment,Integer> map){
        int rand = new Random().nextInt(map.keySet().size());
        Enchantment enchantment = map.keySet().stream().toList().get(rand);
        map.remove(enchantment);
    }

}
