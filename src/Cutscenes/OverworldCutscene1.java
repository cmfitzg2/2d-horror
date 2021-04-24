package Cutscenes;

import Entities.Creatures.*;
import Entities.EntityManager;
import Graphics.Assets;
import Input.KeyManager;
import Textboxes.TextboxHandler;
import Tiles.Tile;
import Utils.GeneralUtils;
import Variables.GeneralConstants;
import Variables.Handler;
import Worlds.World;
import Worlds.WorldManager;

import java.awt.*;

public class OverworldCutscene1 implements Cutscene {
    Player player;
    private boolean firstTime = true;
    private Handler handler;
    private final TextboxHandler textboxHandler1, textboxHandler2, textboxHandler3, textboxHandler4, textboxHandler5,
            textboxHandler6, textboxHandler7, textboxHandler8, textboxHandler9, textboxHandler10, textboxHandler11,
            textboxHandler12, textboxHandler13, textboxHandler14, textboxHandler15, textboxHandler16;
    private KeyManager keyManager;
    private Denial denial;
    private Anger anger;
    private Bargaining bargaining;
    private Depression depression;
    private Acceptance acceptance;
    private EntityManager entityManager;
    private boolean textbox1, textbox2, textbox3, textbox4, textbox5, textbox6, textbox7, textbox8, textbox9, textbox10,
            textbox11, textbox12, textbox13, textbox14, textbox15, textbox16, dialogueOver;
    private boolean depressionStop1Hit, depressionStop2Hit;
    private final int depressionStop1 = 48 * Tile.TILEWIDTH, depressionStop2 = (int) (50.5 * Tile.TILEHEIGHT), depressionStop3 = (int) (44.5f * Tile.TILEWIDTH),
            angerXStart = (int) (41.5 * Tile.TILEWIDTH);
    private final String message1 = "Whoa! Slow down there.",
            message2 = "Huh? It's you guys? \r " +
                    "So the person who just hit my window was...",
            message3 = "I may have some information regarding that.",
            message4 = "Depression, I told you not to...",
            message5 = "Ahh come on, he came out didn't he? \r " +
                    "I was just messing around.",
            message6 = "Oh yeah? Did you cut my power as a joke too? You scared the hell out of me.",
            message7 = "What? We just got here.",
            message8 = "Your power's out? Yeah that definitely wasn't us. \r " +
                    "We assumed you were sleeping when we pulled up and saw all your lights out.",
            message9 = "And I heroically volunteered to wake you up!",
            message10 = "Yeah... \"heroically\"...",
            message11 = "Well, that's.. That's really odd timing then.",
            message12 = "Don't correlate unrelated things. Random power surges are pretty common. I doubt this is the first for you. \r "
                    + "Even in the modern era, we're still incapable of reliably delivering power to homes. \r "
                    + "There's just too many moving pieces. A lot of things can go wrong, and any of them can cause a power outage.",
            message13 = "Ever the rationalist. \r "
                    + "But yeah, he's probably right. I wouldn't dwell on it.",
            message14 = "Hey, speaking of not dwelling... \r "
                    + "CAN WE LEAVE ALREADY?",
            message15 = "Ahh shoot that's right, we're already wasting Anger's whole night. \n Let's try to be gracious. \r "
                    + "You ready to head out MC?",
            message16 = "Ready as I'll ever be. Let's go.";

