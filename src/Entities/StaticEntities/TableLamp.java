package Entities.StaticEntities;

import Graphics.Assets;
import Variables.Handler;

import java.awt.*;

public class TableLamp extends StaticEntity {

    private boolean lit = false;

    public TableLamp(Handler handler, float x, float y, int width, int height, String uniqueName) {
        super(handler, x, y, width, height, uniqueName);
        bounds.x = 0;
        bounds.y = 48;
        bounds.width = width;
        bounds.height = (int) (height * (2 / 3.0f) - 28);
    }

    @Override
    public void preRender(Graphics g) {

    }

    @Override
    public void postRender(Graphics g) {
        if (lit && !handler.getFlags().isViewingArt() && !handler.getFlags().isInPuzzle() && !handler.isInMenu()) {
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
        if (lit) {
            g.drawImage(Assets.tableLampOn, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else {
            g.drawImage(Assets.tableLampOff, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }
    }

    @Override
    public void die() {
        handler.getActiveWorld().getEntityManager().removeEntity(this);
    }

    @Override
    public void interactedWith() {
        lit = !lit;
    }

    @Override
    public boolean isInteracting() {
        return isInteracting;
    }
}
