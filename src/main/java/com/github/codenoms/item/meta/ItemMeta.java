package com.github.codenoms.item.meta;

import com.github.codenoms.item.meta.enchant.Enchantment;
import com.github.codenoms.item.meta.enchant.EnchantmentType;
import com.github.codenoms.item.meta.enchant.NBTEnchantAdapter;
import com.github.codenoms.item.type.ItemType;
import com.github.codenoms.nbt.NBTCompound;
import com.github.codenoms.nbt.TypedList;
import com.github.codenoms.util.NamespacedKey;

import java.util.*;
import java.util.stream.Collectors;

public class ItemMeta
{
    protected final ItemType type;
    protected final NBTCompound                       tag;
    protected final Map<EnchantmentType, Enchantment> enchantMap = new HashMap<>();

    public ItemMeta(ItemType type)
    {
        this(type, new NBTCompound());
    }

    public ItemMeta(ItemMeta meta)
    {
        this(meta.getType(), new NBTCompound(meta.getTag()));
    }

    public ItemMeta(ItemType type, NBTCompound tag)
    {
        this.type = type;
        this.tag  = tag;

        if(tag.containsList("Enchantments", Enchantment.class))
        {
            TypedList<Enchantment> enchants = tag.getList("Enchantments", Enchantment.class);
            enchants.forEach(this::addEnchantment);
        }
    }

    public void allowAnyBlockBreaking()
    {
        tag.remove("CanDestroy");
    }

    public boolean canBreakAnyBlocks()
    {
        return !tag.containsList("CanDestroy", String.class);
    }

    public boolean canBreakBlock(NamespacedKey key)
    {
        return canBreakAnyBlocks() || getBreakableBlocks().contains(key);
    }

    public void addBlockBreakAbility(NamespacedKey key)
    {
        TypedList<String> canPlaceOn;
        if(tag.containsList("CanDestroy", String.class))
            canPlaceOn = tag.getList("CanDestroy", String.class);
        else
            canPlaceOn = new TypedList<>(String.class);
        canPlaceOn.add(key.toString());
        tag.setList("CanDestroy", canPlaceOn);
    }

    public void removeBlockBreakAbility(NamespacedKey key)
    {
        TypedList<String> canPlaceOn;
        if(tag.containsList("CanDestroy", String.class))
            canPlaceOn = tag.getList("CanDestroy", String.class);
        else
            return;
        canPlaceOn.remove(key.toString());
        tag.setList("CanDestroy", canPlaceOn);
    }

    public Set<NamespacedKey> getBreakableBlocks()
    {
        if(tag.containsList("CanDestroy", String.class))
            return tag.getList("CanDestroy", String.class)
                    .stream()
                    .map(NamespacedKey::new)
                    .collect(Collectors.toSet());
        else
            return new HashSet<>();
    }

    public ItemType getType()
    {
        return type;
    }

    public NBTCompound getTag()
    {
        if(enchantMap.size() == 0)
            tag.remove("Enchantments");
        else
            tag.setList("Enchantments", new TypedList<>(Enchantment.class, new ArrayList<>(enchantMap.values())));
        return tag;
    }

    public boolean hasCustomModelData()
    {
        return tag.contains("CustomModelData", int.class);
    }

    public int getCustomModelData()
    {
        return tag.optInt("CustomModelData").orElse(0);
    }

    public Enchantment getEnchantment(EnchantmentType type)
    {
        return enchantMap.get(type);
    }

    public void addEnchantment(Enchantment enchantment)
    {
        addEnchantment(enchantment, false);
    }

    public void addEnchantment(Enchantment enchantment, boolean unsafe)
    {
        EnchantmentType type = enchantment.getType();
        if(type.canBeAppliedTo(this) || unsafe)
            enchantMap.put(enchantment.getType(), enchantment);
    }

    public void removeEnchantment(EnchantmentType type)
    {
        enchantMap.remove(type);
    }
}
