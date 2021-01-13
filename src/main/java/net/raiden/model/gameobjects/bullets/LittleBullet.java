package net.raiden.model.gameobjects.bullets;

import net.raiden.model.Direction;
import net.raiden.model.gameobjects.Bullet;

import java.awt.*;

public class LittleBullet extends Bullet {

    public LittleBullet(int x, int y, Direction d) {
        super(x, y, 10, 10, 30, d, 50);
    }

    public LittleBullet(int x, int y, Direction d, boolean good) {
        super(x, y, 10, 10, 30, d, good, 50);
    }

    @Override
    protected void draw(Graphics g) {
        Color old = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillOval(x - 5, y + 5, width, height);
        g.setColor(old);
    }

}
