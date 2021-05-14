package Cutscenes;

import Entities.Creatures.*;
import Entities.EntityManager;
import Entities.StaticEntities.IronGate;
import Entities.StaticEntities.Mansion;
import Graphics.Assets;
import Input.KeyManager;
import Textboxes.TextboxHandler;
import Tiles.Tile;
import Utils.GeneralUtils;
import Variables.GeneralConstants;
import Variables.Handler;
import Worlds.WorldManager;
import Graphics.GameCamera;

import java.awt.*;

public class MansionExteriorCutscene1 implements Cutscene {
    Player player;
    private boolean firstTime = true;
    private Handler handler;
    private final TextboxHandler textboxHandler1, textboxHandler2, textboxHandler3, textboxHandler4, textboxHandler5,
            textboxHandler6, textboxHandler7, textboxHandler8, textboxHandler9, textboxHandler10, textboxHandler11,
            textboxHandler12, textboxHandler13, textboxHandler14;
    private KeyManager keyManager;
    private Denial denial;
    private Anger anger;
    private Bargaining bargaining;
    private Depression depression;
    private Acceptance acceptance;
    private EntityManager entityManager;
    private Mansion mansion;
    private IronGate ironGate;
    private boolean textbox1, textbox2, textbox3, textbox4, textbox5, textbox6, textbox7, textbox8, textbox9, textbox10,
            textbox11, textbox12, textbox13, dialogueOver, playerOutro, playerOutroTextbox, transitioning;
    private boolean acceptanceStop1Hit, acceptanceStop2Hit, acceptanceStop3Hit;
    private float acceptanceXStart = 20 * Tile.TILEWIDTH + Tile.TILEWIDTH / 32f;
    private float acceptanceStop1, acceptanceStop2, acceptanceStop3;
    private float acceptanceYStart;
    private GameCamera gameCamera;
    private final String message1 = "Well, here we are.",
            message2 = "Man, talk about cliché.",
            message3 = "It's obvious, isn't it? \r " +
                    "There wouldn't be any mystique surrounding this place if it didn't fit the profile of such a place. \r " +
                    "In other words, if it weren't cliché, we wouldn't be here. It's a self-fulfilling prophecy.",
            message4 = "...Yeah, right? Totally cliché.",
            message5 = "Cliche or not, I still think we shouldn't be here...",
            message6 = "Oh, hey guys, Bargaining says we shouldn't be here. Guess we're heading home. \r " +
                    "If only you had said something before.",
            message7 = "Listen to you, droning on and on. It's like you're trying to delay going inside. \r " +
                    "It's alright if you're scared. We won't think less of you!",
            message8 = "Shut UP! I'm not scared! The whole reason I came was to prove that!",
            message9 = "I had no idea my opinion was so important to you.",
            message10 = "Alright already, geez. You guys sure are at each other's throats today.",
            message11 = "You did say this place brings out \"the real you\" or whatever, right?",
            message12 = "Fair enough. But for all our sake, let's try to keep the quarreling to a minimum. \r " +
                    "So on the that note, and if you're all ready...",
            message13 = "Let's head inside.",
            playerOutroMessage = "... \r " +
                    "Honestly, I think I expected the gate to slam behind me.";

