package Entities.StaticEntities;

import Variables.Handler;
import java.awt.*;
import Graphics.Assets;

public class FrontGateSide extends StaticEntity {

    boolean left;

    public FrontGateSide(Handler handler, float x, float y, int width, int height, String uniqueName, boolean left) {
        super(handler, x, y, width, height, uniqueName);
        bounds.x = 0;
        bounds.y = (int) (0.75 * height);
        bounds.width = width;
        bounds.height = (int) (0.25 * height);
        this.left = left;
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
        if (left) {
            g.drawImage(Assets.frontGateLeft, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else {
            g.drawImage(Assets.frontGateRight, (int) (x - handler.getGameCamera().getxOffset()),
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
}
