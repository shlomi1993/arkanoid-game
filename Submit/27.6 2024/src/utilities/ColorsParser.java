package utilities;

import java.awt.Color;
import java.lang.reflect.Field;

/**
 * The type ColorsParser.
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020 -06-27
 */
public class ColorsParser {
    /**
     * Parse color definition and return the specified color.
     *
     * @param s a string of color name.
     * @return a java.awt.Color if possible. null otherwise.
     */
    public java.awt.Color colorFromString(String s) {

        if (s.startsWith("RGB")) {

            // Case 1: color representation is in R.G.B numbers.
            String[] rgb = s.substring((s.indexOf("(")) + 1).split(",");
            int r = Integer.parseInt(rgb[0]);
            int g = Integer.parseInt(rgb[1]);
            int b = Integer.parseInt(rgb[2]);
            return new Color(r, g, b);
        } else {

            // Case 2: color representation is a name that might be in Java's library.
            try {
                Field field = Class.forName("java.awt.Color").getField(s);
                return (Color) field.get(null);
            } catch (Exception e) {
                System.out.println("Error in ColorParser class: couldn't parse the given color.");
                return null;
            }
        }
    }
}
