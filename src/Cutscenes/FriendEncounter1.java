package Cutscenes;

import Graphics.Assets;
import Input.KeyManager;
import Textboxes.TextboxHandler;
import Utils.GeneralUtils;
import Variables.Handler;

import java.awt.*;

public class FriendEncounter1 implements Cutscene {
    private boolean showTextbox1 = false;
    private Handler handler;
    private TextboxHandler textboxHandler1, textboxHandler2;
    private KeyManager keyManager;
    private int messageNum = 1;
    private boolean textbox1 = true, textbox2 = false;
    private final String messageOne = "Hey MC!";
    private final String messageTwo = "Sounds like Acceptance is already here. \r " +
            " I don't know why, but today I really feel relieved to hear his voice again.";

    public FriendEncounter1(Handler handler) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
        textboxHandler1 = new TextboxHandler(handler, Assets.sans, messageOne, null, 3, Color.WHITE, null, null, 50, true, false);
        textboxHandler2 = new TextboxHandler(handler, Assets.serif, messageTwo, null, 3, Color.WHITE, null, null, 50, true, true);
    }

    @Override
    public void tick() {
        if (textbox1 && !showTextbox1) {
            if ((handler.getKeyManager().up || handler.getKeyManager().down || handler.getKeyManager().left
                    || handler.getKeyManager().right || handler.getKeyManager().z)) {
                showTextbox1 = true;
                handler.setPlayerFrozen(true);
            }
        }
        if (textbox1 && showTextbox1) {
            if (!textboxHandler1.isFinished()) {
                textboxHandler1.tick();
            }
        }
        if (textbox2) {
            if (!textboxHandler2.isFinished()) {
                textboxHandler2.tick();
            } else {
                handler.setPlayerFrozen(false);
                handler.getCutsceneManager().setActiveCutscene(null);
                handler.getFlags().setCutsceneActive(false);
                handler.getFlags().setFriendEncounter1(false);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (textbox1 && showTextbox1) {
            if (!textboxHandler1.isFinished()) {
                textboxHandler1.render(g);
            } else {
                textbox2 = true;
                textbox1 = false;
                showTextbox1 = false;
            }
        }
        if (textbox2) {
            if (!textboxHandler2.isFinished()) {
                textboxHandler2.render(g);
            }
        }
    }
}
