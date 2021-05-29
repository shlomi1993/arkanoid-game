package com.company;

import gamepieces.Ball;
import gamepieces.Block;
import interfaces.HitListener;

public class PrintingHitListener implements HitListener {
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}