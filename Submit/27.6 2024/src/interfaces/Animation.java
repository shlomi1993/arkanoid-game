package interfaces;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */
public interface Animation {

    /**
     * This method draw frames of a certain animation.
     *
     * @param d DrawSurface - a surface to draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * This method return the if this animation should stop or continue.
     *
     * @return true -- should stop. false -- otherwise.
     */
    boolean shouldStop();

    /**
     * This method allows to change the stop boolean manually.
     *
     * @param bool a true or false to be set.
     */
    void setStop(boolean bool);

}
