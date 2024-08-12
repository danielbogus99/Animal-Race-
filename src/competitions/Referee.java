package competitions;

import java.util.concurrent.atomic.AtomicBoolean;

public class Referee implements Runnable {
    private String groupName;
    private Scores scores;
    private AtomicBoolean finishFlag; // Flag to check if the animal has finished

    public Referee(String groupName, Scores scores, AtomicBoolean finishFlag) {
        this.groupName = groupName;
        this.scores = scores;
        this.finishFlag = finishFlag;
    }

    @Override
    public void run()
    {
        // Wait for the finish flag to become true
        while (!finishFlag.get()) {
            try {
                Thread.sleep(100); // Polling mechanism, waits until the finish flag is true
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }


        synchronized (scores)
        {
            scores.add(groupName);
            System.out.println("Animal from group " + groupName + " has finished the race.");
        }
    }
}
