package nemesis.impl.gui.render.impl;

import nemesis.impl.gui.render.SettingRenderer;
import nemesis.impl.module.client.Ui;
import nemesis.impl.module.client.ColorManagement;
import nemesis.settings.impl.BindSetting;
import nemesis.util.KeyUtil;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import org.lwjgl.glfw.GLFW;
import java.awt.*;

import static nemesis.NemesisClient.moduleManager;
import static nemesis.impl.gui.api.GuiConstants.*;

public class BindSettingRenderer implements SettingRenderer<BindSetting> {
    private static BindSetting waitingForKeyBind = null;

    @Override
    public void render(DrawContext context, TextRenderer textRenderer, BindSetting setting, int x, int y, int mouseX, int mouseY) {
        boolean hovered = isHovered(mouseX, mouseY, x, y);
        Color primary = getColorModule().getStyledGlobalColor();
        Color textCol = moduleManager.get(Ui.class).moduleFill.get()
        //Color textCol = MODULE_MANAGER.getStorage().getByClass(ClickGuiModule.class).moduleFill.get()
                ? new Color(255, 255, 255, 255)
                : new Color(primary.getRed(), primary.getGreen(), primary.getBlue(), 255);
        Color bgColor = new Color(30, 30, 30, 0);

        context.fill(x, y, x + WIDTH, y + HEIGHT, toRGBA(bgColor));

        int lineOffset = 1;
        context.fill(
                x - 1,
                y - lineOffset,
                x,
                y + HEIGHT,
                primary.getRGB()
        );

        int textX = x + PADDING + (hovered ? 1 : 0);
        int textY = y + (HEIGHT - 8) / 2;

        context.drawText(
                textRenderer,
                setting.getName(),
                textX,
                textY,
                toRGBA(textCol),
                false
        );

        String valueStr = waitingForKeyBind == setting ? "Press a key..." : KeyUtil.getKeyName(setting.get());

        context.drawText(
                textRenderer,
                valueStr,
                x + WIDTH - PADDING - textRenderer.getWidth(valueStr),
                textY,
                toRGBA(textCol),
                false
        );
    }

    @Override
    public boolean mouseClicked(BindSetting setting, double mouseX, double mouseY, int button) {
        if (waitingForKeyBind == null) {
            waitingForKeyBind = setting;
        } else if (waitingForKeyBind == setting) {
            waitingForKeyBind = null;
        }
        return true;
    }

    @Override
    public void mouseDragged(BindSetting setting, double mouseX) {
    }

    public static boolean keyPressed(int keyCode) {
        if (waitingForKeyBind != null) {
            if (keyCode == GLFW.GLFW_KEY_DELETE) {
                waitingForKeyBind.set(-1);
            } else {
                waitingForKeyBind.set(keyCode);
            }
            waitingForKeyBind = null;
            return true;
        }
        return false;
    }

    private float approach(float current, float target, float maxDelta) {
        if (current < target) {
            current += maxDelta;
            if (current > target) current = target;
        } else if (current > target) {
            current -= maxDelta;
            if (current < target) current = target;
        }
        return current;
    }

    private static boolean isHovered(double mouseX, double mouseY, int x, int y) {
        return mouseX >= x && mouseX <= x + WIDTH && mouseY >= y && mouseY <= y + HEIGHT;
    }

    private ColorManagement getColorModule() {
        //return MODULE_MANAGER.getStorage().getByClass(ColorModule.class);
        return moduleManager.get(ColorManagement.class);
    }
}
