package nemesis.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class MathUtil {
    public static float rad(float angle) {
        return (float)((double)angle * Math.PI / 180.0);
    }

    public static double normalize(double value, double min, double max) {
        return (value - min) / (max - min);
    }

    public static double roundToPlaces(double number, int places) {
        BigDecimal decimal = new BigDecimal(number);
        decimal = decimal.setScale(places, RoundingMode.HALF_UP);
        return decimal.doubleValue();
    }

    public static int randomInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    public static double random(double min, double max) {
        return Math.random() * (max - min) + min;
    }

    public static float random(float min, float max) {
        return (float)(Math.random() * (double)(max - min) + (double)min);
    }

    public static String getOrdinal(int i) {
        String[] suffixes = new String[]{"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
        return switch (i % 100) {
            case 11, 12, 13 -> "th";
            default -> suffixes[i % 10];
        };
    }

    public static int clamp(int num, int min, int max) {
        return num < min ? min : Math.min(num, max);
    }

    public static float clamp(float num, float min, float max) {
        return num < min ? min : Math.min(num, max);
    }

    public static double clamp(double num, double min, double max) {
        return num < min ? min : Math.min(num, max);
    }

    public static double round(double value, int places) {
        return places < 0 ? value : new BigDecimal(value).setScale(places, RoundingMode.HALF_UP).doubleValue();
    }

    public static float round(float value, int places) {
        return places < 0 ? value : new BigDecimal(value).setScale(places, RoundingMode.HALF_UP).floatValue();
    }

    public static long clamp(long num, long min, long max) {
        return num < min ? min : Math.min(num, max);
    }

    public static double square(double input) {
        return input * input;
    }

    public static float square(float input) {
        return input * input;
    }

    public static double distance(float x, float y, float x1, float y1) {
        return Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));
    }

    public static double distance(float x, float y, boolean root) {
        double change = Math.abs(x - y);
        if (root) {
            return Math.sqrt(MathUtil.square(change));
        }
        return change;
    }

    public static double getRandomDoubleInRange(Random random, double minimum, double maximum) {
        return minimum >= maximum ? minimum : random.nextDouble() * (maximum - minimum) + minimum;
    }

    public static float wrap(float valI) {
        float val = valI % 360.0f;
        if (val >= 180.0f) {
            val -= 360.0f;
        }
        if (val < -180.0f) {
            val += 360.0f;
        }
        return val;
    }
}

