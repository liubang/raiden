package net.raiden.model.gameobjects;

import net.raiden.model.Direction;
import net.raiden.model.GameObject;
import net.raiden.view.GameMain;

public abstract class Bullet extends GameObject {

    public Bullet(int x, int y, int width, int height, int speed, Direction d, int lifeValue) {
        this(x, y, width, height, speed, d, true, lifeValue);
    }

    public Bullet(int x, int y, int width, int height, int speed, Direction d, boolean good, int lifeValue) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.d = d;
        this.good = good;
        this.lifeValue = lifeValue;
    }

    @Override
    protected void chechBounds() {
        if (x < 0 || y < 0 || (x + this.width) > GameMain.VIEW_WIDTH || (y + this.height) > GameMain.VIEW_HEIGHT) {
            this.die();
        }
    }

    protected void move() {
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
