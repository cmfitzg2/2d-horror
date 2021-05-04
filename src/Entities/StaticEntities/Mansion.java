package Entities.StaticEntities;

import Entities.Entity;
import Graphics.Assets;
import Variables.Handler;

import java.awt.*;

public class Mansion extends StaticEntity {

    private float xScale, yScale;
    private Rectangle behindMansion;

    public Mansion(Handler handler, float x, float y, int width, int height, String uniqueName) {
        super(handler, x, y, width, height, uniqueName);
        xScale = (float) width / Assets.mansion.getWidth();
        yScale = (float) height / Assets.mansion.getHeight();
        int halfHeight = (int) (Assets.mansion.getHeight() * yScale / 2);
        addBoundingBox(new Rectangle(0, halfHeight, (int) (Assets.mansion.getWidth() * xScale), halfHeight - (int) (32 * yScale)));
        addBoundingBox(new Rectangle((int) (161 * xScale), (int) (416 * yScale), (int) (126 * xScale), (int) (32 * yScale)));
        behindMansion = new Rectangle((int) x - (int) handler.getGameCamera().getxOffset(),
                (int) y - (int) handler.getGameCamera().getyOffset(),
                (int) (Assets.mansion.getWidth() * xScale), (int) (Assets.mansion.getHeight() * yScale / 2));
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
        behindMansion = new Rectangle((int) x - (int) handler.getGameCamera().getxOffset(),
                (int) y - (int) handler.getGameCamera().getyOffset(),
                (int) (Assets.mansion.getWidth() * xScale), (int) (Assets.mansion.getHeight() * yScale / 2));
    }

    @Override
    public void render(Graphics g) {
        if (handler.getPlayer().getPlayerRec().intersects(behindMansion)) {
            g.drawImage(Assets.mansionTransparent, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else {
            g.drawImage(Assets.mansion, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }
    }

    @Override
    public void die() {
        handler.getActiveWorld().getEntityManager().removeEntity(this);
    }

    @Override
    public boolean interactedWith() {
        return false;
    }

    @Override
    public boolean isInteracting() {
        return isInteracting;
    }

    @Override
    public boolean itemInteraction(String item) {
        return false;
    }

    @Override
    public int renderVsEntity(Entity e) {
        //if the entity is on the left or right side of the middle of the mansion & in front of it
        //we need to render them in front of it
        if (e.getX() < x + width && e.getX() + e.getWidth() > x && e.getY() + e.getHeight() >= y && e.getY() <= y + height) {
            //we're occupying the same area
            if (e.getY() > y + height / 2f) {
                //the entity is in front of the mansion
                return 1;
            } else {
                //they're behind it
                return -1;
            }
        }
        return 0;
    }

    @Override
    public boolean customRenderVsEntity() {
        return true;
    }
}
