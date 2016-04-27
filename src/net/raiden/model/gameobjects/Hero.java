package net.raiden.model.gameobjects;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;

import net.raiden.model.FireStrategy;
import net.raiden.model.Game;
import net.raiden.model.GameObject;
import net.raiden.model.SoundPlayer;
import net.raiden.view.GameMain;

public class Hero extends GameObject
{

	private Image img;
	private int step = 0;
	private FireStrategy fireStrategy;
	private Properties props;
	private Power power;

	public Hero() {
		this.width = 76;
		this.height = 100;
		this.x = (GameMain.VIEW_WIDTH - this.width) / 2;
		this.y = GameMain.VIEW_HEIGHT - this.height;
		this.speed = 10;
		try {
			img = ImageIO.read(Hero.class.getClassLoader().getResourceAsStream("images/hero.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		props = new Properties();
		try {
			props.load(Hero.class.getClassLoader().getResourceAsStream("config/fire_strategy.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.changeFireStrategy();
		// 初始化生命值
		this.lifeValue = 5000;
		power = new Power(this, 530, 540);
	}

	public void stageOver()
	{
		Game.getInstance().addGameObject(power);
	}

	@Override
	public void addLifeValue(int inc)
	{
		this.lifeValue += inc;
		if (this.lifeValue > 5000) {
			this.lifeValue = 5000;
		}
		if (this.lifeValue <= 0) {
			this.die();
		}
	}

	public void forward()
	{
		y -= speed;
	}

	public void back()
	{
		y += speed;
	}

	public void left()
	{
		x -= speed;
	}

	public void right()
	{
		x += speed;
	}

	protected void draw(Graphics g)
	{
		// System.out.println("当前血量：" + this.lifeValue);
		g.drawImage(img, x, y, width, height, null);
	}

	public void hit()
	{
		fireStrategy.fire(x + width / 2, y);
		try {
			new SoundPlayer("src/audio/fire_bullet.wav").play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changeFireStrategy()
	{
		String className = props.getProperty("s" + step);
		try {
			this.fireStrategy = (FireStrategy) Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		step++;
	}

	@Override
	public void die()
	{
		this.dead = true;
		Game.getInstance().reborn();
	}
}
