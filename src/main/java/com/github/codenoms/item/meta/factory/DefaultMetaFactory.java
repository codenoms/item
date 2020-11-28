package com.github.codenoms.item.meta.factory;

import com.github.codenoms.item.meta.ItemMeta;
import com.github.codenoms.item.meta.enchant.Enchantment;
import com.github.codenoms.item.meta.enchant.EnchantmentType;
import com.github.codenoms.item.meta.enchant.NBTEnchantAdapter;
import com.github.codenoms.item.type.ItemType;
import com.github.codenoms.nbt.NBTCompound;
import com.github.codenoms.util.repo.TypeRepo;

public final class DefaultMetaFactory implements MetaFactory<ItemMeta>
{
    private final TypeRepo<EnchantmentType> enchantmentTypeRepo;

    public DefaultMetaFactory(TypeRepo<EnchantmentType> enchantmentTypeRepo)
    {
        this.enchantmentTypeRepo = enchantmentTypeRepo;
    }

    @Override
    public ItemMeta createNewMeta(ItemType type, NBTCompound compound)
    {
        compound = new NBTCompound(compound);
        compound.registerAdapter(Enchantment.class, new NBTEnchantAdapter(enchantmentTypeRepo));
        return new ItemMeta(type, compound);
    }

    @Override
    public ItemMeta copyMeta(ItemMeta meta)
    {
        return new ItemMeta(meta);
    }
}
