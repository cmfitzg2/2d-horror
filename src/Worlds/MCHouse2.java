package Worlds;

import Cutscenes.Cutscene;
import Cutscenes.CutsceneManager;
import Cutscenes.MCHouseNightCutscene2;
import Entities.Creatures.Acceptance;
import Entities.Creatures.Player;
import Entities.StaticEntities.Fireplace;
import Tiles.Tile;
import Variables.Handler;
import Graphics.Assets;

import java.awt.*;

public class MCHouse2 extends World {

    private boolean stillInOutsideLoadzone = false, lookedOutside = false;
    private Rectangle loadzonePlayerRoom, loadzoneOutside;

    public MCHouse2(Handler handler, int id, Player player) {
        super(handler, "res/worlds/mc-house-2.txt", id, player);
    }

    @Override
    public void checkLoadZones() {
        loadzonePlayerRoom = new Rectangle((int) (Tile.TILEWIDTH * 38.5f) - (int) handler.getGameCamera().getxOffset(),
                15 * Tile.TILEHEIGHT - (int) handler.getGameCamera().getyOffset(), Tile.TILEWIDTH / 2, Tile.TILEHEIGHT);
        if (handler.getPlayer().getPlayerRec().intersects(loadzonePlayerRoom)) {
            transitionFrom(handler.getWorldManager().getWorld(WorldManager.MC_HOUSE_1_ID), Tile.TILEWIDTH * 10,
                    Tile.TILEHEIGHT * 9 - handler.getPlayer().getPlayerRec().height / 2.0f);
        }
        loadzoneOutside = new Rectangle(Tile.TILEWIDTH * 19 - (int) handler.getGameCamera().getxOffset(),
                (int) (Tile.TILEHEIGHT * 17.5) - (int) handler.getGameCamera().getyOffset(), Tile.TILEWIDTH * 2, Tile.TILEHEIGHT / 2);
        if (handler.getPlayer().getPlayerRec().intersects(loadzoneOutside)) {
            //if this flag is set, we're in the part of the story that this should act as a loadzone
            if (handler.getFlags().isMCHouseNightCutscene1()) {
                transitionFrom(handler.getWorldManager().getWorld(WorldManager.OVERWORLD_1_ID), Tile.TILEWIDTH * 43,
                        Tile.TILEHEIGHT * 49);
            } else {
                if (handler.getPlayer().getyMove() > 0) {
                    if (handler.getFlags().isMCHouseNightCutscene2() && !handler.getFlags().isCutsceneActive()) {
                        CutsceneManager cutsceneManager = handler.getCutsceneManager();
                        MCHouseNightCutscene2 mcHouseNightCutscene2 = (MCHouseNightCutscene2) cutsceneManager.getCutscene(Cutscene.MC_HOUSE_NIGHT_CUTSCENE_2);
                        if (mcHouseNightCutscene2.isMessageDisplayed()) {
                            //have already looked outside
                            if (!stillInOutsideLoadzone) {
                                handler.getFlags().setCutsceneActive(true);
                                cutsceneManager.setActiveCutscene(mcHouseNightCutscene2);
                                handler.setPlayerFrozen(true);
                            }
                        } else {
                            handler.getFlags().setCutsceneActive(true);
                            cutsceneManager.setActiveCutscene(mcHouseNightCutscene2);
                            handler.setPlayerFrozen(true);
                            lookedOutside = true;
                        }
                    }
                }
            }
            stillInOutsideLoadzone = true;
        } else {
            stillInOutsideLoadzone = false;
        }
    }

    @Override
    protected void addEntities() {
        if (handler.getFlags().isAcceptanceEncounter1()) {
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
        if (handler.getFlags().isAcceptanceEncounter1() && !handler.getFlags().isCutsceneActive()) {
            if (handler.getPlayer().getX() < Tile.TILEWIDTH * 33) {
                handler.getFlags().setCutsceneActive(true);
                CutsceneManager cutsceneManager = handler.getCutsceneManager();
                cutsceneManager.setActiveCutscene(cutsceneManager.getCutscene(2));
                handler.setPlayerFrozen(true);
            }
        }
        if (lookedOutside) {
            if (null != loadzoneOutside) {
                int xOrigin = loadzoneOutside.x + loadzoneOutside.width / 2;
                int yOrigin = loadzoneOutside.y;
                if ((Math.abs(handler.getPlayer().getX() - handler.getGameCamera().getxOffset() - xOrigin ) > 3 * Tile.TILEWIDTH)
                        || (Math.abs(handler.getPlayer().getY() - handler.getGameCamera().getyOffset() - yOrigin) > 3 * Tile.TILEHEIGHT)) {
                    handler.getFlags().setCutsceneActive(true);
                    CutsceneManager cutsceneManager = handler.getCutsceneManager();
                    cutsceneManager.setActiveCutscene(cutsceneManager.getCutscene(Cutscene.MC_HOUSE_NIGHT_CUTSCENE_3));
                }
            }
        }
    }
}
