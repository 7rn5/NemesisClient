package nemesis.impl.gui.widget.impl;

import nemesis.impl.gui.widget.Widget;
import nemesis.settings.impl.ColorSetting;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class ColorWidget implements Widget<ColorSetting> {
    private static final int SLIDER_WIDTH = 100;
    private static final int BUTTON_WIDTH = 40;
    private static final int BUTTON_HEIGHT = HEIGHT;

    private boolean rainbow = false;

    @Override
    public void render(DrawContext context, TextRenderer textRenderer, ColorSetting setting, int x, int y, int mouseX, int mouseY) {
        int currentY = y;

        // 1. カラー選択バー（ここでは単純に現在の色を表示）
        context.fill(x, currentY, x + SLIDER_WIDTH, currentY + HEIGHT, setting.get().getRGB());
        context.drawBorder(x, currentY, SLIDER_WIDTH, HEIGHT, Color.BLACK.getRGB());
        currentY += HEIGHT + PADDING;

        // 2. レインボー切り替え
        String rainbowText = "Rainbow: " + (rainbow ? "ON" : "OFF");
        context.drawText(textRenderer, rainbowText, x, currentY, Color.WHITE.getRGB(), false);
        currentY += HEIGHT + PADDING;

        // 3. アルファスライダー表示
        int alphaWidth = (int) ((setting.get().getAlpha() / 255.0) * SLIDER_WIDTH);
        context.fill(x, currentY, x + alphaWidth, currentY + SLIDER_HEIGHT, new Color(200, 200, 200).getRGB());
        context.drawBorder(x, currentY, SLIDER_WIDTH, SLIDER_HEIGHT, Color.BLACK.getRGB());
        currentY += SLIDER_HEIGHT + PADDING;

        // 4. コピー・ペーストボタン
        // コピー
        context.fill(x, currentY, x + BUTTON_WIDTH, currentY + BUTTON_HEIGHT, new Color(80, 80, 80).getRGB());
        context.drawText(textRenderer, "Copy", x + 5, currentY + 3, Color.WHITE.getRGB(), false);
        // ペースト
        int pasteX = x + BUTTON_WIDTH + PADDING;
        context.fill(pasteX, currentY, pasteX + BUTTON_WIDTH, currentY + BUTTON_HEIGHT, new Color(80, 80, 80).getRGB());
        context.drawText(textRenderer, "Paste", pasteX + 5, currentY + 3, Color.WHITE.getRGB(), false);
    }

    @Override
    public boolean mouseClicked(ColorSetting setting, double mouseX, double mouseY, int button) {
        // クリック判定（例：コピー・ペーストのボタン範囲）
        if (button == 0) {
            // Copyボタン
            if (mouseX >= 0 && mouseX <= BUTTON_WIDTH) {
                copyToClipboard(setting.get());
                return true;
            }
            // Pasteボタン
            if (mouseX >= BUTTON_WIDTH + PADDING && mouseX <= BUTTON_WIDTH * 2 + PADDING) {
                Color pasted = pasteFromClipboard();
                if (pasted != null) setting.set(pasted);
                return true;
            }
        }
        return false;
    }

    @Override
    public void mouseDragged(ColorSetting setting, double mouseX) {
        // ここでスライダー動作（アルファや色変更）を実装できる
    }

    private void copyToClipboard(Color color) {
        String rgb = color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "," + color.getAlpha();
        StringSelection selection = new StringSelection(rgb);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
    }

    private Color pasteFromClipboard() {
        try {
            String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
            String[] parts = data.split(",");
            if (parts.length == 4) {
                int r = Integer.parseInt(parts[0]);
                int g = Integer.parseInt(parts[1]);
                int b = Integer.parseInt(parts[2]);
                int a = Integer.parseInt(parts[3]);
                return new Color(r, g, b, a);
            }
        } catch (UnsupportedFlavorException | IOException | NumberFormatException ignored) {}
        return null;
    }
    
    public static int getWHeight() {
        return Widget.HEIGHT;
    }
}