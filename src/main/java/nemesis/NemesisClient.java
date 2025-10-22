package nemesis;

import nemesis.event.bus.EventHandler;
import nemesis.event.bus.Subscribe;
import nemesis.event.TickEvent;
import nemesis.impl.module.client.Ui;
import nemesis.impl.module.ModuleManager;
import nemesis.impl.gui.ClickGui;
import nemesis.manager.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class NemesisClient implements ModInitializer, ClientModInitializer {
    public static String CLIENT_ID = "nemesis";
    public static String CLIENT_STATUS = getClientStatus();
    public static String CLIENT_VERSION = BuildConfig.CLIENT_VERSION;
    public static String CLIENT_NAME = "NemesisClient";
    public static String GIT_HASH = BuildConfig.GIT_HASH;
    public static String LOG_PREFIX = "[" + CLIENT_NAME + "] ";
    public static MinecraftClient mc;
    public static KeyBinding openGuiKey;
    private static final List<KeyBinding> allKeys = new ArrayList<>();

    //managers
    public static ConfigManager configManager;
    public static ModuleManager moduleManager;

    public static final Logger LOGGER = LoggerFactory.getLogger(CLIENT_NAME);
    public static final EventHandler eventHandler = new EventHandler();

    @Override
    public void onInitialize() {
        //log
        LOGGER.info(LOG_PREFIX + "Welcome to NemesisClient");

        //Nemesis
        LOGGER.info(LOG_PREFIX + "    " + CLIENT_NAME + " v" + CLIENT_VERSION + " (" + CLIENT_STATUS + ")");
    }

    @Override
    public void onInitializeClient() {
        mc = MinecraftClient.getInstance();

        //Register Manager
        moduleManager = new ModuleManager();
        configManager = new ConfigManager();

        //config manager
        configManager.loadConfig();
        
        //module manager
        moduleManager.init();
        
        //register event
        eventHandler.subscribe(this);
        
        //KeyBind
        openGuiKey = new KeyBinding(
            "key.nemesis.open_gui",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            "Nemesis"
        );
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            configManager.saveConfig();
        }));
    }
    
    @Subscribe
    public void onTick(TickEvent event) {
        while (openGuiKey.wasPressed()) {
            mc.setScreen(new ClickGui());
            moduleManager.get(Ui.class).enable();
        }
    }
    
    public static String getClientStatus() {
        try {
            String[] parts = CLIENT_VERSION.split("\\.");
            if (parts.length < 3) return "Unknown";
            int patch = Integer.parseInt(parts[2]);
            return (patch == 0) ? "Release" : "Beta";
        } catch  (Exception e) {
            return "Unknown";
        }
    }
}
