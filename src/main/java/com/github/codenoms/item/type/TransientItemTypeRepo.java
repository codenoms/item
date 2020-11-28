package com.github.codenoms.item.type;

import com.github.codenoms.item.meta.factory.MetaFactoryProvider;
import com.github.codenoms.util.NamespacedKey;
import com.github.codenoms.util.repo.TypeRepo;

public final class TransientItemTypeRepo implements TypeRepo<ItemType>
{
    private final MetaFactoryProvider metaFactoryProvider;

    public TransientItemTypeRepo(MetaFactoryProvider metaFactoryProvider)
    {
        this.metaFactoryProvider = metaFactoryProvider;
    }

    @Override
    public ItemType getByNamespacedKey(NamespacedKey key)
    {
        return new ItemType(key, metaFactoryProvider.getMetaFactory(key));
    }
}
