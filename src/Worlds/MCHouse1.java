package Worlds;

import Cutscenes.CutsceneManager;
import Entities.Creatures.Player;
import Entities.StaticEntities.*;
import Tiles.Tile;
import Variables.Handler;
import Graphics.Assets;

import java.awt.*;

public class MCHouse1 extends World {

    public MCHouse1(Handler handler, int id, Player player) {
        super(handler, "res/worlds/mc-house-1.txt", id, player);
    }

    @Override
    public void checkLoadZones() {
        Rectangle loadzoneMainHouse = new Rectangle(Tile.TILEWIDTH * 9 - (int) handler.getGameCamera().getxOffset(),
                Tile.TILEHEIGHT * 9 - (int) handler.getGameCamera().getyOffset(), Tile.TILEWIDTH / 2, Tile.TILEHEIGHT);
        if (entityManager.getPlayer().getPlayerRec().intersects(loadzoneMainHouse)) {
            transitionFrom(handler.getWorldManager().getWorld(2), 37 * Tile.TILEWIDTH, 15 * Tile.TILEHEIGHT - handler.getPlayer().getPlayerRec().height / 2.0f);
        }
    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new Bed(handler, 17.7f * Tile.TILEWIDTH, 6.75f * Tile.TILEHEIGHT, Assets.bedOne.getWidth() * 2, Assets.bedOne.getHeight() * 2, null));
        entityManager.addEntity(new TableLamp(handler, 16.7f * Tile.TILEWIDTH, 6 * Tile.TILEHEIGHT, Assets.tableLampOff.getWidth() * 2, Assets.tableLampOff.getHeight() * 2, null));
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
        firstRender = true;
    }
}
