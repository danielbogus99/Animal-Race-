package competitions;

import animals.Animal;

class Referee implements Runnable {
    private final String name;
    private final Scores scores;
    private final Boolean finishFlag;

    public Referee(String name, Scores scores, Boolean finishFlag) {
        this.name = name;
        this.scores = scores;
        this.finishFlag = finishFlag;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (finishFlag) {
                if (finishFlag) {
                    scores.add(name);
                    return;
                }
            }

            try {
                Thread.sleep(100); // Sleep for a short period before checking again
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
