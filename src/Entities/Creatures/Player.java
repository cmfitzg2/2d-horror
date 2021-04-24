package Entities.Creatures;

import Entities.Entity;
import Input.KeyManager;
import Items.Item;
import Items.Key;
import Items.Lighter;
import Variables.Flags;
import Variables.Handler;
import Items.Inventory;
import Graphics.Animation;
import Graphics.Assets;
import Graphics.ScreenOverlay;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Creature {
    private static boolean down, up, left, right;
    private boolean interactedWith, transparent, invisible, lockX, lockY, lockZ, headOnly;
    private Inventory inventory;
    public static float defaultSpeed = 4.0f, defaultRunSpeed = 16.0f;
    public float cutsceneSpeed = 3.0f;
    //Animations
    private Animation animDown, animUp, animLeft, animRight;
    private java.util.List<Animation> animationList = new ArrayList<>();
    private BufferedImage currentFrame;
    private Rectangle playerRec, interactionRectangle;
    private ScreenOverlay screenOverlay;
    private KeyManager keyManager;
    private int ambientLight;
    //Font
    Font f;

    public Player(Handler handler, float x, float y, String uniqueName) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, uniqueName);

        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;

        //Animations
        animDown = new Animation(200, Assets.playerDown);
        animLeft = new Animation(200, Assets.playerLeft);
        animUp = new Animation(200, Assets.playerUp);
        animRight = new Animation(200, Assets.playerRight);
        animationList.add(animDown);
        animationList.add(animLeft);
        animationList.add(animUp);
        animationList.add(animRight);

        //Common globals
        screenOverlay = new ScreenOverlay(handler);
        keyManager = handler.getKeyManager();

        //Font
        f = new Font("overlay", Font.ITALIC|Font.BOLD, 16);

        //Items
        inventory = new Inventory(handler);
        inventory.addItem(new Key(handler, "Bell Tower Key", Inventory.KEY_ITEM, "A key for accessing the belltower on the main grounds.", Key.BELLTOWER, Assets.keyInventory));
        inventory.addItem(new Lighter(handler, "Lighter", Inventory.REGULAR_ITEM, "A lighter with infinite fluid.", Item.LIGHTER_UID, Assets.keyInventory));

        runSpeed = defaultRunSpeed;
        speed = defaultSpeed;

        ambientLight = handler.getFlags().getTimeOfDay();
    }

    @Override
    public void tick() {
        playerRec = currentPlayerRectangle();
        if (xMove != 0 || yMove != 0) {
            //Animations
            animDown.tick();
            animLeft.tick();
            animUp.tick();
            animRight.tick();
        }
        //Movement
        getInput();
        move();
        if (!handler.isPlayerFrozen()) {
            checkInteraction();
        }
        if (!handler.getFlags().isCameraOverride()) {
            handler.getGameCamera().centerOnEntity(this);
        }
        inventory.tick();
    }

    public boolean interactedWith() {
        //Player should never be interacted with
        return false;
    }

    @Override
    public void preRender(Graphics g) {

    }

    @Override
    public void render(Graphics g) {
        if (!handler.getFlags().isCameraOverride()) {
            handler.getGameCamera().centerOnEntity(this);
        }
        if (invisible) {
            return;
        }
        if (handler.isPlayerFrozen()) {
            if (xMove != 0 || yMove != 0) {
                currentFrame = getCurrentAnimationFrame();
            } else {
                if (!handler.getFlags().isViewingArt() && !handler.getFlags().isInPuzzle() && !handler.isInMenu()) {
                    if (up) {
                        if (headOnly) {
                            currentFrame = Assets.headUp;
                        } else if (!transparent) {
                            currentFrame = Assets.playerUpNormal;
                        } else {
                            currentFrame = Assets.playerUpTransparent;
                        }
                    } else if (down) {
                        if (headOnly) {
                            currentFrame = Assets.headDown;
                        } else if (!transparent) {
                            currentFrame = Assets.playerDownNormal;
                        } else {
                            currentFrame = Assets.playerDownTransparent;
                        }
                    } else if (left) {
                        if (headOnly) {
                            currentFrame = Assets.headLeft;
                        } else if (!transparent) {
                            currentFrame = Assets.playerLeftNormal;
                        } else {
                            currentFrame = Assets.playerLeftTransparent;
                        }
                    } else if (right) {
                        if (headOnly) {
                            currentFrame = Assets.headRight;
                        } else if (!transparent) {
                            currentFrame = Assets.playerRightNormal;
                        } else {
                            currentFrame = Assets.playerRightTransparent;
                        }
                    } else {
                        if (headOnly) {
                            currentFrame = Assets.headDown;
                        } else if (!transparent) {
                            currentFrame = Assets.playerDownNormal;
                        } else {
                            currentFrame = Assets.playerDownTransparent;
                        }
                    }
                }
            }
        } else {
            currentFrame = getCurrentAnimationFrame();
        }
        g.drawImage(currentFrame, (int) (x - handler.getGameCamera().getxOffset()),  (int) (y-handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void postRender(Graphics g) {
        drawAmbientLight(g);
        screenOverlay.drawVision(g);
        /*
        if (null != playerRec) {
            g.fillRect(playerRec.x, playerRec.y, playerRec.width, playerRec.height);
        }
         */
    }

    @Override
    public void finalRender(Graphics g) {
        inventory.render(g);
        g.setColor(Color.WHITE);
        g.setFont(f);
        g.drawString("Current (x,y): (" + x + ", " + y + ")", 16, handler.getHeight() - 16);
        g.drawString("Current mouse (x,y): ("  + (handler.getMouseManager().getMouseX() + handler.getGameCamera().getxOffset())
                + ", " + (handler.getMouseManager().getMouseY() + handler.getGameCamera().getyOffset()) + ")", 16, handler.getHeight() - 100);
    }

    private Rectangle currentPlayerRectangle() {
        return new Rectangle((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
                bounds.width, bounds.height);
    }

    private void drawAmbientLight(Graphics g) {
        if (ambientLight > 0) {
            switch (ambientLight) {
                case 1:
                    screenOverlay.overlayScreen(g, ScreenOverlay.someDark);
                    break;
                case 2:
                    screenOverlay.overlayScreen(g, ScreenOverlay.dark);
                    break;
                case 3:
                    screenOverlay.overlayScreen(g, ScreenOverlay.veryDark);
                    break;
                case 4:
                    screenOverlay.overlayScreen(g, ScreenOverlay.pitchBlack);
                    break;
            }
        }
    }

    private void checkInteraction() {
        Rectangle collisionBounds = getCollisionBounds(0,0);
        interactionRectangle = new Rectangle();
        int interactionSize = 30;
        interactionRectangle.width = interactionSize;
        interactionRectangle.height = interactionSize;
        if (up) {
            interactionRectangle.x = collisionBounds.x + collisionBounds.width / 2 - interactionSize / 2;
            interactionRectangle.y = collisionBounds.y - interactionSize;
        } else if (down) {
            interactionRectangle.x = collisionBounds.x + collisionBounds.width / 2 - interactionSize / 2;
            interactionRectangle.y = collisionBounds.y + collisionBounds.height;
        } else if (left) {
            interactionRectangle.x = collisionBounds.x - interactionSize;
            interactionRectangle.y = collisionBounds.y + collisionBounds.height / 2 - interactionSize / 2;
        } else if (right) {
            interactionRectangle.x = collisionBounds.x + collisionBounds.width;
            interactionRectangle.y = collisionBounds.y + collisionBounds.height / 2 - interactionSize / 2;
        }
    }


    @Override
    public void die() {
        System.out.println("you lose");
    }

    private void getInput() {
        if (!handler.isPlayerFrozen()) {
            xMove = 0;
            yMove = 0;

            if (!lockY) {
                if (keyManager.up) {
                    handler.getKeyManager().setStillHoldingUp(true);
                    if (keyManager.shift) {
                        yMove = -runSpeed;
                    } else {
                        yMove = -speed;
                    }
                }
                if (keyManager.down) {
                    handler.getKeyManager().setStillHoldingDown(true);
                    if (keyManager.shift) {
                        yMove = runSpeed;
                    } else {
                        yMove = speed;
                    }
                }
            }
            if (!lockX) {
                if (keyManager.left) {
                    handler.getKeyManager().setStillHoldingLeft(true);
                    if (keyManager.shift) {
                        xMove = -runSpeed;
                    } else {
                        xMove = -speed;
                    }
                }
                if (keyManager.right) {
                    handler.getKeyManager().setStillHoldingRight(true);
                    if (keyManager.shift) {
                        xMove = runSpeed;
                    } else {
                        xMove = speed;
                    }
                }
            }
            if (!lockZ) {
                if (keyManager.z) {
                    if (!keyManager.isStillHoldingZ()) {
                        keyManager.setStillHoldingZ(true);
                        for (Entity e : handler.getActiveWorld().getEntityManager().getEntities()) {
                            if (e.equals(this)) {                   //an entity cannot interact with itself
                                continue;
                            }
                            if (interactionRectangle != null) {
                                if (e.getCollisionBounds(0, 0).intersects(interactionRectangle)) {
                                    if (e.isInteracting()) {
                                        break;
                                    } else {
                                        //call this entity's custom interaction
                                        boolean interaction = e.interactedWith();
                                        if (!interaction) {
                                            //this entity doesn't have one, check if another one does
                                            continue;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0) {
            left = true; right = false; up = false; down = false;
            if (headOnly) {
                return Assets.headLeft;
            }
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            right = true; left = false; up = false; down = false;
            if (headOnly) {
                return Assets.headRight;
            }
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            up = true; left = false; right = false; down = false;
            if (headOnly) {
                return Assets.headUp;
            }
            return animUp.getCurrentFrame();
        } else if (yMove > 0) {
            down = true; left = false; up = false; right = false;
            if (headOnly) {
                return Assets.headDown;
            }
            return animDown.getCurrentFrame();
        } else {
            if (!transparent) {
                if (right) {
                    if (headOnly) {
                        return Assets.headRight;
                    }
                    return Assets.playerRightNormal;
                } else if (up) {
                    if (headOnly) {
                        return Assets.headUp;
                    }
                    return Assets.playerUpNormal;
                } else if (left) {
                    if (headOnly) {
                        return Assets.headLeft;
                    }
                    return Assets.playerLeftNormal;
                } else {
                    if (headOnly) {
                        return Assets.headDown;
                    }
                    return Assets.playerDownNormal;
                }
            } else {
                if (right) {
                    return Assets.playerRightTransparent;
                } else if (up) {
                    return Assets.playerUpTransparent;
                } else if (left) {
                    return Assets.playerLeftTransparent;
                } else {
                    return Assets.playerDownTransparent;
                }
            }
        }
    }

    public void updateAnimations(Animation ignoredAnimation) {
        for (Animation animation : animationList) {
            if (animation != ignoredAnimation) {
                animation.setTimer(0);
                animation.setIndex(0);
            }
        }
    }

    public boolean isInteracting() {
        return interactedWith;
    }

    @Override
    public boolean itemInteraction(String item) {
        return false;
    }

    public Rectangle getPlayerRec() {
        return playerRec;
    }

    public static String getDirection() {
        if (up) {
            return "up";
        }
        if (down) {
            return "down";
        }
        if (left) {
            return "left";
        }
        return "right";
    }

    public void setDirection(String dir) {
        if (dir.equals("up")) {
            up = true;
            down = false;
            left = false;
            right = false;
        }
        if (dir.equals("down")) {
            down = true;
            left = false;
            right = false;
            up = false;
        }
        if (dir.equals("left")) {
            left = true;
            down = false;
            up = false;
            right = false;
        }
        if (dir.equals("right")) {
            right = true;
            up = false;
            down = false;
            left = false;
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean isTransparent() {
        return transparent;
    }

    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
    }

    public boolean isLockX() {
        return lockX;
    }

    public void setLockX(boolean lockX) {
        this.lockX = lockX;
    }

    public boolean isLockY() {
        return lockY;
    }

    public void setLockY(boolean lockY) {
        this.lockY = lockY;
    }

    public boolean isLockZ() {
        return lockZ;
    }

    public void setLockZ(boolean lockZ) {
        this.lockZ = lockZ;
    }

    public boolean isHeadOnly() {
        return headOnly;
    }

    public void setHeadOnly(boolean headOnly) {
        this.headOnly = headOnly;
    }

    public Rectangle getInteractionRectangle() {
        return interactionRectangle;
    }

    public float getCutsceneSpeed() {
        return cutsceneSpeed;
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public int getAmbientLight() {
        return ambientLight;
    }

    public void setAmbientLight(int ambientLight) {
        if (this.ambientLight >= Flags.TIME_OF_DAY_PITCH_BLACK) {
            lockX = false;
            lockY = false;
            lockZ = false;
        }
        this.ambientLight = ambientLight;
        if (ambientLight >= Flags.TIME_OF_DAY_PITCH_BLACK) {
            lockX = true;
            lockY = true;
            lockZ = true;
        }
    }
}
