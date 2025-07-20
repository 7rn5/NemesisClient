package nemesis.setting;

public abstract class Setting {
    private final String name;
    
    public final String getName() {
         return this.name;
    }
    
    public Setting(String name) {
        this.name = name;
    }
}