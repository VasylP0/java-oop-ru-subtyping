package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

// BEGIN
class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.TRUNCATE_EXISTING);
    }

    @Test
    void testFileKVOperations() {
        KeyValueStorage storage = new FileKV(filepath.toString(), Map.of("key", "value"));

        // Check initial value
        assertThat(storage.get("key", "default")).isEqualTo("value");

        // Set new key
        storage.set("key2", "value2");
        assertThat(storage.get("key2", "default")).isEqualTo("value2");

        // Unset key
        storage.unset("key");
        assertThat(storage.get("key", "default")).isEqualTo("default");

        // Final map should contain only key2
        assertThat(storage.toMap()).isEqualTo(Map.of("key2", "value2"));
    }
}
// END
