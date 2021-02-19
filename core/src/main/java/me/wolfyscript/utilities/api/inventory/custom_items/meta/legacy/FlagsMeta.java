package me.wolfyscript.utilities.api.inventory.custom_items.meta.legacy;


import me.wolfyscript.utilities.api.inventory.custom_items.meta.LegacyMetaSettings;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;

public class FlagsMeta extends Meta {

    public FlagsMeta() {
        setOption(LegacyMetaSettings.Option.EXACT);
        setAvailableOptions(LegacyMetaSettings.Option.EXACT, LegacyMetaSettings.Option.IGNORE);
    }

    @Override
    public boolean check(ItemBuilder itemOther, ItemBuilder item) {
        if (option.equals(LegacyMetaSettings.Option.IGNORE)) {
            itemOther.getItemMeta().getItemFlags().forEach(itemOther::removeItemFlags);
            item.getItemMeta().getItemFlags().forEach(item::removeItemFlags);
        }
        return true;
    }
}
