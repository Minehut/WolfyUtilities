package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.util.NamespacedKey;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;

import java.util.Objects;

public class OptionMetaLore extends SimpleMetaOption {

    public static final Creator<OptionMetaLore> CREATOR = new Creator<OptionMetaLore>() {
        @Override
        public OptionMetaLore create(CustomItem customItem) {
            return new OptionMetaLore(customItem);
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("lore");
        }
    };

    public OptionMetaLore(CustomItem customItem) {
        super(customItem);
    }

    @Override
    public boolean check(CustomItem itemThis, ItemBuilder itemThat) {
        if (!option.equals(SimpleSetting.IGNORE)) {
            return Objects.equals(itemThis.getItemMeta().getLore(), itemThat.getItemMeta().getLore());
        }
        return false;
    }
}
