package io.github.razordevs.deep_aether.util;

public class RendererUtil {

    public static int AddAlpha(int color, int alpha) {

        int red = color >> 24 & 0xFF;
        int green = color >> 16 & 0xFF;
        int blue = color >> 8 & 0xFF;

        var r = red & 0xFF;
        var g = green & 0xFF;
        var b = blue & 0xFF;
        var a = alpha & 0xFF;

        return (r << 24) + (g << 16) + (b << 8) + (a);
    }
}
