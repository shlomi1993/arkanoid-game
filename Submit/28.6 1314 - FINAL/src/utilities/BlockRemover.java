package utilities;

import gamepieces.Ball;
import gamepieces.Block;
import gamemanagement.GameLevel;
import interfaces.HitListener;

/**
 * The type BlockRemover.
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-05-24
 */
public class BlockRemover implements HitListener {

    /**
     * Privates.
     * <p>A BlockRemover needs to know which game to remove a block from and it's block count.</p>
     */
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     * <p>A BlockRemover is in charge of removing blocks from the game, as well
     * as keeping count of the number of blocks that remain.</p>
     *
     * @param gameLevel         the game which this BallRemover should remove a ball.
     * @param removedBlocks the counter to update.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Once hit, this method removes a block from the game and update it's counter.
     *
     * @param beingHit the block that was hit.
     * @param hitter   the ball that hit.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.gameLevel);
        this.remainingBlocks.decrease(1);
    }

}
