package exercise;

import java.util.Map;

public class FileKV implements KeyValueStorage {
    private String filePath;

    public FileKV(String filePath, Map<String, String> initial) {
        this.filePath = filePath;
        save(initial);
    }

    private void save(Map<String, String> data) {
        String json = Utils.serialize(data);
        Utils.writeFile(filePath, json);
    }

    private Map<String, String> load() {
        String json = Utils.readFile(filePath);
        return Utils.deserialize(json);
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> data = load();
        data.put(key, value);
        save(data);
    }

    @Override
    public void unset(String key) {
        Map<String, String> data = load();
        data.remove(key);
        save(data);
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> data = load();
        return data.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return load();
    }
}
