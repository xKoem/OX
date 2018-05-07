package pl.xkoem.gamestates;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.xkoem.GameConfiguration;
import pl.xkoem.Players;

import java.util.Scanner;

@Test
public class MatchTest {

    Match match;

    @BeforeMethod
    public void setUp() {
        match = new Match(new Scanner(System.in)::nextLine, System.out::println, new Players(), new GameConfiguration());
    }

    public void checkGettingPointsOnMatchEnd() {

    }


}
