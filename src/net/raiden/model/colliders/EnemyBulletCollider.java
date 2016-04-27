package net.raiden.model.colliders;

import net.raiden.model.Collider;
import net.raiden.model.ColliderChain;
import net.raiden.model.GameObject;
import net.raiden.model.gameobjects.Bullet;
import net.raiden.model.gameobjects.Enemy;

public class EnemyBulletCollider implements Collider
{

	public void collide(GameObject go1, GameObject go2, ColliderChain chain)
	{
		if (go1 instanceof Enemy && go2 instanceof Bullet && !go1.isDead() && !go2.isDead() && go2.isGood()) {
			if (!(go1.getRect().intersection(go2.getRect())).isEmpty()) {
				go2.die();
				go1.addLifeValue(-go2.getLifeValue());
			}
		} else if (go2 instanceof Enemy && go1 instanceof Bullet && !go1.isDead() && !go2.isDead() && go1.isGood()) {
			collide(go2, go1, chain);
		} else {
			chain.doCollide(go1, go2);
		}
	}

}
