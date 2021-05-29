package levels;

import backgrounds.SunLight;
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
 * The class WideEasy (level)).
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020 -06-12
 */
public class WideEasy implements LevelInformation {

    /**
     * Privates.
     */
    private Sprite background;
    private List<Velocity> velocities;
    private List<Block> blocks;

    /**
     * Instantiates a new WideEasy.
     */
    public WideEasy() {
        this.background = new SunLight();
        this.velocities = new LinkedList<>();
        this.blocks = new LinkedList<>();
        setBlocksLayout();
    }

    /**
     * Return the number of balls in the game.
     *
     * @return the number of balls in the level.
     */
    @Override
    public int numberOfBalls() {
        return 10;
    }

    /**
     * Return the initial velocity of each ball.
     *
     * @return a list of velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {

        for (int i = 0; i < numberOfBalls() / 2; i++) {
            double speed = 6;
            double angle = -60 + i * 10;
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            this.velocities.add(v);
        }
        for (int i = numberOfBalls() / 2; i < numberOfBalls(); i++) {
            double speed = 6;
            double angle = -30 + i * 10;
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            this.velocities.add(v);
        }
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
        return 600;
    }

    /**
     * Return the level name that displayed at the top of the screen.
     *
     * @return level name.
     */
    @Override
    public String levelName() {
        return "Wide Easy";
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
        return 15;
    }

    /**
     * Sets blocks layout.
     */
    public void setBlocksLayout() {
        Color[] colors = new Color[] {Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW,
                Color.YELLOW, Color.GREEN, Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE,
                Color.PINK, Color.PINK, Color.CYAN, Color.CYAN};
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            Point p = new Point(25 + i * 50, 250);
            Rectangle rect = new Rectangle(p, 50, 20);
            Block block = new Block(rect, colors[i]);
            this.blocks.add(block);
        }
    }

}
