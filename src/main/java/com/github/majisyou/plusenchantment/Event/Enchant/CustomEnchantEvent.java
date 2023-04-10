package com.github.majisyou.plusenchantment.Event.Enchant;

import com.github.majisyou.plusenchantment.PlusEnchantment;
import com.github.majisyou.plusenchantment.System.CustomEnchants;
import com.github.majisyou.plusenchantment.System.EnchantSystem;
import com.github.majisyou.plusenchantment.System.SoundSystem;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.block.Structure;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Sapling;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.eclipse.sisu.Priority;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CustomEnchantEvent implements Listener {
    private static final PlusEnchantment plugin = PlusEnchantment.getInstance();
    private static Map<String,List<ItemStack>> maps = new HashMap<>();
    public CustomEnchantEvent(PlusEnchantment plugin){ //mainメソッド内でイベントリスナを呼び出すためのコマンド
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

//    @EventHandler
//    public static void TelekinesisBreak(BlockBreakEvent event){
//        //TELEKINESISイベント処理
//        if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR))
//            return;
//        if(!event.getPlayer().getInventory().getItemInMainHand().hasItemMeta())
//            return;
//        if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.TELEKINESIS))
//            return;
//        if(event.getPlayer().getGameMode() == GameMode.CREATIVE || event.getPlayer().getGameMode() == GameMode.SPECTATOR)
//            return;
//        if(event.getPlayer().getInventory().firstEmpty() == -1)
//            return;
//        if(event.getBlock().getState() instanceof Container)
//            return;
//        if(!event.isDropItems())
//            return;
//        Integer lev = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchants().get(CustomEnchants.TELEKINESIS);
//        int random = new Random().nextInt(5);
//        if(random > lev)
//            return;
//        event.setDropItems(false);
//        Player player = event.getPlayer();
//        Collection<ItemStack> drops = event.getBlock().getDrops(event.getPlayer().getInventory().getItemInMainHand());
//        if(drops.isEmpty())
//            return;
//        player.getInventory().addItem(drops.iterator().next());
//    }

    @EventHandler
    public static void TelekinesisBlock(BlockDropItemEvent event){
        //TELEKINESISイベント処理
        if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR))
            return;
        if(!event.getPlayer().getInventory().getItemInMainHand().hasItemMeta())
            return;
        if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.TELEKINESIS))
            return;
        if(event.getPlayer().getGameMode() == GameMode.CREATIVE || event.getPlayer().getGameMode() == GameMode.SPECTATOR)
            return;
        if(event.getPlayer().getInventory().firstEmpty() == -1)
            return;
        if(event.getBlockState() instanceof Container)
            return;


        Integer lev = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchants().get(CustomEnchants.TELEKINESIS);
        int random = new Random().nextInt(10);
        if(random > lev)
            return;

        List<Item> items = new ArrayList<>(event.getItems());
        for (Item item : items){
            Map<Integer,ItemStack> drop = event.getPlayer().getInventory().addItem(item.getItemStack());
            if(!drop.isEmpty()) break;

            event.getItems().remove(item);
        }
    }

    @EventHandler
    public static void TelekinesisEntity(EntityDeathEvent event){
        //TELEKINESISイベント処理
        if(event.getEntity().getKiller() == null){
            return;
        }
        if(event.getEntity().getType().equals(EntityType.PLAYER)){
            return;
        }
        if(event.getEntity().getKiller().getInventory().getItemInMainHand().getType().equals(Material.AIR))
            return;
        if(!event.getEntity().getKiller().getInventory().getItemInMainHand().hasItemMeta())
            return;
        if(!event.getEntity().getKiller().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.TELEKINESIS))
            return;
        if(event.getEntity().getKiller().getGameMode() == GameMode.CREATIVE || event.getEntity().getKiller().getGameMode() == GameMode.SPECTATOR)
            return;
        if(event.getEntity().getKiller().getInventory().firstEmpty() == -1)
            return;
        if(event.getDrops().isEmpty())
            return;

        Integer lev = event.getEntity().getKiller().getInventory().getItemInMainHand().getItemMeta().getEnchants().get(CustomEnchants.TELEKINESIS);
        int random = new Random().nextInt(10);
        if(random > lev)
            return;

        List<ItemStack> items = new ArrayList<>(event.getDrops());
        for (ItemStack itemStack : items){
            Map<Integer,ItemStack> drop = event.getEntity().getKiller().getInventory().addItem(itemStack);
            if(!drop.isEmpty()) break;

            event.getDrops().remove(itemStack);
        }

        event.getEntity().getKiller().setTotalExperience(event.getEntity().getKiller().getTotalExperience()+event.getDroppedExp());
        event.setDroppedExp(0);
    }

    @EventHandler(priority = EventPriority.LOW)
    public static void AuToSmeltBreak(BlockDropItemEvent event){
        if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR))
            return;
        if(!event.getPlayer().getInventory().getItemInMainHand().hasItemMeta())
            return;
        if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.AUTO_SMELT))
            return;
        if(event.getPlayer().getGameMode() == GameMode.CREATIVE || event.getPlayer().getGameMode() == GameMode.SPECTATOR)
            return;
        if(event.getBlock().getState() instanceof Container)
            return;

        Integer lev = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchants().get(CustomEnchants.AUTO_SMELT);
        int random = new Random().nextInt(5);

        if(random > lev)
            return;


        List<Item> items = new ArrayList<>(event.getItems());
        for (Item item : items){
            Iterator<Recipe> iter = Bukkit.recipeIterator();
            ItemStack itemStack = item.getItemStack();
            while (iter.hasNext()) {
                Recipe recipe = iter.next();
                if (!(recipe instanceof FurnaceRecipe)) continue;
                if (!((FurnaceRecipe) recipe).getInputChoice().test(itemStack)){
//                    plugin.getLogger().info((((FurnaceRecipe) recipe).getInput().getType().name()));
                    continue;
                }
                event.getItems().remove(item);
                ItemStack result = recipe.getResult();
                result.setAmount(itemStack.getAmount());
                item.setItemStack(result);
                event.getItems().add(item);
                break;
            }
        }
    }

    @EventHandler
    public static void Presentiment(PlayerItemDamageEvent event){
        if(event.getItem().getItemMeta().getEnchants().containsKey(CustomEnchants.PRESENTIMENT)){
            ItemStack item = event.getItem();
            ItemMeta itemMeta = item.getItemMeta();
            if(itemMeta instanceof Damageable damageable){
                int durability =  item.getType().getMaxDurability();
                int damage = damageable.getDamage();

                int judge = (int) (durability*0.9);

                if(damage >= judge){
                    SoundSystem.warmItem(event.getPlayer());
                }
            }
        }
    }

    @EventHandler
    public static void HarvestDance(PlayerToggleSneakEvent event){
        if(!event.isSneaking()){
            return;
        }
        if(event.getPlayer().getInventory().getLeggings() == null){
            return;
        }
        if(event.getPlayer().getInventory().getLeggings().getType().equals(Material.AIR))
            return;
        if(!event.getPlayer().getInventory().getLeggings().hasItemMeta())
            return;
        if(!event.getPlayer().getInventory().getLeggings().getItemMeta().hasEnchant(CustomEnchants.HARVEST_DANCE))
            return;
        if(event.getPlayer().getGameMode() == GameMode.CREATIVE || event.getPlayer().getGameMode() == GameMode.SPECTATOR)
            return;

        int lv = event.getPlayer().getInventory().getLeggings().getItemMeta().getEnchantLevel(CustomEnchants.HARVEST_DANCE);

        Location loc = event.getPlayer().getLocation();
//        boolean success = false;
        for (int x = -2 ; x <= 2 ; x ++){
            for (int z = -2 ; z <= 2 ; z ++){
                for (int y = -2 ; y <= 2 ; y ++){
                    Block block = loc.getWorld().getBlockAt(loc.getBlockX()+x,loc.getBlockY()+y,loc.getBlockZ()+z);
                    BlockData data = block.getBlockData();
                    int random = new Random().nextInt(20);
                    if(lv > random){
                        continue;
                    }
                    // if ブロックが作物
                    if(data instanceof Ageable ageable){
                        if(ageable.getMaximumAge() != ageable.getAge()){
                            ageable.setAge(ageable.getAge()+1);
                            block.setBlockData(ageable);
//                            if(!success){
//                                success = true;
//                            }
                        }
                    }

//            if(data instanceof Sapling sapling){
//                if(sapling.getMaximumStage() != sapling.getStage()){
//                    new StructureGrowEvent(block.getLocation(),)
//                    sapling.setStage(sapling.getStage()+1);
//                    block.setBlockData(sapling);
//                }
//            }

                }
            }
        }
//
//        if(success) {
//
//            if (!event.getPlayer().getInventory().getLeggings().containsEnchantment(Enchantment.DURABILITY)) {
//                Damageable meta = (Damageable) event.getPlayer().getInventory().getLeggings().getItemMeta();
//                meta.setDamage(meta.getDamage() + 1);
//                event.getPlayer().getInventory().getLeggings().setItemMeta(meta);
//            }
//
//            int level = event.getPlayer().getInventory().getLeggings().getEnchantmentLevel(Enchantment.DURABILITY);
//            if (Math.random() > (1.0 / (level + 1))) {
//                Damageable meta = (Damageable) event.getPlayer().getInventory().getLeggings().getItemMeta();
//                meta.setDamage(meta.getDamage() + 1);
//                event.getPlayer().getInventory().getLeggings().setItemMeta(meta);
//            }
//        }
    }


    @EventHandler
    public static void HeadCutEntity(EntityDeathEvent event){
        //TELEKINESISイベント処理
        if(event.getEntity().getKiller() == null){
            return;
        }
        if(event.getEntity().getType().equals(EntityType.PLAYER)){
            return;
        }
        if(event.getEntity().getKiller().getInventory().getItemInMainHand().getType().equals(Material.AIR))
            return;
        if(!event.getEntity().getKiller().getInventory().getItemInMainHand().hasItemMeta())
            return;
        if(!event.getEntity().getKiller().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.HEAD_CUT))
            return;
