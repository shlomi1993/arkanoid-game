package interfaces;

/**
 * The interface Menu.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020 -06-27
 *
 * @param <T> generics.
 */
public interface Menu<T> extends Animation {

    /**
     * add selection to the main game menu.
     *
     * @param key the key that the player should press.
     * @param message a message to be print.
     * @param returnVal an object to return (for task to do).
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Return the relevant object.
     *
     * @return object.
     */
    T getStatus();
}
