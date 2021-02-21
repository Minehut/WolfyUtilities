package me.wolfyscript.utilities.api.inventory.custom_items.meta;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class SimpleMetaOption extends MetaOption {

    protected MetaOption.SimpleSetting option;

    @Override
    public boolean check(CustomItem customItem, ItemMeta thatMeta, ItemStack thatItem) {
        return option.equals(SimpleSetting.IGNORE);
    }

    public SimpleSetting getOption() {
        return option;
    }

    public void setOption(SimpleSetting option) {
        this.option = option;
    }
}
