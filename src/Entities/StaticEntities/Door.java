package Entities.StaticEntities;

import Graphics.Assets;
import Variables.GeneralConstants;
import Variables.Handler;
import Worlds.World;

import java.awt.*;

public class Door extends StaticEntity {

    private int doorHeight = 96, style, transitionFrames = GeneralConstants.levelTransitionFrames;
    private boolean includeStairs;
    private boolean includeArch;
    private Rectangle enterDoor;
    private World destination;
    private float newX, newY;
    public static final int PLAIN_WOOD = 0, STAIRS = 1, ARCH = 2, STAIRS_ARCH = 3, BATHROOM_MALE = 4, BATHROOM_FEMALE = 5;

    public Door(Handler handler, float x, float y, int width, int height, String uniqueName, World destination, float newX, float newY, int style, int transitionFrames) {
        super(handler, x, y, width, height, uniqueName);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;
        this.destination = destination;
        this.newX = newX;
        this.newY = newY;
        this.style = style;
        if (style == STAIRS || style == STAIRS_ARCH) {
            includeStairs = true;
        }
        if (style == ARCH || style == STAIRS_ARCH) {
            includeArch = true;
        }
        this.transitionFrames = transitionFrames;
    }

    public Door(Handler handler, float x, float y, int width, int height, String uniqueName, World destination, float newX, float newY, int style) {
        this(handler, x, y, width, height, uniqueName, destination, newX, newY, style, GeneralConstants.levelTransitionFrames);
    }

    @Override
    public void preRender(Graphics g) {

    }

    @Override
    public void postRender(Graphics g) {
        //g.drawRect(enterDoor.x, enterDoor.y, enterDoor.width, enterDoor.height);
    }

    @Override
    public void finalRender(Graphics g) {

    }

    @Override
    public void tick() {
        enterDoor = new Rectangle((int) x - 3  - (int) handler.getGameCamera().getxOffset(),
                (int) y - 3  - (int) handler.getGameCamera().getyOffset(), width + 6, height + 20);
        if (handler.getPlayer().getPlayerRec().intersects(enterDoor)) {
            handler.getActiveWorld().transitionFrom(destination, newX, newY, transitionFrames);
        }
    }

    @Override
    public void render(Graphics g) {
        if (style == PLAIN_WOOD || includeArch || includeStairs) {
            g.drawImage(Assets.closedDoorOne, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (style == BATHROOM_MALE) {
            g.drawImage(Assets.bathroomDoorMale, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (style == BATHROOM_FEMALE) {
            g.drawImage(Assets.bathroomDoorFemale, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }
        if (includeArch) {
            g.drawImage(Assets.doorwayArch, (int) (x - handler.getGameCamera().getxOffset() - 16),
                    (int) (y - handler.getGameCamera().getyOffset() - 12), 96, 108, null);
        }
        if (includeStairs) {
            g.drawImage(Assets.stairs, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset() + doorHeight), 64, 48, null);
        }
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

    @Override
    public boolean itemInteraction(String item) {
        return false;
    }
}
