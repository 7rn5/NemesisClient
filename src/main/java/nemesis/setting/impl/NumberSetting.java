package nemesis.setting.NumberSetting;

import nemesis.setting.Setting;

public class NumberSetting extends Setting<Double> {
    private double min;
    private double max;
    private double increment;

    public NumberSetting(String name, double defaultValue, double min, double max, double increment) {
        super(name, defaultValue);
        this.min = min;
        this.max = max;
        this.increment = increment;
    }

    public double getMin() { return min; }
    public double getMax() { return max; }
    public double getIncrement() { return increment; }

    public void setValue(double value) {
        if (value < min) value = min;
        if (value > max) value = max;
        super.setValue(value);
    }
}