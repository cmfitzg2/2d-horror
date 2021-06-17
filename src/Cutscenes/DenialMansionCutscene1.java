package Cutscenes;

import Entities.Creatures.Acceptance;
import Entities.Creatures.Denial;
import Entities.Creatures.Player;
import Entities.EntityManager;
import Graphics.Assets;
import Input.KeyManager;
import Textboxes.TextboxHandler;
import Tiles.Tile;
import Variables.GeneralConstants;
import Variables.Handler;
import Worlds.WorldManager;

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

    public DenialMansionCutscene1(Handler handler) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
    }

    @Override
    public void tick() {
        if (firstTime) {
            EntityManager entityManagerL2R4 = handler.getWorldManager().getWorld(WorldManager.MANSION_L2_ROOM_4_ID).getEntityManager();
            entityManagerL2R4.removeEntity(entityManagerL2R4.getEntityByUid("denial-mansionL2Room4"));
            player = handler.getPlayer();
            firstTime = false;
        }
        if (textbox1 && !textboxHandler1.isFinished()) {
            textboxHandler1.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        if (textbox1) {
            if (!textboxHandler1.isFinished()) {
                textboxHandler1.render(g);
            } else {
                textbox2 = true;
                textbox1 = false;
            }
        }
    }

    @Override
    public void exit() {
        handler.setPlayerFrozen(false);
        handler.getCutsceneManager().setActiveCutscene(null);
        handler.getFlags().setCutsceneActive(false);
        handler.getFlags().setDenialMansionCutscene1(false);
    }
}
