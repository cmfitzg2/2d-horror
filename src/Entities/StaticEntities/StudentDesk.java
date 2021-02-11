package Entities.StaticEntities;

import Graphics.Assets;
import Textboxes.TextboxHandler;
import Variables.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class StudentDesk extends StaticEntity {

    public static final String DENIAL = "denial";
    public static final String ANGER = "anger";
    public static final String BARGAINING = "bargaining";
    public static final String DEPRESSION = "depression";
    public static final String ACCEPTANCE = "acceptance";
    public static final String PLAYER = "player";

    private String character;
    private boolean occupied;
    TextboxHandler textboxHandler;

    public StudentDesk(Handler handler, float x, float y, int width, int height, String uniqueName, String character, boolean occupied) {
        super(handler, x, y, width, height, uniqueName);
        this.character = character;
        this.occupied = occupied;
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height - height / 6;
    }

    @Override
    public void preRender(Graphics g) {

    }

    @Override
    public void postRender(Graphics g) {
        if (null != textboxHandler) {
            textboxHandler.render(g);
        }
    }

    @Override
    public void finalRender(Graphics g) {

    }

    @Override
    public void tick() {
        if (null != textboxHandler) {
            textboxHandler.tick();
            if (textboxHandler.isFinished()) {
                textboxCallback(textboxHandler.getOptionSelected());
            }
        }
    }

    @Override
    public void render(Graphics g) {
        BufferedImage image;
        if (occupied) {
            switch (character) {
                case DENIAL:
                    image = Assets.denialDesk;
                    break;
                case ANGER:
                    image = Assets.angerDesk;
                    break;
                case BARGAINING:
                    image = Assets.bargainingDesk;
                    break;
                case DEPRESSION:
                    image = Assets.depressionDesk;
                    break;
                case ACCEPTANCE:
                    image = Assets.acceptanceDesk;
                    break;
                case PLAYER:
                    image = Assets.playerDesk;
                    break;
                default:
                    image = Assets.emptyDesk;
            }
        } else {
            image = Assets.emptyDesk;
        }
        g.drawImage(image, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    private void textboxCallback(String option) {
        if (option.equals("Yes")) {
            occupied = true;
            handler.getPlayer().setInvisible(true);
            handler.getPlayer().setX(x);
            handler.getPlayer().setY(y);
        } else {
            handler.setPlayerFrozen(false);
            System.out.println(option);
        }
        textboxHandler = null;
    }

    @Override
    public void die() {
        handler.getActiveWorld().getEntityManager().removeEntity(this);
    }

    @Override
    public void interactedWith() {
        if (character.equals(PLAYER) && handler.getFlags().isClassroomCutscene1() && !occupied) {
            handler.setPlayerFrozen(true);
            textboxHandler = new TextboxHandler(handler, Assets.textboxFontDefault, "Sit down?", new String[]{"Yes", "No"}, 2, Color.WHITE, null, Assets.textboxDefault, null, 50, true, false);
        }
    }

    @Override
    public boolean isInteracting() {
        return isInteracting;
    }

    @Override
    public boolean itemInteraction(String item) {
        return false;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
