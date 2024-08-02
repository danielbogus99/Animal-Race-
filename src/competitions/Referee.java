package competitions;

import animals.*;

class Referee implements Runnable {
    private final String name;
    private final Scores scores;


    public Referee(String name, Scores scores, Animal participant) {
        this.name = name;
        this.scores = scores;

    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted())
        {
            synchronized (this) {
                if (fin) {
                    scores.add(name);
                    return;
                }
            }

            try
            {
                Thread.sleep(100); // Sleep for a short period before checking again
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }
    }
}
