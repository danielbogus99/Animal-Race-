package Olympics;

/**
 * The Medal class represents a medal won in a tournament.
 * It includes the tournament name, the year of the tournament, and the type of medal.
 */
public class Medal {
    /**
     * Enumeration for the types of medals: bronze, silver, and gold.
     */
    public enum type {bronze, silver, gold}

    private String tournament;
    private int year;
    private type type;

    /**
     * Constructs a Medal with the specified year, tournament, and type.
     *
     * @param year the year the medal was awarded
     * @param tournament the name of the tournament
     * @param type the type of the medal
     */
    public Medal(int year, String tournament, type type) {
        this.year = year;
        this.tournament = tournament;
        this.type = type;
    }

    /**
     * Checks if this medal is equal to another medal.
     * Two medals are considered equal if they have the same tournament name, year, and type.
     *
     * @param m the medal to compare to
     * @return true if the medals are equal, false otherwise
     */
    public boolean equals(Medal m) {
        return this.tournament.equals(m.tournament) && this.year == m.year && this.type.equals(m.type);
    }

    /**
     * Returns a string representation of the medal.
     *
     * @return a string describing the medal
     */
    @Override
    public String toString() {
        return this.tournament + " was in the year " + this.year + " and the type " + this.type + ".";
    }

    /**
     * Checks if this object is equal to another object.
     * Overrides the default equals method.
     *
     * @param obj the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof Medal) {
            result = this.equals((Medal) obj)
                    && this.year == ((Medal) obj).year
                    && this.type.equals(((Medal) obj).type)
                    && this.tournament.equals(((Medal) obj).tournament);
        }
        return result;
    }
}
