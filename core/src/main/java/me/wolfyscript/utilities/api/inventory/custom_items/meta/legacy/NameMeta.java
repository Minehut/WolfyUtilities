package me.wolfyscript.utilities.api.inventory.custom_items.meta.legacy;

import me.wolfyscript.utilities.api.inventory.custom_items.meta.LegacyMetaSettings;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;

public class NameMeta extends Meta {

    public NameMeta() {
        setOption(LegacyMetaSettings.Option.EXACT);
        setAvailableOptions(LegacyMetaSettings.Option.EXACT, LegacyMetaSettings.Option.IGNORE);
    }

    @Override
    public boolean check(ItemBuilder meta1, ItemBuilder meta2) {
        if (option.equals(LegacyMetaSettings.Option.IGNORE)) {
            meta1.setDisplayName(null);
            meta2.setDisplayName(null);
        }
        return true;
    }
}
