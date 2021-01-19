package Worlds;

import Cutscenes.CutsceneManager;
import Entities.Creatures.Friend1;
import Entities.Creatures.Player;
import Entities.StaticEntities.*;
import Tiles.Tile;
import Variables.Handler;
import Graphics.Assets;

import java.awt.*;

public class World1 extends World {

    private Rectangle loadzoneSouth;
    public World1(Handler handler, String path, int id) {
        super(handler, path, id, new Player(handler, 700, 428, "Player"));
    }

    @Override
    public void checkLoadZones() {
        loadzoneSouth = new Rectangle(894 - (int) handler.getGameCamera().getxOffset(), 795 - (int) handler.getGameCamera().getyOffset(), 132, 40);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneSouth)) {
            transitionFrom(handler.getWorldManager().getWorld(2), 2748, 3128);
        }
    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new Friend1(handler, 900, 388, "Friend1"));
        entityManager.addEntity(new Bed(handler, 700, 428, Assets.bedOne.getWidth() * 2, Assets.bedOne.getHeight() * 2, null));
        entityManager.addEntity(new TableLamp(handler, 800, 388, Assets.tableLampOff.getWidth() * 2, Assets.tableLampOff.getHeight() * 2, null));
        entityManager.addEntity(new WindowLight(handler, 930, 320, Assets.windowLight.getWidth() * 2, Assets.windowLight.getHeight() * 2, "windowlight1"));
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

    @Override
    public void render(Graphics g) {
        renderTiles(g);
        entityManager.render(g);
        if (null != loadzoneSouth) {
            g.drawRect(loadzoneSouth.x, loadzoneSouth.y, loadzoneSouth.width, loadzoneSouth.height);
        }
        firstRender = true;
    }
}
