package gamemanagement;

import animations.AnimationRunner;
import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import biuoop.KeyboardSensor;
import gamepieces.Ball;
import gamepieces.Block;
import gamepieces.Paddle;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import shapes.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import utilities.BallRemover;
import utilities.BlockRemover;
import utilities.Counter;
import utilities.ScoreIndicator;
import utilities.ScoreTrackingListener;
import java.awt.Color;

/**
 * The type Game.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 3.0
 * @since 2020-05-24
 */
public class GameLevel implements Animation {

    /**
     * Privates.
     */
    private GUI gui;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blocksCount;
    private Counter ballsCount;
    private Counter score;
    private ScoreIndicator scoreIndicator;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInfo;

    /**
     * Instantiates a new Game and it's collections.
     *
     * @param ar    the animation runner.
     * @param gui   the GUI where the game run.
     * @param li    the level information.
     * @param score a score counter.
     * @param si    score indicator (from GameFlow).
     */
    public GameLevel(LevelInformation li, GUI gui, AnimationRunner ar, Counter score, ScoreIndicator si) {
        this.gui = gui;
        this.keyboard = gui.getKeyboardSensor();
        this.levelInfo = li;
        this.runner = ar;
        this.score = score;
        this.scoreIndicator = si;
    }

    /**
     * Add a collidable object to this game's environment..
     *
     * @param c the collidable to be added to environment list.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add a sprite object to this game's sprites collection.
     *
     * @param s the sprite to be added to sprites collection.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * remove a collidable object to this game's environment..
     *
     * @param c the collidable to be removed to environment list.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove a sprite object to this game's sprites collection.
     *
     * @param s the sprite to be removed to sprites collection.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game and it's components.
     */
    public void initialize() {

        // Initialize collections
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();

        // Initialize score tracking listener
        ScoreTrackingListener stl = new ScoreTrackingListener(this.score);

        // Background
        this.sprites.addSprite(this.levelInfo.getBackground());

        // Borders
        Rectangle topBorder = new Rectangle(0, 20, 800, 20);
        Rectangle leftBorder = new Rectangle(0, 20, 20, 600);
        Rectangle rightBorder = new Rectangle(780, 20, 20, 600);
        Block topBlock = new Block(topBorder, Color.GRAY);
        Block leftBlock = new Block(leftBorder, Color.GRAY);
        Block rightBlock = new Block(rightBorder, Color.GRAY);
        Block[] borders = new Block[]{topBlock, leftBlock, rightBlock};
        for (Block border : borders) {
            border.addToGame(this);
        }

        // Balls
        this.ballsCount = new Counter();
        int n = this.levelInfo.numberOfBalls();;
        int d = 0;
        for (int i = 0; i < n; i++) {
            if (n % 2 == 0) {
                d = 5;
            }
            Ball ball = new Ball(400 + d - 10 * (n / 2) + i * 10, 550, 5, Color.WHITE);
            ball.setVelocity(this.levelInfo.initialBallVelocities().get(i));
            ball.setGame(this.environment);
            ball.addToGame(this);
            this.ballsCount.increase(1);
        }

        // "Death Region"
        Rectangle drRect = new Rectangle(0, 610, 800, 20);
        Block drBlock = new Block(drRect, Color.GRAY);
        BallRemover ballsRemover = new BallRemover(this, ballsCount);
        drBlock.addToGame(this);
        drBlock.addHitListener(ballsRemover);

        // Blocks
        this.blocksCount = new Counter();
        BlockRemover blocksRemover = new BlockRemover(this, blocksCount);
        for (Block block : levelInfo.blocks()) {
            block.addHitListener(blocksRemover);
            block.addHitListener(stl);
            block.addToGame(this);
            this.blocksCount.increase(1);
        }

        // Paddle
        Paddle paddle = new Paddle(gui, levelInfo);
        paddle.addToGame(this);

    }

    /**
     * This method draw frames of this level's animation.
     *
     * @param d DrawSurface - a surface to draw on.

     */
    @Override
    public void doOneFrame(DrawSurface d) {

        // Stopping condition
        if (this.blocksCount.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }
        if (this.ballsCount.getValue() == 0) {
            this.running = false;
        }

        // Pause screen
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, "space", new PauseScreen()));
        }

        // Instant win - why not?
        if (this.keyboard.isPressed("0") && this.keyboard.isPressed("9")) {
            this.blocksCount.reset();
        }

        // Draw and notify
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        // Draw score indicator and level name
        this.scoreIndicator.drawOn(d);
        d.drawText(25, 15, "Level: " + this.levelInfo.levelName(), 16);

    }

    /**
     * Return the if this animation should stop or continue.
     *
     * @return true -- should stop. false -- otherwise.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Calling this method starts the animation loop and run the game.
     */
    public void run() {

        // Countdown animation screen.
        this.runner.run(new CountdownAnimation(2, 3, this.sprites, this.levelInfo.levelName()));

        // Call the runner to run the level.
        this.running = true;
        this.runner.run(this);

    }

    /**
     * A getter for balls count.
     *
     * @return the number of remaining balls.
     */
    public int getBallCount() {
        return this.ballsCount.getValue();
    }

    /**
     * A getter for blocks count.
     *
     * @return the number of remaining blocks.
     */
    public int getBlocksCount() {
        return this.blocksCount.getValue();
    }

    /**
     * Allows to change the stop boolean manually (not required here).
     *
     * @param bool true or false to be set for stop member.
     */
    @Override
    public void setStop(boolean bool) {

    }


}