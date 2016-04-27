package net.raiden.model.gameobjects.enemies;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import net.raiden.model.Direction;
import net.raiden.model.gameobjects.Enemy;
import net.raiden.model.gameobjects.bullets.LittleBullet;
import net.raiden.model.gameobjects.bullets.SilverBullet;
import net.raiden.view.GameMain;

public class EnemyA extends Enemy
{
	private static Image img;
	static {
		try {
			SilverBullet.class.getClassLoader();
			img = ImageIO.read(ClassLoader.getSystemResourceAsStream("images/enemyb.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public EnemyA() {
		super(new Random().nextInt(GameMain.VIEW_WIDTH - 40), new Random().nextInt(300), 40, 40, 10, Direction.D, 1000);
	}

	@Override
	protected void draw(Graphics g)
	{
		g.drawImage(img, x, y, width, height, null);
	}

	@Override
	protected void fire()
	{
		if (x < GameMain.VIEW_HEIGHT / 2 - 100) {
			new LittleBullet(x + this.width / 2 - 5, y + 30, Direction.RD, false);
		} else if (x > GameMain.VIEW_HEIGHT / 2 + 100) {
			new LittleBullet(x + this.width / 2 - 5, y + 30, Direction.LD, false);
		} else {
			new LittleBullet(x + this.width / 2 - 5, y + 30, Direction.D, false);
		}
	}

}
