package Worlds;

import Entities.Creatures.*;
import Entities.StaticEntities.MansionStairs;
import Tiles.Tile;
import Variables.Flags;
import Variables.Handler;
import Graphics.Assets;

import java.awt.*;

public class MansionInterior1 extends World {

    private boolean initialCutsceneTransition = true;

    public MansionInterior1(Handler handler, int id, Player player) {
        super(handler, "res/worlds/mansion-interior-1.txt", id, player);
    }

    @Override
    public void checkLoadZones() {
        Rectangle loadzoneOutside = new Rectangle(17 * Tile.TILEWIDTH - (int) handler.getGameCamera().getxOffset(), (int) (19.5f * Tile.TILEHEIGHT - (int) handler.getGameCamera().getyOffset()), Tile.TILEWIDTH * 2, Tile.TILEHEIGHT / 2);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneOutside)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.MANSION_EXTERIOR_ID), 19 * Tile.TILEWIDTH + handler.getPlayer().getWidth() / 2f, 18 * Tile.TILEHEIGHT);
        }
    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new MansionStairs(handler, 25 * Tile.TILEWIDTH, 7 * Tile.TILEHEIGHT - Assets.mansionStairsUpRight.getHeight(),
                Assets.mansionStairsUpRight.getWidth() * 2, Assets.mansionStairsUpRight.getHeight() * 2, null,
                handler.getWorldManager().getWorld(WorldManager.MANSION_INTERIOR_2_ID), 17 * Tile.TILEWIDTH + handler.getPlayer().getWidth() / 2f, 15 * Tile.TILEHEIGHT,
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
