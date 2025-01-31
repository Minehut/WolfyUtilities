package me.wolfyscript.utilities.api.nms.v1_14_R1.nbt;

import me.wolfyscript.utilities.api.nms.nbt.NBTTagShort;
import me.wolfyscript.utilities.api.nms.nbt.NBTTagType;

public class NBTTagShortImpl extends NBTNumberImpl<net.minecraft.server.v1_14_R1.NBTTagShort> implements NBTTagShort {

    public static final NBTTagType<NBTTagShort> TYPE = new NBTTagTypeImpl<>(NBTTagType.Type.SHORT, nbtBase -> new NBTTagShortImpl((net.minecraft.server.v1_14_R1.NBTTagShort) nbtBase));

    private NBTTagShortImpl() {
        super(new net.minecraft.server.v1_14_R1.NBTTagShort((short) 0));
    }

    NBTTagShortImpl(net.minecraft.server.v1_14_R1.NBTTagShort nbtBase) {
        super(nbtBase);
    }

    public static NBTTagShort of(short value) {
        return new NBTTagShortImpl(new net.minecraft.server.v1_14_R1.NBTTagShort(value));
    }

    @Override
    public NBTTagType<NBTTagShort> getType() {
        return TYPE;
    }
}
