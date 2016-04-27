package net.raiden.model.colliders;

import net.raiden.model.Collider;
import net.raiden.model.ColliderChain;
import net.raiden.model.GameObject;
import net.raiden.model.gameobjects.BloodUpObject;
import net.raiden.model.gameobjects.Hero;

public class HeroBloodUpObjectCollider implements Collider
{

	public void collide(GameObject go1, GameObject go2, ColliderChain chain)
	{
		if (go1 instanceof Hero && go2 instanceof BloodUpObject && !go1.isDead() && !go2.isDead()) {
			if (!(go1.getRect().intersection(go2.getRect())).isEmpty()) {
				go2.die();
				go1.addLifeValue(go2.getLifeValue());
			}
		} else if (go2 instanceof Hero && go1 instanceof BloodUpObject && !go1.isDead() && !go2.isDead()) {
			collide(go2, go1, chain);
		} else {
			chain.doCollide(go1, go2);
		}
	}
}
