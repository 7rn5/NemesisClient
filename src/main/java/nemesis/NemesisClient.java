//    ##    ##  ########  ##     ##  ########   ######   ####   ######  
//    ###   ##  ##        ###   ###  ##        ##    ##   ##   ##    ## 
//    ####  ##  ##        #### ####  ##        ##         ##   ##       
//    ## ## ##  ######    ## ### ##  ######     ######    ##    ######  
//    ##  ####  ##        ##     ##  ##              ##   ##         ## 
//    ##   ###  ##        ##     ##  ##        ##    ##   ##   ##    ## 
//    ##    ##  ########  ##     ##  ########   ######   ####   ######  

package nemesis;

import nemesis.manager.*;
import nemesis.impl.module.ModuleManager;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NemesisClient implements ModInitializer {
	public static final String CLIENT_ID = "nemesis";
	public static final String CLIENT_STATUS = "Beta"; //Beta or Release
  public static final String CLIENT_VERSION = "0.0.2";
  public static final String CLIENT_NAME = "NemesisClient";
  public static final String LOG_PREFIX = "[" + CLIENT_NAME + "] ";
	
	public static MinecraftClient mc;
	
	//managers
	public static ConfigManager configManager;
	public static ModuleManager moduleManager;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(CLIENT_NAME);

	@Override
	public void onInitialize() {
	    //log
	    LOGGER.info(LOG_PREFIX + "Welcome to NemesisClient");
	    
	    //Nemesis
	    LOGGER.info(LOG_PREFIX + "");
	    LOGGER.info(LOG_PREFIX + "##    ##  ########  ##     ##  ########   ######   ####   ######  ");
	    LOGGER.info(LOG_PREFIX + "###   ##  ##        ###   ###  ##        ##    ##   ##   ##    ## ");
	    LOGGER.info(LOG_PREFIX + "####  ##  ##        #### ####  ##        ##         ##   ##       ");
	    LOGGER.info(LOG_PREFIX + "## ## ##  ######    ## ### ##  ######     ######    ##    ######  ");
	    LOGGER.info(LOG_PREFIX + "##  ####  ##        ##     ##  ##              ##   ##         ## ");
	    LOGGER.info(LOG_PREFIX + "##   ###  ##        ##     ##  ##        ##    ##   ##   ##    ## ");
	    LOGGER.info(LOG_PREFIX + "##    ##  ########  ##     ##  ########   ######   ####   ######  ");
	    LOGGER.info(LOG_PREFIX + "");
	    LOGGER.info(LOG_PREFIX + "    " + CLIENT_NAME + " v" +CLIENT_VERSION + " (" + CLIENT_STATUS + ")");
	}
	
	public void onInitializeClient() {
	    mc = MinecraftClient.getInstance();
	    
	    //Register Manager
	    moduleManager = new ModuleManager();
	    configManager = new ConfigManager();
	    configManager.load();
	    ConfigManager.init();
	}
}