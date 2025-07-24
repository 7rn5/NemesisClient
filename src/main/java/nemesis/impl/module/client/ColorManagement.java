package nemesis.impl.module.client;

import nemesis.impl.module.Module;
import nemesis.setting.Setting;
import nemesis.setting.impl.ColorSetting;
import nemesis.setting.impl.GroupSetting;

import java.awt.Color;

public class ColorManagement extends Module {
    public final GroupSetting general = new GroupSetting("General");
    
    public final ColorSetting friendColor = new ColorSetting(
        "FriendColor",
        new Color(255, 0, 0)/*,
        new GroupSetting(general)*/
    );
    
    public final ColorSetting enemyColor = new ColorSetting(
        "EnemyColor",
        new Color(255, 0, 0)/*,
        new GroupSetting(general)*/
    );
    
    public final ColorSetting defaultColor = new ColorSetting(
        "DefaultColor",
        new Color(255, 0, 0)/*,
        new GroupSetting(general)*/
    );
    
    public ColorManagement() {
        super("ColorManagement", Category.Client, "ColorManager");
    }
}