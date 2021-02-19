package me.wolfyscript.utilities.api.inventory.custom_items.meta.options;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.SimpleMetaOption;
import me.wolfyscript.utilities.util.NamespacedKey;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;
import org.bukkit.inventory.meta.Repairable;

public class OptionMetaRepairCost extends SimpleMetaOption {

    public static final Creator<OptionMetaRepairCost> CREATOR = new Creator<OptionMetaRepairCost>() {
        @Override
        public OptionMetaRepairCost create(CustomItem customItem) {
            return new OptionMetaRepairCost(customItem);
        }

        @Override
        public NamespacedKey getNamespacedKey() {
            return NamespacedKey.wolfyutilties("repair_cost");
        }
    };

    public OptionMetaRepairCost(CustomItem customItem) {
        super(customItem);
    }

    @Override
    public boolean check(CustomItem itemThis, ItemBuilder itemThat) {
        if (!option.equals(SimpleSetting.IGNORE)) {
            if (itemThis.getItemMeta() instanceof Repairable && itemThat.getItemMeta() instanceof Repairable) {
                Repairable metaThis = (Repairable) itemThis.getItemMeta();
                Repairable metaThat = (Repairable) itemThat.getItemMeta();
                if (metaThis.hasRepairCost()) {
                    if (!metaThat.hasRepairCost() || metaThis.getRepairCost() != metaThat.getRepairCost()) {
                        return false;
                    }
                } else if (metaThat.hasRepairCost()) {
                    return false;
                }
            }
        }
        return false;
    }
}
