package me.wolfyscript.utilities.api.nms.v1_14_R1.nbt;

import me.wolfyscript.utilities.api.nms.nbt.NBTTagEnd;
import me.wolfyscript.utilities.api.nms.nbt.NBTTagType;

public class NBTTagEndImpl extends NBTBaseImpl<net.minecraft.server.v1_14_R1.NBTTagEnd> implements NBTTagEnd {

    public static final NBTTagType<NBTTagEnd> TYPE = new NBTTagTypeImpl<>(NBTTagType.Type.TAG_END, nbtBase -> new NBTTagEndImpl());

    private NBTTagEndImpl() {
        super(new net.minecraft.server.v1_14_R1.NBTTagEnd());
    }

    NBTTagEndImpl(net.minecraft.server.v1_14_R1.NBTTagEnd nbtBase) {
        super(new net.minecraft.server.v1_14_R1.NBTTagEnd());
    }

    public static NBTTagEnd of() {
        return new NBTTagEndImpl();
    }

    @Override
    public NBTTagType<NBTTagEnd> getType() {
        return TYPE;
    }
}
