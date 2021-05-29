package levels;

import backgrounds.Target;
import gamepieces.Block;
import interfaces.Sprite;
import interfaces.LevelInformation;
import shapes.Point;
import shapes.Rectangle;
import utilities.Velocity;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The type DirectHit (level).
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */
public class DirectHit implements LevelInformation {

    /**
     * Privates.
     */
    private Sprite background;
    private List<Velocity> velocities;
    private List<Block> blocks;

    /**
     * Instantiates a new DirectHit.
     */
    public DirectHit() {
        this.background = new Target();
        this.velocities = new LinkedList<>();
        this.blocks = new LinkedList<>();
        this.setBlocksLayout();
    }

    /**
     * Return the number of balls in the game.
     *
     * @return the number of balls in the level.
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * Return the initial velocity of each ball.
     *
     * @return a list of velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        this.velocities.add(new Velocity(0, -7));
        return velocities;
    }

    /**
     * Return paddle speed.
     *
     * @return speed.
     */
    @Override
    public int paddleSpeed() {
        return 10;
    }

    /**
     * Return paddle width.
     *
     * @return width.
     */
    @Override
    public int paddleWidth() {
        return 70;
    }

    /**
     * Return the level name that displayed at the top of the screen.
     *
     * @return level name.
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return background as a sprite.
     */
    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * Return blocks layout.
     *
     * @return list of blocks.
     */
    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * Return the number of blocks to remove before the level is "cleared".
     *
     * @return number of blocks in the level.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    /**
     * Sets blocks layout.
     */
    public void setBlocksLayout() {
        Point p = new Point(385, 185);
        Rectangle rect = new Rectangle(p, 30, 30);
        Block block = new Block(rect, Color.RED);
        this.blocks.add(block);
    }

}
