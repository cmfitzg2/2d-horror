package Entities.StaticEntities;

import Game.Handler;
import Graphics.Assets;
import java.awt.*;

public class Hole extends StaticEntity {

    private boolean intersected = false, justStartedFalling = true;
    private int fallFrames = 45, frameCounter = 0;
    private int alphaThreshold = 255 / fallFrames, alpha = 0;
    private float originalY;
    private Rectangle area;

    public Hole(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        area = new Rectangle((int) x + width / 2 - width / 32 , (int) y + height / 2 - height / 32, width / 32, height / 32);
        solid = false;
    }

    @Override
    public void preRender(Graphics g) {
        if (intersected) {
            g.drawImage(Assets.hole, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (originalY - handler.getGameCamera().getyOffset()), null);
        }
    }

    @Override
    public void postRender(Graphics g) {
        if (intersected) {
            handler.getScreenOverlay().overlayScreen(g, new Color(0, 0, 0, alpha));
        }
    }

    @Override
    public void tick() {
        if (handler.getPlayer().getPlayerRec().intersects(area)) {
            handler.setPlayerFrozen(true);
            handler.getPlayer().setTransparent(true);
            intersected = true;
        }
        if (intersected) {
            if (justStartedFalling) {
                // render order happens in order of Y, and since we're overlaying the screen,
                // we need to take priority over other entity screen overlays
                // this is stupid though and i should probably standardize this in one class
                originalY = y;
                y = handler.getHeight() + 20;
                justStartedFalling = false;
            }
            if (fallFrames - frameCounter >= 0) {
                handler.getPlayer().setY(handler.getPlayer().getY() + 7);
                if (alpha + alphaThreshold > 255) {
                    alpha = 255;
                } else {
                    alpha += alphaThreshold;
                }
                frameCounter++;
            } else {
                alpha = 255;
                //handler.setWorld();
            }
            if (handler.getKeyManager().z) {
                intersected = false;
                handler.getPlayer().setTransparent(false);
                justStartedFalling = true;
                y = originalY;
                alpha = 0;
                frameCounter = 0;
                handler.setPlayerFrozen(false);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(area.x, area.y, area.width, area.height);
    }

    @Override
    public void die() {
        handler.getWorld().getEntityManager().removeEntity(this);
    }

    @Override
    public void interactedWith() {
        isInteracting = true;
        isInteracting = false;
    }

    @Override
    public boolean isInteracting() {
        return isInteracting;
    }
}
