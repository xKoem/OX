package pl.xkoem;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DashBoardTests {
    DashBoard dashBoard;
    Players players;

    @BeforeTest
    public void setUp() {
        players = new Players();
        players.setPlayers("p1", "p2");
        dashBoard = new DashBoard(players);
    }

    @Test
    public void checkIfIsAnyWinner_ShouldReturnTrue() {
        dashBoard.addPointsToWinner(players.getPlayer(0)); // adding 3 points to p1
        dashBoard.addPointsToWinner(players.getPlayer(0)); // adding 3 points to p1
        dashBoard.addPointsToWinner(players.getPlayer(1)); // adding 3 points to p2

        Assert.assertTrue(dashBoard.isWinner());
    }


    @Test
    public void checkIfIsAnyWinner_ShouldReturnFalse() {
        dashBoard.addPointsToWinner(players.getPlayer(0)); // adding 3 points to p1
        dashBoard.addPointsToWinner(players.getPlayer(1)); // adding 3 points to p2

        Assert.assertFalse(dashBoard.isWinner());
    }

    @Test
    public void testWinningPlayer() {

    }


}
