package nemesis.settings.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import nemesis.settings.Setting;
import org.lwjgl.glfw.GLFW;

import static nemesis.NemesisClient.mc;

public class BindSetting extends Setting<Integer> {
    public static final int KEY_NONE = -1;
    private boolean wasPressedLastTick = false;
    private boolean holdMode = false;
    
    public BindSetting(String name, int defaultValue) {
        super(name, defaultValue);
    }
    
    public boolean isPressed() {
        if (value == KEY_NONE) return false;
        return GLFW.glfwGetKey(mc.getWindow().getHandle(), value) == GLFW.GLFW_PRESS;
    }
    
    public String getKeyName() {
        if (value == KEY_NONE) return "none";
        return GLFW.glfwGetKeyName(value, 0);
    }
    
    public boolean isHoldMode() {
        return holdMode;
    }
    
    public void setHoldMode(boolean holdMode) {
        this.holdMode = holdMode;
    }
    
    public boolean wasPressedLastTick() {
        return wasPressedLastTick;
    }
    
    public void setWasPressedLastTick(boolean val) {
        this.wasPressedLastTick = val;
    }
    
    @Override
    public void set(Integer value) {
        this.value = value;
    }
    
    @Override
    public JsonElement toJson() {
        return new JsonPrimitive(value);
    }

    @Override
    public void fromJson(JsonElement json) {
        if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isNumber()) {
            this.value = json.getAsInt();
        } else {
            this.value = KEY_NONE;
        }
    }
}
