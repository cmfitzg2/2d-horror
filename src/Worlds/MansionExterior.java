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

    public MansionExterior(Handler handler, int id, Player player) {
        super(handler, "res/worlds/mansion-exterior.txt", id, player);
    }

    @Override
    public void checkLoadZones() {

    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new Follower(handler, 400, 400, null));
        entityManager.addEntity(new BellTower(handler, 5 * Tile.TILEWIDTH, 5 * Tile.TILEHEIGHT, Assets.bellTower.getWidth() * 2, Assets.bellTower.getHeight() * 2, true, null));
        entityManager.addEntity(new Mansion(handler, 15 * Tile.TILEWIDTH, 8 * Tile.TILEHEIGHT, Assets.mansion.getWidth() * 2, Assets.mansion.getHeight() * 2, "mansion"));
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
            handler.getGame().fadeIn(GeneralConstants.veryLongLevelTransition);
        }
        if (handler.getGame().isFadeIn() && handler.getGame().isFinishedFadingIn()) {
            GeneralUtils.stopLevelFadeIn(handler, false);
            transitioningTo = false;
        }
    }

    private void createGate() {
        int pillarWidth = Assets.ironGate[IronGate.NORMAL_PILLAR].getWidth();
        int gateSideWidth = Assets.ironGate[IronGate.GATE_LEFT].getWidth();

        //build the left side
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 2f, 28 * Tile.TILEHEIGHT,
                pillarWidth * 2, Assets.height * 4, null, IronGate.BOTTOM_CORNER_PILLAR, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 2f, 28 * Tile.TILEHEIGHT - Assets.height * 4,
                pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 2f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 2 + gateSideWidth * 2,
                28 * Tile.TILEHEIGHT - Assets.height * 4 - Assets.height * 4, pillarWidth * 2, Assets.height * 4, null, IronGate.TOP_CORNER_PILLAR, true));

        //build the front
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 2f + pillarWidth * 2, 28 * Tile.TILEHEIGHT,
                gateSideWidth * 2, Assets.height * 4, null, IronGate.GATE_RIGHT, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 2f + pillarWidth * 2 + gateSideWidth * 2,
                28 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 2f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 2,
                28 * Tile.TILEHEIGHT, gateSideWidth * 2, Assets.height * 4, null, IronGate.GATE_LEFT, true));


        //build the right
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 2f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 2 + gateSideWidth * 2,
                28 * Tile.TILEHEIGHT, pillarWidth * 2, Assets.height * 4, null, IronGate.BOTTOM_CORNER_PILLAR, false));
        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 2f + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 2 + gateSideWidth * 2,
                28 * Tile.TILEHEIGHT - Assets.height * 4, pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));


        entityManager.addEntity(new IronGate(handler, Tile.TILEWIDTH / 2f, 28 * Tile.TILEHEIGHT - Assets.height * 4 - Assets.height * 4,
                pillarWidth * 2, Assets.height * 4, null, IronGate.TOP_CORNER_PILLAR, true));


/*        entityManager.addEntity(new FrontGate(handler, Tile.TILEWIDTH, 28 * Tile.TILEHEIGHT,
                Assets.frontGateClosed.getWidth() * 2, Assets.frontGateClosed.getHeight() * 2, null, false));
        entityManager.addEntity(new FrontGateSide(handler, Tile.TILEWIDTH - Assets.frontGateLeft.getWidth() * 2, 28 * Tile.TILEHEIGHT,
                Assets.frontGateLeft.getWidth() * 2, Assets.frontGateLeft.getHeight() * 2, null, true));
        entityManager.addEntity(new FrontGateSide(handler, Tile.TILEWIDTH + Assets.frontGateClosed.getWidth() * 2, 28 * Tile.TILEHEIGHT,
                Assets.frontGateRight.getWidth() * 2, Assets.frontGateRight.getHeight() * 2, null, false));*/
    }
}
