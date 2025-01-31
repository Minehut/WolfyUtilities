package me.wolfyscript.utilities.api.inventory.gui.button.buttons;

import me.wolfyscript.utilities.api.inventory.gui.GuiHandler;
import me.wolfyscript.utilities.api.inventory.gui.button.ButtonAction;
import me.wolfyscript.utilities.api.inventory.gui.button.ButtonRender;
import me.wolfyscript.utilities.api.inventory.gui.button.ButtonState;
import me.wolfyscript.utilities.api.inventory.gui.button.ButtonType;
import me.wolfyscript.utilities.api.inventory.gui.cache.CustomCache;
import me.wolfyscript.utilities.api.nms.inventory.GUIInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This Button acts as a container for Items.
 * It saves the placed in item and can also execute an action on each click.
 *
 * @param <C> The type of the {@link CustomCache}
 */
public class ItemInputButton<C extends CustomCache> extends ActionButton<C> {

    private final Map<GuiHandler<C>, ItemStack> content;

    public ItemInputButton(String id, ButtonState<C> state) {
        super(id, ButtonType.ITEM_SLOT, state);
        this.content = new HashMap<>();
    }

    public ItemInputButton(String id, ItemStack itemStack) {
        this(id, new ButtonState<>(id, itemStack));
    }

    public ItemInputButton(String id, Material material) {
        this(id, new ButtonState<>(id, material));
    }

    public ItemInputButton(String id, ItemStack itemStack, ButtonAction<C> action) {
        this(id, new ButtonState<>(id, itemStack, action));
    }

    public ItemInputButton(String id, ItemStack itemStack, ButtonRender<C> render) {
        this(id, new ButtonState<>(id, itemStack, render));
    }

    public ItemInputButton(String id, ItemStack itemStack, ButtonAction<C> action, ButtonRender<C> render) {
        this(id, new ButtonState<>(id, itemStack, action, render));
    }

    public ItemInputButton(String id, Material material, ButtonAction<C> action) {
        this(id, new ButtonState<>(id, material, action));
    }

    public ItemInputButton(String id, Material material, ButtonRender<C> render) {
        this(id, new ButtonState<>(id, material, render));
    }

    public ItemInputButton(String id, Material material, ButtonAction<C> action, ButtonRender<C> render) {
        this(id, new ButtonState<>(id, material, action, render));
    }

    @Override
    public boolean execute(GuiHandler<C> guiHandler, Player player, GUIInventory<C> inventory, int slot, InventoryInteractEvent event) throws IOException {
        if (!getType().equals(ButtonType.DUMMY) && getState().getAction() != null) {
            return getState().getAction().run(guiHandler.getCustomCache(), guiHandler, player, inventory, slot, event);
        }
        return false;
    }

    @Override
    public void postExecute(GuiHandler<C> guiHandler, Player player, GUIInventory<C> inventory, ItemStack itemStack, int slot, InventoryInteractEvent event) throws IOException {
        content.put(guiHandler, itemStack != null ? itemStack.clone() : new ItemStack(Material.AIR));
        super.postExecute(guiHandler, player, inventory, itemStack, slot, event);
    }

    @Override
    public void preRender(GuiHandler<C> guiHandler, Player player, GUIInventory<C> inventory, ItemStack itemStack, int slot, boolean help) {
        super.preRender(guiHandler, player, inventory, itemStack, slot, help);
    }

    @Override
    public void render(GuiHandler<C> guiHandler, Player player, GUIInventory<C> guiInventory, Inventory inventory, ItemStack itemStack, int slot, boolean help) throws IOException {
        ItemStack item = content.getOrDefault(guiHandler, new ItemStack(Material.AIR));
        HashMap<String, Object> values = new HashMap<>();
        if (getState().getRenderAction() != null) {
            item = getState().getRenderAction().render(values, guiHandler.getCustomCache(), guiHandler, player, guiInventory, item, slot, help);
        }
        inventory.setItem(slot, replaceKeysWithValue(item, values));
    }
}
