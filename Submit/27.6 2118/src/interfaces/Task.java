package interfaces;

/**
 * The interface Task.
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020 -06-27
 *
 * @param <T> generics.
 */
public interface Task<T> {

    /**
     * This method will define what how to do the wanted job.
     *
     * @return the type of task.
     */
    T run();
}
