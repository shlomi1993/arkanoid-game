package gamepieces;

import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import shapes.Point;
import shapes.Rectangle;
import biuoop.DrawSurface;
import gamemanagement.GameLevel;
import utilities.Velocity;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Block.
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 3.0
 * @since 2020-06-27
 */
public class Block implements Collidable, Sprite, HitNotifier {

    /**
     * Privates.
     * <p>A Block consists of a Rectangle, a Color a list of hit-listeners.</p>
     */
    private Rectangle rect;
    private Color color = null;
    private Image img = null;
    private List<HitListener> hitListeners;

    /**
     * Instantiates a new Block - a constructor for colored block.
     *
     * @param rect  a Shapes.Rectangle to be set for rect.
     * @param color a value to be set for color.
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }
    /**
     * Instantiates a new Block - a constructor for "imaged" block.
     *
     * @param rect  a Shapes.Rectangle to be set for rect.
     * @param img a value to be set for Image.
     */
    public Block(Rectangle rect, Image img) {
        this.rect = rect;
        this.img = img;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Draw the Block's Rectangle on a given surface.
     *
     * @param surface the surface to draw the rectangle on.
     */
    @Override
    public void drawOn(DrawSurface surface) {

        // x and y are the rectangles upper-left point's values, w is the width and h is the height.
        int x = (int) this.rect.getUpperLeft().getX();
        int y = (int) this.rect.getUpperLeft().getY();
        int w = (int) this.rect.getWidth();
        int h = (int) this.rect.getHeight();

        if (this.color != null) {

            // Fill color
            surface.setColor(this.color);
            surface.fillRectangle(x, y, w, h);

        } else {

            // Draw image
            surface.drawImage(x, y, img);

        }

        // Draw outline
        surface.setColor((Color.BLACK));
        surface.drawRectangle(x, y, w, h);

    }

    /**
     * According to assignments instructions we should do nothing with this method at this point.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Add this block to a given game's sprite and collidable collections.
     *
     * @param g the game to which the block should be added.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * remove this block from a given game's sprite and collidable collections.
     *
     * @param gameLevel the game to which the block should be added.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Returns the Shapes.Rectangle of this GamePieces.Block.
     *
     * @return the Shapes.Rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Returns the Color of this GamePieces.Block.
     *
     * @return the Color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Notifies collided object with a given velocity and collisionPoint, and return a new velocity.
     *
     * @param collisionPoint    the collision point of the two objects.
     * @param currentVelocity   the velocity of the hitting object.
     * @param hitter            the ball that hit this block.
     * @return a new velocity based on the force the object inflicted on the block.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        double dx = currentVelocity.getX();
        double dy = currentVelocity.getY();

        boolean hitUpper = collisionPoint.onSegment(this.rect.getUpper());
        boolean hitBottom = collisionPoint.onSegment(this.rect.getBottom());
        boolean hitRight = collisionPoint.onSegment(this.rect.getRight());
        boolean hitLeft = collisionPoint.onSegment(this.rect.getLeft());

        if (hitUpper || hitBottom) {
            dy = -dy;
        }

        if (hitRight || hitLeft) {
            dx = -dx;
        }

        this.notifyHit(hitter);
        return new Velocity(dx, dy);

    }

    /**
     * Notifies all listeners that hit event occurred.
     *
     * @param hitter the hitting ball.
     */
    private void notifyHit(Ball hitter) {

        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);

        // Notify all listeners that hit event occurred.
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }

    }

    /**
     * Add hl as a listener to this block's list.
     *
     * @param hl the hit-listener to be added.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl as a listener from this block's list.
     *
     * @param hl the hit-listener to be removed.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * A getter for block's width.
     *
     * @return block's width.
     */
    public int getBlockWidth() {
        return (int) this.rect.getWidth();
    }

    /**
     * A getter for block's height.
     *
     * @return block's height.
     */
    public int getBlockHeight() {
        return (int) this.rect.getHeight();
    }

}
