package me.wolfyscript.utilities.api.inventory;

import me.wolfyscript.utilities.api.WolfyUtilities;
import me.wolfyscript.utilities.api.utils.chat.ClickData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class GuiWindow implements Listener {

    private String namespace;
    public String itemKey;
    private InventoryAPI inventoryAPI;
    private HashMap<GuiHandler, Inventory> cachedInventories;

    //Inventory
    private InventoryType inventoryType;
    private int size;

    public GuiWindow(String namespace, InventoryAPI inventoryAPI, int size) {
        this(namespace, namespace, inventoryAPI, null, size);
    }

    public GuiWindow(String namespace, InventoryAPI inventoryAPI, InventoryType inventoryType) {
        this(namespace, namespace, inventoryAPI, inventoryType, 0);
    }

    public GuiWindow(String namespace, String itemKey, InventoryAPI inventoryAPI, InventoryType inventoryType, int size) {
        this.namespace = namespace;
        this.inventoryAPI = inventoryAPI;
        this.itemKey = itemKey;
        this.cachedInventories = new HashMap<>();
        this.inventoryType = inventoryType;
        this.size = size;
        Bukkit.getPluginManager().registerEvents(this, inventoryAPI.getPlugin());
        onInit();
    }

    public InventoryType getInventoryType() {
        return inventoryType;
    }

    public int getSize() {
        return size;
    }

    //OVERRIDE METHODS

    public void onInit() {

    }

    public boolean onAction(GuiAction guiAction){
        return true;
    }

    public boolean onClick(GuiClick guiClick){
        return true;
    }

    public void onUpdate(){

    }

    /*
    Replaced by direct accessed methods via lambda expressions! This method only exist for backwards compatibility!
    Will be removed completely in the near future!
     */
    @Deprecated
    public boolean parseChatMessage(int id, String message, GuiHandler guiHandler) {
        return true;
    }

    //

    protected void update(GuiHandler guiHandler){
        Bukkit.getScheduler().runTaskLater(inventoryAPI.getPlugin(), () -> {
            if(!guiHandler.isChatEventActive()){
                GuiUpdateEvent event = new GuiUpdateEvent(guiHandler, this);
                Bukkit.getPluginManager().callEvent(event);
            }
        },1);
    }

    public String getNamespace() {
        return namespace;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String newItemKey) {
        itemKey = newItemKey;
    }

    public void createItem(String id, Material material) {
        createItem(itemKey, id, material);
    }

    public void createItem(String id, ItemStack itemStack) {
        createItem(itemKey, id, itemStack);
    }

    public void createItem(String key, String id, Material material) {
        createItem(key, id, new ItemStack(material));
    }

    public void createItem(String key, String id, ItemStack itemStack) {
        inventoryAPI.registerItem(key, id, itemStack);
    }

    public void reloadInv(GuiHandler guiHandler) {
        guiHandler.reloadInv(namespace);
    }

    /*
    Replaced by openChat() method. It is easier to use via lambda!
    And it is faster in execution! You don't need to check the ids all the time!
     */
    @Deprecated
    public void runChat(int id, String message, GuiHandler guiHandler) {
        guiHandler.setTestChatID(id);
        guiHandler.close();
        guiHandler.getApi().sendPlayerMessage(guiHandler.getPlayer(), message);
    }

    /*
    Opens the chat, send the player the defined message and waits for the input of the player.
    When the player sends the message the inputAction method is executed
     */
    public void openChat(GuiHandler guiHandler, String msg, ChatInputAction inputAction){
        guiHandler.setChatInputAction(inputAction);
        guiHandler.close();
        guiHandler.getApi().sendPlayerMessage(guiHandler.getPlayer(), msg);
    }

    /*
    Opens the chat, send the player the defined action messages and waits for the input of the player.
    When the player sends the message the inputAction method is executed
     */
    public void openActionChat(GuiHandler guiHandler, ClickData clickData, ChatInputAction inputAction){
        guiHandler.setChatInputAction(inputAction);
        guiHandler.close();
        guiHandler.getApi().sendActionMessage(guiHandler.getPlayer(), clickData);
    }

    protected String getInventoryName(){
        return WolfyUtilities.translateColorCodes(inventoryAPI.getWolfyUtilities().getLanguageAPI().getActiveLanguage().replaceKeys("$inventories."+namespace+"$"));
    }

    public Inventory getInventory(GuiHandler guiHandler) {
        return cachedInventories.get(guiHandler);
    }

    public boolean hasCachedInventory(GuiHandler guiHandler){
        return cachedInventories.containsKey(guiHandler);
    }

    public void setCachedInventorie(GuiHandler guiHandler, Inventory inventory){
        cachedInventories.put(guiHandler, inventory);
    }



}
