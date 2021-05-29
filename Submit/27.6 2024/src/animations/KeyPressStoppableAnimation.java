package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * The type KeyPressStoppableAnimation.
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */
public class KeyPressStoppableAnimation implements Animation {

    /**
     * Privates.
     */
    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Instantiates a new KeyPressStoppableAnimation.
     *
     * @param animation an animation to draw in a loop.
     * @param key       a string that can be used to choose the keyboard key to stop the animation.
     * @param sensor    a keyboard sensor.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * This method draw frames of the KeyPressStoppableAnimation.
     *
     * @param d DrawSurface - a surface to draw on.

     */
    @Override
    public void doOneFrame(DrawSurface d) {

        // Make a frame.
        this.animation.doOneFrame(d);

        // Fix the "already pressed" bug.
        boolean pressed = this.keyboard.isPressed(this.key);
        if (pressed && !isAlreadyPressed) {
            this.stop = true;
        } else if (!pressed) {
            this.isAlreadyPressed = false;
        }

    }

    /**
     * This method return the if this animation should stop or continue.
     *
     * @return true -- should stop. false -- otherwise.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * This method allows to change the stop boolean manually.
     */
    @Override
    public void setStop(boolean bool) {
        this.stop = bool;
    }

}
