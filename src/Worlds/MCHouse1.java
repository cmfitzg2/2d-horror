package Worlds;

import Cutscenes.Cutscene;
import Cutscenes.CutsceneManager;
import Entities.Creatures.Player;
import Entities.StaticEntities.*;
import Tiles.Tile;
import Variables.Flags;
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
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.MC_HOUSE_2_ID), 37 * Tile.TILEWIDTH, 15 * Tile.TILEHEIGHT - handler.getPlayer().getPlayerRec().height / 2.0f);
        }
    }

    @Override
    protected void addEntities() {
        entityManager.addEntity(new Bed(handler, 17.7f * Tile.TILEWIDTH, 6.75f * Tile.TILEHEIGHT, Assets.bedOne.getWidth() * 2, Assets.bedOne.getHeight() * 2, "bed1-mchouse1"));
        entityManager.addEntity(new TableLamp(handler, 16.7f * Tile.TILEWIDTH, 6 * Tile.TILEHEIGHT, Assets.tableLampOff.getWidth() * 2, Assets.tableLampOff.getHeight() * 2, null));
        if (!handler.getFlags().isClassroomCutscene1()) {
            entityManager.addEntity(new WindowOutside(handler, 930, 320, Assets.windowLight.getWidth() * 2, Assets.windowLight.getHeight() * 2, "windowoutside1-mchouse1", WindowOutside.DARK));
        } else {
            entityManager.addEntity(new WindowOutside(handler, 930, 320, Assets.windowLight.getWidth() * 2, Assets.windowLight.getHeight() * 2, "windowoutside1-mchouse1", WindowOutside.LIGHT));
        }
    }

    @Override
    protected void load() {
        if (handler.getFlags().isPrologue() || (handler.getFlags().isMCHouseNightCutscene1() && !handler.getFlags().isClassroomCutscene1())) {
            handler.getFlags().setCutsceneActive(true);
            CutsceneManager cutsceneManager = handler.getCutsceneManager();
            if (handler.getFlags().isPrologue()) {
                fadeIn = false;
                cutsceneManager.setActiveCutscene(cutsceneManager.getCutscene(Cutscene.PROLOGUE));
            } else {
                cutsceneManager.setActiveCutscene(cutsceneManager.getCutscene(Cutscene.MC_HOUSE_NIGHT_CUTSCENE_1));
                handler.getFlags().setTimeOfDay(Flags.TIME_OF_DAY_DARK);
                handler.getPlayer().setAmbientLight(Flags.TIME_OF_DAY_DARK);
            }
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
