package Worlds;

import Cutscenes.Cutscene;
import Entities.Creatures.*;
import Entities.StaticEntities.*;
import Tiles.Tile;
import Utils.GeneralUtils;
import Variables.Flags;
import Variables.GeneralConstants;
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
        entityManager.addEntity(new Door(handler, 43 * Tile.TILEWIDTH, 47 * Tile.TILEHEIGHT, 64, 96, "Door",
                handler.getWorldManager().getWorld(WorldManager.MC_HOUSE_2_ID), (int) (Tile.TILEWIDTH * 19.5), Tile.TILEHEIGHT * 16,
                Door.STAIRS, true, GeneralConstants.levelTransitionFrames, false));
        entityManager.addEntity(new Door(handler, 59 * Tile.TILEWIDTH, 47 * Tile.TILEHEIGHT, 64, 96, "Door",
                handler.getWorldManager().getWorld(WorldManager.MC_HOUSE_2_ID), (int) (Tile.TILEWIDTH * 19.5), Tile.TILEHEIGHT * 16,
                Door.STAIRS, false, GeneralConstants.levelTransitionFrames, false));
        entityManager.addEntity(new Door(handler, 29 * Tile.TILEWIDTH, 34 * Tile.TILEHEIGHT, 64, 96, "Door",
                handler.getWorldManager().getWorld(WorldManager.MC_HOUSE_2_ID), (int) (Tile.TILEWIDTH * 19.5), Tile.TILEHEIGHT * 16,
                Door.STAIRS, false, GeneralConstants.levelTransitionFrames, false));
        entityManager.addEntity(new Door(handler, 14 * Tile.TILEWIDTH, 44 * Tile.TILEHEIGHT, 64, 96, "Door",
                handler.getWorldManager().getWorld(WorldManager.SCHOOL_1_ID), 21.5f * Tile.TILEWIDTH, 27 * Tile.TILEHEIGHT,
                Door.STAIRS, true, GeneralConstants.levelTransitionFrames, false));
        entityManager.addEntity(new BellTower(handler, 80 * Tile.TILEWIDTH, 50 * Tile.TILEHEIGHT, Assets.bellTower.getWidth() * 2, Assets.bellTower.getHeight() * 2, true, null));
        entityManager.addEntity(new Well(handler, 87 * Tile.TILEWIDTH, 50 * Tile.TILEHEIGHT, Assets.well[0].getWidth() * 2, Assets.well[0].getHeight() * 2, null, Well.TYPE_NORMAL));
        entityManager.addEntity(new Well(handler, 87 * Tile.TILEWIDTH, 54 * Tile.TILEHEIGHT, Assets.well[0].getWidth() * 2, Assets.well[0].getHeight() * 2, null, Well.TYPE_COVERED));
        entityManager.addEntity(new Well(handler, 87 * Tile.TILEWIDTH, 58 * Tile.TILEHEIGHT, Assets.well[0].getWidth() * 2, Assets.well[0].getHeight() * 2, null, Well.TYPE_DAMAGED));
        entityManager.addEntity(new Furnace(handler, 87 * Tile.TILEWIDTH, 62 * Tile.TILEHEIGHT, Assets.furnaceUnlit.getWidth() * 3, Assets.furnaceUnlit.getHeight() * 3, null, Furnace.TYPE_UNLIT));
        entityManager.addEntity(new School(handler, 6 * Tile.TILEWIDTH, 40 * Tile.TILEHEIGHT, Assets.school.getWidth() * 2, Assets.school.getHeight() * 2, "school"));
        entityManager.addEntity(new House(handler, 39 * Tile.TILEWIDTH, 41 * Tile.TILEHEIGHT, Assets.houseDefault.getWidth() * 2, Assets.houseDefault.getHeight() * 2, null, House.STYLE_DEFAULT));

        if (!handler.getFlags().isMCHouseNightCutscene5()) {
            entityManager.addEntity(new Denial(handler, 41.5f * Tile.TILEWIDTH, 49.5f * Tile.TILEHEIGHT, "denial-overworld1"));
            entityManager.addEntity(new Anger(handler, 41.5f * Tile.TILEWIDTH, 50.5f * Tile.TILEHEIGHT, "anger-overworld1"));
            entityManager.addEntity(new Bargaining(handler, 41.5f * Tile.TILEWIDTH, 51.5f * Tile.TILEHEIGHT, "bargaining-overworld1"));
            entityManager.addEntity(new Depression(handler, 46f * Tile.TILEWIDTH, 43 * Tile.TILEHEIGHT, "depression-overworld1"));
            Acceptance acceptance = new Acceptance(handler, 44.5f * Tile.TILEWIDTH, 49.5f * Tile.TILEHEIGHT, "acceptance-overworld1");
            entityManager.addEntity(acceptance);
            acceptance.setDirection("left");
        }
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
        if (handler.getFlags().isOverworldCutscene1() && !handler.getFlags().isMCHouseNightCutscene5()) {
            handler.getFlags().setTimeOfDay(Flags.TIME_OF_DAY_VERY_DARK);
            handler.getPlayer().setAmbientLight(Flags.TIME_OF_DAY_VERY_DARK);
            handler.getFlags().setVisionLimited(false);
        }
        if (handler.getGame().isFadeIn() && handler.getGame().isFinishedFadingIn()) {
            GeneralUtils.stopLevelFadeIn(handler, false);
            transitioningTo = false;
            if (handler.getFlags().isOverworldCutscene1() && !handler.getFlags().isMCHouseNightCutscene5()) {
                handler.getCutsceneManager().setActiveCutscene(handler.getCutsceneManager().getCutscene(Cutscene.OVERWORLD_CUTSCENE_1));
                handler.getFlags().setCutsceneActive(true);
                handler.setPlayerFrozen(true);
            }
        }
    }
}
