package Variables;

import Entities.Creatures.Player;
import Game.Game;
import Graphics.GameCamera;
import Graphics.ScreenOverlay;
import Input.KeyManager;
import Input.MouseManager;
import Textboxes.EntityMessages;
import Worlds.World;
import Worlds.WorldManager;

public class Handler {
	private Game game;
	private World activeWorld;
	private EntityMessages entityMessages;
	private Flags flags;
	private boolean playerFrozen = false, isInMenu = false, gamePaused = false;
	private int worldNumber = 1;
	private WorldManager worldManager;
	private Player player;

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

	public World getActiveWorld() {
		return activeWorld;
	}

	public void setActiveWorld(World world) {
		this.activeWorld = world;
	}

	public int getWorldNumber()
	{
		return worldNumber;
	}

	public void setWorldNumber(int worldNumber)
	{
		this.worldNumber = worldNumber;
	}

	public EntityMessages getEntityMessages() {
		return entityMessages;
	}

	public void setEntityMessages(EntityMessages entityMessages) {
		this.entityMessages = entityMessages;
	}

	public Flags getFlags() {
		return flags;
	}

	public void setFlags(Flags flags) {
		this.flags = flags;
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

	public WorldManager getWorldManager() {
		return worldManager;
	}

	public void setWorldManager(WorldManager worldManager) {
		this.worldManager = worldManager;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
