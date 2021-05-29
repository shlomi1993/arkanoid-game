package utilities;

import gamepieces.Ball;
import gamepieces.Block;
import gamemanagement.GameLevel;
import interfaces.HitListener;

/**
 * The type BallRemover.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-05-24
 */
public class BallRemover implements HitListener {

    /**
     * Privates.
     * <p>A BallRemover needs to know which game to remove a ball from and it's ball count.</p>
     */
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     * <p>A Ball Remover needs to know which game to remove a ball from and update the game counter accordingly.</p>
     *
     * @param gameLevel    the game which this BallRemover should remove a ball.
     * @param removedBalls the counter to update.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    /**
     * When a ball hits the "death-region", this method removes a it from the game and update it's counter.
     *
     * @param beingHit the block that was hit.
     * @param hitter   the ball that hit.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(1);
    }

}
