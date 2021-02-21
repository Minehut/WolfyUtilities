package me.wolfyscript.utilities.api.inventory.custom_items.meta;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import me.wolfyscript.utilities.api.inventory.custom_items.CustomItem;
import me.wolfyscript.utilities.api.inventory.custom_items.meta.legacy.Meta;
import me.wolfyscript.utilities.util.NamespacedKey;
import me.wolfyscript.utilities.util.Registry;
import me.wolfyscript.utilities.util.inventory.item_builder.ItemBuilder;
import me.wolfyscript.utilities.util.json.jackson.JacksonUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

@Deprecated
@JsonDeserialize(using = LegacyMetaSettings.Deserializer.class)
public class LegacyMetaSettings extends HashMap<NamespacedKey, Meta> {

    public LegacyMetaSettings() {
        Registry.META_PROVIDER.entrySet().forEach(entry -> put(entry.getKey(), entry.getValue().provide()));
    }

    public LegacyMetaSettings(JsonNode node) {
        this();
        if (node != null) {
            node.fields().forEachRemaining(entry -> {
                String key = entry.getKey().toLowerCase(Locale.ROOT);
                NamespacedKey namespacedKey = key.contains(":") ? NamespacedKey.of(key) : NamespacedKey.wolfyutilties(key);
                Meta.Provider<?> provider = Registry.META_PROVIDER.get(namespacedKey);
                if (provider != null) {
                    Meta meta;
                    if (entry.getValue().isTextual()) {
                        meta = provider.provide();
                        meta.setOption(JacksonUtil.getObjectMapper().convertValue(entry.getValue(), LegacyMetaSettings.Option.class));
                    } else {
                        meta = provider.parse(entry.getValue());
                    }
                    put(namespacedKey, meta);
                }
            });
        }
    }

    public static SettingsMetaItem convert(CustomItem item, JsonNode node) throws JsonProcessingException {
        if (node.isTextual()) {
            //Old String style meta
            node = JacksonUtil.getObjectMapper().readTree(node.asText());
        }
        //New Json style meta
        LegacyMetaSettings legacyMetaSettings = new LegacyMetaSettings(node);

        return null;
    }

    public boolean check(ItemBuilder itemOther, ItemBuilder item) {
        return values().stream().allMatch(meta -> meta.check(itemOther, item));
    }

    public enum Option {
        EXACT, IGNORE, HIGHER, HIGHER_EXACT, LOWER, LOWER_EXACT, HIGHER_LOWER
    }

    public static class Deserializer extends StdDeserializer<LegacyMetaSettings> {

        public Deserializer() {
            super(LegacyMetaSettings.class);
        }

        protected Deserializer(Class<LegacyMetaSettings> t) {
            super(t);
        }

        @Override
        public LegacyMetaSettings deserialize(com.fasterxml.jackson.core.JsonParser p, DeserializationContext ctxt) throws IOException {
            JsonNode node = p.readValueAsTree();
            if (node.isTextual()) {
                //Old String style meta
                node = JacksonUtil.getObjectMapper().readTree(node.asText());
            }
            //New Json style meta
            return new LegacyMetaSettings(node);
        }
    }
}
