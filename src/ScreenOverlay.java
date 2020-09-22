import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ScreenOverlay {
	int gameWidth = 0;
	int gameHeight = 0;
	Handler handler;
	
	public ScreenOverlay(Handler handler) {
		this.handler = handler;
		gameWidth = handler.getGame().getWidth();
		gameHeight = handler.getGame().getHeight();
	}
	
	void overlayScreen(Graphics graphics, Color c) {
		graphics.setColor(c);
		graphics.fillRect(0, 0, gameWidth, gameHeight);
	}

	public void drawVision(Graphics graphics) {
		if (!handler.getFlags().isVisionLimited() || handler.getFlags().isViewingArt()) {
			return;
		}
		int xOffsetCamera = (int) handler.getGameCamera().getxOffsetCamera();
		int yOffsetCamera = (int) handler.getGameCamera().getyOffsetCamera();
		int xOffset = (int) handler.getGameCamera().getxOffset();
		int yOffset = (int) handler.getGameCamera().getyOffset();
		int scale = 0;

		//find the smaller dimension and constrain it to that one
		if (gameWidth > gameHeight) {
			while (scale * Assets.tunnelVision[0].getWidth() < gameWidth * 3) {
				scale++;
			}
		} else {
			while (scale * Assets.tunnelVision[0].getHeight() < gameHeight * 3) {
				scale++;
			}
		}
		int xScale = Assets.tunnelVision[0].getWidth() * scale;
		int yScale = Assets.tunnelVision[0].getHeight() * scale;
		int xDrawFrom = -Assets.tunnelVision[0].getWidth() * ((scale - 1) / 2) + ((gameWidth - 900) / 2);
		int yDrawFrom = -Assets.tunnelVision[0].getHeight() * ((scale - 1) / 2) + ((gameHeight - 640) / 2);
		if (scale % 2 == 0) {
			//i don't remember why i do this sorry
			xDrawFrom -= 900 / 2;
			yDrawFrom -= 640 / 2;
		}

		//we need to check the 4 cardinal directions and the diagonals to figure out where to center the vision
		if (xOffsetCamera < 0) {
			xDrawFrom += xOffsetCamera;
		} else if (xOffsetCamera > xOffset) {
			xDrawFrom += xOffsetCamera - xOffset;
		}
		if (yOffsetCamera < 0) {
			yDrawFrom += yOffsetCamera;
		} else if (yOffsetCamera > yOffset) {
			yDrawFrom += yOffsetCamera - yOffset;
		}

		if (handler.getFlags().isVisionLimited()) {
			overlayScreen(graphics, new Color(0, 0, 120, 100));
			graphics.drawImage(Assets.tunnelVision[Assets.tunnelVision.length - 1], xDrawFrom, yDrawFrom, xScale, yScale, null);
		}
	}

	public void drawArt(Graphics graphics, BufferedImage art) {
		int scale = 1;
		if (!handler.getFlags().isViewingArt()) {
			return;
		}
		//this texture looks alright even when it's stretched so let's not worry about constraints and just fill the window
		graphics.drawImage(Assets.wall, 0, 0, handler.getWidth(), handler.getHeight(), null);

		//find the smaller dimension and constrain it to that one
		if (handler.getWidth() < handler.getHeight()) {
			while (handler.getWidth() < Assets.paintingWidth / scale) {
				scale++;
			}
		} else {
			while (handler.getHeight() < Assets.paintingHeight / scale) {
				scale++;
			}
		}
		graphics.drawImage(art, handler.getWidth() / 2 - (Assets.paintingWidth / (2 * scale)),
				handler.getHeight() / 2 - (Assets.paintingHeight / (2 * scale)),
				Assets.paintingWidth / scale, Assets.paintingHeight / scale, null);

	}
}
