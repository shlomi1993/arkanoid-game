package tasks;

import gamemanagement.GameFlow;
import interfaces.Task;

/**
 * The task StartGame.
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-06-27
 */
public class StartGame implements Task<Void> {

    /**
     * Private.
     */
    private GameFlow gf;

    /**
     * Instantiates a new StartGame, which is a task to start a... game.
     *
     * @param gameFlow the GameFlow needed to run the game from it.
     */
    public StartGame(GameFlow gameFlow) {
        this.gf = gameFlow;
    }

    /**
     * Run levels (from GameFlow) and then update the highest score file.
     *
     * @return Void for generic uses.
     */
    @Override
    public Void run() {
        gf.runLevels();
        gf.updateHighestScoreFile();
        return null;
    }
}
