package levels;

import biuoop.DrawSurface;
import gamepieces.Block;
import interfaces.LevelInformation;
import interfaces.Sprite;
import utilities.BlocksDefinitionReader;
import utilities.BlocksFromSymbolsFactory;
import utilities.ColorsParser;
import utilities.Velocity;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The type LevelSpecificationReader.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020 -06-27
 */
public class LevelSpecificationReader {

    /**
     * Private.
     */
    private List<LevelInformation> levels;


    /**
     * Instantiates a new Level specification reader.
     */
    public LevelSpecificationReader() {
        this.levels = new LinkedList<>();
    }

    /**
     * This method gets a reader and returns a list of levels.
     *
     * @param reader the given reader.
     * @return a list of defined levels.
     * @throws IOException the io exception.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) throws IOException {

        // Extract the content of the reader and split to separated levels..
        char[] charRead = new char[8 * 1024];
        StringBuilder stringBuilder = new StringBuilder();
        int numRead;
        while ((numRead = reader.read(charRead, 0, charRead.length)) != -1) {
            stringBuilder.append(charRead, 0, numRead);
        }
        List<String> separatedLevels = splitFileToLevels(stringBuilder);

        // Parsing...
        for (String sl : separatedLevels) {

            // Parse property title and value as key:value.
            Map<String, String> map = new Hashtable<>();
            String[] lines = sl.split("\n");
            for (String line : lines) {
                if (line.contains(":")) {
                    String[] keyVal = line.split(":");
                    map.put(keyVal[0], keyVal[1].trim());
                }
            }

            // Level name.
            String levelName = map.get("level_name");

            // Ball velocities and number of balls.
            String arrStr = map.get("ball_velocities");
            String[] velCoordinates = arrStr.split(" ");
            List<Velocity> initialVelocities = new LinkedList<>();
            for (String v : velCoordinates) {
                String[] coordinates = v.split(",");
                int a = Integer.parseInt(coordinates[0]);
                int s = Integer.parseInt(coordinates[1]);
                initialVelocities.add(Velocity.fromAngleAndSpeed(a, s));
            }

            // Background
            String type = map.get("background");
            String input = type.substring(type.indexOf("(") + 1, type.indexOf(")")).trim();
            Sprite background = null;
            ColorsParser cp = new ColorsParser();
            if (type.startsWith("color")) {
                Color color = cp.colorFromString(input);
                background = new Sprite() {
                    @Override
                    public void drawOn(DrawSurface d) {
                        d.setColor(color);
                        d.fillRectangle(0, 0, 800, 600);
                    }

                    @Override
                    public void timePassed() {

                    }
                };
            } else {
                background = new Sprite() {
                    @Override
                    public void drawOn(DrawSurface d) {
                        try {
                            Image img = ImageIO.read(new File(input));
                            d.drawImage(0, 0, img);
                        } catch (IOException e) {
                            System.out.println("Error: IOException caught in LevelSpecificationReader class");
                        }
                    }

                    @Override
                    public void timePassed() {

                    }

                };
            }

            // Paddle speed.
            int paddleSpeed = Integer.parseInt(map.get("paddle_speed"));

            // Paddle width.
            int paddleWidth = Integer.parseInt(map.get("paddle_width"));

            // Number of blocks.
            int numberOfBlocksToRemove = Integer.parseInt(map.get("num_blocks"));

            // Blocks
            int blockX = Integer.parseInt(map.get("blocks_start_x"));
            int blockY = Integer.parseInt(map.get("blocks_start_y"));
            int rowHeight = Integer.parseInt(map.get("row_height"));
            List<Block> blocks = new LinkedList<>();
            String resourcePath = map.get("block_definitions");
            Reader tempReader = new FileReader(resourcePath);
            BlocksFromSymbolsFactory factory = BlocksDefinitionReader.fromReader(tempReader);
            String start = "START_BLOCKS";
            String end = "END_BLOCKS";
            String blocksStart = sl.substring(sl.indexOf(start) + start.length() + 1);
            String[] blocksSection = blocksStart.substring(0, blocksStart.indexOf(end) - 1).split("\n");
            for (String line : blocksSection) {
                String[] chars = line.split("");
                for (int i = 0; i < chars.length; i++) {
                    if (factory.isBlockSymbol(chars[i])) {
                        Block b = factory.getBlock(chars[i], blockX, blockY);
                        blockX += b.getBlockWidth();
                        blocks.add(b);
                    } else if (factory.isSpaceSymbol(chars[i])) {
                        blockX += factory.getSpaceWidth(chars[i]);
                    }
                }
                blockX = Integer.parseInt(map.get("blocks_start_x"));
                blockY += rowHeight;
            }
            LevelInformation levelInfo = new GenericLevel(initialVelocities, paddleSpeed,
            paddleWidth, levelName, background, numberOfBlocksToRemove, blocks);
            this.levels.add(levelInfo);

        }

        return this.levels;

    }

    /**
     * This methods helps to split the content of the given StringBuilder to separated levels.
     *
     * @param stringBuilder gets a StringBuilder
     * @return a list of Strings -- each of them is a separated level.
     */
    public List<String> splitFileToLevels(StringBuilder stringBuilder) {
        List<String> separatedLevels = new LinkedList<>();
        String content = stringBuilder.toString();
        while (content.contains("START_LEVEL")) {
            String extract = content.substring(content.indexOf("START_LEVEL") + 12, content.indexOf("END_LEVEL") - 1);
            separatedLevels.add(extract);
            stringBuilder.replace(0, content.indexOf("END_LEVEL") + 8, "");
            content = stringBuilder.toString();
        }
        return separatedLevels;
    }

}
