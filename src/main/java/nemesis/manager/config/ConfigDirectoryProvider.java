package nemesis.manager.config;

import net.fabricmc.loader.api.FabricLoader;

import java.io.File;

import static nemesis.NemesisClient.CLIENT_ID;

public class ConfigDirectoryProvider {

    private final File baseDir;

    public ConfigDirectoryProvider() {
        this.baseDir = new File(FabricLoader.getInstance().getGameDir().toFile(), CLIENT_ID);
    }

    public File getModuleConfigDir() {
        return new File(baseDir, "config");
    }

    public File getConfigSaveDir() {
        return new File(baseDir, "configs");
    }

    public File getFriendFile() {
        return new File(baseDir, "friends.json");
    }
    
    public File getEnemyFile() {
        return new File(baseDir, "enemies.json");
    }

    public File getBaseDir() {
        return baseDir;
    }
}
