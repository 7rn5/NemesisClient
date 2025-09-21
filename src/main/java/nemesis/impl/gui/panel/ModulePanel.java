package nemesis.impl.gui.panel;

import nemesis.impl.gui.panel.CategoryPanel;
import nemesis.impl.gui.widget.Widget;
import nemesis.impl.module.Module;
import nemesis.settings.Setting;
import nemesis.settings.impl.*;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

import java.awt.Color;

public class ModulePanel {
    private final Module module;
    private final List<SettingPanel<?>> settingPanels = new ArrayList<>();
    private int x, y;
    private boolean expanded = false;

    public ModulePanel(Module module, int x, int y) {
        this.module = module;
        this.x = x;
        this.y = y;
        
        int offsetY = 28;
        
        for (Setting<?> setting : module.getSettings()) {
            settingPanels.add(new SettingPanel<>(setting, x, y + offsetY));
            offsetY += Widget.HEIGHT + 3;
        }
    }
    
    public void render(DrawContext context, TextRenderer textRenderer, int mouseX, int mouseY) {
        // モジュール名
        context.drawText(textRenderer, module.getName(), x, y, 0xFFFFFF, false);
        
        context.drawBorder(x - 2, y - 2, Widget.WIDTH - 4, Widget.HEIGHT - 1, Color.WHITE.getRGB());
        
        if (expanded) {
            for (SettingPanel<?> panel : settingPanels) {
                panel.render(context, textRenderer, mouseX, mouseY + 2);
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
        return mouseX >= this.x - 2 && mouseX <= this.x + Widget.WIDTH - 4 &&
                mouseY >= this.y - 2 && mouseY <= this.y + Widget.HEIGHT - 1;
    }
}
