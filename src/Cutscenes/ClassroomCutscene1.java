package Cutscenes;

import Entities.Creatures.*;
import Entities.EntityManager;
import Entities.StaticEntities.*;
import Graphics.Assets;
import Input.KeyManager;
import Textboxes.TextboxHandler;
import Tiles.Tile;
import Variables.GeneralConstants;
import Variables.Handler;
import Worlds.WorldManager;

import java.awt.*;

public class ClassroomCutscene1 implements Cutscene {
    private Player player;
    private boolean firstTime = true, bellPlayed = false, friendsAdded;
    private Handler handler;
    private TextboxHandler textboxHandler1, textboxHandler2, textboxHandler3, textboxHandler4, textboxHandler5,
            textboxHandler6, textboxHandler7, textboxHandler8, textboxHandler9, textboxHandler10, textboxHandler11,
            textboxHandler12;
    private KeyManager keyManager;
    private boolean showTextbox1 = false, textbox1 = true, textbox2, textbox3, textbox4, textbox5, textbox6, textbox7,
            textbox8, textbox9, textbox10, textbox11, textbox12;
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
    private final float exitX = 23 * Tile.TILEWIDTH, exitY = 4 * Tile.TILEHEIGHT;
    private final String message1 = "Alright, let's get started. We're going to pick up where we left off yesterday. \r " +
            "Logarithms are pretty simple if you understand exponents. They're really just a different way ... \r " +
            "...................... \n ...................... \n ......................",
            message2 = "... \r " +
                    "...Huh? \r " +
                    "It's that same feeling again as this morning. Did I fall asleep?",
            message3 = "...will go over Euler's constant as it relates to logarithms on Monday. \r " +
                    "Until then, have a safe and fun weekend.",
            message4 = "..That's not good. Did I really sleep through the entire lesson? \r " +
                    "Well, I'm definitely screwed in this class. But I guess that's one way to make it to the end of the day. \r " +
                    "Still though, why am I so exhausted? I don't feel any less tired than before.",
            message5 = "Seems like math didn't cheer you up. You look even worse than this morning.",
            message6 = "Yeah, I ended up sleeping through the whole class.",
            message7 = "That's not like you. You still up for tonight?",
            message8 = "For sure. The last thing I want is to do nothing while I'm feeling like this. \r " +
                    "But I think I'm gonna head home for the day. I don't see any reason to stay here if I can't even stay awake for class.",
            message9 = "Agreed, I think that's the right call. \r You want to pick us up from school?",
            message10 = "No, I don't think that's a good idea. I don't think I'm in any shape to drive.",
            message11 = "Yeah, good point. We'll swing by your place tonight then.",
            message12 = "Sounds good. See you then.";

