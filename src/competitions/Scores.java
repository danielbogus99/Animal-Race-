package competitions;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Scores {
    // A synchronized map to store scores with participant names and their finish times
    private final Map<String, Date> scores = Collections.synchronizedMap(new HashMap<>());

    // Adds a participant's result to the map with the current timestamp
    public void add(String name) {
        synchronized (scores) {
            scores.put(name, new Date()); // Add current date and time as finish time
        }
    }

    // Returns a copy of the scores map to avoid concurrent modification issues
    public Map<String, Date> getAll() {
        synchronized (scores) {
            return new HashMap<>(scores); // Return a copy of the map
        }
    }
}
