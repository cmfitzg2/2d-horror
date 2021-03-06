package Entities.Creatures;

import java.awt.*;

import Variables.Handler;
import Graphics.Animation;
import Graphics.Assets;

public class Ghoul extends Creature {

    private Animation animDown, animUp, animLeft, animRight;
    private boolean down = false, up = false, left = false, right = false;
    private float playerX = 0, playerY = 0,
            adjustedX = 0, adjustedY = 0, outerDistanceThreshold = 128, innerDistanceThreshold = 0.5f,
            patrolYMin, patrolYMax;
    private boolean patrolling = true;
    private String currentDir = "up";

    public Ghoul(Handler handler, float x, float y, int width, int height, String uniqueName, float patrolYMin, float patrolYMax) {
        super(handler, x, y, width, height, uniqueName);
        this.patrolYMin = patrolYMin;
        this.patrolYMax = patrolYMax;
        bounds.y = (int) (height / 1.5f);
        bounds.height = (int) (height - height / 1.5f);
        animDown = new Animation(200, Assets.ghoulDown);
        animLeft = new Animation(200, Assets.ghoulLeft);
        animRight = new Animation(200, Assets.ghoulRight);
        animUp = new Animation(200, Assets.ghoulUp);
        ignoreCollision = true;
    }

    @Override
    public void tick() {
        playerX = handler.getActiveWorld().getEntityManager().getPlayer().getX();
        playerY = handler.getActiveWorld().getEntityManager().getPlayer().getY();
        adjustedX = x + (float) width / 4;
        adjustedY = y + (float) height / 4;

        xMove = 0;
        yMove = 0;
        speed = 0.5f;
        runSpeed = 4f;
        innerDistanceThreshold = speed;

        //decide when to move here
        if (!patrolling) {
            //pursuing player
            if (playerX < adjustedX && (Math.abs(playerX - x) > outerDistanceThreshold)
                    || (Math.abs(playerY - adjustedY) > outerDistanceThreshold + 32 && adjustedX - playerX > innerDistanceThreshold)) {
                xMove = -runSpeed;
            }
            if (playerX > adjustedX && (Math.abs(playerX - x) > outerDistanceThreshold)
                    || (Math.abs(playerY - adjustedY) > outerDistanceThreshold + 32 && playerX - adjustedX > innerDistanceThreshold)) {
                xMove = runSpeed;
            }
            if (playerY < adjustedY && (Math.abs(playerY - y) > outerDistanceThreshold)
                    || (Math.abs(playerX - adjustedX) > outerDistanceThreshold + 32 && adjustedY - playerY > innerDistanceThreshold)) {
                yMove = -runSpeed;
            }
            if (playerY > adjustedY && (Math.abs(playerY - y) > outerDistanceThreshold)
                    || (Math.abs(playerX - adjustedX) > outerDistanceThreshold + 32 && playerY - adjustedY > innerDistanceThreshold)) {
                yMove = runSpeed;
            }
        } else {
            double distance = Math.sqrt((handler.getPlayer().getY() - y) * (handler.getPlayer().getY() - y)
                    + (handler.getPlayer().getX() - x) * (handler.getPlayer().getX() - x));
            if (distance < 40) {
                patrolling = false;
            }

            if (currentDir.equals("up")) {
                if (y <= patrolYMin) {
                    currentDir = "down";
                    yMove = speed;
                } else {
                    yMove = -speed;
                }
            }
            if (currentDir.equals("down")) {
                if (y >= patrolYMax) {
                    currentDir = "up";
                    yMove = -speed;
                } else {
                    yMove = speed;
                }
            }
        }

        if (xMove != 0) {
            animLeft.tick();
            animRight.tick();
            moveX();
        }
        if (yMove != 0) {
            animDown.tick();
            animUp.tick();
            moveY();
        }
    }

    @Override
    public void preRender(Graphics g) {

    }

    @Override
    public void render(Graphics g) {
        if (yMove > 0) {
            down = true; left = false; up = false; right = false;
            g.drawImage(animDown.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()),  (int) (y-handler.getGameCamera().getyOffset()), width, height, null);
        }
        else if (xMove > 0) {
            down = false; right = true; left = false; up = false;
            g.drawImage(animRight.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()),  (int) (y-handler.getGameCamera().getyOffset()), width, height, null);
        }
        else if (yMove < 0) {
            up = true; right = false; left = false; down = false;
            g.drawImage(animUp.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()),  (int) (y-handler.getGameCamera().getyOffset()), width, height, null);
        }
        else if (xMove < 0) {
            left = true; up = false; right = false; down = false;
            g.drawImage(animLeft.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()),  (int) (y-handler.getGameCamera().getyOffset()), width, height, null);
        }

        else if (xMove == 0 && yMove == 0) {
            if (down) {
                g.drawImage(animDown.getDefaultFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
            }
            else if (left) {
                g.drawImage(animLeft.getDefaultFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
            }
            else if (up) {
                g.drawImage(animUp.getDefaultFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
            } else {
                g.drawImage(animRight.getDefaultFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
            }
        }
    }

    @Override
    public void postRender(Graphics g) {

    }

    @Override
    public void finalRender(Graphics g) {

    }

    @Override
    public void die() {

    }

    @Override
    public boolean interactedWith() {
        return false;
    }

    @Override
    public boolean isInteracting() {
        return false;
    }

    @Override
    public boolean itemInteraction(String item) {
        return false;
    }
}
