package Entities.Creatures;

import Entities.Entity;
import Entities.EntityManager;
import Game.Handler;
import Items.Inventory;
import Graphics.Animation;
import Graphics.Assets;
import Graphics.ScreenOverlay;
import Worlds.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {
	private static boolean down, up, left, right;
	private boolean interactedWith, textboxOpen, transparent;
	private Inventory inventory;
	//Animations	
	private Animation animDown, animUp, animLeft, animRight;
	public static Rectangle playerRec, interactionRectangle;
	private ScreenOverlay screenOverlay;
	//Font
	Font f;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		bounds.x = 16;
		bounds.y = 32;
		bounds.width = 32;
		bounds.height = 32;

		//Animations
		animDown = new Animation(150, Assets.player_down);
		animLeft = new Animation(150, Assets.player_left);
		animUp = new Animation(150, Assets.player_up);
		animRight = new Animation(150, Assets.player_right);

		//ScreenOverlay
		screenOverlay = new ScreenOverlay(handler);

		//Font
		f = new Font("overlay", Font.ITALIC|Font.BOLD, 16);

		//Items
		inventory = new Inventory(handler, this);
	}

	@Override
	public void tick() {
		currentPlayerRectangle();
		if (!handler.isPlayerFrozen()) {
			//Animations
			animDown.tick();
			animLeft.tick();
			animUp.tick();
			animRight.tick();
		}
		//Movement
		getInput();
		if (!handler.isPlayerFrozen()) {
			move();
			handler.getGameCamera().centerOnEntity(this);
			checkInteraction();
		}
		inventory.tick();
	}

	public void interactedWith() {
		//Player should never be interacted with
		interactedWith = true;
		System.out.println("Interaction with " + this);
		interactedWith = false;
	}

	@Override
	public void preRender(Graphics g) {

	}

	@Override
	public void render(Graphics g) {
		if (handler.isPlayerFrozen()) {
			if (!handler.getFlags().isViewingArt()) {
				if (up) {
					if (!transparent) {
						g.drawImage(Assets.playerUpNormal, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
					} else {
						g.drawImage(Assets.playerUpTransparent, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
					}
				}
				else if (down) {
					if (!transparent) {
						g.drawImage(Assets.playerDownNormal, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
					} else {
						g.drawImage(Assets.playerDownTransparent, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
					}
				}
				else if (left) {
					if (!transparent) {
						g.drawImage(Assets.playerLeftNormal, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
					} else {
						g.drawImage(Assets.playerLeftTransparent, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
					}
				}
				else if (right) {
					if (!transparent) {
						g.drawImage(Assets.playerRightNormal, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
					} else {
						g.drawImage(Assets.playerRightTransparent, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
					}
				}
				else {
					if (!transparent) {
						g.drawImage(Assets.playerDownNormal, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
					} else {
						g.drawImage(Assets.playerDownTransparent, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
					}
				}
			} else {
				g.drawImage(Assets.wall, 0, 0, handler.getWidth(), handler.getHeight(), null);
			}
		} else {
			g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),  (int) (y-handler.getGameCamera().getyOffset()), width, height, null);


			//collison box viewer
/*		g.setColor(Color.RED);
		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
				bounds.width, bounds.height);*/

			g.setFont(f);
		}
	}

	@Override
	public void postRender(Graphics g) {
		screenOverlay.drawVision(g);
		inventory.render(g);
		drawTextboxes(g);
		g.setColor(Color.WHITE);
		g.setFont(f);
		g.drawString("Current (x,y): (" + handler.getActiveWorld().getEntityManager().getPlayer().x + ", "
				+ handler.getActiveWorld().getEntityManager().getPlayer().y + ")", 16, handler.getHeight() - 16);
	}

	private void currentPlayerRectangle() {
		playerRec = new Rectangle((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
				bounds.width, bounds.height);
	}

	private void drawTextboxes(Graphics g) {

	}

	private void checkInteraction() {
		Rectangle collisionBounds = getCollisionBounds(0,0);
		interactionRectangle = new Rectangle();
		int interactionSize = 20;
		interactionRectangle.width = interactionSize;
		interactionRectangle.height = interactionSize;
		if (up) {
			interactionRectangle.x = collisionBounds.x + collisionBounds.width/2 - interactionSize/2;
			interactionRectangle.y = collisionBounds.y - interactionSize;
		}
		else if (down) {
			interactionRectangle.x = collisionBounds.x + collisionBounds.width/2 - interactionSize/2;
			interactionRectangle.y = collisionBounds.y + collisionBounds.height;
		}
		else if (left) {
			interactionRectangle.x = collisionBounds.x - interactionSize;
			interactionRectangle.y = collisionBounds.y + collisionBounds.height/2 - interactionSize/2;
		}
		else if (right) {
			interactionRectangle.x = collisionBounds.x + collisionBounds.width;
			interactionRectangle.y = collisionBounds.y + collisionBounds.height/2 - interactionSize/2;
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
			runSpeed = 8.0f;
			speed = 4.0f;

			if (handler.getKeyManager().up)
				if (handler.getKeyManager().shift)
					yMove = -runSpeed;
				else
					yMove = -speed;
			if (handler.getKeyManager().down)
				if (handler.getKeyManager().shift)
					yMove = runSpeed;
				else
					yMove = speed;
			if (handler.getKeyManager().left)
				if (handler.getKeyManager().shift)
					xMove = -runSpeed;
				else
					xMove = -speed;
			if (handler.getKeyManager().right)
				if (handler.getKeyManager().shift)
					xMove = runSpeed;
				else
					xMove = speed;

			if (handler.getKeyManager().z) {
				if (!handler.getKeyManager().isStillHoldingZ()) {
					handler.getKeyManager().setStillHoldingZ(true);

					for (Entity e : handler.getActiveWorld().getEntityManager().getEntities()) {
						if (e.equals(this)) {                   //an entity cannot interact with itself
							continue;
						}
						if (e.getCollisionBounds(0, 0).intersects(interactionRectangle)) {
							if (e.isInteracting()) {
								break;
							} else {
								e.interactedWith();                //call interaction function specified by any class extending entity
							}
							break;
						}
					}
				}
			}
		}
	}

	private BufferedImage getCurrentAnimationFrame() {
		if (xMove < 0) {
			left = true; right = false; up = false; down = false;
			return animLeft.getCurrentFrame();
		} else if (xMove > 0) {
			right = true; left = false; up = false; down = false;
			return animRight.getCurrentFrame();
		} else if (yMove < 0) {
			up = true; left = false; right = false; down = false;
			return animUp.getCurrentFrame();
		} else if (yMove > 0) {
			down = true; left = false; up = false; right = false;
			return animDown.getCurrentFrame();
		}
		//not moving
		else {
			if (!transparent) {
				if (right) {
					return Assets.playerRightNormal;
				} else if (up) {
					return Assets.playerUpNormal;
				} else if (left) {
					return Assets.playerLeftNormal;
				} else {
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

	public boolean isInteracting() {
		return interactedWith;
	}

	public Rectangle getPlayerRec() {
		return playerRec;
	}

	public static String getDirection() {
		if (up)
			return "up";
		if (down)
			return "down";
		if (left)
			return "left";
		else return "right";
	}

	public void setDirection(String dir) {
		if (dir.equals("up")) {
			up = true; down = false; left = false; right = false;
		}
		if (dir.equals("down")) {
			down = true; left = false; right = false; up = false;
		}
		if (dir.equals("left")) {
			left = true; down = false; up = false; right = false;
		}
		if (dir.equals("right")) {
			right = true; up = false; down = false; left = false;
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
}
