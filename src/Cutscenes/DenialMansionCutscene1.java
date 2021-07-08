package Cutscenes;

import Entities.Creatures.Denial;
import Entities.Creatures.Player;
import Entities.EntityManager;
import Entities.StaticEntities.Dresser;
import Entities.StaticEntities.Hole;
import Input.KeyManager;
import Textboxes.TextboxHandler;
import Tiles.Tile;
import Utils.TimerManager;
import Variables.GeneralConstants;
import Variables.Handler;
import Worlds.WorldManager;
import Graphics.Assets;

import java.awt.*;

public class DenialMansionCutscene1 implements Cutscene {

    Player player;
    private boolean firstTime = true, timer1Added, timer2Added, timer3Added, initialSetup;
    private Handler handler;
    private TimerManager timerManager;
    private TextboxHandler textboxHandler1, textboxHandler2, textboxHandler3, textboxHandler4, textboxHandler5, textboxHandler6, textboxHandler7, textboxHandler8;
    private KeyManager keyManager;
    private int messageNum = 1;
    private boolean textbox1, textbox2, textbox3, textbox4, textbox5, textbox6, textbox7, textbox8;
    private Denial denial;
    private final String message1 = "Hey MC, something weird just happened in the room I was in. \r "
            + "I don't really know how to explain this, but... \r "
            + "One of the bookshelves seemed to just...",
            message2 = "Huh? \r "
                    + "Wait, in here too?",
            message3 = "Hey, there's something here...",
            message4 = "...",
            message5 = "What the heck? \n MC, come look at this.",
            message6 = "What is it?",
            message7 = "I'm not totally sure, but it looks like some kind of map to this place.",
            message8 = "Let's go find everyone else. We should show";
    private Dresser movingDresser;
    private float initialX, stepSize;
    private int oscillationIndex = 0;

