package nemesis.impl.module.client;

import nemesis.impl.gui.ClickGui;
import nemesis.impl.module.Module;
import nemesis.settings.impl.BoolSetting;
import nemesis.settings.impl.ColorSetting;
import nemesis.settings.impl.DoubleSetting;
import nemesis.settings.impl.EnumSetting;

import static nemesis.NemesisClient.mc;

import org.lwjgl.glfw.GLFW;
import java.awt.Color;

public class Ui extends Module {
    public final BoolSetting fill = addSetting(new BoolSetting.Builder()
        .name("ModuleFill")
        .defaultValue(true)
        .build()
    );
    
    public final BoolSetting textShadow = addSetting(new BoolSetting.Builder()
        .name("TextShadow")
        .defaultValue(true)
        .build()
    );
    
    public final BoolSetting background = addSetting(new BoolSetting.Builder()
        .name("BackGround")
        .defaultValue(true)
        .build()
    );
    
    public final BoolSetting descriptions = addSetting(new BoolSetting.Builder()
        .name("Descriptions")
        .defaultValue(false)
        .build()
    );
    
    public final BoolSetting bounce = addSetting(new BoolSetting.Builder()
        .name("Bounce")
        .defaultValue(true)
        .build()
    );
    
    public final ColorSetting color = addSetting(new ColorSetting.Builder()
        .name("Color")
        .defaultValue(new Color(170, 170, 170, 255))
        .alpha(true)
        .build()
    );
    
    public Ui() {
        super("UI", "Management to your click gui", Category.Client, true);
    }
}

/*
// ===== BoolSetting =====
private final BoolSetting exampleBool = new BoolSetting.Builder()
    .name("Example Bool")
    .description("true/false を切り替える設定")
    .defaultValue(false)
    .build();


// ===== DoubleSetting =====
private final DoubleSetting exampleDouble = new DoubleSetting.Builder()
    .name("Example Double")
    .description("スライダーで数値を設定する")
    .defaultValue(1.0)
    .min(0.1)
    .max(10.0)
    .build();


// ===== EnumSetting =====
public enum Mode {
    Normal, Fast, Silent
}

private final EnumSetting<Mode> exampleEnum = new EnumSetting.Builder<Mode>()
    .name("Example Mode")
    .description("モードを切り替える設定")
    .defaultValue(Mode.Normal)
    .build();


// ===== ColorSetting =====
private final ColorSetting exampleColor = new ColorSetting.Builder()
    .name("Example Color")
    .description("GUIやHUDなどの色設定")
    .defaultValue(new Color(255, 100, 100, 255))
    .alpha(true)   // アルファを使うか
    .sync(false)   // 他と同期するか
    .build();


// ===== BindSetting =====
private final BindSetting exampleBind = new BindSetting.Builder()
    .name("Example Bind")
    .description("キーを設定する")
    .defaultValue(GLFW.GLFW_KEY_RSHIFT) // 初期キー設定
    .holdMode(false) // ホールド式にするか
    .build();
*/