package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.util.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;

public class OptionMetaRepairCost extends SimpleMetaOption {

    public static final Creator<OptionMetaRepairCost> CREATOR = new Creator<OptionMetaRepairCost>() {
        @Override
        public OptionMetaRepairCost create(CustomItem customItem) {
            return new OptionMetaRepairCost();
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("repair_cost");
        }
    };

    public OptionMetaRepairCost() {
        super();
    }

    @Override
    public boolean check(CustomItem customItem, ItemMeta thatMeta, ItemStack thatItem) {
        if (super.check(customItem, thatMeta, thatItem)) return true;
        Repairable metaThis = (Repairable) customItem.getItemMeta();
        Repairable metaThat = (Repairable) thatMeta;
        if (metaThis.hasRepairCost()) {
            return metaThat.hasRepairCost() && metaThis.getRepairCost() == metaThat.getRepairCost();
        } else return !metaThat.hasRepairCost();
    }
}
