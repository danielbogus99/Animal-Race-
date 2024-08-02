package competitions;

import java.util.*;
import animals.*;

class TournamentThread implements Runnable {
    private  Scores scores;
    private Boolean startSignal;
    private final int groups;


    private final Runnable updateUI;
    private final Map<String, Long> displayTimes;

    public TournamentThread(Scores scores, Boolean startSignal, int groups, Runnable updateUI) {
        this.scores = scores;
        this.startSignal = startSignal;
        this.groups = groups;
        this.updateUI = updateUI;
        this.displayTimes = new HashMap<>();
    }

    @Override
    public void run() {
        synchronized (startSignal)
        {
            startSignal = true;
            startSignal.notifyAll();
        }

        while (!Thread.currentThread().isInterrupted())
        {
            Map<String, Date> allScores = scores.getAll();

            for (Map.Entry<String, Date> entry : allScores.entrySet())
            {
                displayTimes.putIfAbsent(entry.getKey(), entry.getValue().getTime());
            }

            updateUI.run();

            if (displayTimes.size() == groups)
            {
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public Map<String, Long> getDisplayTimes() {
        return new HashMap<>(displayTimes);
    }
}
