package nemesis.impl.module.client;

import nemesis.impl.gui.ClickGui;
import nemesis.impl.module.Module;
import nemesis.settings.impl.BoolSetting;
import nemesis.settings.impl.ColorSetting;
import nemesis.settings.impl.DoubleSetting;
import nemesis.settings.impl.EnumSetting;

import static nemesis.NemesisClient.mc;

import org.lwjgl.glfw.GLFW;
import java.awt.Color;

public class Ui extends Module {
    public final BoolSetting fill = addSetting(new BoolSetting("ModuleFill", true));
    public final BoolSetting textShadow = addSetting(new BoolSetting("TextShadow", true));
    public final BoolSetting background = addSetting(new BoolSetting("BackGround", true));
    public final BoolSetting descriptions = addSetting(new BoolSetting("Descriptions", false));
    public final ColorSetting color = addSetting(new ColorSetting("Color", new Color(170, 170, 170, 255), true));
    //public final DoubleSetting testDouble = addSetting(new DoubleSetting("Double", 20, 50, 30));
    //public final EnumSetting<Mode> mode = addSetting(new EnumSetting<>("Enum", Mode.aiueo));
    
    //private enum Mode {
    //    aiueo,
    //    kakikukeko
    //}
    
    
    public Ui() {
        super("UI", "Management to your click gui", Category.Client, true);
    }
}