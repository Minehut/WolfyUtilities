package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.util.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OptionMetaPersistentDataContainer extends SimpleMetaOption {

    public static final Creator<OptionMetaPersistentDataContainer> CREATOR = new Creator<OptionMetaPersistentDataContainer>() {
        @Override
        public OptionMetaPersistentDataContainer create(CustomItem customItem) {
            return new OptionMetaPersistentDataContainer();
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("persistent_data");
        }
    };

    public OptionMetaPersistentDataContainer() {
        super();
    }

    @Override
    public boolean check(CustomItem customItem, ItemMeta thatMeta, ItemStack thatItem) {
        if (super.check(customItem, thatMeta, thatItem)) return true;
        return customItem.getItemMeta().getPersistentDataContainer().equals(thatMeta.getPersistentDataContainer());
    }
}
