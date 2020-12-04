package States;

import Entities.Creatures.Player;
import Entities.EntityManager;
import Game.Handler;
import Worlds.World;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Rectangle;
import Graphics.ScreenOverlay;

public class GameState extends State {
	private ScreenOverlay screenOverlay;
	private long startTime;
	private World world1, world2;
	private Rectangle loadZoneWest, loadZoneEast, loadZoneNorth, loadZoneSouth;
	public static boolean playStarted = false;
	static AudioClip overworldMusic;
	static AudioClip overworldMusicLoop;
	private boolean firstTime = true, justTransitioned = false;
	private int alpha = 255;
	private boolean fadeIn = true;
	//Entities
	private EntityManager entityManager;

	public GameState(Handler handler) {
		super(handler);
		world1 = new World(handler,"world1.txt");
		world2 = new World(handler,"world2.txt");
		screenOverlay = new ScreenOverlay(handler);
		handler.setWorld(world1);
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
	}

	@Override
	public void tick() {
		entityManager.tick();
	}

	@Override
	public void render(Graphics graphics) {
		entityManager.render(graphics);
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
