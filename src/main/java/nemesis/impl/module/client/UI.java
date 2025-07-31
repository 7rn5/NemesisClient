package nemesis.impl.module.client;

import nemesis.impl.module.Module;
import nemesis.settings.impl.BoolSetting;
import nemesis.settings.impl.BindSetting;
import nemesis.event.SubscribeEvent;
import nemesis.event.EventPriority;
import nemesis.event.impl.PostTickEvent;

import org.lwjgl.glfw.GLFW;

import static nemesis.NemesisClient.mc;
import static nemesis.NemesisClient.clickGuiScreen;

public class Ui extends Module {
    public final BoolSetting moduleFill = addSetting(new BoolSetting("ModuleFill", true));
    public final BoolSetting background = addSetting(new BoolSetting("BackGround", true));
    public final BoolSetting descriptions = addSetting(new BoolSetting("Descriptions", false));
    
    public Ui() {
        super("UI", "Management to your click gui", Category.Client);
        //add(GLFW.GLFW_KEY_RIGHT_SHIFT);
    }
    
    @Override
    public void onEnabled() {
        if (mc == null || mc.mouse == null) return;
        
        mc.setScreen(clickGuiScreen);
        this.toggle();
    }
}