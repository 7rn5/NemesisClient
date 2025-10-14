package nemesis.settings.impl;

import nemesis.settings.Setting;
import java.util.Arrays;
import java.util.List;

public class EnumSetting<T extends Enum<T>> extends Setting<T> {
    private final List<T> values;

    public EnumSetting(String name, T defaultValue) {
        super(name, defaultValue);
        this.values = Arrays.asList(defaultValue.getDeclaringClass().getEnumConstants());
    }

    public List<T> getValues() {
        return values;
    }

    public void next() {
        int index = values.indexOf(get()) + 1;
        if (index >= values.size()) index = 0;
        set(values.get(index));
    }

    public void previous() {
        int index = values.indexOf(get()) - 1;
        if (index < 0) index = values.size() - 1;
        set(values.get(index));
    }
    
    public int getSize() {
        return values.size();
    }
}