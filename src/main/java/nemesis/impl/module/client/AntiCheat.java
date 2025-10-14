package nemesis.impl.module.client;

import nemesis.impl.module.Module;

public class AntiCheat extends Module {
    public AntiCheat() {
        super("AntiCheat", "Anti cheat settings", Category.Client, true);
        if (this.isDisabled()) {
            this.toggle();
        }
    }
}