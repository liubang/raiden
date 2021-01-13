package net.raiden.model.gameobjects;

import net.raiden.model.Direction;
import net.raiden.model.GameObject;
import net.raiden.view.GameMain;

import java.awt.*;
import java.util.Random;

public class BloodUpObject extends GameObject {
    private Color c = Color.RED;

    public BloodUpObject() {
        this.width = 100;
        this.height = 50;
        Random r = new Random();
        this.x = r.nextInt(GameMain.VIEW_WIDTH - this.width);
        this.y = r.nextInt(GameMain.VIEW_HEIGHT - this.height);
        this.lifeValue = r.nextInt(1000) + 200;
        this.speed = 6;
        this.d = Direction.U;
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
        g.setColor(Color.WHITE);
        g.drawString("+" + this.lifeValue, x + 20, y + 20);
        g.setColor(old);
    }

    @Override
    protected void move() {
        if (new Random().nextInt(40) > 38) {
            Direction[] ds = Direction.values();
            this.d = ds[new Random().nextInt(ds.length)];
        }
        switch (d) {
            case U:
                y -= speed;
                break;
            case D:
                y += speed;
                break;
            case L:
                x -= speed;
                break;
            case R:
                x += speed;
                break;
            case LU:
                y -= speed;
                x -= speed;
                break;
            case LD:
                y += speed;
                x -= speed;
                break;
            case RD:
                y += speed;
                x += speed;
                break;
            case RU:
                y -= speed;
                x += speed;
                break;
        }
    }

}
