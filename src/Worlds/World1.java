package Worlds;

import Cutscenes.CutsceneManager;
import Entities.Creatures.Friend1;
import Entities.Creatures.Player;
import Entities.StaticEntities.*;
import Tiles.Tile;
import Variables.Handler;
import Graphics.Assets;
public class World1 extends World {

    public World1(Handler handler, String path, int id) {
        super(handler, path, id, new Player(handler, 100, 100, "Player"));
    }

    @Override
    public void checkLoadZones() {
/*        if (entityManager.getPlayer().getX() > 1000 && entityManager.getPlayer().getY() > 1000) {
            transitionFrom(handler.getWorldManager().getWorld(2), 100, 100);
        }*/
    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new ArtFrameSmall(handler, 72, 72, 48, 48, "Solace", null, Assets.solaceInventory));
        entityManager.addEntity(new ArtFrameSmall(handler, 136, 72, 48, 48, "Prophet", null, Assets.prophetInventory));
        entityManager.addEntity(new Hole(handler, 100, 500, 64, 64, "Hole 1", handler.getWorldManager().getWorld(3), 100, 1000));
        entityManager.addEntity(new Friend1(handler, 800, 500, "Friend1"));
        entityManager.addEntity(new Bed(handler, 100, 228, Assets.bedOne.getWidth() * 2, Assets.bedOne.getHeight() * 2, null));
        entityManager.addEntity(new TableLamp(handler, 200, 188, Assets.tableLampOff.getWidth() * 2, Assets.tableLampOff.getHeight() * 2, null));
        entityManager.addEntity(new Chair1(handler, 300, 428, Assets.chairOneRight.getWidth() * 2, Assets.chairOneRight.getHeight() * 2, "chair1", "Right"));
        entityManager.addEntity(new Chair1(handler, 500, 420, Assets.chairOneLeft.getWidth() * 2, Assets.chairOneLeft.getHeight() * 2, "chair2", "Left"));
        entityManager.addEntity(new Chair1(handler, 400, 528, Assets.chairOneUp.getWidth() * 2, Assets.chairOneUp.getHeight() * 2, "chair3", "Up"));
        entityManager.addEntity(new Chair1(handler, 400, 328, Assets.chairOneDown.getWidth() * 2, Assets.chairOneDown.getHeight() * 2, "chair4", "Down"));
        entityManager.addEntity(new WindowLight(handler, 800, 72, Assets.windowLight.getWidth() * 2, Assets.windowLight.getHeight() * 2, "windowlight1"));
        entityManager.addEntity(new Dresser(handler, 800, 800, Assets.dressers[0].getWidth() * 2, Assets.dressers[0].getHeight() * 2, "dresser1", 0));
        entityManager.addEntity(new Dresser(handler, 864, 800, Assets.dressers[0].getWidth() * 2, Assets.dressers[0].getHeight() * 2, "dresser2", 1));
        entityManager.addEntity(new Dresser(handler, 928, 800, Assets.dressers[0].getWidth() * 2, Assets.dressers[0].getHeight() * 2, "dresser3", 2));
        entityManager.addEntity(new Dresser(handler, 992, 800, Assets.dressers[0].getWidth() * 2, Assets.dressers[0].getHeight() * 2, "dresser4", 3));
        entityManager.addEntity(new Dresser(handler, 1056, 800, Assets.dressers[0].getWidth() * 2, Assets.dressers[0].getHeight() * 2, "dresser5", 4));
        entityManager.addEntity(new Dresser(handler, 1120, 800, Assets.dressers[0].getWidth() * 2, Assets.dressers[0].getHeight() * 2, "dresser6", 5));
        entityManager.addEntity(new Dresser(handler, 1184, 800, Assets.dressers[0].getWidth() * 2, Assets.dressers[0].getHeight() * 2, "dresser7", 6));
        entityManager.addEntity(new Dresser(handler, 1248, 800, Assets.dressers[0].getWidth() * 2, Assets.dressers[0].getHeight() * 2, "dresser8", 7));
        entityManager.addEntity(new Dresser(handler, 1312, 800, Assets.dressers[0].getWidth() * 2, Assets.dressers[0].getHeight() * 2, "dresser9", 8));
        entityManager.addEntity(new Fireplace(handler, 1000, Tile.TILEHEIGHT * 3 - Assets.firePlace.getHeight() * 2 + 20, Assets.firePlace.getWidth() * 2, Assets.firePlace.getHeight() * 2, "fireplace1"));
    }

    @Override
    protected void load() {
        //handler.getFlags().setVisionLimited(true);
        if (handler.getFlags().isPrologue()) {
            fadeIn = false;
            handler.getFlags().setCutsceneActive(true);
            CutsceneManager cutsceneManager = handler.getCutsceneManager();
            cutsceneManager.setActiveCutscene(cutsceneManager.getCutscene(1));
            handler.setPlayerFrozen(true);
        }
    }
}
