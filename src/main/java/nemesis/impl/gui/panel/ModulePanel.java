package nemesis.impl.gui.panel;

import nemesis.impl.gui.widget.Widget;
import nemesis.impl.module.Module;
import nemesis.settings.Setting;
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
        int offsetY = 14; // モジュールタイトルの高さ
        for (Setting<?> setting : module.getSettings()) {
            // Setting に対応する Widget を生成するファクトリーを呼ぶ
            settingPanels.add(new SettingPanel<>(setting, x + 5, y + offsetY));
            //settingPanels.add(new SettingPanel<>(setting, WidgetFactory.create(setting), x + 5, y + offsetY));
            offsetY += Widget.HEIGHT + 2;
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