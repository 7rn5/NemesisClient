package nemesis.manager.misc;

import nemesis.NemesisClient;
import com.google.gson.*;
import nemesis.util.file.JsonUtil;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ConfigManager {
    private final List<JsonUtil> jsonables = List.of();
    
    private static final Path NEMESIS_PATH = FabricLoader.getInstance().getGameDir().resolve(NemesisClient.CLIENT_ID);
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    
    public void load() {
        NemesisClient.LOGGER.info(NemesisClient.LOG_PREFIX + "ConfigLoading...");
        if (!NEMESIS_PATH.toFile().exists()) NEMESIS_PATH.toFile().mkdirs();
        for (JsonUtil jsonable : jsonables) {
            try {
                String read = Files.readString(NEMESIS_PATH.resolve(jsonable.getFileName()));
                jsonable.fromJson(JsonParser.parseString(read));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        NemesisClient.LOGGER.info(NemesisClient.LOG_PREFIX + "Config load Success!");
    }

    public void save() {
        NemesisClient.LOGGER.info(NemesisClient.LOG_PREFIX + "Saving config...")
        if (!NEMESIS_PATH.toFile().exists()) NEMESIS_PATH.toFile().mkdirs();
        for (JsonUtil jsonable : jsonables) {
            try {
                JsonElement json = jsonable.toJson();
                Files.writeString(NEMESIS_PATH.resolve(jsonable.getFileName()), gson.toJson(json));
            } catch (Throwable e) {
            }
        }
        NemesisClient.LOGGER.info(NemesisClient.LOG_PREFIX + "Config save Success!");
    }
    public static void init() {
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            NemesisClient.LOGGER.info(NemesisClient.LOG_PREFIX + "Saving config...")
            NemesisClient.configManager.save();
            NemesisClient.LOGGER.info(NemesisClient.LOG_PREFIX + "Config save Success!");
        });
    }
}
