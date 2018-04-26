package pl.xkoem;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GameBoardTests {

    GameBoard gameBoard;

    @BeforeTest
    public void setUpDefaultBoard() {
        gameBoard = new GameBoard(new GameConfiguration(), System.out::println);
    }

    @Test
    public void checkValidPositionsForDefaultBoard() {
        Assert.assertTrue(gameBoard.isPositionValid(1));
    }

    @Test
    public void checkInvalidPositionForDefaultBoard() {
        Assert.assertFalse(gameBoard.isPositionValid(9));
    }
}
