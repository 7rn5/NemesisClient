package nemesis.impl.gui.panel;

import nemesis.impl.gui.widget.Widget;
import nemesis.impl.module.Module;
import nemesis.settings.Setting;
import nemesis.settings.impl.*;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

public class ModulePanel {
    private final Module module;
    private final List<SettingPanel<?>> settingPanels = new ArrayList<>();
    private int x, y;

    public ModulePanel(Module module, int x, int y) {
        this.module = module;
        this.x = x;
        this.y = y;

        // モジュールの settings を SettingPanel に変換して追加
        int offsetY = 20; // モジュールタイトルの高さ
        //int yOffset = 14;
        
        for (Setting<?> setting : module.getSettings()) {
            /*if (setting instanceof BoolSetting) {
            *    //drawBoolWidget(context, x, offsetY, (BoolSetting) setting);
            *    offsetY += Widget.HEIGHT + Widget.PADDING;
            *} else if (setting instanceof BindSetting) {
            *    //drawBindWidget(context, x, offsetY, (BindSetting) setting);
            *    offsetY += Widget.HEIGHT + Widget.PADDING;
            *} else if (setting instanceof ColorSetting) {
            *    //drawColorWidget(context, x, offsetY, (ColorSetting) setting);
            *    offsetY += Widget.HEIGHT * 3 + Widget.PADDING;
            }*/
            
            settingPanels.add(new SettingPanel<>(setting, x, y + offsetY));
            offsetY += Widget.HEIGHT + 15;
        }
    }

    public void render(DrawContext context, TextRenderer textRenderer, int mouseX, int mouseY) {
        // モジュール名
        context.drawText(textRenderer, module.getName(), x, y, 0xFFFFFF, false);

        for (SettingPanel<?> panel : settingPanels) {
            panel.render(context, textRenderer, mouseX, mouseY);
        }
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (SettingPanel<?> panel : settingPanels) {
            if (panel.mouseClicked(mouseX, mouseY, button)) return true;
        }
        return false;
    }
}