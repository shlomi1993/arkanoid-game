package interfaces;

import gamepieces.Ball;
import gamepieces.Block;

/**
 * The interface HitListener.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-05-24
 */
public interface HitListener {

    /**
     * This method is called whenever an block (beingHit) is hit by a ball (hitter).
     *
     * @param beingHit the block that was hit.
     * @param hitter   the ball that hit.
     */
    void hitEvent(Block beingHit, Ball hitter);

}
