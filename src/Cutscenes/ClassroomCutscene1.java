package Cutscenes;

import Entities.Creatures.*;
import Entities.EntityManager;
import Entities.StaticEntities.Chalkboard;
import Entities.StaticEntities.StudentDesk;
import Entities.StaticEntities.TeacherDesk;
import Graphics.Assets;
import Input.KeyManager;
import Textboxes.TextboxHandler;
import Tiles.Tile;
import Variables.Handler;

import java.awt.*;

public class ClassroomCutscene1 implements Cutscene {
    private Player player;
    private boolean firstTime = true, bellPlayed = false, friendsAdded;
    private Handler handler;
    private TextboxHandler textboxHandler1, textboxHandler2, textboxHandler3, textboxHandler4, textboxHandler5,
            textboxHandler6, textboxHandler7, textboxHandler8, textboxHandler9;
    private KeyManager keyManager;
    private boolean showTextbox1 = false, textbox1 = true, textbox2, textbox3, textbox4, textbox5, textbox6, textbox7, textbox8, textbox9;
    private Teacher teacher;
    private Chalkboard chalkboard;
    private StudentDesk denialDesk, angerDesk, bargainingDesk, depressionDesk, acceptanceDesk, playerDesk;
    private TeacherDesk teacherDesk;
    private Denial denial;
    private Anger anger;
    private Bargaining bargaining;
    private Depression depression;
    private Acceptance acceptance;
    private EntityManager entityManager;
    private final String messageOne = "Alright, let's get started. We're going to pick up where we left off yesterday. \r " +
            "Logarithms are pretty simple if you understand exponents. They're really just a different way ... \r " +
            "...................... \n ...................... \n ......................",
            messageTwo = "... \r " +
                    "...Huh? \r " +
                    "It's that same feeling again as this morning. Did I fall asleep?",
            messageThree = "...will go over Euler's constant as it relates to logarithms on Monday. \r " +
                    "Until then, have a safe and fun weekend.",
            messageFour = "..That's not good. Did I really sleep through the entire lesson? \r " +
                    "Well, I'm definitely screwed in this class. But I guess that's one way to make it to the end of the day. \r " +
                    "Still though, why am I so exhausted? I don't feel any less tired than before.",
            messageFive = "Seems like math didn't cheer you up. You look even worse than this morning.",
            messageSix = "Yeah, I ended up sleeping through the whole class.",
            messageSeven = "That's not like you. You still up for tonight?",
            messageEight = "For sure. The last thing I want to do is just go home feeling like this.",
            messageNine = "Great. We'll meet you outside.";

