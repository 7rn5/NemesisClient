package nemesis.settings.impl;

//import com.google.gson.JsonElement;
//import com.google.gson.JsonPrimitive;
import nemesis.settings.Setting;

public class BoolSetting extends Setting<Boolean> {
    public BoolSetting(String name, boolean defaultValue) {
        super(name, defaultValue);
    }
    
    public void toggle() {
        set(!value);
    }
    
    //@Override
    //public void fromJson(JsonElement json) {
    //    if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isBoolean()) {
    //        this.value = json.getAsBoolean();
    //    }
    //}

    //@Override
    //public JsonElement toJson() {
    //    return new JsonPrimitive(value);
    //}
}