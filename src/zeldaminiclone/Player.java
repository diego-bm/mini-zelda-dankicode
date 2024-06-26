package zeldaminiclone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle {
	// Movement variables
	public int spd = 4;
	public boolean right, up, down, left;
	// Animation variables
	public int curAnimation = 0;
	public int curFrames = 0, targetFrames = 16;
	// Bullet variables
	public static List<Bullet> bullets = new ArrayList<>();
	public boolean shoot = false;
	public int dir = 1;
	
	public Player(int x, int y) {
		super(x, y, 32, 32);
	}

	public void tick() {
		boolean moved = false;
		
		if(right && World.isFree(x+spd, y)) {
			x+=spd;
			moved = true;
			dir = 1;
		} else if(left && World.isFree(x-spd, y)) {
			x-=spd;
			moved = true;
			dir = -1;
		}
		
		if(up && World.isFree(x, y-spd)) {
			y-=spd;
			moved = true;
		} else if(down && World.isFree(x, y+spd)) {
			y+=spd;
			moved = true;
		}
		
		if(moved) {
			animatePlayer();
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
		g.drawImage(Spritesheet.player_front[curAnimation], x, y, 32, 32, null);
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
	}
	
	private void animatePlayer() {
		curFrames++;
		if(curFrames == targetFrames) {
			curFrames = 0;
			curAnimation++;
			if(curAnimation == Spritesheet.player_front.length) {
				curAnimation = 0;
			}
		}
	}
}
