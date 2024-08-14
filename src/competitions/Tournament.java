package competitions;

import java.util.*;
import animals.*;

/**
 * Represents an abstract base class for different types of tournaments.
 * A Tournament consists of multiple animal groups and manages the overall race logic.
 */
public abstract class Tournament {

    protected TournamentThread tournamentThread; // Thread responsible for managing the tournament

    /**
     * Constructs a Tournament with the specified animal groups and additional information.
     *
     * @param animalGroups   The groups of animals participating in the tournament.
     * @param additionalInfo Additional information needed for the tournament setup.
     */
    public Tournament(Animal[][] animalGroups, Object additionalInfo) {

    }

    /**
     * Abstract method to be implemented by subclasses to set up the tournament.
     *
     * @param animalGroups   The groups of animals participating in the tournament.
     * @param additionalInfo Additional information needed for the setup.
     */
    protected abstract void setup(Animal[][] animalGroups, Object additionalInfo);

    /**
     * Returns the TournamentThread responsible for managing the tournament.
     *
     * @return The tournament thread.
     */
    public TournamentThread getTournamentThread() {
        return tournamentThread;
    }
}