    public ClassroomCutscene1(Handler handler) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
        textboxHandler1 = new TextboxHandler(handler, Assets.teacherFont, message1, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxTeacher, null, 50, true, false);
        textboxHandler2 = new TextboxHandler(handler, Assets.playerThinkingFont, message2, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayerThinking, null, 50, true, false);
        textboxHandler3 = new TextboxHandler(handler, Assets.teacherFont, message3, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxTeacher, null, 50, true, false);
        textboxHandler4 = new TextboxHandler(handler, Assets.playerThinkingFont, message4, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayerThinking, null, 50, true, false);
        textboxHandler5 = new TextboxHandler(handler, Assets.acceptanceFont, message5, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, null, 50, true, false);
        textboxHandler6 = new TextboxHandler(handler, Assets.playerSpeakingFont, message6, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayer, null, 50, true, false);
        textboxHandler7 = new TextboxHandler(handler, Assets.acceptanceFont, message7, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, null, 50, true, false);
        textboxHandler8 = new TextboxHandler(handler, Assets.playerSpeakingFont, message8, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayer, null, 50, true, false);
        textboxHandler9 = new TextboxHandler(handler, Assets.acceptanceFont, message9, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, null, 50, true, false);
        textboxHandler10 = new TextboxHandler(handler, Assets.playerSpeakingFont, message10, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayer, null, 50, true, false);
        textboxHandler11 = new TextboxHandler(handler, Assets.acceptanceFont, message11, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxAcceptance, null, 50, true, false);
        textboxHandler12 = new TextboxHandler(handler, Assets.playerSpeakingFont, message12, null, GeneralConstants.defaultTextSpeed, Color.WHITE, null, Assets.textboxPlayer, null, 50, true, false);
    }

    @Override
    public void tick() {
        if (firstTime) {
            teacher = new Teacher(handler, exitX, exitY - Tile.TILEHEIGHT / 2f, "teacher-school1");
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
                        player.setX(playerDesk.getX() - playerDesk.getWidth());
                        player.setY(playerDesk.getY());
                        Door door = (Door) entityManager.getEntityByUid("door1-classroom1");
                        entityManager.addEntity(new Door(handler, door.getX(), door.getY(), door.getWidth(), door.getHeight(),
                                "door2-classroom1", handler.getWorldManager().getWorld(WorldManager.MC_HOUSE_1_ID),
                                700, 428, Door.PLAIN_WOOD, false, GeneralConstants.longLevelTransition, false));
                        entityManager.removeEntity(door);
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
                        if (teacher.getX() < exitX) {
                            teacher.setxMove(teacher.getSpeed());
                        } else {
                            entityManager.removeEntity(teacher);
                            teacher = null;
                            textbox3 = false;
                            textbox4 = true;
                        }
                    }
                }
            }
        }
        //this ones a little complicated, we want to scroll MC's thoughts as friends exit
        //and then move acceptance over for dialogue after *both* of those are done
        if (textbox4) {
            if (!textboxHandler4.isFinished()) {
                textboxHandler4.tick();
            } else if (null == depression) {
                if (null == acceptance) {
                    acceptance = new Acceptance(handler, acceptanceDesk.getX() + acceptanceDesk.getWidth(), acceptanceDesk.getY(), "acceptance-school1");
                    entityManager.addEntity(acceptance);
                    acceptanceDesk.setOccupied(false);
                } else {
                    if (acceptance.getX() < playerDesk.getX() - 2 * Tile.TILEWIDTH) {
                        acceptance.setxMove(acceptance.getSpeed());
                    } else {
                        acceptance.setxMove(0);
                        playerDesk.setOccupied(false);
                        player.setDirection("left");
                        player.setInvisible(false);
                        textbox5 = true;
                        textbox4 = false;
                    }
                }
            }
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
            } else {
                if (null != denial) {
                    if (denial.getX() < exitX) {
                        denial.setxMove(denial.getSpeed());
                    } else if (denial.getY() > exitY) {
                        denial.setxMove(0);
                        denial.setyMove(-denial.getSpeed());
                    } else {
                        entityManager.removeEntity(denial);
                        denial = null;
                    }
                }
                if (null != anger) {
                    if (anger.getY() > denialDesk.getY() - Tile.TILEHEIGHT) {
                        anger.setyMove(-anger.getSpeed());
                    } else if (anger.getX() < exitX) {
                        anger.setyMove(0);
                        anger.setxMove(anger.getSpeed());
                    } else if (anger.getY() > exitY) {
                        anger.setxMove(0);
                        anger.setyMove(-anger.getSpeed());
                    } else {
                        entityManager.removeEntity(anger);
                        anger = null;
                    }
                }
                if (null != bargaining) {
                    if (bargaining.getY() > denialDesk.getY() - Tile.TILEHEIGHT) {
                        bargaining.setyMove(-bargaining.getSpeed());
                    } else if (bargaining.getX() < exitX) {
                        bargaining.setyMove(0);
                        bargaining.setxMove(bargaining.getSpeed());
                    } else if (bargaining.getY() > exitY) {
                        bargaining.setxMove(0);
                        bargaining.setyMove(-bargaining.getSpeed());
                    } else {
                        entityManager.removeEntity(bargaining);
                        bargaining = null;
                    }
                }
                if (null != depression) {
                    if (depression.getY() > denialDesk.getY() - Tile.TILEHEIGHT) {
                        depression.setyMove(-depression.getSpeed());
                    } else if (depression.getX() < exitX) {
                        depression.setyMove(0);
                        depression.setxMove(depression.getSpeed());
                    } else if (depression.getY() > exitY) {
                        depression.setxMove(0);
                        depression.setyMove(-depression.getSpeed());
                    } else {
                        entityManager.removeEntity(depression);
                        depression = null;
                    }
                }
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
            } else {
                if (null != acceptance) {
                    if (acceptance.getY() > denialDesk.getY() - Tile.TILEHEIGHT) {
                        acceptance.setyMove(-acceptance.getSpeed());
                    } else if (acceptance.getX() < exitX) {
                        acceptance.setyMove(0);
                        acceptance.setxMove(acceptance.getSpeed());
                    } else if (acceptance.getY() > exitY) {
                        acceptance.setxMove(0);
                        acceptance.setyMove(-acceptance.getSpeed());
                    } else {
                        entityManager.removeEntity(acceptance);
                        acceptance = null;
                    }
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
                                textbox2 = false;
                                textbox3 = true;
                                handler.getGame().setFadeIn(false, true);
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
                }
            }
            if (textbox5) {
                if (!textboxHandler5.isFinished()) {
                    textboxHandler5.render(g);
                } else {
                    textbox5 = false;
                    textbox6 = true;
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
                if (!textboxHandler8.isFinished()) {
                    textboxHandler8.render(g);
                } else {
                    textbox8 = false;
                    textbox9 = true;
                }
            }
            if (textbox9) {
                if (!textboxHandler9.isFinished()) {
                    textboxHandler9.render(g);
                } else {
                    textbox9 = false;
                    textbox10 = true;
                }
            }
            if (textbox10) {
                if (!textboxHandler10.isFinished()) {
                    textboxHandler10.render(g);
                } else {
                    textbox10 = false;
                    textbox11 = true;
                }
            }
            if (textbox11) {
                if (!textboxHandler11.isFinished()) {
                    textboxHandler11.render(g);
                } else {
                    textbox11 = false;
                    textbox12 = true;
                }
            }
            if (textbox12) {
                if (!textboxHandler12.isFinished()) {
                    textboxHandler12.render(g);
                } else {
                    textbox9 = false;
                    textbox10 = true;
                    if (null == acceptance) {
                        textbox12 = false;
                        exit();
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
        handler.getFlags().setClassroomCutscene1(false);
        ((Bed) handler.getWorldManager().getWorld(WorldManager.MC_HOUSE_1_ID).getEntityManager()
                .getEntityByUid("bed1-mchouse1")).setFirstTime(true);
        EntityManager overworldManager = handler.getWorldManager().getWorld(WorldManager.OVERWORLD_1_ID).getEntityManager();
        overworldManager.addEntity(new Denial(handler, 41.5f * Tile.TILEWIDTH, 49.5f * Tile.TILEHEIGHT, "denial-overworld1"));
        overworldManager.addEntity(new Anger(handler, 41.5f * Tile.TILEWIDTH, 50.5f * Tile.TILEHEIGHT, "anger-overworld1"));
        overworldManager.addEntity(new Bargaining(handler, 41.5f * Tile.TILEWIDTH, 51.5f * Tile.TILEHEIGHT, "bargaining-overworld1"));
        overworldManager.addEntity(new Depression(handler, 46f * Tile.TILEWIDTH, 43 * Tile.TILEHEIGHT, "depression-overworld1"));
        Acceptance acceptance = new Acceptance(handler, 44.5f * Tile.TILEWIDTH, 49.5f * Tile.TILEHEIGHT, "acceptance-overworld1");
        overworldManager.addEntity(acceptance);
        acceptance.setDirection("left");
    }
}
