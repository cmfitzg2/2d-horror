package Cutscenes;

import Graphics.Assets;
import Input.KeyManager;
import Items.Inventory;
import Items.Item;
import Items.Lighter;
import Textboxes.TextboxHandler;
import Variables.Flags;
import Variables.GeneralConstants;
import Variables.Handler;
import Worlds.WorldManager;

import java.awt.*;

public class MCHouseNightCutscene4 implements Cutscene {
    private Handler handler;
    private TextboxHandler textboxHandler1, textboxHandler2;
    private KeyManager keyManager;
    private boolean showingText = false, textbox1 = true, textbox2 = false;
    private boolean firstTime = true;
    private final String messageOne = "?! \r " +
            "That came from my room!";
    public MCHouseNightCutscene4(Handler handler) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
        textboxHandler1 = new TextboxHandler(handler, Assets.playerThinkingFont, messageOne, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayerThinking, null, 50, true, false);
    }

    @Override
    public void tick() {
        if (handler.getActiveWorld().getId() != WorldManager.MC_HOUSE_2_ID) {
            return;
        }
        if (firstTime) {
            handler.setPlayerFrozen(true);
            Assets.windowKnock.start();
            firstTime = false;
        }
        if (!Assets.windowKnock.isActive()) {
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
        if (!Assets.windowKnock.isActive()) {
            if (!textboxHandler1.isFinished()) {
                textboxHandler1.render(g);
            }
        }
    }

    @Override
    public void exit() {
        handler.setPlayerFrozen(false);
        handler.getCutsceneManager().setActiveCutscene(null);
        handler.getFlags().setCutsceneActive(false);
        handler.getFlags().setMcHouseNightCutscene4(false);
    }
}
