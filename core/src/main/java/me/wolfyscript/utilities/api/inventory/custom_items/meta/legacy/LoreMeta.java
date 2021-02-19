package me.wolfyscript.utilities.api.inventory.custom_items.meta.legacy;


import me.wolfyscript.utilities.api.inventory.custom_items.meta.LegacyMetaSettings;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;

import java.util.ArrayList;

public class LoreMeta extends Meta {

    public LoreMeta() {
        setOption(LegacyMetaSettings.Option.EXACT);
        setAvailableOptions(LegacyMetaSettings.Option.EXACT, LegacyMetaSettings.Option.IGNORE);
    }

    @Override
    public boolean check(ItemBuilder itemOther, ItemBuilder item) {
        if (option.equals(LegacyMetaSettings.Option.IGNORE)) {
            itemOther.setLore(new ArrayList<>());
            item.setLore(new ArrayList<>());
        }
        return true;
    }
}
