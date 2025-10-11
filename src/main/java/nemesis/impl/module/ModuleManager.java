package nemesis.impl.module;

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

public class ModuleManager {
    public static final ModuleManager INSTANCE = new ModuleManager();
    public final List<Module> modules = new ArrayList<>();
    private final Map<Module.Category, List<Module>> categoryMap = new HashMap<>();

    public void init() {
        //Combat
          add(new Offhand());
        //Misc
          add(new Announcer());
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
          add(new ColorManagement());
          add(new Ui());
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
        //return categoryMap.get(category);
        return categoryMap.getOrDefault(category, new ArrayList<>());
    }
    
    public int getCountCategory(Module.Category category) {
        List<Module> targetCategory = categoryMap.getOrDefault(category, new ArrayList<>());
        return targetCategory.size();
    }
}
