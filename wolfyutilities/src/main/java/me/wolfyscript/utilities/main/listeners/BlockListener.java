package me.wolfyscript.utilities.main.listeners;

import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.main.WUPlugin;
import me.wolfyscript.utilities.util.NamespacedKey;
import me.wolfyscript.utilities.util.Registry;
import me.wolfyscript.utilities.util.events.CustomItemBreakEvent;
import me.wolfyscript.utilities.util.events.CustomItemPlaceEvent;
import me.wolfyscript.utilities.util.inventory.ItemUtils;
import me.wolfyscript.utilities.util.world.WorldUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Container;
import org.bukkit.block.ShulkerBox;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.type.Bed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class BlockListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent event) {
        if (!event.isCancelled()) {
            Block block = event.getBlock();
            CustomItem storedItem = WorldUtils.getWorldCustomItemStore().getCustomItem(block.getLocation());
            if (!ItemUtils.isAirOrNull(storedItem)) {
                event.setDropItems(false);
                CustomItemBreakEvent event1 = new CustomItemBreakEvent(storedItem, event);
                Bukkit.getPluginManager().callEvent(event1);
                event.setCancelled(event1.isCancelled());
                storedItem = event1.getCustomItem();
                if (!event1.isCancelled() && !ItemUtils.isAirOrNull(storedItem)) {
                    ItemStack result = dropItems(block, storedItem);
                    if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE) && event1.isDropItems()) {
                        block.getWorld().dropItemNaturally(block.getLocation(), result);
                    }
                    removeMultiBlockItems(block);
                }
            }
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        dropItemsOnExplosion(event.isCancelled(), event.blockList());
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event) {
        dropItemsOnExplosion(event.isCancelled(), event.blockList());
    }

    private void dropItemsOnExplosion(boolean cancelled, List<Block> blocks) {
        if (!cancelled) {
            Iterator<Block> blockList = blocks.iterator();
            while (blockList.hasNext()) {
                Block block = blockList.next();
                CustomItem storedItem = WorldUtils.getWorldCustomItemStore().getCustomItem(block.getLocation());
                if (!ItemUtils.isAirOrNull(storedItem)) {
                    blockList.remove();
                    block.setType(Material.AIR);
                    block.getWorld().dropItemNaturally(block.getLocation(), dropItems(block, storedItem));
                    removeMultiBlockItems(block);
                }
            }
        }
    }

    private ItemStack dropItems(Block block, CustomItem storedItem) {
        ItemStack result = storedItem.create();
        WorldUtils.getWorldCustomItemStore().remove(block.getLocation());
        if (block.getState() instanceof Container) {
            Container container = (Container) block.getState();
            BlockStateMeta blockStateMeta = (BlockStateMeta) result.getItemMeta();
            if (container instanceof ShulkerBox) {
                ShulkerBox shulkerBox = (ShulkerBox) blockStateMeta.getBlockState();
                shulkerBox.getInventory().setContents(container.getInventory().getContents());
                blockStateMeta.setBlockState(shulkerBox);
            } else {
                Container itemContainer = (Container) blockStateMeta.getBlockState();
                itemContainer.getInventory().clear();
                blockStateMeta.setBlockState(itemContainer);
            }
            result.setItemMeta(blockStateMeta);
        }
        return result;
    }

    private void removeMultiBlockItems(Block block) {
        if (block.getBlockData() instanceof Bisected) {
            WorldUtils.getWorldCustomItemStore().remove(((Bisected) block.getBlockData()).getHalf().equals(Bisected.Half.BOTTOM) ? block.getLocation().add(0, 1, 0) : block.getLocation().subtract(0, 1, 0));
        } else if (block.getBlockData() instanceof Bed) {
            Bed bed = (Bed) block.getBlockData();
            WorldUtils.getWorldCustomItemStore().remove(block.getLocation().add(bed.getFacing().getDirection()));
        }
    }

    /*
    Called when liquid flows or when an Dragon Egg teleports.
    This Listener only listens for the Dragon Egg
    */
    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        if (!event.isCancelled()) {
            Block block = event.getBlock();
            CustomItem storedItem = WorldUtils.getWorldCustomItemStore().getCustomItem(block.getLocation());
            if (!ItemUtils.isAirOrNull(storedItem)) {
                WorldUtils.getWorldCustomItemStore().remove(block.getLocation());
                WorldUtils.getWorldCustomItemStore().store(event.getToBlock().getLocation(), storedItem);
            }
        }
    }

    /*
     * Piston Events to make sure the position of CustomItems is updated correctly.
     *
     */

    @EventHandler
    public void onPistonExtend(BlockPistonExtendEvent event) {
        if (!event.isCancelled()) {
            updatePistonBlocks(event.getBlocks(), event.getDirection());
        }
    }

    @EventHandler
    public void onPistonRetract(BlockPistonRetractEvent event) {
        if (!event.isCancelled() && event.isSticky()) {
            updatePistonBlocks(event.getBlocks(), event.getDirection());
        }
    }

    private void updatePistonBlocks(List<Block> blocks, BlockFace direction) {
        HashMap<Location, CustomItem> newLocations = new HashMap<>();
        blocks.forEach(block -> {
            CustomItem storedItem = WorldUtils.getWorldCustomItemStore().getCustomItem(block.getLocation());
            if (storedItem != null) {
                WorldUtils.getWorldCustomItemStore().remove(block.getLocation());
                newLocations.put(block.getRelative(direction).getLocation(), storedItem);
            }
        });
        newLocations.forEach((location, customItem) -> WorldUtils.getWorldCustomItemStore().store(location, customItem));
    }

    /*
     * Update the CustomItems if they disappear because of natural causes.
     */

    /*
    Unregisters the placed CustomItem when the block is burned by fire.
     */
    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        if (!event.isCancelled()) {
            Block block = event.getBlock();
            CustomItem storedItem = WorldUtils.getWorldCustomItemStore().getCustomItem(block.getLocation());
            if (storedItem != null) {
                WorldUtils.getWorldCustomItemStore().remove(block.getLocation());
            }
        }
    }

    /*
    Unregisters the placed CustomItem when the CustomItem is a leaf and decays.
     */
    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent event) {
        if (!event.isCancelled()) {
            Block block = event.getBlock();
            CustomItem storedItem = WorldUtils.getWorldCustomItemStore().getCustomItem(block.getLocation());
            if (storedItem != null) {
                WorldUtils.getWorldCustomItemStore().remove(block.getLocation());
            }
        }
    }

    @EventHandler
    public void onBlockFade(BlockFadeEvent event) {
        if (!event.isCancelled() && event.getNewState().getType().equals(Material.AIR)) {
            Block block = event.getBlock();
            CustomItem storedItem = WorldUtils.getWorldCustomItemStore().getCustomItem(block.getLocation());
            if (storedItem != null) {
                WorldUtils.getWorldCustomItemStore().remove(block.getLocation());
            }
        }
    }

    @EventHandler
    public void test(EntityChangeBlockEvent event) {

    }

    /**
     * Update the CustomItem when it is placed by an Player
     */
    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!event.isCancelled() && event.canBuild()) {
            CustomItem customItem = Registry.CUSTOM_ITEMS.get(NamespacedKey.of(getCustomItemID(event.getItemInHand())));
            if (!ItemUtils.isAirOrNull(customItem) && customItem.getItemStack().getType().isBlock()) {
                if (customItem.isBlockPlacement()) {
                    event.setCancelled(true);
                }
                CustomItemPlaceEvent event1 = new CustomItemPlaceEvent(customItem, event);
                Bukkit.getPluginManager().callEvent(event1);
                customItem = event1.getCustomItem();

                if (!event1.isCancelled()) {
                    if (customItem != null) {
                        WorldUtils.getWorldCustomItemStore().store(event.getBlockPlaced().getLocation(), customItem);
                    }
                } else {
                    event.setCancelled(true);
                }
            }

        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockPlaceMulti(BlockMultiPlaceEvent event) {
        if (!event.isCancelled()) {
            CustomItem customItem = Registry.CUSTOM_ITEMS.get(NamespacedKey.of(getCustomItemID(event.getItemInHand())));
            if (!ItemUtils.isAirOrNull(customItem)) {
                if (customItem.isBlockPlacement()) {
                    event.setCancelled(true);
                    return;
                }
                event.getReplacedBlockStates().forEach(state -> WorldUtils.getWorldCustomItemStore().store(state.getLocation(), customItem));
            }
        }
    }

    private String getCustomItemID(ItemStack itemStack) {
        if (!ItemUtils.isAirOrNull(itemStack)) {
            ItemMeta itemMeta = itemStack.getItemMeta();
            if (itemMeta != null && itemMeta.getPersistentDataContainer().has(new org.bukkit.NamespacedKey(WUPlugin.getInstance(), "custom_item"), PersistentDataType.STRING)) {
                return itemMeta.getPersistentDataContainer().get(new org.bukkit.NamespacedKey(WUPlugin.getInstance(), "custom_item"), PersistentDataType.STRING);
            }
        }
        return "";
    }
}
