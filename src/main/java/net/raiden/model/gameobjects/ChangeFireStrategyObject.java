package net.raiden.model.gameobjects;

import net.raiden.model.GameObject;
import net.raiden.view.GameMain;

import java.awt.*;
import java.util.Random;

public class ChangeFireStrategyObject extends GameObject {
    private Color c = Color.RED;

    public ChangeFireStrategyObject() {
        this.width = 50;
        this.height = 50;
        Random r = new Random();
        this.x = r.nextInt(GameMain.VIEW_WIDTH - this.width);
        this.y = r.nextInt(GameMain.VIEW_HEIGHT - this.height);
    }

    @Override
    protected void draw(Graphics g) {
        Color old = g.getColor();
        if (c == Color.RED) {
            c = Color.GREEN;
        } else {
            c = Color.RED;
        }
        g.setColor(c);
        g.fillOval(x, y, width, height);
        g.setColor(old);
    }
}
