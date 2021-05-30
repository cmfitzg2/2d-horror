package Worlds.MansionInterior.L2;

import Entities.Creatures.*;
import Entities.StaticEntities.MansionStairs;
import Tiles.Tile;
import Variables.Flags;
import Variables.Handler;
import Graphics.Assets;
import Worlds.World;
import Worlds.WorldManager;

import java.awt.*;

public class MansionL2Room1 extends World {

    public MansionL2Room1(Handler handler, int id, Player player) {
        super(handler, "res/worlds/mansion-L2-room-1.txt", id, player);
    }

    @Override
    public void checkLoadZones() {
        Rectangle loadzoneOutside = new Rectangle(17 * Tile.TILEWIDTH - (int) handler.getGameCamera().getxOffset(), (int) (19.5f * Tile.TILEHEIGHT - (int) handler.getGameCamera().getyOffset()), Tile.TILEWIDTH * 2, Tile.TILEHEIGHT / 2);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneOutside)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.MANSION_EXTERIOR_ID), 19 * Tile.TILEWIDTH + handler.getPlayer().getWidth() / 2f, 18 * Tile.TILEHEIGHT);
        }

        Rectangle loadzoneL2Room2 = new Rectangle(7 * Tile.TILEWIDTH - (int) handler.getGameCamera().getxOffset(), 7 * Tile.TILEHEIGHT - (int) handler.getGameCamera().getyOffset(),
                Tile.TILEWIDTH / 2, Tile.TILEHEIGHT);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneL2Room2)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.MANSION_L2_ROOM_2_ID), 20 * Tile.TILEWIDTH, 12.75f * Tile.TILEHEIGHT);
        }

        Rectangle loadzoneL2Room3 = new Rectangle(14 * Tile.TILEWIDTH - (int) handler.getGameCamera().getxOffset(), 14 * Tile.TILEHEIGHT - (int) handler.getGameCamera().getyOffset(),
                Tile.TILEWIDTH / 2, Tile.TILEHEIGHT);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneL2Room3)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.MANSION_L2_ROOM_3_ID), 23 * Tile.TILEWIDTH, 12.75f * Tile.TILEHEIGHT);
        }

        Rectangle loadzoneL2Room4 = new Rectangle((int) (21.5f * Tile.TILEWIDTH - (int) handler.getGameCamera().getxOffset()), 14 * Tile.TILEHEIGHT - (int) handler.getGameCamera().getyOffset(),
                Tile.TILEWIDTH / 2, Tile.TILEHEIGHT);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneL2Room4)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.MANSION_L2_ROOM_4_ID), 12 * Tile.TILEWIDTH, 12.75f * Tile.TILEHEIGHT);
        }
    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new MansionStairs(handler, 25 * Tile.TILEWIDTH, 7 * Tile.TILEHEIGHT - Assets.mansionStairsUpRight.getHeight(),
                Assets.mansionStairsUpRight.getWidth() * 2, Assets.mansionStairsUpRight.getHeight() * 2, null,
                handler.getWorldManager().getWorld(WorldManager.MANSION_L3_ROOM_1_ID), 17 * Tile.TILEWIDTH + handler.getPlayer().getWidth() / 2f, 15 * Tile.TILEHEIGHT,
                MansionStairs.STYLE_UP_RIGHT));
    }

    @Override
    protected void load() {
        firstRender = true;
        handler.getFlags().setTimeOfDay(Flags.TIME_OF_DAY_SOME_DARK);
        handler.getPlayer().setAmbientLight(Flags.TIME_OF_DAY_SOME_DARK);
        fadeIn = !handler.getFlags().isMansionExteriorCutscene1();
        handler.getFlags().setMansionExteriorCutscene1(false);
    }
}
