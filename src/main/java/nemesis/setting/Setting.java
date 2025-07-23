package nemesis.setting;

import java.util.function.BooleanSupplier;

public abstract class Setting<T> {
    public final String name;
    public final BooleanSupplier visible;
    
    public final String getName() {
         return this.name;
    }
    
    public Setting(String name) {
        this.name = name;
        this.visible = null;
    }
    
    public abstract T get();
    public abstract void set(T value);
}
