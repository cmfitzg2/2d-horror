package Worlds;

import Entities.Creatures.Player;
import Entities.EntityManager;
import Variables.Handler;
import Tiles.Tile;
import java.awt.Graphics;
import Utils.Utils;
import Utils.GeneralUtils;

public abstract class World {
	protected Handler handler;
	private int width, height, id, spawnX, spawnY;
	private int[][] tiles;
	private String path;
	private boolean firstTime = true;
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
			GeneralUtils.levelFadeIn(handler);
			firstTime = false;
		}
		entityManager.tick();
		checkLoadZones();
		if (transitioningTo) {
			transitionTo();
		}
	}

	public void render(Graphics g) {
		renderTiles(g);
		entityManager.render(g);
	}

	private void transitionTo() {
		if (!handler.getGame().isFadeIn()) {
			GeneralUtils.levelFadeIn(handler);
		}
		if (handler.getGame().isFadeIn() && handler.getGame().isFinishedFadingIn()) {
			GeneralUtils.stopLevelFadeIn(handler);
			transitioningTo = false;
		}
	}

	protected void transitionFrom(World newWorld, float newX, float newY) {
		if (!handler.getGame().isFadeOut()) {
			GeneralUtils.levelFadeOut(handler);
		} else if (handler.getGame().isFinishedFadingOut()) {
			GeneralUtils.stopLevelFadeOut(handler, newWorld, newX, newY);
			transitioningTo = true;
		}
	}

	private void renderTiles(Graphics g) {
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

		for(int y=yStart; y<yEnd; y++) {
			for(int x=xStart; x<xEnd; x++) {
				getTile(x,y).render(g, (int) (x*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
	}

	public Tile getTile(int x, int y) {
		if(x<0 || y<0 || x>=width || y>=height)
			return Tile.black;

		Tile t = Tile.tiles[tiles[x][y]];
		if(t==null)
			return Tile.black;
		return t;
	}

	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);

		tiles = new int[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
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
}