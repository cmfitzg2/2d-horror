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
        }
    }
}