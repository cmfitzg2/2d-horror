package Entities.StaticEntities;

import Entities.Entity;
import Graphics.Assets;
import Variables.Handler;
import Worlds.World;

import java.awt.*;

public class MansionStairs extends StaticEntity {

    public static final int STYLE_UP_RIGHT = 0;
    private int style;
    private float xScale, yScale;
    private World destination;
    private float newX, newY;

    public MansionStairs(Handler handler, float x, float y, int width, int height, String uniqueName,
                         World destination, float newX, float newY, int style) {
        super(handler, x, y, width, height, uniqueName);
        this.destination = destination;
        this.newX = newX;
        this.newY = newY;
        this.style = style;
        xScale = (float) width / Assets.mansionStairsUpRight.getWidth();
        yScale = (float) height / Assets.mansionStairsUpRight.getHeight();
        addBoundingBox(new Rectangle(0, (int) (38 * yScale), width, (int) (6 * yScale)));
        addBoundingBox(new Rectangle((int) (17 * xScale), (int) (68 * yScale), (int) (47 * xScale), (int) (12 * yScale)));
        addBoundingBox(new Rectangle((int) (25 * xScale), (int) (38 * yScale), (int) (40 * xScale), (int) (40 * yScale)));
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
        Rectangle levelTransition = null;
        if (style == STYLE_UP_RIGHT) {
            levelTransition = new Rectangle((int) x + (int) (xScale * 20) - (int) handler.getGameCamera().getxOffset(),
                    (int) y + (int) (yScale * 40) - (int) handler.getGameCamera().getyOffset(),
                    (int) (16 * xScale), (int) (20 * yScale));
        }
        if (levelTransition != null) {
            if (handler.getPlayer().getPlayerRec().intersects(levelTransition)) {
                handler.getActiveWorld().transitionFrom(destination, newX, newY, -1);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (style == STYLE_UP_RIGHT) {
            g.drawImage(Assets.mansionStairsUpRight, (int) (x - handler.getGameCamera().getxOffset()),
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
        if (e.getY() + e.getHeight() > y + yScale * 38) {
            return 1;
        }
        return -1;
    }

    @Override
    public boolean customRenderVsEntity() {
        return true;
    }
}
