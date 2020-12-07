package Utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader 
{
	public static BufferedImage loadImage(String path)
	{
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			System.out.println("Couldn't read image " + path);
			System.exit(1);
		}
		return null;
	}

}