    public DenialMansionCutscene1(Handler handler) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
        textboxHandler1 = new TextboxHandler(handler, Assets.denialFont, message1, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDenial, Assets.denialText, 50, true, false);
        textboxHandler2 = new TextboxHandler(handler, Assets.denialFont, message2, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDenial, Assets.denialText, 50, true, false);
        textboxHandler3 = new TextboxHandler(handler, Assets.denialFont, message3, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDenial, Assets.denialText, 50, true, false);
        textboxHandler4 = new TextboxHandler(handler, Assets.denialFont, message4, null, GeneralConstants.slowTextSpeed, Color.WHITE, null, Assets.textboxDenial, Assets.denialText, 50, false, false);
        textboxHandler5 = new TextboxHandler(handler, Assets.denialFont, message5, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDenial, Assets.denialText, 50, true, false);
        textboxHandler6 = new TextboxHandler(handler, Assets.playerSpeakingFont, message6, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayer, Assets.playerText, 50, true, false);
        textboxHandler7 = new TextboxHandler(handler, Assets.denialFont, message7, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDenial, Assets.denialText, 50, true, false);
        textboxHandler8 = new TextboxHandler(handler, Assets.denialFont, message8, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDenial, Assets.denialText, 50, false, false);
    }

    @Override
    public void tick() {
        if (firstTime) {
            timerManager = handler.getTimerManager();
            EntityManager entityManagerL2R4 = handler.getWorldManager().getWorld(WorldManager.MANSION_L2_ROOM_4_ID).getEntityManager();
            entityManagerL2R4.removeEntity(entityManagerL2R4.getEntityByUid("denial-mansionL2Room4"));
            Dresser dresserL2R4 = (Dresser) entityManagerL2R4.getEntityByUid("mansionL2Room4-dresser2");
            dresserL2R4.setX(dresserL2R4.getX() + Tile.TILEWIDTH * 1.5f);
            EntityManager entityManagerL2R3 = handler.getWorldManager().getWorld(WorldManager.MANSION_L2_ROOM_3_ID).getEntityManager();
            movingDresser = (Dresser) entityManagerL2R3.getEntityByUid("mansionL2Room3-dresser2");
            player = handler.getPlayer();
            Assets.woodDrag.setFramePosition(0);
            Assets.woodDrag.start();
            stepSize = Tile.TILEWIDTH * 1.5f / (Assets.woodDrag.getMicrosecondLength() * handler.getGame().getFps() / 1000000f);
            initialX = movingDresser.getX();
            firstTime = false;
        }
        if (!initialSetup) {
            if (movingDresser.getX() > initialX - Tile.TILEWIDTH * 1.5f) {
                moveDresser();
            } else {
                if (!timer1Added) {
                    timerManager.addTimer("denial-approach-timer", 120);
                    timer1Added = true;
                } else {
                    if (timerManager.timerExpired("denial-approach-timer")) {
                        denial = new Denial(handler, 23 * Tile.TILEWIDTH, 12.75f * Tile.TILEHEIGHT, "denial-l2r3-cutscene");
                        denial.setDirection("left");
                        handler.getActiveWorld().getEntityManager().addEntity(denial);
                        textbox1 = true;
                        initialSetup = true;
                    }
                }
            }
        }
        if (textbox1) {
            if (!textboxHandler1.isFinished()) {
                textboxHandler1.tick();
            }
        }
        if (textbox2) {
            if (!textboxHandler2.isFinished()) {
                textboxHandler2.tick();
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
            if (!textboxHandler8.isTextFinished()) {
                textboxHandler8.tick();
            } else {
                Hole hole = new Hole(handler, denial.getX() - Assets.hole.getWidth() / 4f, denial.getY() + Assets.hole.getHeight() / 4f,
                        Assets.hole.getWidth() * 2, Assets.hole.getHeight() * 2, "hole-L2R3",
                        handler.getWorldManager().getWorld(WorldManager.MANSION_L1_ROOM_3_ID), initialX + 4 - player.getWidth(), movingDresser.getY() + movingDresser.getHeight());

                handler.getActiveWorld().getEntityManager().addEntity(hole);
                exit();
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (textbox1) {
            if (denial.getX() > initialX) {
                denial.setxMove(-denial.getSpeed());
            } else {
                denial.setxMove(0);
                if (player.isSitBookRight()) {
                    player.setSitBookRight(false);
                    player.setSitRight(true);
                }
                if (!textboxHandler1.isFinished()) {
                    textboxHandler1.render(g);
                } else {
                    if (!timer2Added) {
                        timerManager.addTimer("denial-turn-timer", 60);
                        denial.setDirection("up");
                        timer2Added = true;
                    } else if (timerManager.timerExpired("denial-turn-timer")) {
                        textbox1 = false;
                        textbox2 = true;
                    }
                }
            }
        }
        if (textbox2) {
            if (timerManager.timerExpired("denial-turn-timer")) {
                if (!textboxHandler2.isFinished()) {
                    textboxHandler2.render(g);
                } else {
                    if (denial.getY() + denial.getHeight() / 2 > movingDresser.getY() + movingDresser.getHeight()) {
                        denial.setyMove(-denial.getSpeed());
                    } else {
                        if (!timer3Added) {
                            denial.setyMove(0);
                            timerManager.addTimer("denial-check-item-timer", 60);
                            timer3Added = true;
                        } else if (timerManager.timerExpired("denial-check-item-timer")) {
                            textbox2 = false;
                            textbox3 = true;
                        }
                    }
                }
            }
        }
        if (textbox3) {
            if (!textboxHandler3.isFinished()) {
                textboxHandler3.render(g);
            } else {
                textbox3 = false;
                textbox4 = true;
            }
        }
        if (textbox4) {
            if (!textboxHandler4.isFinished()) {
                textboxHandler4.render(g);
            } else {
                textbox4 = false;
                textbox5 = true;
            }
        }
        if (textbox5) {
            if (!textboxHandler5.isFinished()) {
                textboxHandler5.render(g);
            } else {
                if (player.isSitRight()) {
                    player.setSitRight(false);
                    player.setX(handler.getPlayer().getX() + Tile.TILEWIDTH);
                } else {
                    if (player.getX() < denial.getX() + 4 - player.getWidth()) {
                        player.setxMove(player.getCutsceneSpeed());
                    } else {
                        player.setxMove(0);
                    }
                    if (player.getY() > denial.getY()) {
                        player.setyMove(-player.cutsceneSpeed);
                    } else {
                        player.setyMove(0);
                    }
                    if (player.getX() >= denial.getX() + 4 - player.getWidth() && player.getY() <= denial.getY()) {
                        textbox5 = false;
                        textbox6 = true;
                    }
                }
            }
        }
        if (textbox6) {
            if (!textboxHandler6.isFinished()) {
                textboxHandler6.render(g);
            } else {
                textbox6 = false;
                textbox7 = true;
            }
        }
        if (textbox7) {
            if (!textboxHandler7.isFinished()) {
                textboxHandler7.render(g);
            } else {
                textbox7 = false;
                textbox8 = true;
            }
        }
        if (textbox8) {
            if (!textboxHandler8.isTextFinished()) {
                textboxHandler8.render(g);
            }
        }
    }

    @Override
    public void exit() {
        handler.setPlayerFrozen(false);
        player.setLockX(false);
        player.setLockY(false);
        player.setLockZ(false);
        handler.getCutsceneManager().setActiveCutscene(null);
        handler.getFlags().setCutsceneActive(false);
        handler.getFlags().setDenialMansionCutscene1(false);
    }

    private void moveDresser() {
        movingDresser.setX(movingDresser.getX() - stepSize);
        if (oscillationIndex < 3) {
            movingDresser.setY(movingDresser.getY() - 1);
        } else {
            movingDresser.setY(movingDresser.getY() + 1);
        }
        oscillationIndex++;
        if (oscillationIndex > 5) {
            oscillationIndex = 0;
        }
    }
}
