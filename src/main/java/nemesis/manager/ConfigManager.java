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

/*

code base

package nemesis.manager;

import com.google.gson.*;
import nemesis.impl.module.Module;
import nemesis.settings.Setting;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

import static nemesis.NemesisClient.moduleManager;

public class ConfigManager {
    private static final Path CONFIG_DIR = Paths.get("config", "nemesis");
    private static final Path MODULES_FILE = CONFIG_DIR.resolve("modules.json");
    private static final Path SETTINGS_FILE = CONFIG_DIR.resolve("settings.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public void loadConfig() {
        try {
            if (!Files.exists(CONFIG_DIR)) Files.createDirectories(CONFIG_DIR);

            if (Files.exists(MODULES_FILE)) loadModules();
            if (Files.exists(SETTINGS_FILE)) loadSettings();

            System.out.println("[Nemesis] Config loaded.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[Nemesis] Failed to load config!");
        }
    }

    public void saveConfig() {
        try {
            if (!Files.exists(CONFIG_DIR)) Files.createDirectories(CONFIG_DIR);

            saveModules();
            saveSettings();

            System.out.println("[Nemesis] Config saved.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[Nemesis] Failed to save config!");
        }
    }

    private void loadModules() throws IOException {
        JsonObject json = JsonParser.parseReader(Files.newBufferedReader(MODULES_FILE, StandardCharsets.UTF_8)).getAsJsonObject();

        for (Module module : moduleManager.getModules()) {
            if (json.has(module.getName())) {
                boolean enabled = json.get(module.getName()).getAsBoolean();
                module.setEnabled(enabled);
            }
        }
    }

    private void saveModules() throws IOException {
        JsonObject json = new JsonObject();

        for (Module module : moduleManager.getModules()) {
            json.addProperty(module.getName(), module.isEnabled());
        }

        Files.write(MODULES_FILE, GSON.toJson(json).getBytes(StandardCharsets.UTF_8));
    }

    private void loadSettings() throws IOException {
        JsonObject json = JsonParser.parseReader(Files.newBufferedReader(SETTINGS_FILE, StandardCharsets.UTF_8)).getAsJsonObject();

        for (Module module : moduleManager.getModules()) {
            if (!json.has(module.getName())) continue;
            JsonObject moduleJson = json.getAsJsonObject(module.getName());

            for (Setting<?> setting : module.getSettings()) {
                if (!moduleJson.has(setting.getName())) continue;

                try {
                    if (setting.get() instanceof Number) {
                        setting.set(moduleJson.get(setting.getName()).getAsDouble());
                    } else if (setting.get() instanceof Boolean) {
                        setting.set(moduleJson.get(setting.getName()).getAsBoolean());
                    } else if (setting.get() instanceof Enum<?>) {
                        String value = moduleJson.get(setting.getName()).getAsString();
                        for (Object constant : setting.get().getClass().getEnumConstants()) {
                            if (((Enum<?>) constant).name().equalsIgnoreCase(value)) {
                                setting.set((Enum<?>) constant);
                                break;
                            }
                        }
                    } else if (setting.get() instanceof java.awt.Color) {
                        int color = moduleJson.get(setting.getName()).getAsInt();
                        setting.set(new java.awt.Color(color, true));
                    } else if (setting.get() instanceof String) {
                        setting.set(moduleJson.get(setting.getName()).getAsString());
                    }
                } catch (Exception e) {
                    System.err.println("[Nemesis] Failed to load setting: " + setting.getName());
                }
            }
        }
    }

    private void saveSettings() throws IOException {
        JsonObject json = new JsonObject();

        for (Module module : moduleManager.getModules()) {
            JsonObject moduleJson = new JsonObject();

            for (Setting<?> setting : module.getSettings()) {
                Object value = setting.get();
                if (value instanceof Number) moduleJson.addProperty(setting.getName(), (Number) value);
                else if (value instanceof Boolean) moduleJson.addProperty(setting.getName(), (Boolean) value);
                else if (value instanceof Enum<?>) moduleJson.addProperty(setting.getName(), ((Enum<?>) value).name());
                else if (value instanceof java.awt.Color) moduleJson.addProperty(setting.getName(), ((java.awt.Color) value).getRGB());
                else if (value instanceof String) moduleJson.addProperty(setting.getName(), (String) value);
            }

            json.add(module.getName(), moduleJson);
        }

        Files.write(SETTINGS_FILE, GSON.toJson(json).getBytes(StandardCharsets.UTF_8));
    }
}*/