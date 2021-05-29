package tasks;

import animations.AnimationRunner;
import interfaces.Animation;
import interfaces.Task;

/**
 * The task ShowHiScoresTask.
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-06-27
 */
public class ShowHiScoresTask implements Task<Void> {

    /**
     * Privates.
     */
    private final AnimationRunner runner;
    private final Animation highScoresAnimation;

    /**
     * Instantiates a new ShowHiScoresTask, which is a task to show the player the highest score so far.
     *
     * @param hsa a Show Highest Score Animation.
     * @param runner the Animation Runner.
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation hsa) {
        this.runner = runner;
        this.highScoresAnimation = hsa;
    }

    /**
     * Run the highScoresAnimation.
     */
    @Override
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }
}
