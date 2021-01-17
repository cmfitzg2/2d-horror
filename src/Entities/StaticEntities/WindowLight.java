package Entities.StaticEntities;

import Graphics.Assets;
import Variables.Handler;

import java.awt.*;

public class WindowLight extends StaticEntity {

    public WindowLight(Handler handler, float x, float y, int width, int height, String uniqueName) {
        super(handler, x, y, width, height, uniqueName);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;
    }

    @Override
    public void preRender(Graphics g) {
        g.drawImage(Assets.windowLight, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void postRender(Graphics g) {
        if (!handler.getFlags().isViewingArt() && !handler.getFlags().isInPuzzle() && !handler.isInMenu()) {
            g.drawImage(Assets.yellowLight, (int) (x - handler.getGameCamera().getxOffset() - 32),
                    (int) (y - handler.getGameCamera().getyOffset() - 32), 128, 128, null);
        }
    }

    @Override
    public void finalRender(Graphics g) {

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void die() {
        handler.getActiveWorld().getEntityManager().removeEntity(this);
    }

    @Override
    public void interactedWith() {

    }

    @Override
    public boolean isInteracting() {
        return isInteracting;
    }
}
