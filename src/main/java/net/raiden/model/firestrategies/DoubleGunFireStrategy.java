package net.raiden.model.firestrategies;

import net.raiden.model.Direction;
import net.raiden.model.FireStrategy;
import net.raiden.model.gameobjects.bullets.SilverBullet;

public class DoubleGunFireStrategy extends FireStrategy {
    public void fire(int x, int y) {
        new SilverBullet(x - 20, y + 25, Direction.U);
        new SilverBullet(x + 20, y + 25, Direction.U);
    }
}
