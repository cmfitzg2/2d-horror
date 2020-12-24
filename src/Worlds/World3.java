package Worlds;

import Entities.StaticEntities.Door1;
import Entities.StaticEntities.Hole;
import Entities.StaticEntities.Window1;
import Graphics.Assets;
import Tiles.Tile;
import Variables.Handler;

public class World3 extends World {

    public World3(Handler handler, String path, int id) {
        super(handler, path, id, null);
    }

    @Override
    public void checkLoadZones() {

    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new Hole(handler, 420, 420, 64, 64, "Hole 3", handler.getWorldManager().getWorld(2), 100, 100));
        entityManager.addEntity(new Door1(handler, 960, 9 * Tile.TILEHEIGHT, 64, 96, "Door1", handler.getWorldManager().getWorld(2), 900, 900, true, true));
        entityManager.addEntity(new Window1(handler, 858, 9 * Tile.TILEHEIGHT - 12, 64, 88, "Window1-1"));
        entityManager.addEntity(new Window1(handler, 770, 9 * Tile.TILEHEIGHT - 12, 64, 88, "Window1-2"));
        entityManager.addEntity(new Window1(handler, 1062, 9 * Tile.TILEHEIGHT - 12, 64, 88, "Window1-3"));
        entityManager.addEntity(new Window1(handler, 1164, 9 * Tile.TILEHEIGHT - 12, 64, 88, "Window1-4"));
    }

    @Override
    protected void load() {
        handler.getFlags().setVisionLimited(false);
    }
}
