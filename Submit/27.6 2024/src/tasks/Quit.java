package tasks;

import biuoop.GUI;
import interfaces.Task;

/**
 * The task Quit.
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-06-27
 */
public class Quit implements Task<Void> {

    /**
     * Private.
     */
    private GUI gui;

    /**
     * Instantiates a new Quit, which is a task to abort the program neatly.
     *
     * @param gui the gui that this task should close.
     */
    public Quit(GUI gui) {
        this.gui = gui;
    }

    /**
     * Close the GUI and abort the program.
     */
    @Override
    public Void run() {
        this.gui.close();
        System.exit(1);
        return null;
    }
}
