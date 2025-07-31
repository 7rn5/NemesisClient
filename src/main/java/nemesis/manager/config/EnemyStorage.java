package nemesis.manager.config;

import com.google.gson.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import static nemesis.NemesisClient.LOGGER;

public class EnemyStorage {
    private final ConfigDirectoryProvider dirs;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public EnemyStorage(ConfigDirectoryProvider dirs) {
        this.dirs = dirs;
    }

    public void save(Set<String> enemies) {
        JsonArray array = new JsonArray();
        for (String e : enemies) {
            array.add(e.toLowerCase());
        }

        File file = dirs.getFriendFile();
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8)) {
            gson.toJson(array, writer);
        } catch (Exception e) {
            LOGGER.error("Failed to save enemies.json", e);
        }
    }

    public Set<String> load() {
        Set<String> enemies = new HashSet<>();
        File file = dirs.getFriendFile();

        if (!file.exists()) return enemies;

        try (FileReader reader = new FileReader(file, StandardCharsets.UTF_8)) {
            JsonElement element = JsonParser.parseReader(reader);
            if (element.isJsonArray()) {
                for (JsonElement e : element.getAsJsonArray()) {
                    if (e.isJsonPrimitive()) {
                        enemies.add(e.getAsString().toLowerCase());
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Failed to load enemies.json", e);
        }

        return enemies;
    }

    public boolean isEnemy(String name) {
        return load().contains(name.toLowerCase());
    }
}
