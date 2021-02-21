package me.wolfyscript.utilities.api.inventory.custom_items.meta;

import com.fasterxml.jackson.annotation.JacksonInject;
import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.options.*;
import me.wolfyscript.utilities.util.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class SettingsMetaItem {

    private final MetaOption displayName;
    private final MetaOption locName;
    private final MetaOption lore;
    private final MetaOption customModelData;
    private final MetaOption enchantments;
    private final MetaOption repairCost;
    private final MetaOption attributes;
    private final MetaOption hideFlags;
    private final MetaOption unbreakable;
    private final MetaOption damage;
    private final MetaOption blockData;
    private final MetaOption customBukkitTags;
    private final MetaOption customDurability;
    private final Map<NamespacedKey, MetaOption> customOptions;
    private final Set<MetaOption> checkList;
    @JacksonInject
    protected CustomItem customItem;

    public SettingsMetaItem() {
        this.displayName = new OptionMetaDisplayName();
        this.locName = new OptionMetaLocalizedName();
        this.lore = new OptionMetaLore();
        this.customModelData = new OptionMetaCustomModelData();
        this.enchantments = new OptionMetaEnchants();
        this.repairCost = new OptionMetaRepairCost();
        this.attributes = new OptionMetaAttributes();
        this.hideFlags = new OptionMetaHideFlag();
        this.unbreakable = new OptionMetaUnbreakable();
        this.damage = new OptionMetaDamage();
        this.blockData = new OptionMetaBlockData();
        this.customBukkitTags = new OptionMetaPersistentDataContainer();
        this.customDurability = new OptionMetaCustomDurability();
        this.customOptions = new HashMap<>();

        //Add the MetaOptions for easier checking later.
        checkList = new HashSet<>(
                Arrays.asList(
                        displayName,
                        locName,
                        lore,
                        customModelData,
                        enchantments,
                        repairCost,
                        attributes,
                        hideFlags,
                        unbreakable,
                        damage,
                        blockData,
                        customBukkitTags,
                        customDurability
                )
        );
    }

    public SettingsMetaItem(CustomItem customItem) {
        this();
        this.customItem = customItem;
    }

    /*
    Perhaps compare it to other CustomItem or directly to the SettingsMetaItem.
    This would be the same as bukkit does it with their CraftMetaItem etc.

    For that I would have to load the values of all the available meta on CustomItem creation.
    Not sure if that is more efficient than just comparing the values of the ItemStacks to the already existing CustomItem.

    Or do both?! to check which one performs better.
     */
    public boolean check(ItemStack that) {
        ItemMeta thatMeta = that.getItemMeta();
        if (checkList.stream().allMatch(metaOption -> metaOption.check(this.customItem, thatMeta, that))) {
            return this.customOptions.values().parallelStream().allMatch(metaOption -> metaOption.check(this.customItem, thatMeta, that));
        }
        return false;
    }

    public MetaOption getDisplayName() {
        return displayName;
    }

    public MetaOption getLocName() {
        return locName;
    }

    public MetaOption getLore() {
        return lore;
    }

    public MetaOption getCustomModelData() {
        return customModelData;
    }

    public MetaOption getEnchantments() {
        return enchantments;
    }

    public MetaOption getRepairCost() {
        return repairCost;
    }

    public MetaOption getAttributes() {
        return attributes;
    }

    public MetaOption getHideFlags() {
        return hideFlags;
    }

    public MetaOption getUnbreakable() {
        return unbreakable;
    }

    public MetaOption getDamage() {
        return damage;
    }

    public MetaOption getBlockData() {
        return blockData;
    }

    public MetaOption getCustomBukkitTags() {
        return customBukkitTags;
    }

    public MetaOption getCustomDurability() {
        return customDurability;
    }
}
