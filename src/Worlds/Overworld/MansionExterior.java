package Worlds.Overworld;

import Entities.Creatures.*;
import Entities.StaticEntities.*;
import Graphics.Assets;
import Tiles.Tile;
import Utils.GeneralUtils;
import Variables.GeneralConstants;
import Variables.Handler;
import Worlds.World;
import Worlds.WorldManager;

import java.awt.*;

public class MansionExterior extends World {

    private boolean initialCutsceneTransition = true;
    private Rectangle loadzoneMansion;

    public MansionExterior(Handler handler, int id, Player player) {
        super(handler, "res/worlds/mansion-exterior.txt", id, player);
    }

    @Override
    public void checkLoadZones() {
        if (!handler.getFlags().isMansionExteriorCutscene1()) {
            loadzoneMansion = new Rectangle(19 * Tile.TILEWIDTH - (int) handler.getGameCamera().getxOffset(), (int) (18 * Tile.TILEHEIGHT - (int) handler.getGameCamera().getyOffset()),
                    Tile.TILEWIDTH * 2, Tile.TILEHEIGHT / 4);
            if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneMansion)) {
                transitionFrom(handler.getWorldManager().getWorld(WorldManager.MANSION_L2_ROOM_1_ID), 17 * Tile.TILEWIDTH + handler.getPlayer().getWidth() / 2f, 17.5f * Tile.TILEHEIGHT);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (firstRender) {
            firstRender = false;
            return;
        }
        renderTiles(g);
        entityManager.render(g);
    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new BellTower(handler, 2.5f * Tile.TILEWIDTH, 4.75f * Tile.TILEHEIGHT, Assets.bellTower.getWidth() * 2, Assets.bellTower.getHeight() * 2, true, null));
        entityManager.addEntity(new Door(handler, 2.5f * Tile.TILEWIDTH + Assets.bellTower.getWidth() - Assets.doorStairs.getWidth(),
                4.75f * Tile.TILEHEIGHT + Assets.bellTower.getHeight() * 2 - Assets.closedDoorOne.getHeight() * 2 - Assets.doorStairs.getHeight(), Assets.closedDoorOne.getWidth() * 2, Assets.closedDoorOne.getHeight() * 2,
                "belltower-overworld1", WorldManager.MC_HOUSE_1_ID, 700, 600, Door.PLAIN_WOOD, true, GeneralConstants.levelTransitionFrames, true));
        entityManager.addEntity(new Mansion(handler, 20 * Tile.TILEWIDTH - Assets.mansion.getWidth(), 4 * Tile.TILEHEIGHT, Assets.mansion.getWidth() * 2, Assets.mansion.getHeight() * 2, "mansion-mansionexterior1"));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f, 5, Assets.gateTop.getWidth() * 2, Assets.gateTop.getHeight() * 2, null, IronGate.TYPE_TOP));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f, 5 + Assets.height * 4, Assets.gateSide.getWidth() * 2, Assets.gateSide.getHeight() * 2, null, IronGate.TYPE_SIDE));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + Assets.gateTop.getWidth() * 2 - 17 * 2, 5 + Assets.height * 4, Assets.gateSide.getWidth() * 2, Assets.gateSide.getHeight() * 2, null, IronGate.TYPE_SIDE));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f, 5 + Assets.gateTop.getHeight() * 2 + Assets.gateSide.getHeight() * 2, Assets.gateBot.getWidth() * 2, Assets.gateBot.getHeight() * 2, "irongatebot-mansionexterior1", IronGate.TYPE_BOT));
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
        fadeIn = !handler.getFlags().isMansionExteriorCutscene1();
    }

    @Override
    protected void transitionTo() {
        if (!handler.getGame().isFadeIn()) {
            if (initialCutsceneTransition && handler.getFlags().isMansionExteriorCutscene1()) {
                GeneralUtils.levelFadeIn(handler, GeneralConstants.veryLongLevelTransition);
            } else if (fadeIn) {
                GeneralUtils.levelFadeIn(handler, -1);
            }
        }
        if (handler.getGame().isFadeIn() && handler.getGame().isFinishedFadingIn()) {
            if (handler.getFlags().isMansionExteriorCutscene1()) {
                GeneralUtils.stopLevelFadeIn(handler, true);
                initialCutsceneTransition = false;
            } else {
                GeneralUtils.stopLevelFadeIn(handler, false);
            }
            transitioningTo = false;
        }
    }
}
