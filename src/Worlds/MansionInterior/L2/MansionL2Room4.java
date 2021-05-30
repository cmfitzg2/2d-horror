package Worlds.MansionInterior.L2;

import Entities.Creatures.Player;
import Tiles.Tile;
import Variables.Handler;
import Worlds.World;
import Worlds.WorldManager;

import java.awt.*;

public class MansionL2Room4 extends World {

    public MansionL2Room4(Handler handler, int id, Player player) {
        super(handler, "res/worlds/mansion-L2-room-4.txt", id, player);
    }

    @Override
    public void checkLoadZones() {
        Rectangle loadzoneL2Room1 = new Rectangle(11 * Tile.TILEWIDTH - (int) handler.getGameCamera().getxOffset(), 13 * Tile.TILEHEIGHT - (int) handler.getGameCamera().getyOffset(),
                Tile.TILEWIDTH / 2, Tile.TILEHEIGHT);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneL2Room1)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.MANSION_L2_ROOM_1_ID), 20 * Tile.TILEWIDTH, 13.75f * Tile.TILEHEIGHT);
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
