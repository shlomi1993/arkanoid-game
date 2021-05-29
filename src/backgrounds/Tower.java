package backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;
import java.awt.Color;

/**
 * The type Target (background).
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */
public class Tower implements Sprite {

    /**
     * Draw Target background as a sprite.
     *
     * @param d DrawSurface - a surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(new Color(0, 0, 120));
        d.fillRectangle(0, 20, 800, 600);

        // Draw a tower.
        d.setColor(Color.BLACK);
        d.fillRectangle(70, 400, 150, 250);

        // Draw windows.
        d.setColor(Color.WHITE);
        for (int y = 415; y < 600; y += 55) {
            for (int x = 82; x < 200; x += 28) {
                d.fillRectangle(x, y, 15, 40);
            }
        }

        // Draw antenna.
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(125, 340, 40, 60);
        d.setColor(new Color(80, 80, 80));
        d.fillRectangle(140, 150, 10, 190);

        // Draw signal.
        d.setColor(new Color(255, 127, 80));
        d.fillCircle(145, 150, 10);
        d.setColor(new Color(255, 99, 71));
        d.fillCircle(145, 150, 7);
        d.setColor(Color.WHITE);
        d.fillCircle(145, 150, 3);


    }

    /**
     * There is no use of this function from the interface in this assignment.
     */
    @Override
    public void timePassed() {

    }
}
