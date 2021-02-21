package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.WolfyUtilities;
import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.api.nms.NBTUtil;
import me.wolfyscript.utilities.api.nms.nbt.NBTItem;
import me.wolfyscript.utilities.util.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OptionMetaBlockData extends SimpleMetaOption {

    private static final String BLOCK_DATA = "BlockStateTag";
    public static Creator<OptionMetaBlockData> CREATOR = new Creator<OptionMetaBlockData>() {
        @Override
        public OptionMetaBlockData create(CustomItem customItem) {
            return new OptionMetaBlockData();
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("block_data");
        }
    };

    public OptionMetaBlockData() {
        super();
    }

    @Override
    public boolean check(CustomItem customItem, ItemMeta thatMeta, ItemStack thatItem) {
        if (super.check(customItem, thatMeta, thatItem)) return true;
        NBTUtil nbtUtil = WolfyUtilities.getWUCore().getNmsUtil().getNBTUtil();
        NBTItem thisNBTItem = nbtUtil.getItem(customItem.getItemStack());
        NBTItem thatNBTItem = nbtUtil.getItem(thatItem);
        if (thisNBTItem.hasKeyOfType(BLOCK_DATA, 10)) {
            return thatNBTItem.hasKeyOfType(BLOCK_DATA, 10) && thisNBTItem.getCompound(BLOCK_DATA).equals(thatNBTItem.getCompound(BLOCK_DATA));
        } else return !thatNBTItem.hasKeyOfType(BLOCK_DATA, 10);
    }
}
