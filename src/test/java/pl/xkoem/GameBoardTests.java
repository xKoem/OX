package pl.xkoem;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.xkoem.userinterface.UserInterface;

import java.util.Scanner;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class GameBoardTests {

    GameBoard gameBoard;

    @BeforeMethod
    public void setUpDefaultBoard() {
        gameBoard = new GameBoard(new GameConfiguration(3,3,3), new UserInterface(new Scanner(System.in)::nextLine, System.out::println, FileReader.readLanguageFile("PL")));
    }

    public void checkValidPositionsForDefaultBoard() {
        assertTrue(gameBoard.isPositionValid(1));
    }

    public void checkInvalidPositionForDefaultBoard() {
        assertFalse(gameBoard.isPositionValid(-1));
    }

    public void checkTranslatePosition() {
        assertEquals(gameBoard.translatePositionToCoordinates(5), new int[]{2,1});
        assertEquals(gameBoard.translatePositionToCoordinates(1), new int[]{1,0});
        assertEquals(gameBoard.translatePositionToCoordinates(0), new int[]{0,0});
        assertEquals(gameBoard.translatePositionToCoordinates(8), new int[]{2,2});
    }

    public void checkGetArrayPosition() {
        assertEquals(gameBoard.translateCoordinatesToPosition(1,0), 1);
        assertEquals(gameBoard.translateCoordinatesToPosition(2,1), 5);
        assertEquals(gameBoard.translateCoordinatesToPosition(1,1), 4);
        assertEquals(gameBoard.translateCoordinatesToPosition(2,2), 8);
        assertEquals(gameBoard.translateCoordinatesToPosition(0,0), 0);
    }

    public void checkWhenAddingSignAtPosition_thenGetSameObject() {
        gameBoard.setSymbolAtPosition(Symbol.X, 2);
        assertEquals(gameBoard.getSymbolAtPosition(2,0), Symbol.X);
    }


}
