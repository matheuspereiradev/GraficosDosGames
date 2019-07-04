package Game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

	public BufferedImage folhadesprites;
	
	public Spritesheet(String path) {
		try {
			folhadesprites=ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite(int x, int y,int widith, int height) {
		return folhadesprites.getSubimage(x, y, widith, height);
	}
}
