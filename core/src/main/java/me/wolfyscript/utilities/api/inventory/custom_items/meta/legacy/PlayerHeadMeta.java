package me.wolfyscript.utilities.api.inventory.custom_items.meta.legacy;


import me.wolfyscript.utilities.api.inventory.custom_items.meta.LegacyMetaSettings;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;

public class PlayerHeadMeta extends Meta {

    public PlayerHeadMeta() {
        setOption(LegacyMetaSettings.Option.EXACT);
        setAvailableOptions(LegacyMetaSettings.Option.EXACT, LegacyMetaSettings.Option.IGNORE);
    }

    @Override
    public boolean check(ItemBuilder itemOther, ItemBuilder item) {
        if (option.equals(LegacyMetaSettings.Option.EXACT)) {
            String valueOther = itemOther.getPlayerHeadValue();
            String value = item.getPlayerHeadValue();
            if (!valueOther.equals(value)) {
                return false;
            }
        }
        itemOther.removePlayerHeadValue();
        item.removePlayerHeadValue();
        return true;
    }
}
