package nemesis;

import nemesis.manager.misc.*;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NemesisClient implements ModInitializer {
	public static final String CLIENT_ID = "nemesis";
  public static final String CLIENT_VERSION = "0.0.0";
  public static final String CLIENT_NAME = "NemesisClient";
	
	public static MinecraftClient mc;
	
	//managers
	public static ConfigManager configManager;

	public static final Logger LOGGER = LoggerFactory.getLogger(CLIENT_ID);

	@Override
	public void onInitialize() {
	    //log
	    LOGGER.info("Welcome to NemesisClient");
	    
	    //nemesis managers
	}
	
	public void onInitializeClient() {
	    configManager = new ConfigManager();
	    configManager.load();
	}
}