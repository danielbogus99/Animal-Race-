package competitions;

import animals.Animal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

public class TournamentThread implements Runnable {
    private Scores scores; // Holds the final results of each group
    private AtomicBoolean startSignal; // Special flag to start all animals
    private int groups; // Number of competing groups
    private Map<String, Date> raceResults; // Store the results for UI updates

    public TournamentThread(Scores scores, AtomicBoolean startSignal, int groups) {
        this.scores = scores;
        this.startSignal = startSignal;
        this.groups = groups;
        this.raceResults = new ConcurrentHashMap<>(); // Initialize raceResults
    }

    @Override
    public void run()
    {

        synchronized (startSignal)
        {
            startSignal.set(true);
            startSignal.notifyAll();
        }
        while (true)
        {
            Map<String, Date> allScores = scores.getAll();

            synchronized (raceResults)
            {
                raceResults.putAll(allScores);
            }

            for (int i = 0; i < groups; i++)
            {
                String groupName = "Group " + (i + 1);
                Date finishTime = raceResults.get(groupName);
                if (finishTime != null)
                {
                    System.out.println(groupName + " finished at: " + finishTime);
                } else
                {
                    System.out.println(groupName + " is still racing.");
                }
            }

            if (raceResults.size() == groups)
            {
                System.out.println("All groups have finished the race.");
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
