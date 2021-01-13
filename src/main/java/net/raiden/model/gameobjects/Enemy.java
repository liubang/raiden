package net.raiden.model.gameobjects;

import net.raiden.model.Direction;
import net.raiden.model.GameObject;
import net.raiden.view.GameMain;

import java.util.Random;

public abstract class Enemy extends GameObject {
    public Enemy(int x, int y, int width, int height, int speed, Direction d, int lifeValue) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.d = d;
        this.lifeValue = lifeValue;
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
        if (new Random().nextInt(40) > 38) {
            fire();
        }
    }

    protected abstract void fire();

    @Override
    protected void chechBounds() {
        if (x < 0) {
            x = 0;
        }
        if (x + this.width > GameMain.VIEW_WIDTH) {
            x = GameMain.VIEW_WIDTH - this.width;
        }
        if (y < 0) {
            y = 0;
        }
        if (y + this.height > 300) {
            y = 300 - this.height;
        }
    }

    @Override
    public void die() {
        super.die();
        new Exploder(x, y);
    }
}