    public OverworldCutscene1(Handler handler) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
        textboxHandler1 = new TextboxHandler(handler, Assets.acceptanceFont, message1, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, Assets.acceptanceText, 50, true, false);
        textboxHandler2 = new TextboxHandler(handler, Assets.playerSpeakingFont, message2, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayer, Assets.playerText, 50, true, false);
        textboxHandler3 = new TextboxHandler(handler, Assets.depressionFont, message3, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler4 = new TextboxHandler(handler, Assets.bargainingFont, message4, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxBargaining, Assets.bargainingText, 50, true, false);
        textboxHandler5 = new TextboxHandler(handler, Assets.depressionFont, message5, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler6 = new TextboxHandler(handler, Assets.playerSpeakingFont, message6, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayer, Assets.playerText, 50, true, false);
        textboxHandler7 = new TextboxHandler(handler, Assets.depressionFont, message7, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler8 = new TextboxHandler(handler, Assets.acceptanceFont, message8, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, Assets.acceptanceText, 50, true, false);
        textboxHandler9 = new TextboxHandler(handler, Assets.depressionFont, message9, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler10 = new TextboxHandler(handler, Assets.bargainingFont, message10, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxBargaining, Assets.bargainingText, 50, true, false);
        textboxHandler11 = new TextboxHandler(handler, Assets.playerSpeakingFont, message11, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayer, Assets.playerText, 50, true, false);
        textboxHandler12 = new TextboxHandler(handler, Assets.denialFont, message12, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDenial, Assets.denialText, 50, true, false);
        textboxHandler13 = new TextboxHandler(handler, Assets.acceptanceFont, message13, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, Assets.acceptanceText, 50, true, false);
        textboxHandler14 = new TextboxHandler(handler, Assets.angerFont, message14, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAnger, Assets.angerText, 50, true, false);
        textboxHandler15 = new TextboxHandler(handler, Assets.acceptanceFont, message15, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, Assets.acceptanceText, 50, true, false);
        textboxHandler16 = new TextboxHandler(handler, Assets.playerSpeakingFont, message16, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayer, Assets.playerText, 50, true, false);
    }

    @Override
    public void tick() {
        if (firstTime) {
            if (handler.getWorldManager().getActiveWorld().getId() != WorldManager.OVERWORLD_1_ID) {
                return;
            }
            entityManager = handler.getWorldManager().getWorld(WorldManager.OVERWORLD_1_ID).getEntityManager();
            denial = (Denial) entityManager.getEntityByUid("denial-overworld1");
            anger = (Anger) entityManager.getEntityByUid("anger-overworld1");
            bargaining = (Bargaining) entityManager.getEntityByUid("bargaining-overworld1");
            depression = (Depression) entityManager.getEntityByUid("depression-overworld1");
            acceptance = (Acceptance) entityManager.getEntityByUid("acceptance-overworld1");
            player = handler.getPlayer();
            textbox1 = true;
            firstTime = false;
        }
        if (!handler.getGame().isFadeIn()) {
            if (textbox1) {
                if (!textboxHandler1.isFinished()) {
                    textboxHandler1.tick();
                }
            }
            if (textbox2) {
                if (!textboxHandler2.isFinished()) {
                    textboxHandler2.tick();
                } else {
                    if (!depressionStop1Hit) {
                        if (depression.getX() < depressionStop1) {
                            depression.setxMove(depression.getSpeed());
                        } else {
                            depression.setxMove(0);
                            depressionStop1Hit = true;
                        }
                    } else if (!depressionStop2Hit) {
                        if (depression.getY() < depressionStop2) {
                            depression.setyMove(depression.getSpeed());
                        } else {
                            depression.setyMove(0);
                            depressionStop2Hit = true;
                        }
                    } else {
                        if (depression.getX() > depressionStop3) {
                            depression.setxMove(-depression.getSpeed());
                        } else {
                            textbox3 = true;
                            textbox2 = false;
                        }
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
            if (textbox14) {
                if (!textboxHandler14.isFinished()) {
                    textboxHandler14.tick();
                }
            }
            if (textbox15) {
                if (!textboxHandler15.isFinished()) {
                    textboxHandler15.tick();
                }
            }
            if (textbox16) {
                if (!textboxHandler16.isFinished()) {
                    textboxHandler16.tick();
                }
            }
            if (dialogueOver) {
                anger.setxMove(-anger.getSpeed());
                handler.getFlags().setCameraOverride(true);
                if (anger.getX() < angerXStart - Tile.TILEWIDTH * 3) {
                    player.setxMove(-player.getCutsceneSpeed());
                    denial.setxMove(-denial.getSpeed());
                    bargaining.setxMove(-bargaining.getSpeed());
                    depression.setxMove(-depression.getSpeed());
                    acceptance.setxMove(-acceptance.getSpeed());
                    if (!handler.getGame().isFadeOut()) {
                        GeneralUtils.levelFadeOut(handler, GeneralConstants.veryLongLevelTransition);
                    } else {
                        if (handler.getGame().isFinishedFadingOut()) {
                            exit();
                        }
                    }
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
                textbox13 = true;
                textbox12 = false;
            }
        }
        if (textbox13) {
            if (!textboxHandler13.isFinished()) {
                textboxHandler13.render(g);
            } else {
                textbox14 = true;
                textbox13 = false;
            }
        }
        if (textbox14) {
            if (!textboxHandler14.isFinished()) {
                textboxHandler14.render(g);
            } else {
                textbox15 = true;
                textbox14 = false;
            }
        }
        if (textbox15) {
            if (!textboxHandler15.isFinished()) {
                textboxHandler15.render(g);
            } else {
                textbox16 = true;
                textbox15 = false;
            }
        }
        if (textbox16) {
            if (!textboxHandler16.isFinished()) {
                textboxHandler16.render(g);
            } else {
                textbox16 = false;
                dialogueOver = true;
            }
        }
    }

    @Override
    public void exit() {
        handler.setPlayerFrozen(false);
        handler.getCutsceneManager().setActiveCutscene(null);
        handler.getFlags().setCutsceneActive(false);
        handler.getFlags().setOverworldCutscene1(false);
        handler.getFlags().setCameraOverride(false);
        handler.getGame().setFadeOut(false, true);
        GeneralUtils.stopLevelFadeOut(handler, handler.getWorldManager().getWorld(WorldManager.MANSION_EXTERIOR_ID), 64, 64, false);
        handler.getWorldManager().setActiveWorld(handler.getWorldManager().getWorld(WorldManager.MANSION_EXTERIOR_ID));
    }
}
