package Entities.StaticEntities;

import Entities.Entity;
import Graphics.Assets;
import Tiles.Tile;
import Variables.Handler;

import java.awt.*;

public class School extends StaticEntity {

    private float xScale, yScale;
    private Rectangle behindSchool;

    public School(Handler handler, float x, float y, int width, int height, String uniqueName) {
        super(handler, x, y, width, height, uniqueName);
        xScale = (float) width / Assets.school.getWidth();
        yScale = (float) height / Assets.school.getHeight();
        int quarterHeight = (int) (Assets.school.getHeight() * yScale / 4);
        addBoundingBox(new Rectangle(0, quarterHeight, (int) (128 * xScale), quarterHeight * 3));
        addBoundingBox(new Rectangle((int) (128 * xScale), quarterHeight, (int) (288 * xScale), Tile.TILEHEIGHT * 3));
        addBoundingBox(new Rectangle((int) (128 * xScale), quarterHeight, (int) (128 * xScale), quarterHeight * 3 - (int) (32 * yScale)));
        addBoundingBox(new Rectangle((int) (288 * xScale), quarterHeight, (int) (128 * xScale), quarterHeight * 3 - (int) (32 * yScale)));
        addBoundingBox(new Rectangle((int) (416 * xScale), quarterHeight, (int) (128 * xScale), quarterHeight * 3));
        behindSchool = new Rectangle((int) x - (int) handler.getGameCamera().getxOffset(),
                (int) y - (int) handler.getGameCamera().getyOffset(),
                (int) (Assets.school.getWidth() * xScale), (int) (Assets.school.getHeight() * yScale / 2));
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
        behindSchool = new Rectangle((int) x - (int) handler.getGameCamera().getxOffset(),
                (int) y - (int) handler.getGameCamera().getyOffset(),
                (int) (Assets.school.getWidth() * xScale), (int) (Assets.school.getHeight() * yScale / 2));
    }

    @Override
    public void render(Graphics g) {
        if (handler.getPlayer().getPlayerRec().intersects(behindSchool)) {
            g.drawImage(Assets.schoolTransparent, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else {
            g.drawImage(Assets.school, (int) (x - handler.getGameCamera().getxOffset()),
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
        if (e.getY() > y + yScale * Assets.school.getHeight() / 2) {
            return 1;
        }
        return -1;
    }

    @Override
    public boolean customRenderVsEntity() {
        return true;
    }
}
