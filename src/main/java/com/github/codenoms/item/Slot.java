package com.github.codenoms.item;

import java.util.Optional;

public final class Slot
{
    private final int slot;

    private Item item;

    public Slot(int slot)
    {
        this(slot, null);
    }

    public Slot(int slot, Item item)
    {
        this.slot = slot;
        this.item = item;
    }

    public int getSlot()
    {
        return slot;
    }

    public Optional<Item> getItem()
    {
        return Optional.ofNullable(item);
    }

    public void setItem(Item item)
    {
        this.item = item;
    }
}
