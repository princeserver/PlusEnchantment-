package com.github.majisyou.plusenchantment.System;

import io.papermc.paper.enchantments.EnchantmentRarity;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityCategory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class EnchantmentWrapper extends Enchantment {

    private final String name;
    private final int maxLvl;
    private final Component display;
    private final boolean curse;
    private final String key;

    public EnchantmentWrapper(String namespace, String name, int lvl, boolean curse){
        super(NamespacedKey.minecraft(namespace));
        this.name = name;
        this.maxLvl = lvl;
        this.display = Component.text(name);
        this.curse = curse;
        this.key = namespace;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMaxLevel() {
        return maxLvl;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ALL;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return curse;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }

    @Override
    public Component displayName(int level) {
        switch (level){
            case 1 ->{
                return (Component.text(display+" I")).toBuilder().build();
            }
            case 2 ->{
                return (Component.text(display+" II")).toBuilder().build();
            }
            case 3 ->{
                return (Component.text(display+" III")).toBuilder().build();
            }
            case 4 ->{
                return (Component.text(display+" IV")).toBuilder().build();
            }
            case 5 ->{
                return (Component.text(display+" V")).toBuilder().build();
            }
            case 6 ->{
                return (Component.text(display+" VI")).toBuilder().build();
            }
            case 7 ->{
                return (Component.text(display+" VII")).toBuilder().build();
            }
            case 8 ->{
                return (Component.text(display+" VIII")).toBuilder().build();
            }
            case 9 ->{
                return (Component.text(display+" IX")).toBuilder().build();
            }
            case 10 ->{
                return (Component.text(display+" X")).toBuilder().build();
            }
            default -> {
                return (Component.text(display+"lv"+level)).toBuilder().build();
            }
        }
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }

    @Override
    public EnchantmentRarity getRarity() {
        return EnchantmentRarity.COMMON;
    }

    @Override
    public float getDamageIncrease(int level, EntityCategory entityCategory) {
        return 0;
    }

    @Override
    public Set<EquipmentSlot> getActiveSlots() {
        Set<EquipmentSlot> slots = new HashSet<>();
        slots.add(EquipmentSlot.HAND);
        return slots;
    }

    @Override
    public String translationKey() {
        String result = "enchantment.minecraft."+key;
        return result;
    }



}
