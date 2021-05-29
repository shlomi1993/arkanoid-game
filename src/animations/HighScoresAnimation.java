package animations;

import biuoop.DrawSurface;
import interfaces.Animation;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The type HighScoresAnimation.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-06-27
 */
public class HighScoresAnimation implements Animation {

    /**
     * This method draw frames of the HighScoresAnimation.
     *
     * @param d DrawSurface - a surface to draw on.

     */
    @Override
    public void doOneFrame(DrawSurface d) {
        String hsPath = "highscores.txt";
        try {

            // Read image and draw background.
            Image img = ImageIO.read(new File("resources\\background_images\\ScoreScreenBackground.jpg"));
            d.drawImage(0, 0, img);

            // Write a message.
            String content;
            try {
                byte[] allBytes = Files.readAllBytes(Paths.get(hsPath));
                content = new String(allBytes);
            } catch (IOException ignored) {
                content = "No game have been played yet!";
            }
            d.setColor(Color.WHITE);
            d.drawText(140, 260, content, 40);

        } catch (IOException e) {
            System.out.println("Error: IOException caught in HighScoresAnimation class");
        }

    }

    /**
     * Return the if this animation should stop or continue.
     *
     * @return true -- should stop. false -- otherwise.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }

    /**
     * Allows to change the stop boolean manually.
     * <p>Implemented in KeyPressStoppableAnimation class.</p>
     *
     * @param bool true or false to be set for stop member.
     */
    @Override
    public void setStop(boolean bool) {

    }

}
