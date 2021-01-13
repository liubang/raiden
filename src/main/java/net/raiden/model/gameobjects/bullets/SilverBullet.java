package net.raiden.model.gameobjects.bullets;

import net.raiden.model.Direction;
import net.raiden.model.gameobjects.Bullet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class SilverBullet extends Bullet {
    private static Image img;
    private static final String IMAGE_PATH = "/images/silverbullet.png";

    static {
        try {
            SilverBullet.class.getClassLoader();
            img = ImageIO.read(SilverBullet.class.getResourceAsStream(IMAGE_PATH));
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
    protected void draw(Graphics g) {
        g.drawImage(img, x - 5, y - 5, width, height, null);
    }
}
