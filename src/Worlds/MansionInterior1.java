package Worlds;

import Entities.Creatures.*;
import Tiles.Tile;
import Utils.GeneralUtils;
import Variables.Flags;
import Variables.Handler;

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

    }

    @Override
    protected void load() {
        firstRender = true;
        handler.getFlags().setTimeOfDay(Flags.TIME_OF_DAY_SOME_DARK);
        handler.getPlayer().setAmbientLight(Flags.TIME_OF_DAY_SOME_DARK);
        fadeIn = !handler.getFlags().isMansionExteriorCutscene1();
        handler.getFlags().setMansionExteriorCutscene1(false);
    }

    @Override
    protected void transitionTo() {
        if (!handler.getGame().isFadeIn() && fadeIn) {
            GeneralUtils.levelFadeIn(handler, -1);
        }
        if (handler.getGame().isFadeIn() && handler.getGame().isFinishedFadingIn()) {
            GeneralUtils.stopLevelFadeIn(handler, false);
            transitioningTo = false;
        }
    }
}
