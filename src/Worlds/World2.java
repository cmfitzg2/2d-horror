package Worlds;

import Cutscenes.CutsceneManager;
import Entities.Creatures.Friend1;
import Entities.StaticEntities.*;
import Tiles.Tile;
import Utils.GeneralUtils;
import Variables.Handler;
import Graphics.Assets;

public class World2 extends World {

    public World2(Handler handler, String path, int id) {
        super(handler, path, id, null);
    }

    @Override
    public void checkLoadZones() {
/*        if (entityManager.getPlayer().getX() > 1000 && entityManager.getPlayer().getY() > 1000) {
            transitionFrom(handler.getWorldManager().getWorld(1), 400, 450);
        }*/
    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new ArtFrameSmall(handler, 72, 8, 48, 48, "Solace", null, Assets.solaceInventory, Assets.darkWall));
        entityManager.addEntity(new ArtFrameSmall(handler, 136, 8, 48, 48, "Prophet", null, Assets.prophetInventory, Assets.darkWall));
        entityManager.addEntity(new Friend1(handler, 2748 + handler.getWidth() / 2.0f + 64, 3322, "Friend1"));
        entityManager.addEntity(new Door(handler, 43 * Tile.TILEWIDTH, 47 * Tile.TILEHEIGHT, 64, 96, "Door", handler.getWorldManager().getWorld(1), 930, 720, true, false));
        entityManager.addEntity(new Door(handler, 59 * Tile.TILEWIDTH, 47 * Tile.TILEHEIGHT, 64, 96, "Door", handler.getWorldManager().getWorld(1), 930, 720, true, false));
        entityManager.addEntity(new Door(handler, 29 * Tile.TILEWIDTH, 34 * Tile.TILEHEIGHT, 64, 96, "Door", handler.getWorldManager().getWorld(1), 930, 720, true, false));
        entityManager.addEntity(new Door(handler, 14 * Tile.TILEWIDTH, 44 * Tile.TILEHEIGHT, 64, 96, "Door", handler.getWorldManager().getWorld(3), 21.5f * Tile.TILEWIDTH, 27 * Tile.TILEHEIGHT, true, true));
        entityManager.addEntity(new Window(handler, 12 * Tile.TILEWIDTH - 64, 44 * Tile.TILEHEIGHT - 12, 64, 88, "Window-1"));
        entityManager.addEntity(new Window(handler, 13 * Tile.TILEWIDTH - 32, 44 * Tile.TILEHEIGHT - 12, 64, 88, "Window-2"));
        entityManager.addEntity(new Window(handler, 15 * Tile.TILEWIDTH + 32, 44 * Tile.TILEHEIGHT - 12, 64, 88, "Window-3"));
        entityManager.addEntity(new Window(handler, 16 * Tile.TILEWIDTH + 64, 44 * Tile.TILEHEIGHT - 12, 64, 88, "Window-4"));
    }

    @Override
    protected void load() {
        handler.getFlags().setVisionLimited(false);
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
            if (handler.getFlags().isFriendEncounter1()) {
                handler.getFlags().setCutsceneActive(true);
                CutsceneManager cutsceneManager = handler.getCutsceneManager();
                cutsceneManager.setActiveCutscene(cutsceneManager.getCutscene(2));
                handler.setPlayerFrozen(true);
            }
        }
    }
}
