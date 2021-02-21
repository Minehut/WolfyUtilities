package me.wolfyscript.utilities.api.inventory.custom_items.meta;

import org.bukkit.inventory.ItemStack;

public class SettingsMetaTropicalFishBucket extends SettingsMetaItem {

    private final MetaOption variant;
    private final MetaOption entityTag;

    public SettingsMetaTropicalFishBucket() {
        variant = null;
        entityTag = null;
    }

    @Override
    public boolean check(ItemStack that) {
        return super.check(that);
    }
}
