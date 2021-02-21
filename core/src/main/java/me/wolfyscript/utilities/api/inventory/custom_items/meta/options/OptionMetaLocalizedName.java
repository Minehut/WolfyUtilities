package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.util.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OptionMetaLocalizedName extends SimpleMetaOption {

    public static final Creator<OptionMetaLocalizedName> CREATOR = new Creator<OptionMetaLocalizedName>() {
        @Override
        public OptionMetaLocalizedName create(CustomItem customItem) {
            return new OptionMetaLocalizedName();
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("localized_name");
        }
    };


    public OptionMetaLocalizedName() {
        super();
    }

    @Override
    public boolean check(CustomItem customItem, ItemMeta thatMeta, ItemStack thatItem) {
        if (super.check(customItem, thatMeta, thatItem)) return true;
        if (customItem.getItemMeta().hasLocalizedName()) {
            return thatMeta.hasLocalizedName() && customItem.getItemMeta().getLocalizedName().equals(thatMeta.getLocalizedName());
        } else return !thatMeta.hasLocalizedName();
    }
}
