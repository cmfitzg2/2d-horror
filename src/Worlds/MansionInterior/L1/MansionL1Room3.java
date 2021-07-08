package Worlds.MansionInterior.L1;

import Entities.Creatures.Denial;
import Entities.Creatures.Player;
import Entities.StaticEntities.Well;
import Tiles.Tile;
import Variables.Handler;
import Worlds.World;
import Worlds.WorldManager;
import Graphics.Assets;

import java.awt.*;

public class MansionL1Room3 extends World {

    public MansionL1Room3(Handler handler, int id, Player player) {
        super(handler, "res/worlds/mansion-L1-room-3.txt", id, player);
    }

    @Override
    public void checkLoadZones() {
        Rectangle loadzoneL1Room1 = new Rectangle((int) (24.5f * Tile.TILEWIDTH - (int) handler.getGameCamera().getxOffset()), 13 * Tile.TILEHEIGHT - (int) handler.getGameCamera().getyOffset(),
                Tile.TILEWIDTH / 2, Tile.TILEHEIGHT);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneL1Room1)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.MANSION_L1_ROOM_1_ID), 14 * Tile.TILEWIDTH, 15.75f * Tile.TILEHEIGHT);
        }
    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new Well(handler, 18 * Tile.TILEWIDTH - Assets.well[0].getWidth(), 12.5f * Tile.TILEHEIGHT - Assets.well[0].getHeight(),
                Assets.well[0].getWidth() * 2, Assets.well[0].getHeight() * 2, "well-mansionL1R3", Well.TYPE_COVERED));
        //note: denial's position here is essentially hardcoded due to his position being set based on a separate room
        if (handler.getFlags().isDenialMansionCutscene2()) {
            entityManager.addEntity(new Denial(handler, 22.0625f * Tile.TILEWIDTH, 10.625f * Tile.TILEHEIGHT, "denial-mansionL1R3"));
        }
    }

    @Override
    protected void load() {
        firstRender = true;
        //handler.getFlags().setTimeOfDay(Flags.TIME_OF_DAY_PITCH_BLACK);
        //handler.getPlayer().setAmbientLight(Flags.TIME_OF_DAY_PITCH_BLACK);
    }
}
