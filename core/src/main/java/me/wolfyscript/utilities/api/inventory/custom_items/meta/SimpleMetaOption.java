package me.wolfyscript.utilities.api.inventory.custom_items.meta;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;

public abstract class SimpleMetaOption extends MetaOption {

    protected MetaOption.SimpleSetting option;

    public SimpleMetaOption(CustomItem customItem) {
        super(customItem);
    }

    public SimpleSetting getOption() {
        return option;
    }

    public void setOption(SimpleSetting option) {
        this.option = option;
    }
}
