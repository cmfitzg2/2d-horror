package Worlds;

import Entities.Creatures.*;
import Entities.StaticEntities.*;
import Graphics.Assets;
import Tiles.Tile;
import Utils.GeneralUtils;
import Variables.Flags;
import Variables.GeneralConstants;
import Variables.Handler;

public class MansionExterior extends World {

    private boolean initialCutsceneTransition = true;

    public MansionExterior(Handler handler, int id, Player player) {
        super(handler, "res/worlds/mansion-exterior.txt", id, player);
    }

    @Override
    public void checkLoadZones() {

    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new BellTower(handler, 5 * Tile.TILEWIDTH, 5 * Tile.TILEHEIGHT, Assets.bellTower.getWidth() * 2, Assets.bellTower.getHeight() * 2, true, null));
        entityManager.addEntity(new Mansion(handler, 20 * Tile.TILEWIDTH - Assets.mansion.getWidth(), 4 * Tile.TILEHEIGHT, Assets.mansion.getWidth() * 2, Assets.mansion.getHeight() * 2, "mansion"));
        entityManager.addEntity(new IronGate(handler, 0, 5, Assets.fullGate.getWidth() * 2, Assets.fullGate.getHeight() * 2, null));
    }

    @Override
    protected void load() {
        firstRender = true;
        fadeIn = false;
    }

    @Override
    protected void transitionTo() {
        handler.getFlags().setTimeOfDay(Flags.TIME_OF_DAY_BRIGHT);
        handler.getPlayer().setAmbientLight(Flags.TIME_OF_DAY_BRIGHT);
        if (!handler.getGame().isFadeIn()) {
            if (initialCutsceneTransition) {
                GeneralUtils.levelFadeIn(handler, GeneralConstants.veryLongLevelTransition);
            } else if (fadeIn) {
                GeneralUtils.levelFadeIn(handler, -1);
            }
        }
        if (handler.getGame().isFadeIn() && handler.getGame().isFinishedFadingIn()) {
            GeneralUtils.stopLevelFadeIn(handler, false);
            transitioningTo = false;
            initialCutsceneTransition = false;
        }
    }
}
