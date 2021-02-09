package Cutscenes;

import Entities.Creatures.*;
import Graphics.Assets;
import Input.KeyManager;
import Textboxes.TextboxHandler;
import Tiles.Tile;
import Variables.GeneralConstants;
import Variables.Handler;
import Worlds.WorldManager;

import java.awt.*;

public class SchoolCutscene1 implements Cutscene {
    Player player;
    private boolean firstTime = true;
    private Handler handler;
    private final TextboxHandler textboxHandler1, textboxHandler2, textboxHandler3, textboxHandler4, textboxHandler5,
            textboxHandler6, textboxHandler7, textboxHandler8, textboxHandler9, textboxHandler10, textboxHandler11,
            textboxHandler12, textboxHandler13, textboxHandler14, textboxHandler15, textboxHandler16, textboxHandler17,
            textboxHandler18, textboxHandler19, textboxHandler20, textboxHandler21, textboxHandler22, textboxHandler23,
            textboxHandler24, textboxHandler25, textboxHandler26, textboxHandler27, textboxHandler28, textboxHandler29,
            textboxHandler30, textboxHandler31, textboxHandler32, textboxHandler33, textboxHandler34, textboxHandler35,
            textboxHandler36, textboxHandler37, textboxHandler38, textboxHandler39, textboxHandler40, textboxHandler41,
            textboxHandler42, textboxHandler43, textboxHandler44, textboxHandler45, textboxHandler46, textboxHandler47,
            textboxHandler48, textboxHandler49, textboxHandler50, textboxHandler51, textboxHandler52, textboxHandler53,
            textboxHandler54, textboxHandler55, textboxHandler56, textboxHandler57, textboxHandler58;
    private KeyManager keyManager;
    private Denial denial;
    private Anger anger;
    private Bargaining bargaining;
    private Depression depression;
    private Acceptance acceptance;
    private boolean showTextboxOne = false, initialSetupFinished = false;
    private boolean textbox2, textbox3, textbox4, textbox5, textbox6, textbox7, textbox8, textbox9, textbox10,
            textbox11, textbox12, textbox13, textbox14, textbox15, textbox16, textbox17, textbox18, textbox19,
            textbox20, textbox21, textbox22, textbox23, textbox24, textbox25, textbox26, textbox27, textbox28,
            textbox29, textbox30, textbox31, textbox32, textbox33, textbox34, textbox35, textbox36, textbox37,
            textbox38, textbox39, textbox40, textbox41, textbox42, textbox43, textbox44, textbox45, textbox46,
            textbox47, textbox48, textbox49, textbox50, textbox51, textbox52, textbox53, textbox54, textbox55,
            textbox56, textbox57, textbox58;
    private final int acceptanceXFinal = 790, acceptanceYFinal = 550, playerXFinal = 790, playerYFinal = 600, classroomX = 6 * Tile.TILEWIDTH, classroomY = 11 * Tile.TILEHEIGHT + Tile.TILEHEIGHT / 2;
    private final String message1 = "There you are! \r " +
            "We're a little early, but everyone's already here.",
            message2 = "Hey MC. You look like literal garbage.",
            message3 = "That... seems unnecessary to say.",
            message4 = "I'm gonna go with Anger on this one. \r " +
                    "I spend a lot of time around garbage, and this definitely feels reminiscent.",
            message5 = "Yeah, I've just been really tired lately. Can't seem to think clearly.",
            message6 = "Happens to everyone sometimes. You'll get over it. \r " +
                    "Besides, you don't look that bad.",
            message7 = "I'm sure you're trying to be nice, Denial, but that comes off as .. a little unsympathetic.",
            message8 = "Whatever.",
            message9 = "If it's sympathy you're after, I'm sorry that you look like literal garbage.",
            message10 = "I wish you'd talk to me like that.",
            message11 = "You probably shouldn't talk to anyone like that.",
            message12 = "Don't be such a thin skinned baby for once, Bargaining.",
            message13 = "Back off. Don't be mean to Bargaining.",
            message14 = "You know, I bet all this bickering isn't helping MC feel much better.",
            message15 = "It's a little nice. I can't really keep up right now, so it all kind of just blends together into white noise.",
            message16 = "About time one of you joined me as the second basket case of the group.",
            message17 = "You joke about your mental health a lot, Depression. You should take that stuff seriously.",
            message18 = "Yeah, please don't make any jokes, Depression. Bargaining might start crying.",
            message19 = "It's serious!",
            message20 = "It really isn't.",
            message21 = "No, it is. Take care of yourself, Depression. \r " +
                    "Actually, MC and I were hoping to do something after school. You wanna join us?",
            message22 = "You know how much I like being a third wheel.",
            message23 = "No, seriously, come with us. We like having you around.",
            message24 = "You're gonna make me blush. \r " +
                    "Alright, sure, I'm up for whatever. Beats spending the night with myself. \r " +
                    "What's the plan anyway?",
            message25 = "Well, I haven't given it much thought. \r " +
                    "What do you guys think about heading up to (THE PLACE)?",
            message26 = "... \r " +
                    "You haven't given it much thought and that's what pops into your head?",
            message27 = "Didn't think we'd be getting the group's third basket case so soon.",
            message28 = "I know, I know. But have you heard of the art gallery that's supposed to be there?",
            message29 = "Art gallery?",
            message30 = "Yeah. You know how abstract art can affect different people in different ways? \r " +
                    "There's supposed to be a gallery there with art that looks different to everyone. \r " +
                    "But it's more than that. They say it reveals your innermost self. That looking at it can help you to find yourself.",
            message31 = "You really buy into that crap?",
            message32 = "I'm not totally sure. I just want to help MC out of his rut, and if the rumors are true, this could help. \r " +
                    "And Depression, I suspect you might also benefit.",
            message33 = "I know myself fine. That's the problem.",
            message34 = "You're missing the point. \r " +
                    "Even if you didn't, going there wouldn't help. It's just an urban legend. \r " +
                    "Anger, can you talk some sense into these people?",
            message35 = "I've tried. I've been calling them idiots for years, and they're still idiots.",
            message36 = "I can hear your voice shaking.",
            message37 = "Idiot.",
            message38 = "Don't be like that. You should come too.",
            message39 = "Why would I? \r " +
                    "If you idiots want to waste your Friday night, be my guest, but I have a life.",
            message40 = "Scared you'll lose it?",
            message41 = "Will you shut up about it?",
            message42 = "Come on, you know he's teasing you. \r " +
                    "Just come with us. You can help keep us safe.",
            message43 = "... \r " +
                    "Fine, whatever. \r " +
                    "If this place does help you to find yourself, I want to be there when you all realize you're idiots.",
            message44 = "Way ahead of you.",
            message45 = "Well, this is shaping up to be quite the night. I'm more excited than nervous now.",
            message46 = "What about you, Bargaining? You've been quiet.",
            message47 = "... \r " +
                    "I don't know guys. \n I really don't think it's such a good idea to go. \r " +
                    "That place is supposed to be really sketchy. Can't we do our soul-searching at home? \r " +
                    "We could just go hang out at my place. I'll make us food.",
            message48 = "Shut up and just say no already. You're such a wuss.",
            message49 = "You've got nothing to be worried about. \n If a monster wants to eat one of us, they'll definitely start with Anger. \r " +
                    "That should give you some time to escape!",
            message50 = "It's up to you. \r " +
                    "We'd love to have you, but I won't pressure you to do something you don't want to do.",
            message51 = "I will. Come with us!",
            message52 = "... \r " +
                    "If you really want me to, I guess I will.",
            message53 = "See Acceptance? Pressuring people works.",
            message54 = "Guess so. \r " +
                    "Well Denial, you're officially the odd man out.",
            message55 = "Yeah, *I'm* the odd one in this group. \r " +
                    "(Sigh). \r " +
                    "Yeah, whatever, alright. \r " +
                    "I know it'll be a wasted night, but at least I won't be the one who didn't do something that even Bargaining did.",
            message56 = "Don't worry, Bargaining. I'm the low bar in every other situation.",
            message57 = "Looks like our plans are made then! \r " +
                    "Let's just make it to the end of the day now, MC.",
            message58 = "Yep. Back to plan A.";

