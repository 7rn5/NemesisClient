package nemesis.impl.module.client;

import nemesis.impl.module.Module;
import nemesis.settings.impl.ColorSetting;

import java.awt.Color;

public class Notification extends Module {
    private final ColorSetting bracketColor = addSetting(new ColorSetting("BracketColor", new Color(255, 255, 0), false));
    private final ColorSetting watermarkColor = addSetting(new ColorSetting("Watermark", new Color(255, 255, 0), false));
    
    public Notification() {
        super("Notification", "custom client notification", Category.Client);
    }
}