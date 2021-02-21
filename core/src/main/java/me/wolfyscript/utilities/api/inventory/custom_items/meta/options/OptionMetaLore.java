package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.util.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class OptionMetaLore extends SimpleMetaOption {

    public static final Creator<OptionMetaLore> CREATOR = new Creator<OptionMetaLore>() {
        @Override
        public OptionMetaLore create(CustomItem customItem) {
            return new OptionMetaLore();
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("lore");
        }
    };

    public OptionMetaLore() {
        super();
    }

    @Override
    public boolean check(CustomItem customItem, ItemMeta thatMeta, ItemStack thatItem) {
        if (super.check(customItem, thatMeta, thatItem)) return true;
        return Objects.equals(customItem.getItemMeta().getLore(), thatMeta.getLore());
    }
}
