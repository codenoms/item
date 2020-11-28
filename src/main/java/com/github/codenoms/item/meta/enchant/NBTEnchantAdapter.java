package com.github.codenoms.item.meta.enchant;

import com.github.codenoms.nbt.NBTAdapter;
import com.github.codenoms.nbt.NBTCompound;
import com.github.codenoms.util.repo.TypeRepo;

import java.util.NoSuchElementException;

public final class NBTEnchantAdapter implements NBTAdapter<Enchantment>
{
    private final TypeRepo<EnchantmentType> enchantmentTypeRepo;

    public NBTEnchantAdapter(TypeRepo<EnchantmentType> enchantmentTypeRepo)
    {
        this.enchantmentTypeRepo = enchantmentTypeRepo;
    }

    @Override
    public NBTCompound serializeToNBT(Enchantment enchantment)
    {
        NBTCompound compound = new NBTCompound();
        compound.setString("id", enchantment.getType().getKey().toString());
        compound.setInt("lvl", enchantment.getLevel());
        return compound;
    }

    @Override
    public Enchantment deserializeFromNBT(NBTCompound compound)
    {
        EnchantmentType type = enchantmentTypeRepo.getByNamespacedKey(compound.getString("id"));
        Class<?> levelType = compound.typeOf("lvl").orElse(null);
        if(levelType == Integer.class)
            return new Enchantment(type, compound.getInt("lvl"));
        else if(levelType == Short.class)
            return new Enchantment(type, compound.getShort("lvl"));
        else
            throw new NoSuchElementException("lvl");
    }
}
