//    ##    ##  ########  ##     ##  ########   ######   ####   ######  
//    ###   ##  ##        ###   ###  ##        ##    ##   ##   ##    ## 
//    ####  ##  ##        #### ####  ##        ##         ##   ##       
//    ## ## ##  ######    ## ### ##  ######     ######    ##    ######  
//    ##  ####  ##        ##     ##  ##              ##   ##         ## 
//    ##   ###  ##        ##     ##  ##        ##    ##   ##   ##    ## 
//    ##    ##  ########  ##     ##  ########   ######   ####   ######  

package nemesis;

import nemesis.manager.*;
import nemesis.manager.config.*;
import nemesis.impl.module.ModuleManager;
import nemesis.impl.gui.screen.ClickGuiScreen;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NemesisClient implements ModInitializer {
	public static String CLIENT_ID = "nemesis";
	public static String CLIENT_STATUS = "Beta"; //Beta or Release
  public static String CLIENT_VERSION = "0.0.3";
  public static String CLIENT_NAME = "NemesisClient";
  public static String LOG_PREFIX = "[" + CLIENT_NAME + "] ";
	
	public static MinecraftClient mc;
	
	//managers
	    public static ConfigManager configManager;
	    public static ModuleManager moduleManager;
	    public static EventManager eventManager;
	//gui 
	    public static ClickGuiScreen clickGuiScreen;
	
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
	        eventManager = new EventManager();
	   
	   //gui 
	        clickGuiScreen = new ClickGuiScreen();
	   
	   //config manager
	   configManager.loadModules();
	   configManager.loadFriends();
	   
	   if (configManager.loadName() == null)
         configManager.saveName(CLIENT_ID);
     else
         CLIENT_ID = configManager.loadName();
    
     ClientLifecycleEvents.CLIENT_STOPPING.register(client -> {
          configManager.saveModules();
     });
	}
}