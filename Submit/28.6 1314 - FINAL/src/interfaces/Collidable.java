package interfaces;

import gamepieces.Ball;
import shapes.Point;
import shapes.Rectangle;
import utilities.Velocity;

/**
 * The interface Collidable.
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-04-28
 */
public interface Collidable {

    /**
     * Returns the shape (rectangle) of the collidable object.
     *
     * @return the collision rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies collided object with a given velocity and collisionPoint, and return a new velocity.
     *
     * @param collisionPoint    the collision point of the two objects.
     * @param currentVelocity   the velocity of the hitting object.
     * @param hitter            the ball that hit this collidable.
     * @return a new velocity based on the force the object inflicted on the block.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}