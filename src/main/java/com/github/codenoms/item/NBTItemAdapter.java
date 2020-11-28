package com.github.codenoms.item;

import com.github.codenoms.item.type.ItemType;
import com.github.codenoms.nbt.NBTAdapter;
import com.github.codenoms.nbt.NBTCompound;
import com.github.codenoms.util.repo.TypeRepo;

public final class NBTItemAdapter implements NBTAdapter<Item>
{
    private final TypeRepo<ItemType> itemTypeRepo;

    public NBTItemAdapter(TypeRepo<ItemType> itemTypeRepo)
    {
        this.itemTypeRepo = itemTypeRepo;
    }

    @Override
    public NBTCompound serializeToNBT(Item item)
    {
        NBTCompound compound = new NBTCompound();
        compound.setString("id", item.getType().getKey().toString());
        compound.setByte("Count", (byte) item.getCount());
        compound.setCompound("tag", item.getMeta().getTag());
        return compound;
    }

    @Override
    public Item deserializeFromNBT(NBTCompound compound)
    {
        ItemType type  = itemTypeRepo.getByNamespacedKey(compound.getString("id"));
        int      count = compound.getByte("Count");
        Item     item;
        if(compound.contains("tag", NBTCompound.class))
            item = new Item(type, count, compound.getCompound("tag"));
        else
            item = new Item(type, count);
        return item;
    }
}
