package nemesis.setting.impl;

import nemesis.manager.client.ModuleManager;
import nemesis.setting.Setting;

public class BooleanSetting extends Setting {
    public Runnable task = null;
    public boolean injectTask = false;
    private boolean value;
    public final boolean defaultValue;
    
    public BooleanSetting(String name, boolean defaultValue) {
        super(name);
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }
    
    public final boolean get() { //getValueよりgetのほうが短いからgetにしてる
		    return this.value;
	  }
	  
	  public final void set(boolean value) {
		    if (injectTask && value != this.value) {
			      task.run();
		    }
		    this.value = value;
	  }

	  public final void toggle() {
		    set(!value);
	  }
	  
	  public BooleanSetting injectTask(Runnable task) {
		    this.task = task;
		    injectTask = true;
		    return this;
	  }
}

// example
// public final BooleanSetting exampleBoolean = this.add(new BooleanSetting("ExampleBoolean", true));
