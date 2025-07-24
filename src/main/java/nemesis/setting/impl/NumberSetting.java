package nemesis.setting.impl;

import nemesis.setting.Setting;

import java.util.function.BooleanSupplier;

public class NumberSetting extends Setting<Number> {
    private final Number defaultValue;
    private final Number min;
    private final Number max;
    private Number value;

    public NumberSetting(String name, Number defaultValue, Number min, Number max) {
        this(name, defaultValue, min, max, () -> true);
    }

    public NumberSetting(String name, Number defaultValue, Number min, Number max, BooleanSupplier visible) {
        super(name);
        this.defaultValue = defaultValue;
        this.value = defaultValue;
        this.min = min;
        this.max = max;
    }

    @Override
    public Number get() {
        return value;
    }

    @Override
    public void set(Number value) {
        double val = value.doubleValue();
        double minVal = min.doubleValue();
        double maxVal = max.doubleValue();

        if (val < minVal) {
            this.value = min;
        } else if (val > maxVal) {
            this.value = max;
        } else {
            this.value = value;
        }
    }

    public void reset() {
        this.value = defaultValue;
    }

    public Number getMin() {
        return min;
    }

    public Number getMax() {
        return max;
    }

    public Number getDefault() {
        return defaultValue;
    }

    public boolean isInteger() {
        return defaultValue instanceof Integer || defaultValue instanceof Long;
    }
}