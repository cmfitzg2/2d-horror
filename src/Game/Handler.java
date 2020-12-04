package Game;

import Entities.Creatures.Player;
import Game.Game;
import Graphics.GameCamera;
import Graphics.ScreenOverlay;
import Input.KeyManager;
import Input.MouseManager;
import Worlds.World;

public class Handler {
	private Game game;
	private World world;
	private Flags flags;
	private boolean playerFrozen = false, isInMenu = false, gamePaused = false;
	private int worldNumber = 1;

	public Handler(Game game)
	{
		this.game = game;
	}

	public KeyManager getKeyManager()
	{
		return game.getKeyManager();
	}

	public MouseManager getMouseManager()
	{
		return game.getMouseManager();
	}

	public GameCamera getGameCamera()
	{
		return game.getGameCamera();
	}

	public int getWidth()
	{
		return game.getWidth();
	}

	public int getHeight()
	{
		return game.getHeight();
	}

	public Game getGame() {
		return game;
	}

	public boolean isPlayerFrozen()
	{
		return playerFrozen;
	}

	public void setPlayerFrozen(boolean playerFrozen)
	{
		this.playerFrozen = playerFrozen;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public int getWorldNumber()
	{
		return worldNumber;
	}

	public void setWorldNumber(int worldNumber)
	{
		this.worldNumber = worldNumber;
	}

	public Flags getFlags() {
		return flags;
	}

	public void setFlags(Flags flags) {
		this.flags = flags;
	}

	public Player getPlayer() {
		return world.getEntityManager().getPlayer();
	}

	public ScreenOverlay getScreenOverlay() {
		return game.getScreenOverlay();
	}

	public boolean isInMenu() {
		return isInMenu;
	}

	public void setInMenu(boolean inMenu) {
		isInMenu = inMenu;
	}

	public boolean isGamePaused() {
		return gamePaused;
	}

	public void setGamePaused(boolean gamePaused) {
		this.gamePaused = gamePaused;
	}
}
