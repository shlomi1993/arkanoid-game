package backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;
import java.awt.Color;

/**
 * The type Rainy (background).
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */
public class Rainy implements Sprite {

    /**
     * Draw Rainy background as a sprite.
     *
     * @param d DrawSurface - a surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(new Color(30, 144, 255));
        d.fillRectangle(0, 20, 800, 600);

        // Cloud 1
        d.setColor(new Color(211, 211, 211));
        for (int i = 165; i < 270; i += 10) {
            d.drawLine(i, 380, i - 15, 600);
        }
        d.fillCircle(175, 380, 20);
        d.fillCircle(195, 400, 20);
        d.setColor(new Color(192, 192, 192));
        d.fillCircle(210, 370, 30);
        d.setColor(new Color(170, 170, 170));
        d.fillCircle(230, 400, 25);
        d.fillCircle(250, 380, 30);

        // Cloud 2
        d.setColor(new Color(211, 211, 211));
        for (int i = 565; i < 670; i += 10) {
            d.drawLine(i, 480, i - 10, 600);
        }
        d.fillCircle(575, 480, 20);
        d.fillCircle(595, 500, 30);
        d.setColor(new Color(192, 192, 192));
        d.fillCircle(610, 470, 30);
        d.setColor(new Color(170, 170, 170));
        d.fillCircle(630, 500, 20);
        d.fillCircle(650, 480, 25);

    }

    /**
     * There is no use of this function from the interface in this assignment.
     */
    @Override
    public void timePassed() {

    }

}
