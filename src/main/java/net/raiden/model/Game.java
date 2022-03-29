package net.raiden.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import net.raiden.model.gameobjects.Hero;

public class Game {
    private BackGround bg;
    private Hero hero;

    private static final Game game = new Game();

    private boolean up, left, down, right, hit;

    private List<GameObject> gameObjects = new ArrayList<>();

    private GameStatus status = GameStatus.READY;

    private int step = 0;

    private GameObjectDispatcherThread gdt;

    private int maxLife = 3;

    private Game() {

    }

    public GameStatus getStatus() {
        return this.status;
    }

    public void init() {
        bg = new BackGround();
        hero = new Hero();
    }

    public static Game getInstance() {
        return game;
    }

    // 加入游戏物体到集合中
    public void addGameObject(GameObject go) {
        this.gameObjects.add(go);
    }

    public void paint(Graphics g) {
        bg.paint(g);
        showInfo(g);
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject go = gameObjects.get(i);
            for (GameObject go2 : gameObjects) {
                ColliderChain.getInstance().doCollide(go, go2);
            }
            go.paint(g);
            // 判断游戏物体是否死亡，移出集合
            if (go.isDead()) {
                this.gameObjects.remove(go);
            }
        }

        move();
    }

    public void move() {
        if (status == GameStatus.RUNNING) {
            if (up) {
                hero.forward();
            }
            if (down) {
                hero.back();
            }
            if (left) {
                hero.left();
            }
            if (right) {
                hero.right();
            }
            if (hit) {
                hero.hit();
            }
        }
    }

    public void notifyHeroKeypressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W:
                up = true;
                break;
            case KeyEvent.VK_S:
                down = true;
                break;
            case KeyEvent.VK_A:
                left = true;
                break;
            case KeyEvent.VK_D:
                right = true;
                break;
            case KeyEvent.VK_J:
                hit = true;
                break;
            case KeyEvent.VK_ENTER:
                start();
                break;
        }
    }

    private void start() {
        if (status == GameStatus.READY || status == GameStatus.STAGE_OVER) {
            status = GameStatus.RUNNING;
            step++;
            gdt = new GameObjectDispatcherThread();
            gdt.start();
        }
    }

    public void notifyHeroKeyreleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W:
                up = false;
                break;
            case KeyEvent.VK_S:
                down = false;
                break;
            case KeyEvent.VK_A:
                left = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
            case KeyEvent.VK_J:
                hit = false;
                break;
        }
    }

    private void showInfo(Graphics g) {
        Color old = g.getColor();
        Font oldFont = g.getFont();
        if (status == GameStatus.READY) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("黑体", Font.PLAIN, 50));
            g.drawString("请按下回车键盘开始游戏", 140, 300);
        }
        if (status == GameStatus.STAGE_OVER) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("黑体", Font.PLAIN, 50));
            g.drawString("第" + step + "关通过，请按下回车键开始游戏", 100, 300);
        }
        if (status == GameStatus.FAIL) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("黑体", Font.PLAIN, 50));
            g.drawString("闯关失败", 300, 300);
        }
        g.setColor(old);
        g.setFont(oldFont);
    }

    public void stageOver() {
        status = GameStatus.STAGE_OVER;
        gdt.interrupt();

        gameObjects.clear();
        hero.stageOver();
        gameObjects.add(hero);
    }

    public int getStep() {
        return step;
    }

    public void reborn() {
        if (maxLife == 1) {
            status = GameStatus.FAIL;
            return;
        }
        maxLife--;
        hero = new Hero();
    }

    public int getMaxLife() {
        return maxLife;
    }

}
