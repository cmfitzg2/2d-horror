package Entities.StaticEntities;

import Entities.Creatures.Player;
import Graphics.Assets;
import Variables.Handler;

import java.awt.*;

public class Bed extends StaticEntity {

    private boolean inBed = false, firstTime = true;
    private Rectangle bedBounds;

    public Bed(Handler handler, float x, float y, int width, int height, String uniqueName) {
        super(handler, x, y, width, height, uniqueName);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;
    }

    @Override
    public void preRender(Graphics g) {
        g.drawImage(Assets.bedOne, (int) (x - handler.getGameCamera().getxOffset()),
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
        if (firstTime && handler.getActiveWorld().getId() == 1 && handler.getFlags().isPrologue()) {
            if (handler.getGame().isFadeIn()) {
                interactedWith();
                firstTime = false;
            }
        }
        bedBounds = new Rectangle((int) x + width / 2 - (5 * width / 12)  - (int) handler.getGameCamera().getxOffset(),
                (int) y + 16  - (int) handler.getGameCamera().getyOffset(), width - 2 * (width / 2 - (5 * width / 12)), height - 32);
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
    public void interactedWith() {
        if (inBed) {
            return;
        }
        inBed = true;
        handler.getPlayer().setSpeed(Player.defaultSpeed / 2);
        handler.getPlayer().setRunSpeed(Player.defaultSpeed / 2);
        handler.getPlayer().setX(x + width / 2.0f - handler.getPlayer().getWidth() / 2.0f);
        handler.getPlayer().setY(y + 16);
        handler.getPlayer().setLockY(true);
        handler.getPlayer().setHeadOnly(true);
    }

    @Override
    public boolean isInteracting() {
        return isInteracting;
    }
}
