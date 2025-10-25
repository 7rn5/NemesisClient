package nemesis.settings.impl;

import nemesis.settings.Setting;

import java.awt.Color;

public class ColorSetting extends Setting<Color> {
    private final boolean alpha;
    private final boolean sync;
    
    private ColorSetting(Builder builder) {
        super(builder);
        this.alpha = builder.alpha;
        this.sync = builder.sync;
    }
    
    public int getRed() {
        return get().getRed();
    }
    
    public int getGreen() {
        return get().getGreen();
    }

    public int getBlue() {
        return get().getBlue();
    }
    
    public int getAlpha() {
        return alpha ? get().getAlpha() : 255;
    }
    
    public boolean hasAlpha() {
        return alpha;
    }
    
    public boolean isSynced() {
        return sync;
    }
    
    public int getRGB() {
        return (getAlpha() << 24) | (getRed() << 16) | (getGreen() << 8) | getBlue();
    }
    
    public void setValue(int r, int g, int b, int a) {
        set(new Color(r, g, b, alpha ? a : 255));
    }
    
    public static class Builder extends Setting.Builder<Color, Builder> {
        private boolean alpha = true;
        private boolean sync = true;
        
        public Builder alpha(boolean alpha) {
            this.alpha = alpha;
            return this;
        }
        
        public Builder sync(boolean sync) {
            this.sync = sync;
            return this;
        }
        
        @Override
        public ColorSetting build() {
            return new ColorSetting(this);
        }
    }
}