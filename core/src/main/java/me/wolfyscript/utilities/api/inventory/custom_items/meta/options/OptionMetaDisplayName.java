package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.util.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OptionMetaDisplayName extends SimpleMetaOption {

    public static final Creator<OptionMetaDisplayName> CREATOR = new Creator<OptionMetaDisplayName>() {
        @Override
        public OptionMetaDisplayName create(CustomItem customItem) {
            return new OptionMetaDisplayName();
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("display_name");
        }
    };

    public OptionMetaDisplayName() {
        super();
    }

    @Override
    public boolean check(CustomItem customItem, ItemMeta thatMeta, ItemStack thatItem) {
        if (super.check(customItem, thatMeta, thatItem)) return true;
        if (customItem.getItemMeta().hasDisplayName()) {
            return thatMeta.hasDisplayName() && customItem.getItemMeta().getDisplayName().equals(thatMeta.getDisplayName());
        } else return !thatMeta.hasDisplayName();
    }
}
