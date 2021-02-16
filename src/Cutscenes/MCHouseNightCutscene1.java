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
            "this sucks man",
            messageTwo = "aaaaa";
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
                }
            } else if (textbox2) {
                if (!textboxHandler2.isFinished()) {
                    textboxHandler2.tick();
                } else {
                    exit();
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
            if (!textboxHandler2.isFinished()) {
                textboxHandler2.render(g);
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
