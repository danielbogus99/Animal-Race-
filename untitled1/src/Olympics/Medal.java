package Olympics;

    public class Medal
    {
        enum type{bronze, silver,gold}
        private String tournament;
        private int year;
        public Medal(int year,String tournament)
        {
            this.year=year;
            this.tournament=tournament;
        }
        public boolean equals(Medal m){
            return this.tournament.equals(m.tournament) && this.year==m.year;
        }
        public String toString() {
            return this.tournament + " " + this.year;
        }
    }
