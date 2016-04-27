package net.raiden.model.gameobjects.enemies;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import net.raiden.model.Direction;
import net.raiden.model.Game;
import net.raiden.model.gameobjects.Enemy;
import net.raiden.model.gameobjects.Exploder;
import net.raiden.model.gameobjects.Power;
import net.raiden.model.gameobjects.bullets.LaserBullet;
import net.raiden.model.gameobjects.bullets.SilverBullet;
import net.raiden.view.GameMain;

public class Boss extends Enemy
{
	private static Image img;
	private int times;
	static {
		try {
			SilverBullet.class.getClassLoader();
			img = ImageIO.read(ClassLoader.getSystemResourceAsStream("images/boss.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Boss() {
		super(300, 50, 120, 60, 10, Direction.L, 20000);
		new Power(this, 10, 10);
	}

	@Override
	protected void draw(Graphics g)
	{
		g.drawImage(img, x, y, width, height, null);
	}

	@Override
	protected void fire()
	{
		times++;
		if (times == 3) {
			new LaserBullet(x + this.width / 2 - 2, y + 50, Direction.D, false);
			new LaserBullet(x + this.width / 2 - 2, y + 50, Direction.D, false);
			new LaserBullet(x + this.width / 2 - 2, y + 50, Direction.D, false);
			times = 0;
		} else {

			if (x < GameMain.VIEW_HEIGHT / 2 - 100) {
				new SilverBullet(x + this.width / 2 - 5, y + 50, Direction.RD, false);
				new SilverBullet(x + this.width / 2 - 5, y + 50, Direction.RD, false);
				new SilverBullet(x + this.width / 2 - 5, y + 50, Direction.RD, false);
			} else if (x > GameMain.VIEW_HEIGHT / 2 + 100) {
				new SilverBullet(x + this.width / 2 - 5, y + 50, Direction.LD, false);
				new SilverBullet(x + this.width / 2 - 5, y + 50, Direction.LD, false);
				new SilverBullet(x + this.width / 2 - 5, y + 50, Direction.LD, false);
			} else {
				new SilverBullet(x + this.width / 2 - 5, y + 50, Direction.D, false);
				new SilverBullet(x + this.width / 2 - 5, y + 50, Direction.D, false);
				new SilverBullet(x + this.width / 2 - 5, y + 50, Direction.D, false);
			}
		}
	}

	@Override
	protected void move()
	{
		if (x <= 0) {
			this.d = Direction.R;
		}
		if (x + this.width >= GameMain.VIEW_WIDTH) {
			this.d = Direction.L;
		}
		switch (d) {
			case L:
				x -= speed;
				break;
			case R:
				x += speed;
				break;
			default :
				break;
		}
		if (new Random().nextInt(40) > 30) {
			fire();
		}
	}

	@Override
	public void die()
	{
		this.dead = true;
		Random r = new Random();
		for (int i = 0; i < 40; i++) {
			new Exploder(r.nextInt(GameMain.VIEW_WIDTH), r.nextInt(GameMain.VIEW_HEIGHT));
		}

		Game.getInstance().stageOver();
	}
}
