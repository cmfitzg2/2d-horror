package Worlds.MansionInterior.L1;

import Entities.Creatures.Player;
import Entities.StaticEntities.Door;
import Graphics.Assets;
import Tiles.Tile;
import Variables.GeneralConstants;
import Variables.Handler;
import Worlds.World;
import Worlds.WorldManager;

import java.awt.*;

public class MansionL1Room2 extends World {

    public MansionL1Room2(Handler handler, int id, Player player) {
        super(handler, "res/worlds/mansion-L1-room-2.txt", id, player);
    }

    @Override
    public void checkLoadZones() {
        Rectangle loadzoneL1Room1 = new Rectangle((int) (24.5f * Tile.TILEWIDTH - (int) handler.getGameCamera().getxOffset()), 13 * Tile.TILEHEIGHT - (int) handler.getGameCamera().getyOffset(),
                Tile.TILEWIDTH / 2, Tile.TILEHEIGHT);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneL1Room1)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.MANSION_L1_ROOM_1_ID), 14 * Tile.TILEWIDTH, 8.75f * Tile.TILEHEIGHT);
        }
    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new Door(handler, 18 * Tile.TILEWIDTH - Assets.closedDoorOne.getWidth() / 2f, 10 * Tile.TILEHEIGHT - Assets.closedDoorOne.getHeight() * 2,
                Assets.closedDoorOne.getWidth() * 2, Assets.closedDoorOne.getHeight() * 2, null,
                WorldManager.MANSION_L1_ROOM_4_ID, 18 * Tile.TILEWIDTH, 16 * Tile.TILEHEIGHT, Door.PLAIN_WOOD, Door.VISIBLE,
                GeneralConstants.levelTransitionFrames, Door.UNLOCKED));
    }

    @Override
    protected void load() {
        firstRender = true;
        //handler.getFlags().setTimeOfDay(Flags.TIME_OF_DAY_PITCH_BLACK);
        //handler.getPlayer().setAmbientLight(Flags.TIME_OF_DAY_PITCH_BLACK);
    }
}
