package States;

import Entities.Creatures.Player;
import Entities.EntityManager;
import Game.Handler;
import java.awt.Graphics;
import Graphics.ScreenOverlay;
import Worlds.World1;

public class GameState extends State {
	private ScreenOverlay screenOverlay;
	//Entities
	private EntityManager entityManager;

	public GameState(Handler handler) {
		super(handler);
		World1 world1 = new World1(handler, "res/worlds/world1.txt", 3);
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

}