//        if(event.getEntity().getKiller().getGameMode() == GameMode.CREATIVE || event.getEntity().getKiller().getGameMode() == GameMode.SPECTATOR)
//            return;

        Integer lev = event.getEntity().getKiller().getInventory().getItemInMainHand().getItemMeta().getEnchants().get(CustomEnchants.HEAD_CUT);
        Entity entity = event.getEntity();
        ItemStack head = getHead(entity.getType());

        if(head != null){
            int dropLv = 0;
            if(event.getEntity().getKiller().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_MOBS)){
                dropLv = event.getEntity().getKiller().getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_MOBS);
            }
            int max = (int) (((double) 1)/(((double) lev*lev/10000)*(1+0.4*dropLv)));
            int random = new Random().nextInt(max);
//            plugin.getLogger().info(max+"の値");
            if(random < 1){
                event.getDrops().add(head);
            }
        }
    }

    @EventHandler
    public static void Ev_replace(BlockDropItemEvent event){
        if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR))
            return;
        if(!event.getPlayer().getInventory().getItemInMainHand().hasItemMeta())
            return;
//        if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.SOUL_BIND))
//            return;
        if(!event.getPlayer().getInventory().getItemInOffHand().hasItemMeta())
            return;

        if(event.getBlockState().getBlockData() instanceof Ageable ageable){
            ItemStack itemStack = event.getPlayer().getInventory().getItemInOffHand();
            ItemMeta itemMeta = itemStack.getItemMeta();
            boolean success = false;
            for (Item item:event.getItems()){
                ItemStack drop = item.getItemStack();
                ItemMeta dropMeta = drop.getItemMeta();
                if(dropMeta.equals(itemMeta)){
                    success = true;
                    break;
                }
            }

            if(success){
                ageable.setAge(1);
                event.getBlock().getWorld().setBlockData(event.getBlock().getLocation(), ageable);
                itemStack.setAmount(itemStack.getAmount()-1);
            }
        }
    }


    public static ItemStack getHead(EntityType entityType){
        switch (entityType){
            case ZOMBIE -> {
                return new ItemStack(Material.ZOMBIE_HEAD);
            }
            case SKELETON -> {
                return new ItemStack(Material.SKELETON_SKULL);
            }
            case ENDER_DRAGON -> {
                return new ItemStack(Material.DRAGON_HEAD);
            }
            case CREEPER -> {
                return new ItemStack(Material.CREEPER_HEAD);
            }
//            case PLAYER -> {
//                ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
//                SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
//                skullMeta.setOwningPlayer(player);
//                itemStack.setItemMeta(skullMeta);
//                return itemStack;
//            }
            default -> {
                return null;
            }
        }
    }

    @EventHandler
    public static void ToolDamage(PlayerItemDamageEvent event){
        if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR))
            return;
        if(!event.getPlayer().getInventory().getItemInMainHand().hasItemMeta())
            return;
        if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.DURABILITY_PLUS))
            return;
        if(event.getPlayer().getGameMode() == GameMode.CREATIVE || event.getPlayer().getGameMode() == GameMode.SPECTATOR)
            return;

        int random = new Random().nextInt(5);
        if(random < 2){
            event.setCancelled(true);
            return;
        }
    }

