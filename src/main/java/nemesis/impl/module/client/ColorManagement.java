package nemesis.impl.module.client;

import nemesis.impl.module.Module;
import nemesis.settings.impl.BoolSetting;
import nemesis.settings.impl.ColorSetting;
import nemesis.settings.impl.DoubleSetting;

import java.awt.*;

public class ColorManagement extends Module {

    public ColorManagement() {
        super("ColorManagement", "management to your color", Category.Client, true);
        if (this.isDisabled())
            this.toggle();
    }
}