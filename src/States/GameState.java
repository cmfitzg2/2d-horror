package States;

import Cutscenes.CutsceneManager;
import Entities.Creatures.Player;
import Variables.Handler;
import java.awt.Graphics;
import Graphics.ScreenOverlay;
import Worlds.*;

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
		worldManager = new WorldManager(handler, new MCHouse1(handler, WorldManager.MC_HOUSE_1_ID, new Player(handler, 700, 428, "Player")));
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
