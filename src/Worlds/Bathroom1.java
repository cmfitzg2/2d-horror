package Worlds;

import Entities.Creatures.Player;
import Tiles.Tile;
import Variables.Handler;

import java.awt.*;

public class Bathroom1 extends World {

    public Bathroom1(Handler handler, int id, Player player) {
        super(handler, "res/worlds/bathroom1.txt", id, player);
    }

    @Override
    public void checkLoadZones() {
        Rectangle loadzoneSchool = new Rectangle(9 * Tile.TILEWIDTH - (int) handler.getGameCamera().getxOffset(), (int) (11.5 * Tile.TILEHEIGHT) - (int) handler.getGameCamera().getyOffset(), 130, 36);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneSchool)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.SCHOOL_1_ID), 15 * Tile.TILEWIDTH, 4 * Tile.TILEHEIGHT);
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
