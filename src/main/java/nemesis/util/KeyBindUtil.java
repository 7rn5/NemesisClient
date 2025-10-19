package nemesis.util;

import nemesis.mixin.KeyBindingAccessor;
import net.minecraft.client.option.KeyBinding;

import java.util.Map;

import static nemesis.NemesisClient.openGuiKey;

public class KeyBindUtil {
    public static KeyBinding[] apply(KeyBinding[] binds) {
        Map<String, Integer> categories = KeyBindingAccessor.getCategoryOrderMap();
        int highest = 0;
        for (int i : categories.values()) {
            if (i > highest) highest = i;
        }
        categories.put("Nemesis", highest + 1);
        
        KeyBinding[] newBinds = new KeyBinding[binds.length + 1];
        System.arraycopy(binds, 0, newBinds, 0, binds.length);
        newBinds[binds.length] = openGuiKey;
        return newBinds;
    }
}