package Game;

import java.awt.*;

public class Launcher {
	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Game game = new Game("Game", 1600, 800);
		game.start();
	}

}
