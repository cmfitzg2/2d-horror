package Worlds;

import Entities.Creatures.Player;
import Entities.StaticEntities.*;
import Tiles.Tile;
import Utils.GeneralUtils;
import Variables.Handler;
import Graphics.Assets;

public class Overworld1 extends World {

    public Overworld1(Handler handler, int id, Player player) {
        super(handler, "res/worlds/overworld1.txt", id, player);
    }

    @Override
    public void checkLoadZones() {

    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new ArtFrameSmall(handler, 72, 8, 48, 48, "Solace", null, Assets.solaceInventory, Assets.darkWall));
        entityManager.addEntity(new ArtFrameSmall(handler, 136, 8, 48, 48, "Prophet", null, Assets.prophetInventory, Assets.darkWall));
        entityManager.addEntity(new Door(handler, 43 * Tile.TILEWIDTH, 47 * Tile.TILEHEIGHT, 64, 96, "Door", handler.getWorldManager().getWorld(WorldManager.MC_HOUSE_2_ID), (int) (Tile.TILEWIDTH * 19.5), Tile.TILEHEIGHT * 16, Door.STAIRS));
        entityManager.addEntity(new Door(handler, 59 * Tile.TILEWIDTH, 47 * Tile.TILEHEIGHT, 64, 96, "Door", handler.getWorldManager().getWorld(WorldManager.MC_HOUSE_2_ID), (int) (Tile.TILEWIDTH * 19.5), Tile.TILEHEIGHT * 16, Door.STAIRS));
        entityManager.addEntity(new Door(handler, 29 * Tile.TILEWIDTH, 34 * Tile.TILEHEIGHT, 64, 96, "Door", handler.getWorldManager().getWorld(WorldManager.MC_HOUSE_2_ID), (int) (Tile.TILEWIDTH * 19.5), Tile.TILEHEIGHT * 16, Door.STAIRS));
        entityManager.addEntity(new Door(handler, 14 * Tile.TILEWIDTH, 44 * Tile.TILEHEIGHT, 64, 96, "Door", handler.getWorldManager().getWorld(WorldManager.SCHOOL_1_ID), 21.5f * Tile.TILEWIDTH, 27 * Tile.TILEHEIGHT, Door.STAIRS));
        entityManager.addEntity(new Window(handler, 12 * Tile.TILEWIDTH - 64, 44 * Tile.TILEHEIGHT - 12, 64, 88, "Window-1"));
        entityManager.addEntity(new Window(handler, 13 * Tile.TILEWIDTH - 32, 44 * Tile.TILEHEIGHT - 12, 64, 88, "Window-2"));
        entityManager.addEntity(new Window(handler, 15 * Tile.TILEWIDTH + 32, 44 * Tile.TILEHEIGHT - 12, 64, 88, "Window-3"));
        entityManager.addEntity(new Window(handler, 16 * Tile.TILEWIDTH + 64, 44 * Tile.TILEHEIGHT - 12, 64, 88, "Window-4"));
        entityManager.addEntity(new BellTower(handler, 80 * Tile.TILEWIDTH, 50 * Tile.TILEHEIGHT, Assets.bellTower.getWidth() * 2, Assets.bellTower.getHeight() * 2, true, null));
        createGate();
    }

    @Override
    protected void load() {
        firstRender = true;
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

    private void createGate() {
        int pillarWidth = Assets.ironGate[IronGate.NORMAL_PILLAR].getWidth();
        int gateSideWidth = Assets.ironGate[IronGate.GATE_LEFT].getWidth();
        entityManager.addEntity(new IronGate(handler, 43 * Tile.TILEWIDTH, 60 * Tile.TILEHEIGHT,
                pillarWidth * 2, Assets.height * 4, null, IronGate.BOTTOM_CORNER_PILLAR, false));
        entityManager.addEntity(new IronGate(handler, 43 * Tile.TILEWIDTH + pillarWidth * 2, 60 * Tile.TILEHEIGHT,
                gateSideWidth * 2, Assets.height * 4, null, IronGate.GATE_RIGHT, true));
        entityManager.addEntity(new IronGate(handler, 43 * Tile.TILEWIDTH + pillarWidth * 2 + gateSideWidth * 2,
                60 * Tile.TILEHEIGHT, Assets.width * 2, Assets.height * 4, null, IronGate.GATE, true));
        entityManager.addEntity(new IronGate(handler, 43 * Tile.TILEWIDTH + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 2,
                60 * Tile.TILEHEIGHT, gateSideWidth * 2, Assets.height * 4, null, IronGate.GATE_LEFT, true));
        entityManager.addEntity(new IronGate(handler, 43 * Tile.TILEWIDTH + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 2 + gateSideWidth * 2,
                60 * Tile.TILEHEIGHT, pillarWidth * 2, Assets.height * 4, null, IronGate.BOTTOM_CORNER_PILLAR, false));
        entityManager.addEntity(new IronGate(handler, 43 * Tile.TILEWIDTH + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 2 + gateSideWidth * 2,
                60 * Tile.TILEHEIGHT - Assets.height * 4, pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));
        entityManager.addEntity(new IronGate(handler, 43 * Tile.TILEWIDTH + pillarWidth * 2 + gateSideWidth * 2 + Assets.width * 2 + gateSideWidth * 2,
                60 * Tile.TILEHEIGHT - Assets.height * 4 - Assets.height * 4, pillarWidth * 2, Assets.height * 4, null, IronGate.TOP_CORNER_PILLAR, true));
        entityManager.addEntity(new IronGate(handler, 43 * Tile.TILEWIDTH, 60 * Tile.TILEHEIGHT - Assets.height * 4,
                pillarWidth * 2, Assets.height * 4, null, IronGate.VERTICAL, false));
        entityManager.addEntity(new IronGate(handler, 43 * Tile.TILEWIDTH, 60 * Tile.TILEHEIGHT - Assets.height * 4 - Assets.height * 4,
                pillarWidth * 2, Assets.height * 4, null, IronGate.TOP_CORNER_PILLAR, true));
        entityManager.addEntity(new FrontGate(handler, 43 * Tile.TILEWIDTH, 64 * Tile.TILEHEIGHT,
                Assets.frontGateClosed.getWidth() * 2, Assets.frontGateClosed.getHeight() * 2, null, false));
        entityManager.addEntity(new FrontGateSide(handler, 43 * Tile.TILEWIDTH - Assets.frontGateLeft.getWidth() * 2, 64 * Tile.TILEHEIGHT,
                Assets.frontGateLeft.getWidth() * 2, Assets.frontGateLeft.getHeight() * 2, null, true));
        entityManager.addEntity(new FrontGateSide(handler, 43 * Tile.TILEWIDTH + Assets.frontGateClosed.getWidth() * 2, 64 * Tile.TILEHEIGHT,
                Assets.frontGateRight.getWidth() * 2, Assets.frontGateRight.getHeight() * 2, null, false));
    }
}
