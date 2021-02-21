package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.util.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OptionMetaEnchants extends SimpleMetaOption {

    public static final Creator<OptionMetaEnchants> CREATOR = new Creator<OptionMetaEnchants>() {
        @Override
        public OptionMetaEnchants create(CustomItem customItem) {
            return new OptionMetaEnchants();
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("lore");
        }
    };

    public OptionMetaEnchants() {
        super();
    }

    @Override
    public boolean check(CustomItem customItem, ItemMeta thatMeta, ItemStack thatItem) {
        if (super.check(customItem, thatMeta, thatItem)) return true;
        if (customItem.getItemMeta().hasEnchants()) {
            return thatMeta.hasEnchants() && customItem.getItemMeta().getEnchants().equals(thatMeta.getEnchants());
        } else return !thatMeta.hasEnchants();
    }
}
