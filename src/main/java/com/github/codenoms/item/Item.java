package com.github.codenoms.item;

import com.github.codenoms.item.meta.ItemMeta;
import com.github.codenoms.item.type.ItemType;
import com.github.codenoms.nbt.NBT;
import com.github.codenoms.nbt.NBTCompound;

import java.util.Objects;

public final class Item
{
    private ItemMeta meta;
    private int      count;

    public Item(Item item)
    {
        this.meta  = item.getType().getMetaFactory().copyMeta(item.getMeta());
        this.count = item.getCount();
    }

    public Item(ItemType type, int count)
    {
        if(type == null)
            throw new NullPointerException("type");
        this.meta  = type.getMetaFactory().createNewMeta(type);
        this.count = count;
    }

    public Item(ItemType type, int count, NBTCompound tag)
    {
        if(type == null)
            throw new NullPointerException("type");
        else if(tag == null)
            throw new NullPointerException("tag");
        this.meta  = type.getMetaFactory().createNewMeta(type, tag);
        this.count = count;
    }

    public ItemMeta getMeta()
    {
        return meta;
    }

    public ItemType getType()
    {
        return meta.getType();
    }

    public void setType(ItemType type)
    {
        setType(type, false);
    }

    public void setType(ItemType type, boolean copyMetaData)
    {
        if(copyMetaData)
            this.meta = type.getMetaFactory().createNewMeta(type, meta.getTag());
        else
            this.meta = type.getMetaFactory().createNewMeta(type);
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return count == item.count &&
               meta.equals(item.meta);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(meta, count);
    }
}
