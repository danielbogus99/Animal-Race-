package competitions;

public class Referee implements Runnable {
    private String groupName;
    private Scores scores;
    private Boolean finishFlag; // Flag to check if the animal has finished

    public Referee(String groupName, Scores scores, Boolean finishFlag) {
        this.groupName = groupName;
        this.scores = scores;
        this.finishFlag = finishFlag;
    }

    @Override
    public void run() {
        // Wait for the finish flag to become true
        while (!finishFlag) {
            try {
                Thread.sleep(100); // Polling mechanism, waits until the finish flag is true
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        // Once the finish flag is true, add the group's name to scores
        synchronized (scores) {
            scores.add(groupName);
            System.out.println("Animal from group " + groupName + " has finished the race.");
        }
    }
}
