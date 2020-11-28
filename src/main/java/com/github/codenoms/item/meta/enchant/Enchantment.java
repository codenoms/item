package com.github.codenoms.item.meta.enchant;

public final class Enchantment
{
    private final EnchantmentType type;

    private int level;

    public Enchantment(EnchantmentType type, int level)
    {
        this(type, level, false);
    }

    public Enchantment(EnchantmentType type, int level, boolean unsafe)
    {
        this.type = type;
        setLevel(level, unsafe);
    }

    public EnchantmentType getType()
    {
        return type;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        setLevel(level, false);
    }

    public void setLevel(int level, boolean unsafe)
    {
        if(!(type.isValidLevel(level) || unsafe))
            throw new IndexOutOfBoundsException("level");
        this.level = level;
    }
}
