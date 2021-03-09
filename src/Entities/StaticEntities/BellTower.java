package Entities.StaticEntities;

import Graphics.Assets;
import Textboxes.TextboxHandler;
import Variables.Handler;

import java.awt.*;

public class BellTower extends StaticEntity {

    private boolean includeStairs;
    private final int stairsWidth = 32, stairsHeight = 16;
    private int stairsX, stairsY;
    private float xScale, yScale;

    public BellTower(Handler handler, float x, float y, int width, int height, boolean includeStairs, String uniqueName) {
        super(handler, x, y, width, height, uniqueName);
        addBoundingBox(new Rectangle(0, 6 * height / 7, width, height / 7));
        this.includeStairs = includeStairs;
        xScale = width / (float) Assets.bellTower.getWidth();
        yScale = height / (float) Assets.bellTower.getHeight();
        stairsX = (int) ((xScale / 2) * (Assets.bellTower.getWidth() - stairsWidth));
        stairsY = (int) (yScale * (Assets.bellTower.getHeight() - stairsHeight));
    }

    @Override
    public void preRender(Graphics g) {

    }

    @Override
    public void postRender(Graphics g) {
        for (Rectangle rectangle : collisionBoundsList) {
            g.fillRect((int) (x + rectangle.x - handler.getGameCamera().getxOffset()), (int) (y + rectangle.y - handler.getGameCamera().getyOffset()), rectangle.width, rectangle.height);
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
        g.drawImage(Assets.bellTower, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        if (includeStairs) {
            g.drawImage(Assets.stairs, (int) (x - handler.getGameCamera().getxOffset() + stairsX),
                    (int) (y - handler.getGameCamera().getyOffset() + stairsY), 64, 48, null);
        }
    }

    @Override
    public void die() {
        handler.getActiveWorld().getEntityManager().removeEntity(this);
    }

    @Override
    public void interactedWith() {

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
