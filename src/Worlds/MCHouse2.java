package Worlds;

import Cutscenes.CutsceneManager;
import Entities.Creatures.Friend1;
import Entities.Creatures.Player;
import Tiles.Tile;
import Variables.Handler;

import java.awt.*;

public class MCHouse2 extends World {

    private Rectangle loadzonePlayerRoom;

    public MCHouse2(Handler handler, int id, Player player) {
        super(handler, "res/worlds/mc-house-2.txt", id, player);
    }

    @Override
    public void checkLoadZones() {
        loadzonePlayerRoom = new Rectangle((int) (Tile.TILEWIDTH * 38.5f) - (int) handler.getGameCamera().getxOffset(),
                15 * Tile.TILEHEIGHT - (int) handler.getGameCamera().getyOffset(), Tile.TILEWIDTH / 2, Tile.TILEHEIGHT);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzonePlayerRoom)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.MC_HOUSE_1_ID), Tile.TILEWIDTH * 10,
                    Tile.TILEHEIGHT * 9 - handler.getPlayer().getPlayerRec().height / 2.0f);
        }
    }

    @Override
    protected void addEntities() {
        if (handler.getFlags().isFriendEncounter1()) {
            Friend1 friend1 = new Friend1(handler, 1250, 857, "friend1-mchouse2");
            friend1.setDirection("up");
            entityManager.addEntity(friend1);
        }
    }

    @Override
    protected void load() {
        firstRender = true;
    }

    @Override
    protected void tick() {
        super.tick();
        if (handler.getFlags().isFriendEncounter1() && !handler.getFlags().isCutsceneActive()) {
            if (handler.getPlayer().getX() < Tile.TILEWIDTH * 33) {
                handler.getFlags().setCutsceneActive(true);
                CutsceneManager cutsceneManager = handler.getCutsceneManager();
                cutsceneManager.setActiveCutscene(cutsceneManager.getCutscene(2));
                handler.setPlayerFrozen(true);
            }
        }
    }
}
