package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.util.NamespacedKey;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;

public class OptionMetaDisplayName extends SimpleMetaOption {

    public static final Creator<OptionMetaDisplayName> CREATOR = new Creator<OptionMetaDisplayName>() {
        @Override
        public OptionMetaDisplayName create(CustomItem customItem) {
            return new OptionMetaDisplayName(customItem);
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("display_name");
        }
    };

    public OptionMetaDisplayName(CustomItem customItem) {
        super(customItem);
    }

    @Override
    public boolean check(CustomItem itemThis, ItemBuilder itemThat) {
        if (!option.equals(SimpleSetting.IGNORE)) {
            if (itemThis.getItemMeta().hasDisplayName()) {
                if (!itemThat.getItemMeta().hasDisplayName() || !itemThis.getItemMeta().getDisplayName().equals(itemThat.getItemMeta().getDisplayName())) {
                    return false;
                }
            } else if (itemThat.getItemMeta().hasDisplayName()) {
                return false;
            }
        }
        return false;
    }
}
