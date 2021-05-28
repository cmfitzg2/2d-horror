package Worlds;

import Entities.Creatures.*;
import Entities.StaticEntities.Chalkboard;
import Entities.StaticEntities.Door;
import Entities.StaticEntities.StudentDesk;
import Entities.StaticEntities.TeacherDesk;
import Graphics.Assets;
import Tiles.Tile;
import Variables.GeneralConstants;
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
        entityManager.addEntity(new Door(handler, 23 * Tile.TILEWIDTH, 2.5f * Tile.TILEHEIGHT, 64, 96, "door1-classroom1",
                handler.getWorldManager().getWorld(WorldManager.SCHOOL_1_ID), 6 * Tile.TILEWIDTH, (int) (10 * Tile.TILEHEIGHT),
                Door.PLAIN_WOOD, false, GeneralConstants.levelTransitionFrames, false));
        if (handler.getFlags().isClassroomCutscene1()) {
            entityManager.addEntity(new Chalkboard(handler, 13.0f * Tile.TILEWIDTH, 42 + Tile.TILEHEIGHT, Assets.chalkboard[0].getWidth(), Assets.chalkboard[0].getHeight(), "chalkboard-classroom1", Chalkboard.STYLE_INITIAL));
        } else {
            entityManager.addEntity(new Chalkboard(handler, 13.0f * Tile.TILEWIDTH, 42 + Tile.TILEHEIGHT, Assets.chalkboard[1].getWidth(), Assets.chalkboard[1].getHeight(), "chalkboard-classroom1", Chalkboard.STYLE_DRAWN));
        }
        entityManager.addEntity(new TeacherDesk(handler, 570, 313, Assets.teacherDesk.getWidth() * 2, Assets.teacherDesk.getHeight() * 2, "teacherdesk-classroom1"));
        entityManager.addEntity(new StudentDesk(handler, 13.0f * Tile.TILEWIDTH - 147 - Assets.emptyDesk.getWidth(), 440, Assets.emptyDesk.getWidth() * 2, Assets.emptyDesk.getHeight() * 2, "bargainingdesk-classroom1", StudentDesk.BARGAINING, handler.getFlags().isClassroomCutscene1()));
        entityManager.addEntity(new StudentDesk(handler, 13.0f * Tile.TILEWIDTH - 147 - Assets.emptyDesk.getWidth(), 580, Assets.emptyDesk.getWidth() * 2, Assets.emptyDesk.getHeight() * 2, "depressiondesk-classroom1", StudentDesk.DEPRESSION, handler.getFlags().isClassroomCutscene1()));
        entityManager.addEntity(new StudentDesk(handler, 13.0f * Tile.TILEWIDTH + Assets.chalkboard[0].getWidth() / 2.0f - Assets.emptyDesk.getWidth(), 440, Assets.emptyDesk.getWidth() * 2, Assets.emptyDesk.getHeight() * 2, "angerdesk-classroom1", StudentDesk.ANGER, handler.getFlags().isClassroomCutscene1()));
        entityManager.addEntity(new StudentDesk(handler, 13.0f * Tile.TILEWIDTH + Assets.chalkboard[0].getWidth() / 2.0f - Assets.emptyDesk.getWidth(), 580, Assets.emptyDesk.getWidth() * 2, Assets.emptyDesk.getHeight() * 2, "acceptancedesk-classroom1", StudentDesk.ACCEPTANCE, handler.getFlags().isClassroomCutscene1()));
        entityManager.addEntity(new StudentDesk(handler, 13.0f * Tile.TILEWIDTH + 147 + Assets.chalkboard[0].getWidth() - Assets.emptyDesk.getWidth(), 440, Assets.emptyDesk.getWidth() * 2, Assets.emptyDesk.getHeight() * 2, "denialdesk-classroom1", StudentDesk.DENIAL, handler.getFlags().isClassroomCutscene1()));
        entityManager.addEntity(new StudentDesk(handler, 13.0f * Tile.TILEWIDTH + 147 + Assets.chalkboard[0].getWidth() - Assets.emptyDesk.getWidth(), 580, Assets.emptyDesk.getWidth() * 2, Assets.emptyDesk.getHeight() * 2, "playerdesk-classroom1", StudentDesk.PLAYER, false));
    }

    @Override
    protected void load() {
        firstRender = true;
    }
}
