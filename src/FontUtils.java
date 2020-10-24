import java.awt.*;

public class FontUtils {
    public static Font scaleFontUpVertically(Rectangle rect, Graphics g, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int textHeight = metrics.getHeight();
        while (textHeight < rect.height) {
            font = font.deriveFont(font.getSize() + 1.0f);
            metrics = g.getFontMetrics(font);
            textHeight = metrics.getHeight();
        }
        return font;
    }

    public static Font scaleFontDownVertically(Rectangle rect, Graphics g, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int textHeight = metrics.getHeight();
        while (textHeight > rect.height && font.getSize() > 1) {
            font = font.deriveFont(font.getSize() - 1.0f);
            metrics = g.getFontMetrics(font);
            textHeight = metrics.getHeight();
        }
        return font;
    }

    public static Font scaleFontDownHorizontally(String text, Rectangle rect, Graphics g, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int textWidth = metrics.stringWidth(text);
        while (textWidth > rect.width && font.getSize() > 1) {
            font = font.deriveFont(font.getSize() - 1.0f);
            metrics = g.getFontMetrics(font);
            textWidth = metrics.stringWidth(text);
        }
        return font;
    }

    public static Font scaleFontUpHorizontally(String text, Rectangle rect, Graphics g, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int textWidth = metrics.stringWidth(text);
        while (textWidth < rect.width) {
            font = font.deriveFont(font.getSize() + 1.0f);
            metrics = g.getFontMetrics(font);
            textWidth = metrics.stringWidth(text);
        }
        return font;
    }

    public static Font scaleFontDown(String text, Rectangle rect, Graphics g, Font font) {
        font = scaleFontDownHorizontally(text, rect, g, font);
        return scaleFontDownVertically(rect, g, font);
    }

    public static Font scaleFontUp(String text, Rectangle rect, Graphics g, Font font) {
        font = scaleFontUpHorizontally(text, rect, g, font);
        return scaleFontUpVertically(rect, g, font);
    }
}
