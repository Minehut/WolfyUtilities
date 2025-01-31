package me.wolfyscript.utilities.api.nms.v1_14_R1.inventory;

import me.wolfyscript.utilities.api.inventory.gui.GuiHandler;
import me.wolfyscript.utilities.api.inventory.gui.GuiWindow;
import me.wolfyscript.utilities.api.inventory.gui.cache.CustomCache;
import me.wolfyscript.utilities.api.nms.inventory.GUIInventory;
import net.minecraft.server.v1_14_R1.IInventory;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftInventory;

public class GUIInventoryImpl<C extends CustomCache> extends CraftInventory implements GUIInventory<C> {

    private final GuiWindow<C> window;
    private final GuiHandler<C> guiHandler;

    public GUIInventoryImpl(GuiHandler<C> guiHandler, GuiWindow<C> window, IInventory inventory) {
        super(inventory);
        this.guiHandler = guiHandler;
        this.window = window;
    }

    @Override
    public GuiWindow<C> getWindow() {
        return window;
    }

    @Override
    public GuiHandler<C> getGuiHandler() {
        return guiHandler;
    }
}
