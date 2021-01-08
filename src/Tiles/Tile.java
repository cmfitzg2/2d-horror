package Tiles;

import Tiles.Interior.*;
import Tiles.Overworld.*;
import Tiles.Overworld.BrickWall.*;
import Tiles.Overworld.Roof.*;

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
	public static Tile woodFloor = new WoodFloorTile(8);

	//brick wall
	public static Tile brickTopLeft = new BrickTopLeft(20);
	public static Tile brickTopMiddle = new BrickTopMiddle(21);
	public static Tile brickTopRight = new BrickTopRight(22);
	public static Tile brickMiddleLeft = new BrickMiddleLeft(23);
	public static Tile brickMiddleMiddle = new BrickMiddleMiddle(24);
	public static Tile brickMiddleRight = new BrickMiddleRight(25);
	public static Tile brickBottomLeft = new BrickBottomLeft(26);
	public static Tile brickBottomMiddle = new BrickBottomMiddle(27);
	public static Tile brickBottomRight = new BrickBottomRight(28);

	//roof
	public static Tile roofBackLeft = new RoofBackLeft(29);
	public static Tile roofBackMiddle = new RoofBackMiddle(30);
	public static Tile roofBackRight = new RoofBackRight(31);
	public static Tile roofMiddleLeft = new RoofMiddleLeft(32);
	public static Tile roofMiddleMiddle = new RoofMiddleMiddle(33);
	public static Tile roofMiddleRight = new RoofMiddleRight(34);
	public static Tile roofFrontLeft = new RoofFrontLeft(35);
	public static Tile roofFrontMiddle = new RoofFrontMiddle(36);
	public static Tile roofFrontRight = new RoofFrontRight(37);
	public static Tile lowerRoofLeft = new LowerRoofLeft(38);
	public static Tile lowerRoofRight = new LowerRoofRight(39);
	public static Tile roofPivotBottomRight = new RoofPivotBottomRight(40);
	public static Tile roofPivotBottomLeft = new RoofPivotBottomLeft(41);
	public static Tile roofPivotTopRight = new RoofPivotTopRight(42);
	public static Tile roofPivotTopLeft = new RoofPivotTopLeft(43);
	public static Tile roofFlatLeftBottomRight = new RoofFlatLeftBottomRight(44);
	public static Tile roofFlatLeftRight = new RoofFlatLeftRight(45);
	public static Tile roofFlatTopLeftBottom = new RoofFlatTopLeftBottom(46);
	public static Tile roofFlatTopBottom = new RoofFlatTopBottom(47);
	public static Tile roofFlatTopRightBottom= new RoofFlatTopRightBottom(48);
	public static Tile roofFlatTopLeft = new RoofFlatTopLeft(49);
	public static Tile roofFlatTop = new RoofFlatTop(50);
	public static Tile roofFlatTopRight = new RoofFlatTopRight(51);
	public static Tile roofFlatLeftBottom = new RoofFlatLeftBottom(52);
	public static Tile roofFlatBottom = new RoofFlatBottom(53);
	public static Tile roofFlatRightBottom = new RoofFlatRightBottom(54);
	public static Tile roofFlatLeft = new RoofFlatLeft(55);
	public static Tile roofFlat = new RoofFlat(56);
	public static Tile roofFlatRight = new RoofFlatRight(57);

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