    public SchoolCutscene1(Handler handler) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
        textboxHandler1 = new TextboxHandler(handler, Assets.acceptanceFont, message1, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, Assets.acceptanceText, 50, true, false);
        textboxHandler2 = new TextboxHandler(handler, Assets.angerFont, message2, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAnger, Assets.angerText, 50, true, false);
        textboxHandler3 = new TextboxHandler(handler, Assets.bargainingFont, message3, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxBargaining, Assets.bargainingText, 50, true, false);
        textboxHandler4 = new TextboxHandler(handler, Assets.depressionFont, message4, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler5 = new TextboxHandler(handler, Assets.playerSpeakingFont, message5, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayer, Assets.playerText, 50, true, false);
        textboxHandler6 = new TextboxHandler(handler, Assets.denialFont, message6, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDenial, Assets.denialText, 50, true, false);
        textboxHandler7 = new TextboxHandler(handler, Assets.bargainingFont, message7, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxBargaining, Assets.bargainingText, 50, true, false);
        textboxHandler8 = new TextboxHandler(handler, Assets.denialFont, message8, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDenial, Assets.denialText, 50, true, false);
        textboxHandler9 = new TextboxHandler(handler, Assets.angerFont, message9, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAnger, Assets.angerText, 50, true, false);
        textboxHandler10 = new TextboxHandler(handler, Assets.depressionFont, message10, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler11 = new TextboxHandler(handler, Assets.bargainingFont, message11, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxBargaining, Assets.bargainingText, 50, true, false);
        textboxHandler12 = new TextboxHandler(handler, Assets.angerFont, message12, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAnger, Assets.angerText, 50, true, false);
        textboxHandler13 = new TextboxHandler(handler, Assets.depressionFont, message13, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler14 = new TextboxHandler(handler, Assets.acceptanceFont, message14, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, Assets.acceptanceText, 50, true, false);
        textboxHandler15 = new TextboxHandler(handler, Assets.playerSpeakingFont, message15, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayer, Assets.playerText, 50, true, false);
        textboxHandler16 = new TextboxHandler(handler, Assets.depressionFont, message16, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler17 = new TextboxHandler(handler, Assets.bargainingFont, message17, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxBargaining, Assets.bargainingText, 50, true, false);
        textboxHandler18 = new TextboxHandler(handler, Assets.angerFont, message18, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAnger, Assets.angerText, 50, true, false);
        textboxHandler19 = new TextboxHandler(handler, Assets.bargainingFont, message19, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxBargaining, Assets.bargainingText, 50, true, false);
        textboxHandler20 = new TextboxHandler(handler, Assets.denialFont, message20, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDenial, Assets.denialText, 50, true, false);
        textboxHandler21 = new TextboxHandler(handler, Assets.acceptanceFont, message21, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, Assets.acceptanceText, 50, true, false);
        textboxHandler22 = new TextboxHandler(handler, Assets.depressionFont, message22, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler23 = new TextboxHandler(handler, Assets.playerSpeakingFont, message23, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayer, Assets.playerText, 50, true, false);
        textboxHandler24 = new TextboxHandler(handler, Assets.depressionFont, message24, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler25 = new TextboxHandler(handler, Assets.acceptanceFont, message25, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, Assets.acceptanceText, 50, true, false);
        textboxHandler26 = new TextboxHandler(handler, Assets.playerSpeakingFont, message26, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayer, Assets.playerText, 50, true, false);
        textboxHandler27 = new TextboxHandler(handler, Assets.depressionFont, message27, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler28 = new TextboxHandler(handler, Assets.acceptanceFont, message28, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, Assets.acceptanceText, 50, true, false);
        textboxHandler29 = new TextboxHandler(handler, Assets.playerSpeakingFont, message29, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayer, Assets.playerText, 50, true, false);
        textboxHandler30 = new TextboxHandler(handler, Assets.acceptanceFont, message30, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, Assets.acceptanceText, 50, true, false);
        textboxHandler31 = new TextboxHandler(handler, Assets.denialFont, message31, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDenial, Assets.denialText, 50, true, false);
        textboxHandler32 = new TextboxHandler(handler, Assets.acceptanceFont, message32, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, Assets.acceptanceText, 50, true, false);
        textboxHandler33 = new TextboxHandler(handler, Assets.depressionFont, message33, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler34 = new TextboxHandler(handler, Assets.denialFont, message34, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDenial, Assets.denialText, 50, true, false);
        textboxHandler35 = new TextboxHandler(handler, Assets.angerFont, message35, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAnger, Assets.angerText, 50, true, false);
        textboxHandler36 = new TextboxHandler(handler, Assets.depressionFont, message36, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler37 = new TextboxHandler(handler, Assets.angerFont, message37, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAnger, Assets.angerText, 50, true, false);
        textboxHandler38 = new TextboxHandler(handler, Assets.acceptanceFont, message38, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, Assets.acceptanceText, 50, true, false);
        textboxHandler39 = new TextboxHandler(handler, Assets.angerFont, message39, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAnger, Assets.angerText, 50, true, false);
        textboxHandler40 = new TextboxHandler(handler, Assets.depressionFont, message40, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler41 = new TextboxHandler(handler, Assets.angerFont, message41, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAnger, Assets.angerText, 50, true, false);
        textboxHandler42 = new TextboxHandler(handler, Assets.acceptanceFont, message42, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, Assets.acceptanceText, 50, true, false);
        textboxHandler43 = new TextboxHandler(handler, Assets.angerFont, message43, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAnger, Assets.angerText, 50, true, false);
        textboxHandler44 = new TextboxHandler(handler, Assets.depressionFont, message44, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler45 = new TextboxHandler(handler, Assets.acceptanceFont, message45, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, Assets.acceptanceText, 50, true, false);
        textboxHandler46 = new TextboxHandler(handler, Assets.playerSpeakingFont, message46, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayer, Assets.playerText, 50, true, false);
        textboxHandler47 = new TextboxHandler(handler, Assets.bargainingFont, message47, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxBargaining, Assets.bargainingText, 50, true, false);
        textboxHandler48 = new TextboxHandler(handler, Assets.angerFont, message48, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAnger, Assets.angerText, 50, true, false);
        textboxHandler49 = new TextboxHandler(handler, Assets.depressionFont, message49, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler50 = new TextboxHandler(handler, Assets.acceptanceFont, message50, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, Assets.acceptanceText, 50, true, false);
        textboxHandler51 = new TextboxHandler(handler, Assets.depressionFont, message51, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler52 = new TextboxHandler(handler, Assets.bargainingFont, message52, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxBargaining, Assets.bargainingText, 50, true, false);
        textboxHandler53 = new TextboxHandler(handler, Assets.depressionFont, message53, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler54 = new TextboxHandler(handler, Assets.acceptanceFont, message54, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, Assets.acceptanceText, 50, true, false);
        textboxHandler55 = new TextboxHandler(handler, Assets.denialFont, message55, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDenial, Assets.denialText, 50, true, false);
        textboxHandler56 = new TextboxHandler(handler, Assets.depressionFont, message56, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxDepression, Assets.depressionText, 50, true, false);
        textboxHandler57 = new TextboxHandler(handler, Assets.acceptanceFont, message57, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, Assets.acceptanceText, 50, true, false);
        textboxHandler58 = new TextboxHandler(handler, Assets.playerSpeakingFont, message58, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayer, Assets.playerText, 50, true, false);
    }

    @Override
    public void tick() {
        if (firstTime) {
            denial = (Denial) handler.getWorldManager().getWorld(WorldManager.SCHOOL_1_ID).getEntityManager().getEntityByUid("denial-school1");
            anger = (Anger) handler.getWorldManager().getWorld(WorldManager.SCHOOL_1_ID).getEntityManager().getEntityByUid("anger-school1");
            bargaining = (Bargaining) handler.getWorldManager().getWorld(WorldManager.SCHOOL_1_ID).getEntityManager().getEntityByUid("bargaining-school1");
            depression = (Depression) handler.getWorldManager().getWorld(WorldManager.SCHOOL_1_ID).getEntityManager().getEntityByUid("depression-school1");
            acceptance = new Acceptance(handler, 15 * Tile.TILEWIDTH, 3.5f * Tile.TILEHEIGHT, "acceptance-school1");
            handler.getActiveWorld().getEntityManager().addEntity(acceptance);
            player = handler.getPlayer();
            acceptance.setDirection("down");
            firstTime = false;
        }
        if (!initialSetupFinished) {
            if (!showTextboxOne) {
                if (acceptance.getY() < player.getY()) {
                    acceptance.setyMove(acceptance.getSpeed());
                } else {
                    acceptance.setyMove(0);
                    if (acceptance.getX() < player.getX() - Tile.TILEWIDTH) {
                        acceptance.setxMove(acceptance.getSpeed());
                    } else {
                        acceptance.setxMove(0);
                        showTextboxOne = true;
                    }
                }
            }
            if (showTextboxOne) {
                if (!textboxHandler1.isFinished()) {
                    textboxHandler1.tick();
                } else {
                    if (acceptance.getY() < acceptanceYFinal && Math.abs(acceptance.getY() - acceptanceYFinal) > acceptance.getSpeed()) {
                        acceptance.setyMove(acceptance.getSpeed());
                    } else if (acceptance.getY() > acceptanceYFinal && Math.abs(acceptance.getY() - acceptanceYFinal) > acceptance.getSpeed()) {
                        acceptance.setyMove(-acceptance.getSpeed());
                    } else {
                        acceptance.setyMove(0);
                        if (acceptance.getX() > acceptanceXFinal && Math.abs(acceptance.getX() - acceptanceXFinal) > acceptance.getSpeed()) {
                            acceptance.setxMove(-acceptance.getSpeed());
                        } else {
                            acceptance.setxMove(0);
                        }
                        if (player.getY() < playerYFinal && Math.abs(player.getY() - playerYFinal) > player.getSpeed()) {
                            player.setyMove(player.getSpeed());
                        } else if (player.getY() > playerYFinal && Math.abs(player.getY() - playerYFinal) > player.getSpeed()) {
                            player.setyMove(-player.getSpeed());
                        } else {
                            player.setyMove(0);
                            if (player.getX() > playerXFinal && Math.abs(player.getX() - playerXFinal) > player.getSpeed()) {
                                player.setxMove(-player.getSpeed());
                            } else {
                                player.setxMove(0);
                                showTextboxOne = false;
                                initialSetupFinished = true;
                                textbox2 = true;
                            }
                        }
                    }
                }
            }
        } else {
            //now we just scroll through the textboxes
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
            if (textbox17) {
                if (!textboxHandler17.isFinished()) {
                    textboxHandler17.tick();
                }
            }
            if (textbox18) {
                if (!textboxHandler18.isFinished()) {
                    textboxHandler18.tick();
                }
            }
            if (textbox19) {
                if (!textboxHandler19.isFinished()) {
                    textboxHandler19.tick();
                }
            }
            if (textbox20) {
                if (!textboxHandler20.isFinished()) {
                    textboxHandler20.tick();
                }
            }
            if (textbox21) {
                if (!textboxHandler21.isFinished()) {
                    textboxHandler21.tick();
                }
            }
            if (textbox22) {
                if (!textboxHandler22.isFinished()) {
                    textboxHandler22.tick();
                }
            }
            if (textbox23) {
                if (!textboxHandler23.isFinished()) {
                    textboxHandler23.tick();
                }
            }
            if (textbox24) {
                if (!textboxHandler24.isFinished()) {
                    textboxHandler24.tick();
                }
            }
            if (textbox25) {
                if (!textboxHandler25.isFinished()) {
                    textboxHandler25.tick();
                }
            }
            if (textbox26) {
                if (!textboxHandler26.isFinished()) {
                    textboxHandler26.tick();
                }
            }
            if (textbox27) {
                if (!textboxHandler27.isFinished()) {
                    textboxHandler27.tick();
                }
            }
            if (textbox28) {
                if (!textboxHandler28.isFinished()) {
                    textboxHandler28.tick();
                }
            }
            if (textbox29) {
                if (!textboxHandler29.isFinished()) {
                    textboxHandler29.tick();
                }
            }
            if (textbox30) {
                if (!textboxHandler2.isFinished()) {
                    textboxHandler2.tick();
                }
            }
            if (textbox30) {
                if (!textboxHandler30.isFinished()) {
                    textboxHandler30.tick();
                }
            }
            if (textbox31) {
                if (!textboxHandler31.isFinished()) {
                    textboxHandler31.tick();
                }
            }
            if (textbox32) {
                if (!textboxHandler32.isFinished()) {
                    textboxHandler32.tick();
                }
            }
            if (textbox33) {
                if (!textboxHandler33.isFinished()) {
                    textboxHandler33.tick();
                }
            }
            if (textbox34) {
                if (!textboxHandler34.isFinished()) {
                    textboxHandler34.tick();
                }
            }
            if (textbox35) {
                if (!textboxHandler35.isFinished()) {
                    textboxHandler35.tick();
                }
            }
            if (textbox36) {
                if (!textboxHandler36.isFinished()) {
                    textboxHandler36.tick();
                }
            }
            if (textbox37) {
                if (!textboxHandler37.isFinished()) {
                    textboxHandler37.tick();
                }
            }
            if (textbox38) {
                if (!textboxHandler38.isFinished()) {
                    textboxHandler38.tick();
                }
            }
            if (textbox39) {
                if (!textboxHandler39.isFinished()) {
                    textboxHandler39.tick();
                }
            }
            if (textbox40) {
                if (!textboxHandler40.isFinished()) {
                    textboxHandler40.tick();
                }
            }
            if (textbox41) {
                if (!textboxHandler41.isFinished()) {
                    textboxHandler41.tick();
                }
            }
            if (textbox42) {
                if (!textboxHandler42.isFinished()) {
                    textboxHandler42.tick();
                }
            }
            if (textbox43) {
                if (!textboxHandler43.isFinished()) {
                    textboxHandler43.tick();
                }
            }
            if (textbox44) {
                if (!textboxHandler44.isFinished()) {
                    textboxHandler44.tick();
                }
            }
            if (textbox45) {
                if (!textboxHandler45.isFinished()) {
                    textboxHandler45.tick();
                }
            }
            if (textbox46) {
                if (!textboxHandler46.isFinished()) {
                    textboxHandler46.tick();
                }
            }
            if (textbox47) {
                if (!textboxHandler47.isFinished()) {
                    textboxHandler47.tick();
                }
            }
            if (textbox48) {
                if (!textboxHandler48.isFinished()) {
                    textboxHandler48.tick();
                }
            }
            if (textbox49) {
                if (!textboxHandler49.isFinished()) {
                    textboxHandler49.tick();
                }
            }
            if (textbox50) {
                if (!textboxHandler50.isFinished()) {
                    textboxHandler50.tick();
                }
            }
            if (textbox51) {
                if (!textboxHandler51.isFinished()) {
                    textboxHandler51.tick();
                }
            }
            if (textbox52) {
                if (!textboxHandler52.isFinished()) {
                    textboxHandler52.tick();
                }
            }
            if (textbox53) {
                if (!textboxHandler53.isFinished()) {
                    textboxHandler53.tick();
                }
            }
            if (textbox54) {
                if (!textboxHandler54.isFinished()) {
                    textboxHandler54.tick();
                }
            }
            if (textbox55) {
                if (!textboxHandler55.isFinished()) {
                    textboxHandler55.tick();
                }
            }
            if (textbox56) {
                if (!textboxHandler56.isFinished()) {
                    textboxHandler56.tick();
                }
            }
            if (textbox57) {
                if (!textboxHandler57.isFinished()) {
                    textboxHandler57.tick();
                }
            }
            if (textbox58) {
                if (!textboxHandler58.isFinished()) {
                    textboxHandler58.tick();
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (showTextboxOne && !textboxHandler1.isFinished()) {
            textboxHandler1.render(g);
        }
        if (textbox2) {
            if (!textboxHandler2.isFinished()) {
                textboxHandler2.render(g);
            } else {
                textbox3 = true;
                textbox2 = false;
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
                textbox17 = true;
                textbox16 = false;
            }
        }
        if (textbox17) {
            if (!textboxHandler17.isFinished()) {
                textboxHandler17.render(g);
            } else {
                textbox18 = true;
                textbox17 = false;
            }
        }
        if (textbox18) {
            if (!textboxHandler18.isFinished()) {
                textboxHandler18.render(g);
            } else {
                textbox19 = true;
                textbox18 = false;
            }
        }
        if (textbox19) {
            if (!textboxHandler19.isFinished()) {
                textboxHandler19.render(g);
            } else {
                textbox20 = true;
                textbox19 = false;
            }
        }
        if (textbox20) {
            if (!textboxHandler20.isFinished()) {
                textboxHandler20.render(g);
            } else {
                textbox21 = true;
                textbox20 = false;
            }
        }
        if (textbox21) {
            if (!textboxHandler21.isFinished()) {
                textboxHandler21.render(g);
            } else {
                textbox22 = true;
                textbox21 = false;
            }
        }
        if (textbox22) {
            if (!textboxHandler22.isFinished()) {
                textboxHandler22.render(g);
            } else {
                textbox23 = true;
                textbox22 = false;
            }
        }
        if (textbox23) {
            if (!textboxHandler23.isFinished()) {
                textboxHandler23.render(g);
            } else {
                textbox24 = true;
                textbox23 = false;
            }
        }
        if (textbox24) {
            if (!textboxHandler24.isFinished()) {
                textboxHandler24.render(g);
            } else {
                textbox25 = true;
                textbox24 = false;
            }
        }
        if (textbox25) {
            if (!textboxHandler25.isFinished()) {
                textboxHandler25.render(g);
            } else {
                textbox26 = true;
                textbox25 = false;
            }
        }
        if (textbox26) {
            if (!textboxHandler26.isFinished()) {
                textboxHandler26.render(g);
            } else {
                textbox27 = true;
                textbox26 = false;
            }
        }
        if (textbox27) {
            if (!textboxHandler27.isFinished()) {
                textboxHandler27.render(g);
            } else {
                textbox28 = true;
                textbox27 = false;
            }
        }
        if (textbox28) {
            if (!textboxHandler28.isFinished()) {
                textboxHandler28.render(g);
            } else {
                textbox29 = true;
                textbox28 = false;
            }
        }
        if (textbox29) {
            if (!textboxHandler29.isFinished()) {
                textboxHandler29.render(g);
            } else {
                textbox30 = true;
                textbox29 = false;
            }
        }
        if (textbox30) {
            if (!textboxHandler30.isFinished()) {
                textboxHandler30.render(g);
            } else {
                textbox31 = true;
                textbox30 = false;
            }
        }
        if (textbox31) {
            if (!textboxHandler31.isFinished()) {
                textboxHandler31.render(g);
            } else {
                textbox32 = true;
                textbox31 = false;
            }
        }
        if (textbox32) {
            if (!textboxHandler32.isFinished()) {
                textboxHandler32.render(g);
            } else {
                textbox33 = true;
                textbox32 = false;
            }
        }
        if (textbox33) {
            if (!textboxHandler33.isFinished()) {
                textboxHandler33.render(g);
            } else {
                textbox34 = true;
                textbox33 = false;
            }
        }
        if (textbox34) {
            if (!textboxHandler34.isFinished()) {
                textboxHandler34.render(g);
            } else {
                textbox35 = true;
                textbox34 = false;
            }
        }
        if (textbox35) {
            if (!textboxHandler35.isFinished()) {
                textboxHandler35.render(g);
            } else {
                textbox36 = true;
                textbox35 = false;
            }
        }
        if (textbox36) {
            if (!textboxHandler36.isFinished()) {
                textboxHandler36.render(g);
            } else {
                textbox37 = true;
                textbox36 = false;
            }
        }
        if (textbox37) {
            if (!textboxHandler37.isFinished()) {
                textboxHandler37.render(g);
            } else {
                textbox38 = true;
                textbox37 = false;
            }
        }
        if (textbox38) {
            if (!textboxHandler38.isFinished()) {
                textboxHandler38.render(g);
            } else {
                textbox39 = true;
                textbox38 = false;
            }
        }
        if (textbox39) {
            if (!textboxHandler39.isFinished()) {
                textboxHandler39.render(g);
            } else {
                textbox40 = true;
                textbox39 = false;
            }
        }
        if (textbox40) {
            if (!textboxHandler40.isFinished()) {
                textboxHandler40.render(g);
            } else {
                textbox41 = true;
                textbox40 = false;
            }
        }
        if (textbox41) {
            if (!textboxHandler41.isFinished()) {
                textboxHandler41.render(g);
            } else {
                textbox42 = true;
                textbox41 = false;
            }
        }
        if (textbox42) {
            if (!textboxHandler42.isFinished()) {
                textboxHandler42.render(g);
            } else {
                textbox43 = true;
                textbox42 = false;
            }
        }
        if (textbox43) {
            if (!textboxHandler43.isFinished()) {
                textboxHandler43.render(g);
            } else {
                textbox44 = true;
                textbox43 = false;
            }
        }
        if (textbox44) {
            if (!textboxHandler44.isFinished()) {
                textboxHandler44.render(g);
            } else {
                textbox45 = true;
                textbox44 = false;
            }
        }
        if (textbox45) {
            if (!textboxHandler45.isFinished()) {
                textboxHandler45.render(g);
            } else {
                textbox46 = true;
                textbox45 = false;
            }
        }
        if (textbox46) {
            if (!textboxHandler46.isFinished()) {
                textboxHandler46.render(g);
            } else {
                textbox47 = true;
                textbox46 = false;
            }
        }
        if (textbox47) {
            if (!textboxHandler47.isFinished()) {
                textboxHandler47.render(g);
            } else {
                textbox48 = true;
                textbox47 = false;
            }
        }
        if (textbox48) {
            if (!textboxHandler48.isFinished()) {
                textboxHandler48.render(g);
            } else {
                textbox49 = true;
                textbox48 = false;
            }
        }
        if (textbox49) {
            if (!textboxHandler49.isFinished()) {
                textboxHandler49.render(g);
            } else {
                textbox50 = true;
                textbox49 = false;
            }
        }
        if (textbox50) {
            if (!textboxHandler50.isFinished()) {
                textboxHandler50.render(g);
            } else {
                textbox51 = true;
                textbox50 = false;
            }
        }
        if (textbox51) {
            if (!textboxHandler51.isFinished()) {
                textboxHandler51.render(g);
            } else {
                textbox52 = true;
                textbox51 = false;
            }
        }
        if (textbox52) {
            if (!textboxHandler52.isFinished()) {
                textboxHandler52.render(g);
            } else {
                textbox53 = true;
                textbox52 = false;
            }
        }
        if (textbox53) {
            if (!textboxHandler53.isFinished()) {
                textboxHandler53.render(g);
            } else {
                textbox54 = true;
                textbox53 = false;
            }
        }
        if (textbox54) {
            if (!textboxHandler54.isFinished()) {
                textboxHandler54.render(g);
            } else {
                textbox55 = true;
                textbox54 = false;
            }
        }
        if (textbox55) {
            if (!textboxHandler55.isFinished()) {
                textboxHandler55.render(g);
            } else {
                textbox56 = true;
                textbox55 = false;
            }
        }
        if (textbox56) {
            if (!textboxHandler56.isFinished()) {
                textboxHandler56.render(g);
            } else {
                textbox57 = true;
                textbox56 = false;
            }
        }
        if (textbox57) {
            if (!textboxHandler57.isFinished()) {
                textboxHandler57.render(g);
            } else {
                textbox58 = true;
                textbox57 = false;
            }
        }
        if (textbox58) {
            if (!textboxHandler58.isFinished()) {
                textboxHandler58.render(g);
            } else {
                textbox58 = false;
                exit();
            }
        }
    }

    private void exit() {
        handler.setPlayerFrozen(false);
        handler.getCutsceneManager().setActiveCutscene(null);
        handler.getFlags().setCutsceneActive(false);
        handler.getFlags().setSchoolCutscene1(false);
        //handler.getActiveWorld().getEntityManager().removeEntity(acceptance);
    }
}
