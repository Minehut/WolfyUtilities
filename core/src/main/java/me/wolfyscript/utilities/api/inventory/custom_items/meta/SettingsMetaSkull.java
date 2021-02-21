package me.wolfyscript.utilities.api.inventory.custom_items.meta;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.options.OptionMetaSkullOwner;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.options.OptionMetaSkullProfile;
import me.wolfyscript.utilities.util.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class SettingsMetaSkull extends SettingsMetaItem {

    private final OptionMetaSkullProfile skullProfile;
    private final OptionMetaSkullOwner skullOwner;

    private final Map<NamespacedKey, MetaOption> customSkullOptions;

    public SettingsMetaSkull(CustomItem customItem) {
        super(customItem);
        this.skullOwner = new OptionMetaSkullOwner(customItem);
        this.skullProfile = new OptionMetaSkullProfile(customItem);
        this.customSkullOptions = new HashMap<>();
    }

    @Override
    public boolean check(ItemStack that) {
        if (super.check(that)) {
            if (skullOwner.check(, that.getItemMeta(), that) && skullProfile.check(, that.getItemMeta(), that)) {
                return customSkullOptions.values().stream().allMatch(metaOption -> metaOption.check(, that.getItemMeta(), that));
            }
        }
        return false;
    }

    public OptionMetaSkullProfile getSkullProfile() {
        return skullProfile;
    }

    public OptionMetaSkullOwner getSkullOwner() {
        return skullOwner;
    }
}
