package nemesis.impl.gui.widget.impl;

import nemesis.impl.gui.widget.Widget;
import nemesis.settings.impl.EnumSetting;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.util.List;

public class EnumWidget<T extends Enum<T>> implements Widget<EnumSetting<T>> {
    private boolean expanded = false;

    @Override
    public void render(DrawContext context, TextRenderer textRenderer, EnumSetting<T> setting, int x, int y, int mouseX, int mouseY) {
        // 現在の値を表示
        context.drawText(textRenderer, setting.getName() + ": " + setting.get().name(), x, y, Color.WHITE.getRGB(), false);

        // 展開状態なら選択肢一覧を描画
        if (expanded) {
            List<T> values = setting.getValues();
            int offsetY = HEIGHT;
            for (T val : values) {
                context.drawText(textRenderer, "- " + val.name(), x + 4, y + offsetY, Color.LIGHT_GRAY.getRGB(), false);
                offsetY += HEIGHT;
            }
        }
    }

    @Override
    public boolean mouseClicked(EnumSetting<T> setting, double mouseX, double mouseY, int button) {
        if (button == 1) { // 右クリックで展開/閉じる
            expanded = !expanded;
            return true;
        }
        if (button == 0 && expanded) { // 左クリックで選択
            List<T> values = setting.getValues();
            // 適当にクリック位置から選択計算
            // クリック位置→index計算
            return true;
        }
        return false;
    }

    @Override
    public void mouseDragged(EnumSetting<T> setting, double mouseX) {
        // なし
    }
    
    public static int getWHeight() {
        return Widget.HEIGHT;
    }
}