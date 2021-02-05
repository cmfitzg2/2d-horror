package Cutscenes;

import Entities.Creatures.Acceptance;
import Entities.Creatures.Player;
import Graphics.Assets;
import Input.KeyManager;
import Textboxes.TextboxHandler;
import Tiles.Tile;
import Variables.Handler;
import Worlds.WorldManager;

import java.awt.*;

public class AcceptanceEncounter1 implements Cutscene {
    Player player;
    private boolean firstTime = true;
    private Handler handler;
    private TextboxHandler textboxHandler1, textboxHandler2, textboxHandler3, textboxHandler4, textboxHandler5,
            textboxHandler6, textboxHandler7, textboxHandler8, textboxHandler9, textboxHandler10;
    private KeyManager keyManager;
    private int messageNum = 1;
    private boolean textbox1 = true, textbox2, textbox3, textbox4, textbox5, textbox6, textbox7, textbox8, textbox9, textbox10;
    private Acceptance acceptance;
    private final String messageOne = "Hey MC!",
            messageTwo = "Guess Acceptance is already here. \r " +
                    "I don't know why, but today I really feel relieved to hear his voice again.",
            messageThree = "Hey, it's good to see you again.",
            messageFour = "Good to see me again? \n You know we walk to school together every day right?",
            messageFive = "Haha, yeah I know. Sorry.",
            messageSix = "You alright? You look like you haven't slept much.",
            messageSeven = "Yeah, I feel like it too.",
            messageEight = "That sucks. \r " +
                    "Well, you know I'm not normally one for platitudes, but hey, it *is* Friday! \r " +
                    "Let's just knock this day out. Maybe we can do something after school to clear your head.",
            messageNine = "That sounds good. You can go ahead without me, I'll finish getting ready and be right behind you.",
            messageTen = "Cool. See you there!";

    public AcceptanceEncounter1(Handler handler) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
        textboxHandler1 = new TextboxHandler(handler, Assets.acceptanceFont, messageOne, null, 3, Color.WHITE, null, null, 50, true, false);
        textboxHandler2 = new TextboxHandler(handler, Assets.playerThinkingFont, messageTwo, null, 3, Color.WHITE, null, null, 50, true, false);
        textboxHandler3 = new TextboxHandler(handler, Assets.playerSpeakingFont, messageThree, null, 3, Color.WHITE, null, null, 50, true, false);
        textboxHandler4 = new TextboxHandler(handler, Assets.acceptanceFont, messageFour, null, 3, Color.WHITE, null, null, 50, true, false);
        textboxHandler5 = new TextboxHandler(handler, Assets.playerSpeakingFont, messageFive, null, 3, Color.WHITE, null, null, 50, true, false);
        textboxHandler6 = new TextboxHandler(handler, Assets.acceptanceFont, messageSix, null, 3, Color.WHITE, null, null, 50, true, false);
        textboxHandler7 = new TextboxHandler(handler, Assets.playerSpeakingFont, messageSeven, null, 3, Color.WHITE, null, null, 50, true, false);
        textboxHandler8 = new TextboxHandler(handler, Assets.acceptanceFont, messageEight, null, 3, Color.WHITE, null, null, 50, true, false);
        textboxHandler9 = new TextboxHandler(handler, Assets.playerSpeakingFont, messageNine, null, 3, Color.WHITE, null, null, 50, true, false);
        textboxHandler10 = new TextboxHandler(handler, Assets.acceptanceFont, messageTen, null, 3, Color.WHITE, null, null, 50, true, false);
    }

    @Override
    public void tick() {
        if (firstTime) {
            acceptance = (Acceptance) handler.getWorldManager().getWorld(WorldManager.MC_HOUSE_2_ID).getEntityManager().getEntityByUid("acceptance-mchouse2");
            player = handler.getPlayer();
            acceptance.setDirection("right");
        }
        if (textbox1 && !textboxHandler1.isFinished()) {
            textboxHandler1.tick();
        }
        if (textbox2) {
            player.setDirection("left");
            if (!textboxHandler2.isFinished()) {
                textboxHandler2.tick();
            } else {
                if (acceptance.getX() < player.getX() - 100) {
                    player.setxMove(-player.getSpeed());
                    if (Math.abs(acceptance.getY() - player.getY()) > player.getSpeed()) {
                        if (acceptance.getY() > player.getY()) {
                            player.setyMove(player.getSpeed());
                        } else if (acceptance.getY() < player.getY()) {
                            player.setyMove(-player.getSpeed());
                        }
                    } else if (Math.abs(acceptance.getY() - player.getY()) >= 1) {
                        if (acceptance.getY() > player.getY()) {
                            player.setyMove(1);
                        } else if (acceptance.getY() < player.getY()) {
                            player.setyMove(-1);
                        }
                    } else {
                        player.setyMove(0);
                    }
                } else {
                    player.setxMove(0);
                    player.setyMove(0);
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
            }
        }
        if (textbox8) {
            if (!textboxHandler8.isFinished()) {
                textboxHandler8.tick();
            }
        }
        if (textbox9) {
            if (!textboxHandler9.isFinished()) {
                textboxHandler9.tick();
            }
        }
        if (textbox10) {
            if (!textboxHandler10.isFinished()) {
                textboxHandler10.tick();
            } else {
                //move acceptance to the exit
                if (acceptance.getY() < 16 * Tile.TILEHEIGHT) {
                    acceptance.setyMove(acceptance.getSpeed());
                } else {
                    handler.getActiveWorld().getEntityManager().removeEntity(acceptance);
                    textbox10 = false;
                    exit();
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (textbox1) {
            if (!textboxHandler1.isFinished()) {
                textboxHandler1.render(g);
            } else {
                textbox2 = true;
                textbox1 = false;
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
            } else {
                textbox8 = true;
                textbox7 = false;
            }
        }
        if (textbox8) {
            if (!textboxHandler8.isFinished()) {
                textboxHandler8.render(g);
            } else {
                textbox9 = true;
                textbox8 = false;
            }
        }
        if (textbox9) {
            if (!textboxHandler9.isFinished()) {
                textboxHandler9.render(g);
            } else {
                textbox10 = true;
                textbox9 = false;
            }
        }
        if (textbox10) {
            if (!textboxHandler10.isFinished()) {
                textboxHandler10.render(g);
            }
        }
    }

    private void exit() {
        handler.setPlayerFrozen(false);
        handler.getCutsceneManager().setActiveCutscene(null);
        handler.getFlags().setCutsceneActive(false);
        handler.getFlags().setFriendEncounter1(false);
        handler.getActiveWorld().getEntityManager().removeEntity(acceptance);
    }
}
