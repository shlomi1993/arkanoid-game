package utilities;

import interfaces.Collidable;
import shapes.Point;

/**
 * The type Collision info.
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-04-28
 */
public class CollisionInfo {

    /**
     * Privates.
     * <p>Collision information should include the collidable object and the collision point.</p>
     */
    private final Collidable collidable;
    private final Point collisionPoint;

    /**
     * Instantiates a new Collision info.
     *
     * @param collidable     a value to be set for the collidable.
     * @param collisionPoint a value to be set for the collision point.
     */
    public CollisionInfo(Collidable collidable, Point collisionPoint) {
        this.collidable = collidable;
        this.collisionPoint = collisionPoint;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object.
     */
    public Collidable collisionObject() {
        return collidable;
    }

}