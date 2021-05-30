package Entities.StaticEntities;

import Graphics.Assets;
import Variables.Handler;

import java.awt.*;

public class TableLamp extends StaticEntity {

    private boolean lit = false;
    private float xScale, yScale;

    public TableLamp(Handler handler, float x, float y, int width, int height, String uniqueName) {
        super(handler, x, y, width, height, uniqueName);
        xScale = (float) width / Assets.tableLampOff.getWidth();
        yScale = (float) height / Assets.tableLampOff.getHeight();
        bounds.x = (int) (2 * xScale);
        bounds.y = 48;
        bounds.width = (int) (width - 4 * xScale);
        bounds.height = (int) (height * (2 / 3.0f) - 28);
    }

    @Override
    public void preRender(Graphics g) {

    }

    @Override
    public void postRender(Graphics g) {

    }

    @Override
    public void finalRender(Graphics g) {
        if (!handler.getFlags().isPowerOut()) {
            if (lit && !handler.getFlags().isViewingArt() && !handler.getFlags().isInPuzzle() && !handler.isInMenu() && !handler.getFlags().isHideEffects()) {
                g.drawImage(Assets.yellowLight, (int) (x - handler.getGameCamera().getxOffset() - 32),
                        (int) (y - handler.getGameCamera().getyOffset() - 32), 128, 128, null);
            }
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if (!handler.getFlags().isPowerOut()) {
            if (lit) {
                g.drawImage(Assets.tableLampOn, (int) (x - handler.getGameCamera().getxOffset()),
                        (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
            } else {
                g.drawImage(Assets.tableLampOff, (int) (x - handler.getGameCamera().getxOffset()),
                        (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
            }
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
    public boolean interactedWith() {
        lit = !lit;
        if (lit) {
            Assets.lampOn.stop();
            Assets.lampOn.play();
        } else {
            Assets.lampOff.stop();
            Assets.lampOff.play();
        }
        return true;
    }

    @Override
    public boolean isInteracting() {
        return isInteracting;
    }

    @Override
    public boolean itemInteraction(String item) {
        return false;
    }

    public boolean isLit() {
        return lit;
    }

    public void setLit(boolean lit) {
        this.lit = lit;
    }
}
