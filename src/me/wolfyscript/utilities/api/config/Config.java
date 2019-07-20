package me.wolfyscript.utilities.api.config;

import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Set;

public interface Config {

    String getName();

    Type getType();

    void load();

    void save();

    void reload();

    Set<String> getKeys();

    void set(String path, Object value);

    Object get(String path);

    Object get(String path, Object def);

    String getString(String path);

    int getInt(String path);

    boolean getBoolean(String path);

    double getDouble(String path);

    long getLong(String path);

    List<?> getList(String path);

    List<String> getStringList(String path);

    void setItem(String path, ItemStack itemStack);

    void setItem(String path, String name, ItemStack itemStack);

    @Deprecated
    void saveItem(String path, ItemStack item);

    @Deprecated
    void saveItem(String path, String name, ItemStack itemStack);

    ItemStack getItem(String path);

    ItemStack getItem(String path, boolean replaceKeys);

    enum Type{
        YAML, JSON
    }
}
