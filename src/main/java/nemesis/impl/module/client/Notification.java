package nemesis.impl.module.client;

import nemesis.impl.module.Module;
import nemesis.settings.impl.ColorSetting;

import java.awt.Color;

public class Notification extends Module {
    public final ColorSetting bracketColor = addSetting(new ColorSetting("BracketColor", new Color(170, 170, 170), false));
    public final ColorSetting watermarkColor = addSetting(new ColorSetting("Watermark", new Color(170, 170, 170), false));
    
    public Notification() {
        super("Notification", "custom client notification", Category.Client);
    }
}