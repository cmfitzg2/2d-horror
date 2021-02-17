package Cutscenes;

import Graphics.Assets;
import Input.KeyManager;
import Textboxes.TextboxHandler;
import Variables.GeneralConstants;
import Variables.Handler;
import Worlds.WorldManager;

import java.awt.*;

public class MCHouseNightCutscene2 implements Cutscene {
    private Handler handler;
    private TextboxHandler textboxHandler1;
    private KeyManager keyManager;
    private boolean messageDisplayed = false, showTextbox = false;
    private final String messageOne = "That's weird. No one is out there. I guess I'm hearing things. \r " +
            "I'm sure I heard three knocks though. There's something distinctly deliberate about that.";
    public MCHouseNightCutscene2(Handler handler) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
        textboxHandler1 = new TextboxHandler(handler, Assets.playerThinkingFont, messageOne, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayerThinking, null, 50, true, false);
    }

    @Override
    public void tick() {
        if (handler.getActiveWorld().getId() != WorldManager.MC_HOUSE_2_ID) {
            return;
        }
        if (!messageDisplayed) {
            if (handler.getKeyManager().z) {
                if (!handler.getKeyManager().isStillHoldingZ()) {
                    handler.getKeyManager().setStillHoldingZ(true);
                    messageDisplayed = true;
                    showTextbox = true;
                }
            }
        } else {
            if (textboxHandler1.isFinished()) {
                //checking the door again after the initial cutscene
                if (handler.getKeyManager().z) {
                    if (!handler.getKeyManager().isStillHoldingZ()) {
                        exit();
                    }
                }
            }
        }
        if (showTextbox) {
            if (!textboxHandler1.isFinished()) {
                textboxHandler1.tick();
            } else {
                exit();
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (handler.getActiveWorld().getId() != WorldManager.MC_HOUSE_2_ID) {
            return;
        }
        g.drawImage(Assets.frontDoor, 0, 0, handler.getWidth(), handler.getHeight(), null);
        if (showTextbox) {
            if (!textboxHandler1.isFinished()) {
                textboxHandler1.render(g);
            }
        }
    }

    @Override
    public void exit() {
        showTextbox = false;
        handler.setPlayerFrozen(false);
        handler.getCutsceneManager().setActiveCutscene(null);
        handler.getFlags().setCutsceneActive(false);
    }

    public boolean isMessageDisplayed() {
        return messageDisplayed;
    }
}
