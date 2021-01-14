package Entities.StaticEntities;

import Graphics.Assets;
import Variables.Handler;

import java.awt.*;

public class GrandfatherClock extends StaticEntity {

    public GrandfatherClock(Handler handler, float x, float y, int width, int height, String uniqueName) {
        super(handler, x, y, width, height, uniqueName);
        bounds.x = 0;
        bounds.y = 3 * height / 4;
        bounds.width = width;
        bounds.height = height / 4;
    }

    @Override
    public void preRender(Graphics g) {

    }

    @Override
    public void postRender(Graphics g) {

    }

    @Override
    public void finalRender(Graphics g) {

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.grandfatherClock, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
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