    public MansionExteriorCutscene1(Handler handler) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
        textboxHandler1 = new TextboxHandler(handler, Assets.acceptanceFont, message1, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, Assets.acceptanceText, 50, true, false);
        textboxHandler2 = new TextboxHandler(handler, Assets.depressionFont, message2, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler3 = new TextboxHandler(handler, Assets.denialFont, message3, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDenial, Assets.denialText, 50, true, false);
        textboxHandler4 = new TextboxHandler(handler, Assets.depressionFont, message4, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler5 = new TextboxHandler(handler, Assets.bargainingFont, message5, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxBargaining, Assets.bargainingText, 50, true, false);
        textboxHandler6 = new TextboxHandler(handler, Assets.angerFont, message6, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAnger, Assets.angerText, 50, true, false);
        textboxHandler7 = new TextboxHandler(handler, Assets.depressionFont, message7, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler8 = new TextboxHandler(handler, Assets.angerFont, message8, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAnger, Assets.angerText, 50, true, false);
        textboxHandler9 = new TextboxHandler(handler, Assets.depressionFont, message9, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler10 = new TextboxHandler(handler, Assets.acceptanceFont, message10, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, Assets.acceptanceText, 50, true, false);
        textboxHandler11 = new TextboxHandler(handler, Assets.playerSpeakingFont, message11, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayer, Assets.playerText, 50, true, false);
        textboxHandler12 = new TextboxHandler(handler, Assets.acceptanceFont, message12, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, Assets.acceptanceText, 50, true, false);
        textboxHandler13 = new TextboxHandler(handler, Assets.acceptanceFont, message13, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, Assets.acceptanceText, 50, true, false);
        textboxHandler14 = new TextboxHandler(handler, Assets.playerThinkingFont, playerOutroMessage, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayerThinking, Assets.playerText, 50, true, false);
    }

    @Override
    public void tick() {
        if (firstTime) {
            if (handler.getWorldManager().getActiveWorld().getId() != WorldManager.MANSION_EXTERIOR_ID) {
                return;
            }
            gameCamera = handler.getGameCamera();
            entityManager = handler.getWorldManager().getWorld(WorldManager.MANSION_EXTERIOR_ID).getEntityManager();
            denial = (Denial) entityManager.getEntityByUid("denial-mansionexterior1");
            anger = (Anger) entityManager.getEntityByUid("anger-mansionexterior1");
            bargaining = (Bargaining) entityManager.getEntityByUid("bargaining-mansionexterior1");
            depression = (Depression) entityManager.getEntityByUid("depression-mansionexterior1");
            acceptance = (Acceptance) entityManager.getEntityByUid("acceptance-mansionexterior1");
            mansion = (Mansion) entityManager.getEntityByUid("mansion-mansionexterior1");
            ironGate = (IronGate) entityManager.getEntityByUid("irongatebot-mansionexterior1");
            player = handler.getPlayer();
            denial.setIgnoreCollision(true);
            anger.setIgnoreCollision(true);
            bargaining.setIgnoreCollision(true);
            depression.setIgnoreCollision(true);
            acceptance.setIgnoreCollision(true);
            acceptanceYStart = 27 * Tile.TILEHEIGHT;
            acceptanceStop1 = acceptanceXStart + player.getWidth() + Tile.TILEWIDTH / 32f;
            acceptanceStop2 = acceptanceYStart - player.getHeight() * 3;
            acceptanceStop3 = acceptanceXStart - player.getWidth() / 2f;
            player.setIgnoreCollision(true);
            handler.setPlayerFrozen(true);
            textbox1 = true;
            firstTime = false;
        }
        if (player.getY() <= 27 * Tile.TILEHEIGHT && !handler.getGame().isFadeIn()) {
            if (textbox1) {
                if (!handler.getTimerManager().timerAdded("initial-mansion-timer")) {
                    handler.getTimerManager().addTimer("initial-mansion-timer", 60);
                } else if (handler.getTimerManager().timerExpired("initial-mansion-timer")) {
                    if (!textboxHandler1.isFinished()) {
                        textboxHandler1.tick();
                    }
                }
            }
            if (textbox2) {
                if (gameCamera.getyOffset() > mansion.getY()) {
                    gameCamera.setyOffset(gameCamera.getyOffset() - 2);
                } else {
                    if (!textboxHandler2.isFinished()) {
                        textboxHandler2.tick();
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
                }
            }
            if (textbox11) {
                if (!textboxHandler11.isFinished()) {
                    textboxHandler11.tick();
                }
            }
            if (textbox12) {
                if (!textboxHandler12.isFinished()) {
                    textboxHandler12.tick();
                }
            }
            if (textbox13) {
                if (!textboxHandler13.isFinished()) {
                    textboxHandler13.tick();
                }
            }
            if (playerOutroTextbox) {
                if (!textboxHandler14.isFinished()) {
                    textboxHandler14.tick();
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (firstTime || handler.getGame().isFadeIn()) {
            return;
        }
        if (!dialogueOver) {
            if (player.getY() > 27 * Tile.TILEHEIGHT) {
                if (textbox1) {
                    denial.setyMove(-denial.getSpeed());
                    anger.setyMove(-anger.getSpeed());
                    bargaining.setyMove(-bargaining.getSpeed());
                    depression.setyMove(-depression.getSpeed());
                    acceptance.setyMove(-acceptance.getSpeed());
                    player.setyMove(-player.getCutsceneSpeed());
                }
            } else {
                if (textbox1) {
                    denial.setyMove(0);
                    anger.setyMove(0);
                    bargaining.setyMove(0);
                    depression.setyMove(0);
                    acceptance.setyMove(0);
                    player.setyMove(0);
                    if (handler.getTimerManager().timerExpired("initial-mansion-timer")) {
                        if (!textboxHandler1.isFinished()) {
                            textboxHandler1.render(g);
                        } else {
                            textbox2 = true;
                            textbox1 = false;
                            handler.getFlags().setCameraOverride(true);
                        }
                    }
                }
                if (textbox2) {
                    if (gameCamera.getyOffset() <= mansion.getY()) {
                        if (!handler.getTimerManager().timerAdded("mansion-view-timer")) {
                            handler.getTimerManager().addTimer("mansion-view-timer", 60);
                        } else if (handler.getTimerManager().timerExpired("mansion-view-timer")) {
                            if (!textboxHandler2.isFinished()) {
                                textboxHandler2.render(g);
                            } else {
                                textbox3 = true;
                                textbox2 = false;
                            }
                        }
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
                    } else {
                        textbox11 = true;
                        textbox10 = false;
                    }
                }
                if (textbox11) {
                    if (!textboxHandler11.isFinished()) {
                        textboxHandler11.render(g);
                    } else {
                        textbox12 = true;
                        textbox11 = false;
                    }
                }
                if (textbox12) {
                    if (!textboxHandler12.isFinished()) {
                        textboxHandler12.render(g);
                    } else {
                        textbox12 = false;
                        textbox13 = true;
                    }
                }
                if (textbox13) {
                    if (gameCamera.getyOffset() < handler.getActiveWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()) {
                        gameCamera.setyOffset(gameCamera.getyOffset() + 2);
                    } else {
                        handler.getFlags().setCameraOverride(false);
                        if (!acceptanceStop1Hit) {
                            if (acceptance.getX() < acceptanceStop1) {
                                acceptance.setxMove(acceptance.getSpeed());
                            } else {
                                acceptance.setxMove(0);
                                acceptanceStop1Hit = true;
                            }
                        } else if (!acceptanceStop2Hit) {
                            if (acceptance.getY() > acceptanceStop2) {
                                acceptance.setyMove(-acceptance.getSpeed());
                            } else {
                                acceptance.setyMove(0);
                                acceptanceStop2Hit = true;
                            }
                        } else if (!acceptanceStop3Hit) {
                            if (acceptance.getX() > acceptanceStop3) {
                                acceptance.setxMove(-acceptance.getSpeed());
                            } else {
                                acceptance.setxMove(0);
                                acceptanceStop3Hit = true;
                                acceptance.setDirection("up");
                                ironGate.setOpen(true);
                            }
                        } else if (!handler.getTimerManager().timerAdded("gateopen-timer")) {
                            handler.getTimerManager().addTimer("gateopen-timer", 60);
                        } else if (handler.getTimerManager().timerExpired("gateopen-timer")) {
                            if (!textboxHandler13.isFinished()) {
                                textboxHandler13.render(g);
                            } else {
                                dialogueOver = true;
                                textbox13 = false;
                            }
                        }
                    }
                }
            }
        } else {
            if (!playerOutro) {
                if (acceptance.getY() > mansion.getY() + mansion.getHeight()) {
                    acceptance.setyMove(-acceptance.getSpeed());
                } else {
                    acceptance.setyMove(0);
                    entityManager.removeEntity(acceptance);
                }
                if (acceptance.getY() < acceptanceStop2 - Tile.TILEHEIGHT / 2f) {
                    if (denial.getY() > mansion.getY() + mansion.getHeight()) {
                        denial.setyMove(-denial.getSpeed());
                    } else {
                        denial.setyMove(0);
                        entityManager.removeEntity(denial);
                    }
                    if (anger.getY() > mansion.getY() + mansion.getHeight()) {
                        anger.setyMove(-anger.getSpeed());
                    } else {
                        anger.setyMove(0);
                        entityManager.removeEntity(anger);
                    }
                    if (anger.getY() < acceptanceStop2) {
                        if (bargaining.getY() > mansion.getY() + mansion.getHeight()) {
                            bargaining.setyMove(-bargaining.getSpeed());
                        } else {
                            bargaining.setyMove(0);
                            entityManager.removeEntity(bargaining);
                        }
                        if (depression.getY() > mansion.getY() + mansion.getHeight()) {
                            depression.setyMove(-depression.getSpeed());
                        } else {
                            depression.setyMove(0);
                            entityManager.removeEntity(depression);
                        }
                        if (depression.getY() < acceptanceStop2 + Tile.TILEHEIGHT / 2f) {
                            if (player.getY() > mansion.getY() + mansion.getHeight() + Tile.TILEHEIGHT / 2f) {
                                player.setyMove(-player.getCutsceneSpeed());
                            } else {
                                playerOutro = true;
                                player.setyMove(0);
                                handler.getTimerManager().addTimer("final-mansion-timer1", 60);
                            }
                        }
                    }
                }
            } else {
                //player outro
                if (handler.getTimerManager().timerExpired("final-mansion-timer1") && !playerOutroTextbox) {
                    handler.getPlayer().setDirection("down");
                    playerOutroTextbox = true;
                }
                if (playerOutroTextbox) {
                    if (!textboxHandler14.isFinished()) {
                        textboxHandler14.render(g);
                    } else {
                        if (player.getY() > mansion.getY() + mansion.getHeight()) {
                            player.setyMove(-player.getCutsceneSpeed());
                        } else {
                            player.setyMove(0);
                            if (handler.getGame().isFinishedFadingOut()) {
                                handler.getActiveWorld().transitionFrom(handler.getWorldManager().getWorld(WorldManager.MC_HOUSE_2_ID), 700, 600, GeneralConstants.longLevelTransition);
                                exit();
                                GeneralUtils.levelFadeIn(handler, GeneralConstants.longLevelTransition);
                                return;
                            }
                            handler.getActiveWorld().transitionFrom(handler.getWorldManager().getWorld(WorldManager.MC_HOUSE_2_ID), 700, 600, GeneralConstants.longLevelTransition);
                            if (ironGate.isOpen() && handler.getGame().getAlpha() >= 120) {
                                ironGate.setOpen(false);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void exit() {
        handler.setPlayerFrozen(false);
        handler.getCutsceneManager().setActiveCutscene(null);
        handler.getFlags().setCutsceneActive(false);
        handler.getFlags().setMansionExteriorCutscene1(false);
        denial.setIgnoreCollision(false);
        anger.setIgnoreCollision(false);
        bargaining.setIgnoreCollision(false);
        depression.setIgnoreCollision(false);
        acceptance.setIgnoreCollision(false);
        player.setIgnoreCollision(false);
    }
}
