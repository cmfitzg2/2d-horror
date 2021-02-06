package Entities.StaticEntities;

import Graphics.Assets;
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
    private boolean occupied = false;

    public StudentDesk(Handler handler, float x, float y, int width, int height, String uniqueName, String character) {
        super(handler, x, y, width, height, uniqueName);
        this.character = character;
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

    }

    @Override
    public void finalRender(Graphics g) {

    }

    @Override
    public void tick() {

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

    @Override
    public void die() {
        handler.getActiveWorld().getEntityManager().removeEntity(this);
    }

    @Override
    public void interactedWith() {
        setOccupied(!occupied);
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
