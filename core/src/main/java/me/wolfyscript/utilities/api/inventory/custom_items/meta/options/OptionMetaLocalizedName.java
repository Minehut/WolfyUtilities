package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.util.NamespacedKey;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;

public class OptionMetaLocalizedName extends SimpleMetaOption {

    public static final Creator<OptionMetaLocalizedName> CREATOR = new Creator<OptionMetaLocalizedName>() {
        @Override
        public OptionMetaLocalizedName create(CustomItem customItem) {
            return null;
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("localized_name");
        }
    };

    private SimpleSetting option;

    public OptionMetaLocalizedName(CustomItem customItem) {
        super(customItem);
    }

    @Override
    public boolean check(CustomItem itemThis, ItemBuilder itemThat) {
        if (!option.equals(SimpleSetting.IGNORE)) {
            if (itemThis.getItemMeta().hasLocalizedName()) {
                if (!itemThat.getItemMeta().hasLocalizedName() || !itemThis.getItemMeta().getLocalizedName().equals(itemThat.getItemMeta().getLocalizedName())) {
                    return false;
                }
            } else if (itemThat.getItemMeta().hasLocalizedName()) {
                return false;
            }
        }
        return false;
    }
}
