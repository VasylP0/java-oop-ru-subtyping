package exercise;

import java.util.Map;

public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> current = storage.toMap();

        for (String key : current.keySet()) {
            storage.unset(key);
        }

        for (Map.Entry<String, String> entry : current.entrySet()) {
            storage.set(entry.getValue(), entry.getKey());
        }
    }
}
