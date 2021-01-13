package net.raiden.model;

import net.raiden.view.GameMain;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class BackGround {
    private Image img = null;
    private int y;

    public BackGround() {
        try {
            img = ImageIO.read(BackGround.class.getClassLoader().getResourceAsStream("images/bg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        if (y > GameMain.VIEW_HEIGHT) {
            y = 0;
        }
        y += 2;
        g.drawImage(img, 0, y - GameMain.VIEW_HEIGHT, GameMain.VIEW_WIDTH, GameMain.VIEW_HEIGHT, null);
        g.drawImage(img, 0, y, GameMain.VIEW_WIDTH, GameMain.VIEW_HEIGHT, null);
    }
}
