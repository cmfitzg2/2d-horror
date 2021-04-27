package Worlds;

import Entities.Creatures.Creature;
import Entities.Creatures.Player;
import Entities.Entity;
import Entities.EntityManager;
import Variables.GeneralConstants;
import Variables.Handler;
import Tiles.Tile;
import java.awt.Graphics;
import Utils.Utils;
import Utils.GeneralUtils;

public abstract class World {
	protected Handler handler;
	private int width, height, id;
	private int[][] tiles;
	private String path;
	protected boolean firstTime = true, fadeIn = true, firstRender = true;
	public boolean transitioningTo = true, transitioningFrom = false;
	//Entities
	protected EntityManager entityManager;

	protected int frameIndex = 1;

	public World(Handler handler, String path, int id, Player player) {
		this.handler = handler;
		this.id = id;
		this.path = path;
		if (null != player) {
			handler.setPlayer(player);
			entityManager = new EntityManager(handler, player);
		} else {
			entityManager = new EntityManager(handler, handler.getPlayer());
		}
		loadWorld(path);
	}

	protected void tick() {
		if (firstTime) {
			addEntities();
			if (fadeIn) {
				GeneralUtils.levelFadeIn(handler, -1);
			}
			firstTime = false;
		}
		entityManager.tick();
		if (!transitioningTo) {
			checkLoadZones();
		}
		if (transitioningTo) {
			transitionTo();
		}
	}

	public void render(Graphics g) {
		if (firstRender) {
			firstRender = false;
			return;
		}
		renderTiles(g);
		entityManager.render(g);
	}

	protected void transitionTo() {
		if (!handler.getGame().isFadeIn() && fadeIn) {
			GeneralUtils.levelFadeIn(handler, -1);
		}
		if (handler.getGame().isFadeIn() && handler.getGame().isFinishedFadingIn()) {
			GeneralUtils.stopLevelFadeIn(handler, false);
			transitioningTo = false;
		}
	}

	public void transitionFrom(World newWorld, float newX, float newY) {
		transitionFrom(newWorld, newX, newY, GeneralConstants.levelTransitionFrames);
	}

	public void transitionFrom(World newWorld, float newX, float newY, int transitionFrames) {
		if (!handler.getGame().isFadeOut()) {
			GeneralUtils.levelFadeOut(handler, transitionFrames);
			transitioningFrom = true;
		} else if (handler.getGame().isFinishedFadingOut()) {
			GeneralUtils.stopLevelFadeOut(handler, newWorld, newX, newY, true);
			transitioningTo = true;
			firstRender = true;
			transitioningFrom = false;
		}
	}

	public void renderTiles(Graphics g) {
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				getTile(x,y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.blackSolid;
		}
		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null) {
			return Tile.black;
		}
		return t;
	}

	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\t");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		tiles = new int[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 2]);
			}
		}
	}

	protected void resetEntityMessages() {
		for (Entity e : entityManager.getEntities()) {
			if (e instanceof Creature) {
				((Creature) e).setMessageNumber(1);
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	protected abstract void checkLoadZones();

	protected abstract void addEntities();

	protected abstract void load();

	public int getId() {
		return id;
	}
}
