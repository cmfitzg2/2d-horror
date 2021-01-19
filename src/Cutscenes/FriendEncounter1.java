package Cutscenes;

import Entities.Creatures.Friend1;
import Entities.Entity;
import Graphics.Assets;
import Input.KeyManager;
import Textboxes.TextboxHandler;
import Utils.GeneralUtils;
import Variables.Handler;

import java.awt.*;

public class FriendEncounter1 implements Cutscene {
    private boolean firstTime = true;
    private boolean showTextbox1 = false, showTextbox3 = false;
    private Handler handler;
    private TextboxHandler textboxHandler1, textboxHandler2, textboxHandler3;
    private KeyManager keyManager;
    private int messageNum = 1;
    private boolean textbox1 = true, textbox2 = false, textbox3 = false;
    private Friend1 friend1;
    private final String messageOne = "Hey MC!";
    private final String messageTwo = "Sounds like Acceptance is already here. \r " +
            "I don't know why, but today I really feel relieved to hear his voice again.";
    private final String messageThree = "Hey, it's good to see you again.";

    public FriendEncounter1(Handler handler) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
        textboxHandler1 = new TextboxHandler(handler, Assets.sans, messageOne, null, 3, Color.WHITE, null, null, 50, true, false);
        textboxHandler2 = new TextboxHandler(handler, Assets.serif, messageTwo, null, 3, Color.WHITE, null, null, 50, true, false);
    }

    @Override
    public void tick() {
        if (firstTime) {
            for (Entity e : handler.getActiveWorld().getEntityManager().getEntities()) {
                if (e instanceof Friend1) {
                    friend1 = (Friend1) e;
                }
            }
        }
        if (textbox1 && !showTextbox1) {
            if ((handler.getKeyManager().up || handler.getKeyManager().down || handler.getKeyManager().left
                    || handler.getKeyManager().right || handler.getKeyManager().z)) {
                showTextbox1 = true;
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
                if (friend1.getX() > handler.getPlayer().getX() + 100) {
                    friend1.setxMove(-friend1.getRunSpeed());
                } else {
                    handler.setPlayerFrozen(false);
                    handler.getCutsceneManager().setActiveCutscene(null);
                    handler.getFlags().setCutsceneActive(false);
                    handler.getFlags().setFriendEncounter1(false);
                }
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
