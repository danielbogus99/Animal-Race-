package competitions;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a Scores object that manages the results of a tournament.
 * It stores the names of participants and their corresponding finish times.
 */
public class Scores {
    // A synchronized map to store scores with participant names and their finish times
    private final Map<String, Date> scores = Collections.synchronizedMap(new HashMap<>());

    /**
     * Adds a participant's result to the map with the current timestamp.
     *
     * @param name The name of the participant.
     */
    public void add(String name) {
        scores.put(name, new Date()); // Add current date and time as finish time
    }

    /**
     * Returns a copy of the scores map to avoid concurrent modification issues.
     *
     * @return A copy of the map containing participant names and their finish times.
     */
    public Map<String, Date> getAll() {
        return new HashMap<>(scores); // Return a copy of the map
    }
}
