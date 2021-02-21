package me.wolfyscript.utilities.api.inventory.custom_items.meta;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.util.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class SettingsMetaBook extends SettingsMetaItem {

    private final MetaOption title;
    private final MetaOption author;
    private final MetaOption pages;
    private final MetaOption resolved;
    private final MetaOption generation;

    private Map<NamespacedKey, MetaOption> customBookOptions;

    public SettingsMetaBook(CustomItem customItem) {
        super(customItem);
        title = null;
        author = null;
        pages = null;
        resolved = null;
        generation = null;
    }

    @Override
    public boolean check(ItemStack that) {
        if (super.check(that)) {

        }
        return false;
    }
}
