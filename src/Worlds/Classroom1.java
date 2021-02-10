package Worlds;

import Entities.Creatures.*;
import Entities.StaticEntities.Chalkboard;
import Entities.StaticEntities.Door;
import Entities.StaticEntities.StudentDesk;
import Entities.StaticEntities.TeacherDesk;
import Graphics.Assets;
import Tiles.Tile;
import Variables.Handler;

public class Classroom1 extends World {

    public Classroom1(Handler handler, int id, Player player) {
        super(handler, "res/worlds/classroom1.txt", id, player);
    }

    @Override
    public void checkLoadZones() {

    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new Door(handler, 23 * Tile.TILEWIDTH, 2.5f * Tile.TILEHEIGHT, 64, 96, "Door", handler.getWorldManager().getWorld(WorldManager.SCHOOL_1_ID), 383 - (int) handler.getGameCamera().getxOffset(), 797 - (int) handler.getGameCamera().getyOffset(), Door.PLAIN_WOOD));
        entityManager.addEntity(new Chalkboard(handler, 13.0f * Tile.TILEWIDTH, 42 + Tile.TILEHEIGHT, Assets.chalkboard[0].getWidth(), Assets.chalkboard[1].getHeight(), "chalkboard1", 0));
        entityManager.addEntity(new TeacherDesk(handler, 570, 313, Assets.teacherDesk.getWidth() * 2, Assets.teacherDesk.getHeight() * 2, "TeacherDesk-1"));
        entityManager.addEntity(new StudentDesk(handler, 13.0f * Tile.TILEWIDTH - 147 - Assets.emptyDesk.getWidth(), 440, Assets.emptyDesk.getWidth() * 2, Assets.emptyDesk.getHeight() * 2, "StudentDesk-1", StudentDesk.BARGAINING, handler.getFlags().isClassroomCutscene1()));
        entityManager.addEntity(new StudentDesk(handler, 13.0f * Tile.TILEWIDTH - 147 - Assets.emptyDesk.getWidth(), 580, Assets.emptyDesk.getWidth() * 2, Assets.emptyDesk.getHeight() * 2, "StudentDesk-2", StudentDesk.DEPRESSION, handler.getFlags().isClassroomCutscene1()));
        entityManager.addEntity(new StudentDesk(handler, 13.0f * Tile.TILEWIDTH + Assets.chalkboard[0].getWidth() / 2.0f - Assets.emptyDesk.getWidth(), 440, Assets.emptyDesk.getWidth() * 2, Assets.emptyDesk.getHeight() * 2, "StudentDesk-3", StudentDesk.ANGER, handler.getFlags().isClassroomCutscene1()));
        entityManager.addEntity(new StudentDesk(handler, 13.0f * Tile.TILEWIDTH + Assets.chalkboard[0].getWidth() / 2.0f - Assets.emptyDesk.getWidth(), 580, Assets.emptyDesk.getWidth() * 2, Assets.emptyDesk.getHeight() * 2, "StudentDesk-4", StudentDesk.ACCEPTANCE, handler.getFlags().isClassroomCutscene1()));
        entityManager.addEntity(new StudentDesk(handler, 13.0f * Tile.TILEWIDTH + 147 + Assets.chalkboard[0].getWidth() - Assets.emptyDesk.getWidth(), 440, Assets.emptyDesk.getWidth() * 2, Assets.emptyDesk.getHeight() * 2, "StudentDesk-5", StudentDesk.DENIAL, handler.getFlags().isClassroomCutscene1()));
        entityManager.addEntity(new StudentDesk(handler, 13.0f * Tile.TILEWIDTH + 147 + Assets.chalkboard[0].getWidth() - Assets.emptyDesk.getWidth(), 580, Assets.emptyDesk.getWidth() * 2, Assets.emptyDesk.getHeight() * 2, "StudentDesk-6", StudentDesk.PLAYER, false));
    }

    @Override
    protected void load() {
        handler.getFlags().setVisionLimited(false);
        firstRender = true;
    }
}
