package Entities.StaticEntities;

import Graphics.Assets;
import Variables.Handler;

import java.awt.*;

public class IronGate extends StaticEntity {

    private int type, shadowWidth, shadowHeight;
    public static final int TOP_CORNER_PILLAR = 0, NORMAL_PILLAR = 1, GATE = 2, BOTTOM_CORNER_PILLAR = 3, VERTICAL = 4,
            GATE_RIGHT = 5, GATE_LEFT = 6;
    private float xScale, yScale;

    public IronGate(Handler handler, float x, float y, int width, int height, String uniqueName, int type, boolean spaceBehind) {
        super(handler, x, y, width, height, uniqueName);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;
        if (spaceBehind) {
            bounds.y = (int) (0.75 * height);
            bounds.height = (int) (0.25 * height);
        }
        this.type = type;
        if (type == TOP_CORNER_PILLAR || type == NORMAL_PILLAR || type == BOTTOM_CORNER_PILLAR || type == VERTICAL) {
            //figure out how big to make the shadow
            xScale = width / (float) Assets.ironGate[1].getWidth();
            yScale = height / (float) Assets.ironGate[1].getHeight();
            shadowWidth = (int) (7 * xScale);
            shadowHeight = (int) (64 * yScale);
        }
    }

    @Override
    public void preRender(Graphics g) {
        if (type == TOP_CORNER_PILLAR || type == NORMAL_PILLAR || type == BOTTOM_CORNER_PILLAR || type == VERTICAL) {
            //draw the shadow for the pillar
            g.drawImage(Assets.ironGate[7], (int) (x - handler.getGameCamera().getxOffset() + width),
                    (int) (y - handler.getGameCamera().getyOffset()), shadowWidth, shadowHeight, null);
        }
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
        g.drawImage(Assets.ironGate[type], (int) (x - handler.getGameCamera().getxOffset()),
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
