package zeldaminiclone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Inimigo extends Rectangle {
	// Movement variables
	public int spd = 2;
	public int right = 0, up = 0, down = 0, left = 0;
	// Animation variables
	public int curAnimation = 0;
	public int curFrames = 0, targetFrames = 16;
	// Bullet variables
	public static List<Bullet> bullets = new ArrayList<>();
	public boolean shoot = false;
	public int dir = 1;
	
	public Inimigo(int x, int y) {
		super(x, y, 32, 32);
	}
	
	public void perseguirPlayer() {
		Player p = Game.player;
		
		if(x < p.x && World.isFree(x+spd, y)) {
			if(new Random().nextInt(100) < 50) {
				x += spd;
			}
		} else if(x > p.x && World.isFree(x-spd, y)) {
			if(new Random().nextInt(100) < 50) {
				x -= spd;
			}
		}
		
		if(y < p.y && World.isFree(x, y+spd)) {
			if(new Random().nextInt(100) < 50) {
				y += spd;
			}
		} else if(y > p.y && World.isFree(x, y-spd)) {
			if(new Random().nextInt(100) < 50) {
				y -= spd;
			}
		}
	}

	public void tick() {
		boolean moved = true;
		
		perseguirPlayer();
		
		if(right == 1 && World.isFree(x + 1, y)) {
			x++;
		}
		
		if(moved) {
			animateInimigo();
		}
		
		if(shoot) {
			shoot = false;
			bullets.add(new Bullet(x, y, dir));
		}
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(Spritesheet.inimigo_front[curAnimation], x, y, 32, 32, null);
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
	}
	
	private void animateInimigo() {
		curFrames++;
		if(curFrames == targetFrames) {
			curFrames = 0;
			curAnimation++;
			if(curAnimation == Spritesheet.inimigo_front.length) {
				curAnimation = 0;
			}
		}
	}
}
