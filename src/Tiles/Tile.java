package Tiles;

import Tiles.Interior.*;
import Tiles.Interior.HouseWall.Blue.*;
import Tiles.Interior.HouseWall.Red.*;
import Tiles.Interior.HouseWall.Green.*;
import Tiles.Interior.HouseWall.Orange.*;
import Tiles.Interior.School.TiledFloorBlack;
import Tiles.Interior.School.TiledFloorWhite;
import Tiles.Interior.School.WhiteBrickBottom;
import Tiles.Interior.School.WhiteBrickMiddle;
import Tiles.Interior.WallBorder.*;
import Tiles.Interior.BathroomWall.*;
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
	public static Tile diamondTileBlue = new DiamondTileBlue(9);
	public static Tile diamondTileGreen = new DiamondTileGreen(10);
	public static Tile diamondTileWood = new DiamondTileWood(11);
	public static Tile diamondTileDouble = new DiamondTileDouble(12);
	public static Tile bathroomTile = new BathroomTile(13);
	public static Tile whiteBrickMiddle = new WhiteBrickMiddle(14);
	public static Tile whiteBrickBottom = new WhiteBrickBottom(15);
	public static Tile tiledFloorWhite = new TiledFloorWhite(16);
	public static Tile tiledFloorBlack = new TiledFloorBlack(17);

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

	//house wall borders
	public static Tile wallBorderBottom = new WallBorderBottom(58);
	public static Tile wallBorderCornerBottomLeft = new WallBorderCornerBottomLeft(59);
	public static Tile wallBorderCornerBottomRight = new WallBorderCornerBottomRight(60);
	public static Tile wallBorderCornerTopLeft = new WallBorderCornerTopLeft(61);
	public static Tile wallBorderCornerTopRight = new WallBorderCornerTopRight(62);
	public static Tile wallBorderLeft = new WallBorderLeft(63);
	public static Tile wallBorderLeftBottom = new WallBorderLeftBottom(64);
	public static Tile wallBorderLeftTop = new WallBorderLeftTop(65);
	public static Tile wallBorderRight = new WallBorderRight(66);
	public static Tile wallBorderRightBottom = new WallBorderRightBottom(67);
	public static Tile wallBorderRightTop = new WallBorderRightTop(68);
	public static Tile wallBorderTop = new WallBorderTop(69);
	public static Tile wallBorderVerticalLeft = new WallBorderVerticalLeft(70);
	public static Tile wallBorderVerticalRight = new WallBorderVerticalRight(71);

	//house wall
	public static Tile houseWallTopLeftBlue = new HouseWallTopLeftBlue(72);
	public static Tile houseWallTopMiddleBlue = new HouseWallTopMiddleBlue(73);
	public static Tile houseWallTopRightBlue = new HouseWallTopRightBlue(74);
	public static Tile houseWallMiddleLeftBlue = new HouseWallMiddleLeftBlue(75);
	public static Tile houseWallMiddleMiddleBlue = new HouseWallMiddleMiddleBlue(76);
	public static Tile houseWallMiddleRightBlue = new HouseWallMiddleRightBlue(77);
	public static Tile houseWallBottomLeftBlue = new HouseWallBottomLeftBlue(78);
	public static Tile houseWallBottomMiddleBlue = new HouseWallBottomMiddleBlue(79);
	public static Tile houseWallBottomRightBlue = new HouseWallBottomRightBlue(80);

	public static Tile houseWallTopLeftGreen = new HouseWallTopLeftGreen(81);
	public static Tile houseWallTopMiddleGreen = new HouseWallTopMiddleGreen(82);
	public static Tile houseWallTopRightGreen = new HouseWallTopRightGreen(83);
	public static Tile houseWallMiddleLeftGreen = new HouseWallMiddleLeftGreen(84);
	public static Tile houseWallMiddleMiddleGreen = new HouseWallMiddleMiddleGreen(85);
	public static Tile houseWallMiddleRightGreen = new HouseWallMiddleRightGreen(86);
	public static Tile houseWallBottomLeftGreen = new HouseWallBottomLeftGreen(87);
	public static Tile houseWallBottomMiddleGreen = new HouseWallBottomMiddleGreen(88);
	public static Tile houseWallBottomRightGreen = new HouseWallBottomRightGreen(89);

	public static Tile houseWallTopLeftRed = new HouseWallTopLeftRed(90);
	public static Tile houseWallTopMiddleRed = new HouseWallTopMiddleRed(91);
	public static Tile houseWallTopRightRed = new HouseWallTopRightRed(92);
	public static Tile houseWallMiddleLeftRed = new HouseWallMiddleLeftRed(93);
	public static Tile houseWallMiddleMiddleRed = new HouseWallMiddleMiddleRed(94);
	public static Tile houseWallMiddleRightRed = new HouseWallMiddleRightRed(95);
	public static Tile houseWallBottomLeftRed = new HouseWallBottomLeftRed(96);
	public static Tile houseWallBottomMiddleRed = new HouseWallBottomMiddleRed(97);
	public static Tile houseWallBottomRightRed = new HouseWallBottomRightRed(98);

	public static Tile houseWallTopLeftOrange = new HouseWallTopLeftOrange(99);
	public static Tile houseWallTopMiddleOrange = new HouseWallTopMiddleOrange(100);
	public static Tile houseWallTopRightOrange = new HouseWallTopRightOrange(101);
	public static Tile houseWallMiddleLeftOrange = new HouseWallMiddleLeftOrange(102);
	public static Tile houseWallMiddleMiddleOrange = new HouseWallMiddleMiddleOrange(103);
	public static Tile houseWallMiddleRightOrange = new HouseWallMiddleRightOrange(104);
	public static Tile houseWallBottomLeftOrange = new HouseWallBottomLeftOrange(105);
	public static Tile houseWallBottomMiddleOrange = new HouseWallBottomMiddleOrange(106);
	public static Tile houseWallBottomRightOrange = new HouseWallBottomRightOrange(107);

	public static Tile bathroomWallTopLeft = new BathroomWallTopLeft(108);
	public static Tile bathroomWallTopMiddle = new BathroomWallTopMiddle(109);
	public static Tile bathroomWallTopRight = new BathroomWallTopRight(110);
	public static Tile bathroomWallMiddleLeft = new BathroomWallMiddleLeft(111);
	public static Tile bathroomWallMiddleMiddle = new BathroomWallMiddleMiddle(112);
	public static Tile bathroomWallMiddleRight = new BathroomWallMiddleRight(113);
	public static Tile bathroomWallBottomLeft = new BathroomWallBottomLeft(114);
	public static Tile bathroomWallBottomMiddle = new BathroomWallBottomMiddle(115);
	public static Tile bathroomWallBottomRight = new BathroomWallBottomRight(116);

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
