package nemesis.impl.module;

import nemesis.event.bus.Subscribe;
import nemesis.event.KeyEvent;
import nemesis.impl.module.Module;
import nemesis.impl.module.client.*;
import nemesis.impl.module.combat.*;
import nemesis.impl.module.exploit.*;
import nemesis.impl.module.misc.*;
import nemesis.impl.module.movement.*;
import nemesis.impl.module.player.*;
import nemesis.impl.module.visual.*;

import java.util.*;
import java.util.stream.Collectors;

import static nemesis.NemesisClient.eventHandler;

public class ModuleManager {
    public final List<Module> modules = new ArrayList<>();
    private final Map<Module.Category, List<Module>> categoryMap = new HashMap<>();

    public void init() {
        //Combat
            add(new Offhand());
        //Misc
            add(new Announcer());
            add(new SplashText());
        //Visual
            add(new NoRender());
        //Movement
            add(new NoJumpDelay());
            add(new NoSlow());
            add(new Sprint());
            add(new Velocity());
        //Player
            add(new SpeedMine());
        //Exploit
            add(new Multitask());
        //Client
            add(new AntiCheat());
            add(new ColorManagement());
            add(new Notification());
            add(new Ui());
        
        //register event
            eventHandler.subscribe(this);
    }

    public void add(Module module) {
        modules.add(module);
        categoryMap.computeIfAbsent(module.getCategory(), k -> new ArrayList<>()).add(module);
    }
    
    public Module get(String name) {
        for (Module module : this.modules) {
            if (!module.getName().equalsIgnoreCase(name)) continue;
            return module;
        }
        return null;
    }
    
    public <T extends Module> T get(Class<T> clazz) {
        for (Module module : this.modules) {
            if (!clazz.isInstance(module)) continue;
            return (T) module;
        }
        return null;
    }
    
    public void enableModule(Class<Module> clazz) {
        Module module = this.get(clazz);
        if (module != null) {
            module.enable();
        }
    }
    
    public void disableModule(Class<Module> clazz) {
        Module module = this.get(clazz);
        if (module != null) {
            module.disable();
        }
    }

    public List<Module> getModules() {
        return modules;
    }
    
    public List<Module.Category> getCategories() {
        return Arrays.asList(Module.Category.values());
    }
    
    public List<Module> getByCategory(Module.Category category) {
        return categoryMap.getOrDefault(category, new ArrayList<>());
    }
    
    public int getCountCategory(Module.Category category) {
        List<Module> targetCategory = categoryMap.getOrDefault(category, new ArrayList<>());
        return targetCategory.size();
    }
    
    @Subscribe
    public void onKeyPressed(KeyEvent event) {
        int key = event.getKey();
        if (key >= 0) return;
        modules.forEach(module -> {
            if (module.getBindKey() == key) {
                module.toggle();
            }
        });
    }
}
