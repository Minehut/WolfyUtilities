package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.util.NamespacedKey;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OptionMetaCustomDurability extends SimpleMetaOption {

    public static final Creator<OptionMetaCustomDurability> CREATOR = new Creator<OptionMetaCustomDurability>() {
        @Override
        public OptionMetaCustomDurability create(CustomItem customItem) {
            return new OptionMetaCustomDurability();
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("repair_cost");
        }
    };

    public OptionMetaCustomDurability() {
        super();
    }

    @Override
    public boolean check(CustomItem customItem, ItemMeta thatMeta, ItemStack thatItem) {
        if (super.check(customItem, thatMeta, thatItem)) return true;
        ItemBuilder that = new ItemBuilder(thatItem);
        if (customItem.hasCustomDurability()) {
            if (!that.hasCustomDurability() || customItem.getCustomDurability() != that.getCustomDurability()) {
                return false;
            }
        } else if (that.hasCustomDurability()) {
            return false;
        }
        if (customItem.hasCustomDamage() && that.hasCustomDamage()) {
            return that.hasCustomDamage() && customItem.getCustomDamage() == that.getCustomDamage();
        } else return !that.hasCustomDamage();
    }
}
