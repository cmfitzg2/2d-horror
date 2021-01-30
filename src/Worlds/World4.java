package Worlds;

import Entities.Creatures.Player;
import Entities.StaticEntities.Chalkboard;
import Entities.StaticEntities.Door;
import Graphics.Assets;
import Tiles.Tile;
import Variables.Handler;

public class World4 extends World {

    public World4(Handler handler, int id, Player player) {
        super(handler, "res/worlds/world4.txt", id, player);
    }

    @Override
    public void checkLoadZones() {

    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new Door(handler, 23 * Tile.TILEWIDTH, 2.5f * Tile.TILEHEIGHT, 64, 96, "Door", handler.getWorldManager().getWorld(3), 383 - (int) handler.getGameCamera().getxOffset(), 797 - (int) handler.getGameCamera().getyOffset(), false, false));
        entityManager.addEntity(new Chalkboard(handler, 13.0f * Tile.TILEWIDTH, 42 + Tile.TILEHEIGHT, Assets.chalkboard[0].getWidth(), Assets.chalkboard[1].getHeight(), "chalkboard1", 0));
    }

    @Override
    protected void load() {
        handler.getFlags().setVisionLimited(false);
        firstRender = true;
    }
}
