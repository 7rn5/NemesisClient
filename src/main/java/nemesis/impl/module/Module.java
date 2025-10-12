package nemesis.impl.module;

import nemesis.event.bus.Subscribe;
import nemesis.event.TickEvent;
import nemesis.settings.Setting;
import nemesis.settings.impl.BindSetting;
import nemesis.util.player.ChatUtil;

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
    
    public void onToggle() {
        int id = getName().hashCode();
        if (isEnabled()) {
            ChatUtil.info("§a[+]" + getName(), id);
        } else {
            ChatUtil.info("§c[-]" + getName(), id);
        }
    }
    
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
        Misc("Misc"),
        Visual("Visual"),
        Movement("Movement"),
        Player("Player"),
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