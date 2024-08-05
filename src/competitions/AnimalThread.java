package competitions;

import animals.Animal;

public class AnimalThread implements Runnable {
    private final Animal participant;
    private final double neededDistance;
    private final Object startFlagLock;
    private volatile boolean startFlag;
    private final Object finishFlagLock;
    private volatile boolean finishFlag;
    private volatile boolean isStopped;
    private static long sleepDuration = 100;

    public AnimalThread(Animal participant, double neededDistance, Object startFlagLock, Object finishFlagLock) {
        this.participant = participant;
        this.neededDistance = neededDistance;
        this.startFlagLock = startFlagLock;
        this.finishFlagLock = finishFlagLock;
        this.isStopped = false;
        this.finishFlag = false;
    }

    @Override
    public void run() {
        // Wait for the start signal
        synchronized (startFlagLock) {
            while (!startFlag && !isStopped) {
                try {
                    startFlagLock.wait(); // Wait for the race to start
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Set interrupt flag again
                    return;
                }
            }
        }

        double distanceCovered = participant.getTotalDistance();
        while (distanceCovered < neededDistance && !isStopped) {
            synchronized (participant) {
                distanceCovered += participant.move();
            }

            if (distanceCovered >= neededDistance) {
                synchronized (finishFlagLock) {
                    finishFlag = true;
                    finishFlagLock.notifyAll(); // Notify that this participant has finished
                }
                break;
            }

            try {
                Thread.sleep(sleepDuration);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public void stop() {
        isStopped = true;
    }

    public boolean isFinished() {
        synchronized (finishFlagLock) {
            return finishFlag;
        }
    }

    public static void setSleepDuration(long duration) {
        sleepDuration = duration;
    }

    // Call this method from your RegularTournament when the race starts
    public void startRace() {
        synchronized (startFlagLock) {
            startFlag = true;
            startFlagLock.notifyAll();
        }
    }
}
