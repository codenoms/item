package com.github.codenoms.item;

import com.github.codenoms.nbt.NBTAdapter;
import com.github.codenoms.nbt.NBTCompound;

import java.util.Optional;

public final class NBTSlotAdapter implements NBTAdapter<Slot>
{
    private final NBTItemAdapter itemAdapter;

    public NBTSlotAdapter(NBTItemAdapter itemAdapter)
    {
        this.itemAdapter = itemAdapter;
    }

    @Override
    public NBTCompound serializeToNBT(Slot slot)
    {
        NBTCompound compound = slot.getItem()
                                   .flatMap((item) -> Optional.of(itemAdapter.serializeToNBT(item)))
                                   .or(() -> Optional.of(new NBTCompound()))
                                   .get();
        compound.setByte("Slot", (byte) slot.getSlot());
        return compound;
    }

    @Override
    public Slot deserializeFromNBT(NBTCompound compound)
    {
        return new Slot(compound.getByte("Slot"), itemAdapter.deserializeFromNBT(compound));
    }
}
