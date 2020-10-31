

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GameState extends State {
	private ScreenOverlay screenOverlay;
	private long startTime;
	private World world1, world2;
	private Rectangle loadZoneWest, loadZoneEast, loadZoneNorth, loadZoneSouth;
	public static boolean playStarted = false;
	static AudioClip overworldMusic;
	static AudioClip overworldMusicLoop;
	private boolean firstTime = true, justTransitioned = false;
	boolean switcher = false;
	private int alpha = 255;
	private boolean fadeIn = true;

	public GameState(Handler handler) {
		super(handler);
		world1 = new World(handler,"world1.txt");
		world2 = new World(handler,"world2.txt");
		screenOverlay = new ScreenOverlay(handler);
		handler.setWorld(world1);
	}

	@Override
	public void tick() {
		getLoadZones();

		if (firstTime) {
			world1.tick();
			world2.tick();
			handler.setPlayerFrozen(true);
			firstTime = false;
		}

		if(handler.getWorldNumber() == 1) {
			world1.tick();

			if(Player.playerRec.intersects(loadZoneWest)) {
				handler.setWorld(world2);
				handler.setWorldNumber(2);
				justTransitioned = true;
				world2.tick();
			}
		}


		else if(handler.getWorld() == world2) {
			world2.tick();

			if(Player.playerRec.intersects(loadZoneEast)) {
				handler.setWorld(world1);
				handler.setWorldNumber(1);
				justTransitioned = true;
				world1.tick();
			}
		}
	}

	@Override
	public void render(Graphics graphics) {
		if (handler.getWorldNumber() == 1) {
			world1.render(graphics);
			if (justTransitioned) {
				handler.getWorld().getEntityManager().getPlayer().setX(20);
				handler.getWorld().getEntityManager().getPlayer().setY(496);
				justTransitioned = false;
			}
			graphics.drawRect((int) -handler.getGameCamera().getxOffset(),(int) (511 - handler.getGameCamera().getyOffset()),10,64);
		} else if(handler.getWorldNumber() == 2) {
			world2.render(graphics);
			if (justTransitioned) {
				handler.getWorld().getEntityManager().getPlayer().setX(2458);
				handler.getWorld().getEntityManager().getPlayer().setY(304);
				justTransitioned = false;
			}
			graphics.drawRect((int) (2528 - handler.getGameCamera().getxOffset()),(int) (320 - handler.getGameCamera().getyOffset()),10,64);
		}
		graphics.setColor(Color.WHITE);
		graphics.setColor(Color.BLACK);

		if (fadeIn) {
			if (alpha > 0) {
				alpha-= 3;
				if (alpha >= 0)
					screenOverlay.overlayScreen(graphics, new Color(0, 0, 0, alpha));
			} else {
				fadeIn = false;
				handler.setPlayerFrozen(false);
			}
		}
	}

	private void getLoadZones() {
		if(handler.getWorldNumber() == 1) {
			loadZoneWest = new Rectangle((int) -handler.getGameCamera().getxOffset(),(int) (511 - handler.getGameCamera().getyOffset()),10,64);
		} else if(handler.getWorldNumber() == 2) {
			loadZoneEast = new Rectangle((int) (2528 - handler.getGameCamera().getxOffset()),(int) (320 - handler.getGameCamera().getyOffset()),10,64);
		}
	}

	public void playMusic() {
		playStarted = true;
		overworldMusic = Applet.newAudioClip(getClass().getResource("/sounds/ocean.au"));
		overworldMusicLoop = Applet.newAudioClip(getClass().getResource("/sounds/ocean.au"));

		overworldMusic.play();
		startTime = System.currentTimeMillis();
	}

	public static void stopMusic() {
		playStarted = false;
		overworldMusic.stop();
	}

}
