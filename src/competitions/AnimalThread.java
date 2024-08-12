package competitions;

import Graphics.ImagePanel;
import animals.Animal;
import javax.swing.SwingUtilities;
import java.util.concurrent.atomic.AtomicBoolean;

public class AnimalThread implements Runnable {
    private  Animal participant;
    private  double neededDistance;
    private  AtomicBoolean startFlag;
    private  AtomicBoolean finishFlag;
    private static long sleepDuration = 100;
    private  ImagePanel imagePanel; // Reference to the ImagePanel for updates

    public AnimalThread(Animal participant, double neededDistance, AtomicBoolean startFlag, AtomicBoolean finishFlag) {
        this.participant = participant;
        this.neededDistance = neededDistance;
        this.startFlag = startFlag;
        this.finishFlag = finishFlag;
        this.imagePanel = new ImagePanel(null);

    }

    @Override
    public void run() {
        // Wait for the start signal
        synchronized (startFlag) {
            while (!startFlag.get()) {
                try {
                    startFlag.wait(); // Wait for the race to start
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Set interrupt flag again
                    return;
                }
            }
        }

        double distanceCovered = participant.getTotalDistance();
        while (distanceCovered < neededDistance && !Thread.interrupted()) {
            synchronized (participant) {
                if (!participant.isOutOfEnergy()) {
                    distanceCovered += participant.move();
                    participant.checkBoundsAndChangeDirection(participant); // Check and change direction if needed
                    participant.decreaseEnergy(); // Decrease energy after moving


                }
            }

            System.out.println("Distance covered: " + distanceCovered + " / " + neededDistance);

            if (distanceCovered >= neededDistance) {
                synchronized (finishFlag) {
                    finishFlag.set(true);
                    finishFlag.notifyAll(); // Notify that this participant has finished
                }
                System.out.println(participant.getAnimalName() + " finished");
                int width = imagePanel.getWidth2()/14;
                participant.resetPosition(participant,width);
                participant.setTotalDistance();
                participant.setNotMoving();
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
