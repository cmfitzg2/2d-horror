package Worlds;

import Tiles.Tile;
import Variables.Handler;

import java.awt.*;

public class World3 extends World {

    public World3(Handler handler, String path, int id) {
        super(handler, path, id, null);
    }

    @Override
    public void checkLoadZones() {
        Rectangle loadzoneClassroom = new Rectangle(383 - (int) handler.getGameCamera().getxOffset(), 733 - (int) handler.getGameCamera().getyOffset(), 63, 34);
        Rectangle loadzoneOutside = new Rectangle(1342 - (int) handler.getGameCamera().getxOffset(), 1818 - (int) handler.getGameCamera().getyOffset(), 130, 36);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneClassroom)) {
            transitionFrom(handler.getWorldManager().getWorld(4), 23 * Tile.TILEWIDTH, 4 * Tile.TILEHEIGHT);
        }
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneOutside)) {
            transitionFrom(handler.getWorldManager().getWorld(2), 14 * Tile.TILEWIDTH, 46 * Tile.TILEHEIGHT);
        }
    }

    @Override
    protected void addEntities() {

    }

    @Override
    protected void load() {
        handler.getFlags().setVisionLimited(false);
        firstRender = true;
    }
}
