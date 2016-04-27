package net.raiden.model.gameobjects.bullets;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.raiden.model.Direction;
import net.raiden.model.gameobjects.Bullet;

public class SilverBullet extends Bullet
{
	private static Image img;
	static {
		try {
			SilverBullet.class.getClassLoader();
			img = ImageIO.read(ClassLoader.getSystemResourceAsStream("images/silverbullet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public SilverBullet(int x, int y, Direction d) {
		super(x, y, 10, 10, 30, d, 80);
	}

	public SilverBullet(int x, int y, Direction d, boolean good) {
		super(x, y, 10, 10, 30, d, good, 80);
	}

	@Override
	protected void draw(Graphics g)
	{
		g.drawImage(img, x - 5, y - 5, width, height, null);
	}
}
