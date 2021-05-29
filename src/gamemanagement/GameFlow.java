package gamemanagement;

import animations.AnimationRunner;
import animations.KeyPressStoppableAnimation;
import animations.LoseScreen;
import animations.WinScreen;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import interfaces.LevelInformation;
import utilities.Counter;
import utilities.ScoreIndicator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * The type GameFlow.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 2.0
 * @since 2020 -06-12
 */
public class GameFlow {

    /**
     * Privates.
     */
    private GUI gui;
    private AnimationRunner runner;
    private Sleeper sleeper;
    private Counter score;
    private ScoreIndicator scoreIndicator;
    private List<LevelInformation> levels;

    /**
     * Instantiates a new Game flow.
     *
     * @param gui a given GUI to show the game.
     * @param ar  the animation runner that manage the animation.
     * @param levels a list of levels to run one-by-one.
     */
    public GameFlow(GUI gui, AnimationRunner ar,  List<LevelInformation> levels) {
        this.gui = gui;
        this.runner = ar;
        this.levels = levels;
        this.sleeper = new Sleeper();
        this.score = new Counter();
        this.scoreIndicator = new ScoreIndicator(this.score);
    }

    /**
     * This method is in charge of running levels one-by-one.
     */
    public void runLevels() {

        KeyboardSensor ks = gui.getKeyboardSensor();
        boolean isWin = true;

        // Run each level in the given list of levels.
        for (LevelInformation levelInfo : this.levels) {

            // Set variables for shorter usage.
            AnimationRunner ar = this.runner;
            ScoreIndicator si = this.scoreIndicator;

            // Initialize a new level from the list.
            GameLevel level = new GameLevel(levelInfo, this.gui, ar, this.score, si);
            level.initialize();

            // Running loop.
            while (level.getBallCount() != 0 && level.getBlocksCount() != 0) {
                level.run();
            }

            // Loosing condition -- and show LoseScreen.
            if (level.getBallCount() == 0) {
                this.runner.run(new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY, new LoseScreen(score)));
                this.score.reset();
                isWin = false;
                break;
            }
        }

        // Winning condition -- and show WinScreen.
        if (isWin) {
            this.runner.run(new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY, new WinScreen(score)));
        }

    }

    /**
     * This method update the text file which contains the highest score so far.
     */
    public void updateHighestScoreFile() {

        // Set path.
        String hsPath = "highscores.txt";

        // Update the file if the score in it is lower that the current one.
        try {
            File file = new File(hsPath);
            String msg = "The highest score so far is: " + this.score.getValue();
            if (file.createNewFile()) {
                FileWriter writer = new FileWriter(hsPath);
                writer.write(msg);
                writer.close();
            } else {
                byte[] allBytes = Files.readAllBytes(Paths.get(hsPath));
                String content = new String(allBytes);
                String[] split = content.split(": ");
                int previousHighestScore = Integer.parseInt(split[1]);
                if (previousHighestScore <= this.score.getValue()) {
                    FileWriter writer = new FileWriter(hsPath);
                    writer.write(msg);
                    writer.close();
                }
            }
        } catch (IOException e) {
            System.out.println("Error: IOException caught in updateHighestScoreFile()");
        }
    }

}
