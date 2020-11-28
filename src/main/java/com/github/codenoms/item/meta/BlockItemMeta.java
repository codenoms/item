package com.github.codenoms.item.meta;

import com.github.codenoms.item.type.ItemType;
import com.github.codenoms.nbt.NBTCompound;
import com.github.codenoms.nbt.TypedList;
import com.github.codenoms.util.NamespacedKey;

import java.util.*;
import java.util.stream.Collectors;

public class BlockItemMeta extends ItemMeta
{
    public BlockItemMeta(ItemType type)
    {
        super(type);
    }

    public BlockItemMeta(ItemMeta meta)
    {
        super(meta);
    }

    public BlockItemMeta(ItemType type, NBTCompound compound)
    {
        super(type, compound);
    }

    public void allowPlacementOnAnyBlocks()
    {
        tag.remove("CanPlaceOn");
    }

    public boolean canPlaceOnAnyBlock()
    {
        return !tag.containsList("CanPlaceOn", String.class);
    }

    public boolean canPlaceOnBlock(NamespacedKey key)
    {
        return canPlaceOnAnyBlock() || getBlocksToPlaceOn().contains(key);
    }

    public void addBlockPlaceAbility(NamespacedKey key)
    {
        TypedList<String> canPlaceOn;
        if(tag.containsList("CanPlaceOn", String.class))
            canPlaceOn = tag.getList("CanPlaceOn", String.class);
        else
            canPlaceOn = new TypedList<>(String.class);
        canPlaceOn.add(key.toString());
        tag.setList("CanPlaceOn", canPlaceOn);
    }

    public void removeBlockPlaceAbility(NamespacedKey key)
    {
        TypedList<String> canPlaceOn;
        if(tag.containsList("CanPlaceOn", String.class))
            canPlaceOn = tag.getList("CanPlaceOn", String.class);
        else
            return;
        canPlaceOn.remove(key.toString());
        tag.setList("CanPlaceOn", canPlaceOn);
    }

    public Set<NamespacedKey> getBlocksToPlaceOn()
    {
        if(tag.containsList("CanPlaceOn", String.class))
            return tag.getList("CanPlaceOn", String.class)
                      .stream()
                      .map(NamespacedKey::new)
                      .collect(Collectors.toSet());
        else
            return new HashSet<>();
    }

    public NBTCompound getTileProperties()
    {
        return tag.optCompound("BlockEntityTag").orElseGet(NBTCompound::new);
    }

    public void addPlaceProperty(String key, String value)
    {
        NBTCompound compound = tag.optCompound("BlockStateTag").orElseGet(NBTCompound::new);
        compound.setString(key, value);
        tag.set("BlockStateTag", compound);
    }

    public void removePlaceProperty(String key)
    {
        tag.optCompound("BlockStateTag").ifPresent((compound) -> compound.remove(key));
    }

    public void removePlaceProperties()
    {
        tag.remove("BlockStateTag");
    }

    public Map<String, String> getPlaceProperties()
    {
        return tag.optCompound("BlockStateTag").flatMap((compound) ->
        {
            Map<String, String> properties = new HashMap<>();
            compound.entries(String.class).forEach((entry) -> properties.put(entry.getKey(), entry.getValue()));
            return Optional.of(properties);
        }).orElseGet(HashMap::new);
    }
}
