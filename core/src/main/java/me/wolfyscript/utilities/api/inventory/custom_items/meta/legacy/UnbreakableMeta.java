package me.wolfyscript.utilities.api.inventory.custom_items.meta.legacy;


import me.wolfyscript.utilities.api.inventory.custom_items.meta.LegacyMetaSettings;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;
import org.bukkit.inventory.meta.ItemMeta;

public class UnbreakableMeta extends Meta {

    public UnbreakableMeta() {
        setOption(LegacyMetaSettings.Option.EXACT);
        setAvailableOptions(LegacyMetaSettings.Option.EXACT, LegacyMetaSettings.Option.IGNORE);
    }

    @Override
    public boolean check(ItemBuilder itemOther, ItemBuilder item) {
        ItemMeta meta1 = itemOther.getItemMeta();
        ItemMeta meta2 = item.getItemMeta();
        if (option.equals(LegacyMetaSettings.Option.IGNORE)) {
            meta1.setUnbreakable(false);
            meta2.setUnbreakable(false);
            itemOther.setItemMeta(meta1);
            item.setItemMeta(meta2);
        }
        return true;
    }
}
