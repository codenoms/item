package com.github.codenoms.item.meta;

import com.github.codenoms.item.type.ItemType;
import com.github.codenoms.nbt.NBTCompound;

public class DamageableItemMeta extends ItemMeta
{
    public DamageableItemMeta(ItemType type)
    {
        super(type);
    }

    public DamageableItemMeta(ItemMeta meta)
    {
        super(meta);
    }

    public DamageableItemMeta(ItemType type, NBTCompound compound)
    {
        super(type, compound);
    }

    public int getDamage()
    {
        return tag.optInt("Damage").orElse(0);
    }

    public void setDamage(int damage)
    {
        tag.setInt("Damage", damage);
    }

    public boolean isUnbreakable()
    {
        return tag.optByte("Unbreakable").orElseGet(() -> (byte) 0) == 1;
    }

    public void setUnbreakable(boolean unbreakable)
    {
        tag.setByte("Unbreakable", (byte) (unbreakable ? 1 : 0));
    }
}
