package nemesis.util.renderer;

import java.awt.Color;
import nemesis.util.MathUtil;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ColorUtil {
    public static Color newAlpha(Color color, int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    public static void glColor(Color color) {
        GL11.glColor4f(((float) color.getRed() / 255.0f), ((float) color.getGreen() / 255.0f), ((float) color.getBlue() / 255.0f), ((float)color.getAlpha() / 255.0f));
    }

    public static void reset() {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static Color interpolate(float value, Color start, Color end) {
        float sr = (float) start.getRed() / 255.0f;
        float sg = (float) start.getGreen() / 255.0f;
        float sb = (float) start.getBlue() / 255.0f;
        float sa = (float) start.getAlpha() / 255.0f;
        float er = (float) end.getRed() / 255.0f;
        float eg = (float) end.getGreen() / 255.0f;
        float eb = (float) end.getBlue() / 255.0f;
        float ea = (float) end.getAlpha() / 255.0f;
        float r = sr * value + er * (1.0f - value);
        float g = sg * value + eg * (1.0f - value);
        float b = sb * value + eb * (1.0f - value);
        float a = sa * value + ea * (1.0f - value);
        return new Color(r, g, b, a);
    }

    public static Color interpolate(float value, Color start, Color middle, Color end) {
        if (value < 0.5f) {
            return ColorUtil.interpolate((float)MathHelper.clamp(MathUtil.normalize(value, 0.0, 0.5), 0.0, 1.0), middle, start);
        }

        return ColorUtil.interpolate((float)MathHelper.clamp(MathUtil.normalize(value, 0.5, 1.0), 0.0, 1.0), end, middle);
    }
}

