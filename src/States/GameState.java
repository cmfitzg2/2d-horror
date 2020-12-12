package States;

import Variables.Handler;
import java.awt.Graphics;
import Graphics.ScreenOverlay;
import Worlds.World1;
import Worlds.WorldManager;

public class GameState extends State {
	private ScreenOverlay screenOverlay;

	//Worlds
	private WorldManager worldManager;

	public GameState(Handler handler) {
		super(handler);
		screenOverlay = new ScreenOverlay(handler);
		worldManager = new WorldManager(handler, new World1(handler, "C:\\Users\\CFitzgerald\\dev\\personal\\out\\production\\2d-horror\\res/worlds/world1.txt", 1));
		handler.setWorldManager(worldManager);
	}

	@Override
	public void tick() {
		worldManager.tick();
	}

	@Override
	public void render(Graphics g) {
		worldManager.render(g);
	}
}
