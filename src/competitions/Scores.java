package competitions;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Scores {
    private final Map<String, Date> scores;

    public Scores() {
        scores = Collections.synchronizedMap(new HashMap<>());
    }

    /**
     * Adds a score to the map with the current time.
     *
     * @param name The name to be added.
     */
    public void add(String name) {
        synchronized (scores) {
            scores.put(name, new Date());
        }
    }

    /**
     * Returns all the scores.
     *
     * @return The map of scores.
     */
    public Map<String, Date> getAll() {
        synchronized (scores) {
            return new HashMap<>(scores);
        }
    }
}
