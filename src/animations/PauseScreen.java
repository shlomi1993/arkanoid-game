package animations;

import biuoop.DrawSurface;
import interfaces.Animation;

/**
 * The type PauseScreen.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */
public class PauseScreen implements Animation {

    /**
     * This method draw frames of the PauseScreen animation.
     *
     * @param d DrawSurface - a surface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * shouldStop change to true from outside this class.
     * <p>Implemented in KeyPressStoppableAnimation class.</p>
     *
     * @return  false.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }

    /**
     * Allows to change the stop boolean manually.
     * <p>Implemented in KeyPressStoppableAnimation class.</p>
     *
     * @param bool true or false to be set for stop member.
     */
    @Override
    public void setStop(boolean bool) {

    }
}
