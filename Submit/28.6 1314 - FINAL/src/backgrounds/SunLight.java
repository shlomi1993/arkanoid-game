package backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;
import java.awt.Color;

/**
 * The type SunLight (background).
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */
public class SunLight implements Sprite {

    /**
     * Draw Rainy background as a sprite.
     *
     * @param d DrawSurface - a surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(Color.WHITE);
        d.fillRectangle(0, 20, 800, 600);

        // Draw sun rays.
        d.setColor(new Color(240, 230, 120));
        for (int i = 20; i < 780; i = i + 10) {
            d.drawLine(200, 150, i, 250);
        }

        // Draw sun.
        d.fillCircle(200, 150, 70);
        d.setColor(new Color(218, 165, 32));
        d.fillCircle(200, 150, 55);
        d.setColor(new Color(255, 215, 0));
        d.fillCircle(200, 150, 40);

    }

    /**
     * There is no use of this function from the interface in this assignment.
     */
    @Override
    public void timePassed() {

    }
}
