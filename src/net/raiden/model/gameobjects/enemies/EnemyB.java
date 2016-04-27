package net.raiden.model.gameobjects.enemies;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import net.raiden.model.Direction;
import net.raiden.model.gameobjects.Enemy;
import net.raiden.model.gameobjects.bullets.LaserBullet;
import net.raiden.model.gameobjects.bullets.SilverBullet;
import net.raiden.view.GameMain;

public class EnemyB extends Enemy
{
	private static Image img;
	static {
		try {
			SilverBullet.class.getClassLoader();
			img = ImageIO.read(ClassLoader.getSystemResourceAsStream("images/enemya.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public EnemyB() {
		super(new Random().nextInt(GameMain.VIEW_WIDTH - 60), new Random().nextInt(300), 60, 60, 10, Direction.D, 2000);
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
			new LaserBullet(x + this.width / 2 - 2, y + 50, Direction.RD, false);
		} else if (x > GameMain.VIEW_HEIGHT / 2 + 100) {
			new LaserBullet(x + this.width / 2 - 2, y + 50, Direction.LD, false);
		} else {
			new LaserBullet(x + this.width / 2 - 2, y + 50, Direction.D, false);
		}
	}

}
