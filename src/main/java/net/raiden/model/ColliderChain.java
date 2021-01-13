package net.raiden.model;

import net.raiden.model.colliders.EnemyBulletCollider;
import net.raiden.model.colliders.HeroBloodUpObjectCollider;
import net.raiden.model.colliders.HeroBulletCollider;
import net.raiden.model.colliders.HeroChangeFireStrategyCollider;

public class ColliderChain {
    private Node head = new Node();
    private Node current = head;
    private static final ColliderChain chain = new ColliderChain();

    class Node {
        Node next;
        Collider value;
    }

    private ColliderChain() {
        Node n = new Node();
        n.value = new HeroChangeFireStrategyCollider();
        head.next = n;

        n = new Node();
        n.value = new HeroBloodUpObjectCollider();
        head.next.next = n;

        n = new Node();
        n.value = new HeroBulletCollider();
        head.next.next.next = n;

        n = new Node();
        n.value = new EnemyBulletCollider();
        head.next.next.next.next = n;
    }

    public static ColliderChain getInstance() {
        chain.current = chain.head;
        return chain;
    }

    public void doCollide(GameObject go1, GameObject go2) {
        if (current.next != null) {
            current = current.next;
            current.value.collide(go1, go2, this);
        }
    }

}
