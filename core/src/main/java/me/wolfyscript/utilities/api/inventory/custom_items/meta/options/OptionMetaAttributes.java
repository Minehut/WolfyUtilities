package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import com.google.common.collect.Multimap;
import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.util.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Iterator;
import java.util.Map;

public class OptionMetaAttributes extends SimpleMetaOption {

    public static Creator<OptionMetaAttributes> CREATOR = new Creator<OptionMetaAttributes>() {
        @Override
        public OptionMetaAttributes create(CustomItem customItem) {
            return new OptionMetaAttributes();
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("attributes");
        }
    };

    public OptionMetaAttributes() {
        super();
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
    public boolean check(CustomItem customItem, ItemMeta thatMeta, ItemStack itemThat) {
        if (super.check(customItem, thatMeta, itemThat)) return true;
        if (customItem.getItemMeta().hasAttributeModifiers()) {
            return thatMeta.hasAttributeModifiers() && compareModifiers(customItem.getItemMeta().getAttributeModifiers(), thatMeta.getAttributeModifiers());
        } else return !thatMeta.hasAttributeModifiers();
    }
}
