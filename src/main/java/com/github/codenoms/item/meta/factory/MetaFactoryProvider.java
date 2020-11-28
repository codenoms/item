package com.github.codenoms.item.meta.factory;

import com.github.codenoms.item.meta.ItemMeta;
import com.github.codenoms.util.NamespacedKey;

import java.util.HashMap;
import java.util.Map;

public final class MetaFactoryProvider
{
    private final MetaFactory<ItemMeta> defaultMetaFactory;
    private final Map<NamespacedKey, MetaFactory<?>> factoryMap = new HashMap<>();

    public MetaFactoryProvider(MetaFactory<ItemMeta> defaultMetaFactory)
    {
        this.defaultMetaFactory = defaultMetaFactory;
    }

    public void registerMetaFactory(NamespacedKey key, MetaFactory<?> factory)
    {
        factoryMap.put(key, factory);
    }

    public MetaFactory<?> getMetaFactory(NamespacedKey key)
    {
        return factoryMap.getOrDefault(key, defaultMetaFactory);
    }

    public void unregisterMetaFactory(NamespacedKey key)
    {
        factoryMap.remove(key);
    }
}
