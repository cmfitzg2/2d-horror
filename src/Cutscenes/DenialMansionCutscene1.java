package Cutscenes;

import Entities.Creatures.Denial;
import Entities.Creatures.Player;
import Entities.EntityManager;
import Entities.StaticEntities.Dresser;
import Input.KeyManager;
import Textboxes.TextboxHandler;
import Tiles.Tile;
import Variables.Handler;
import Worlds.WorldManager;
import Graphics.Assets;

import java.awt.*;

public class DenialMansionCutscene1 implements Cutscene {

    Player player;
    private boolean firstTime = true;
    private Handler handler;
    private TextboxHandler textboxHandler1;
    private KeyManager keyManager;
    private int messageNum = 1;
    private boolean textbox1 = true, textbox2;
    private Denial denial;
    private final String messageOne = "????????",
            messageTwo = "????????????????????????? \r " +
                    "????????";
    private Dresser movingDresser;
    private float initialX, stepSize;
    private int oscillationIndex = 0;

    public DenialMansionCutscene1(Handler handler) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
    }

    @Override
    public void tick() {
        if (firstTime) {
            EntityManager entityManagerL2R4 = handler.getWorldManager().getWorld(WorldManager.MANSION_L2_ROOM_4_ID).getEntityManager();
            entityManagerL2R4.removeEntity(entityManagerL2R4.getEntityByUid("denial-mansionL2Room4"));
            Dresser dresserL2R4 = (Dresser) entityManagerL2R4.getEntityByUid("mansionL2Room4-dresser2");
            dresserL2R4.setX(dresserL2R4.getX() + Tile.TILEWIDTH * 1.5f);
            EntityManager entityManagerL2R3 = handler.getWorldManager().getWorld(WorldManager.MANSION_L2_ROOM_3_ID).getEntityManager();
            movingDresser = (Dresser) entityManagerL2R3.getEntityByUid("mansionL2Room3-dresser2");
            player = handler.getPlayer();
            Assets.woodDrag.setFramePosition(0);
            Assets.woodDrag.start();
            stepSize = Tile.TILEWIDTH * 1.5f / (Assets.woodDrag.getMicrosecondLength() * handler.getGame().getFps() / 1000000f);
            initialX = movingDresser.getX();
            firstTime = false;
        }
        if (movingDresser.getX() > initialX - Tile.TILEWIDTH * 1.5f) {
            moveDresser();
        } else {
            exit();
        }
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void exit() {
        handler.setPlayerFrozen(false);
        handler.getCutsceneManager().setActiveCutscene(null);
        handler.getFlags().setCutsceneActive(false);
        handler.getFlags().setDenialMansionCutscene1(false);
    }

    private void moveDresser() {
        movingDresser.setX(movingDresser.getX() - stepSize);
        if (oscillationIndex < 3) {
            movingDresser.setY(movingDresser.getY() - 1);
        } else {
            movingDresser.setY(movingDresser.getY() + 1);
        }
        oscillationIndex++;
        if (oscillationIndex > 5) {
            oscillationIndex = 0;
        }
    }
}
