package Entities.StaticEntities;

import Graphics.Assets;
import Graphics.Animation;
import Variables.Handler;

import java.awt.*;

public class Furnace extends StaticEntity {

    private int type;
    private float xScale, yScale;
    public static final int TYPE_UNLIT = 0, TYPE_LIT = 1;
    private boolean lit;
    private Animation fire;

    public Furnace(Handler handler, float x, float y, int width, int height, String uniqueName, int type) {
        super(handler, x, y, width, height, uniqueName);
        this.type = type;
        fire = new Animation(75, Assets.furnaceLit);
        xScale = width / (float) Assets.furnaceUnlit.getWidth();
        yScale = height / (float) Assets.furnaceUnlit.getHeight();
        addBoundingBox(new Rectangle(0, (int) (89 * yScale), width, (int) (34 * yScale)));
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
            if (handler.getFlags().isVisionLimited()) {
                handler.getFlags().setVisionLimited(false);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.furnaceUnlit, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        if (lit) {
            g.drawImage(fire.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset() + (98 * yScale)), (int) (Assets.furnaceLit[0].getWidth() * xScale),
                    (int) (Assets.furnaceLit[0].getHeight() * yScale), null);
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
