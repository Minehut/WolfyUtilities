package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.WolfyUtilities;
import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.api.nms.NBTUtil;
import me.wolfyscript.utilities.api.nms.nbt.NBTCompound;
import me.wolfyscript.utilities.util.NamespacedKey;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;

public class OptionMetaHideFlag extends SimpleMetaOption {

    public static final Creator<OptionMetaHideFlag> CREATOR = new Creator<OptionMetaHideFlag>() {
        @Override
        public OptionMetaHideFlag create(CustomItem customItem) {
            return new OptionMetaHideFlag(customItem);
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("hide_flags");
        }
    };
    private static final String HIDE_FLAGS = "HideFlags";

    public OptionMetaHideFlag(CustomItem customItem) {
        super(customItem);
    }

    @Override
    public boolean check(CustomItem itemThis, ItemBuilder itemThat) {
        if (!option.equals(SimpleSetting.IGNORE)) {
            NBTUtil nbtUtil = WolfyUtilities.getWUCore().getNmsUtil().getNBTUtil();
            NBTCompound thisNBTItem = nbtUtil.getItem(itemThis.getItemStack()).getCompound();
            NBTCompound thatNBTItem = nbtUtil.getItem(itemThat.getItemStack()).getCompound();
            if (thisNBTItem.hasKey(HIDE_FLAGS)) {
                if (!thatNBTItem.hasKey(HIDE_FLAGS) || thisNBTItem.getInt(HIDE_FLAGS) != thatNBTItem.getInt(HIDE_FLAGS)) {
                    return false;
                }
            } else if (thatNBTItem.hasKey(HIDE_FLAGS)) {
                return false;
            }
        }
        return false;
    }
}
