package me.wolfyscript.utilities.api.inventory.custom_items.meta.legacy;

import me.wolfyscript.utilities.api.inventory.custom_items.meta.LegacyMetaSettings;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomDamageMeta extends Meta {

    public CustomDamageMeta() {
        setOption(LegacyMetaSettings.Option.EXACT);
        setAvailableOptions(LegacyMetaSettings.Option.EXACT, LegacyMetaSettings.Option.IGNORE, LegacyMetaSettings.Option.HIGHER, LegacyMetaSettings.Option.LOWER);
    }

    @Override
    public boolean check(ItemBuilder itemOther, ItemBuilder item) {
        boolean meta0 = item.hasCustomDurability();
        boolean meta1 = itemOther.hasCustomDurability();
        ItemMeta metaOther = itemOther.getItemMeta();
        ItemMeta meta = item.getItemMeta();
        if (meta0 && meta1) {
            switch (option) {
                case EXACT:
                    return itemOther.getCustomDamage() == item.getCustomDamage();
                case IGNORE:
                    itemOther.setCustomDamage(0);
                    item.setCustomDamage(0);
                    ((Damageable)metaOther).setDamage(0);
                    ((Damageable)meta).setDamage(0);
                    itemOther.setItemMeta(metaOther);
                    item.setItemMeta(meta);
                    return true;
                case LOWER:
                    return itemOther.getCustomDamage() < item.getCustomDamage();
                case HIGHER:
                    return itemOther.getCustomDamage() > item.getCustomDamage();
            }
            return true;
        } else return !meta0 && !meta1;
    }
}
