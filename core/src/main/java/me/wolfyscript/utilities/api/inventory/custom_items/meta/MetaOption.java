package me.wolfyscript.utilities.api.inventory.custom_items.meta;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.util.Keyed;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;

public abstract class MetaOption {

    public MetaOption(CustomItem customItem) {

    }

    abstract public boolean check(CustomItem itemThis, ItemBuilder itemThat);

    public enum SimpleSetting {

        EXACT,
        IGNORE,
        HIGHER,
        LOWER,
        HIGHER_EQUALS,
        LOWER_EQUALS,
        HIGHER_LOWER,
        BOUNDS

    }

    public interface Creator<M extends MetaOption> extends Keyed {

        M create(CustomItem customItem);

    }
}
