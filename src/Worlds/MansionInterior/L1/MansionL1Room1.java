package Worlds.MansionInterior.L1;

import Entities.Creatures.Player;
import Entities.StaticEntities.BasementKitchen;
import Entities.StaticEntities.Furnace;
import Entities.StaticEntities.MansionStairs;
import Entities.StaticEntities.SideTable;
import Graphics.Assets;
import Tiles.Tile;
import Variables.Flags;
import Variables.Handler;
import Worlds.World;
import Worlds.WorldManager;

import java.awt.*;

public class MansionL1Room1 extends World {

    public MansionL1Room1(Handler handler, int id, Player player) {
        super(handler, "res/worlds/mansion-L1-room-1.txt", id, player);
    }

    @Override
    public void checkLoadZones() {
        Rectangle loadzoneL1Room3 = new Rectangle(13 * Tile.TILEWIDTH - (int) handler.getGameCamera().getxOffset(), 16 * Tile.TILEHEIGHT - (int) handler.getGameCamera().getyOffset(),
                Tile.TILEWIDTH / 2, Tile.TILEHEIGHT);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneL1Room3)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.MANSION_L1_ROOM_3_ID), 23 * Tile.TILEWIDTH, 12.75f * Tile.TILEHEIGHT);
        }

        Rectangle loadzoneL1Room2 = new Rectangle(13 * Tile.TILEWIDTH - (int) handler.getGameCamera().getxOffset(), 9 * Tile.TILEHEIGHT - (int) handler.getGameCamera().getyOffset(),
                Tile.TILEWIDTH / 2, Tile.TILEHEIGHT);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneL1Room2)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.MANSION_L1_ROOM_2_ID), 23 * Tile.TILEWIDTH, 12.75f * Tile.TILEHEIGHT);
        }
    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new Furnace(handler, 19 * Tile.TILEWIDTH, 4 * Tile.TILEHEIGHT,
                Assets.furnaceUnlit.getWidth() * 2, Assets.furnaceUnlit.getHeight() * 2, null, Furnace.TYPE_UNLIT));
    }

    @Override
    protected void load() {
        firstRender = true;
        handler.getFlags().setTimeOfDay(Flags.TIME_OF_DAY_SOME_DARK);
        handler.getPlayer().setAmbientLight(Flags.TIME_OF_DAY_SOME_DARK);
    }
}
