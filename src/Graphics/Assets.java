package Graphics;

import Utils.ImageLoader;

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
	public static BufferedImage textbox, textboxOptions;
	public static BufferedImage inventory, inventoryHighlight, keyPreview, abyssPreview, apparitionPreview,
			blightPreview, butcherPreview, captivityPreview, cipherPreview, condemnationPreview, delugePreview,
			dissonancePreview, dutyPreview, facadePreview, harbingerPreview, harrowerPreview, holocaustPreview,
			manifestationPreview, moribundPreview, oblationPreview, perditionPreview, processionPreview, prophetPreview,
			revelryPreview, senescencePreview, solacePreview, sufferingPreview, synchronicityPreview, tormentPreview,
			vilomahPreview;
	public static BufferedImage hole;
	public static BufferedImage[] activeInventoryHeader;
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage[] reflection_down, reflection_up, reflection_left, reflection_right;
	public static BufferedImage[] ghoulDown, ghoulLeft, ghoulRight, ghoulUp;
	public static BufferedImage[] tunnelVision;
	public static Font sans, serif, philosopher, textboxDefault;
	public static void init() {
		try {
			//create the font to use. Specify the size!
			sans = Font.createFont(Font.TRUETYPE_FONT, new File("sans.ttf")).deriveFont(36f);
			serif = Font.createFont(Font.TRUETYPE_FONT, new File("serif.ttf")).deriveFont(36f);
			philosopher = Font.createFont(Font.TRUETYPE_FONT, new File("philosopher.ttf")).deriveFont(36f);
			textboxDefault = Font.createFont(Font.TRUETYPE_FONT, new File("uwch.ttf")).deriveFont(128f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			//register the font
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("sans.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("serif.ttf")));
		} catch (IOException e) {
			e.printStackTrace();
		} catch(FontFormatException e) {
			e.printStackTrace();
		}

		SpriteSheet reflectionSheet = new SpriteSheet(ImageLoader.loadImage("/textures/characterclone.png"));
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Charactersheet.png"));
		SpriteSheet playerSheetTransparent = new SpriteSheet(ImageLoader.loadImage("/textures/Charactersheet-transparent.png"));
		SpriteSheet ghoulSheet = new SpriteSheet(ImageLoader.loadImage("/textures/ghoul.png"));
		SpriteSheet inventoryActiveSheet = new SpriteSheet(ImageLoader.loadImage("/textures/inventory-active-header.png"));

		textbox = ImageLoader.loadImage("/textures/tb.png");
		textboxOptions = ImageLoader.loadImage("/textures/tb-options.png");

		activeInventoryHeader = new BufferedImage[3];
		activeInventoryHeader[0] = inventoryActiveSheet.crop(0, 0, 268, 70);
		activeInventoryHeader[1] = inventoryActiveSheet.crop(268, 0, 268, 70);
		activeInventoryHeader[2] = inventoryActiveSheet.crop(535, 0, 268, 70);
		inventory = ImageLoader.loadImage("/textures/inventory.png");
		inventoryHighlight = ImageLoader.loadImage("/textures/inventory-highlight.png");

		keyPreview = ImageLoader.loadImage("/textures/inventory/key.png");

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

		playerDownTransparent = playerSheetTransparent.crop(0, 0, width, height);
		playerUpTransparent = playerSheetTransparent.crop(0, height, width, height);
		playerLeftTransparent = playerSheetTransparent.crop(0, height * 2, width, height);
		playerRightTransparent = playerSheetTransparent.crop(0, height * 3, width, height);

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

		tunnelVision = new BufferedImage[46];
		for (int i = 0, j = 20; i < tunnelVision.length; i++, j+=2) {
			tunnelVision[i] = ImageLoader.loadImage("/textures/reduced-vision/vision-" + j + "-75.png");
		}

		dirt = sheet.crop(0,0,width,height);
		grass = sheet.crop(width, 0, width, height);
		stone = sheet.crop(width*2, 0, width, height);
		tree = sheet.crop(width*3, 0, width, height);
		water = sheet.crop(0, height*2, width, height);
		black = sheet.crop(width * 2, height * 3, width, height);
		gray = sheet.crop(width*2, height*2, width, height);
		darkStoneWall = sheet.crop(0, height, width, height);

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

		abyssPreview = ImageLoader.loadImage("/textures/inventory/Abyss.png");
		apparitionPreview = ImageLoader.loadImage("/textures/inventory/Apparition.png");
		blightPreview = ImageLoader.loadImage("/textures/inventory/Blight.png");
		butcherPreview = ImageLoader.loadImage("/textures/inventory/Butcher.png");
		captivityPreview = ImageLoader.loadImage("/textures/inventory/Captivity.png");
		cipherPreview = ImageLoader.loadImage("/textures/inventory/Cipher.png");
		condemnationPreview = ImageLoader.loadImage("/textures/inventory/Condemnation.png");
		delugePreview = ImageLoader.loadImage("/textures/inventory/Deluge.png");
		dissonancePreview = ImageLoader.loadImage("/textures/inventory/Dissonance.png");
		dutyPreview = ImageLoader.loadImage("/textures/inventory/Duty.png");
		facadePreview = ImageLoader.loadImage("/textures/inventory/Facade.png");
		harbingerPreview = ImageLoader.loadImage("/textures/inventory/Harbinger.png");
		harrowerPreview = ImageLoader.loadImage("/textures/inventory/Harrower.png");
		holocaustPreview = ImageLoader.loadImage("/textures/inventory/Holocaust.png");
		manifestationPreview = ImageLoader.loadImage("/textures/inventory/Manifestation.png");
		moribundPreview = ImageLoader.loadImage("/textures/inventory/Moribund.png");
		oblationPreview = ImageLoader.loadImage("/textures/inventory/Oblation.png");
		perditionPreview = ImageLoader.loadImage("/textures/inventory/Perdition.png");
		processionPreview = ImageLoader.loadImage("/textures/inventory/Procession.png");
		prophetPreview = ImageLoader.loadImage("/textures/inventory/Prophet.png");
		revelryPreview = ImageLoader.loadImage("/textures/inventory/Revelry.png");
		senescencePreview = ImageLoader.loadImage("/textures/inventory/Senescence.png");
		solacePreview = ImageLoader.loadImage("/textures/inventory/Solace.png");
		sufferingPreview = ImageLoader.loadImage("/textures/inventory/Suffering.png");
		synchronicityPreview = ImageLoader.loadImage("/textures/inventory/Synchronicity.png");
		tormentPreview = ImageLoader.loadImage("/textures/inventory/Torment.png");
		vilomahPreview = ImageLoader.loadImage("/textures/inventory/Vilomah.png");

		hole = ImageLoader.loadImage("/textures/hole.png");
	}

	public static BufferedImage getArtworkByName(String name) {
		switch (name) {
			case "Solace":
				return gallerySolace;
			case "Prophet":
				return galleryProphet;
			default:
				return artFrame;
		}
	}
}
