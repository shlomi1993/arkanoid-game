package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * The type AnimationRunner.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */
public class AnimationRunner {

    /**
     * Privates.
     */
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Instantiates a new AnimationRunner.
     *
     * @param gui the GUI from Ass6Game main class
     * @param fps the FPS that have been set in Ass6Game (60).
     */
    public AnimationRunner(GUI gui, int fps) {
        this.gui = gui;
        this.framesPerSecond = fps;
        this.sleeper = new Sleeper();
    }

    /**
     * This method gets an Animation class and run it.
     *
     * @param animation the animation class.
     */
    public void run(Animation animation) {

        int millisecondsPerFrame = 1000 / framesPerSecond;

        // The animation loop.
        while (!animation.shouldStop()) {

            // Timing.
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();

            // Draw and show.
            animation.doOneFrame(d);
            gui.show(d);

            // Timing.
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}