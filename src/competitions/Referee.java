package competitions;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents a Referee in the tournament, responsible for monitoring when an animal has finished the race.
 */
public class Referee implements Runnable {
    private String groupName; // The name of the group being monitored
    private Scores scores; // Reference to the Scores object to record results
    private AtomicBoolean finishFlag; // Flag to check if the animal has finished

    /**
     * Constructs a Referee with the specified parameters.
     *
     * @param groupName  The name of the group that the referee is monitoring.
     * @param scores     The Scores object to record the results.
     * @param finishFlag The flag to check if the animal has finished the race.
     */
    public Referee(String groupName, Scores scores, AtomicBoolean finishFlag) {
        this.groupName = groupName;
        this.scores = scores;
        this.finishFlag = finishFlag;
    }

    /**
     * The main logic of the referee, which waits for the animal to finish the race and records the result.
     */
    @Override
    public void run() {
        // Wait for the finish flag to become true
        while (!finishFlag.get()) {
            try {
                Thread.sleep(100); // Polling mechanism, waits until the finish flag is true
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        // Record the result once the finish flag is true
        synchronized (scores) {
            scores.add(groupName);
            System.out.println("Animal from group " + groupName + " has finished the race.");
        }
    }
}
