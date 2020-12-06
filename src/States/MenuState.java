package States;

import javax.sound.sampled.*;
import java.awt.*;
import java.util.ArrayList;
import Variables.Handler;
import Graphics.Assets;
import Graphics.ScreenOverlay;
import Utils.GeneralUtils;

public class MenuState extends State {
	private Clip music;
	private int alpha = 255;
	private int yIndex;
	private ScreenOverlay screenOverlay;
	private Font menuFont;
	private FontMetrics fm;
	private int verticalSpace;
	private java.util.List<String> menuOptions;
	private boolean firstTime = true, fadeIn = false, fadeOut = false, fadeFinished = false;
	private FloatControl musicControl;
	private float volume;

	public MenuState(final Handler handler) {
		super(handler);
		menuOptions = new ArrayList<>();
		menuOptions.add("Start");
		menuOptions.add("Config");
		menuOptions.add("Some other fake option");
		initSounds();
		menuFont = Assets.serif;
		screenOverlay = new ScreenOverlay(handler);
	}

	@Override
	public void tick() {
		if (firstTime) {
			fadeIn = true;
			firstTime = false;
		}
		if (handler.getKeyManager().esc) {
			if (!handler.getKeyManager().isStillHoldingEsc()) {
				handler.getKeyManager().setStillHoldingEsc(true);
				if (fadeIn || fadeOut) {
					fadeFinished = true;
					fadeIn = false;
					fadeOut = false;
				} else {
					music.stop();
					State.setState(handler.getGame().gameState);
				}
			}
		}
		if (fadeFinished) {
			if (handler.getKeyManager().up) {
				if (!handler.getKeyManager().isStillHoldingUp()) {
					handler.getKeyManager().setStillHoldingUp(true);
					moveCursor("up");
					Assets.menuMove.play();
				}
			} else if (handler.getKeyManager().down) {
				if (!handler.getKeyManager().isStillHoldingDown()) {
					handler.getKeyManager().setStillHoldingDown(true);
					moveCursor("down");
					Assets.menuMove.play();
				}
			}
			if (handler.getKeyManager().enter) {
				if (!handler.getKeyManager().isStillholdingEnter()) {
					handler.getKeyManager().setStillholdingEnter(true);
					fadeOut = true;
					alpha = 0;
				}
			} else if (handler.getKeyManager().z) {
				if (!handler.getKeyManager().isStillHoldingZ()) {
					handler.getKeyManager().setStillHoldingZ(true);
					fadeOut = true;
					alpha = 0;
				}
			}
		}
		/*
		if(fadeFinished) {
			soundFile.stop();
			State.setState(handler.getGame().gameState);
		}
		*/
	}

	@Override
	public void render(Graphics g) {
		fm = g.getFontMetrics(menuFont);
		verticalSpace = fm.getAscent() * 3;
		g.setColor(Color.WHITE);
		if ((fadeOut || fadeIn) && !fadeFinished) {
			GeneralUtils.drawCenteredString(g, "spooky game", new Rectangle(0, 0, handler.getWidth(), handler.getHeight()), menuFont);
		}

		if (fadeFinished) {
			for (int i = 0; i < menuOptions.size(); i++) {
				Rectangle r = new Rectangle(0, 0, handler.getWidth(), handler.getHeight() + calculateYOffset(i));
				if (i == yIndex) {
					g.setColor(Color.YELLOW);
					Rectangle cursor = new Rectangle(r);
					cursor.x -= fm.stringWidth(menuOptions.get(i)) / 2 + fm.stringWidth(">");
					GeneralUtils.drawCenteredString(g, ">", cursor, menuFont);
					GeneralUtils.drawCenteredString(g, menuOptions.get(i), r, menuFont);
				} else {
					g.setColor(Color.WHITE);
					GeneralUtils.drawCenteredString(g, menuOptions.get(i), r, menuFont);
				}
			}
		}

		if (fadeIn) {
			if (alpha > 0) {
				alpha--;
				if (alpha >= 0)
					screenOverlay.overlayScreen(g, new Color(0, 0, 0, alpha));
			} else {
				fadeOut = true;
				fadeIn = false;
			}
		}
		if (fadeOut) {
			alpha++;
			if (fadeFinished) {
				volume = -.0007f * (float) (alpha * alpha);
				musicControl.setValue(volume > -80 ? volume : -80);
			}
			if (alpha < 256) {
				screenOverlay.overlayScreen(g, new Color(0, 0, 0, alpha));
			} else {
				screenOverlay.overlayScreen(g, new Color(0, 0, 0, 255));
				if (fadeFinished) {
					State.setState(handler.getGame().gameState);
					music.stop();
				}
			}
		}
		if (alpha >= 255 && !fadeFinished) {
			fadeOut = false;
			fadeFinished = true;
			screenOverlay.overlayScreen(g, new Color(0, 0, 0, 255));
		}
	}

	private void moveCursor(String dir) {
		if (dir.equals("down")) {
			if (yIndex == menuOptions.size() - 1) {
				yIndex = 0;
			} else {
				yIndex++;
			}
		} else if (dir.equals("up")) {
			if (yIndex == 0) {
				yIndex = menuOptions.size() - 1;
			} else {
				yIndex--;
			}
		}
	}

	private int calculateYOffset(int index) {
		int size = menuOptions.size();
		boolean even = size % 2 == 0;
		int middleIndex = (int) Math.ceil((double) size / 2);
		if (even) {
			int centerOffset = verticalSpace / 2;
			if (index == middleIndex - 1) {
				return -centerOffset;
			}
			if (index == middleIndex) {
				return centerOffset;
			}
			if (index < middleIndex) {
				return (index + 1 - middleIndex) * verticalSpace - centerOffset;
			}
			return (index - middleIndex) * verticalSpace + centerOffset;
		} else {
			if (index + 1 == middleIndex) {
				return 0;
			}
			//positive for indexes after middle, negative for indexes before middle
			return (index + 1 - middleIndex) * verticalSpace;
		}
	}

	private void initSounds() {
		try {
			music = Assets.menuMusic;
			musicControl = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
			musicControl.setValue(0);
			music.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
