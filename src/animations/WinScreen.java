package animations;

import biuoop.DrawSurface;
import interfaces.Animation;
import utilities.Counter;
import java.awt.Color;

/**
 * The type WinScreen.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */
public  class WinScreen implements Animation {

    /**
     * Private.
     */
    private Counter score;

    /**
     * Instantiates a new WinScreen.
     *
     * @param score score counter to show final score on the WinScreen.
     */
    public WinScreen(Counter score) {
        this.score = score;
    }

    /**
     * This method draw frames of the WinScreen animation.
     *
     * @param d DrawSurface - a surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {

        d.setColor(new Color(0, 255, 127));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score.getValue(), 32);

        // Make a smiley.
        d.setColor(Color.ORANGE);
        d.fillCircle(550, 500, 40);
        d.setColor(Color.WHITE);
        d.fillCircle(535, 485, 6);
        d.fillCircle(565, 485, 6);
        d.setColor(Color.BLACK);
        d.drawCircle(535, 485, 6);
        d.drawCircle(565, 485, 6);
        d.fillCircle(535, 485, 2);
        d.fillCircle(565, 485, 2);
        d.setColor(new Color(205, 92, 92));
        d.fillCircle(550, 513, 20);
        d.setColor(Color.BLACK);
        d.drawCircle(550, 513, 20);
        d.setColor(Color.ORANGE);
        d.fillRectangle(530, 492, 45, 25);
        d.setColor(Color.BLACK);
        d.drawLine(530, 516, 570, 516);

    }

    /**
     * shouldStop change to true from outside this class.
     * <p>Implemented in KeyPressStoppableAnimation class.</p>
     *
     * @return  false.
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
