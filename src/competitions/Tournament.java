package competitions;

import animals.Animal;

public abstract class Tournament {
    protected Animal[][] animalGroups;

    public Tournament(Animal[][] animalGroups, Object additionalInfo) {
        this.animalGroups = animalGroups;
        setup(animalGroups, additionalInfo);
    }

    protected abstract void setup(Animal[][] animalGroups, Object additionalInfo);

    // Method to return the animal teams
    public Animal[][] getAnimalTeams() {
        return animalGroups;
    }
}
