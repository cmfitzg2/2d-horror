package Entities.StaticEntities;

import Graphics.Assets;
import Textboxes.TextboxHandler;
import Tiles.Tile;
import Variables.GeneralConstants;
import Variables.Handler;
import Worlds.WorldManager;

import java.awt.*;

public class BellTower extends StaticEntity {

    private boolean includeStairs;
    private final int stairsWidth = 32, stairsHeight = 16;
    private int stairsX, stairsY;
    private float xScale, yScale;

    public BellTower(Handler handler, float x, float y, int width, int height, boolean includeStairs, String uniqueName) {
        super(handler, x, y, width, height, uniqueName);
        this.includeStairs = includeStairs;
        xScale = width / (float) Assets.bellTower.getWidth();
        yScale = height / (float) Assets.bellTower.getHeight();
        addBoundingBox(new Rectangle(0, 6 * height / 7 - Tile.TILEHEIGHT / 2, width / 2 - (int) (xScale * (stairsWidth / 2)), height / 7 + Tile.TILEHEIGHT / 2));
        addBoundingBox(new Rectangle(width / 2 + (int) (xScale * (stairsWidth / 2)), 6 * height / 7 - Tile.TILEHEIGHT / 2, width / 2 - (int) (xScale * (stairsWidth / 2)), height / 7 + Tile.TILEHEIGHT / 2));
        addBoundingBox(new Rectangle(width / 2 - (int) (xScale * (stairsWidth / 2)), 6 * height / 7 - Tile.TILEHEIGHT / 2, width / 2 - (int) (xScale * (stairsWidth / 2)) + 2, 8));
        stairsX = width / 2 - (int) (xScale * (stairsWidth / 2));
        stairsY = (int) (yScale * (Assets.bellTower.getHeight() - stairsHeight));
        handler.getActiveWorld().getEntityManager().addEntity(
                new Door(handler, x + stairsX, y + stairsY - Assets.closedDoorOne.getHeight() * 2,
                        Assets.closedDoorOne.getWidth() * 2, Assets.closedDoorOne.getHeight() * 2, "belltower-overworld1",
                        handler.getWorldManager().getWorld(WorldManager.MC_HOUSE_1_ID), 700, 600, Door.PLAIN_WOOD,
                        GeneralConstants.levelTransitionFrames, true));
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
