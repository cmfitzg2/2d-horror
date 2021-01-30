package Worlds;

import Entities.Creatures.Player;
import Tiles.Tile;
import Variables.Handler;

import java.awt.*;

public class MCHouse2 extends World {

    public MCHouse2(Handler handler, int id, Player player) {
        super(handler, "res/worlds/mc-house-2.txt", id, player);
    }

    @Override
    public void checkLoadZones() {
        Rectangle loadzonePlayerRoom = new Rectangle((int) (Tile.TILEWIDTH * 38.5f) - (int) handler.getGameCamera().getxOffset(),
                15 * Tile.TILEHEIGHT - (int) handler.getGameCamera().getyOffset(), Tile.TILEWIDTH / 2, Tile.TILEHEIGHT);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzonePlayerRoom)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.MC_HOUSE_1_ID), Tile.TILEWIDTH * 10,
                    Tile.TILEHEIGHT * 9 - handler.getPlayer().getPlayerRec().height / 2.0f);
        }
    }

    @Override
    protected void addEntities() {

    }

    @Override
    protected void load() {
        firstRender = true;
    }
}
