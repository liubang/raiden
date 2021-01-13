package net.raiden.model.firestrategies;

import net.raiden.model.Direction;
import net.raiden.model.FireStrategy;
import net.raiden.model.gameobjects.bullets.LaserBullet;

public class ShotGunFireStrategy extends FireStrategy {
    public void fire(int x, int y) {
        new LaserBullet(x, y, Direction.U);
        new LaserBullet(x, y, Direction.LU);
        new LaserBullet(x, y, Direction.RU);
    }
}
