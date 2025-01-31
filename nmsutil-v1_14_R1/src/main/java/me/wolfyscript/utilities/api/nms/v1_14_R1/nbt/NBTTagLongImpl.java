package me.wolfyscript.utilities.api.nms.v1_14_R1.nbt;

import me.wolfyscript.utilities.api.nms.nbt.NBTTagLong;
import me.wolfyscript.utilities.api.nms.nbt.NBTTagType;

public class NBTTagLongImpl extends NBTNumberImpl<net.minecraft.server.v1_14_R1.NBTTagLong> implements NBTTagLong {

    public static final NBTTagType<NBTTagLong> TYPE = new NBTTagTypeImpl<>(NBTTagType.Type.LONG, nbtBase -> new NBTTagLongImpl((net.minecraft.server.v1_14_R1.NBTTagLong) nbtBase));

    private NBTTagLongImpl() {
        super(new net.minecraft.server.v1_14_R1.NBTTagLong(0));
    }

    NBTTagLongImpl(net.minecraft.server.v1_14_R1.NBTTagLong nbtBase) {
        super(nbtBase);
    }

    public static NBTTagLong of(long value) {
        return new NBTTagLongImpl(new net.minecraft.server.v1_14_R1.NBTTagLong(value));
    }

    @Override
    public NBTTagType<NBTTagLong> getType() {
        return TYPE;
    }
}
