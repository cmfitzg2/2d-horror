package Entities.StaticEntities;

import Entities.Entity;
import Graphics.Assets;
import Variables.Handler;

import java.awt.*;

public class IronGate extends StaticEntity {

    private float xScale, yScale;
    private boolean open = false;
    private Rectangle gateBounds;
    private int pillarWidth, pillarHeight, frontGateHeight, frontGateWidth, cameraX, cameraY, type;
    public static final int TYPE_TOP = 0, TYPE_BOT = 1, TYPE_SIDE = 2;

    public IronGate(Handler handler, float x, float y, int width, int height, String uniqueName, int type) {
        super(handler, x, y, width, height, uniqueName);
        this.type = type;
        if (type == TYPE_TOP) {
            xScale = (float) width / Assets.gateTop.getWidth();
            yScale = (float) height / Assets.gateTop.getHeight();
        } else if (type == TYPE_BOT) {
            xScale = (float) width / Assets.gateBot.getWidth();
            yScale = (float) height / Assets.gateBot.getHeight();
        } else if (type == TYPE_SIDE) {
            xScale = (float) width / Assets.gateSide.getWidth();
            yScale = (float) height / Assets.gateSide.getHeight();
        }
        pillarWidth = (int) (17 * xScale);
        pillarHeight = (int) (64 * yScale);
        frontGateWidth = (int) (Assets.frontGateOpen.getWidth() * xScale);
        frontGateHeight = (int) (Assets.frontGateOpen.getHeight() * yScale);

        //top
        if (type == TYPE_TOP) {
            addBoundingBox(new Rectangle(0, 2 * pillarHeight / 3, width, pillarHeight / 3));
        } else if (type == TYPE_SIDE) {
            addBoundingBox(new Rectangle(0, 0, width, height));
        } else if (type == TYPE_BOT) {
            //the part left of the front gate
            addBoundingBox(new Rectangle(0, height - pillarHeight / 3, width / 2 - frontGateWidth / 2, pillarHeight / 3));
            addBoundingBox(new Rectangle(0, 0, pillarWidth, pillarHeight));
            addBoundingBox(new Rectangle(width - pillarWidth, 0, pillarWidth, pillarHeight));
            //the part right of the front gate
            addBoundingBox(new Rectangle(width / 2 + frontGateWidth / 2, height - pillarHeight / 3, width / 2 - frontGateWidth / 2, pillarHeight / 3));
            //lastly, initialize the gate box
            gateBounds = new Rectangle(width / 2 - frontGateWidth / 2, height - pillarHeight / 3, frontGateWidth, pillarHeight / 3);
            if (!open) {
                addBoundingBox(gateBounds);
            }
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
        cameraX = (int) handler.getGameCamera().getxOffset();
        cameraY = (int) handler.getGameCamera().getyOffset();
        if (type == TYPE_TOP) {
            g.drawImage(Assets.gateTop, (int) (x - cameraX), (int) (y - cameraY), width, height, null);
        } else if (type == TYPE_BOT) {
            g.drawImage(Assets.gateBot, (int) (x - cameraX), (int) (y - cameraY), width, height, null);
            if (open) {
                g.drawImage(Assets.frontGateOpen, (int) (x - cameraX + width / 2 - frontGateWidth / 2),
                        (int) (y - cameraY + height - frontGateHeight), frontGateWidth, frontGateHeight, null);
            } else {
                g.drawImage(Assets.frontGateClosed, (int) (x - cameraX + width / 2 - frontGateWidth / 2),
                        (int) (y - cameraY + height - frontGateHeight), frontGateWidth, frontGateHeight, null);
            }
        } else if (type == TYPE_SIDE) {
            g.drawImage(Assets.gateSide, (int) (x - cameraX), (int) (y - cameraY), width, height, null);
        }

    }

    @Override
    public void die() {
        handler.getActiveWorld().getEntityManager().removeEntity(this);
    }

    @Override
    public boolean interactedWith() {
        if (type == TYPE_BOT) {
            setOpen(!open);
            return true;
        }
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

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
        if (!open) {
            addBoundingBox(gateBounds);
        } else {
            collisionBoundsList.remove(gateBounds);
        }
    }

    @Override
    public int renderVsEntity(Entity e) {
        if (e.getX() < x + width && e.getX() + e.getWidth() > x && e.getY() + e.getHeight() >= y && e.getY() <= y + height) {
            if (type == TYPE_TOP || type == TYPE_BOT) {
                if (e.getY() + e.getHeight() <= Math.ceil(y + 2 * pillarHeight / 3f)) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
        return 0;
    }

    @Override
    public boolean customRenderVsEntity() {
        return true;
    }
}
