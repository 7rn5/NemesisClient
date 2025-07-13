package nemesis.manager.misc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import java.nio.file.Path;

public class ConfigManager {
    private static final Path NEMESIS_PATH = FabricLoader.getInstance().getGameDir().resolve("nemesis");
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    
    //public void save() {}
    //public void load() {}
}