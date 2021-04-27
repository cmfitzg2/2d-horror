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
        entityManager.addEntity(new Mansion(handler, 20.5f * Tile.TILEWIDTH - Assets.mansion.getWidth(), 4 * Tile.TILEHEIGHT, Assets.mansion.getWidth() * 2, Assets.mansion.getHeight() * 2, "mansion"));
        createGate();
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

    private void createGate() {
        int pillarWidth = Assets.ironGate[IronGate.NORMAL_PILLAR].getWidth();
        int gateSideWidth = Assets.ironGate[IronGate.GATE_LEFT].getWidth();
        int frontGateHeightOffset = 19;

        //build the left side
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f,
                22 * Tile.TILEHEIGHT, pillarWidth * 2, Assets.height * 4, null, IronGate.BOTTOM_CORNER_PILLAR, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f,
                22 * Tile.TILEHEIGHT - Assets.height * 4, pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f,
                22 * Tile.TILEHEIGHT - Assets.height * 8, pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f,
                22 * Tile.TILEHEIGHT - Assets.height * 12, pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f,
                22 * Tile.TILEHEIGHT - Assets.height * 16, pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f,
                22 * Tile.TILEHEIGHT - Assets.height * 20, pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f,
                22 * Tile.TILEHEIGHT - Assets.height * 24, pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f,
                22 * Tile.TILEHEIGHT - Assets.height * 28, pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f,
                22 * Tile.TILEHEIGHT - Assets.height * 32, pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f,
                22 * Tile.TILEHEIGHT - Assets.height * 36, pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f,
                22 * Tile.TILEHEIGHT - Assets.height * 40, pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f,
                22 * Tile.TILEHEIGHT - Assets.height * 44, pillarWidth * 2, Assets.height * 4, null, IronGate.TOP_CORNER_PILLAR, false));

        //build the top
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2,
                22 * Tile.TILEHEIGHT - Assets.height * 44, gateSideWidth * 2, Assets.height * 4, null, IronGate.GATE_RIGHT, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 2,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 4,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 6,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 8,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 10,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 12,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 14,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 16,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 18,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 20,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 22,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 24,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 26,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 28,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 30,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 32,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 34,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 36,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 38,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 40,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 36,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 38,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 40,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 42,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 44,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 46,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 48,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 50,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 52,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 54,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 56,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 58,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 60,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 62,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 64,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 66,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 68,
                22 * Tile.TILEHEIGHT - Assets.height * 44, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));


        //build the front
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2,
                22 * Tile.TILEHEIGHT, gateSideWidth * 2, Assets.height * 4, null, IronGate.GATE_RIGHT, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 2,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 4,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 6,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 8,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 10,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 12,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 14,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 16,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 18,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 20,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 22,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 24,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 26,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 28,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 30,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 32,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));

        //the front gate
        entityManager.addEntity(new FrontGateSide(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 34,
                22 * Tile.TILEHEIGHT - 2 * frontGateHeightOffset, Assets.frontGateLeft.getWidth() * 2, Assets.frontGateLeft.getHeight() * 2, null, true));
        entityManager.addEntity(new FrontGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 34 + Assets.frontGateLeft.getWidth() * 2,
                22 * Tile.TILEHEIGHT - 2 * frontGateHeightOffset, Assets.frontGateClosed.getWidth() * 2, Assets.frontGateClosed.getHeight() * 2, null, false));
        entityManager.addEntity(new FrontGateSide(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 34 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2,
                22 * Tile.TILEHEIGHT - 2 * frontGateHeightOffset, Assets.frontGateRight.getWidth() * 2, Assets.frontGateRight.getHeight() * 2, null, false));

        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 36,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 38,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 40,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 42,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 44,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 46,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 48,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 50,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 52,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 54,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 56,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 58,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 60,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 62,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 64,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 66,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));


        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 68,
                22 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 70,
                22 * Tile.TILEHEIGHT, pillarWidth * 2, Assets.height * 4, null, IronGate.BOTTOM_CORNER_PILLAR, false));


        //build the right
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 70,
                22 * Tile.TILEHEIGHT - Assets.height * 4, pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 70,
                22 * Tile.TILEHEIGHT - Assets.height * 8, pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 70,
                22 * Tile.TILEHEIGHT - Assets.height * 12, pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 70,
                22 * Tile.TILEHEIGHT - Assets.height * 16, pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 70,
                22 * Tile.TILEHEIGHT - Assets.height * 20, pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));

        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 70,
                22 * Tile.TILEHEIGHT - Assets.height * 24, pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 70,
                22 * Tile.TILEHEIGHT - Assets.height * 28, pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 70,
                22 * Tile.TILEHEIGHT - Assets.height * 32, pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 70,
                22 * Tile.TILEHEIGHT - Assets.height * 36, pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 70,
                22 * Tile.TILEHEIGHT - Assets.height * 40, pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 4f + pillarWidth * 2 + gateSideWidth * 2 + Assets.frontGateLeft.getWidth() * 2 + Assets.frontGateClosed.getWidth() * 2 + Assets.width * 70,
                22 * Tile.TILEHEIGHT - Assets.height * 44, pillarWidth * 2, Assets.height * 4, null, IronGate.TOP_CORNER_PILLAR, false));
    }
}
