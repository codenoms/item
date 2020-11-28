package com.github.codenoms.item.meta.enchant;

import com.github.codenoms.util.NamespacedKey;
import com.github.codenoms.util.repo.TypeRepo;

import java.util.HashMap;
import java.util.Map;

public final class EnchantmentTypeRegistry implements TypeRepo<EnchantmentType>
{
    private final Map<NamespacedKey, EnchantmentType> enchantmentTypeMap = new HashMap<>();

    @Override
    public EnchantmentType getByNamespacedKey(NamespacedKey namespacedKey)
    {
        return enchantmentTypeMap.get(namespacedKey);
    }

    public void registerType(EnchantmentType type)
    {
        enchantmentTypeMap.put(type.getKey(), type);
    }

    public void unregisterType(String key)
    {
        unregisterType(new NamespacedKey(key));
    }

    public void unregisterType(NamespacedKey key)
    {
        enchantmentTypeMap.remove(key);
    }
}
