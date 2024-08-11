package competitions;

import Graphics.ImagePanel;
import animals.Animal;
import javax.swing.SwingUtilities;
import java.util.concurrent.atomic.AtomicBoolean;

public class AnimalThread implements Runnable {
    private final Animal participant;
    private final double neededDistance;
    private final AtomicBoolean startFlag;
    private final AtomicBoolean finishFlag;
    private static long sleepDuration = 100;
    private final ImagePanel imagePanel; // Reference to the ImagePanel for updates

    public AnimalThread(Animal participant, double neededDistance, AtomicBoolean startFlag, AtomicBoolean finishFlag, ImagePanel imagePanel) {
        this.participant = participant;
        this.neededDistance = neededDistance;
        this.startFlag = startFlag;
        this.finishFlag = finishFlag;
        this.imagePanel = imagePanel; // Initialize the imagePanel reference
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

                    // Update the UI on the Event Dispatch Thread
                    SwingUtilities.invokeLater(() -> imagePanel.repaint());
                }
            }

            System.out.println("Distance covered: " + distanceCovered + " / " + neededDistance);

            if (distanceCovered >= neededDistance) {
                synchronized (finishFlag) {
                    finishFlag.set(true);
                    finishFlag.notifyAll(); // Notify that this participant has finished
                }
                System.out.println(participant.getAnimalName() + " finished");
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
