package interfaces;

import gamepieces.Block;

/**
 * The interface BlockCreator.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-06-27
 */
public interface BlockCreator {

    /**
     * Create a block at the specified location.
     *
     * @param xpos position x for the block creation.
     * @param ypos position y for the block creation.
     * @return a new Block.
     */
    Block create(int xpos, int ypos);

}
