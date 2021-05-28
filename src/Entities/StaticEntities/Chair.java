package Entities.StaticEntities;

import Variables.Handler;
import java.awt.*;
import java.awt.image.BufferedImage;
import Graphics.Assets;

public class Chair extends StaticEntity {

    private int style;
    public static final int STYLE_UP = 0, STYLE_DOWN = 1, STYLE_LEFT = 2, STYLE_RIGHT = 3;
    public Chair(Handler handler, float x, float y, int width, int height, String uniqueName, int style) {
        super(handler, x, y, width, height, uniqueName);
        this.style = style;
        bounds.x = 0;
        bounds.width = width;
        bounds.height = height - 25;
        if (style == STYLE_LEFT || style == STYLE_RIGHT || style == STYLE_DOWN) {
            bounds.y = 20;
        } else if (style == STYLE_UP) {
            bounds.y = 15;
        }
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
        BufferedImage chair;
        switch (style) {
            case STYLE_DOWN:
                chair = Assets.chairOneDown;
                break;
            case STYLE_UP:
                chair = Assets.chairOneUp;
                break;
            case STYLE_LEFT:
                chair = Assets.chairOneLeft;
                break;
            default:
                chair = Assets.chairOneRight;
                break;
        }
        g.drawImage(chair, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {
        handler.getActiveWorld().getEntityManager().removeEntity(this);
    }

    @Override
    public boolean interactedWith() {
        return false;
    }

    @Override
    public boolean isInteracting() {
        return isInteracting;
    }

    @Override
    public boolean itemInteraction(String item) {
        return false;
    }
}
