package gamemanagement;

import interfaces.Collidable;
import shapes.Line;
import shapes.Point;
import utilities.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * The type GameEnvironment.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020 -04-28
 */
public class GameEnvironment {

    /**
     * Initialize a list to store collidable objects that are in the game environment.
     */
    private List<Collidable> collidables = new ArrayList<Collidable>();

    /**
     * Add a given collidable object to the environment.
     *
     * @param c the collidable to be added to the collidables list.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Returns the closest collision point if exist.
     * <p>This method checks if a moving object is about to hit an object from collidables list.
     * if no  -- returns null.
     * if yes -- returns the closest collision that is going to occur.</p>
     *
     * @param trajectory the Shapes.Line which the object moves from its start to its end.
     * @return the closest collision information or null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        // Case no collidable objects in the game environment.
        if (collidables.isEmpty()) {
            return null;
        }

        // The algorithm to find the closest collision point if exist.
        CollisionInfo cInfo = null;
        double curDist;
        double minDist = Double.MAX_VALUE;
        for (Collidable collidable : collidables) {
            Point hit = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (hit != null) {
                curDist = hit.distance(trajectory.start());
                if (curDist < minDist) {
                    minDist = curDist;
                    cInfo = new CollisionInfo(collidable, hit);
                }
            }
        }
        return cInfo;
    }
}