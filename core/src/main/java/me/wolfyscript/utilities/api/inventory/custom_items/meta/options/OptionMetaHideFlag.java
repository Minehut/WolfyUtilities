package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.WolfyUtilities;
import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.api.nms.NBTUtil;
import me.wolfyscript.utilities.api.nms.nbt.NBTCompound;
import me.wolfyscript.utilities.util.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OptionMetaHideFlag extends SimpleMetaOption {

    public static final Creator<OptionMetaHideFlag> CREATOR = new Creator<OptionMetaHideFlag>() {
        @Override
        public OptionMetaHideFlag create(CustomItem customItem) {
            return new OptionMetaHideFlag();
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("hide_flags");
        }
    };
    private static final String HIDE_FLAGS = "HideFlags";

    public OptionMetaHideFlag() {
        super();
    }

    @Override
    public boolean check(CustomItem customItem, ItemMeta thatMeta, ItemStack thatItem) {
        if (super.check(customItem, thatMeta, thatItem)) return true;
        if (!option.equals(SimpleSetting.IGNORE)) {
            NBTUtil nbtUtil = WolfyUtilities.getWUCore().getNmsUtil().getNBTUtil();
            NBTCompound thisNBTItem = nbtUtil.getItem(customItem.getItemStack()).getCompound();
            NBTCompound thatNBTItem = nbtUtil.getItem(thatItem).getCompound();
            if (thisNBTItem.hasKey(HIDE_FLAGS)) {
                return thatNBTItem.hasKey(HIDE_FLAGS) && thisNBTItem.getInt(HIDE_FLAGS) == thatNBTItem.getInt(HIDE_FLAGS);
            } else return !thatNBTItem.hasKey(HIDE_FLAGS);
        }
        return true;
    }
}
