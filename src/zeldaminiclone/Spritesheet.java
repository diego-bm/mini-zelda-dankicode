package zeldaminiclone;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	public static BufferedImage spritesheet;
	public static BufferedImage[] player_front;
	public static BufferedImage[] inimigo_front;
	public static BufferedImage tileWall;
	
	public Spritesheet() {
		try {
			spritesheet = ImageIO.read(getClass().getResource("/spritesheet.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		getPlayerAnimationSprites();
		getInimigoAnimationSprites();
		getTileWallSprite();		
	}
	
	public static BufferedImage getSprite(int x, int y, int width, int height) {
		return spritesheet.getSubimage(x, y, width, height);
	}
	
	private void getPlayerAnimationSprites() {
		player_front = new BufferedImage[2];
		player_front[0] = Spritesheet.getSprite(1, 11, 16, 16);
		player_front[1] = Spritesheet.getSprite(18, 11, 16, 16);
	}
	
	private void getInimigoAnimationSprites() {
		inimigo_front = new BufferedImage[2];
		inimigo_front[0] = Spritesheet.getSprite(273, 228, 16, 16);
		inimigo_front[1] = Spritesheet.getSprite(290, 228, 16, 16);
	}
	
	private void getTileWallSprite() {
		tileWall = Spritesheet.getSprite(273, 211, 16, 16);
	}
}
