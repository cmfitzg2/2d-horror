package States;

import Cutscenes.CutsceneManager;
import Variables.Handler;
import java.awt.Graphics;
import Graphics.ScreenOverlay;
import Worlds.World1;
import Worlds.WorldManager;

public class GameState extends State {
	private ScreenOverlay screenOverlay;

	//Worlds
	private WorldManager worldManager;

	//Cutscenes
	private CutsceneManager cutsceneManager;

	public GameState(Handler handler) {
		super(handler);
		screenOverlay = new ScreenOverlay(handler);
		cutsceneManager = new CutsceneManager(handler);
		handler.setCutsceneManager(cutsceneManager);
		worldManager = new WorldManager(handler, new World1(handler, "res/worlds/world4.txt", 1));
		handler.setWorldManager(worldManager);
	}

	@Override
	public void tick() {
		worldManager.tick();
		if (handler.getFlags().isCutsceneActive()) {
			cutsceneManager.tick();
		}
	}

	@Override
	public void render(Graphics g) {
		worldManager.render(g);
		if (handler.getFlags().isCutsceneActive()) {
			cutsceneManager.render(g);
		}
	}
}
