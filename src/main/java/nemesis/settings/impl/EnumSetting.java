package nemesis.settings.impl;

import nemesis.settings.Setting;
import java.util.Arrays;
import java.util.List;

public class EnumSetting<T extends Enum<T>> extends Setting<T> {
    private final List<T> values;
    
    protected EnumSetting(Builder<T> builder) {
        super(builder);
        this.values = Arrays.asList(builder.enumClass.getEnumConstants());
        this.value = values.get(0);
    }
    
    public List<T> getValues() {
        return values;
    }
    
    public void next() {
        int index = values.indexOf(get()) + 1;
        if (index >= values.size()) index = 0;
        set(values.get(index));
    }
    
    public void previous() {
        int index = values.indexOf(get()) - 1;
        if (index < 0) index = values.size() - 1;
        set(values.get(index));
    }
    
    public int getSize() {
        return values.size();
    }
    
    public static class Builder<T extends Enum<T>> extends Setting.Builder<T, Builder<T>> {
        private Class<T> enumClass;
        
        public Builder<T> enumClass(Class<T> enumClass) {
            this.enumClass = enumClass;
            return this;
        }
        
        public EnumSetting<T> build() {
            if (enumClass == null) throw new IllegalStateException("EnumSetting requires an enum class");
            return new EnumSetting<>(this);
        }
    }
}