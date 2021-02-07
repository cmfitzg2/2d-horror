package Cutscenes;

import Entities.Creatures.*;
import Graphics.Assets;
import Input.KeyManager;
import Textboxes.TextboxHandler;
import Tiles.Tile;
import Variables.Handler;

import java.awt.*;

public class SchoolCutscene1 implements Cutscene {
    Player player;
    private boolean firstTime = true;
    private Handler handler;
    private TextboxHandler textboxHandler1;
    private KeyManager keyManager;
    private Denial denial;
    private Acceptance acceptance;
    private final String messageOne = "There you are! \r We're a little early, but everyone's already here.";
    private boolean showTextboxOne = false, initialSetupFinished = false;
    private final int acceptanceXFinal = 790, acceptanceYFinal = 550, playerXFinal = 790, playerYFinal = 600;


    public SchoolCutscene1(Handler handler) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
        textboxHandler1 = new TextboxHandler(handler, Assets.acceptanceFont, messageOne, null, 3, Color.WHITE, null, Assets.textboxAcceptance, null, 50, true, false);
    }

    @Override
    public void tick() {
        if (firstTime) {
            acceptance = new Acceptance(handler, 15 * Tile.TILEWIDTH, 3.5f * Tile.TILEHEIGHT, "acceptance-school1");
            handler.getActiveWorld().getEntityManager().addEntity(acceptance);
            player = handler.getPlayer();
            acceptance.setDirection("down");
            firstTime = false;
        }

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
                        }
                    }
                }
            }
        }
        if (initialSetupFinished) {
            //now we just scroll through the textboxes
        }
    }

    @Override
    public void render(Graphics g) {
        if (showTextboxOne && !textboxHandler1.isFinished()) {
            textboxHandler1.render(g);
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
