package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import com.google.common.collect.Multimap;
import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.util.NamespacedKey;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;

import java.util.Iterator;
import java.util.Map;

public class OptionMetaAttributes extends SimpleMetaOption {

    public static Creator<OptionMetaAttributes> CREATOR = new Creator<OptionMetaAttributes>() {
        @Override
        public OptionMetaAttributes create(CustomItem customItem) {
            return new OptionMetaAttributes(customItem);
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("attributes");
        }
    };

    public OptionMetaAttributes(CustomItem customItem) {
        super(customItem);
    }

    private static boolean compareModifiers(Multimap<Attribute, AttributeModifier> first, Multimap<Attribute, AttributeModifier> second) {
        if (first != null && second != null) {
            Iterator<Map.Entry<Attribute, AttributeModifier>> iterator = first.entries().iterator();
            Map.Entry<Attribute, AttributeModifier> entry;
            while (iterator.hasNext()) {
                entry = iterator.next();
                if (!second.containsEntry(entry.getKey(), entry.getValue())) {
                    return false;
                }
            }
            iterator = second.entries().iterator();
            while (iterator.hasNext()) {
                entry = iterator.next();
                if (!first.containsEntry(entry.getKey(), entry.getValue())) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean check(CustomItem itemThis, ItemBuilder itemThat) {
        if (!option.equals(SimpleSetting.IGNORE)) {
            if (itemThis.getItemMeta().hasAttributeModifiers()) {
                if (!itemThat.getItemMeta().hasAttributeModifiers() || !compareModifiers(itemThis.getItemMeta().getAttributeModifiers(), itemThat.getItemMeta().getAttributeModifiers())) {
                    return false;
                }
            } else if (itemThat.getItemMeta().hasAttributeModifiers()) {
                return false;
            }
        }
        return false;
    }
}
