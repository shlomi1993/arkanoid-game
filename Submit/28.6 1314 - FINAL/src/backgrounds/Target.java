package backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;
import java.awt.Color;

/**
 * The type Target (background).
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */
public class Target implements Sprite {

    /**
     * Draw Target background as a sprite.
     *
     * @param d DrawSurface - a surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 20, 800, 600);
        d.setColor(Color.BLUE);
        d.drawCircle(400, 200, 40);
        d.drawCircle(400, 200, 80);
        d.drawCircle(400, 200, 120);
        d.drawLine(400, 40, 400, 170);
        d.drawLine(400, 230, 400, 360);
        d.drawLine(240, 200, 370, 200);
        d.drawLine(430, 200, 560, 200);
    }

    /**
     * There is no use of this function from the interface in this assignment.
     */
    @Override
    public void timePassed() {

    }
}
