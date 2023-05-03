package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
// BEGIN

// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.CREATE);
    }

    // BEGIN
    @Test
    public void fileKVTest(){
        KeyValueStorage storage = new FileKV("src/test/resources/file",Map.of("Name", "John",
                "Lastname","McClane"));
        assertThat(storage.get("Nickname", "Cowboy")).isEqualTo("Cowboy");
        assertThat(storage.get("Name", "NoName")).isEqualTo("John");

        storage.set("Nickname", "Billy");
        storage.set("Name", "Bruce");

        assertThat(storage.get("Nickname", "Cowboy")).isEqualTo("Billy");
        assertThat(storage.get("Name", "John")).isEqualTo("Bruce");

        storage.unset("Name");
        assertThat(storage.get("Name", "John")).isEqualTo("John");
        assertThat(storage.toMap()).isEqualTo(Map.of("Lastname", "McClane", "Nickname", "Billy"));
    }

    @Test
    void mustBeImmutableTest() {
        Map<String, String> initial = new HashMap<>();
        initial.put("key", "10");

        Map<String, String> clonedInitial = new HashMap<>();
        clonedInitial.putAll(initial);

        KeyValueStorage storage = new FileKV("src/test/resources/file",initial);

        initial.put("key2", "value2");
        assertThat(storage.toMap()).isEqualTo(clonedInitial);

        Map<String, String> map = storage.toMap();
        map.put("key2", "value2");
        assertThat(storage.toMap()).isEqualTo(clonedInitial);
    }
    // END
}
