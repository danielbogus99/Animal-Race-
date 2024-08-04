package competitions;
import animals.Animal;

public class AnimalThread implements Runnable {
    private Animal participant;
    private double neededDistance;
    private Boolean startFlag;
    private Boolean finishFlag;
    private volatile boolean isStopped;
    private static long sleepDuration = 100;

    public AnimalThread(Animal participant, double neededDistance, Boolean startFlag, Boolean finishFlag) {
        this.participant = participant;
        this.neededDistance = neededDistance;
        this.startFlag = startFlag;
        this.finishFlag = finishFlag;
        this.isStopped = false;
    }

    @Override
    public void run() {
        synchronized (startFlag)
        {
            while (!startFlag && !isStopped)
            {
                try {
                    startFlag.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        double distanceCovered = participant.getTotalDistance();
        while (distanceCovered < neededDistance && !isStopped) {
            synchronized (participant)
            {
                distanceCovered += participant.move();
                if (distanceCovered >= neededDistance)
                {
                    synchronized (finishFlag)
                    {
                        finishFlag = true;
                        finishFlag.notifyAll();
                    }
                    break;
                }
            }

            try
            {
                Thread.sleep(sleepDuration);
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                return;
            }

            if (Thread.interrupted())
            {
                return;
            }
        }
    }

    public void stop() {
        isStopped = true;
    }
    public Boolean getFinishFlag() {
        return finishFlag;
    }
    public static void setSleepDuration(long duration) {
        sleepDuration = duration;
    }
}
