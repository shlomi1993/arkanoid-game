package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Menu;
import java.awt.Color;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The class MenuAnimation.
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-06-27
 *
 * @param <Task> generics.
 */
public class MenuAnimation<Task> implements Menu {

    /**
     * Privates.
     */
    private KeyboardSensor ks;
    private List<String> keys;
    private List<String> messages;
    private Map<String, Object> tasks;
    private Object status;
    private boolean stop;

    /**
     * Instantiates a new Menu animation.
     *
     * @param keyboardSensor the keyboard sensor
     */
    public MenuAnimation(KeyboardSensor keyboardSensor) {
        this.ks = keyboardSensor;
        this.keys = new LinkedList<>();
        this.messages = new LinkedList<>();
        this.tasks = new Hashtable();
        this.stop = false;
    }

    /**
     * add selection to the main game menu.
     *
     * @param key the key that the player should press.
     * @param message a message to be print.
     * @param returnVal an object to return (for task to do).
     */
    @Override
    public void addSelection(String key, String message, Object returnVal) {
        this.keys.add(key);
        this.messages.add(message);
        this.tasks.put(key, returnVal);
    }

    /**
     * Return the relevant object.
     *
     * @return object.
     */
    @Override
    public Object getStatus() {
        return this.status;

    }

    /**
     * This method draw frames of the MenuAnimation.
     *
     * @param d DrawSurface - a surface to draw on.

     */
    @Override
    public void doOneFrame(DrawSurface d) {

        // Draw image background.
        try {
            Image img = ImageIO.read(new File("resources\\background_images\\MainMenuBackground.jpg"));
            d.drawImage(0, 0, img);
        } catch (IOException e) {
            System.out.println("Error: IOException catched in MenuAnimation class");
        }

        // Set menu rectangle parameters.
        int x = 260;
        int y = 200;
        int w = 280;
        int h = 200;

        // Draw menu.
        d.setColor(new Color(147, 112, 219));
        d.fillRectangle(x, y, w, h);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, w, h);
        d.drawText(x + 10, y + 40, "Arkanoid Game", 32);
        d.drawText(x + 10, y + 90, "Press:", 22);
        d.drawText(x + 10, y + 120, "- 's' to start a new game", 22);
        d.drawText(x + 10, y + 150, "- 'h' to see the highest score", 22);
        d.drawText(x + 10, y + 180, "- 'q' to quit", 22);

        // Check for key press.
        for (String k : keys) {
            if (ks.isPressed(k)) {
                this.status = tasks.get(k);
                this.stop = true;
                break;
            }
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
        this.stop = false;
    }

}
