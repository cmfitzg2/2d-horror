package Tiles;

import Tiles.Overworld.BrickWall.*;
import Tiles.Overworld.Roof.*;
import Tiles.Interior.DarkStoneWallTile;
import Tiles.Overworld.DirtTile;
import Tiles.Overworld.GrassTile;
import Tiles.Overworld.RockTile;
import Tiles.Overworld.WaterTile;

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

	//brick wall
	public static Tile brickTopLeft = new BrickTopLeft(8);
	public static Tile brickTopMiddle = new BrickTopMiddle(9);
	public static Tile brickTopRight = new BrickTopRight(10);
	public static Tile brickMiddleLeft = new BrickMiddleLeft(11);
	public static Tile brickMiddleMiddle = new BrickMiddleMiddle(12);
	public static Tile brickMiddleRight = new BrickMiddleRight(13);
	public static Tile brickBottomLeft = new BrickBottomLeft(14);
	public static Tile brickBottomMiddle = new BrickBottomMiddle(15);
	public static Tile brickBottomRight = new BrickBottomRight(16);

	//roof
	public static Tile roofBackLeft = new RoofBackLeft(17);
	public static Tile roofBackMiddle = new RoofBackMiddle(18);
	public static Tile roofBackRight = new RoofBackRight(19);
	public static Tile roofMiddleLeft = new RoofMiddleLeft(20);
	public static Tile roofMiddleMiddle = new RoofMiddleMiddle(21);
	public static Tile roofMiddleRight = new RoofMiddleRight(22);
	public static Tile roofFrontLeft = new RoofFrontLeft(23);
	public static Tile roofFrontMiddle = new RoofFrontMiddle(24);
	public static Tile roofFrontRight = new RoofFrontRight(25);
	public static Tile lowerRoofLeft = new LowerRoofLeft(26);
	public static Tile lowerRoofRight = new LowerRoofRight(27);
	public static Tile roofPivotBottomRight = new RoofPivotBottomRight(28);
	public static Tile roofPivotBottomLeft = new RoofPivotBottomLeft(29);
	public static Tile roofPivotTopRight = new RoofPivotTopRight(30);
	public static Tile roofPivotTopLeft = new RoofPivotTopLeft(31);
	public static Tile roofFlatLeftBottomRight = new RoofFlatLeftBottomRight(32);
	public static Tile roofFlatLeftRight = new RoofFlatLeftRight(33);
	public static Tile roofFlatTopLeftBottom = new RoofFlatTopLeftBottom(34);
	public static Tile roofFlatTopBottom = new RoofFlatTopBottom(35);
	public static Tile roofFlatTopRightBottom= new RoofFlatTopRightBottom(36);
	public static Tile roofFlatTopLeft = new RoofFlatTopLeft(37);
	public static Tile roofFlatTop = new RoofFlatTop(38);
	public static Tile roofFlatTopRight = new RoofFlatTopRight(39);
	public static Tile roofFlatLeftBottom = new RoofFlatLeftBottom(40);
	public static Tile roofFlatBottom = new RoofFlatBottom(41);
	public static Tile roofFlatRightBottom = new RoofFlatRightBottom(42);
	public static Tile roofFlatLeft = new RoofFlatLeft(43);
	public static Tile roofFlat = new RoofFlat(44);
	public static Tile roofFlatRight = new RoofFlatRight(45);

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
