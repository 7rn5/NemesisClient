package nemesis.settings.impl;

import nemesis.settings.Setting;

public class DoubleSetting extends Setting<Double> {
    private final double min;
    private final double max;
    
    protected DoubleSetting(Builder builder) {
        super(builder);
        this.min = builder.min;
        this.max = builder.max;
    }
    
    public double getMin() {
        return min;
    }
    
    public double getMax() {
        return max;
    }
    
    @Override
    public void set(Double value) {
        if (value == null) return;
        this.value = clamp(value);
        if (onChanged != null) onChanged.accept(this.value);
    }
    
    private double clamp(double value) {
        return Math.max(min, Math.min(max, value));
    }
    
    public static class Builder extends Setting.Builder<Double, Builder> {
        private double min = 0.0;
        private double max = 1.0;
        
        public Builder min(double min) {
            this.min = min;
            return this;
        }
        
        public Builder max(double max) {
            this.max = max;
            return this;
        }
        
        public DoubleSetting build() {
            return new DoubleSetting(this);
        }
    }
}