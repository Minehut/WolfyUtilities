package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.WolfyUtilities;
import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.api.nms.NBTUtil;
import me.wolfyscript.utilities.api.nms.nbt.NBTItem;
import me.wolfyscript.utilities.util.NamespacedKey;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;

public class OptionMetaBlockData extends SimpleMetaOption {

    private static final String BLOCK_DATA = "BlockStateTag";
    public static Creator<OptionMetaBlockData> CREATOR = new Creator<OptionMetaBlockData>() {
        @Override
        public OptionMetaBlockData create(CustomItem customItem) {
            return new OptionMetaBlockData(customItem);
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("block_data");
        }
    };


    public OptionMetaBlockData(CustomItem customItem) {
        super(customItem);
    }

    @Override
    public boolean check(CustomItem itemThis, ItemBuilder itemThat) {
        if (!option.equals(SimpleSetting.IGNORE)) {
            NBTUtil nbtUtil = WolfyUtilities.getWUCore().getNmsUtil().getNBTUtil();
            NBTItem thisNBTItem = nbtUtil.getItem(itemThis.getItemStack());
            NBTItem thatNBTItem = nbtUtil.getItem(itemThat.getItemStack());
            if (thisNBTItem.hasKeyOfType(BLOCK_DATA, 10)) {
                if (!thatNBTItem.hasKeyOfType(BLOCK_DATA, 10) || !thisNBTItem.getCompound(BLOCK_DATA).equals(thatNBTItem.getCompound(BLOCK_DATA))) {
                    return false;
                }
            } else if (thatNBTItem.hasKeyOfType(BLOCK_DATA, 10)) {
                return false;
            }
        }
        return false;
    }
}