    public ClassroomCutscene1(Handler handler) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
        textboxHandler1 = new TextboxHandler(handler, Assets.teacherFont, messageOne, null, 3, Color.WHITE, null, Assets.textboxTeacher, null, 50, true, false);
        textboxHandler2 = new TextboxHandler(handler, Assets.playerThinkingFont, messageTwo, null, 3, Color.WHITE, null, Assets.textboxPlayerThinking, null, 50, true, false);
        textboxHandler3 = new TextboxHandler(handler, Assets.teacherFont, messageThree, null, 3, Color.WHITE, null, Assets.textboxTeacher, null, 50, true, false);
        textboxHandler4 = new TextboxHandler(handler, Assets.playerThinkingFont, messageFour, null, 3, Color.WHITE, null, Assets.textboxPlayerThinking, null, 50, true, false);
        textboxHandler5 = new TextboxHandler(handler, Assets.acceptanceFont, messageFive, null, 3, Color.WHITE, null, Assets.textboxAcceptance, null, 50, true, false);
        textboxHandler6 = new TextboxHandler(handler, Assets.playerSpeakingFont, messageSix, null, 3, Color.WHITE, null, Assets.textboxPlayer, null, 50, true, false);
        textboxHandler7 = new TextboxHandler(handler, Assets.acceptanceFont, messageSeven, null, 3, Color.WHITE, null, Assets.textboxAcceptance, null, 50, true, false);
        textboxHandler8 = new TextboxHandler(handler, Assets.playerSpeakingFont, messageEight, null, 3, Color.WHITE, null, Assets.textboxPlayer, null, 50, true, false);
        textboxHandler9 = new TextboxHandler(handler, Assets.acceptanceFont, messageNine, null, 3, Color.WHITE, null, Assets.textboxAcceptance, null, 50, true, false);
    }

    @Override
    public void tick() {
        if (firstTime) {
            teacher = new Teacher(handler, 23 * Tile.TILEWIDTH, 3.5f * Tile.TILEHEIGHT, "teacher-school1");
            entityManager = handler.getActiveWorld().getEntityManager();
            entityManager.addEntity(teacher);
            chalkboard = (Chalkboard) entityManager.getEntityByUid("chalkboard-classroom1");
            teacherDesk = (TeacherDesk) entityManager.getEntityByUid("teacherdesk-classroom1");
            denialDesk = (StudentDesk) entityManager.getEntityByUid("denialdesk-classroom1");
            angerDesk = (StudentDesk) entityManager.getEntityByUid("angerdesk-classroom1");
            bargainingDesk = (StudentDesk) entityManager.getEntityByUid("bargainingdesk-classroom1");
            depressionDesk = (StudentDesk) entityManager.getEntityByUid("depressiondesk-classroom1");
            acceptanceDesk = (StudentDesk) entityManager.getEntityByUid("acceptancedesk-classroom1");
            playerDesk = (StudentDesk) entityManager.getEntityByUid("playerdesk-classroom1");
            player = handler.getPlayer();
            teacher.setDirection("down");
            firstTime = false;
        }
        if (!showTextbox1) {
            if (teacher.getX() > teacherDesk.getX() + teacherDesk.getWidth() / 2f - teacher.getWidth() / 2f) {
                teacher.setxMove(-teacher.getSpeed());
            } else {
                teacher.setDirection("down");
                teacher.setxMove(0);
                showTextbox1 = true;
                textbox1 = true;
            }
        } else {
            if (textbox1) {
                if (!textboxHandler1.isFinished()) {
                    textboxHandler1.tick();
                } else {
                    if (!handler.getGame().isFadeOut()) {
                        handler.getGame().fadeOut(255);
                    } else if (handler.getGame().isFinishedFadingOut()) {
                        chalkboard.setType(1);
                        teacher.setX(chalkboard.getX() + chalkboard.getWidth());
                        textbox2 = true;
                        textbox1 = false;
                    }
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
                } else {
                    if (teacher != null) {
                        if (teacher.getX() < 23 * Tile.TILEWIDTH) {
                            teacher.setxMove(teacher.getSpeed());
                        } else {
                            entityManager.removeEntity(teacher);
                            teacher = null;
                        }
                    } else {
                        if (!friendsAdded) {
                            friendsAdded = true;
                            denial = new Denial(handler, denialDesk.getX() + denialDesk.getWidth(), denialDesk.getY(), "denial-school1");
                            entityManager.addEntity(denial);
                            denialDesk.setOccupied(false);
                            anger = new Anger(handler, angerDesk.getX() + angerDesk.getWidth(), angerDesk.getY(), "anger-school1");
                            entityManager.addEntity(anger);
                            angerDesk.setOccupied(false);
                            bargaining = new Bargaining(handler, bargainingDesk.getX() + bargainingDesk.getWidth(), bargainingDesk.getY(), "bargaining-school1");
                            entityManager.addEntity(bargaining);
                            bargainingDesk.setOccupied(false);
                            depression = new Depression(handler, depressionDesk.getX() + depressionDesk.getWidth(), depressionDesk.getY(), "depression-school1");
                            entityManager.addEntity(depression);
                            depressionDesk.setOccupied(false);
                        }
                    }
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
        }
    }

    @Override
    public void render(Graphics g) {
        if (showTextbox1) {
            if (textbox1) {
                if (!textboxHandler1.isFinished()) {
                    textboxHandler1.render(g);
                }
            }
            if (textbox2) {
                if (!textboxHandler2.isFinished()) {
                    handler.getScreenOverlay().overlayScreen(g, Color.black);
                    handler.getGame().setFadeOut(false, true);
                    textboxHandler2.render(g);
                } else {
                    if (!bellPlayed) {
                        Assets.schoolBell.start();
                        bellPlayed = true;
                        handler.getScreenOverlay().overlayScreen(g, Color.black);
                    } else {
                        if (!Assets.schoolBell.isActive()) {
                            if (!handler.getGame().isFadeIn()) {
                                handler.getGame().fadeIn(255);
                            } else if (handler.getGame().isFinishedFadingIn()) {
                                textbox3 = true;
                                textbox2 = false;
                            }
                        } else {
                            handler.getScreenOverlay().overlayScreen(g, Color.black);
                        }
                    }
                }
            }
            if (textbox3) {
                if (!textboxHandler3.isFinished()) {
                    textboxHandler3.render(g);
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
                    textbox9 = false;
                    exit();
                }
            }
        }
    }

    private void exit() {
        handler.setPlayerFrozen(false);
        handler.getCutsceneManager().setActiveCutscene(null);
        handler.getFlags().setCutsceneActive(false);
        handler.getFlags().setClassroomCutscene1(false);
    }
}