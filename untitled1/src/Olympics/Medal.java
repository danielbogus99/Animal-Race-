package Olympics;

    public class Medal
    {
        public enum type{bronze, silver,gold}
        private String tournament;
        private int year;
        private type type;
        public Medal(int year,String tournament,type type)
        {
            this.year=year;
            this.tournament=tournament;
            this.type=type;
        }
        public boolean equals(Medal m){
            return this.tournament.equals(m.tournament) && this.year==m.year;
        }
        public String toString() {
            return this.tournament + " " + this.year;
        }
    }
