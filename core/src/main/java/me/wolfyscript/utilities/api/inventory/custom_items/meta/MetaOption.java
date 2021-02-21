package me.wolfyscript.utilities.api.inventory.custom_items.meta;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.util.Keyed;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class MetaOption {

    abstract public boolean check(CustomItem customItem, ItemMeta thatItemMeta, ItemStack thatItem);

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
