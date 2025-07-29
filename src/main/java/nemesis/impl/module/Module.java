/*package nemesis.impl.module;

import nemesis.settings.Setting;
//import nemesis.setting.impl.BindSetting;
//import nemesis.impl.module.Modules;
//import net.minecraft.client.util.InputUtil;
//import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    private final String name;
    private final Category category;
    private final String description;
    //public final BindSetting bind = new BindSetting("Keybind", new Bind(-1));
    //public final BindSetting bind = new BindSetting("Keybind", -1);

    private boolean enabled = false;
    //private final List<Setting<?>> settings = new ArrayList<>();
    //private static final List<Module> modules = new ArrayList<>();

    //public final BindSetting bind = new BindSetting("Key", -1); 

    public Module(String name, Category category, String description) {
        this.name = name;
        this.category = category;
        this.description = description;

        //add(bind); 
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }
    
    //public static List<Module> getModules() {
    //    return Collections.unmodifiableList(modules);
    //}

    //protected <T extends Setting<?>> T add(T setting) {
    //    settings.add(setting);
    //    return setting;
    //}

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        if (this.enabled != enabled) {
            this.enabled = enabled;
            if (enabled) {
                onEnable();
            } else {
                onDisable();
            }
        }
    }

    public void toggle() {
        setEnabled(!enabled);
    }

    public void onEnable() {}
    public void onDisable() {}
    
    public Integer getBind() {
        return this.bind.get();
    }
    
    public void setBind(int key) {
        this.bind.set(key);
    }

    public List<Setting<?>> getSettings() {
        return settings;
    }

    protected <T extends Setting<?>> T add(T setting) {
        settings.add(setting);
        return setting;
    }
    
    //Category list
    public enum Category {
        Combat,
        Player,
        Visual,
        Movement,
        Exploit,
        Client
    }
}
*/

package nemesis.impl.module;

import nemesis.settings.Setting;
import nemesis.settings.impl.BindSetting;

import java.util.List;
import java.util.ArrayList;

public abstract class Module {
    private final String name, description;
    private Category category;
    private boolean alwaysEnable;
    private boolean enabled = false;
    private final BindSetting Bind;
    
    private final List<Setting<?>> settings = new ArrayList<>();
    
    public Module(String name, String description, Category category, boolean alwaysEnable) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.alwaysEnable = alwaysEnable;
        
        this.Bind = new BindSetting("bind", BindSetting.KEY_NONE);
        this.settings.add(Bind);
    }
    
    public String getName() {
        return name;
    }
    
    public Category getCategory() {
        return category;
    }
    
    public void setEnabled(boolean enabled) {
        if (this.enabled != enabled) {
            this.enabled = enabled;
            if (enabled) {
                onEnabled();
            } else {
                onDisabled();
            }
        }
    }
    
    public boolean isEnabled() {
        return !enabled;
    }
    
    private void onEnabled() {}
    private void onDisabled() {}
    
    public void toggle() {
        setEnabled(!enabled);
    }
    
    public List<Setting<?>> getSettings() {
        return settings;
    }
    
    public enum Category {
        Combat,
        Player,
        Visual,
        Movement,
        Exploit,
        Client
    }
}