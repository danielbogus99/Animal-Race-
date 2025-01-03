package competitions;

import Graphics.ImagePanel;
import Graphics.RaceResultsPanel;
import animals.Animal;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Represents a thread responsible for managing and monitoring the entire tournament.
 * It starts the race, tracks the results, and displays them once all participants have finished.
 */
public class TournamentThread implements Runnable {
    private Scores scores; // Holds the final results of each group
    private AtomicBoolean startSignal; // Special flag to start all animals
    private int groups; // Number of competing groups
    private Map<String, Date> raceResults; // Stores the results for UI updates
    private String compositeKey; // Key used to track occupied paths in the tournament
    private Map<String, Integer> occupiedPaths; // Map to track occupied paths
    private String raceName; // Name of the race
    private Animal[][]animals;
    private ImagePanel imagePanel; // Reference to the ImagePanel for updates

    // Update the format to include both date and time
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Constructs a TournamentThread with the specified parameters.
     *
     * @param scores        The Scores object to store the final results of each group.
     * @param startSignal   The flag to start all animals in the tournament.
     * @param groups        The number of competing groups in the tournament.
     * @param raceName      The name of the race.
     * @param occupiedPaths The map to track occupied paths.
     * @param compositeKey  The key used to track the specific race in the occupied paths.
     */
    public TournamentThread(Scores scores, AtomicBoolean startSignal, int groups, String raceName, Map<String, Integer> occupiedPaths, String compositeKey,Animal[][]animals) {
        this.scores = scores;
        this.startSignal = startSignal;
        this.groups = groups;
        this.raceName = raceName;
        this.occupiedPaths = occupiedPaths;
        this.compositeKey = compositeKey;
        this.raceResults = new ConcurrentHashMap<>();
        this.animals = animals;
        this.imagePanel = new ImagePanel(null);
    }

    /**
     * The main logic of the tournament thread. It starts the race, monitors the results,
     * and displays the results once all participants have finished.
     */
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
                if (finishTime != null)
                {
                    // Print the group name and its finish time in the formatted output
                    System.out.println(groupName + " finished at: " + dateFormat.format(finishTime));
                } else {
                    System.out.println(groupName + " is still racing.");
                }
            }

            // Break the loop if all groups have finished
            if (raceResults.size() == groups)
            {
                int width = imagePanel.getWidth2() / 14;
                for(int i = 0;animals.length > i; i++) {
                    for (Animal animal : animals[i]) {
                        animal.resetPosition(animal,width);
                        animal.setOrientation(Animal.Orientation.EAST);
                    }
                }
                System.out.println("All groups have finished the race.");

                SwingUtilities.invokeLater(() -> showResultsPanel(raceResults));
                occupiedPaths.remove(compositeKey);
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

    /**
     * Displays the race results in a new panel.
     *
     * @param raceResults The map containing the results of the race.
     */
    private void showResultsPanel(Map<String, Date> raceResults) {
        JFrame resultsFrame = new JFrame("Race Results");
        resultsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultsFrame.setContentPane(new RaceResultsPanel(raceName, raceResults)); // Pass the raceName here
        resultsFrame.pack();
        resultsFrame.setLocationRelativeTo(null); // Center the frame on screen
        resultsFrame.setVisible(true);
    }
}
