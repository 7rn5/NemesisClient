package nemesis.setting.impl;

import nemesis.setting.Setting;
import java.util.List;

public class ModeSetting extends Setting<String> {
    private final List<String> modes;

    public ModeSetting(String name, String defaultValue, List<String> modes) {
        super(name, defaultValue);
        this.modes = modes;
    }

    public List<String> getModes() {
        return modes;
    }

    public void cycle() {
        int index = modes.indexOf(getValue());
        setValue(modes.get((index + 1) % modes.size()));
    }
}