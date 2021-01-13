package Cutscenes;

import Input.KeyManager;
import Textboxes.TextboxHandler;
import Utils.GeneralUtils;
import Variables.Handler;
import Graphics.Assets;

import java.awt.*;

public class Prologue implements Cutscene {
    private boolean showTextbox2 = false;
    private Handler handler;
    private TextboxHandler textboxHandler1, textboxHandler2;
    private KeyManager keyManager;
    private int messageNum = 1;
    private boolean textbox1 = true, textbox2 = false;
    private final String messageOne = "... \r ";
/*            "I wonder if it's time to wake up. \r " +
            "I hate this feeling. It's the uncertainty of it. \r " +
            "If I open my eyes and it's bright in my room, it'll be time to get up. \r " +
            "If it's still dark, I'll have to find a way to get back to sleep. \r " +
            "Why is it that I don't know whether it's morning or still night before I open my eyes? \r " +
            "I wonder if most people know when it's time to get up based on how they feel. \r " +
            "I mean, sleep is a biological process, right? \r " +
            "I feel full after I eat. \n I feel sated when I drink. \r " +
            "When I'm cold, it feels good to warm up. \n When I'm hot, it feels good to cool down. \r " +
            "But when I wake up, I'm still tired. \n There's no relief. \r " +
            "I think I used to wake up feeling refreshed. \n I did, didn't I? \r " +
            "I wonder when that stopped. \r " +
            "I guess it was probably gradual. \n I'm sure I would've noticed if it was all at once. \r " +
            "Gosh, that should probably alarm me more, shouldn't it? I wonder what else I've lost without noticing. \r " +
            "... \r " +
            "Well, I followed that train of thought a little too far. \n At this point, I don't think I'll be getting back to sleep no matter what time it is. \r " +
            "Guess I'd better hope it's morning then.";*/
    private final String messageTwo = "... \r " +
            "I think I'm disappointed anyway.";

    public Prologue(Handler handler) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
        textboxHandler1 = new TextboxHandler(handler, Assets.serif, messageOne, null, 3, Color.WHITE, null, null, 50, true);
        textboxHandler2 = new TextboxHandler(handler, Assets.serif, messageTwo, null, 3, Color.WHITE, null, null, 50, true);
    }

    @Override
    public void tick() {
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
                if (!handler.getGame().isFadeIn()) {
                    handler.setPlayerFrozen(true);
                    if ((handler.getKeyManager().up || handler.getKeyManager().down || handler.getKeyManager().left
                            || handler.getKeyManager().right || handler.getKeyManager().z)) {
                        showTextbox2 = true;
                    }
                }
            } else {
                handler.setPlayerFrozen(false);
                handler.getCutsceneManager().setActiveCutscene(null);
                handler.getFlags().setCutsceneActive(false);
                handler.getFlags().setPrologue(false);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (textbox1) {
            handler.getScreenOverlay().overlayScreen(g, Color.black);
            if (!textboxHandler1.isFinished()) {
                textboxHandler1.render(g);
            }
        } else if (textbox2) {
            if (!textboxHandler2.isFinished() && showTextbox2) {
                textboxHandler2.render(g);
            }
        }
    }
}
