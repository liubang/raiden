package net.raiden.model.gameobjects;

import net.raiden.model.FireStrategy;
import net.raiden.model.Game;
import net.raiden.model.GameObject;
import net.raiden.model.SoundPlayer;
import net.raiden.view.GameMain;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Hero extends GameObject {

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
            img = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream("/images/hero.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        props = new Properties();
        try {
            props.load(getClass().getResourceAsStream("/config/fire_strategy.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.changeFireStrategy();
        this.lifeValue = 5000;
        power = new Power(this, 530, 540);
    }

    public void stageOver() {
        Game.getInstance().addGameObject(power);
    }

    @Override
    public void addLifeValue(int inc) {
        this.lifeValue += inc;
        if (this.lifeValue > 5000) {
            this.lifeValue = 5000;
        }
        if (this.lifeValue <= 0) {
            this.die();
        }
    }

    public void forward() {
        y -= speed;
    }

    public void back() {
        y += speed;
    }

    public void left() {
        x -= speed;
    }

    public void right() {
        x += speed;
    }

    protected void draw(Graphics g) {
        g.drawImage(img, x, y, width, height, null);
    }

    public void hit() {
        fireStrategy.fire(x + width / 2, y);
        try {
            new SoundPlayer(new BufferedInputStream(Hero.class.getResourceAsStream("/audio/fire_bullet.wav"))).play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeFireStrategy() {
        String className = props.getProperty("s" + step);
        try {
            this.fireStrategy = (FireStrategy) Class.forName(className).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        step++;
    }

    @Override
    public void die() {
        this.dead = true;
        Game.getInstance().reborn();
    }
}
