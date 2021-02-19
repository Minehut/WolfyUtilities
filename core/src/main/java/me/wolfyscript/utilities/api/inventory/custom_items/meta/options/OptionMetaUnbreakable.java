package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.util.NamespacedKey;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;

public class OptionMetaUnbreakable extends SimpleMetaOption {

    public static final Creator<OptionMetaUnbreakable> CREATOR = new Creator<OptionMetaUnbreakable>() {
        @Override
        public OptionMetaUnbreakable create(CustomItem customItem) {
            return new OptionMetaUnbreakable(customItem);
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("unbreakable");
        }
    };

    public OptionMetaUnbreakable(CustomItem customItem) {
        super(customItem);
    }

    @Override
    public boolean check(CustomItem itemThis, ItemBuilder itemThat) {
        if (!option.equals(SimpleSetting.IGNORE)) {
            return itemThis.getItemMeta().isUnbreakable() == itemThat.getItemMeta().isUnbreakable();
        }
        return false;
    }
}
