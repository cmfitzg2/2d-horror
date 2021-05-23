package Worlds;

import Entities.Creatures.Player;
import Entities.StaticEntities.Door;
import Entities.StaticEntities.MansionStairs;
import Graphics.Assets;
import Tiles.Tile;
import Variables.GeneralConstants;
import Variables.Handler;

public class MansionInterior2 extends World {

    public MansionInterior2(Handler handler, int id, Player player) {
        super(handler, "res/worlds/mansion-interior-2.txt", id, player);
    }

    @Override
    public void checkLoadZones() {

    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new MansionStairs(handler, 15 * Tile.TILEWIDTH, 15 * Tile.TILEHEIGHT - Assets.mansionStairsDownLeft.getHeight() / 2f,
                Assets.mansionStairsDownLeft.getWidth() * 2, Assets.mansionStairsDownLeft.getHeight() * 2, null,
                handler.getWorldManager().getWorld(WorldManager.MANSION_INTERIOR_1_ID), 24 * Tile.TILEWIDTH, 6.9f * Tile.TILEHEIGHT,
                MansionStairs.STYLE_DOWN_LEFT));
        entityManager.addEntity(new Door(handler, 19.5f * Tile.TILEWIDTH - Assets.closedDoorOne.getWidth() / 2f, 14 * Tile.TILEHEIGHT - Assets.closedDoorOne.getHeight() * 2,
                Assets.closedDoorOne.getWidth() * 2, Assets.closedDoorOne.getHeight() * 2, null,
                handler.getWorldManager().getWorld(WorldManager.MC_HOUSE_1_ID), 700, 600, Door.PLAIN_WOOD, Door.VISIBLE,
                GeneralConstants.levelTransitionFrames, Door.LOCKED));
    }

    @Override
    protected void load() {
        firstRender = true;
    }
}
