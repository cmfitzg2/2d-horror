package Entities.StaticEntities;

import Graphics.Assets;
import Graphics.Animation;
import Variables.Handler;

import java.awt.*;

public class Fireplace extends StaticEntity {

    private boolean lit = false, extinguished = false;
    private final Animation fire;
    private float xScale, yScale;

    public Fireplace(Handler handler, float x, float y, int width, int height, String uniqueName) {
        super(handler, x, y, width, height, uniqueName);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;
        xScale = (float) width / Assets.firePlace.getWidth();
        yScale = (float) height / Assets.firePlace.getHeight();
        fire = new Animation(75, Assets.fire);
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
        if (lit) {
            fire.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.firePlace, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        if (lit) {
            g.drawImage(fire.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset() + (20 * xScale)),
                    (int) (y - handler.getGameCamera().getyOffset() + (20 * yScale)), (int) (Assets.fireExtinguished.getWidth() * xScale),
                    (int) (Assets.fireExtinguished.getHeight() * yScale), null);
        } else {
            if (extinguished) {
                g.drawImage(Assets.fireExtinguished, (int) (x - handler.getGameCamera().getxOffset() + (20 * xScale)),
                        (int) (y - handler.getGameCamera().getyOffset() + (20 * yScale)), (int) (Assets.fireExtinguished.getWidth() * xScale),
                        (int) (Assets.fireExtinguished.getHeight() * yScale), null);
            } else {
                g.drawImage(Assets.fireUnlit, (int) (x - handler.getGameCamera().getxOffset() + (20 * xScale)),
                        (int) (y - handler.getGameCamera().getyOffset() + (20 * yScale)), (int) (Assets.fireExtinguished.getWidth() * xScale),
                        (int) (Assets.fireExtinguished.getHeight() * yScale), null);
            }
        }
    }

    @Override
    public void die() {
        handler.getActiveWorld().getEntityManager().removeEntity(this);
    }

    @Override
    public void interactedWith() {
        if (!lit) {
            if (!extinguished) {
                lit = true;
            } else {
                extinguished = false;
            }
        } else {
            lit = false;
            extinguished = true;
        }
    }

    @Override
    public boolean isInteracting() {
        return isInteracting;
    }
}
