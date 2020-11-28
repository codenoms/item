package com.github.codenoms.item.type;

import com.github.codenoms.item.meta.factory.MetaFactory;
import com.github.codenoms.util.NamespacedKey;

import java.util.Objects;

public final class ItemType
{
    private final NamespacedKey  key;
    private final MetaFactory<?> metaFactory;
//    private final boolean       hasCustomModelData;
//    private final int           customModelData;

    public ItemType(NamespacedKey key, MetaFactory<?> metaFactory)
    {
        this.key = key;
        this.metaFactory = metaFactory;
//        this.hasCustomModelData = false;
//        this.customModelData    = 0;
    }

//    public ItemType(NamespacedKey key, int customModelData)
//    {
//        this.key                = key;
//        this.hasCustomModelData = true;
//        this.customModelData    = customModelData;
//    }

    public NamespacedKey getKey()
    {
        return key;
    }

    public MetaFactory<?> getMetaFactory()
    {
        return metaFactory;
    }

    //    public boolean hasCustomModelData()
//    {
//        return hasCustomModelData;
//    }
//
//    public int getCustomModelData()
//    {
//        return customModelData;
//    }

    @Override
    public String toString()
    {
        // TODO add properties in toString?
        return key.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        ItemType itemType = (ItemType) o;
        return /*hasCustomModelData == itemType.hasCustomModelData &&
               customModelData == itemType.customModelData &&*/
               key.equals(itemType.key);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(key/*, hasCustomModelData, customModelData*/);
    }
}
