package Worlds;

import Entities.Creatures.Player;
import Tiles.Tile;
import Variables.Handler;

import java.awt.*;

public class School1 extends World {

    public School1(Handler handler, int id, Player player) {
        super(handler, "res/worlds/world3.txt", id, player);
    }

    @Override
    public void checkLoadZones() {
        Rectangle loadzoneClassroom = new Rectangle(383 - (int) handler.getGameCamera().getxOffset(), 733 - (int) handler.getGameCamera().getyOffset(), 63, 34);
        Rectangle loadzoneOutside = new Rectangle(1342 - (int) handler.getGameCamera().getxOffset(), 1818 - (int) handler.getGameCamera().getyOffset(), 130, 36);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneClassroom)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.CLASSROOM_1_ID), 23 * Tile.TILEWIDTH, 4 * Tile.TILEHEIGHT);
        }
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneOutside)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.OVERWORLD_1_ID), 14 * Tile.TILEWIDTH, 46 * Tile.TILEHEIGHT);
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
