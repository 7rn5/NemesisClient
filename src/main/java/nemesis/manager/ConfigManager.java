package nemesis.manager;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Path;

public class ConfigManager {
    private static final Path nemesisFolder = FabricLoader.getInstance().getGameDir().resolve("nemesis");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public void loadConfig() {
        if (!nemesisFolder.toFile().exists()) nemesisFolder.toFile().mkdirs();
    }
    public void loadFriend() {
        if (!nemesisFolder.toFile().exists()) nemesisFolder.toFile().mkdirs();
    }
    public void loadPrefix() {}
    
    public void saveConfig() {}
    public void saveConfig(String configName) {}
    public void addFriend(String addPlayerName) {}
    public void unAddFriend(String unAddFriendName) {}
    public void setPrefix(String prefix) {}
}
