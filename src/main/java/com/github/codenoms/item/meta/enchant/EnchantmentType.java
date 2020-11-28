package com.github.codenoms.item.meta.enchant;

import com.github.codenoms.item.meta.ItemMeta;
import com.github.codenoms.util.NamespacedKey;

public abstract class EnchantmentType
{
    private final NamespacedKey key;
    private final int           maxLevel;

    public EnchantmentType(NamespacedKey key)
    {
        this(key, -1);
    }

    public EnchantmentType(NamespacedKey key, int maxLevel)
    {
        this.key      = key;
        this.maxLevel = maxLevel;
    }

    public NamespacedKey getKey()
    {
        return key;
    }

    public boolean isValidLevel(int level)
    {
        return maxLevel < 0 || (level > 0 && level <= maxLevel);
    }

    public abstract boolean canBeAppliedTo(ItemMeta meta);
}
