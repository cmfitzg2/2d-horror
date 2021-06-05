package Worlds.MansionInterior.L2;

import Entities.Creatures.Player;
import Entities.StaticEntities.Door;
import Graphics.Assets;
import Tiles.Tile;
import Variables.GeneralConstants;
import Variables.Handler;
import Worlds.World;
import Worlds.WorldManager;

import java.awt.*;

public class MansionL2Room2 extends World {

    public MansionL2Room2(Handler handler, int id, Player player) {
        super(handler, "res/worlds/mansion-L2-room-2.txt", id, player);
    }

    @Override
    public void checkLoadZones() {
        Rectangle loadzoneL2Room1 = new Rectangle((int) (21.5f * Tile.TILEWIDTH - (int) handler.getGameCamera().getxOffset()), 13 * Tile.TILEHEIGHT - (int) handler.getGameCamera().getyOffset(),
                Tile.TILEWIDTH / 2, Tile.TILEHEIGHT);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneL2Room1)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.MANSION_L2_ROOM_1_ID), 8 * Tile.TILEWIDTH, 6.75f * Tile.TILEHEIGHT);
        }
    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new Door(handler, 15 * Tile.TILEWIDTH - Assets.closedDoorOne.getWidth() / 2f, 10 * Tile.TILEHEIGHT - Assets.closedDoorOne.getHeight() * 2,
                Assets.closedDoorOne.getWidth() * 2, Assets.closedDoorOne.getHeight() * 2, null,
                WorldManager.MANSION_L2_ROOM_1_ID, 700, 600, Door.PLAIN_WOOD, Door.VISIBLE,
                GeneralConstants.levelTransitionFrames, Door.LOCKED));
    }

    @Override
    protected void load() {
        firstRender = true;
    }
}
