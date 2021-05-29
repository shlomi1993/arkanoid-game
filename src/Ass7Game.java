import animations.AnimationRunner;
import animations.HighScoresAnimation;
import animations.KeyPressStoppableAnimation;
import animations.MenuAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gamemanagement.GameFlow;
import interfaces.Animation;
import interfaces.LevelInformation;
import interfaces.Menu;
import interfaces.Task;
import levels.DirectHit;
import levels.FinalFour;
import levels.GoodNight;
import levels.WideEasy;
import levels.LevelSpecificationReader;
import tasks.Quit;
import tasks.ShowHiScoresTask;
import tasks.StartGame;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-06-27
 */
public class Ass7Game {

    /**
     * The entry point of the game.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        // Set GUI, Animation Runner and other utilities.
        int fps = 60;
        GUI gui = new GUI("Arkanoid Game", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui, fps);
        KeyboardSensor ks = gui.getKeyboardSensor();
        List<LevelInformation> levels = new LinkedList<>();
        GameFlow gf = new GameFlow(gui, runner, levels);
        Animation hs = new HighScoresAnimation();
        Animation score = new KeyPressStoppableAnimation(gui.getKeyboardSensor(), KeyboardSensor.SPACE_KEY, hs);

        // Convert valid args to levels and add to queue (list).
        for (String arg : args) {
            if (arg.equals("1")) {
                levels.add(new DirectHit());
            } else if (arg.equals("2")) {
                levels.add(new WideEasy());
            } else if (arg.equals("3")) {
                levels.add(new GoodNight());
            } else if (arg.equals("4")) {
                levels.add(new FinalFour());
            } else if (arg.equals("${args}")) {
                ;
            } else {
                try {
                    LevelSpecificationReader lsr = new LevelSpecificationReader();
                    Reader reader = new BufferedReader(new FileReader(arg));
                    List<LevelInformation> levelsFromTextFile = lsr.fromReader(reader);
                    levels.addAll(levelsFromTextFile);
                } catch (IOException ignored) {
                    ;
                }
            }
        }

        // Case no valid args.
        if (levels.isEmpty()) {
            try {
                LevelSpecificationReader lsr = new LevelSpecificationReader();
                String path = "resources/definitions/level_definitions/medium_level_definitions.txt";
                Reader reader = new BufferedReader(new FileReader(path));
                List<LevelInformation> levelsFromTextFile = lsr.fromReader(reader);
                levels.addAll(levelsFromTextFile);
            } catch (IOException ignored) {
                ;
            }
        }

        // Create a menu.
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(ks);
        menu.addSelection("h", "Show highest score", new ShowHiScoresTask(runner, score));
        menu.addSelection("q", "Quit", new Quit(gui));
        menu.addSelection("s", "Play", new StartGame(gf));

        // Running loop.
        while (true) {
            menu.setStop(false);
            score.setStop(false);
            runner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
        }

    }

}
