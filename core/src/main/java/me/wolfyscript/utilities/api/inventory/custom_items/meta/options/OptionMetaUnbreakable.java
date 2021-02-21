package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.util.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OptionMetaUnbreakable extends SimpleMetaOption {

    public static final Creator<OptionMetaUnbreakable> CREATOR = new Creator<OptionMetaUnbreakable>() {
        @Override
        public OptionMetaUnbreakable create(CustomItem customItem) {
            return new OptionMetaUnbreakable();
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("unbreakable");
        }
    };

    public OptionMetaUnbreakable() {
        super();
    }

    @Override
    public boolean check(CustomItem customItem, ItemMeta thatMeta, ItemStack thatItem) {
        if (super.check(customItem, thatMeta, thatItem)) return true;
        return customItem.getItemMeta().isUnbreakable() == thatMeta.isUnbreakable();
    }
}
