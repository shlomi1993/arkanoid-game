package gamemanagement;

import interfaces.Sprite;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * The type SpriteCollection.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.1
 * @since 2020 -04-28
 */
public class SpriteCollection {

    /**
     * Initialize a list to store sprite objects.
     */
    private List<Sprite> sprites = new ArrayList<Sprite>();

    /**
     * Adds a sprite object to the list.
     *
     * @param s the sprite object to be added to sprites list.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);

    }

    /**
     * Removes a sprite object from the list.
     *
     * @param s the sprite object to be removed from sprites list.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Call timePassed() on all sprites.
     * <p>Note: this method didn't work properly with a for-each loop.</p></>
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed();
        }
    }

    /**
     * Call drawOn(d) on all sprites.
     *
     * @param d the surface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }

    }
}