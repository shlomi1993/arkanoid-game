package animations;

import biuoop.DrawSurface;
import interfaces.Animation;
import utilities.Counter;
import java.awt.Color;

/**
 * The type LoseScreen.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */
public class LoseScreen implements Animation {

    /**
     * Private.
     */
    private Counter score;

    /**
     * Instantiates a new LoseScreen.
     *
     * @param score score counter to show final score on the LoseScreen.
     */
    public LoseScreen(Counter score) {
        this.score = score;
    }

    /**
     * This method draw frames of the LoseScreen animation.
     *
     * @param d DrawSurface - a surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(220, 20, 60));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue(), 32);
    }

    /**
     * Return the if this animation should stop or continue.
     *
     * @return true -- should stop. false -- otherwise.
     */
    public boolean shouldStop() {
        return false;
    }

    /**
     * Allows to change the stop boolean manually.
     * <p>Implemented in KeyPressStoppableAnimation class.</p>
     *
     * @param bool true or false to be set for stop member.
     */
    @Override
    public void setStop(boolean bool) {

    }

}
