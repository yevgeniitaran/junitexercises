package com.junitexercises.ch1_5.football;

public class FootballTeam implements  Comparable<FootballTeam> {
    private int gamesWon;

    public FootballTeam(int gamesWon) {
        if (gamesWon < 0) {
            throw new IllegalArgumentException("Number of gamesWon should be >= 0, received " + gamesWon);
        }
        this.gamesWon = gamesWon;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    @Override
    public int compareTo(FootballTeam o) {
        return Integer.compare(this.gamesWon, o.gamesWon);
    }
}
