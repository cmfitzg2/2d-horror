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

public class AcceptanceEncounter2 implements Cutscene {
    Player player;
    private boolean firstTime = true;
    private Handler handler;
    private TextboxHandler textboxHandler1;
    private KeyManager keyManager;
    private int messageNum = 1;
    private Acceptance acceptance;
    private final String messageOne = "There you are! \r We're a little early, but everyone's already in there. \n Let's go.";

    public AcceptanceEncounter2(Handler handler) {
        this.handler = handler;
        keyManager = handler.getKeyManager();
        textboxHandler1 = new TextboxHandler(handler, Assets.acceptanceFont, messageOne, null, 3, Color.WHITE, null, null, 50, true, false);
    }

    @Override
    public void tick() {
        if (firstTime) {
            acceptance = (Acceptance) handler.getWorldManager().getWorld(WorldManager.SCHOOL_1_ID).getEntityManager().getEntityByUid("acceptance-school1");
            player = handler.getPlayer();
            acceptance.setDirection("right");
        }
        if (!textboxHandler1.isFinished()) {
            textboxHandler1.tick();
        } else {
            //move acceptance to the classroom
            if (acceptance.getX() > 6 * Tile.TILEWIDTH) {
                acceptance.setxMove(-acceptance.getSpeed());
            } else if (acceptance.getY() < 10.5 * Tile.TILEHEIGHT) {
                acceptance.setyMove(acceptance.getSpeed());
            } else {
                handler.getActiveWorld().getEntityManager().removeEntity(acceptance);
                exit();
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (!textboxHandler1.isFinished()) {
            textboxHandler1.render(g);
        }
    }

    private void exit() {
        handler.setPlayerFrozen(false);
        handler.getCutsceneManager().setActiveCutscene(null);
        handler.getFlags().setCutsceneActive(false);
        handler.getFlags().setAcceptanceEncounter2(false);
        handler.getActiveWorld().getEntityManager().removeEntity(acceptance);
    }
}
