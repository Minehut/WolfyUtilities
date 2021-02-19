package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.util.NamespacedKey;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;

public class OptionMetaPersistentDataContainer extends SimpleMetaOption {

    public static final Creator<OptionMetaPersistentDataContainer> CREATOR = new Creator<OptionMetaPersistentDataContainer>() {
        @Override
        public OptionMetaPersistentDataContainer create(CustomItem customItem) {
            return new OptionMetaPersistentDataContainer(customItem);
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("persistent_data");
        }
    };

    public OptionMetaPersistentDataContainer(CustomItem customItem) {
        super(customItem);
    }

    @Override
    public boolean check(CustomItem itemThis, ItemBuilder itemThat) {
        if (!option.equals(SimpleSetting.IGNORE)) {
            return itemThis.getItemMeta().getPersistentDataContainer().equals(itemThat.getItemMeta().getPersistentDataContainer());
        }
        return false;
    }
}
