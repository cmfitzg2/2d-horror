package Worlds.MansionInterior.L1;

import Entities.Creatures.Player;
import Tiles.Tile;
import Variables.Handler;
import Worlds.World;
import Worlds.WorldManager;

import java.awt.*;

public class MansionL1Room4 extends World {

    public MansionL1Room4(Handler handler, int id, Player player) {
        super(handler, "res/worlds/mansion-L1-room-4.txt", id, player);
    }

    @Override
    public void checkLoadZones() {

    }

    @Override
    protected void addEntities() {

    }

    @Override
    protected void load() {
        firstRender = true;
        //handler.getFlags().setTimeOfDay(Flags.TIME_OF_DAY_PITCH_BLACK);
        //handler.getPlayer().setAmbientLight(Flags.TIME_OF_DAY_PITCH_BLACK);
    }
}
