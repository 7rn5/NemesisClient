/*package nemesis.settings.impl;

//import com.google.gson.JsonElement;
//import com.google.gson.JsonPrimitive;
import nemesis.settings.Setting;
import java.util.*;

public class EnumSetting<T extends Enum<T>> extends Setting<T> {
    private final List<T> values;
    
    public EnumSetting(String name, T defaultValue) {
        super(name, defaultValue);
        this.values = Arrays.asList(defaultValue.getDeclaringClass().getEnumConstants());
    }
    
    public void cycle() {
        int index = (value.ordinal() + 1) % values.length;
        value = values[index];
    }
    
    public List<T> getValues() {
        return values;
    }
    
    //@Override
    //public void fromJson(JsonElement json) {
    //    if (json.isJsonPrimitive()) {
    //        String name = json.getAsString();
    //        for (T constant : values) {
    //            if (constant.name().equalsIgnoreCase(name)) {
    //                value = constant;
    //                return;
    //            }
    //        }
    //    }
    //}
    
    //@Override
    //public JsonElement toJson() {
    //    return new JsonPrimitive(value.name());
    //}
}*/


package nemesis.settings.impl;

import nemesis.settings.Setting;
import java.util.Arrays;
import java.util.List;

public class EnumSetting<T extends Enum<T>> extends Setting<T> {
    private final List<T> values;

    public EnumSetting(String name, T defaultValue) {
        super(name, defaultValue);
        this.values = Arrays.asList(defaultValue.getDeclaringClass().getEnumConstants());
    }

    public List<T> getValues() {
        return values;
    }

    public void next() {
        int index = values.indexOf(get()) + 1;
        if (index >= values.size()) index = 0;
        set(values.get(index));
    }

    public void previous() {
        int index = values.indexOf(get()) - 1;
        if (index < 0) index = values.size() - 1;
        set(values.get(index));
    }
}