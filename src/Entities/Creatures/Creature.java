package Entities.Creatures;

import Entities.Entity;
import Variables.Handler;
import Tiles.Tile;

public abstract class Creature extends Entity {

	public static float DEFAULT_SPEED = 3.0f;
	public static float RUN_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64,
			DEFAULT_CREATURE_HEIGHT = 64;

	protected float speed, runSpeed;
	protected float xMove, yMove;
	protected int messageNumber = 1;

	public Creature(Handler handler, float x, float y, int width, int height, String uniqueName) {
		super(handler, x, y, width, height, uniqueName);
		speed = DEFAULT_SPEED;
		runSpeed = RUN_SPEED;
		xMove = 0;
		yMove = 0;
	}

	public void move() {
		if (!checkEntityCollisions(xMove, 0f)) {
			moveX();
		}
		if (!checkEntityCollisions(0f, yMove)) {
			moveY();
		}

	}

	public void moveX() {
		moveX(false);
	}

	public void moveY() {
		moveY(false);
	}

	public void moveX(boolean ignoreCollision) {
		if (ignoreCollision) {
			x += xMove;
		} else {
			if (xMove > 0) {
				int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
				if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
						!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
					x += xMove;
				} else {
					x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
				}
			} else if (xMove < 0) {
				int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
				if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
						!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
					x += xMove;
				} else {
					x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
				}
			}
		}
	}

	public void moveY(boolean ignoreCollision) {
		if (ignoreCollision) {
			y += yMove;
		} else {
			if(yMove<0)			//move up
			{
				int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

				if(!collisionWithTile((int) (x+bounds.x)/ Tile.TILEWIDTH, ty) &&
						!collisionWithTile((int) (x+bounds.x+bounds.width)/ Tile.TILEWIDTH, ty))
				{
					y += yMove;
				}
				else
				{
					y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
				}
			}
			else if(yMove>0)	//move down
			{
				int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

				if(!collisionWithTile((int) (x+bounds.x)/ Tile.TILEWIDTH, ty) &&
						!collisionWithTile((int) (x+bounds.x+bounds.width)/ Tile.TILEWIDTH, ty))
				{
					y += yMove;
				}
				else
				{
					y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
				}
			}
		}
	}

	protected boolean collisionWithTile(int x, int y)
	{
		return handler.getActiveWorld().getTile(x, y).isSolid();
	}

	//GETTERS & SETTERS

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getRunSpeed() {
		return runSpeed;
	}

	public void setRunSpeed(float runSpeed) {
		this.runSpeed = runSpeed;
	}

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getMessageNumber() {
		return messageNumber;
	}

	public void setMessageNumber(int messageNumber) {
		this.messageNumber = messageNumber;
	}
}
