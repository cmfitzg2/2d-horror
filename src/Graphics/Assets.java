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
	public static BufferedImage dirt, grass, stone, tree, water, black, gray, darkStoneWall,
			playerDownNormal, playerUpNormal, playerLeftNormal, playerRightNormal,
			playerDownTransparent, playerUpTransparent, playerLeftTransparent, playerRightTransparent;
	public static BufferedImage wall, artFrame, artFrameSmall, artFrameSmallEmpty;
	public static BufferedImage galleryAbyss, galleryApparition, galleryBlight, galleryButcher, galleryCaptivity,
			galleryCipher, galleryCondemnation, galleryDeluge, galleryDissonance, galleryDuty, galleryFacade,
			galleryHarbinger, galleryHarrower, galleryHolocaust, galleryManifestation, galleryMoribund, galleryOblation,
			galleryPerdition, galleryProcession, galleryProphet, galleryRevelry, gallerySenescence, gallerySolace,
			gallerySuffering, gallerySynchronicity, galleryTorment, galleryVilomah;
	public static BufferedImage textbox, textboxOptions, portrait;
	public static BufferedImage inventory, inventoryHighlight, keyInventory, abyssInventory, apparitionInventory,
			blightInventory, butcherInventory, captivityInventory, cipherInventory, condemnationInventory, delugeInventory,
			dissonanceInventory, dutyInventory, facadeInventory, harbingerInventory, harrowerInventory, holocaustInventory,
			manifestationInventory, moribundInventory, oblationInventory, perditionInventory, processionInventory, prophetInventory,
			revelryInventory, senescenceInventory, solaceInventory, sufferingInventory, synchronicityInventory, tormentInventory,
			vilomahInventory;
	public static BufferedImage hole;
	public static BufferedImage[] activeInventoryHeader;
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage[] reflection_down, reflection_up, reflection_left, reflection_right;
	public static BufferedImage[] ghoulDown, ghoulLeft, ghoulRight, ghoulUp;
	public static BufferedImage[] tunnelVision;
	public static Font sans, serif, philosopher, textboxDefault;
	public static AudioClip menuMove, woodBreak, openInventory, closeInventory;
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
			textboxDefault = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/uwch.ttf")).deriveFont(128f);
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
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		dirt = sheet.crop(0,0,width,height);
		grass = sheet.crop(width, 0, width, height);
		stone = sheet.crop(width*2, 0, width, height);
		tree = sheet.crop(width*3, 0, width, height);
		water = sheet.crop(0, height*2, width, height);
		black = sheet.crop(width * 2, height * 3, width, height);
		gray = sheet.crop(width*2, height*2, width, height);
		darkStoneWall = sheet.crop(0, height, width, height);

		SpriteSheet reflectionSheet = new SpriteSheet(ImageLoader.loadImage("/textures/characterclone.png"));
		reflection_down = new BufferedImage[4];
		reflection_up = new BufferedImage[4];
		reflection_left = new BufferedImage[4];
		reflection_right = new BufferedImage[4];

		reflection_down[0] = reflectionSheet.crop(0, 0, width, height);
		reflection_down[1] = reflectionSheet.crop(width, 0, width, height);
		reflection_down[2] = reflectionSheet.crop(width*2, 0, width, height);
		reflection_down[3] = reflectionSheet.crop(width*3, 0, width, height);

		reflection_up[0] = reflectionSheet.crop(0, height, width, height);
		reflection_up[1] = reflectionSheet.crop(width, height, width, height);
		reflection_up[2] = reflectionSheet.crop(width*2, height, width, height);
		reflection_up[3] = reflectionSheet.crop(width*3, height, width, height);

		reflection_left[0] = reflectionSheet.crop(0, height*2, width, height);
		reflection_left[1] = reflectionSheet.crop(width, height*2, width, height);
		reflection_left[2] = reflectionSheet.crop(width*2, height*2, width, height);
		reflection_left[3] = reflectionSheet.crop(width*3, height*2, width, height);

		reflection_right[0] = reflectionSheet.crop(0, height*3, width, height);
		reflection_right[1] = reflectionSheet.crop(width, height*3, width, height);
		reflection_right[2] = reflectionSheet.crop(width*2, height*3, width, height);
		reflection_right[3] = reflectionSheet.crop(width*3, height*3, width, height);

		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Charactersheet.png"));
		player_down = new BufferedImage[4]; 		//4 = frame count
		player_up = new BufferedImage[4];
		player_left = new BufferedImage[4];
		player_right = new BufferedImage[4];

		player_down[0] = playerSheet.crop(0, 0, width, height);
		player_down[1] = playerSheet.crop(width, 0, width, height);
		player_down[2] = playerSheet.crop(width*2, 0, width, height);
		player_down[3] = playerSheet.crop(width*3, 0, width, height);

		player_up[0] = playerSheet.crop(0, height, width, height);
		player_up[1] = playerSheet.crop(width, height, width, height);
		player_up[2] = playerSheet.crop(width*2, height, width, height);
		player_up[3] = playerSheet.crop(width*3, height, width, height);

		player_left[0] = playerSheet.crop(0, height*2, width, height);
		player_left[1] = playerSheet.crop(width, height*2, width, height);
		player_left[2] = playerSheet.crop(width*2, height*2, width, height);
		player_left[3] = playerSheet.crop(width*3, height*2, width, height);

		player_right[0] = playerSheet.crop(0, height*3, width, height);
		player_right[1] = playerSheet.crop(width, height*3, width, height);
		player_right[2] = playerSheet.crop(width*2, height*3, width, height);
		player_right[3] = playerSheet.crop(width*3, height*3, width, height);

		playerDownNormal = player_down[0];
		playerLeftNormal = player_left[0];
		playerRightNormal = player_right[0];
		playerUpNormal = player_up[0];

		SpriteSheet playerSheetTransparent = new SpriteSheet(ImageLoader.loadImage("/textures/Charactersheet-transparent.png"));
		playerDownTransparent = playerSheetTransparent.crop(0, 0, width, height);
		playerUpTransparent = playerSheetTransparent.crop(0, height, width, height);
		playerLeftTransparent = playerSheetTransparent.crop(0, height * 2, width, height);
		playerRightTransparent = playerSheetTransparent.crop(0, height * 3, width, height);

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
		textbox = ImageLoader.loadImage("/textures/tb.png");
		textboxOptions = ImageLoader.loadImage("/textures/tb-options.png");
		portrait = ImageLoader.loadImage("/textures/portrait.png");
		inventory = ImageLoader.loadImage("/textures/inventory.png");
		inventoryHighlight = ImageLoader.loadImage("/textures/inventory-highlight.png");

		keyInventory = ImageLoader.loadImage("/textures/inventory/key.png");

		tunnelVision = new BufferedImage[46];
		for (int i = 0, j = 20; i < tunnelVision.length; i++, j += 2) {
			tunnelVision[i] = ImageLoader.loadImage("/textures/reduced-vision/vision-" + j + "-75.png");
		}

		wall = ImageLoader.loadImage("/textures/wall.png");
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
	}
}
