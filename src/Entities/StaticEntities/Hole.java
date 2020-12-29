package Entities.StaticEntities;

import Utils.GeneralUtils;
import Variables.Handler;
import Graphics.Assets;
import Worlds.World;

import java.awt.*;

public class Hole extends StaticEntity {

    private boolean intersected = false, justStartedFalling = true, broken = false;
    private int fallFrames = 45, frameCounter = 0;
    private Rectangle area;
    private World destination;
    private float newX, newY;

    public Hole(Handler handler, float x, float y, int width, int height, String uniqueName, World destination, float newX, float newY) {
        super(handler, x, y, width, height, uniqueName);
        this.destination = destination;
        this.newX = newX;
        this.newY = newY;
        solid = false;
    }

    @Override
    public void preRender(Graphics g) {
        if (broken) {
            g.drawImage(Assets.hole, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), null);
        }
    }

    @Override
    public void postRender(Graphics g) {

    }

    @Override
    public void finalRender(Graphics g) {

    }

    @Override
    public void tick() {
        area = new Rectangle((int) x + width / 2 - width / 4 - (int) handler.getGameCamera().getxOffset(),
                (int) y + height / 2 - height / 4 - (int) handler.getGameCamera().getyOffset(),
                width / 2, height / 2);
        if (handler.getPlayer().getPlayerRec().intersects(area)) {
            handler.setPlayerFrozen(true);
            handler.getPlayer().setTransparent(true);
            intersected = true;
        }
        if (intersected) {
            if (justStartedFalling) {
                GeneralUtils.levelFadeOut(handler);
                justStartedFalling = false;
                if (!broken) {
                    //Assets.woodBreak.play();
                    broken = true;
                }
            }
            if (fallFrames - frameCounter >= 0) {
                handler.getPlayer().setY(handler.getPlayer().getY() + 7);
                frameCounter++;
            } else {
                intersected = false;
                handler.getPlayer().setTransparent(false);
                justStartedFalling = true;
                frameCounter = 0;
                GeneralUtils.stopLevelFadeOut(handler, destination, newX, newY, true);
                GeneralUtils.levelFadeIn(handler, 40);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(area.x, area.y, area.width, area.height);
    }

    @Override
    public void die() {
        handler.getActiveWorld().getEntityManager().removeEntity(this);
    }

    @Override
    public void interactedWith() {
        isInteracting = true;
        isInteracting = false;
    }

    @Override
    public boolean isInteracting() {
        return isInteracting;
    }
}
