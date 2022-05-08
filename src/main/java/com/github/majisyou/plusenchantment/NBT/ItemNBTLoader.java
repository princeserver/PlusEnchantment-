package com.github.majisyou.plusenchantment.NBT;

import com.github.majisyou.plusenchantment.PlusEnchantment;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ItemNBTLoader {
    private static final PlusEnchantment plugin = PlusEnchantment.getInstance();
    private static ItemMeta itemMeta;
    private static ItemStack itemStack;
    private static PersistentDataContainer persistentDataContainer;

    public static void ItemNBTLoad(ItemStack item){
        itemStack = item;
        itemMeta = item.getItemMeta();
        persistentDataContainer = itemMeta.getPersistentDataContainer();
    }

    public static ItemMeta getItemMeta(){return itemMeta;}
    public static ItemStack getItemStack(){
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static PersistentDataContainer getPersistentDataContainer() {return persistentDataContainer;}

    public static List<String> getKey(String keyName){
        Set<NamespacedKey> keys = persistentDataContainer.getKeys();
        List<String> keyList = new ArrayList<>();

        for (NamespacedKey AccessKey:keys){
            if(AccessKey.getKey().matches(TextBuilder.textplus("^",keyName,".*"))){
                keyList.add(AccessKey.getKey());
            }
        }

        return keyList;
    }
    public static List<String> getALLKey(){
        Set<NamespacedKey> keys = persistentDataContainer.getKeys();
        List<String> keyList = new ArrayList<>();
        for(NamespacedKey key:keys){
            keyList.add(key.getKey());
        }
        return keyList;
    }

    protected int getNextIndex(String path, PersistentDataType type){
        //このメソッドの存在意味がわからない
        //1から順にkeyを確認して存在しないiを取得している
        for(int i =1; true;i++){
            NamespacedKey key = new NamespacedKey(plugin, TextBuilder.textplus(path,String.valueOf(i)));
            if(!(persistentDataContainer.has(key,type))){
                return i;
            }
        }
    }

    public static void writeString(String keyword,String value){
        NamespacedKey key = new NamespacedKey(plugin,keyword);
        persistentDataContainer.set(key,PersistentDataType.STRING,value);
        itemStack.setItemMeta(itemMeta);
    }
    public static String readString(String keyword){
        NamespacedKey key = new NamespacedKey(plugin,keyword);
        return persistentDataContainer.get(key,PersistentDataType.STRING);
    }
    public static boolean hasString(String keyword){
        NamespacedKey key = new NamespacedKey(plugin,keyword);
        return persistentDataContainer.has(key,PersistentDataType.STRING);
    }



}
