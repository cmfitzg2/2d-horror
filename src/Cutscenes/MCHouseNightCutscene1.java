package Cutscenes;

import Graphics.Assets;
import Input.KeyManager;
import Textboxes.TextboxHandler;
import Variables.GeneralConstants;
import Variables.Handler;
import Worlds.WorldManager;

import java.awt.*;

public class MCHouseNightCutscene1 implements Cutscene {
    private Handler handler;
    private TextboxHandler textboxHandler1, textboxHandler2;
    private KeyManager keyManager;
    private boolean showingText = false, textbox1 = false, textbox2 = false;
    private boolean firstTime = true;
    private final String messageOne = "... \r " +
            "(Sigh) \r " +
            "It's so easy to fall asleep when I'm not trying to. \r " +
            "But even now, as tired as I am, I can't fall asleep on command. \n Because my body \"knows\" I'm trying to. \r " +
            "Maybe I should've stayed at school after all.",
            messageTwo = "Oh? Is that Acceptance already? I figured they wouldn't be here for awhile.";
    public MCHouseNightCutscene1(Handler handler) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
        textboxHandler1 = new TextboxHandler(handler, Assets.playerThinkingFont, messageOne, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayerThinking, null, 50, true, false);
        textboxHandler2 = new TextboxHandler(handler, Assets.playerThinkingFont, messageTwo, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayerThinking, null, 50, true, false);
    }

    @Override
    public void tick() {
        if (handler.getActiveWorld().getId() != WorldManager.MC_HOUSE_1_ID) {
            return;
        }
        if (firstTime) {
            handler.getGame().fadeIn(255);
            handler.getActiveWorld().transitioningTo = false;
            firstTime = false;
        }
        if (!showingText) {
            if (handler.getGame().isFinishedFadingIn()) {
                textbox1 = true;
                showingText = true;
                handler.getGame().setFadeIn(false, true);
            }
        } else {
            if (textbox1) {
                if (!textboxHandler1.isFinished()) {
                    textboxHandler1.tick();
                } else {
                    textbox2 = true;
                    textbox1 = false;
                    Assets.triKnock1.start();
                }
            } else if (textbox2) {
                if (!Assets.triKnock1.isActive()) {
                    if (!textboxHandler2.isFinished()) {
                        textboxHandler2.tick();
                    } else {
                        exit();
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (handler.getActiveWorld().getId() != WorldManager.MC_HOUSE_1_ID) {
            return;
        }
        if (textbox1) {
            if (!textboxHandler1.isFinished()) {
                textboxHandler1.render(g);
            }
        } else if (textbox2) {
            if (!Assets.triKnock1.isActive()) {
                if (!textboxHandler2.isFinished()) {
                    textboxHandler2.render(g);
                }
            }
        }
    }

    @Override
    public void exit() {
        handler.setPlayerFrozen(false);
        handler.getCutsceneManager().setActiveCutscene(null);
        handler.getFlags().setCutsceneActive(false);
        handler.getFlags().setMcHouseNightCutscene1(false);
    }
}
