package Game;

import Input.KeyManager;
import Input.MouseManager;
import States.GameState;
import States.MenuState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import Graphics.*;
import States.State;
import Textboxes.EntityMessages;
import Utils.GeneralUtils;
import Variables.Flags;
import Variables.Handler;

public class Game implements Runnable {

	private Display display;
	private int width, height;
	public String title;

	private Thread thread;
	private boolean running = false;

	private BufferStrategy bufferStrategy;
	private Graphics g;

	//Screen fading
	public ScreenOverlay screenOverlay;
	private float alpha, alphaThreshold = 0;
	private boolean fadeOut = false, fadeIn = false, finishedFadingIn = false, finishedFadingOut = false;

	//States
	public State gameState;
	public State menuState;

	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;

	//Camera
	private GameCamera gameCamera;

	//Handler
	private Handler handler;

	//NPC messages
	private EntityMessages entityMessages;

	private Flags flags;

	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}

	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();

		handler = new Handler(this);
		entityMessages = new EntityMessages(handler);
		handler.setEntityMessages(entityMessages);
		flags = new Flags(handler);
		handler.setFlags(flags);
		gameCamera = new GameCamera(handler, 0,0);
		screenOverlay = new ScreenOverlay(handler);

		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		if (!handler.getFlags().isPrologue()) {
			GeneralUtils.levelFadeIn(handler, -1);
		}
		State.setState(menuState);
	}

	private void tick() {
		keyManager.tick();
		if (State.getState() != null) {
			State.getState().tick();
		}
	}

	private void render() {
		bufferStrategy = display.getCanvas().getBufferStrategy();
		if (bufferStrategy == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bufferStrategy.getDrawGraphics();
		//clear screen
		g.clearRect(0, 0, width, height);

		//draw
		if (State.getState() != null) {
			State.getState().render(g);
		} else {
			System.out.println("no state");
		}

		//fade the screen in & out after rendering everything else
		checkScreenFade();

		//done drawing
		bufferStrategy.show();
		g.dispose();
	}

	private void checkScreenFade() {
		if (fadeOut) {
			alpha += alphaThreshold;
			if (alpha >= 255) {
				alpha = 255;
				finishedFadingOut = true;
			}
			screenOverlay.overlayScreen(g, new Color(0, 0, 0, (int) alpha));
		}
		if (fadeIn) {
			alpha -= alphaThreshold;
			if (alpha < 0) {
				alpha = 0;
				finishedFadingIn = true;
			}
			screenOverlay.overlayScreen(g, new Color(0, 0, 0, (int) alpha));
		}
	}

	@Override
	public void run() {
		init();

		int fps = 60;
		double timePerTick = (double) 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
				delta = 0;
			}
		}
		stop();
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public GameCamera getGameCamera() {
		return gameCamera;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void fadeIn(int frameCount) {
		setFadeIn(true, true);
		finishedFadingIn = false;
		alpha = 255;
		alphaThreshold = 255f / frameCount;
	}

	public boolean isFadeIn() {
		return fadeIn;
	}

	public void fadeOut(int frameCount) {
		setFadeOut(true, true);
		finishedFadingOut = false;
		alpha = 0;
		alphaThreshold = 255f / frameCount;
	}

	public boolean isFadeOut() {
		return fadeOut;
	}

	public ScreenOverlay getScreenOverlay() {
		return screenOverlay;
	}

	public void setFadeOut(boolean fadeOut, boolean setPlayerFrozen) {
		handler.setPlayerFrozen(setPlayerFrozen);
		this.fadeOut = fadeOut;
	}

	public void setFadeIn(boolean fadeIn, boolean setPlayerFrozen) {
		handler.setPlayerFrozen(setPlayerFrozen);
		this.fadeIn = fadeIn;
	}

	public boolean isFinishedFadingIn() {
		return finishedFadingIn;
	}

	public void setFinishedFadingIn(boolean finishedFadingIn) {
		this.finishedFadingIn = finishedFadingIn;
	}

	public boolean isFinishedFadingOut() {
		return finishedFadingOut;
	}

	public void setFinishedFadingOut(boolean finishedFadingOut) {
		this.finishedFadingOut = finishedFadingOut;
	}

	public float getAlpha() {
		return alpha;
	}
}
