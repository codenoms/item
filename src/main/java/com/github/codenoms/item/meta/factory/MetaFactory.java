package com.github.codenoms.item.meta.factory;

import com.github.codenoms.item.meta.ItemMeta;
import com.github.codenoms.item.type.ItemType;
import com.github.codenoms.nbt.NBTCompound;

public interface MetaFactory<T extends ItemMeta>
{
    default T createNewMeta(ItemType type)
    {
        return createNewMeta(type, new NBTCompound());
    }

    T createNewMeta(ItemType type, NBTCompound compound);

    T copyMeta(ItemMeta meta);
}
