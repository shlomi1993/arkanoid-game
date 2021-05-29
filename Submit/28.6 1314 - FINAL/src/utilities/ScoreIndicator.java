package utilities;

import biuoop.DrawSurface;
import interfaces.Sprite;
import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {

    /**
     * Private.
     */
    private Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param currentScore the current score
     */
    public ScoreIndicator(Counter currentScore) {
        this.score = currentScore;
    }

    /**
     * Draw a rectangle with the score written inside on a given surface.
     *
     * @param d the surface to draw the rectangle on.
     */
    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor((Color.BLACK));
        d.drawRectangle(0, 0, 800, 20);
        d.drawText(375, 15, "Score: " + this.score.getValue(), 16);

    }

    /**
     * This method is not used because the score is updated by a listener.
     */
    @Override
    public void timePassed() {

    }
}
