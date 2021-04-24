package Entities.StaticEntities;

import Graphics.Assets;
import Variables.Handler;

import java.awt.*;

public class Well extends StaticEntity {

    private int type;
    private float xScale, yScale;
    public static final int TYPE_NORMAL = 0, TYPE_COVERED = 1, TYPE_DAMAGED = 2;

    public Well(Handler handler, float x, float y, int width, int height, String uniqueName, int type) {
        super(handler, x, y, width, height, uniqueName);
        this.type = type;
        xScale = width / (float) Assets.well[0].getWidth();
        yScale = height / (float) Assets.well[0].getHeight();
        final int box1X = (int) (8 * xScale), box1Y = (int) (77 * yScale), box1Width = (int) (8 * xScale), box1Height = (int) (32 * yScale);
        final int box2X = (int) (17 * xScale), box2Y = (int) (77 * yScale), box2Width = (int) (64 * xScale), box2Height = (int) (48 * yScale);
        final int box3X = (int) (82 * xScale), box3Y = (int) (77 * yScale), box3Width = (int) (8 * xScale), box3Height = (int) (32 * yScale);
        addBoundingBox(new Rectangle(box1X, box1Y, box1Width, box1Height));
        addBoundingBox(new Rectangle(box2X, box2Y, box2Width, box2Height));
        addBoundingBox(new Rectangle(box3X, box3Y, box3Width, box3Height));
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
        g.drawImage(Assets.well[type], (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
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
}
