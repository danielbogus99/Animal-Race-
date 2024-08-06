package competitions;
import java.util.*;
import animals.*;

public abstract class Tournament {


    protected TournamentThread tournamentThread;

    public Tournament(Animal[][] animalGroups, Object additionalInfo) {
        setup(animalGroups, additionalInfo);
    }


    protected abstract void setup(Animal[][] animalGroups, Object additionalInfo);


    public TournamentThread getTournamentThread()
    {
        return tournamentThread;
    }
}
