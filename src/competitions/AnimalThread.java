package competitions;

import Graphics.ImagePanel;
import animals.Animal;
import mobility.Point;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents a thread responsible for managing the movement of an Animal participant in a race.
 */
public class AnimalThread implements Runnable {
    private Animal participant;
    private double neededDistance;
    private AtomicBoolean startFlag;
    private AtomicBoolean finishFlag;
    private static long sleepDuration = 100;
    private Point finalPosition; // Final position after the race
    private Animal previousAnimal;

    /**
     * Constructs an AnimalThread with the specified parameters.
     *
     * @param participant    The Animal participant in the race.
     * @param neededDistance The distance the animal needs to cover.
     * @param startFlag      The flag indicating the start of the race.
     * @param finishFlag     The flag indicating the finish of the race.
     * @param startPosition  The starting position for this animal.
     */
    public AnimalThread(Animal participant, double neededDistance, AtomicBoolean startFlag, AtomicBoolean finishFlag, Point startPosition,Animal previousAnimal) {
        this.participant = participant;
        this.neededDistance = neededDistance;
        this.startFlag = startFlag;
        this.finishFlag = finishFlag;
        this.finalPosition = startPosition;
        this.previousAnimal = previousAnimal;


    }

    /**
     * The main logic of the thread, which controls the animal's movement and checks if it has completed the race.
     */
    @Override
    public void run() {
        // Wait for the race to start
        synchronized (startFlag) {
            while (!startFlag.get()) {
                try {
                    startFlag.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        participant.setLocation(previousAnimal.getLocation());
        participant.setOrientation(previousAnimal.getOrientation());

        double distanceCovered = 0; // Use a local variable for distance covered
        while (distanceCovered < neededDistance && !Thread.interrupted()) {
            synchronized (participant) {
                if (!participant.isOutOfEnergy()) {
                    participant.setMoving();
                    distanceCovered += participant.move();
                    participant.checkBoundsAndChangeDirection(participant);
                    participant.decreaseEnergy();
                }
            }

            System.out.println("Distance covered: " + distanceCovered + " / " + neededDistance);

            if (distanceCovered >= neededDistance) {
                synchronized (finishFlag) {
                    finalPosition = participant.getLocation(); // Update the final position
                    finishFlag.set(true);
                    finishFlag.notifyAll(); // Notify that this participant has finished
                }

                System.out.println(participant.getAnimalName() + " finished");

                // Resetting the animal for potential next race or for resetting purposes

                participant.setTotalDistance();
                participant.setNotMoving();

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

    /**
     * Static method to set the sleep duration between animal movements.
     *
     * @param duration The sleep duration in milliseconds.
     */
    public static void setSleepDuration(long duration) {
        sleepDuration = duration;
    }

    /**
     * Method to get the final position of the animal after completing the race.
     *
     * @return The final position of the animal as a Point.
     */
    public Point getFinalPosition() {
        return finalPosition;
    }
}
