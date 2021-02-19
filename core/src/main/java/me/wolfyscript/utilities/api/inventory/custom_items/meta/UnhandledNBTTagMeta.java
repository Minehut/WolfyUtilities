package me.wolfyscript.utilities.api.inventory.custom_items.meta;

import me.wolfyscript.utilities.api.WolfyUtilities;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.legacy.Meta;
import me.wolfyscript.utilities.api.nms.nbt.NBTItem;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;

public class UnhandledNBTTagMeta extends Meta {

    public UnhandledNBTTagMeta() {
        setOption(LegacyMetaSettings.Option.EXACT);
        setAvailableOptions(LegacyMetaSettings.Option.EXACT, LegacyMetaSettings.Option.IGNORE);
    }


    @Override
    public boolean check(ItemBuilder itemOther, ItemBuilder item) {
        NBTItem nbtItem = WolfyUtilities.getWUCore().getNmsUtil().getNBTUtil().getItem(item.create());

        return false;
    }
}
