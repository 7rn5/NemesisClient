package nemesis.impl.module;

import nemesis.setting.Setting;
//import nemesis.impl.module.Modules;
//import nemesis.setting.impl.BindSetting;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    private final String name;
    private final Category category;
    private final String description;

    private boolean enabled = false;
    private final List<Setting<?>> settings = new ArrayList<>();
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
