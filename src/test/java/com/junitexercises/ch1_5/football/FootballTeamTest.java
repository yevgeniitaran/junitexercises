package com.junitexercises.ch1_5.football;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class FootballTeamTest {

    private static final int ANY_NUMBER = 123;

    public Object[] numberOfGamesWon() {
        return $(0, 1, 2);
    }

    public Object[] illegalNumberOfGamesWon() {
        return $(-10, -1);
    }
    
    @Test
    @Parameters(method = "numberOfGamesWon")
    public void constructorShouldSetGamesWon(int numberOfGamesWon) {
        FootballTeam team = new FootballTeam(numberOfGamesWon);
        assertEquals(numberOfGamesWon + " games passed to constructor bot " +
                team.getGamesWon() + " were returned", numberOfGamesWon, team.getGamesWon());
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "illegalNumberOfGamesWon")
    public void constructorShouldThrowExceptionForIllegalGamesNumber(int illegalNumberOfGamesWon) {
        FootballTeam team = new FootballTeam(illegalNumberOfGamesWon);
    }

    @Test
    public void shouldBePossibleToCompareTeams() {
        FootballTeam footballTeam = new FootballTeam(ANY_NUMBER);
        assertTrue("Football team should implement comparable",
                footballTeam instanceof Comparable);
    }

    @Test
    public void teamsShouldBeCorrectlyComparable() {
        FootballTeam team_2 = new FootballTeam(2);
        FootballTeam team_3 = new FootballTeam(3);

        assertTrue("team with 3 games won should be ranked before the team with 2 games won",
                team_3.compareTo(team_2) > 0);
        assertTrue("team with 2 games won should be ranked after the team with 3 games won",
                team_2.compareTo(team_3) < 0);

        FootballTeam team_2b = new FootballTeam(2);

        assertEquals("team with 2 games won should be equal to team with 2 games won",
                team_2.compareTo(team_2b), 0);
    }


}
