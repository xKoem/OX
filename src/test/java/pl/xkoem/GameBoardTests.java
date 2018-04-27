package pl.xkoem;

import org.testng.annotations.BeforeMethod;
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
        assertEquals(gameBoard.translatePositionToCoordinates(5), new int[]{2,1});
        assertEquals(gameBoard.translatePositionToCoordinates(1), new int[]{1,0});
        assertEquals(gameBoard.translatePositionToCoordinates(0), new int[]{0,0});
        assertEquals(gameBoard.translatePositionToCoordinates(8), new int[]{2,2});
    }

    @Test
    public void checkGetArrayPosition() {
        assertEquals(gameBoard.translateCoordinatesToPosition(1,0), 1);
        assertEquals(gameBoard.translateCoordinatesToPosition(2,1), 5);
        assertEquals(gameBoard.translateCoordinatesToPosition(1,1), 4);
        assertEquals(gameBoard.translateCoordinatesToPosition(2,2), 8);
        assertEquals(gameBoard.translateCoordinatesToPosition(0,0), 0);
    }

    @Test
    public void checkWhenAddingSignAtPosition_thenGetSameObject() {
        gameBoard.setSymbolAtPosition(Symbol.X, 2);
        assertEquals(gameBoard.getSymbolAtPosition(2,0), Symbol.X);
    }


}
