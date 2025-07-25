package nemesis.setting.impl;

import nemesis.setting.Setting;

import java.util.function.BooleanSupplier;

public class StringSetting extends Setting<String> {
    private String value;
    private final String defaultValue;

    public StringSetting(String name, String defaultValue) {
        super(name);
        this.value = defaultValue;
        this.defaultValue = defaultValue;
    }

    //public StringSetting(String name, String defaultValue, BooleanSupplier visible) {
    //    super(name, visible);
    //    this.value = defaultValue;
    //    this.defaultValue = defaultValue;
    //}

    @Override
    public String get() {
        return value;
    }

    @Override
    public void set(String value) {
        this.value = value;
    }

    public void reset() {
        this.value = defaultValue;
    }

    public String getDefault() {
        return defaultValue;
    }
}