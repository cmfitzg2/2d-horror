package Worlds;

import Entities.StaticEntities.Hole;
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
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneClassroom)) {
            transitionFrom(handler.getWorldManager().getWorld(4), 29 * Tile.TILEWIDTH, 5 * Tile.TILEHEIGHT);
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
