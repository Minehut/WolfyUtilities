package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.api.inventory.custom_items.references.APIReference;
import me.wolfyscript.utilities.util.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OptionMetaAPIReference extends SimpleMetaOption {

    public static final Creator<OptionMetaAPIReference> CREATOR = new Creator<OptionMetaAPIReference>() {
        @Override
        public OptionMetaAPIReference create(CustomItem customItem) {
            return new OptionMetaAPIReference();
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("api_reference");
        }
    };

    public OptionMetaAPIReference() {
        super();
    }

    @Override
    public boolean check(CustomItem customItem, ItemMeta thatMeta, ItemStack thatItem) {
        if (super.check(customItem, thatMeta, thatItem)) return true;
        APIReference apiReference = customItem.getApiReference();
        CustomItem thatCustomItem = CustomItem.getReferenceByItemStack(thatItem);
        if (!apiReference.getClass().isInstance(thatCustomItem.getApiReference())) {
            return false;
        }
        if (customItem.getItemMeta().hasCustomModelData()) {
            return thatMeta.hasCustomModelData() && customItem.getItemMeta().getCustomModelData() == thatMeta.getCustomModelData();
        } else return !thatMeta.hasCustomModelData();
    }
}