//    private static Map<String,PlayerInventory> map2 = new HashMap<>();

    @EventHandler
    public static void SoulBind(PlayerDeathEvent event){
        List<ItemStack> items = new ArrayList<>();
//        PlayerInventory inventory = event.getPlayer().getInventory();
//        for (ItemStack itemStack : inventory){
//            if(itemStack == null) continue;
//
//            if(itemStack.getType().equals(Material.AIR)) continue;
//
//            if(!itemStack.hasItemMeta()){
//                inventory.removeItem(itemStack);
//                continue;
//            }
//
//            if(itemStack.getItemMeta().hasEnchant(CustomEnchants.SOUL_BIND)){
//                continue;
//            }
//
//
//            inventory.removeItem(itemStack);
//        }
        for (ItemStack itemStack : event.getDrops()){
            event.getPlayer().getInventory().addItem(itemStack);
            if(itemStack.hasItemMeta()){
                if(itemStack.getItemMeta().hasEnchant(CustomEnchants.SOUL_BIND)){
                    items.add(itemStack);
                }
            }
        }
        if(items.size() > 0){
            for (ItemStack itemStack:items){
                event.getDrops().remove(itemStack);
            }
            maps.put(event.getEntity().getPlayerProfile().getName(),items);
        }
    }

    @EventHandler
    public static void SoulBind(PlayerRespawnEvent event){
        if(maps.containsKey(event.getPlayer().getPlayerProfile().getName())){
            for (ItemStack itemStack:maps.get(event.getPlayer().getPlayerProfile().getName())){
                EnchantSystem.addItemToInventory(itemStack,event.getPlayer().getInventory(),event.getRespawnLocation(),event.getPlayer().getName());
            }
        }
    }

    @EventHandler
    public static void Damage(EntityDamageByEntityEvent event){
        //Double_edge_Causeの処理
        if(event.getDamager() instanceof Player player){
            if(player.getInventory().getItemInMainHand().getType().equals(Material.AIR))
                return;
            if(!player.getInventory().getItemInMainHand().hasItemMeta())
                return;
            if(!player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.DOUBLE_EDGE_CURSE))
                return;
            double damage = event.getDamage() / 10;
            if(damage < 0.5){
                int random = new Random().nextInt(2);
                if(random == 1){
                    damage = 0.5;
                }else {
                    return;
                }
            }
            player.damage(damage,player);
        }
    }

    @EventHandler
    public static void ToolBreaking(PlayerItemDamageEvent event){
        if(event.getItem().getItemMeta().getEnchants().containsKey(CustomEnchants.DESTROY_CURSE)){
            int random = new Random().nextInt(50000);
            if(random < 1){
                if(event.getItem().getItemMeta().displayName() == null){
                    ItemMeta itemMeta = event.getItem().getItemMeta();
                    itemMeta.setDisplayName(event.getItem().getType().getKey().getKey());
                    event.getItem().setItemMeta(itemMeta);
                }
                event.getPlayer().sendMessage(ChatColor.RED+"破壊の呪いによって"+event.getItem().getItemMeta().getDisplayName()+"が壊れてしまった");
                event.getItem().setAmount(0);
                SoundSystem.BrokenItem(event.getPlayer());
            }
        }
    }

