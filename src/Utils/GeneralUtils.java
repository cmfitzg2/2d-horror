package Utils;

import Input.MouseManager;
import Variables.GeneralConstants;
import Variables.Handler;
import Worlds.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import Graphics.Assets;

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

    public static void levelFadeIn(Handler handler, int transitionFrames) {
        if (transitionFrames <= 0) {
            transitionFrames = GeneralConstants.levelTransitionFrames;
        }
        handler.getGame().fadeIn(transitionFrames);
    }

    public static void levelFadeOut(Handler handler) {
        handler.getGame().fadeOut(GeneralConstants.levelTransitionFrames);
    }

    public static void stopLevelFadeIn(Handler handler, boolean setPlayerFrozen) {
        handler.getGame().setFadeIn(false, setPlayerFrozen);
        handler.getGame().setFinishedFadingIn(false);
    }

    public static void stopLevelFadeOut(Handler handler, World newWorld, float newX, float newY, boolean setPlayerFrozen) {
        handler.getGame().setFinishedFadingOut(false);
        handler.getGame().setFadeOut(false, setPlayerFrozen);
        handler.getWorldManager().setActiveWorld(newWorld);
        newWorld.getEntityManager().getPlayer().setX(newX);
        newWorld.getEntityManager().getPlayer().setY(newY);
    }

    public static BufferedImage getArtworkByName(String name) {
        switch (name) {
            case "Solace":
                return Assets.gallerySolace;
            case "Prophet":
                return Assets.galleryProphet;
            default:
                return Assets.artFrame;
        }
    }
}
