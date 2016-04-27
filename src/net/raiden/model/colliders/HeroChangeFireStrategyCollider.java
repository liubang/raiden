package net.raiden.model.colliders;

import net.raiden.model.Collider;
import net.raiden.model.ColliderChain;
import net.raiden.model.GameObject;
import net.raiden.model.gameobjects.ChangeFireStrategyObject;
import net.raiden.model.gameobjects.Hero;

public class HeroChangeFireStrategyCollider implements Collider
{

	public void collide(GameObject go1, GameObject go2, ColliderChain chain)
	{
		if (go1 instanceof Hero && go2 instanceof ChangeFireStrategyObject && !go1.isDead() && !go2.isDead()) {
			if (!(go1.getRect().intersection(go2.getRect())).isEmpty()) {
				go2.die();
				((Hero) go1).changeFireStrategy();
			}
		} else if (go2 instanceof Hero && go1 instanceof ChangeFireStrategyObject && !go1.isDead() && !go2.isDead()) {
			collide(go2, go1, chain);
		} else {
			chain.doCollide(go1, go2);
		}
	}
}
