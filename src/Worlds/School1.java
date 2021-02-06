package Worlds;

import Cutscenes.CutsceneManager;
import Entities.Creatures.*;
import Entities.StaticEntities.Door;
import Entities.StaticEntities.Locker;
import Tiles.Tile;
import Variables.Handler;
import Graphics.Assets;

import java.awt.*;

public class School1 extends World {

    public School1(Handler handler, int id, Player player) {
        super(handler, "res/worlds/school1.txt", id, player);
    }

    @Override
    public void checkLoadZones() {
        Rectangle loadzoneClassroom = new Rectangle(383 - (int) handler.getGameCamera().getxOffset(), 733 - (int) handler.getGameCamera().getyOffset(), 63, 34);
        Rectangle loadzoneOutside = new Rectangle(1342 - (int) handler.getGameCamera().getxOffset(), 1818 - (int) handler.getGameCamera().getyOffset(), 130, 36);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneClassroom)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.CLASSROOM_1_ID), 23 * Tile.TILEWIDTH, 4 * Tile.TILEHEIGHT);
        }
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneOutside)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.OVERWORLD_1_ID), 14 * Tile.TILEWIDTH, 46 * Tile.TILEHEIGHT);
        }
    }

    @Override
    protected void addEntities() {
        if (handler.getFlags().isSchoolCutscene1()) {
            if (handler.getFlags().isSchoolCutscene1()) {
                Denial denial = new Denial(handler, 730, 520, "denial-school1");
                denial.setDirection("down");
                entityManager.addEntity(denial);
                Anger anger = new Anger(handler, 670, 520, "anger-school1");
                anger.setDirection("down");
                entityManager.addEntity(anger);
                Bargaining bargaining = new Bargaining(handler, 610, 550, "bargaining-school1");
                bargaining.setDirection("right");
                entityManager.addEntity(bargaining);
                Depression depression = new Depression(handler, 610, 600, "depression-school1");
                depression.setDirection("right");
                entityManager.addEntity(depression);
                Acceptance acceptance = new Acceptance(handler, 790, 550, "acceptance-school1");
                acceptance.setDirection("left");
                entityManager.addEntity(acceptance);
            }
        }
        entityManager.addEntity(new Door(handler, 15 * Tile.TILEWIDTH, 2.5f * Tile.TILEHEIGHT,
                Assets.bathroomDoorMale.getWidth() * 2, Assets.bathroomDoorMale.getHeight() * 2, "school1-bathroom-male",
                handler.getWorldManager().getWorld(WorldManager.BATHROOM_1_ID), 9.5f * Tile.TILEWIDTH, 10 * Tile.TILEHEIGHT, Door.BATHROOM_MALE));
        entityManager.addEntity(new Locker(handler, 25 * Tile.TILEWIDTH, 2.5f * Tile.TILEHEIGHT, Assets.locker.getWidth() * 2, Assets.locker.getHeight() * 2, "school1-locker1"));
        entityManager.addEntity(new Locker(handler, 26 * Tile.TILEWIDTH, 2.5f * Tile.TILEHEIGHT, Assets.locker.getWidth() * 2, Assets.locker.getHeight() * 2, "school1-locker2"));
        entityManager.addEntity(new Locker(handler, 27 * Tile.TILEWIDTH, 2.5f * Tile.TILEHEIGHT, Assets.locker.getWidth() * 2, Assets.locker.getHeight() * 2, "school1-locker3"));
        entityManager.addEntity(new Locker(handler, 28 * Tile.TILEWIDTH, 2.5f * Tile.TILEHEIGHT, Assets.locker.getWidth() * 2, Assets.locker.getHeight() * 2, "school1-locker4"));
        entityManager.addEntity(new Locker(handler, 29 * Tile.TILEWIDTH, 2.5f * Tile.TILEHEIGHT, Assets.locker.getWidth() * 2, Assets.locker.getHeight() * 2, "school1-locker5"));
    }

    @Override
    protected void load() {
        handler.getFlags().setVisionLimited(false);
        firstRender = true;
    }

    @Override
    protected void tick() {
        super.tick();
        if (handler.getFlags().isSchoolCutscene1() && !handler.getFlags().isCutsceneActive()) {
            if (handler.getPlayer().getY() <= Tile.TILEWIDTH * 10 && handler.getPlayer().getX() < Tile.TILEWIDTH * 16) {
                handler.getFlags().setCutsceneActive(true);
                CutsceneManager cutsceneManager = handler.getCutsceneManager();
                cutsceneManager.setActiveCutscene(cutsceneManager.getCutscene(3));
                handler.setPlayerFrozen(true);
            }
        }
    }
}
