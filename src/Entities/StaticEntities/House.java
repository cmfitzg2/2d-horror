package Entities.StaticEntities;

import Entities.Entity;
import Graphics.Assets;
import Tiles.Tile;
import Variables.Handler;

import java.awt.*;

public class House extends StaticEntity {

    private float xScale, yScale;
    private Rectangle behindHouse;
    public static int STYLE_DEFAULT = 0;
    private int style;

    public House(Handler handler, float x, float y, int width, int height, String uniqueName, int style) {
        super(handler, x, y, width, height, uniqueName);
        xScale = (float) width / Assets.houseDefault.getWidth();
        yScale = (float) height / Assets.houseDefault.getHeight();
        this.style = style;
        int halfHeight = (int) (Assets.houseDefault.getHeight() * yScale / 2);
        int halfWidth = (int) (Assets.houseDefault.getWidth() * xScale / 2);
        addBoundingBox(new Rectangle(0, halfHeight, halfWidth - (int) (Assets.closedDoorOne.getWidth() / 2 * xScale), halfHeight));
        addBoundingBox(new Rectangle(halfWidth + (int) (Assets.closedDoorOne.getWidth() / 2 * xScale), halfHeight, halfWidth - (int) (Assets.closedDoorOne.getWidth() / 2 * xScale), halfHeight));
        addBoundingBox(new Rectangle(halfWidth - (int) (Assets.closedDoorOne.getWidth() / 2 * xScale), halfHeight, Tile.TILEWIDTH, Tile.TILEHEIGHT));
        behindHouse = new Rectangle((int) x - (int) handler.getGameCamera().getxOffset(),
                (int) y - (int) handler.getGameCamera().getyOffset(),
                (int) (Assets.houseDefault.getWidth() * xScale), (int) (Assets.houseDefault.getHeight() * yScale / 2));
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
        behindHouse = new Rectangle((int) x - (int) handler.getGameCamera().getxOffset(),
                (int) y - (int) handler.getGameCamera().getyOffset(),
                (int) (Assets.houseDefault.getWidth() * xScale), (int) (Assets.houseDefault.getHeight() * yScale / 2));
    }

    @Override
    public void render(Graphics g) {
        if (handler.getPlayer().getPlayerRec().intersects(behindHouse)) {
            g.drawImage(Assets.houseDefaultTransparent, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else {
            g.drawImage(Assets.houseDefault, (int) (x - handler.getGameCamera().getxOffset()),
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
        if (e.getY() > y + yScale * Assets.houseDefault.getHeight() / 2) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean customRenderVsEntity() {
        return true;
    }
}
