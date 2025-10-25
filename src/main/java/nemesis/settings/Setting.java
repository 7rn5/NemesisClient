package nemesis.settings;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Setting<T> {
    protected final String name;
    protected final String description;
    protected final T defaultValue;
    protected final Predicate<T> visible;
    protected final Consumer<T> onChanged;
    protected T value;
    
    protected <V> Setting(Builder<V, ?> builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.defaultValue = (T) builder.defaultValue;
        this.visible = (Predicate<T>) builder.visible;
        this.onChanged = (Consumer<T>) builder.onChanged;
        this.value = (T) builder.defaultValue;
    }
    
    public T get() {
        return value;
    }
    
    public void set(T value) {
        this.value = value;
        if (onChanged != null) onChanged.accept(value);
    }
    
    public void reset() {
        set(defaultValue);
    }
    
    public boolean isVisible() {
        return visible == null || visible.test(value);
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public T getDefaultValue() {
        return defaultValue;
    }
    
    public static class Builder<T, S extends Builder<T, S>> {
        protected String name;
        protected String description = "";
        protected T defaultValue;
        protected Predicate<T> visible;
        protected Consumer<T> onChanged;
        
        public S name(String name) {
            this.name = name;
            return (S) this;
        }
        
        public S description(String description) {
            this.description = description;
            return (S) this;
        }
        
        public S defaultValue(T value) {
            this.defaultValue = value;
            return (S) this;
        }
        
        public S visible(Predicate<T> visible) {
            this.visible = visible;
            return (S) this;
        }
        
        public S onChanged(Consumer<T> onChanged) {
            this.onChanged = onChanged;
            return (S) this;
        }
        
        public Setting<T> build() {
            if (name == null) throw new IllegalStateException("Setting must have a name");
            if (defaultValue == null) throw new IllegalStateException("Default value must be set");
            return new Setting<>(this);
        }
    }
}