package me.wolfyscript.utilities.api.inventory.custom_items.meta;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.util.NamespacedKey;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;

import java.util.Map;

public class SettingsMetaPotion {

    private MetaOption skullProfile;
    private MetaOption skullOwner;

    private Map<NamespacedKey, MetaOption> customOptions;


    public boolean check(CustomItem thisItem, ItemBuilder thatItem) {
        //TODO: WIP new Metadata check system.
        return false;
    }
}
