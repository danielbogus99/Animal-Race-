package Graphics;
import animals.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompetitionManager {
    private static CompetitionManager instance;
    private Map<String, List<Animal>> groupMap;

    private CompetitionManager() {
        groupMap = new HashMap<>();
    }

    public static CompetitionManager getInstance() {
        if (instance == null) {
            instance = new CompetitionManager();
        }
        return instance;
    }

    public Map<String, List<Animal>> getGroupMap() {
        return groupMap;
    }

    public void addGroup(String groupName, List<Animal> animals) {
        groupMap.put(groupName, animals);
    }

    public void clearGroups() {
        groupMap.clear();
    }
}
