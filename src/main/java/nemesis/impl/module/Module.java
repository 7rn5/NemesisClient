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

import java.util.*;

public abstract class Module {
    private final String name, description;
    private Category category;
    private boolean enabled = false;
    private final BindSetting Bind;
    private final List<Module> modules = new ArrayList<>();
    private final List<Setting<?>> settings = new ArrayList<>();
    
    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        
        this.Bind = new BindSetting("bind", BindSetting.KEY_NONE);
        this.settings.add(Bind);
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
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
    
    public void onEnabled() {}
    public void onDisabled() {}
    public void onToggle() {}
    
    public void enable() {
        setEnabled(true);
        this.onToggle();
        this.onEnabled();
    }

    public void disable() {
        setEnabled(false);
        this.onToggle();
        this.onDisabled();
    }
    
    public void toggle() {
        setEnabled(!enabled);
    }
    
    public List<Setting<?>> getSettings() {
        return settings;
    }
    
    public <T extends Setting<?>> T addSetting(T setting) {
        settings.add(setting);
        return setting;
    }
    public enum Category {
        Combat("Combat"),
        Player("Player"),
        Visual("Visual"),
        Movement("Movement"),
        Exploit("Exploit"),
        Client("Client");
        
        private final String name;
        Category(String name) {
            this.name = name;
        }
        
        public String getName() {
            return this.name;
        }
        
        public static List<Category> getAll() {
            return Arrays.asList(values());
        }
    }
}