package Entities.StaticEntities;

import Entities.Creatures.Player;
import Graphics.Assets;
import Variables.Handler;
import Worlds.WorldManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bed extends StaticEntity {

    private boolean inBed = false, firstTime = true;
    public static final int STYLE_BLUE_SINGLE = 0, STYLE_RED_SINGLE = 1, STYLE_RED_DOUBLE = 2, STYLE_RED_MASTER = 3;
    private int style;

    public Bed(Handler handler, float x, float y, int width, int height, String uniqueName, int style) {
        super(handler, x, y, width, height, uniqueName);
        this.style = style;
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;
    }

    @Override
    public void preRender(Graphics g) {
        BufferedImage image = null;
        switch (style) {
            case STYLE_BLUE_SINGLE:
                image = Assets.blueSingleBed;
                break;
            case STYLE_RED_SINGLE:
                image = Assets.redSingleBed;
                break;
            case STYLE_RED_DOUBLE:
                image = Assets.redDoubleBed;
                break;
            case STYLE_RED_MASTER:
                image = Assets.redMasterBed;
                break;
        }
        g.drawImage(image, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void postRender(Graphics g) {

    }

    @Override
    public void finalRender(Graphics g) {

    }

    @Override
    public void tick() {
        if (firstTime && handler.getActiveWorld().getId() == WorldManager.MC_HOUSE_1_ID && (handler.getFlags().isPrologue()
                || (handler.getFlags().isMCHouseNightCutscene1() && !handler.getFlags().isClassroomCutscene1())) && style == STYLE_BLUE_SINGLE) {
            if (handler.getGame().isFadeIn()) {
                interactedWith();
                firstTime = false;
            }
        }
        if (inBed) {
            if (handler.getPlayer().getX() + handler.getPlayer().getWidth() / 2.0f <= x) {
                inBed = false;
                handler.getPlayer().setLockY(false);
                handler.getPlayer().setX(x - handler.getPlayer().getWidth() - 1);
                handler.getPlayer().setSpeed(Player.defaultSpeed);
                handler.getPlayer().setRunSpeed(Player.defaultRunSpeed);
                handler.getPlayer().setHeadOnly(false);
            } else if (handler.getPlayer().getX() >= x + width - handler.getPlayer().getWidth() / 2.0f) {
                inBed = false;
                handler.getPlayer().setLockY(false);
                handler.getPlayer().setX(x + width + 1);
                handler.getPlayer().setSpeed(Player.defaultSpeed);
                handler.getPlayer().setRunSpeed(Player.defaultRunSpeed);
                handler.getPlayer().setHeadOnly(false);
            }

            if (handler.getKeyManager().up) {
                handler.getPlayer().setDirection("up");
            } else if (handler.getKeyManager().down) {
                handler.getPlayer().setDirection("down");
            }
        }
        solid = !inBed;
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
        if (inBed) {
            return false;
        }
        float playerX = handler.getPlayer().getX();
        float playerY = handler.getPlayer().getY();
        if (playerY + handler.getPlayer().getHeight() / 2 < y || playerY > y + height / 2) {
            return false;
        }
        inBed = true;
        handler.getPlayer().setSpeed(Player.defaultSpeed / 2);
        handler.getPlayer().setRunSpeed(Player.defaultSpeed / 2);
        if (playerX < x) {
            handler.getPlayer().setX(x + width / 4.0f - handler.getPlayer().getWidth() / 2.0f);
        } else {
            handler.getPlayer().setX(x + 0.75f * width - handler.getPlayer().getWidth() / 2.0f);
        }
        handler.getPlayer().setY(y + 16);
        handler.getPlayer().setLockY(true);
        handler.getPlayer().setHeadOnly(true);
        return true;
    }

    @Override
    public boolean isInteracting() {
        return isInteracting;
    }

    @Override
    public boolean itemInteraction(String item) {
        return false;
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }
}
