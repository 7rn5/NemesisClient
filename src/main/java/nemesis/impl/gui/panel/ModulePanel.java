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
    private boolean expanded = false;

    public ModulePanel(Module module, int x, int y) {
        this.module = module;
        this.x = x;
        this.y = y;
        
        int offsetY = 14;
        
        for (Setting<?> setting : module.getSettings()) {
            settingPanels.add(new SettingPanel<>(setting, x, y + offsetY));
            offsetY += Widget.HEIGHT + 2;
        }
    }
    
    public void render(DrawContext context, TextRenderer textRenderer, int mouseX, int mouseY) {
        // モジュール名
        context.drawText(textRenderer, module.getName(), x, y, 0xFFFFFF, false);
        
        if (expanded) {
            for (SettingPanel<?> panel : settingPanels) {
                panel.render(context, textRenderer, mouseX, mouseY);
            }
        }
    }
    
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 1 && isHovered(mouseX, mouseY)) {
            expanded = !expanded;
            return true;
        }
        if (expanded) {
            for (SettingPanel<?> panel : settingPanels) {
                if (panel.mouseClicked(mouseX, mouseY, button)) return true;
            }
        }
        return false;
    }
    
    private boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= this.x && mouseX <= this.x + Widget.WIDTH &&
                mouseY >= this.y && mouseY <= this.y + Widget.HEIGHT;
    }
}
