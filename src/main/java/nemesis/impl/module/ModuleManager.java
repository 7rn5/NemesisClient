package nemesis.impl.module;

import nemesis.impl.module.Module;
//import nemesis.impl.module.client.*;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    public static final ModuleManager INSTANCE = new ModuleManager();
    public final List<Module> modules = new ArrayList<>();

    public void init() {
        //Combat
        
        //Player
        
        //Visual
        
        //Movement
        
        //Exploit
        
        //Client
          //add(new ColorManagement());
          //add(new Ui());
    }

    public void add(Module module) {
        modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }
    
    //public Module getModuleByName(String name) {
    //    for (Module module : this.modules) {
    //        if (!module.getName().equalsIgnoreCase(name)) continue;
    //        return module;
    //    }
    //    return null;
    //}
}