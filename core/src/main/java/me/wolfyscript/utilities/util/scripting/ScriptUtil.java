package me.wolfyscript.utilities.util.scripting;

import javax.script.ScriptEngine;

/**
 * @deprecated There is no need to use this util class!
 * You can get the correct script engine easily using the {@link ScriptEngineManager}
 * or use Graal.js via org.graalvm.polyglot.Context.
 */
@Deprecated
public class ScriptUtil {

    private static final ScriptEngine engine = null;

    public ScriptUtil() {
    }

    /**
     * @return The Nashorn scripting engine.
     * @deprecated Will be removed soon, because it's deprecated in Java 11+ and is no longer used!
     */
    @Deprecated
    public static ScriptEngine getEngine() {
        return null;
    }
}
