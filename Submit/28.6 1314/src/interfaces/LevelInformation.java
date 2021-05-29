package interfaces;

import gamepieces.Block;
import utilities.Velocity;
import java.util.List;

/**
 * The interface LevelInformation.
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020 -06-12
 */
public interface LevelInformation {

    /**
     * Return the number of balls in the game.
     *
     * @return the number of balls in the level.
     */
    int numberOfBalls();

    /**
     * Return the initial velocity of each ball.
     *
     * @return a list of velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Return paddle speed.
     *
     * @return speed.
     */
    int paddleSpeed();

    /**
     * Return paddle width.
     *
     * @return width.
     */
    int paddleWidth();

    /**
     * Return the level name that displayed at the top of the screen.
     *
     * @return level name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return background as a sprite.
     */
    Sprite getBackground();

    /**
     * Return blocks layout.
     *
     * @return list of blocks.
     */
    List<Block> blocks();

    /**
     * Return the number of blocks to remove before the level is "cleared".
     *
     * @return number of blocks in the level.
     */
    int numberOfBlocksToRemove();

}
