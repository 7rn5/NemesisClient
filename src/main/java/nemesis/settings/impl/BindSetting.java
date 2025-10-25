package nemesis.settings.impl;

import nemesis.settings.Setting;
import org.lwjgl.glfw.GLFW;

import static nemesis.NemesisClient.mc;

public class BindSetting extends Setting<Integer> {
    public static final int KEY_NONE = -1;
    private boolean holdMode;
    private boolean wasPressedLastTick;
    
    private BindSetting(Builder builder) {
        super(builder);
        this.holdMode = builder.holdMode;
    }
    
    public boolean isPressed() {
        if (get() == KEY_NONE) return false;
        long handle = mc.getWindow().getHandle();
        return GLFW.glfwGetKey(handle, get()) == GLFW.GLFW_PRESS;
    }
    
    public boolean isJustPressed() {
        boolean pressed = isPressed();
        boolean justPressed = pressed && !wasPressedLastTick;
        wasPressedLastTick = pressed;
        return justPressed;
    }
    
    public String getKeyName() {
        if (get() == KEY_NONE) return "none";
        String name = GLFW.glfwGetKeyName(get(), 0);
        return name != null ? name.toUpperCase() : ("KEY_" + get());
    }
    
    public boolean isHoldMode() {
        return holdMode;
    }
    
    public void setHoldMode(boolean holdMode) {
        this.holdMode = holdMode;
    }
    
    public void resetLastState() {
        this.wasPressedLastTick = false;
    }
    
    public static class Builder extends Setting.Builder<Integer, Builder> {
        private boolean holdMode = false;
        
        public Builder() {
            defaultValue(KEY_NONE);
        }
        
        public Builder holdMode(boolean holdMode) {
            this.holdMode = holdMode;
            return this;
        }
        
        @Override
        public BindSetting build() {
            return new BindSetting(this);
        }
    }
}