package Entities.StaticEntities;

import Entities.Creatures.Denial;
import Utils.GeneralUtils;
import Variables.Handler;
import Graphics.Assets;
import Worlds.World;

import java.awt.*;

public class Hole extends StaticEntity {

    private boolean intersected = false, justStartedFalling = true, broken = false;
    private int fallFrames = 60, frameCounter = 0;
    private Rectangle area;
    private World destination;
    private Denial denial;
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
        area = new Rectangle((int) x - (int) handler.getGameCamera().getxOffset(),
                (int) y - (int) handler.getGameCamera().getyOffset(),
                width / 2, height / 2);
        if (handler.getPlayer().getPlayerRec().intersects(area)) {
            handler.setPlayerFrozen(true);
            intersected = true;
        }
        if (intersected) {
            if (justStartedFalling) {
                handler.getPlayer().setTransparent(true);
                //TODO: when it exists, check that the next cutscene flag is 1 here
                if (uniqueName != null && uniqueName.equals("hole-L2R3")) {
                    denial = (Denial) handler.getActiveWorld().getEntityManager().getEntityByUid("denial-l2r3-cutscene");
                    if (denial != null) {
                        denial.setTransparent(true);
                    }
                }
                GeneralUtils.levelFadeOut(handler, fallFrames);
                justStartedFalling = false;
                if (!broken) {
                    Assets.woodBreak.play();
                    broken = true;
                }
            }
            if (fallFrames - frameCounter >= 0) {
                handler.getPlayer().setY(handler.getPlayer().getY() + 7);
                if (denial != null) {
                    denial.setY(denial.getY() + 7);
                }
                frameCounter++;
            } else {
                intersected = false;
                handler.getPlayer().setTransparent(false);
                if (null != denial) {
                    handler.getActiveWorld().getEntityManager().removeEntity(denial);
                }
                justStartedFalling = true;
                frameCounter = 0;
                GeneralUtils.stopLevelFadeOut(handler, destination, newX, newY, true);
                GeneralUtils.levelFadeIn(handler, 40);
            }
        }
    }

    @Override
    public void render(Graphics g) {

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
