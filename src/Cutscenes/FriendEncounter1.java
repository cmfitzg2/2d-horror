package Cutscenes;

import Entities.Creatures.Friend1;
import Entities.Creatures.Player;
import Entities.Entity;
import Graphics.Assets;
import Input.KeyManager;
import Textboxes.TextboxHandler;
import Variables.Handler;

import java.awt.*;

public class FriendEncounter1 implements Cutscene {
    Player player;
    private boolean firstTime = true;
    private boolean showTextbox1 = false;
    private Handler handler;
    private TextboxHandler textboxHandler1, textboxHandler2, textboxHandler3, textboxHandler4, textboxHandler5,
            textboxHandler6, textboxHandler7;
    private KeyManager keyManager;
    private int messageNum = 1;
    private boolean textbox1 = true, textbox2, textbox3, textbox4, textbox5, textbox6, textbox7;
    private Friend1 friend1;
    private final String messageOne = "Hey MC!",
            messageTwo = "Sounds like Acceptance is already here. \r " +
                    "I don't know why, but today I really feel relieved to hear his voice again.",
            messageThree = "Hey, it's good to see you again.",
            messageFour = "Good to see me again? \n You know we walk to school together every day right? \r " +
                    "But hey -- good to see you too! \r " +
                    "You alright? You look like you haven't slept much.",
            messageFive = "Yeah, I feel like it too.",
            messageSix = "That sucks. \r " +
                    "Well, you know I'm not normally one for platitudes, but hey, it *is* Friday! \r " +
                    "Let's just knock this day out. Maybe we can do something after school to clear your head.",
            messageSeven = "That sounds good. Let's get this over with then.";

    public FriendEncounter1(Handler handler) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
        textboxHandler1 = new TextboxHandler(handler, Assets.acceptanceFont, messageOne, null, 3, Color.WHITE, null, null, 50, true, false);
        textboxHandler2 = new TextboxHandler(handler, Assets.mcThinkingFont, messageTwo, null, 3, Color.WHITE, null, null, 50, true, false);
        textboxHandler3 = new TextboxHandler(handler, Assets.mcSpeakingFont, messageThree, null, 3, Color.WHITE, null, null, 50, true, false);
        textboxHandler4 = new TextboxHandler(handler, Assets.acceptanceFont, messageFour, null, 3, Color.WHITE, null, null, 50, true, false);
        textboxHandler5 = new TextboxHandler(handler, Assets.mcSpeakingFont, messageFive, null, 3, Color.WHITE, null, null, 50, true, false);
        textboxHandler6 = new TextboxHandler(handler, Assets.acceptanceFont, messageSix, null, 3, Color.WHITE, null, null, 50, true, false);
        textboxHandler7 = new TextboxHandler(handler, Assets.mcSpeakingFont, messageSeven, null, 3, Color.WHITE, null, null, 50, true, false);
    }

    @Override
    public void tick() {
        if (firstTime) {
            for (Entity e : handler.getActiveWorld().getEntityManager().getEntities()) {
                if (e instanceof Friend1) {
                    friend1 = (Friend1) e;
                } else if (e instanceof Player) {
                    player = (Player) e;
                }
            }
        }
        if (textbox1 && !showTextbox1) {
            if (player.getY() < 3322) {
                player.setyMove(Player.DEFAULT_SPEED);
            } else {
                player.setyMove(0);
                showTextbox1 = true;
            }
        }
        if (textbox1 && showTextbox1) {
            if (!textboxHandler1.isFinished()) {
                textboxHandler1.tick();
            }
        }
        if (textbox2) {
            player.setDirection("right");
            if (!textboxHandler2.isFinished()) {
                textboxHandler2.tick();
            } else {
                if (friend1.getX() > player.getX() + 100) {
                    friend1.setxMove(-friend1.getRunSpeed());
                } else {
                    textbox3 = true;
                    textbox2 = false;
                }
            }
        }
        if (textbox3) {
            if (!textboxHandler3.isFinished()) {
                textboxHandler3.tick();
            }
        }
        if (textbox4) {
            if (!textboxHandler4.isFinished()) {
                textboxHandler4.tick();
            }
        }
        if (textbox5) {
            if (!textboxHandler5.isFinished()) {
                textboxHandler5.tick();
            }
        }
        if (textbox6) {
            if (!textboxHandler6.isFinished()) {
                textboxHandler6.tick();
            }
        }
        if (textbox7) {
            if (!textboxHandler7.isFinished()) {
                textboxHandler7.tick();
            } else {
                if (friend1.getY() < player.getY() + 100) {
                    friend1.setyMove(friend1.getRunSpeed());
                } else if (friend1.getX() > player.getX() - (handler.getWidth() / 2.0f) - 100) {
                    friend1.setxMove(-friend1.getRunSpeed());
                } else {
                    textbox7 = false;
                    exit();
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
        if (textbox3) {
            if (!textboxHandler3.isFinished()) {
                textboxHandler3.render(g);
            } else {
                textbox4 = true;
                textbox3 = false;
            }
        }
        if (textbox4) {
            if (!textboxHandler4.isFinished()) {
                textboxHandler4.render(g);
            } else {
                textbox5 = true;
                textbox4 = false;
            }
        }
        if (textbox5) {
            if (!textboxHandler5.isFinished()) {
                textboxHandler5.render(g);
            } else {
                textbox6 = true;
                textbox5 = false;
            }
        }
        if (textbox6) {
            if (!textboxHandler6.isFinished()) {
                textboxHandler6.render(g);
            } else {
                textbox7 = true;
                textbox6 = false;
            }
        }
        if (textbox7) {
            if (!textboxHandler7.isFinished()) {
                textboxHandler7.render(g);
            }
        }
    }

    private void exit() {
        handler.setPlayerFrozen(false);
        handler.getCutsceneManager().setActiveCutscene(null);
        handler.getFlags().setCutsceneActive(false);
        handler.getFlags().setFriendEncounter1(false);
        handler.getActiveWorld().getEntityManager().removeEntity(friend1);
    }
}
