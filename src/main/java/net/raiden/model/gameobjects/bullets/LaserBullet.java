package net.raiden.model.gameobjects.bullets;

import net.raiden.model.Direction;
import net.raiden.model.gameobjects.Bullet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class LaserBullet extends Bullet {
    private static Image img;
    private static final String IMAGE_PATH = "/images/laserbullet.png";

    static {
        try {
            SilverBullet.class.getClassLoader();
            img = ImageIO.read(LaserBullet.class.getResourceAsStream(IMAGE_PATH));
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
    protected void draw(Graphics g) {
        g.drawImage(img, x - 2, y, width, height, null);
    }

}
