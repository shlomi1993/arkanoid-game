package interfaces;

import biuoop.DrawSurface;

/**
 * The interface Sprite.
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020 -04-28
 */
public interface Sprite {

    /**
     * Draw the sprite's shape on a given surface.
     *
     * @param d the surface to draw the sprite on.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed and it should do it's job.
     */
    void timePassed();

}