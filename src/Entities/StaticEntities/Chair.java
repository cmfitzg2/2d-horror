package Entities.StaticEntities;

import Variables.Handler;
import java.awt.*;
import java.awt.image.BufferedImage;
import Graphics.Assets;

public class Chair extends StaticEntity {

    private String dir;

    public Chair(Handler handler, float x, float y, int width, int height, String uniqueName, String dir) {
        super(handler, x, y, width, height, uniqueName);
        bounds.x = 0;
        bounds.width = width;
        bounds.height = height - 25;
        if (dir.equals("Left") || dir.equals("Right") || dir.equals("Down")) {
            bounds.y = 20;
        } else if (dir.equals("Up")) {
            bounds.y = 15;
        }
        this.dir = dir;
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
        switch (dir) {
            case "Down":
                chair = Assets.chairOneDown;
                break;
            case "Up":
                chair = Assets.chairOneUp;
                break;
            case "Left":
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
    public void interactedWith() {

    }

    @Override
    public boolean isInteracting() {
        return isInteracting;
    }
}
