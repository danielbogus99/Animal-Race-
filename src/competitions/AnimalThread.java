package competitions;

import animals.Animal;

public class AnimalThread implements Runnable {
    private final Animal participant;
    private  double neededDistance;
    private final Boolean startFlag;
    private Boolean finishFlag;
    private static long sleepDuration = 100;

    public AnimalThread(Animal participant, double neededDistance, Boolean startFlag, Boolean finishFlag) {
        this.participant = participant;
        this.neededDistance = neededDistance;
        this.startFlag = startFlag;
        this.finishFlag = finishFlag;
    }

    @Override
    public void run() {
        // Wait for the start signal
        synchronized (startFlag) {
            while (!startFlag) {
                try {
                    startFlag.wait(); // Wait for the race to start
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Set interrupt flag again
                    return;
                }
            }
        }

        double distanceCovered = participant.getTotalDistance();
        while (distanceCovered < neededDistance && !Thread.interrupted())
        {
            synchronized (participant) {
                distanceCovered += participant.move(); // Move the participant forward
            }

            if (distanceCovered >= neededDistance) {
                synchronized (finishFlag) {
                    finishFlag = true;
                    finishFlag.notifyAll(); // Notify that this participant has finished
                }
                break;
            }

            try {
                Thread.sleep(sleepDuration); // Sleep for a specified duration
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    // Static method to set sleep duration
    public static void setSleepDuration(long duration) {
        sleepDuration = duration;
    }
}
