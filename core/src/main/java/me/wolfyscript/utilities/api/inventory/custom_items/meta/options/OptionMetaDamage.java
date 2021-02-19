package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.util.NamespacedKey;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;
import org.bukkit.inventory.meta.Damageable;

public class OptionMetaDamage extends SimpleMetaOption {

    public static final Creator<OptionMetaDamage> CREATOR = new Creator<OptionMetaDamage>() {
        @Override
        public OptionMetaDamage create(CustomItem customItem) {
            return new OptionMetaDamage(customItem);
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("repair_cost");
        }
    };

    public OptionMetaDamage(CustomItem customItem) {
        super(customItem);
    }


    @Override
    public boolean check(CustomItem itemThis, ItemBuilder itemThat) {
        if (!option.equals(SimpleSetting.IGNORE)) {
            if (itemThis.getItemMeta() instanceof Damageable && itemThat.getItemMeta() instanceof Damageable) {
                Damageable metaThis = (Damageable) itemThis.getItemMeta();
                Damageable metaThat = (Damageable) itemThat.getItemMeta();
                if (metaThis.hasDamage()) {
                    if (!metaThat.hasDamage() || metaThis.getDamage() != metaThat.getDamage()) {
                        return false;
                    }
                } else if (metaThat.hasDamage()) {
                    return false;
                }
            }
        }
        return false;
    }
}
