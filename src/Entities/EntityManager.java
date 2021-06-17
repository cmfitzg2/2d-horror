package Entities;

import Entities.Creatures.Player;
import Variables.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class EntityManager {

	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	private Comparator<Entity> renderSorter = (Entity a, Entity b) -> {
		if (b.customRenderVsEntity()) {
			int val = b.renderVsEntity(a);
			if (val != 0) {
				return val;
			}
		} else if (a.customRenderVsEntity()) {
			int val = a.renderVsEntity(b);
			if (val != 0) {
				return -val;
			}
		}
		if (a.getY() + a.getHeight() < b.getY() + b.getHeight()) {
			return -1;
		} else if (a.getY() + a.getHeight() > b.getY() + b.getHeight()) {
			return 1;
		}
		return 0;
	};

	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<>();
		addEntity(player);
	}

	public void tick() {
		entities.sort(renderSorter);
		if (!handler.isGamePaused()) {
			for (int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.tick();
				if (!e.isActive())
					entities.remove(e);
			}
		} else {
			if (handler.isInMenu()) {
				player.tick();
			}
		}
	}

	public void render(Graphics g) {
		for (Entity e : entities) {
			e.preRender(g);
		}
		for (Entity e : entities) {
			e.render(g);
		}
		for (Entity e : entities) {
			e.postRender(g);
		}
		for (Entity e : entities) {
			e.finalRender(g);
		}
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	public void removeEntity(Entity e) {
		entities.remove(e);
	}

	public Entity getEntityByUid(String uid) {
		for (Entity e : entities) {
			if (e.getUniqueName() != null) {
				if (e.getUniqueName().equals(uid)) {
					return e;
				}
			}
		}
		return null;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
}
