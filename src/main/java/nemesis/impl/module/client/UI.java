package nemesis.impl.module.client;

import nemesis.impl.module.Module;
import org.lwjgl.glfw.GLFW;

public class Ui extends Module {
    public Ui() {
        super("UI", Category.Client, "Management to your click gui");
        setBind(GLFW.GLFW_KEY_RIGHT_SHIFT);
    }
}