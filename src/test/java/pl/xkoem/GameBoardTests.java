package pl.xkoem;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.xkoem.board.GameBoard;
import pl.xkoem.userinterface.UserInterface;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.testng.Assert.*;

@Test
public class GameBoardTests {

    GameBoard gameBoard;

    ByteArrayOutputStream outContent;

    @BeforeMethod
    public void setUpDefaultBoard() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        gameBoard = new GameBoard(new GameConfiguration(3,3,3), new UserInterface(new Scanner(System.in)::nextLine, System.out::println, FileReader.readLanguageFile("PL", System.out::println)));
    }

    public void checkValidPositionsForDefaultBoard() {
        assertTrue(gameBoard.isPositionValid(1));
    }

    public void checkInvalidPositionForDefaultBoard() {
        assertFalse(gameBoard.isPositionValid(-1));
    }
}
