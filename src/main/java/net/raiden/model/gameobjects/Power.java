package net.raiden.model.gameobjects;

import net.raiden.model.Game;
import net.raiden.model.GameObject;

import java.awt.*;

public class Power extends GameObject {
    private GameObject go;
    private int maxLifeValue;

    public Power(GameObject go, int x, int y) {
        this.width = 200;
        this.height = 15;
        this.x = x;
        this.y = y;
        this.go = go;
        maxLifeValue = go.getLifeValue();
    }

    @Override
    protected void draw(Graphics g) {
        Color old = g.getColor();
        Font oldFont = g.getFont();
        g.setColor(Color.WHITE);
        g.fill3DRect(x, y, width, height, true);
        g.setColor(Color.RED);
        int w = (int) (go.getLifeValue() * 1.0 / maxLifeValue * this.width);
        g.fill3DRect(x, y, w, height, true);
        if (go instanceof Hero) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑", Font.ITALIC, 30));
            g.drawString("X" + Game.getInstance().getMaxLife(), x + this.width + 5, y + 17);
        }
        g.setColor(old);
        g.setFont(oldFont);
        if (go.isDead()) {
            this.die();
        }
    }

}
