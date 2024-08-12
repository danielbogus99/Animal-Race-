package competitions;

import Graphics.RaceResultsPanel;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TournamentThread implements Runnable {
    private Scores scores; // Holds the final results of each group
    private AtomicBoolean startSignal; // Special flag to start all animals
    private int groups; // Number of competing groups
    private Map<String, Date> raceResults;// Store the results for UI updates
    private String raceName;

    // Update the format to include both date and time
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public TournamentThread(Scores scores, AtomicBoolean startSignal, int groups,String raceName) {
        this.scores = scores;
        this.startSignal = startSignal;
        this.groups = groups;
        this.raceName = raceName;
        this.raceResults = new ConcurrentHashMap<>();
    }

    @Override
    public void run() {
        // Start the race by setting the startSignal to true
        synchronized (startSignal) {
            startSignal.set(true);
            startSignal.notifyAll();
        }

        // Continuously monitor race results
        while (true) {
            Map<String, Date> allScores = scores.getAll();

            synchronized (raceResults) {
                raceResults.putAll(allScores);
            }

            // Display race results for each group
            for (String groupName : raceResults.keySet()) {
                Date finishTime = raceResults.get(groupName);
                if (finishTime != null) {
                    // Print the group name and its finish time in the formatted output
                    System.out.println(groupName + " finished at: " + dateFormat.format(finishTime));
                } else {
                    System.out.println(groupName + " is still racing.");
                }
            }

            // Break the loop if all groups have finished
            if (raceResults.size() == groups) {
                System.out.println("All groups have finished the race.");

                // Show results in a new panel
                SwingUtilities.invokeLater(() -> showResultsPanel(raceResults));

                break;
            }

            // Pause for a second before the next update
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private void showResultsPanel(Map<String, Date> raceResults) {
        JFrame resultsFrame = new JFrame("Race Results");
        resultsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultsFrame.setContentPane(new RaceResultsPanel(raceResults));
        resultsFrame.pack();
        resultsFrame.setLocationRelativeTo(null); // Center the frame on screen
        resultsFrame.setVisible(true);
    }
}
