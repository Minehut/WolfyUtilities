package me.wolfyscript.utilities.api.inventory.custom_items.meta.legacy;


import me.wolfyscript.utilities.api.inventory.custom_items.meta.LegacyMetaSettings;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;

public class EnchantMeta extends Meta {

    public EnchantMeta() {
        setOption(LegacyMetaSettings.Option.EXACT);
        setAvailableOptions(LegacyMetaSettings.Option.EXACT, LegacyMetaSettings.Option.IGNORE);
    }

    @Override
    public boolean check(ItemBuilder itemOther, ItemBuilder item) {
        if (option.equals(LegacyMetaSettings.Option.IGNORE)) {
            itemOther.getItemMeta().getEnchants().keySet().forEach(itemOther::removeEnchantment);
            item.getItemMeta().getEnchants().keySet().forEach(item::removeEnchantment);
        }
        return true;
    }
}
