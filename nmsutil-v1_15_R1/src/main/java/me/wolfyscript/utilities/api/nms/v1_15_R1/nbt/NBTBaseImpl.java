package me.wolfyscript.utilities.api.nms.v1_15_R1.nbt;

import me.wolfyscript.utilities.api.nms.nbt.NBTBase;

public abstract class NBTBaseImpl<NBT extends net.minecraft.server.v1_15_R1.NBTBase> implements NBTBase {

    protected final NBT nbt;

    NBTBaseImpl(NBT nbtBase) {
        this.nbt = nbtBase;
    }

    @Override
    public byte getTypeId() {
        return nbt.getTypeId();
    }

    @Override
    public String toString() {
        return nbt.toString();
    }

}
