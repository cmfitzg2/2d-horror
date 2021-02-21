package Cutscenes;

import Entities.StaticEntities.WindowOutside;
import Graphics.Assets;
import Input.KeyManager;
import Textboxes.TextboxHandler;
import Variables.GeneralConstants;
import Variables.Handler;
import Worlds.WorldManager;

import java.awt.*;

public class MCHouseNightCutscene5 implements Cutscene {
    private Handler handler;
    private TextboxHandler textboxHandler1, textboxHandler2;
    private KeyManager keyManager;
    private boolean showTextbox1 = false, textbox1 = true, textbox2 = false, soundPlayed = false;
    private final String message1 = "...?",
            message2 = "No one is out there? I was sure I heard someone knocking on";
    public MCHouseNightCutscene5(Handler handler) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
        textboxHandler1 = new TextboxHandler(handler, Assets.playerThinkingFont, message1, null, GeneralConstants.slowTextSpeed, Color.WHITE, null, Assets.textboxPlayerThinking, null, 50, false, false);
        textboxHandler2 = new TextboxHandler(handler, Assets.playerThinkingFont, message2, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayerThinking, null, 50, false, false);
    }

    @Override
    public void tick() {
        if (handler.getActiveWorld().getId() != WorldManager.MC_HOUSE_1_ID) {
            return;
        }
        if (textbox1) {
            if (showTextbox1) {
                if (!textboxHandler1.isFinished()) {
                    textboxHandler1.tick();
                }
            } else {
                if (handler.getKeyManager().z) {
                    if (!handler.getKeyManager().isStillHoldingZ()) {
                        showTextbox1 = true;
                    }
                }
            }
        }
        if (textbox2) {
            if (!textboxHandler2.isTextFinished()) {
                textboxHandler2.tick();
            } else if (!soundPlayed) {
                Assets.windowHit.play();
                soundPlayed = true;
                ((WindowOutside) handler.getActiveWorld().getEntityManager().getEntityByUid("windowoutside1-mchouse1"))
                        .setBackground(Assets.windowOutsideNightHand[0]);
            } else {
                exit();
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (handler.getActiveWorld().getId() != WorldManager.MC_HOUSE_1_ID) {
            return;
        }
        if (textbox1 && showTextbox1) {
            if (!textboxHandler1.isFinished()) {
                textboxHandler1.render(g);
            } else {
                showTextbox1 = false;
                textbox1 = false;
                textbox2 = true;
            }
        }
        if (textbox2) {
            if (!textboxHandler2.isTextFinished()) {
                textboxHandler2.render(g);
            }
        }
    }

    @Override
    public void exit() {
        handler.setPlayerFrozen(false);
        handler.getCutsceneManager().setActiveCutscene(null);
        handler.getFlags().setCutsceneActive(false);
        handler.getFlags().setMcHouseNightCutscene5(false);
    }
}
