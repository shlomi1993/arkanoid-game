package utilities;

import gamepieces.Ball;
import gamepieces.Block;
import interfaces.HitListener;

/**
 * The type ScoreTrackingListener.
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-05-24
 */
public class ScoreTrackingListener implements HitListener {

    /**
     * Private.
     */
    private Counter score;

    /**
     * Instantiates a new Score tracking listener with a given counter.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.score = scoreCounter;
    }

    /**
     * The STL need to know when a ball hits a block to update the score.
     *
     * @param beingHit the block that was hit.
     * @param hitter   the ball that hit.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        this.score.increase(5);
    }
}
