package pl.xkoem;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@Test
public class DashBoardTests {
    DashBoard dashBoard;
    Players players;
    String PLAYER1 = "p1";
    String PLAYER2 = "p2";

    @BeforeMethod
    public void setUp() {
        players = new Players();
        players.setPlayers(PLAYER1, PLAYER2);
        dashBoard = new DashBoard(players);
    }
    public void checkIfIsAnyWinner_WhenNoPointsAdded_ShouldReturnFalse() {
        Assert.assertFalse(dashBoard.isWinner());
    }

    public void checkIfIsAnyWinner_ShouldReturnTrue() {

        dashBoard.addPointsToWinner(players.getPlayer(0)); // adding 3 points to p1
        dashBoard.addPointsToWinner(players.getPlayer(0)); // adding 3 points to p1
        dashBoard.addPointsToWinner(players.getPlayer(1)); // adding 3 points to p2

        Assert.assertTrue(dashBoard.isWinner());
    }

    public void checkIfIsAnyWinner_ShouldReturnFalse() {
        dashBoard.addPointsToWinner(players.getPlayer(0)); // adding 3 points to p1
        dashBoard.addPointsToWinner(players.getPlayer(1)); // adding 3 points to p2

        Assert.assertFalse(dashBoard.isWinner());
    }

    public void checkIfGettingWinningPlayerName() {
        dashBoard.addPointsToWinner(players.getPlayer(0)); // adding 3 points to p1

        Assert.assertEquals(dashBoard.getWinnerName(), PLAYER1);
    }

    public void checkIfGettingLosingPlayerName() {
        dashBoard.addPointsToWinner(players.getPlayer(0)); // adding 3 points to p1

        Assert.assertEquals(dashBoard.getLoserName(), PLAYER2);
    }


    public void checkIfGettingWinningPoints() {
        dashBoard.addPointsToWinner(players.getPlayer(0)); // adding 3 points to p1

        Assert.assertEquals(dashBoard.getWinnerPoints(), "3");
    }

    public void checkIfGettingLosingPoints() {
        dashBoard.addPointsToWinner(players.getPlayer(0)); // adding 3 points to p1

        Assert.assertEquals(dashBoard.getLoserPoints(), "0");
    }


}
