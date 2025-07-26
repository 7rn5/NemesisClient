package nemesis.setting.impl;

import nemesis.setting.Setting;

import java.util.function.BooleanSupplier;

public class BindSetting extends Setting<Integer> {
    private int value;
    private final int defaultValue;

    public BindSetting(String name, int defaultValue) {
        super(name);
        this.value = defaultValue;
        this.defaultValue = defaultValue;
    }

    //public BindSetting(String name, int defaultValue, BooleanSupplier visible) {
    //    super(name, visible);
    //    this.value = defaultValue;
    //    this.defaultValue = defaultValue;
    //}

    @Override
    public Integer get() {
        return value;
    }

    @Override
    public void set(Integer value) {
        this.value = value;
    }

    public void reset() {
        this.value = defaultValue;
    }

    public int getDefault() {
        return defaultValue;
    }

    public boolean isPressed(int keyCode) {
        return this.value == keyCode;
    }
}