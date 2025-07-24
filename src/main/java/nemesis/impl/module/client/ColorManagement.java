package nemesis.impl.module.client;

import nemesis.impl.module.Module;
import nemesis.setting.Setting;
import nemesis.setting.impl.ColorSetting;

import java.awt.Color;

public class ColorManagement extends Module {
    public final ColorSetting defaultColor = new ColorSetting("DefaultColor", new Color(255, 0, 0));
}