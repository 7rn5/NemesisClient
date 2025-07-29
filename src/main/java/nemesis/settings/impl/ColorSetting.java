package nemesis.settings.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import nemesis.settings.Setting;

import java.awt.*;

public class ColorSetting extends Setting<Color> {
    private final boolean Alpha;
    //private final boolean Sync;
    
    public ColorSetting(String name, Color defaultValue, boolean Alpha) {
        super(name, defaultValue);
        this.Alpha = Alpha;
    }
    
    public int getRed() {
        return value.getRed();
    }
    
    public int getGreen() {
        return value.getGreen();
    }
    
    public int getBlue() {
        return value.getBlue();
    }
    
    public int getAlpha() {
        return Alpha ? value.getAlpha() : 255;
    }
    
    @Override
    public void fromJson(JsonElement json) {
        if (json.isJsonObject()) {
            JsonObject obj = json.getAsJsonObject();
            int r = obj.has("r") ? obj.get("r").getAsInt() : 255;
            int g = obj.has("g") ? obj.get("g").getAsInt() : 255;
            int b = obj.has("b") ? obj.get("b").getAsInt() : 255;
            int a = Alpha && obj.has("a") ? obj.get("a").getAsInt() : 255;
            this.value = new Color(r, g, b, a);
        }
    }

    @Override
    public JsonElement toJson() {
        JsonObject obj = new JsonObject();
        obj.addProperty("r", value.getRed());
        obj.addProperty("g", value.getGreen());
        obj.addProperty("b", value.getBlue());
        if (Alpha) obj.addProperty("a", value.getAlpha());
        return obj;
    }
}