package nemesis.impl.module;

import nemesis.event.bus.Subscribe;
import nemesis.event.TickEvent;
import nemesis.settings.Setting;
import nemesis.settings.impl.BindSetting;
import nemesis.util.player.ChatUtil;

import java.util.*;

import static nemesis.NemesisClient.eventHandler;

public abstract class Module {
    private final String name, description;
    private Category category;
    private boolean enabled = false;
    private boolean antiDisable = false;
    private final BindSetting Bind;
    private final List<Module> modules = new ArrayList<>();
    private final List<Setting<?>> settings = new ArrayList<>();
    
    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        
        this.Bind = addSetting(((BindSetting.Builder) new BindSetting.Builder()
            .name("Bind")
            .defaultValue(BindSetting.KEY_NONE))
            .build()
        );
    }
    
    public Module(String name, String description, Category category, boolean antiDisable) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.antiDisable = antiDisable;
        
        this.Bind = addSetting(((BindSetting.Builder) new BindSetting.Builder()
            .name("Bind")
            .defaultValue(BindSetting.KEY_NONE))
            .build()
        );
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
                int id = getName().hashCode();
                ChatUtil.info("§a[+]§f" + getName(), id);
                eventHandler.subscribe(this);
            } else {
                onDisabled();
                int id = getName().hashCode();
                ChatUtil.info("§c[-]§f" + getName(), id);
                eventHandler.unsubscribe(this);
            }
        }
    }
    
    public boolean isEnabled() {
        return enabled;
    }
    
    public boolean isDisabled() {
        return !enabled;
    }
    
    public boolean canDisable() {
        return !antiDisable;
    }
    
    public void onEnabled() {}
    
    public void onDisabled() {}
    
    public void enable() {
        setEnabled(true);
    }

    public void disable() {
        setEnabled(false);
    }
    
    public void toggle() {
        setEnabled(!enabled);
    }
    
    public int getBindKey() {
        return this.Bind.get();
    }
    
    public List<Setting<?>> getSettings() {
        return settings;
    }
    
    public <T extends Setting<?>> T addSetting(Setting<?> setting) {
        settings.add(setting);
        return (T) setting;
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