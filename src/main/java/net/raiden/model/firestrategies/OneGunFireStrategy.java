package net.raiden.model.firestrategies;

import net.raiden.model.Direction;
import net.raiden.model.FireStrategy;
import net.raiden.model.gameobjects.bullets.LittleBullet;

public class OneGunFireStrategy extends FireStrategy {
    public void fire(int x, int y) {
        new LittleBullet(x, y, Direction.U);
    }
}
