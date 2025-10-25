package nemesis.settings.impl;

import nemesis.settings.Setting;

public class BoolSetting extends Setting<Boolean> {
    private BoolSetting(Builder builder) {
        super(builder);
    }
    
    @Override
    public void set(Boolean value) {
        super.set(value != null ? value : false);
    }
    
    public void toggle() {
        set(!get());
    }
    
    public static class Builder extends Setting.Builder<Boolean, Builder> {
        public Builder() {
            defaultValue(false);
        }
        
        @Override
        public BoolSetting build() {
            return new BoolSetting(this);
        }
    }
}