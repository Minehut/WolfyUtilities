package me.wolfyscript.utilities.api.inventory.custom_items.meta.legacy;


import me.wolfyscript.utilities.api.inventory.custom_items.meta.LegacyMetaSettings;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;
import org.bukkit.inventory.meta.ItemMeta;

public class PotionMeta extends Meta {

    public PotionMeta() {
        setOption(LegacyMetaSettings.Option.EXACT);
        setAvailableOptions(LegacyMetaSettings.Option.EXACT, LegacyMetaSettings.Option.IGNORE);
    }

    @Override
    public boolean check(ItemBuilder itemOther, ItemBuilder item) {
        ItemMeta meta1 = itemOther.getItemMeta();
        ItemMeta meta2 = item.getItemMeta();

        if (meta1 instanceof org.bukkit.inventory.meta.PotionMeta && meta2 instanceof org.bukkit.inventory.meta.PotionMeta) {
            if (option.equals(LegacyMetaSettings.Option.IGNORE)) {
                ((org.bukkit.inventory.meta.PotionMeta) meta1).clearCustomEffects();
                ((org.bukkit.inventory.meta.PotionMeta) meta2).clearCustomEffects();
                itemOther.setItemMeta(meta1);
                item.setItemMeta(meta2);
            }
        }
        return true;
    }
}
