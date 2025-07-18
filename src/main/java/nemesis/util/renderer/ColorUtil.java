package nemesis.util.renderer;

import java.awt.*;

public class ColorUtil {

    // RGBA → int（不透明）
    public static int toRGBA(int r, int g, int b) {
        return toRGBA(r, g, b, 255);
    }

    // RGBA → int（指定アルファ）
    public static int toRGBA(int r, int g, int b, int a) {
        return (a & 0xFF) << 24 |
               (r & 0xFF) << 16 |
               (g & 0xFF) << 8 |
               (b & 0xFF);
    }

    // float → int RGBA変換（0.0f ~ 1.0f）
    public static int toRGBA(float r, float g, float b, float a) {
        return toRGBA((int)(r * 255), (int)(g * 255), (int)(b * 255), (int)(a * 255));
    }

    // java.awt.Color → int
    public static int toRGBA(Color color) {
        return toRGBA(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    // Rainbowカラー（Hue回転）
    public static int rainbow(float speed, float saturation, float brightness, int offset) {
        float hue = ((System.currentTimeMillis() + offset) % (int)speed) / speed;
        return Color.HSBtoRGB(hue, saturation, brightness);
    }

    // グラデーション（2色間補間）
    public static int fadeBetween(int color1, int color2, float progress) {
        float inverse = 1.0f - progress;

        int r = (int)((getRed(color1) * inverse) + (getRed(color2) * progress));
        int g = (int)((getGreen(color1) * inverse) + (getGreen(color2) * progress));
        int b = (int)((getBlue(color1) * inverse) + (getBlue(color2) * progress));
        int a = (int)((getAlpha(color1) * inverse) + (getAlpha(color2) * progress));

        return toRGBA(r, g, b, a);
    }

    // 明るさ調整
    public static int adjustBrightness(int color, float factor) {
        int r = clamp((int)(getRed(color) * factor), 0, 255);
        int g = clamp((int)(getGreen(color) * factor), 0, 255);
        int b = clamp((int)(getBlue(color) * factor), 0, 255);
        int a = getAlpha(color);
        return toRGBA(r, g, b, a);
    }

    // 各成分の取得
    public static int getRed(int color)   { return (color >> 16) & 0xFF; }
    public static int getGreen(int color) { return (color >> 8) & 0xFF; }
    public static int getBlue(int color)  { return color & 0xFF; }
    public static int getAlpha(int color) { return (color >> 24) & 0xFF; }

    // 安全な範囲に制限
    public static int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }
}

//一応chat gptに作らせたやつだからうごくかは知らん
//にがーしね