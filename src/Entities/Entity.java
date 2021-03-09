package Entities;

import Variables.Handler;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
	public static final int DEFAULT_HEALTH = 10;
	protected int health;
	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected boolean active = true;
	protected Rectangle bounds;
	protected boolean solid = true;
	protected String uniqueName;
	protected List<Rectangle> collisionBoundsList;
	
	public Entity(Handler handler, float x, float y, int width, int height, String uniqueName) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.uniqueName = uniqueName;
		health = DEFAULT_HEALTH;
		bounds = new Rectangle(0, 0, width, height);
		collisionBoundsList = new ArrayList<>();
	}
	
	public abstract void preRender(Graphics g);

	public abstract void postRender(Graphics g);

	public abstract void finalRender(Graphics g);
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void die();
	
	public abstract void interactedWith();
	
	public abstract boolean isInteracting();

	public abstract boolean itemInteraction(String item);
	
	protected boolean checkEntityCollisions(float xOffset, float yOffset) {
		for (Entity e : handler.getActiveWorld().getEntityManager().getEntities()) {
			if (e.equals(this)) {
				continue;
			}
			if (e.solid) {
				if (e.collisionBoundsList.isEmpty()) {
					if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
						return true;
					}
				} else {
					for (Rectangle rectangle : e.collisionBoundsList) {
						if (e.getCollisionBounds(0f, 0f, rectangle).intersects(getCollisionBounds(xOffset, yOffset))) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}

	public Rectangle getCollisionBounds(float xOffset, float yOffset, Rectangle rectangle) {
		int xStart = (int) (x + rectangle.x + xOffset);
		int yStart = (int) (y + rectangle.y + yOffset);
		int width = rectangle.width;
		int height = rectangle.height;
		return new Rectangle(xStart, yStart, width, height);
	}

		public void addBoundingBox(Rectangle rectangle) {
		collisionBoundsList.add(rectangle);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUniqueName() {
		return uniqueName;
	}

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}
}
