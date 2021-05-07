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
        entityManager.addEntity(new BellTower(handler, 2.5f * Tile.TILEWIDTH, 4.75f * Tile.TILEHEIGHT, Assets.bellTower.getWidth() * 2, Assets.bellTower.getHeight() * 2, true, null));
        entityManager.addEntity(new Mansion(handler, 20 * Tile.TILEWIDTH - Assets.mansion.getWidth(), 4 * Tile.TILEHEIGHT, Assets.mansion.getWidth() * 2, Assets.mansion.getHeight() * 2, "mansion-mansionexterior1"));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f, 5, Assets.gateTop.getWidth() * 2, Assets.gateTop.getHeight() * 2, null, IronGate.TYPE_TOP));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f, 5 + Assets.height * 4, Assets.gateSide.getWidth() * 2, Assets.gateSide.getHeight() * 2, null, IronGate.TYPE_SIDE));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + Assets.gateTop.getWidth() * 2 - 17 * 2, 5 + Assets.height * 4, Assets.gateSide.getWidth() * 2, Assets.gateSide.getHeight() * 2, null, IronGate.TYPE_SIDE));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f, 5 + Assets.gateTop.getHeight() * 2 + Assets.gateSide.getHeight() * 2, Assets.gateBot.getWidth() * 2, Assets.gateBot.getHeight() * 2, null, IronGate.TYPE_BOT));
        if (handler.getFlags().isMansionExteriorCutscene1()) {
            entityManager.addEntity(new Denial(handler, 20 * Tile.TILEWIDTH - handler.getPlayer().getWidth() - Tile.TILEWIDTH / 32f, 30 * Tile.TILEHEIGHT, "denial-mansionexterior1"));
            entityManager.addEntity(new Anger(handler, 20 * Tile.TILEWIDTH + Tile.TILEWIDTH / 32f, 30 * Tile.TILEHEIGHT, "anger-mansionexterior1"));
            entityManager.addEntity(new Bargaining(handler, 20 * Tile.TILEWIDTH - handler.getPlayer().getWidth() - Tile.TILEWIDTH / 32f, 30 * Tile.TILEHEIGHT + handler.getPlayer().getHeight(), "bargaining-mansionexterior1"));
            entityManager.addEntity(new Depression(handler, 20 * Tile.TILEWIDTH + Tile.TILEWIDTH / 32f, 30 * Tile.TILEHEIGHT + handler.getPlayer().getHeight(), "depression-mansionexterior1"));
            entityManager.addEntity(new Acceptance(handler, 20 * Tile.TILEWIDTH + Tile.TILEWIDTH / 32f, 30 * Tile.TILEHEIGHT + handler.getPlayer().getHeight() * 2, "acceptance-mansionexterior1"));
        }
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
            handler.setPlayerFrozen(true);
        }
    }
}
