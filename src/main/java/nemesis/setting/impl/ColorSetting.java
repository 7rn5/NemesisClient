package nemesis.setting.impl;

import nemesis.setting.Setting;
import nemesis.setting.impl.GroupSetting;
import nemesis.util.renderer.ColorUtil;

import java.awt.Color;

public class ColorSetting extends Setting<Color> {
    private Color value;
    private final Color defaultValue;
    //private final groupName = Setting.getName();

    public ColorSetting(String name, Color defaultValue) {
        super(name);
        this.value = defaultValue;
        this.defaultValue = defaultValue;
    }

    @Override
    public Color get() {
        return value;
    }

    @Override
    public void set(Color value) {
        this.value = value;
    }

    public void reset() {
        this.value = defaultValue;
    }

    public Color getDefault() {
        return defaultValue;
    }

    public Color getWithAlpha(int alpha) {
        return ColorUtil.newAlpha(value, alpha);
    }
}