package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.util.NamespacedKey;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;

public class OptionMetaCustomModelData extends SimpleMetaOption {

    public static final Creator<OptionMetaCustomModelData> CREATOR = new Creator<OptionMetaCustomModelData>() {
        @Override
        public OptionMetaCustomModelData create(CustomItem customItem) {
            return new OptionMetaCustomModelData(customItem);
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("custom_model_data");
        }
    };

    public OptionMetaCustomModelData(CustomItem customItem) {
        super(customItem);
    }

    @Override
    public boolean check(CustomItem itemThis, ItemBuilder itemThat) {
        if (!option.equals(SimpleSetting.IGNORE)) {
            if (itemThis.getItemMeta().hasCustomModelData()) {
                if (!itemThat.getItemMeta().hasCustomModelData() || itemThis.getItemMeta().getCustomModelData() != itemThat.getItemMeta().getCustomModelData()) {
                    return false;
                }
            } else if (itemThat.getItemMeta().hasCustomModelData()) {
                return false;
            }
        }
        return false;
    }
}
