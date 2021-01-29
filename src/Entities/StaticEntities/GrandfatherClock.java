package Entities.StaticEntities;

import Graphics.Assets;
import Graphics.Animation;
import Variables.Handler;

import java.awt.*;

public class GrandfatherClock extends StaticEntity {

    private Animation tick;
    private float xScale, yScale;

    public GrandfatherClock(Handler handler, float x, float y, int width, int height, String uniqueName) {
        super(handler, x, y, width, height, uniqueName);
        xScale = (float) width / Assets.grandfatherClock[0].getWidth();
        yScale = (float) height / Assets.grandfatherClock[0].getHeight();
        bounds.x = (int) (7 * xScale);
        bounds.y = (int) (64 * yScale);
        bounds.width = (int) (18 * xScale);
        bounds.height = (int) (19 * yScale);
        tick = new Animation(500, Assets.grandfatherClock);
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
        tick.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tick.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()),
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
