package me.wolfyscript.utilities.api.inventory.custom_items.meta.legacy;


import com.google.common.collect.Multimap;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.LegacyMetaSettings;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.meta.ItemMeta;

public class AttributesModifiersMeta extends Meta {

    public AttributesModifiersMeta() {
        setOption(LegacyMetaSettings.Option.EXACT);
        setAvailableOptions(LegacyMetaSettings.Option.EXACT, LegacyMetaSettings.Option.IGNORE);
    }

    @Override
    public boolean check(ItemBuilder itemOther, ItemBuilder item) {
        ItemMeta metaOther = itemOther.getItemMeta();
        ItemMeta meta = item.getItemMeta();
        if (option.equals(LegacyMetaSettings.Option.IGNORE)) {
            if (metaOther.hasAttributeModifiers()) {
                Multimap<Attribute, AttributeModifier> modifiers = metaOther.getAttributeModifiers();
                modifiers.keySet().forEach(metaOther::removeAttributeModifier);
            }
            if (meta.hasAttributeModifiers()) {
                Multimap<Attribute, AttributeModifier> modifiers = metaOther.getAttributeModifiers();
                modifiers.keySet().forEach(metaOther::removeAttributeModifier);
            }
        }
        itemOther.setItemMeta(metaOther);
        item.setItemMeta(meta);
        return true;
    }
}
