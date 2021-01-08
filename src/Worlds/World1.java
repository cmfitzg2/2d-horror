package Worlds;

import Cutscenes.CutsceneManager;
import Entities.Creatures.Friend1;
import Entities.Creatures.Player;
import Entities.StaticEntities.*;
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
        entityManager.addEntity(new ArtFrameSmall(handler, 72, 8, 48, 48, "Solace", null, Assets.solaceInventory));
        entityManager.addEntity(new ArtFrameSmall(handler, 136, 8, 48, 48, "Prophet", null, Assets.prophetInventory));
        entityManager.addEntity(new Hole(handler, 420, 420, 64, 64, "Hole 1", handler.getWorldManager().getWorld(3), 100, 100));
        entityManager.addEntity(new Friend1(handler, 450, 100, "Friend1"));
        entityManager.addEntity(new Bed(handler, 100, 100, Assets.bedOne.getWidth() * 2, Assets.bedOne.getHeight() * 2, null));
        entityManager.addEntity(new Chair1(handler, 300, 300, Assets.chairOneRight.getWidth() * 2, Assets.chairOneRight.getHeight() * 2, "chair1", Assets.chairOneRight));
        entityManager.addEntity(new WindowLight(handler, 800, 300, Assets.windowLight.getWidth() * 2, Assets.windowLight.getHeight() * 2, "windowlight1"));
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
