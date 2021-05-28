package Graphics;

import Utils.ImageLoader;
import javax.sound.sampled.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Assets {

	public static final int width = 32, height = 32, biggerWidth = 48, biggerHeight = 48;
	public static final int paintingWidth = 815, paintingHeight = 820;
	public static BufferedImage dirt, grass, stone, water, black, gray, darkStoneWall, woodFloor, diamondTileBlue,
			diamondTileGreen, diamondTileWood, diamondTileDouble, bathroomTile, whiteBrickMiddle, whiteBrickBottom,
			tiledFloorWhite, tiledFloorBlack;
	public static BufferedImage cobblestoneLeft, cobblestoneRight, accentedGrass1, accentedGrass2, accentedGrass3,
			accentedGrassDark1, accentedGrassDark2, accentedGrassDark3, flowerGrass1, flowerGrass2, flowerGrass3;
	public static BufferedImage playerDownNormal, playerUpNormal, playerLeftNormal, playerRightNormal,
			playerDownTransparent, playerUpTransparent, playerLeftTransparent, playerRightTransparent;
	public static BufferedImage houseWallTopLeftBlue, houseWallTopMiddleBlue, houseWallTopRightBlue, houseWallMiddleLeftBlue, houseWallMiddleMiddleBlue,
			houseWallMiddleRightBlue, houseWallBottomLeftBlue, houseWallBottomMiddleBlue, houseWallBottomRightBlue;
	public static BufferedImage houseWallTopLeftGreen, houseWallTopMiddleGreen, houseWallTopRightGreen, houseWallMiddleLeftGreen, houseWallMiddleMiddleGreen,
			houseWallMiddleRightGreen, houseWallBottomLeftGreen, houseWallBottomMiddleGreen, houseWallBottomRightGreen;
	public static BufferedImage houseWallTopLeftOrange, houseWallTopMiddleOrange, houseWallTopRightOrange, houseWallMiddleLeftOrange, houseWallMiddleMiddleOrange,
			houseWallMiddleRightOrange, houseWallBottomLeftOrange, houseWallBottomMiddleOrange, houseWallBottomRightOrange;
	public static BufferedImage houseWallTopLeftRed, houseWallTopMiddleRed, houseWallTopRightRed, houseWallMiddleLeftRed, houseWallMiddleMiddleRed,
			houseWallMiddleRightRed, houseWallBottomLeftRed, houseWallBottomMiddleRed, houseWallBottomRightRed;
	public static BufferedImage bathroomWallTopLeft, bathroomWallTopMiddle, bathroomWallTopRight, bathroomWallMiddleLeft, bathroomWallMiddleMiddle,
			bathroomWallMiddleRight, bathroomWallBottomLeft, bathroomWallBottomMiddle, bathroomWallBottomRight;
	public static BufferedImage patternedWallTopLeft, patternedWallTopMiddle, patternedWallTopRight, patternedWallMiddleLeft, patternedWallMiddleMiddle,
			patternedWallMiddleRight, patternedWallBottomLeft, patternedWallBottomMiddle, patternedWallBottomRight;
	public static BufferedImage patternedCarpetTopLeft, patternedCarpetTopMiddle, patternedCarpetTopRight, patternedCarpetMiddleLeft, patternedCarpetMiddleMiddle,
			patternedCarpetMiddleRight, patternedCarpetBottomLeft, patternedCarpetBottomMiddle, patternedCarpetBottomRight,
			patternedCarpetCornerBottomLeft, patternedCarpetCornerBottomRight;
	public static BufferedImage damagedWallTopLeft, damagedWallTopMiddle, damagedWallTopRight, damagedWallMiddleLeft, damagedWallMiddleMiddle,
			damagedWallMiddleRight, damagedWallBottomLeft, damagedWallBottomMiddle, damagedWallBottomRight,
			damagedWallAccent1, damagedWallAccent2, damagedWallAccent3;
	public static BufferedImage wallBorderVerticalLeft, wallBorderVerticalRight, wallBorderCornerBottomRight,
			wallBorderCornerBottomLeft, wallBorderCornerTopRight, wallBorderCornerTopLeft, wallBorderRightBottom,
			wallBorderLeftBottom, wallBorderRightTop, wallBorderLeftTop, wallBorderTop, wallBorderRight, wallBorderLeft,
			wallBorderBottom;
	public static BufferedImage closedDoorOne, closedDoorTwo, doorStairs, doorwayArch, bathroomDoorMale, bathroomDoorFemale,
			locker, windowOne, windowTwo, chimney, bellTower, belltowerTransparent;
	public static BufferedImage blueSingleBed, redSingleBed, redDoubleBed, redMasterBed, chairOneDown, chairOneUp, chairOneLeft, chairOneRight;
	public static BufferedImage windowLight, windowDark, tableLampOff, tableLampOn;
	public static BufferedImage yellowLight;
	public static BufferedImage headDown, headUp, headLeft, headRight;
	public static BufferedImage darkWall, concrete, frontDoor, windowOutsideDay, windowOutsideNight,
			artFrame, artFrameSmall, artFrameSmallEmpty;
	public static BufferedImage buttonPuzzlePanel, buttonPuzzleBorder, buttonPuzzleOn, buttonPuzzleOff;
	public static BufferedImage galleryAbyss, galleryApparition, galleryBlight, galleryButcher, galleryCaptivity,
			galleryCipher, galleryCondemnation, galleryDeluge, galleryDissonance, galleryDuty, galleryFacade,
			galleryHarbinger, galleryHarrower, galleryHolocaust, galleryManifestation, galleryMoribund, galleryOblation,
			galleryPerdition, galleryProcession, galleryProphet, galleryRevelry, gallerySenescence, gallerySolace,
			gallerySuffering, gallerySynchronicity, galleryTorment, galleryVilomah;
	public static BufferedImage textboxDefault, textboxPlayerThinking, textboxPlayer, textboxDenial, textboxAnger,
			textboxBargaining, textboxDepression, textboxAcceptance, textboxTeacher, textboxOptions, portrait;
	public static BufferedImage inventory, inventoryHighlight, keyInventory, abyssInventory, apparitionInventory,
			blightInventory, butcherInventory, captivityInventory, cipherInventory, condemnationInventory, delugeInventory,
			dissonanceInventory, dutyInventory, facadeInventory, harbingerInventory, harrowerInventory, holocaustInventory,
			manifestationInventory, moribundInventory, oblationInventory, perditionInventory, processionInventory, prophetInventory,
			revelryInventory, senescenceInventory, solaceInventory, sufferingInventory, synchronicityInventory, tormentInventory,
			vilomahInventory;
	public static BufferedImage hole, firePlace, fireUnlit, fireExtinguished, toilet, furnaceUnlit, shrub, shrubReflected;
	public static BufferedImage teacherDesk, emptyDesk, denialDesk, angerDesk, bargainingDesk, depressionDesk, acceptanceDesk, playerDesk;
	public static BufferedImage houseDefault, houseDefaultTransparent, mansion, mansionTransparent, school, schoolTransparent;
	public static BufferedImage frontGateClosed, frontGateOpen, gateTop, gateBot, gateSide;
	public static BufferedImage mansionStairsUpRight, mansionStairsDownLeft;
	public static BufferedImage flowerVaseEmpty, flowerVaseSingle, flowerVaseBouquet;
	public static BufferedImage plantVasePlant1, plantVaseFlowers, plantVasePlant2;
	public static BufferedImage barrel, sideTableHorizontal, sideTableVertical;
	public static BufferedImage[] grandfatherClock;
	public static BufferedImage[] dressers, fire, chalkboard, windowOutsideNightHand, well, furnaceLit;
	public static BufferedImage[] activeInventoryHeader;
	public static BufferedImage[] playerDown, playerUp, playerLeft, playerRight;
	public static BufferedImage[] denialDown, denialUp, denialLeft, denialRight;
	public static BufferedImage[] angerDown, angerUp, angerLeft, angerRight;
	public static BufferedImage[] bargainingDown, bargainingUp, bargainingLeft, bargainingRight;
	public static BufferedImage[] depressionDown, depressionUp, depressionLeft, depressionRight;
	public static BufferedImage[] acceptanceDown, acceptanceUp, acceptanceLeft, acceptanceRight;
	public static BufferedImage[] teacherDown, teacherUp, teacherLeft, teacherRight;
	public static BufferedImage[] ghoulDown, ghoulLeft, ghoulRight, ghoulUp;
	public static BufferedImage[] tunnelVision;
	public static Font sans, serif, philosopher, textboxFontDefault;
	public static Font playerSpeakingFont, playerThinkingFont, acceptanceFont, denialFont, angerFont, bargainingFont, depressionFont, teacherFont;
	public static AudioClip menuMove, woodBreak, openInventory, closeInventory, textTest, textTest2,
			denialText, angerText, bargainingText, depressionText, acceptanceText, playerText, teacherText,
			lampOn, lampOff, windowHit;
	public static Clip menuMusic;
	public static Clip schoolBell, triKnock1, powerDown, windowKnock;
	public static void init() {
		initSounds();
		initFonts();
		initSpriteSheets();
		initStandaloneImages();
	}

	private static void initSounds() {
		menuMove = Applet.newAudioClip(Assets.class.getResource("/sounds/misc/menuMove.au"));
		woodBreak = Applet.newAudioClip(Assets.class.getResource("/sounds/misc/woodBreak.au"));
		openInventory = Applet.newAudioClip(Assets.class.getResource("/sounds/inventory/openInventory.au"));
		closeInventory = Applet.newAudioClip(Assets.class.getResource("/sounds/inventory/closeInventory.au"));
		textTest = Applet.newAudioClip(Assets.class.getResource("/sounds/text/text-test.au"));
		textTest2  = Applet.newAudioClip(Assets.class.getResource("/sounds/text/text-test-2.au"));
		lampOn = Applet.newAudioClip(Assets.class.getResource("/sounds/misc/switch36.au"));
		lampOff = Applet.newAudioClip(Assets.class.getResource("/sounds/misc/switch37.au"));
		windowHit = Applet.newAudioClip(Assets.class.getResource("/sounds/misc/window-hit.au"));
		try {
			menuMusic = AudioSystem.getClip();
			AudioInputStream ais1 = AudioSystem.getAudioInputStream(new File("res/music/desolate.au"));
			menuMusic.open(ais1);
			schoolBell = AudioSystem.getClip();
			AudioInputStream ais2 = AudioSystem.getAudioInputStream(new File("res/sounds/misc/school-bell.au"));
			schoolBell.open(ais2);
			triKnock1 = AudioSystem.getClip();
			AudioInputStream ais3 = AudioSystem.getAudioInputStream(new File("res/sounds/misc/triKnock04.au"));
			triKnock1.open(ais3);
			powerDown = AudioSystem.getClip();
			AudioInputStream ais4 = AudioSystem.getAudioInputStream(new File("res/sounds/misc/power-down.au"));
			powerDown.open(ais4);
			windowKnock = AudioSystem.getClip();
			AudioInputStream ais5 = AudioSystem.getAudioInputStream(new File("res/sounds/misc/window-knock.au"));
			windowKnock.open(ais5);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
	}

	private static void initFonts() {
		try {
			//create the font to use. Specify the size!
			sans = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/sans.ttf")).deriveFont(36f);
			serif = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/serif.ttf")).deriveFont(36f);
			philosopher = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/philosopher.ttf")).deriveFont(36f);
			textboxFontDefault = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/uwch.ttf")).deriveFont(128f);
			playerThinkingFont = serif;
			playerSpeakingFont = serif;
			denialFont = serif;
			angerFont = serif;
			bargainingFont = serif;
			depressionFont = serif;
			acceptanceFont = sans;
			teacherFont = textboxFontDefault;
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			//register the font
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/sans.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/serif.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/philosopher.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/uwch.ttf")));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}

	private static void initSpriteSheets() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/tile-sheets/sheet.png"));
		dirt = sheet.crop(0,0,width,height);
		grass = sheet.crop(width, 0, width, height);
		stone = sheet.crop(width*2, 0, width, height);
		water = sheet.crop(width * 3, 0, width, height);
		darkStoneWall = sheet.crop(0, height, width, height);
		woodFloor = sheet.crop(width, height, width, height);
		black = sheet.crop(width * 2, height, width, height);
		gray = sheet.crop(width * 3, height, width, height);

		SpriteSheet houseWallSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tile-sheets/interior/house-wall-sheet.png"));
		houseWallTopLeftBlue = houseWallSheet.crop(0, 0, width, height);
		houseWallTopMiddleBlue = houseWallSheet.crop(width, 0, width, height);
		houseWallTopRightBlue = houseWallSheet.crop(width * 2, 0, width, height);
		houseWallMiddleLeftBlue = houseWallSheet.crop(0, height, width, height);
		houseWallMiddleMiddleBlue = houseWallSheet.crop(width, height, width, height);
		houseWallMiddleRightBlue = houseWallSheet.crop(width * 2, height, width, height);
		houseWallBottomLeftBlue = houseWallSheet.crop(0, height * 2, width, height);
		houseWallBottomMiddleBlue = houseWallSheet.crop(width, height * 2, width, height);
		houseWallBottomRightBlue = houseWallSheet.crop(width * 2, height * 2, width, height);

		houseWallTopLeftGreen = houseWallSheet.crop(0, height * 3, width, height);
		houseWallTopMiddleGreen = houseWallSheet.crop(width, height * 3, width, height);
		houseWallTopRightGreen = houseWallSheet.crop(width * 2, height * 3, width, height);
		houseWallMiddleLeftGreen = houseWallSheet.crop(0, height * 4, width, height);
		houseWallMiddleMiddleGreen = houseWallSheet.crop(width, height * 4, width, height);
		houseWallMiddleRightGreen = houseWallSheet.crop(width * 2, height * 4, width, height);
		houseWallBottomLeftGreen = houseWallSheet.crop(0, height * 5, width, height);
		houseWallBottomMiddleGreen = houseWallSheet.crop(width, height * 5, width, height);
		houseWallBottomRightGreen = houseWallSheet.crop(width * 2, height * 5, width, height);

		houseWallTopLeftOrange = houseWallSheet.crop(width * 3, height * 3, width, height);
		houseWallTopMiddleOrange = houseWallSheet.crop(width * 4, height * 3, width, height);
		houseWallTopRightOrange = houseWallSheet.crop(width * 5, height * 3, width, height);
		houseWallMiddleLeftOrange = houseWallSheet.crop(width * 3, height * 4, width, height);
		houseWallMiddleMiddleOrange = houseWallSheet.crop(width * 4, height * 4, width, height);
		houseWallMiddleRightOrange = houseWallSheet.crop(width * 5, height * 4, width, height);
		houseWallBottomLeftOrange = houseWallSheet.crop(width * 3, height * 5, width, height);
		houseWallBottomMiddleOrange = houseWallSheet.crop(width * 4, height * 5, width, height);
		houseWallBottomRightOrange = houseWallSheet.crop(width * 5, height * 5, width, height);

		houseWallTopLeftRed = houseWallSheet.crop(width * 3, 0, width, height);
		houseWallTopMiddleRed = houseWallSheet.crop(width * 4, 0, width, height);
		houseWallTopRightRed = houseWallSheet.crop(width * 5, 0, width, height);
		houseWallMiddleLeftRed = houseWallSheet.crop(width * 3, height, width, height);
		houseWallMiddleMiddleRed = houseWallSheet.crop(width * 4, height, width, height);
		houseWallMiddleRightRed = houseWallSheet.crop(width * 5, height, width, height);
		houseWallBottomLeftRed = houseWallSheet.crop(width * 3, height * 2, width, height);
		houseWallBottomMiddleRed = houseWallSheet.crop(width * 4, height * 2, width, height);
		houseWallBottomRightRed = houseWallSheet.crop(width * 5, height * 2, width, height);

		patternedWallTopLeft = houseWallSheet.crop(width * 6, 0, width, height);
		patternedWallTopMiddle = houseWallSheet.crop(width * 7, 0, width, height);
		patternedWallTopRight = houseWallSheet.crop(width * 8, 0, width, height);
		patternedWallMiddleLeft = houseWallSheet.crop(width * 6, height, width, height);
		patternedWallMiddleMiddle = houseWallSheet.crop(width * 7, height, width, height);
		patternedWallMiddleRight = houseWallSheet.crop(width * 8, height, width, height);
		patternedWallBottomLeft = houseWallSheet.crop(width * 6, height * 2, width, height);
		patternedWallBottomMiddle = houseWallSheet.crop(width * 7, height * 2, width, height);
		patternedWallBottomRight = houseWallSheet.crop(width * 8, height * 2, width, height);

		patternedCarpetTopLeft = houseWallSheet.crop(width * 6, height * 3, width, height);
		patternedCarpetTopMiddle = houseWallSheet.crop(width * 7, height * 3, width, height);
		patternedCarpetTopRight = houseWallSheet.crop(width * 8, height * 3, width, height);
		patternedCarpetMiddleLeft = houseWallSheet.crop(width * 6, height * 4, width, height);
		patternedCarpetMiddleMiddle = houseWallSheet.crop(width * 7, height * 4, width, height);
		patternedCarpetMiddleRight = houseWallSheet.crop(width * 8, height * 4, width, height);
		patternedCarpetBottomLeft = houseWallSheet.crop(width * 6, height * 5, width, height);
		patternedCarpetBottomMiddle = houseWallSheet.crop(width * 7, height * 5, width, height);
		patternedCarpetBottomRight = houseWallSheet.crop(width * 8, height * 5, width, height);
		patternedCarpetCornerBottomLeft = houseWallSheet.crop(width * 6, height * 6, width, height);
		patternedCarpetCornerBottomRight = houseWallSheet.crop(width * 7, height * 6, width, height);

		bathroomWallTopLeft = houseWallSheet.crop(width * 9, 0, width, height);
		bathroomWallTopMiddle = houseWallSheet.crop(width * 10, 0, width, height);
		bathroomWallTopRight = houseWallSheet.crop(width * 11, 0, width, height);
		bathroomWallMiddleLeft = houseWallSheet.crop(width * 9, height, width, height);
		bathroomWallMiddleMiddle = houseWallSheet.crop(width * 10, height, width, height);
		bathroomWallMiddleRight = houseWallSheet.crop(width * 11, height, width, height);
		bathroomWallBottomLeft = houseWallSheet.crop(width * 9, height * 2, width, height);
		bathroomWallBottomMiddle = houseWallSheet.crop(width * 10, height * 2, width, height);
		bathroomWallBottomRight = houseWallSheet.crop(width * 11, height * 2, width, height);

		damagedWallTopLeft = houseWallSheet.crop(width * 9, height * 3, width, height);
		damagedWallTopMiddle = houseWallSheet.crop(width * 10, height * 3, width, height);
		damagedWallTopRight = houseWallSheet.crop(width * 11, height * 3, width, height);
		damagedWallMiddleLeft = houseWallSheet.crop(width * 9, height * 4, width, height);
		damagedWallMiddleMiddle = houseWallSheet.crop(width * 10, height * 4, width, height);
		damagedWallMiddleRight = houseWallSheet.crop(width * 11, height * 4, width, height);
		damagedWallBottomLeft = houseWallSheet.crop(width * 9, height * 5, width, height);
		damagedWallBottomMiddle = houseWallSheet.crop(width * 10, height * 5, width, height);
		damagedWallBottomRight = houseWallSheet.crop(width * 11, height * 5, width, height);
		damagedWallAccent1 = houseWallSheet.crop(width * 9, height * 6, width, height);
		damagedWallAccent2 = houseWallSheet.crop(width * 10, height * 6, width, height);
		damagedWallAccent3 = houseWallSheet.crop(width * 11, height * 6, width, height);

		SpriteSheet wallBordersSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tile-sheets/interior/wall-borders-sheet.png"));
		wallBorderVerticalLeft = wallBordersSheet.crop(0, 0, width, height);
		wallBorderVerticalRight = wallBordersSheet.crop(width * 4, 0, width, height);
		wallBorderCornerBottomRight = wallBordersSheet.crop(width, 0, width, height);
		wallBorderCornerBottomLeft = wallBordersSheet.crop(width * 3, 0, width, height);
		wallBorderCornerTopRight = wallBordersSheet.crop(0, height * 3, width, height);
		wallBorderCornerTopLeft = wallBordersSheet.crop(width * 4, height * 3, width, height);
		wallBorderRightBottom = wallBordersSheet.crop(width, height, width, height);
		wallBorderLeftBottom = wallBordersSheet.crop(width * 3, height, width, height);
		wallBorderRightTop = wallBordersSheet.crop(width, height * 3, width, height);
		wallBorderLeftTop = wallBordersSheet.crop(width * 3, height * 3, width, height);
		wallBorderBottom = wallBordersSheet.crop(width * 2, 0, width, height);
		wallBorderRight = wallBordersSheet.crop(0, height * 2, width, height);
		wallBorderLeft = wallBordersSheet.crop(width * 4, height * 2, width, height);
		wallBorderTop = wallBordersSheet.crop(width * 2, height * 4, width, height);

		SpriteSheet tilesSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tile-sheets/interior/tiles-sheet.png"));
		diamondTileBlue = tilesSheet.crop(0, 0, width, height);
		diamondTileGreen = tilesSheet.crop(width, 0, width, height);
		diamondTileWood = tilesSheet.crop(width * 2, 0, width, height);
		diamondTileDouble = tilesSheet.crop(width * 3, 0, width, height);
		bathroomTile = tilesSheet.crop(0, height, width, height);
		whiteBrickMiddle = tilesSheet.crop(width * 2, height * 2, width, height);
		whiteBrickBottom = tilesSheet.crop(width * 3, height * 2, width, height);
		tiledFloorWhite = tilesSheet.crop(0, height * 2, width, height);
		tiledFloorBlack = tilesSheet.crop(width, height * 2, width, height);

		SpriteSheet mansionExteriorSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tile-sheets/Overworld/mansion-exterior-tilesheet.png"));
		cobblestoneLeft = mansionExteriorSheet.crop(0, 0, width, height);
		cobblestoneRight = mansionExteriorSheet.crop(width, 0, width, height);
		accentedGrass1 = mansionExteriorSheet.crop(0, height, width, height);
		accentedGrass2 = mansionExteriorSheet.crop(width, height, width, height);
		accentedGrass3 = mansionExteriorSheet.crop(width * 2, height, width, height);
		accentedGrassDark1 = mansionExteriorSheet.crop(0, height * 2, width, height);
		accentedGrassDark2 = mansionExteriorSheet.crop(width, height * 2, width, height);
		accentedGrassDark3 = mansionExteriorSheet.crop(width * 2, height * 2, width, height);
		flowerGrass1 = mansionExteriorSheet.crop(0, height * 3, width, height);
		flowerGrass2 = mansionExteriorSheet.crop(width, height * 3, width, height);
		flowerGrass3 = mansionExteriorSheet.crop(width * 2, height * 3, width, height);

		SpriteSheet dressersSheet = new SpriteSheet(ImageLoader.loadImage("/textures/static-entities/dressers-sheet.png"));
		dressers = new BufferedImage[9];
		dressers[0] = dressersSheet.crop(0, 0, width, 69);
		dressers[1] = dressersSheet.crop(width, 0, width, 69);
		dressers[2] = dressersSheet.crop(width * 2, 0, width, 69);
		dressers[3] = dressersSheet.crop(width * 3, 0, width, 69);
		dressers[4] = dressersSheet.crop(0, 69, width, 69);
		dressers[5] = dressersSheet.crop(width, 69, width, 69);
		dressers[6] = dressersSheet.crop(width * 2, 69, width, 69);
		dressers[7] = dressersSheet.crop(width * 3, 69, width, 69);
		dressers[8] = dressersSheet.crop(width * 4, 69, width, 69);

		SpriteSheet wellSheet = new SpriteSheet(ImageLoader.loadImage("/textures/static-entities/well-sheet.png"));
		well = new BufferedImage[3];
		int wellWidth = width * 3, wellHeight = 124;
		well[0] = wellSheet.crop(0, 0, wellWidth, wellHeight);
		well[1] = wellSheet.crop(0, wellHeight, wellWidth, wellHeight);
		well[2] = wellSheet.crop(0, wellHeight * 2, wellWidth, wellHeight);

		SpriteSheet furnaceSheet = new SpriteSheet(ImageLoader.loadImage("/textures/static-entities/furnace-sheet.png"));
		furnaceUnlit = furnaceSheet.crop(0, 0, width, 123);
		int furnaceFaceHeight = 25;
		furnaceLit = new BufferedImage[3];
		furnaceLit[0] = furnaceSheet.crop(width, 0, width, furnaceFaceHeight);
		furnaceLit[1] = furnaceSheet.crop(width, furnaceFaceHeight, width, furnaceFaceHeight);
		furnaceLit[2] = furnaceSheet.crop(width, furnaceFaceHeight * 2, width, furnaceFaceHeight);

		SpriteSheet fireSheet = new SpriteSheet(ImageLoader.loadImage("/textures/static-entities/fire.png"));
		fire = new BufferedImage[4];
		fire[0] = fireSheet.crop(0, 0, width, height);
		fire[1] = fireSheet.crop(width, 0, width, height);
		fire[2] = fireSheet.crop(width * 2, 0, width, height);
		fire[3] = fireSheet.crop(width * 3, 0, width, height);
		fireUnlit = fireSheet.crop(0, height, width, height);
		fireExtinguished = fireSheet.crop(width, height, width, height);

		SpriteSheet grandfatherClockSheet = new SpriteSheet(ImageLoader.loadImage("/textures/static-entities/grandfather-clock.png"));
		grandfatherClock = new BufferedImage[4];
		grandfatherClock[0] = grandfatherClockSheet.crop(0, 0, width, height * 3);
		grandfatherClock[1] = grandfatherClockSheet.crop(width, 0, width, height * 3);
		grandfatherClock[2] = grandfatherClockSheet.crop(width * 2, 0, width, height * 3);
		grandfatherClock[3] = grandfatherClockSheet.crop(width * 3, 0, width, height * 3);

		SpriteSheet schoolSheet = new SpriteSheet(ImageLoader.loadImage("/textures/static-entities/school-sheet.png"));
		bathroomDoorMale = schoolSheet.crop(0, 0, width, height + height / 2);
		bathroomDoorFemale = schoolSheet.crop(width, 0, width, height + height / 2);
		locker = schoolSheet.crop(width * 2, 0, width, height + height / 2);

		SpriteSheet chalkboardSheet = new SpriteSheet(ImageLoader.loadImage("/textures/static-entities/chalkboard.png"));
		chalkboard = new BufferedImage[2];
		chalkboard[0] = chalkboardSheet.crop(0, 0, 300, height * 4);
		chalkboard[1] = chalkboardSheet.crop(0, height * 4, 300, height * 4);

		SpriteSheet staticEntities = new SpriteSheet(ImageLoader.loadImage("/textures/static-entities/sheet.png"));
		windowOne = staticEntities.crop(0, 0, width, 44);
		windowTwo = staticEntities.crop(0, 44, width, height);
		doorStairs = staticEntities.crop(0, 44 + height, width, height - height / 4);
		chimney = staticEntities.crop(0, 44 + height + (height - height / 4), width, 30);
		closedDoorOne = staticEntities.crop(0, 44 + height + (height - height / 4) + 30, width, height + height / 2);
		closedDoorTwo = staticEntities.crop(0, 44 + height + (height - height / 4) + 30 + height + (height / 2), width, 46);

		SpriteSheet insideAccentsSheet = new SpriteSheet(ImageLoader.loadImage("/textures/static-entities/inside-accents-sheet-sharm.png"));
		blueSingleBed = insideAccentsSheet.crop(200, 303, 48, 81);
		chairOneDown = insideAccentsSheet.crop(227, 400, 26, height);
		chairOneUp = insideAccentsSheet.crop(163, 488, 26, 24);
		chairOneRight = insideAccentsSheet.crop(101, 480, 22, height);
		chairOneLeft = insideAccentsSheet.crop(133, 480, 22, height);
		redSingleBed = insideAccentsSheet.crop(72, 302, 48, 82);
		redDoubleBed = insideAccentsSheet.crop(0, 302, width * 2, 82);

		flowerVaseEmpty = insideAccentsSheet.crop(147, 129, 12, 23);
		flowerVaseSingle = insideAccentsSheet.crop(163, 129, 8, 23);
		flowerVaseBouquet = insideAccentsSheet.crop(173, 123, 19, 29);

		plantVasePlant1 = insideAccentsSheet.crop(32, 65, 30, 48);
		plantVaseFlowers = insideAccentsSheet.crop(66, 75, 28, 38);
		plantVasePlant2 = insideAccentsSheet.crop(98, 64, width, 48);

		barrel = insideAccentsSheet.crop(386, 453, 28, 43);
		sideTableHorizontal = insideAccentsSheet.crop(140, 239, 48, 27);
		sideTableVertical = insideAccentsSheet.crop(108, 225, 16, 46);

		SpriteSheet buttonPuzzleSheet = new SpriteSheet(ImageLoader.loadImage("/textures/static-entities/buttonPuzzleSheet.png"));
		buttonPuzzleOff = buttonPuzzleSheet.crop(0, 0, 216, 216);
		buttonPuzzleOn = buttonPuzzleSheet.crop(216, 0, 216, 216);

		SpriteSheet denialSheet = new SpriteSheet(ImageLoader.loadImage("/textures/characters/denial-sheet.png"));
		denialDown = new BufferedImage[4];
		denialUp = new BufferedImage[4];
		denialLeft = new BufferedImage[4];
		denialRight = new BufferedImage[4];

		denialDown[0] = denialSheet.crop(0, 0, width, height);
		denialDown[1] = denialSheet.crop(width, 0, width, height);
		denialDown[2] = denialSheet.crop(width*2, 0, width, height);
		denialDown[3] = denialSheet.crop(width*3, 0, width, height);

		denialUp[0] = denialSheet.crop(0, height, width, height);
		denialUp[1] = denialSheet.crop(width, height, width, height);
		denialUp[2] = denialSheet.crop(width*2, height, width, height);
		denialUp[3] = denialSheet.crop(width*3, height, width, height);

		denialLeft[0] = denialSheet.crop(0, height*2, width, height);
		denialLeft[1] = denialSheet.crop(width, height*2, width, height);
		denialLeft[2] = denialSheet.crop(width*2, height*2, width, height);
		denialLeft[3] = denialSheet.crop(width*3, height*2, width, height);

		denialRight[0] = denialSheet.crop(0, height*3, width, height);
		denialRight[1] = denialSheet.crop(width, height*3, width, height);
		denialRight[2] = denialSheet.crop(width*2, height*3, width, height);
		denialRight[3] = denialSheet.crop(width*3, height*3, width, height);

		SpriteSheet angerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/characters/anger-sheet.png"));
		angerDown = new BufferedImage[4];
		angerUp = new BufferedImage[4];
		angerLeft = new BufferedImage[4];
		angerRight = new BufferedImage[4];

		angerDown[0] = angerSheet.crop(0, 0, width, height);
		angerDown[1] = angerSheet.crop(width, 0, width, height);
		angerDown[2] = angerSheet.crop(width*2, 0, width, height);
		angerDown[3] = angerSheet.crop(width*3, 0, width, height);

		angerUp[0] = angerSheet.crop(0, height, width, height);
		angerUp[1] = angerSheet.crop(width, height, width, height);
		angerUp[2] = angerSheet.crop(width*2, height, width, height);
		angerUp[3] = angerSheet.crop(width*3, height, width, height);

		angerLeft[0] = angerSheet.crop(0, height*2, width, height);
		angerLeft[1] = angerSheet.crop(width, height*2, width, height);
		angerLeft[2] = angerSheet.crop(width*2, height*2, width, height);
		angerLeft[3] = angerSheet.crop(width*3, height*2, width, height);

		angerRight[0] = angerSheet.crop(0, height*3, width, height);
		angerRight[1] = angerSheet.crop(width, height*3, width, height);
		angerRight[2] = angerSheet.crop(width*2, height*3, width, height);
		angerRight[3] = angerSheet.crop(width*3, height*3, width, height);

		SpriteSheet bargainingSheet = new SpriteSheet(ImageLoader.loadImage("/textures/characters/bargaining-sheet.png"));
		bargainingDown = new BufferedImage[4];
		bargainingUp = new BufferedImage[4];
		bargainingLeft = new BufferedImage[4];
		bargainingRight = new BufferedImage[4];

		bargainingDown[0] = bargainingSheet.crop(0, 0, width, height);
		bargainingDown[1] = bargainingSheet.crop(width, 0, width, height);
		bargainingDown[2] = bargainingSheet.crop(width*2, 0, width, height);
		bargainingDown[3] = bargainingSheet.crop(width*3, 0, width, height);

		bargainingUp[0] = bargainingSheet.crop(0, height, width, height);
		bargainingUp[1] = bargainingSheet.crop(width, height, width, height);
		bargainingUp[2] = bargainingSheet.crop(width*2, height, width, height);
		bargainingUp[3] = bargainingSheet.crop(width*3, height, width, height);

		bargainingLeft[0] = bargainingSheet.crop(0, height*2, width, height);
		bargainingLeft[1] = bargainingSheet.crop(width, height*2, width, height);
		bargainingLeft[2] = bargainingSheet.crop(width*2, height*2, width, height);
		bargainingLeft[3] = bargainingSheet.crop(width*3, height*2, width, height);

		bargainingRight[0] = bargainingSheet.crop(0, height*3, width, height);
		bargainingRight[1] = bargainingSheet.crop(width, height*3, width, height);
		bargainingRight[2] = bargainingSheet.crop(width*2, height*3, width, height);
		bargainingRight[3] = bargainingSheet.crop(width*3, height*3, width, height);

		SpriteSheet depressionSheet = new SpriteSheet(ImageLoader.loadImage("/textures/characters/depression-sheet.png"));
		depressionDown = new BufferedImage[4];
		depressionUp = new BufferedImage[4];
		depressionLeft = new BufferedImage[4];
		depressionRight = new BufferedImage[4];

		depressionDown[0] = depressionSheet.crop(0, 0, width, height);
		depressionDown[1] = depressionSheet.crop(width, 0, width, height);
		depressionDown[2] = depressionSheet.crop(width*2, 0, width, height);
		depressionDown[3] = depressionSheet.crop(width*3, 0, width, height);

		depressionUp[0] = depressionSheet.crop(0, height, width, height);
		depressionUp[1] = depressionSheet.crop(width, height, width, height);
		depressionUp[2] = depressionSheet.crop(width*2, height, width, height);
		depressionUp[3] = depressionSheet.crop(width*3, height, width, height);

		depressionLeft[0] = depressionSheet.crop(0, height*2, width, height);
		depressionLeft[1] = depressionSheet.crop(width, height*2, width, height);
		depressionLeft[2] = depressionSheet.crop(width*2, height*2, width, height);
		depressionLeft[3] = depressionSheet.crop(width*3, height*2, width, height);

		depressionRight[0] = depressionSheet.crop(0, height*3, width, height);
		depressionRight[1] = depressionSheet.crop(width, height*3, width, height);
		depressionRight[2] = depressionSheet.crop(width*2, height*3, width, height);
		depressionRight[3] = depressionSheet.crop(width*3, height*3, width, height);

		SpriteSheet acceptanceSheet = new SpriteSheet(ImageLoader.loadImage("/textures/characters/acceptance-sheet.png"));
		acceptanceDown = new BufferedImage[4];
		acceptanceUp = new BufferedImage[4];
		acceptanceLeft = new BufferedImage[4];
		acceptanceRight = new BufferedImage[4];

		acceptanceDown[0] = acceptanceSheet.crop(0, 0, width, height);
		acceptanceDown[1] = acceptanceSheet.crop(width, 0, width, height);
		acceptanceDown[2] = acceptanceSheet.crop(width*2, 0, width, height);
		acceptanceDown[3] = acceptanceSheet.crop(width*3, 0, width, height);

		acceptanceUp[0] = acceptanceSheet.crop(0, height, width, height);
		acceptanceUp[1] = acceptanceSheet.crop(width, height, width, height);
		acceptanceUp[2] = acceptanceSheet.crop(width*2, height, width, height);
		acceptanceUp[3] = acceptanceSheet.crop(width*3, height, width, height);

		acceptanceLeft[0] = acceptanceSheet.crop(0, height*2, width, height);
		acceptanceLeft[1] = acceptanceSheet.crop(width, height*2, width, height);
		acceptanceLeft[2] = acceptanceSheet.crop(width*2, height*2, width, height);
		acceptanceLeft[3] = acceptanceSheet.crop(width*3, height*2, width, height);

		acceptanceRight[0] = acceptanceSheet.crop(0, height*3, width, height);
		acceptanceRight[1] = acceptanceSheet.crop(width, height*3, width, height);
		acceptanceRight[2] = acceptanceSheet.crop(width*2, height*3, width, height);
		acceptanceRight[3] = acceptanceSheet.crop(width*3, height*3, width, height);

		SpriteSheet mcSheet = new SpriteSheet(ImageLoader.loadImage("/textures/characters/mc-sheet.png"));
		playerDown = new BufferedImage[4]; 		//4 = frame count
		playerUp = new BufferedImage[4];
		playerLeft = new BufferedImage[4];
		playerRight = new BufferedImage[4];

		playerDown[0] = mcSheet.crop(0, 0, width, height);
		playerDown[1] = mcSheet.crop(width, 0, width, height);
		playerDown[2] = mcSheet.crop(width*2, 0, width, height);
		playerDown[3] = mcSheet.crop(width*3, 0, width, height);

		playerUp[0] = mcSheet.crop(0, height, width, height);
		playerUp[1] = mcSheet.crop(width, height, width, height);
		playerUp[2] = mcSheet.crop(width*2, height, width, height);
		playerUp[3] = mcSheet.crop(width*3, height, width, height);

		playerLeft[0] = mcSheet.crop(0, height*2, width, height);
		playerLeft[1] = mcSheet.crop(width, height*2, width, height);
		playerLeft[2] = mcSheet.crop(width*2, height*2, width, height);
		playerLeft[3] = mcSheet.crop(width*3, height*2, width, height);

		playerRight[0] = mcSheet.crop(0, height*3, width, height);
		playerRight[1] = mcSheet.crop(width, height*3, width, height);
		playerRight[2] = mcSheet.crop(width*2, height*3, width, height);
		playerRight[3] = mcSheet.crop(width*3, height*3, width, height);

		playerDownNormal = playerDown[0];
		playerLeftNormal = playerLeft[0];
		playerRightNormal = playerRight[0];
		playerUpNormal = playerUp[0];

		SpriteSheet headSheet = new SpriteSheet(ImageLoader.loadImage("/textures/characters/player-head-sheet.png"));
		headDown = headSheet.crop(0, 0, width, height);
		headUp = headSheet.crop(0, height, width, height);
		headLeft = headSheet.crop(0, height * 2, width, height);
		headRight = headSheet.crop(0, height * 3, width, height);

		int deskHeight = 44;
		SpriteSheet studentDeskSheet = new SpriteSheet(ImageLoader.loadImage("/textures/static-entities/student-desk.png"));
		emptyDesk = studentDeskSheet.crop(0, 0, width, deskHeight);
		denialDesk = studentDeskSheet.crop(width, 0, width, deskHeight);
		angerDesk = studentDeskSheet.crop(width * 2, 0, width, deskHeight);
		bargainingDesk = studentDeskSheet.crop(width * 3, 0, width, deskHeight);
		depressionDesk = studentDeskSheet.crop(0, deskHeight, width, deskHeight);
		acceptanceDesk = studentDeskSheet.crop(width, deskHeight, width, deskHeight);
		playerDesk = studentDeskSheet.crop(width * 2, deskHeight, width, deskHeight);

		SpriteSheet tableLampSheet = new SpriteSheet(ImageLoader.loadImage("/textures/static-entities/table-lamp.png"));
		tableLampOff = tableLampSheet.crop(0, 0, width, 50);
		tableLampOn = tableLampSheet.crop(width, 0, width, 50);

		SpriteSheet windowOutsideSheet = new SpriteSheet(ImageLoader.loadImage("/textures/static-entities/window-outside.png"));
		windowLight = windowOutsideSheet.crop(0, 0, width, height);
		windowDark = windowOutsideSheet.crop(0, height, width, height);

		SpriteSheet frontGateSheet = new SpriteSheet(ImageLoader.loadImage("/textures/static-entities/gate-front.png"));
		frontGateOpen = frontGateSheet.crop(0, 0, 56, 83);
		frontGateClosed = frontGateSheet.crop(0, 83, 56, 83);

		SpriteSheet mansionStairsSheet = new SpriteSheet(ImageLoader.loadImage("/textures/static-entities/mansion-stairs-sheet.png"));
		mansionStairsUpRight = mansionStairsSheet.crop(0, 0, width * 2, 79);
		mansionStairsDownLeft = mansionStairsSheet.crop(0, 79, width * 2, 48);

		SpriteSheet mcSheetTransparent = new SpriteSheet(ImageLoader.loadImage("/textures/characters/mc-sheet-transparent.png"));
		playerDownTransparent = mcSheetTransparent.crop(0, 0, width, height);
		playerUpTransparent = mcSheetTransparent.crop(0, height, width, height);
		playerLeftTransparent = mcSheetTransparent.crop(0, height * 2, width, height);
		playerRightTransparent = mcSheetTransparent.crop(0, height * 3, width, height);

		SpriteSheet teacherSheet = new SpriteSheet(ImageLoader.loadImage("/textures/characters/teacher-sheet.png"));
		teacherDown = new BufferedImage[4]; 		//4 = frame count
		teacherUp = new BufferedImage[4];
		teacherLeft = new BufferedImage[6];
		teacherRight = new BufferedImage[6];

		teacherDown[0] = teacherSheet.crop(0, 0, width, height);
		teacherDown[1] = teacherSheet.crop(width, 0, width, height);
		teacherDown[2] = teacherSheet.crop(width * 2, 0, width, height);
		teacherDown[3] = teacherSheet.crop(width * 3, 0, width, height);

		teacherUp[0] = teacherSheet.crop(0, height, width, height);
		teacherUp[1] = teacherSheet.crop(width, height, width, height);
		teacherUp[2] = teacherSheet.crop(width * 2, height, width, height);
		teacherUp[3] = teacherSheet.crop(width * 3, height, width, height);

		teacherLeft[0] = teacherSheet.crop(0, height * 2, width, height);
		teacherLeft[1] = teacherSheet.crop(width, height * 2, width, height);
		teacherLeft[2] = teacherSheet.crop(width * 2, height * 2, width, height);
		teacherLeft[3] = teacherSheet.crop(width * 3, height * 2, width, height);
		teacherLeft[4] = teacherSheet.crop(width * 4, height * 2, width, height);
		teacherLeft[5] = teacherSheet.crop(width * 5, height * 2, width, height);

		teacherRight[0] = teacherSheet.crop(0, height * 3, width, height);
		teacherRight[1] = teacherSheet.crop(width, height * 3, width, height);
		teacherRight[2] = teacherSheet.crop(width * 2, height * 3, width, height);
		teacherRight[3] = teacherSheet.crop(width * 3, height * 3, width, height);
		teacherRight[4] = teacherSheet.crop(width * 4, height * 3, width, height);
		teacherRight[5] = teacherSheet.crop(width * 5, height * 3, width, height);

		SpriteSheet ghoulSheet = new SpriteSheet(ImageLoader.loadImage("/textures/ghoul.png"));
		ghoulDown = new BufferedImage[3];
		ghoulDown[0] = ghoulSheet.crop(0, 0, biggerWidth, biggerHeight);
		ghoulDown[1] = ghoulSheet.crop(biggerWidth, 0, biggerWidth, biggerHeight);
		ghoulDown[2] = ghoulSheet.crop(biggerWidth * 2, 0, biggerWidth, biggerHeight);

		ghoulLeft = new BufferedImage[3];
		ghoulLeft[0] = ghoulSheet.crop(0, biggerHeight, biggerWidth, biggerHeight);
		ghoulLeft[1] = ghoulSheet.crop(biggerWidth, biggerHeight, biggerWidth, biggerHeight);
		ghoulLeft[2] = ghoulSheet.crop(biggerWidth * 2, biggerHeight, biggerWidth, biggerHeight);

		ghoulRight = new BufferedImage[3];
		ghoulRight[0] = ghoulSheet.crop(0, biggerHeight * 2, biggerWidth, biggerHeight);
		ghoulRight[1] = ghoulSheet.crop(biggerWidth, biggerHeight * 2, biggerWidth, biggerHeight);
		ghoulRight[2] = ghoulSheet.crop(biggerWidth * 2, biggerHeight * 2, biggerWidth, biggerHeight);

		ghoulUp = new BufferedImage[3];
		ghoulUp[0] = ghoulSheet.crop(0, biggerHeight * 3, biggerWidth, biggerHeight);
		ghoulUp[1] = ghoulSheet.crop(biggerWidth, biggerHeight * 3, biggerWidth, biggerHeight);
		ghoulUp[2] = ghoulSheet.crop(biggerWidth * 2, biggerHeight * 3, biggerWidth, biggerHeight);

		SpriteSheet inventoryActiveSheet = new SpriteSheet(ImageLoader.loadImage("/textures/inventory-active-header.png"));
		activeInventoryHeader = new BufferedImage[3];
		activeInventoryHeader[0] = inventoryActiveSheet.crop(0, 0, 268, 70);
		activeInventoryHeader[1] = inventoryActiveSheet.crop(268, 0, 268, 70);
		activeInventoryHeader[2] = inventoryActiveSheet.crop(535, 0, 268, 70);
	}

	private static void initStandaloneImages() {
		textboxDefault = ImageLoader.loadImage("/textures/tb.png");
		textboxPlayerThinking = ImageLoader.loadImage("/textures/tb.png");
		textboxPlayer = ImageLoader.loadImage("/textures/tb.png");
		textboxDenial = ImageLoader.loadImage("/textures/tb.png");
		textboxAnger = ImageLoader.loadImage("/textures/tb.png");
		textboxBargaining = ImageLoader.loadImage("/textures/tb.png");
		textboxDepression = ImageLoader.loadImage("/textures/tb.png");
		textboxAcceptance = ImageLoader.loadImage("/textures/tb.png");
		textboxTeacher = ImageLoader.loadImage("/textures/tb.png");
		textboxOptions = ImageLoader.loadImage("/textures/tb-options.png");
		portrait = ImageLoader.loadImage("/textures/portrait.png");
		inventory = ImageLoader.loadImage("/textures/inventory.png");
		inventoryHighlight = ImageLoader.loadImage("/textures/inventory-highlight.png");
		buttonPuzzleBorder = ImageLoader.loadImage("/textures/static-entities/buttonPuzzleBorder.png");
		buttonPuzzlePanel = ImageLoader.loadImage("/textures/static-entities/buttonPuzzlePanel.png");

		keyInventory = ImageLoader.loadImage("/textures/inventory/key.png");

		tunnelVision = new BufferedImage[46];
		for (int i = 0, j = 20; i < tunnelVision.length; i++, j += 2) {
			tunnelVision[i] = ImageLoader.loadImage("/textures/reduced-vision/vision-" + j + "-75.png");
		}

		windowOutsideNightHand = new BufferedImage[4];
		windowOutsideNightHand[0] = ImageLoader.loadImage("/textures/backgrounds/window-outside-night-hand1.png");
		windowOutsideNightHand[1] = ImageLoader.loadImage("/textures/backgrounds/window-outside-night-hand2.png");
		windowOutsideNightHand[2] = ImageLoader.loadImage("/textures/backgrounds/window-outside-night-hand3.png");
		windowOutsideNightHand[3] = ImageLoader.loadImage("/textures/backgrounds/window-outside-night.png");

		darkWall = ImageLoader.loadImage("/textures/backgrounds/wall.png");
		concrete = ImageLoader.loadImage("/textures/backgrounds/concrete.png");
		frontDoor = ImageLoader.loadImage("/textures/backgrounds/front-door.png");
		windowOutsideDay = ImageLoader.loadImage("/textures/backgrounds/window-outside-day.png");
		windowOutsideNight = ImageLoader.loadImage("/textures/backgrounds/window-outside-night.png");
		artFrame = ImageLoader.loadImage("/textures/artFrame.png");
		artFrameSmall = ImageLoader.loadImage("/textures/artFrameSmall.png");
		artFrameSmallEmpty = ImageLoader.loadImage("/textures/artFrameSmallEmpty.png");

		galleryAbyss = ImageLoader.loadImage("/textures/gallery/Abyss.png");
		galleryApparition = ImageLoader.loadImage("/textures/gallery/Apparition.png");
		galleryBlight = ImageLoader.loadImage("/textures/gallery/Blight.png");
		galleryButcher = ImageLoader.loadImage("/textures/gallery/Butcher.png");
		galleryCaptivity = ImageLoader.loadImage("/textures/gallery/Captivity.png");
		galleryCipher = ImageLoader.loadImage("/textures/gallery/Cipher.png");
		galleryCondemnation = ImageLoader.loadImage("/textures/gallery/Condemnation.png");
		galleryDeluge = ImageLoader.loadImage("/textures/gallery/Deluge.png");
		galleryDissonance = ImageLoader.loadImage("/textures/gallery/Dissonance.png");
		galleryDuty = ImageLoader.loadImage("/textures/gallery/Duty.png");
		galleryFacade = ImageLoader.loadImage("/textures/gallery/Facade.png");
		galleryHarbinger = ImageLoader.loadImage("/textures/gallery/Harbinger.png");
		galleryHarrower = ImageLoader.loadImage("/textures/gallery/Harrower.png");
		galleryHolocaust = ImageLoader.loadImage("/textures/gallery/Holocaust.png");
		galleryManifestation = ImageLoader.loadImage("/textures/gallery/Manifestation.png");
		galleryMoribund = ImageLoader.loadImage("/textures/gallery/Moribund.png");
		galleryOblation = ImageLoader.loadImage("/textures/gallery/Oblation.png");
		galleryPerdition = ImageLoader.loadImage("/textures/gallery/Perdition.png");
		galleryProcession = ImageLoader.loadImage("/textures/gallery/Procession.png");
		galleryProphet = ImageLoader.loadImage("/textures/gallery/Prophet.png");
		galleryRevelry = ImageLoader.loadImage("/textures/gallery/Revelry.png");
		gallerySenescence = ImageLoader.loadImage("/textures/gallery/Senescence.png");
		gallerySolace = ImageLoader.loadImage("/textures/gallery/Solace.png");
		gallerySuffering = ImageLoader.loadImage("/textures/gallery/Suffering.png");
		gallerySynchronicity = ImageLoader.loadImage("/textures/gallery/Synchronicity.png");
		galleryTorment = ImageLoader.loadImage("/textures/gallery/Torment.png");
		galleryVilomah = ImageLoader.loadImage("/textures/gallery/Vilomah.png");

		abyssInventory = ImageLoader.loadImage("/textures/inventory/Abyss.png");
		apparitionInventory = ImageLoader.loadImage("/textures/inventory/Apparition.png");
		blightInventory = ImageLoader.loadImage("/textures/inventory/Blight.png");
		butcherInventory = ImageLoader.loadImage("/textures/inventory/Butcher.png");
		captivityInventory = ImageLoader.loadImage("/textures/inventory/Captivity.png");
		cipherInventory = ImageLoader.loadImage("/textures/inventory/Cipher.png");
		condemnationInventory = ImageLoader.loadImage("/textures/inventory/Condemnation.png");
		delugeInventory = ImageLoader.loadImage("/textures/inventory/Deluge.png");
		dissonanceInventory = ImageLoader.loadImage("/textures/inventory/Dissonance.png");
		dutyInventory = ImageLoader.loadImage("/textures/inventory/Duty.png");
		facadeInventory = ImageLoader.loadImage("/textures/inventory/Facade.png");
		harbingerInventory = ImageLoader.loadImage("/textures/inventory/Harbinger.png");
		harrowerInventory = ImageLoader.loadImage("/textures/inventory/Harrower.png");
		holocaustInventory = ImageLoader.loadImage("/textures/inventory/Holocaust.png");
		manifestationInventory = ImageLoader.loadImage("/textures/inventory/Manifestation.png");
		moribundInventory = ImageLoader.loadImage("/textures/inventory/Moribund.png");
		oblationInventory = ImageLoader.loadImage("/textures/inventory/Oblation.png");
		perditionInventory = ImageLoader.loadImage("/textures/inventory/Perdition.png");
		processionInventory = ImageLoader.loadImage("/textures/inventory/Procession.png");
		prophetInventory = ImageLoader.loadImage("/textures/inventory/Prophet.png");
		revelryInventory = ImageLoader.loadImage("/textures/inventory/Revelry.png");
		senescenceInventory = ImageLoader.loadImage("/textures/inventory/Senescence.png");
		solaceInventory = ImageLoader.loadImage("/textures/inventory/Solace.png");
		sufferingInventory = ImageLoader.loadImage("/textures/inventory/Suffering.png");
		synchronicityInventory = ImageLoader.loadImage("/textures/inventory/Synchronicity.png");
		tormentInventory = ImageLoader.loadImage("/textures/inventory/Torment.png");
		vilomahInventory = ImageLoader.loadImage("/textures/inventory/Vilomah.png");

		shrub = ImageLoader.loadImage("/textures/static-entities/shrub.png");
		shrubReflected = ImageLoader.loadImage("/textures/static-entities/shrub-reflected.png");
		hole = ImageLoader.loadImage("/textures/hole.png");
		doorwayArch = ImageLoader.loadImage("/textures/oga/LPC-Base-Assets/tiles/doorwayArch.png");
		yellowLight = ImageLoader.loadImage("/textures/effects/light-yellow.png");
		firePlace = ImageLoader.loadImage("/textures/static-entities/fireplace-1.png");
		teacherDesk = ImageLoader.loadImage("/textures/static-entities/teacher-desk.png");
		toilet = ImageLoader.loadImage("/textures/static-entities/toilet.png");
		bellTower = ImageLoader.loadImage("/textures/static-entities/belltower.png");
		belltowerTransparent = ImageLoader.loadImage("/textures/static-entities/belltower-transparent.png");
		houseDefault = ImageLoader.loadImage("/textures/static-entities/house-default.png");
		houseDefaultTransparent = ImageLoader.loadImage("/textures/static-entities/house-default-transparent.png");
		mansion = ImageLoader.loadImage("/textures/static-entities/mansion-sample.png");
		mansionTransparent = ImageLoader.loadImage("/textures/static-entities/mansion-sample-transparent.png");
		school = ImageLoader.loadImage("/textures/static-entities/school.png");
		schoolTransparent = ImageLoader.loadImage("/textures/static-entities/school-transparent.png");
		gateTop = ImageLoader.loadImage("/textures/static-entities/gate-top.png");
		gateBot = ImageLoader.loadImage("/textures/static-entities/gate-bot.png");
		gateSide = ImageLoader.loadImage("/textures/static-entities/gate-side.png");
	}
}
