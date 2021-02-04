package Worlds;

import Cutscenes.CutsceneManager;
import Entities.Creatures.Acceptance;
import Entities.Creatures.Player;
import Entities.StaticEntities.Fireplace;
import Tiles.Tile;
import Variables.Handler;
import Graphics.Assets;

import java.awt.*;

public class MCHouse2 extends World {

    public MCHouse2(Handler handler, int id, Player player) {
        super(handler, "res/worlds/mc-house-2.txt", id, player);
    }

    @Override
    public void checkLoadZones() {
        Rectangle loadzonePlayerRoom = new Rectangle((int) (Tile.TILEWIDTH * 38.5f) - (int) handler.getGameCamera().getxOffset(),
                15 * Tile.TILEHEIGHT - (int) handler.getGameCamera().getyOffset(), Tile.TILEWIDTH / 2, Tile.TILEHEIGHT);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzonePlayerRoom)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.MC_HOUSE_1_ID), Tile.TILEWIDTH * 10,
                    Tile.TILEHEIGHT * 9 - handler.getPlayer().getPlayerRec().height / 2.0f);
        }
        Rectangle loadzoneOutside = new Rectangle(Tile.TILEWIDTH * 19 - (int) handler.getGameCamera().getxOffset(),
                (int) (Tile.TILEHEIGHT * 17.5) - (int) handler.getGameCamera().getyOffset(), Tile.TILEWIDTH * 2, Tile.TILEHEIGHT / 2);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneOutside)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.OVERWORLD_1_ID), Tile.TILEWIDTH * 43,
                    Tile.TILEHEIGHT * 49);
        }
    }

    @Override
    protected void addEntities() {
        if (handler.getFlags().isFriendEncounter1()) {
            Acceptance acceptance = new Acceptance(handler, 1250, 857, "acceptance-mchouse2");
            acceptance.setDirection("up");
            entityManager.addEntity(acceptance);
        }
        entityManager.addEntity(new Fireplace(handler, 20 * Tile.TILEWIDTH - Assets.firePlace.getWidth(),
                Tile.TILEHEIGHT * 6 + 10 - Assets.firePlace.getHeight(), Assets.firePlace.getWidth() * 2, Assets.firePlace.getHeight() * 2, "fireplace-mchouse2"));
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
