package nemesis.impl.gui.widget.impl;

import nemesis.impl.gui.widget.Widget;
import nemesis.settings.impl.BindSetting;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import org.lwjgl.glfw.GLFW;

import java.awt.Color;

import static nemesis.NemesisClient.mc;

public class BindWidget implements Widget<BindSetting> {
    private boolean listening = false; // キー入力待機中フラグ

    @Override
    public void render(DrawContext context, TextRenderer textRenderer, BindSetting setting, int x, int y, int mouseX, int mouseY) {
        // 背景色
        int bgColor = listening ? new Color(50, 50, 150, 150).getRGB() : new Color(50, 50, 50, 150).getRGB();
        context.fill(x, y, x + WIDTH, y + HEIGHT, bgColor);

        // 枠線
        context.drawBorder(x, y, WIDTH, HEIGHT, Color.BLACK.getRGB());

        // 表示テキスト
        String keyName = setting.getKeyName();
        String label = setting.getName() + ": " + (listening ? "Press a key..." : keyName);
        context.drawText(textRenderer, label, x + PADDING, y + (HEIGHT - textRenderer.fontHeight) / 2, Color.WHITE.getRGB(), false);
    }

    @Override
    public boolean mouseClicked(BindSetting setting, double mouseX, double mouseY, int button) {
        // 左クリックでリスニング開始
        if (button == 0 && mouseX >= 0 && mouseX <= WIDTH && mouseY >= 0 && mouseY <= HEIGHT) {
            listening = !listening;
            return true;
        }
        return false;
    }

    @Override
    public void mouseDragged(BindSetting setting, double mouseX) {
        // ドラッグは不要
    }

    // このメソッドはGUI側で呼び出す想定（キーイベント時）
    public boolean keyPressed(BindSetting setting, int keyCode, int scanCode, int modifiers) {
        if (!listening) return false;

        if (keyCode == GLFW.GLFW_KEY_ESCAPE) { // ESCで解除
            setting.set(BindSetting.KEY_NONE);
        } else {
            setting.set(keyCode);
        }

        listening = false;
        return true;
    }
}