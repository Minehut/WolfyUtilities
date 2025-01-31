package me.wolfyscript.utilities.api.nms.v1_14_R1;

import me.wolfyscript.utilities.api.nms.NBTUtil;
import me.wolfyscript.utilities.api.nms.NMSUtil;
import me.wolfyscript.utilities.api.nms.nbt.NBTItem;
import me.wolfyscript.utilities.api.nms.v1_14_R1.nbt.NBTItemImpl;

public class NBTUtilImpl extends NBTUtil {

    protected NBTUtilImpl(NMSUtil nmsUtil) {
        super(nmsUtil);
        this.nbtTag = new NBTTagImpl();
    }

    @Override
    public NBTItem getItem(org.bukkit.inventory.ItemStack bukkitItemStack) {
        return new NBTItemImpl(bukkitItemStack);
    }

}
