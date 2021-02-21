package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.util.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class OptionMetaDamage extends SimpleMetaOption {

    public static final Creator<OptionMetaDamage> CREATOR = new Creator<OptionMetaDamage>() {
        @Override
        public OptionMetaDamage create(CustomItem customItem) {
            return new OptionMetaDamage();
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("repair_cost");
        }
    };

    public OptionMetaDamage() {
        super();
    }

    @Override
    public boolean check(CustomItem customItem, ItemMeta thatMeta, ItemStack thatItem) {
        if (super.check(customItem, thatMeta, thatItem)) return true;
        Damageable metaThis = (Damageable) customItem.getItemMeta();
        Damageable metaThat = (Damageable) thatMeta;
        if (metaThis.hasDamage()) {
            return metaThat.hasDamage() && metaThis.getDamage() == metaThat.getDamage();
        } else return !metaThat.hasDamage();
    }
}
