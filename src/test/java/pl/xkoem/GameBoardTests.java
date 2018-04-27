package pl.xkoem;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class GameBoardTests {

    GameBoard gameBoard;

    @BeforeMethod
    public void setUpDefaultBoard() {
        gameBoard = new GameBoard(new GameConfiguration(3,3,3), System.out::println);
    }

    @Test
    public void checkValidPositionsForDefaultBoard() {
        assertTrue(gameBoard.isPositionValid(1));
    }

    @Test
    public void checkInvalidPositionForDefaultBoard() {
        assertFalse(gameBoard.isPositionValid(-1));
    }

    @Test
    public void checkTranslatePosition() {
        assertEquals(gameBoard.translate(5), new Integer[]{2,1});
        assertEquals(gameBoard.translate(1), new Integer[]{1,0});
        assertEquals(gameBoard.translate(0), new Integer[]{0,0});
        assertEquals(gameBoard.translate(8), new Integer[]{2,2});
    }

    @Test
    public void checkGetArrayPosition() {
        assertEquals(gameBoard.getArrayPosition(1,0), new Integer(1));
        assertEquals(gameBoard.getArrayPosition(2,1), new Integer(5));
        assertEquals(gameBoard.getArrayPosition(1,1), new Integer(4));
        assertEquals(gameBoard.getArrayPosition(2,2), new Integer(8));
        assertEquals(gameBoard.getArrayPosition(0,0), new Integer(0));
    }

    @Test
    public void checkWhenAddingSignAtPosition_thenGetSameObject() {
        gameBoard.setSymbolAtPosition(Symbol.X, 2);
        assertEquals(gameBoard.getSymbolAtPosition(2,0), Symbol.X);
    }


}