//    @EventHandler
//    public static void ShaveWool(PlayerShearEntityEvent event){
//        if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR))
//            return;
//        if(!event.getPlayer().getInventory().getItemInMainHand().hasItemMeta())
//            return;
//        if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.RAINBOW_SHAVE))
//            return;
//        if(event.getPlayer().getGameMode() == GameMode.CREATIVE || event.getPlayer().getGameMode() == GameMode.SPECTATOR)
//            return;
//        if(!event.getEntity().getType().equals(EntityType.SHEEP))
//            return;
//
//        event.getItem().setType(getRainbow().getType());
//    }

//    public static void ShaveWool(EntityDropItemEvent event){
//        if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR))
//            return;
//        if(!event.getPlayer().getInventory().getItemInMainHand().hasItemMeta())
//            return;
//        if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.RAINBOW_SHAVE))
//            return;
//        if(event.getPlayer().getGameMode() == GameMode.CREATIVE || event.getPlayer().getGameMode() == GameMode.SPECTATOR)
//            return;
//        if(!event.getEntity().getType().equals(EntityType.SHEEP))
//            return;
//        event.getItem().setType(getRainbow().getType());
//    }


    @EventHandler
    public static void DropItem(EntityDropItemEvent event){
        if(event.getEntity().getType().equals(EntityType.SHEEP)){
            Material material = event.getItemDrop().getItemStack().getType();
            if(material.equals(Material.WHITE_WOOL) || material.equals(Material.GRAY_WOOL) || material.equals(Material.LIME_WOOL) || material.equals(Material.CYAN_WOOL) || material.equals(Material.BLACK_WOOL) || material.equals(Material.BLUE_WOOL) || material.equals(Material.BROWN_WOOL) || material.equals(Material.GREEN_WOOL) || material.equals(Material.RED_WOOL) || material.equals(Material.ORANGE_WOOL) || material.equals(Material.PINK_WOOL) || material.equals(Material.LIGHT_BLUE_WOOL) || material.equals(Material.LIGHT_GRAY_WOOL) || material.equals(Material.YELLOW_WOOL) || material.equals(Material.PURPLE_WOOL) || material.equals(Material.MAGENTA_WOOL)){
                ItemStack itemStack = getRainbow();
                itemStack.setAmount(event.getItemDrop().getItemStack().getAmount());
                event.getItemDrop().setItemStack(itemStack);
            }
        }
    }

    public static ItemStack getRainbow(){
        int random = new Random().nextInt(16)+1;
        switch (random){
            case 1 ->{
                return new ItemStack(Material.WHITE_WOOL);
            }
            case 2 ->{
                return new ItemStack(Material.RED_WOOL);
            }
            case 3 ->{
                return new ItemStack(Material.BLACK_WOOL);
            }
            case 4 ->{
                return new ItemStack(Material.BLUE_WOOL);
            }
            case 5 ->{
                return new ItemStack(Material.BROWN_WOOL);
            }
            case 6 ->{
                return new ItemStack(Material.GRAY_WOOL);
            }
            case 7 ->{
                return new ItemStack(Material.CYAN_WOOL);
            }
            case 8 ->{
                return new ItemStack(Material.LIGHT_BLUE_WOOL);
            }
            case 9 ->{
                return new ItemStack(Material.LIGHT_GRAY_WOOL);
            }
            case 10 ->{
                return new ItemStack(Material.MAGENTA_WOOL);
            }
            case 11 ->{
                return new ItemStack(Material.ORANGE_WOOL);
            }
            case 12 ->{
                return new ItemStack(Material.LIME_WOOL);
            }
            case 13 -> {
                return new ItemStack(Material.PINK_WOOL);
            }
            case 14 ->{
                return new ItemStack(Material.PURPLE_WOOL);
            }
            case 15 ->{
                return new ItemStack(Material.YELLOW_WOOL);
            }
            case 16 ->{
                return new ItemStack(Material.GREEN_WOOL);
            }
            default -> {
                return new ItemStack(Material.COBBLESTONE);
            }
        }
    }


}
