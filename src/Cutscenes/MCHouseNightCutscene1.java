package Cutscenes;

import Graphics.Assets;
import Input.KeyManager;
import Textboxes.TextboxHandler;
import Utils.GeneralUtils;
import Variables.Handler;
import Worlds.WorldManager;

import java.awt.*;

public class MCHouseNightCutscene1 implements Cutscene {
    private Handler handler;
    private TextboxHandler textboxHandler1, textboxHandler2;
    private KeyManager keyManager;
    private boolean textbox1 = true, textbox2 = false;
    private final String messageOne = "... \r " +
            "this sucks man";
    private final String messageTwo = "... \r " +
            "sheeeeyit.";

    public MCHouseNightCutscene1(Handler handler) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
        textboxHandler1 = new TextboxHandler(handler, Assets.serif, messageOne, null, 3, Color.WHITE, null, Assets.textboxPlayerThinking, null, 50, true, false);
        textboxHandler2 = new TextboxHandler(handler, Assets.serif, messageTwo, null, 3, Color.WHITE, null, Assets.textboxPlayerThinking, null, 50, true, true);
    }

    @Override
    public void tick() {
        if (handler.getActiveWorld().getId() != WorldManager.MC_HOUSE_1_ID) {
            return;
        }
        if (!handler.isPlayerFrozen()) {
            handler.setPlayerFrozen(true);
        }
        if (textbox1) {
            if (!textboxHandler1.isFinished()) {
                textboxHandler1.tick();
            } else {
                GeneralUtils.levelFadeIn(handler, 255);
                textbox2 = true;
                textbox1 = false;
            }
        } else if (textbox2) {
            if (!textboxHandler2.isFinished()) {
                textboxHandler2.tick();
            } else {
                handler.setPlayerFrozen(false);
                handler.getCutsceneManager().setActiveCutscene(null);
                handler.getFlags().setCutsceneActive(false);
                handler.getFlags().setMcHouseNightCutscene1(false);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (handler.getActiveWorld().getId() != WorldManager.MC_HOUSE_1_ID) {
            return;
        }
        if (textbox1) {
            handler.getScreenOverlay().overlayScreen(g, Color.black);
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

    }
}
