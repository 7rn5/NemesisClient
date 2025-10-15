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
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class NemesisClient implements ModInitializer, ClientModInitializer {
    public static String CLIENT_ID = "nemesis";
    public static String CLIENT_STATUS = "Beta"; //Beta or Release
    public static String CLIENT_VERSION = "0.0.8";
    public static String CLIENT_NAME = "NemesisClient";
    public static String LOG_PREFIX = "[" + CLIENT_NAME + "] ";
    public static MinecraftClient mc;
    public static KeyBinding openGuiKey;

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
        //LOGGER.info(LOG_PREFIX + "");
        //LOGGER.info(LOG_PREFIX + "##    ##  ########  ##     ##  ########   ######   ####   ######  ");
        //LOGGER.info(LOG_PREFIX + "###   ##  ##        ###   ###  ##        ##    ##   ##   ##    ## ");
        //LOGGER.info(LOG_PREFIX + "####  ##  ##        #### ####  ##        ##         ##   ##       ");
        //LOGGER.info(LOG_PREFIX + "## ## ##  ######    ## ### ##  ######     ######    ##    ######  ");
        //LOGGER.info(LOG_PREFIX + "##  ####  ##        ##     ##  ##              ##   ##         ## ");
        //LOGGER.info(LOG_PREFIX + "##   ###  ##        ##     ##  ##        ##    ##   ##   ##    ## ");
        //LOGGER.info(LOG_PREFIX + "##    ##  ########  ##     ##  ########   ######   ####   ######  ");
        //LOGGER.info(LOG_PREFIX + "");
        LOGGER.info(LOG_PREFIX + "    " + CLIENT_NAME + " v" +CLIENT_VERSION + " (" + CLIENT_STATUS + ")");
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
        
        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nemesis.open_gui", // 翻訳キー（langファイルで名前変更可能）
                InputUtil.Type.KEYSYM, // キータイプ（キーボード、マウスなど）
                GLFW.GLFW_KEY_RIGHT_SHIFT, // デフォルトのキー
                "Nemesis" // カテゴリ（langで表示名設定）
        ));
        
        ClientLifecycleEvents.CLIENT_STOPPING.register(client -> {
            configManager.saveConfig();
        });
    }
    
    @Subscribe
    public void onTick(TickEvent event) {
        while (openGuiKey.wasPressed()) {
            mc.setScreen(new ClickGui());
            moduleManager.get(Ui.class).enable();
        }
    }
}
