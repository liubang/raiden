package net.raiden.model;

import net.raiden.view.GameMain;

import java.awt.*;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int speed;
    protected boolean dead;
    protected Direction d;
    protected int lifeValue;
    protected boolean good;

    public GameObject() {
        Game.getInstance().addGameObject(this);
    }

    public void die() {
        dead = true;
    }

    public boolean isDead() {
        return dead;
    }

    public boolean isGood() {
        return good;
    }

    public void paint(Graphics g) {
        move();
        chechBounds();
        draw(g);
    }

    protected abstract void draw(Graphics g);

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
        if (y + this.height > GameMain.VIEW_HEIGHT) {
            y = GameMain.VIEW_HEIGHT - this.height;
        }
    }

    protected void move() {

    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    public void addLifeValue(int inc) {
        this.lifeValue += inc;
        if (this.lifeValue <= 0) {
            this.die();
        }
    }

    public int getLifeValue() {
        return lifeValue;
    }
}
