package Entities.StaticEntities;

import Entities.Creatures.Creature;
import Entities.Creatures.Player;
import Entities.Entity;
import Graphics.Assets;
import Variables.Handler;

import java.awt.*;

public class IronGate extends StaticEntity {

    private float xScale, yScale;
    private boolean open = true;
    private Rectangle gateBounds;
    private int xOffset, pillarWidth, pillarHeight, frontGateHeight, frontGateWidth, cameraX, cameraY;

    public IronGate(Handler handler, float x, float y, int width, int height, String uniqueName) {
        super(handler, x, y, width, height, uniqueName);
        xScale = (float) width / Assets.fullGate.getWidth();
        yScale = (float) height / Assets.fullGate.getHeight();
        xOffset = (int) (8 * xScale);
        pillarWidth = (int) (17 * xScale);
        pillarHeight = (int) (64 * yScale);
        frontGateWidth = (int) (Assets.frontGateOpen.getWidth() * xScale);
        frontGateHeight = (int) (Assets.frontGateOpen.getHeight() * yScale);

        //top
        addBoundingBox(new Rectangle(xOffset, 2 * pillarHeight / 3, width - xOffset * 2, pillarHeight / 3));
        //left
        addBoundingBox(new Rectangle(xOffset, 2 * pillarHeight / 3, pillarWidth, height - 2 * pillarHeight / 3));
        //right
        addBoundingBox(new Rectangle(width - xOffset - pillarWidth, 2 * pillarHeight / 3, pillarWidth, height - 2 * pillarHeight / 3));
        //bottom
        //the part left of the front gate
        addBoundingBox(new Rectangle(xOffset, height - pillarHeight / 3, width / 2 - frontGateWidth / 2 - xOffset, pillarHeight / 3));
        //the part right of the front gate
        addBoundingBox(new Rectangle(width / 2 + frontGateWidth / 2, height - pillarHeight / 3, width / 2 - frontGateWidth / 2 - xOffset, pillarHeight / 3));
        //lastly, initialize the gate box
        gateBounds = new Rectangle(width / 2 - frontGateWidth / 2, height - pillarHeight / 3, frontGateWidth, pillarHeight / 3);
        if (!open) {
            addBoundingBox(gateBounds);
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
        g.drawImage(Assets.fullGate, (int) (x - cameraX), (int) (y - cameraY), width, height, null);
        if (open) {
            g.drawImage(Assets.frontGateOpen, (int) (x - cameraX + width / 2 - frontGateWidth / 2),
                    (int) (y - cameraY + height - frontGateHeight), frontGateWidth, frontGateHeight, null);
        } else {
            g.drawImage(Assets.frontGateClosed, (int) (x - cameraX + width / 2 - frontGateWidth / 2),
                    (int) (y - cameraY + height - frontGateHeight), frontGateWidth, frontGateHeight, null);
        }
    }

    @Override
    public void die() {
        handler.getActiveWorld().getEntityManager().removeEntity(this);
    }

    @Override
    public boolean interactedWith() {
        setOpen(!open);
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
        if (e instanceof Mansion) {
            return -1;
        }
        if (e.getY() < y + pillarHeight / 3f) {
            //System.out.println(e.getUniqueName() + " -- top, outside -- " + (y + pillarHeight));
            return -1;
        }
        if (e.getY() > y + pillarHeight / 3f && e.getY() < y + height / 2) {
            //System.out.println(e.getUniqueName() + " -- top, inside -- " + (y + pillarHeight) + " && " + (y + height - pillarHeight / 3f));
            return 1;
        }
        if (e.getY() >= y + height / 2 && e.getY() < y + height - pillarHeight / 3f) {
            //System.out.println(e.getUniqueName() + " -- bottom, inside -- " + (height / 2) + " && " + (y + height - pillarHeight / 3f));
            return -1;
        }
        if (e.getY() > y + height - pillarHeight / 3f) {
            //System.out.println(e.getUniqueName() + " -- bottom, outside -- " + (y + height - pillarHeight / 3f));
            return 1;
        }
        return 0;
    }

    @Override
    public boolean customRenderVsEntity() {
        return true;
    }
}
