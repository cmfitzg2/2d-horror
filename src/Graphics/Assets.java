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

	private static final int width = 32, height = 32, biggerWidth = 48, biggerHeight = 48;
	public static final int paintingWidth = 815, paintingHeight = 820;
	public static BufferedImage dirt, grass, stone, water, black, gray, darkStoneWall, woodFloor, diamondTileBlue,
			diamondTileGreen, diamondTileWood, diamondTileDouble, bathroomTile, whiteBrickMiddle, whiteBrickBottom,
			tiledFloorWhite, tiledFloorBlack;
	public static BufferedImage playerDownNormal, playerUpNormal, playerLeftNormal, playerRightNormal,
			playerDownTransparent, playerUpTransparent, playerLeftTransparent, playerRightTransparent;
	public static BufferedImage brickTopLeft, brickTopMiddle, brickTopRight, brickMiddleLeft, brickMiddleMiddle,
			brickMiddleRight, brickBottomLeft, brickBottomMiddle, brickBottomRight;
	public static BufferedImage houseWallTopLeftBlue, houseWallTopMiddleBlue, houseWallTopRightBlue, houseWallMiddleLeftBlue, houseWallMiddleMiddleBlue,
			houseWallMiddleRightBlue, houseWallBottomLeftBlue, houseWallBottomMiddleBlue, houseWallBottomRightBlue;
	public static BufferedImage houseWallTopLeftGreen, houseWallTopMiddleGreen, houseWallTopRightGreen, houseWallMiddleLeftGreen, houseWallMiddleMiddleGreen,
			houseWallMiddleRightGreen, houseWallBottomLeftGreen, houseWallBottomMiddleGreen, houseWallBottomRightGreen;
	public static BufferedImage houseWallTopLeftOrange, houseWallTopMiddleOrange, houseWallTopRightOrange, houseWallMiddleLeftOrange, houseWallMiddleMiddleOrange,
			houseWallMiddleRightOrange, houseWallBottomLeftOrange, houseWallBottomMiddleOrange, houseWallBottomRightOrange;
	public static BufferedImage houseWallTopLeftRed, houseWallTopMiddleRed, houseWallTopRightRed, houseWallMiddleLeftRed, houseWallMiddleMiddleRed,
			houseWallMiddleRightRed, houseWallBottomLeftRed, houseWallBottomMiddleRed, houseWallBottomRightRed;
	public static BufferedImage wallBorderVerticalLeft, wallBorderVerticalRight, wallBorderCornerBottomRight,
			wallBorderCornerBottomLeft, wallBorderCornerTopRight, wallBorderCornerTopLeft, wallBorderRightBottom,
			wallBorderLeftBottom, wallBorderRightTop, wallBorderLeftTop, wallBorderTop, wallBorderRight, wallBorderLeft,
			wallBorderBottom;
	public static BufferedImage roofBackLeft, roofBackMiddle, roofBackRight, roofMiddleLeft, roofMiddleMiddle,
			roofMiddleRight, roofFrontLeft, roofFrontMiddle, roofFrontRight, lowerRoofLeft, lowerRoofRight;
	//these are named for where edges are on the flat pieces
	public static BufferedImage roofPivotBottomRight, roofPivotBottomLeft, roofPivotTopRight, roofPivotTopLeft;
	public static BufferedImage roofFlatLeftBottomRight, roofFlatLeftRight, roofFlatTopLeftBottom, roofFlatTopBottom,
			roofFlatTopRightBottom, roofFlatTopLeft, roofFlatTop, roofFlatTopRight, roofFlatLeftBottom, roofFlatBottom,
			roofFlatRightBottom, roofFlatLeft, roofFlat, roofFlatRight;
	public static BufferedImage closedDoorOne, closedDoorTwo, stairs, doorwayArch, bathroomDoorMale, bathroomDoorFemale,
			locker, windowOne, windowTwo, chimney;
	public static BufferedImage bedOne, chairOneDown, chairOneUp, chairOneLeft, chairOneRight;
	public static BufferedImage windowLight, tableLampOff, tableLampOn;
	public static BufferedImage yellowLight;
	public static BufferedImage headDown, headUp, headLeft, headRight;
	public static BufferedImage darkWall, concrete, artFrame, artFrameSmall, artFrameSmallEmpty;
	public static BufferedImage buttonPuzzlePanel, buttonPuzzleBorder, buttonPuzzleOn, buttonPuzzleOff;
	public static BufferedImage galleryAbyss, galleryApparition, galleryBlight, galleryButcher, galleryCaptivity,
			galleryCipher, galleryCondemnation, galleryDeluge, galleryDissonance, galleryDuty, galleryFacade,
			galleryHarbinger, galleryHarrower, galleryHolocaust, galleryManifestation, galleryMoribund, galleryOblation,
			galleryPerdition, galleryProcession, galleryProphet, galleryRevelry, gallerySenescence, gallerySolace,
			gallerySuffering, gallerySynchronicity, galleryTorment, galleryVilomah;
	public static BufferedImage textboxDefault, textboxPlayerThinking, textboxPlayer, textboxDenial, textboxAnger,
			textboxBargaining, textboxDepression, textboxAcceptance, textboxOptions, portrait;
	public static BufferedImage inventory, inventoryHighlight, keyInventory, abyssInventory, apparitionInventory,
			blightInventory, butcherInventory, captivityInventory, cipherInventory, condemnationInventory, delugeInventory,
			dissonanceInventory, dutyInventory, facadeInventory, harbingerInventory, harrowerInventory, holocaustInventory,
			manifestationInventory, moribundInventory, oblationInventory, perditionInventory, processionInventory, prophetInventory,
			revelryInventory, senescenceInventory, solaceInventory, sufferingInventory, synchronicityInventory, tormentInventory,
			vilomahInventory;
	public static BufferedImage hole, firePlace, fireUnlit, fireExtinguished;
	public static BufferedImage teacherDesk, emptyDesk, denialDesk, angerDesk, bargainingDesk, depressionDesk, acceptanceDesk, playerDesk;
	public static BufferedImage[] grandfatherClock;
	public static BufferedImage[] dressers, fire, chalkboard;
	public static BufferedImage[] activeInventoryHeader;
	public static BufferedImage[] playerDown, playerUp, playerLeft, playerRight;
	public static BufferedImage[] denialDown, denialUp, denialLeft, denialRight;
	public static BufferedImage[] angerDown, angerUp, angerLeft, angerRight;
	public static BufferedImage[] bargainingDown, bargainingUp, bargainingLeft, bargainingRight;
	public static BufferedImage[] depressionDown, depressionUp, depressionLeft, depressionRight;
	public static BufferedImage[] acceptanceDown, acceptanceUp, acceptanceLeft, acceptanceRight;
	public static BufferedImage[] ghoulDown, ghoulLeft, ghoulRight, ghoulUp;
	public static BufferedImage[] tunnelVision;
	public static Font sans, serif, philosopher, textboxFontDefault;
	public static Font playerSpeakingFont, playerThinkingFont, acceptanceFont, denialFont, angerFont, bargainingFont, depressionFont;
	public static AudioClip menuMove, woodBreak, openInventory, closeInventory, textTest, textTest2,
			denialText, angerText, bargainingText, depressionText, acceptanceText, playerText;
	public static Clip menuMusic;
	public static void init() {
		initSounds();
		initFonts();
		initSpriteSheets();
		initStandaloneImages();
	}

	private static void initSounds() {
		menuMove = Applet.newAudioClip(Assets.class.getResource("/sounds/menuMove.au"));
		woodBreak = Applet.newAudioClip(Assets.class.getResource("/sounds/woodBreak.au"));
		openInventory = Applet.newAudioClip(Assets.class.getResource("/sounds/openInventory.au"));
		closeInventory = Applet.newAudioClip(Assets.class.getResource("/sounds/closeInventory.au"));
		textTest = Applet.newAudioClip(Assets.class.getResource("/sounds/text-test.au"));
		textTest2  = Applet.newAudioClip(Assets.class.getResource("/sounds/text-test-2.au"));
		try {
			menuMusic = AudioSystem.getClip();
			AudioInputStream ais1 = AudioSystem.getAudioInputStream(new File("res/music/desolate.au"));
			menuMusic.open(ais1);
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

		SpriteSheet housesSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tile-sheets/overworld/houses-sheet.png"));
		brickTopLeft = housesSheet.crop(0, 0, width, height);
		brickTopMiddle = housesSheet.crop(width, 0, width, height);
		brickTopRight = housesSheet.crop(width * 2, 0, width, height);
		brickMiddleLeft = housesSheet.crop(0, height, width, height);
		brickMiddleMiddle = housesSheet.crop(width, height, width, height);
		brickMiddleRight = housesSheet.crop(width * 2, height, width, height);
		brickBottomLeft = housesSheet.crop(0, height * 2, width, height);
		brickBottomMiddle = housesSheet.crop(width, height * 2, width, height);
		brickBottomRight = housesSheet.crop(width * 2, height * 2, width, height);
		roofBackLeft = housesSheet.crop(0, height * 3, width, height);
		roofBackMiddle = housesSheet.crop(width, height * 3, width, height);
		roofBackRight = housesSheet.crop(width * 2, height * 3, width, height);
		roofMiddleLeft = housesSheet.crop(0, height * 4, width, height);
		roofMiddleMiddle = housesSheet.crop(width, height * 4, width, height);
		roofMiddleRight = housesSheet.crop(width * 2, height * 4, width, height);
		roofFrontLeft = housesSheet.crop(0, height * 5, width, height);
		roofFrontMiddle = housesSheet.crop(width, height * 5, width, height);
		roofFrontRight = housesSheet.crop(width * 2, height * 5, width, height);
		lowerRoofLeft = housesSheet.crop(0, height * 6, width, height);
		lowerRoofRight = housesSheet.crop(width * 2, height * 6, width, height);
		roofPivotBottomRight = housesSheet.crop(width * 3, 0, width, height);
		roofPivotBottomLeft = housesSheet.crop(width * 4, 0, width, height);
		roofPivotTopRight = housesSheet.crop(width * 3, height, width, height);
		roofPivotTopLeft = housesSheet.crop(width * 4, height, width, height);
		roofFlatLeftBottomRight = housesSheet.crop(width * 3, height * 2, width, height);
		roofFlatLeftRight = housesSheet.crop(width * 4, height * 2, width, height);
		roofFlatTopLeftBottom = housesSheet.crop(width * 3, height * 3, width, height);
		roofFlatTopBottom = housesSheet.crop(width * 4, height * 3, width, height);
		roofFlatTopRightBottom = housesSheet.crop(width * 5, height * 3, width, height);
		roofFlatTopLeft = housesSheet.crop(width * 3, height * 4, width, height);
		roofFlatTop = housesSheet.crop(width * 4, height * 4, width, height);
		roofFlatTopRight = housesSheet.crop(width * 5, height * 4, width, height);
		roofFlatLeftBottom = housesSheet.crop(width * 3, height * 5, width, height);
		roofFlatBottom = housesSheet.crop(width * 4, height * 5, width, height);
		roofFlatRightBottom = housesSheet.crop(width * 5, height * 5, width, height);
		roofFlatLeft = housesSheet.crop(width * 3, height * 6, width, height);
		roofFlat = housesSheet.crop(width * 4, height * 6, width, height);
		roofFlatRight = housesSheet.crop(width * 5, height * 6, width, height);

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
		stairs = staticEntities.crop(0, 44 + height, width, height - height / 4);
		chimney = staticEntities.crop(0, 44 + height + (height - height / 4), width, 30);
		closedDoorOne = staticEntities.crop(0, 44 + height + (height - height / 4) + 30, width, height + height / 2);
		closedDoorTwo = staticEntities.crop(0, 44 + height + (height - height / 4) + 30 + height + (height / 2), width, 46);

		SpriteSheet chairsBedSheet = new SpriteSheet(ImageLoader.loadImage("/textures/static-entities/chairs-bed-sheet.png"));
		bedOne = chairsBedSheet.crop(0, 0, 48, 81);
		chairOneDown = chairsBedSheet.crop(48, 0, 26, 32);
		chairOneUp = chairsBedSheet.crop(48, 32, 26, 24);
		chairOneRight = chairsBedSheet.crop(48, 56, 26, 32);
		chairOneLeft = chairsBedSheet.crop(48, 88, 26, 32);

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

		SpriteSheet mcSheetTransparent = new SpriteSheet(ImageLoader.loadImage("/textures/characters/mc-sheet-transparent.png"));
		playerDownTransparent = mcSheetTransparent.crop(0, 0, width, height);
		playerUpTransparent = mcSheetTransparent.crop(0, height, width, height);
		playerLeftTransparent = mcSheetTransparent.crop(0, height * 2, width, height);
		playerRightTransparent = mcSheetTransparent.crop(0, height * 3, width, height);

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

		darkWall = ImageLoader.loadImage("/textures/backgrounds/wall.png");
		concrete = ImageLoader.loadImage("/textures/backgrounds/concrete.png");
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

		hole = ImageLoader.loadImage("/textures/hole.png");
		doorwayArch = ImageLoader.loadImage("/textures/oga/LPC-Base-Assets/tiles/doorwayArch.png");
		windowLight = ImageLoader.loadImage("/textures/static-entities/window-light.png");
		yellowLight = ImageLoader.loadImage("/textures/effects/light-yellow.png");
		firePlace = ImageLoader.loadImage("/textures/static-entities/fireplace-1.png");
		teacherDesk = ImageLoader.loadImage("/textures/static-entities/teacher-desk.png");
	}
}
