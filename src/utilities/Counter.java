package utilities;

/**
 * The type Counter.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 2.0
 * @since 2020-05-24
 */
public class Counter {

    /**
     * Private - the counting number.
     */
    private int count;

    /**
     * Instantiates a new Counter with zero.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Add number to current count.
     *
     * @param number the number
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Subtract number from current count.
     *
     * @param number the number
     */
    public void decrease(int number) {
        this.count -= number;

    }

    /**
     * Get current count.
     *
     * @return the value
     */
    public int getValue() {
        return this.count;
    }

    /**
     * Allows to reset the Counter, which means set it's count to zero.
     */
    public void reset() {
        this.count = 0;
    }
}
