package net.raiden.model.gameobjects;

import net.raiden.model.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Exploder extends GameObject {
    private static Image[] imgs = new Image[3];
    private int times = 0;

    static {
        for (int i = 0; i < imgs.length; i++) {
            try {
                imgs[i] = ImageIO
                        .read(Exploder.class.getResourceAsStream("/images/bomb_" + (i + 1) + ".gif"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Exploder(int x, int y) {
        this.width = 50;
        this.height = 50;
        this.x = x;
        this.y = y;
    }

    @Override
    protected void draw(Graphics g) {
        g.drawImage(imgs[times++], x, y, width, height, null);
        if (times == imgs.length) {
            this.die();
        }
    }
}
