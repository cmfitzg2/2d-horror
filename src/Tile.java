

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//STATIC STUFF HERE
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grass = new GrassTile(0);
	public static Tile dirt = new DirtTile(1);
	public static Tile rock = new RockTile(2);
	public static Tile water = new WaterTile(3);
	public static Tile black = new BlackTile(4);
	public static Tile blackSolid = new BlackTileSolid(5);
	public static Tile gray = new GrayTile(6);
	public static Tile darkStoneWall = new DarkStoneWallTile(7);

	//CLASS
	
	public static final int TILEWIDTH = 64;
	public static final int TILEHEIGHT = 64;

	protected BufferedImage texture;
	protected final int id;
	protected final boolean isSolid;
	
	public Tile(BufferedImage texture, int id, boolean isSolid) {
		this.texture = texture;
		this.id = id;
		this.isSolid = isSolid;

		tiles[id] = this;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y)
	{
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
		return isSolid;
	}
	
	public int getId()
	{
		return id;
	}
}
