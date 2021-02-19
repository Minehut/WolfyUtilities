package me.wolfyscript.utilities.api.inventory.custom_items.meta;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.util.NamespacedKey;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;

import java.util.Map;

public class SettingsMetaItem {

    private MetaOption displayName;
    private MetaOption locName;
    private MetaOption lore;
    private MetaOption customModelData;
    private MetaOption enchantments;
    private MetaOption repairCost;
    private MetaOption attributes;
    private MetaOption hideFlags;
    private MetaOption unbreakable;
    private MetaOption damage;
    private MetaOption blockData;
    private MetaOption customBukkitTags;
    private MetaOption customDurability;
    private Map<NamespacedKey, MetaOption> customOptions;


    public boolean check(CustomItem thisItem, ItemBuilder thatItem) {
        //TODO: WIP new Metadata check system.
        return false;
    }
}
