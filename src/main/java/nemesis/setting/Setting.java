package nemesis.setting;

import java.util.function.BooleanSupplier;

public abstract class Setting<T> {
    public final String name;
    //public final BooleanSupplier visible;
    
    public final String getName() {
         return this.name;
    }
    
    public Setting(String name) {
        this.name = name;
        //this.visible = true;
    }
    
    public abstract T get();
    public abstract void set(T value);
    
    public static class VisibilityCondition implements BooleanSupplier {
        private boolean visible = true;
        
        @Override
        public boolean getAsBoolean() {
            return visible;
        }
        
        public void setVisible(boolean visible) {
            this.visible = visible;
        }
        
        public void update() {}
    }
}
