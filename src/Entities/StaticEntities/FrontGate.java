package Entities.StaticEntities;

import Graphics.Assets;
import Variables.Handler;

import java.awt.*;

public class FrontGate extends StaticEntity {

    boolean open;

    public FrontGate(Handler handler, float x, float y, int width, int height, String uniqueName, boolean open) {
        super(handler, x, y, width, height, uniqueName);
        this.open = open;
        setBounds();
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
        if (open) {
            g.drawImage(Assets.frontGateOpen, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else {
            g.drawImage(Assets.frontGateClosed, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }
    }

    @Override
    public void die() {
        handler.getActiveWorld().getEntityManager().removeEntity(this);
    }

    @Override
    public void interactedWith() {
        setOpen(!open);
    }

    @Override
    public boolean isInteracting() {
        return isInteracting;
    }

    @Override
    public boolean itemInteraction(String item) {
        return false;
    }

    private void setOpen(boolean open) {
        this.open = open;
        setBounds();
    }
    private void setBounds() {
        bounds.x = 0;
        bounds.width = width;
        bounds.y = (int) (0.75 * height);
        bounds.height = (int) (0.25 * height);
        if (open) {
            bounds.width = 1;
        }
    }
}
