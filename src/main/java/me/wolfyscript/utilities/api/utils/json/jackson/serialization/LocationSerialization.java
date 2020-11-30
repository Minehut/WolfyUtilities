package me.wolfyscript.utilities.api.utils.json.jackson.serialization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.module.SimpleModule;
import me.wolfyscript.utilities.api.utils.json.jackson.JacksonUtil;
import me.wolfyscript.utilities.main.WUPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.UUID;

public class LocationSerialization {

    public static void create(SimpleModule module) {
        JacksonUtil.addSerializerAndDeserializer(module, Location.class, (location, gen, serializerProvider) -> {
            gen.writeStartObject();
            gen.writeStringField("world", location.getWorld().getUID().toString());
            gen.writeArrayFieldStart("pos");
            gen.writeNumber(location.getX());
            gen.writeNumber(location.getY());
            gen.writeNumber(location.getZ());
            gen.writeNumber(location.getYaw());
            gen.writeNumber(location.getPitch());
            gen.writeEndArray();
            gen.writeEndObject();
        }, (p, d) -> {
            JsonNode node = p.readValueAsTree();
            if (node.isObject()) {
                UUID uuid = UUID.fromString(node.get("world").asText());
                World world = Bukkit.getWorld(uuid);
                if (world != null) {
                    JsonNode jsonNode = node.get("pos");
                    if (jsonNode.size() == 5) {
                        double x = jsonNode.get(0).asDouble();
                        double y = jsonNode.get(1).asDouble();
                        double z = jsonNode.get(2).asDouble();
                        float yaw = jsonNode.get(3).floatValue();
                        float pitch = jsonNode.get(4).floatValue();
                        return new Location(world, x, y, z, yaw, pitch);
                    }
                    WUPlugin.getWolfyUtilities().sendConsoleWarning("Error Deserializing Location! Invalid Position: expected array size 5 got " + jsonNode.size());
                    return null;
                }
                WUPlugin.getWolfyUtilities().sendConsoleWarning("Error Deserializing Location! Missing World with uid " + uuid.toString());
                return null;
            }
            WUPlugin.getWolfyUtilities().sendConsoleWarning("Error Deserializing Location! Invalid Location object!");
            return null;
        });
    }
}
