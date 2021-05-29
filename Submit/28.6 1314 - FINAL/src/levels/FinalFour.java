package levels;

import backgrounds.Rainy;
import gamepieces.Block;
import interfaces.LevelInformation;
import interfaces.Sprite;
import shapes.Point;
import shapes.Rectangle;
import utilities.Velocity;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The class FinalFour (level)).
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020 -06-12
 */
public class FinalFour implements LevelInformation {

    /**
     * Privates.
     */
    private Sprite background;
    private List<Velocity> velocities;
    private List<Block> blocks;

    /**
     * Instantiates a new FinalFour.
     */
    public FinalFour() {
        this.background = new Rainy();
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
        return 3;
    }

    /**
     * Return the initial velocity of each ball.
     *
     * @return a list of velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        for (int i = 0; i < numberOfBalls(); i++) {
            double speed = 6;
            double angle1 = -35 + i * 35;
            Velocity v = Velocity.fromAngleAndSpeed(angle1, speed);
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
        return 80;
    }

    /**
     * Return the level name that displayed at the top of the screen.
     *
     * @return level name.
     */
    @Override
    public String levelName() {
        return "Final Four";
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
        return 105;
    }

    /**
     * Sets blocks layout.
     */
    public void setBlocksLayout() {

        Color[] colors = new Color[] {Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN,
                Color.WHITE, Color.PINK, Color.CYAN};
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                Point p = new Point(25 + j * 50, 80 + i * 20);
                Rectangle rect = new Rectangle(p, 50, 20);
                Block block = new Block(rect, colors[i]);
                this.blocks.add(block);
            }
        }

    }

}
