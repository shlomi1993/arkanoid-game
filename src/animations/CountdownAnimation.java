package animations;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import interfaces.Animation;
import gamemanagement.SpriteCollection;
import java.awt.Color;

/**
 * The type CountdownAnimation.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020 -06-12
 */
public class CountdownAnimation implements Animation {

    /**
     * Privates.
     */
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private Sleeper sleeper;
    private String levelName;

    /**
     * Instantiates a new CountdownAnimation.
     *
     * @param numOfSeconds seconds for countdown.
     * @param countFrom    the number to count from.
     * @param gameScreen   a sprite collection with the layout of the game.
     * @param levelName    a string name of the level.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, String levelName) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.levelName = levelName;
        this.sleeper = new Sleeper();
    }

    /**
     * This method draw frames of the Countdown Animation.
     *
     * @param d DrawSurface - a surface to draw on.

     */
    @Override
    public void doOneFrame(DrawSurface d) {

        // Stopping condition.
        if (countFrom == 0) {
            this.stop = true;
        }

        // Draw game screen.
        this.gameScreen.drawAllOn(d);

        // Draw upper bar.
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor((Color.BLACK));
        d.drawRectangle(0, 0, 800, 20);

        // Write 3...2...1...
        d.drawText(350, 15, "Get ready: " + countFrom, 18);
        d.drawText(25, 15, "Level: " + this.levelName, 16);

        // Make use of a sleeper to dictate the pace.
        if (countFrom <= numOfSeconds) {
            this.sleeper.sleepFor(1000);
        }

        // Subtract 1 from the count.
        countFrom--;

    }

    /**
     * Return the if this animation should stop or continue.
     *
     * @return true -- should stop. false -- otherwise.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Allows to change the stop boolean manually.
     *
     * @param bool true or false to be set for stop member.
     */
    @Override
    public void setStop(boolean bool) {
        this.stop = bool;
    }

}
