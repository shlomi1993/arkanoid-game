package utilities;

import gamepieces.Block;
import interfaces.BlockCreator;
import java.util.Map;

/**
 * The type BlocksFromSymbolsFactory.
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-06-27
 */
public class BlocksFromSymbolsFactory {

    /**
     * privates.
     */
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Instantiates a new BlocksFromSymbolsFactory.
     *
     * @param sw a value to be set for spacerWidths.
     * @param bc a value to be set for blockCreators.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> sw, Map<String, BlockCreator> bc) {
        this.spacerWidths = sw;
        this.blockCreators = bc;
    }

    /**
     * Returns true if 's' is a valid space symbol.
     *
     * @param s a given string.
     * @return true for valid, false O.W.
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * Returns true if 's' is a valid block symbol.
     *
     * @param s a given string.
     * @return true for valid, false O.W.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    /**
     * Return a block according to the definitions associate with symbol 's'.
     * The block will be located at position (xpos, ypos).
     *
     * @param s a given string - which represent a block.
     * @param x coordinate x.
     * @param y coordinate y.
     * @return return the required Block.
     */
    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
    }

    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     *
     * @param s a given string.
     * @return spacer width.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

}


