package Utils;

import Input.MouseManager;

import java.awt.*;

public class GeneralUtils {

    public static void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }

    public static boolean areaClicked(MouseManager mouseManager, Rectangle bounds) {
        return (bounds.contains(new Point(mouseManager.getMouseX(), mouseManager.getMouseY()))
                && mouseManager.isLeftPressed());
    }
}
