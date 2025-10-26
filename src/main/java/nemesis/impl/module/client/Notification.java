package nemesis.impl.module.client;

import nemesis.impl.module.Module;
import nemesis.settings.impl.BoolSetting;
import nemesis.settings.impl.ColorSetting;

import java.awt.Color;

public class Notification extends Module {
    public final ColorSetting bracketColor = addSetting(new ColorSetting.Builder()
        .name("BracketColor")
        .defaultValue(new Color(170, 170, 170))
        .alpha(false)
        .build()
    );
    
    public final ColorSetting watermarkColor = addSetting(new ColorSetting.Builder()
        .name("Watermark")
        .defaultValue(new Color(170, 170, 170))
        .alpha(false)
        .build()
    );
    
    public final BoolSetting indicatorIcon = addSetting(new BoolSetting.Builder()
        .name("IndicatorIcon")
        .defaultValue(true)
        .build()
    );
    
    public Notification() {
        super("Notification", "custom client notification", Category.Client);
    }
}