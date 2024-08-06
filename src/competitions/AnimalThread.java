package competitions;

import Graphics.ImagePanel;
import animals.*;

public class AnimalThread implements Runnable {
    private final Animal participant;
    private final double neededDistance;
    private  Boolean startFlag;
    private Boolean  finishFlag;
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
            while (! startFlag) {
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
                if (!participant.isOutOfEnergy()) {
                    distanceCovered += participant.move(); // Move the participant forward
                    checkBoundsAndChangeDirection(participant); // Check and change direction if needed
                    participant.decreaseEnergy(); // Decrease energy after moving
                }
            }

            if (distanceCovered >= neededDistance)
            {
                synchronized (finishFlag)
                {
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
    public static void setSleepDuration(long duration)
    {
        sleepDuration = duration;
    }

    // Method to check bounds and change direction
    private void checkBoundsAndChangeDirection(Animal animal) {
        int x = animal.getLocation().getX();
        int y = animal.getLocation().getY();
        int backgroundWidth = new ImagePanel(null).getWidth2();
        int backgroundHeight = new ImagePanel(null).getHeight2();

        if (animal instanceof AirAnimal) {
            if (x > backgroundWidth - 250) {
                animal.Stop(); // Corrected method name
                System.out.println("AirAnimal stopped at boundary.");
            }
        } else if (animal instanceof TerrestrialAnimals || animal instanceof ITerrestrailAnimal) {
            if (x > backgroundWidth - 260 && y > backgroundHeight - 155) {
                animal.setOrientation(Animal.Orientation.WEST);
            } else if (x > backgroundWidth - 260) {
                animal.setOrientation(Animal.Orientation.SOUTH);
            } else if (x < 15 && y > backgroundHeight - 155) {
                animal.setOrientation(Animal.Orientation.NORTH);
            } else if (x == 0 && y == 0) {
                animal.setOrientation(Animal.Orientation.EAST);
            }
        } else if (animal instanceof WaterAnimal) {
            if (x > backgroundWidth - 345) {
                animal.Stop(); // Corrected method name
                System.out.println("WaterAnimal stopped at boundary.");
            }
        }
    }
}
