package Graphics;

import Entities.Entity;
import Variables.Handler;
import Tiles.Tile;

public class GameCamera {
	private float xOffset, yOffset, xOffsetCamera, yOffsetCamera;
	private Handler handler;
	
	public GameCamera(Handler handler, float xOffset, float yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		
	}
	
	public void checkBlankSpace() {
		if(xOffset<0)
			xOffset = 0;
		else if(xOffset>handler.getActiveWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth())
			xOffset = handler.getActiveWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
		if(yOffset<0)
			yOffset = 0;
		else if(yOffset>handler.getActiveWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight())
			yOffset = handler.getActiveWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
	}
	
	public void centerOnEntity(Entity e) {
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
		xOffsetCamera = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
		yOffsetCamera = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
		checkBlankSpace();
	}
	
	public void move(float xAmt, float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}

	public void updateCamera() {
		centerOnEntity(handler.getPlayer());
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

	public float getxOffsetCamera() {
		return xOffsetCamera;
	}

	public float getyOffsetCamera() {
		return yOffsetCamera;
	}
}
