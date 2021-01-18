package Worlds;

import Entities.StaticEntities.Door;
import Entities.StaticEntities.Hole;
import Entities.StaticEntities.Window;
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
        entityManager.addEntity(new Hole(handler, 420, 420, 64, 64, "Hole 3", handler.getWorldManager().getWorld(2), 900, 900));
        entityManager.addEntity(new Door(handler, 960, 9 * Tile.TILEHEIGHT, 64, 96, "Door", handler.getWorldManager().getWorld(2), 900, 900, true, true));
        entityManager.addEntity(new Window(handler, 858, 9 * Tile.TILEHEIGHT - 12, 64, 88, "Window-1"));
        entityManager.addEntity(new Window(handler, 770, 9 * Tile.TILEHEIGHT - 12, 64, 88, "Window-2"));
        entityManager.addEntity(new Window(handler, 1062, 9 * Tile.TILEHEIGHT - 12, 64, 88, "Window-3"));
        entityManager.addEntity(new Window(handler, 1164, 9 * Tile.TILEHEIGHT - 12, 64, 88, "Window-4"));
    }

    @Override
    protected void load() {
        handler.getFlags().setVisionLimited(false);
    }
}
