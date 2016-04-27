package net.raiden.model.gameobjects.bullets;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.raiden.model.Direction;
import net.raiden.model.gameobjects.Bullet;

public class LaserBullet extends Bullet
{
	private static Image img;
	static {
		try {
			SilverBullet.class.getClassLoader();
			img = ImageIO.read(ClassLoader.getSystemResourceAsStream("images/laserbullet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public LaserBullet(int x, int y, Direction d) {
		super(x, y, 4, 25, 30, d, 100);
	}

	public LaserBullet(int x, int y, Direction d, boolean good) {
		super(x, y, 4, 25, 30, d, good, 100);
	}

	@Override
	protected void draw(Graphics g)
	{
		g.drawImage(img, x - 2, y, width, height, null);
	}

}
