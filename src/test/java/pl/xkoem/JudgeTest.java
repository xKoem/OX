package pl.xkoem;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.xkoem.board.GameBoard;
import pl.xkoem.userinterface.UserInterface;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static pl.xkoem.Symbol.O;
import static pl.xkoem.Symbol.X;

@Test
public class JudgeTest {

    Judge judgeWithBoardSize9;
    GameBoard gameBoard;
    UserInterface userInterface;
    private ByteArrayOutputStream outContent;

    @BeforeMethod
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInterface = new UserInterface(new Scanner(System.in)::nextLine, System.out::println, FileReader.readLanguageFile("PL", System.out::println));
        judgeWithBoardSize9 = new Judge(9, 3);
        gameBoard = new GameBoard(new GameConfiguration(), userInterface);
    }

    private void setSymbolsAtPosition(Symbol symbol, int... positions) {
        for (int position: positions) {
            gameBoard.setSymbolAtPosition(symbol, position);
        }
    }

    public void testIfMatchIsFinished_WhenAnyCheckingTookPlace_shouldReturnFalse() {
        assertFalse(judgeWithBoardSize9.isMatchFinished());
    }

    public void testIfMatchIsFinished_When_9_CheckingsTookPlace_shouldReturnTrue() {
        for(int i = 0; i < 9; i++) {
            judgeWithBoardSize9.checkNewPosition(new GameBoard(new GameConfiguration(), userInterface));
        }
        assertTrue(judgeWithBoardSize9.isMatchFinished());
    }

    public void testCheckVerticalShouldReturnTrue() {
        setSymbolsAtPosition(O, 2, 5);
        setSymbolsAtPosition(X, 1, 4, 7);

        assertTrue(judgeWithBoardSize9.checkVertical(gameBoard, 7));
    }

    public void testCheckHorizontalShouldReturnTrue() {
        setSymbolsAtPosition(X, 6, 7, 8);

        assertTrue(judgeWithBoardSize9.checkHorizontal(gameBoard, 8));
    }

    public void testCheckNewPositionWhenIsHorizontal() {
        setSymbolsAtPosition(X, 6, 7, 8);

        assertTrue(judgeWithBoardSize9.checkNewPosition(gameBoard));
    }

    public void testDecreasingDiagonal_whenShouldBeCorrect() {
        setSymbolsAtPosition(X, 0, 4, 8);

        assertTrue(judgeWithBoardSize9.checkDecreasingDiagonal(gameBoard, 8));
    }

    public void testDecreasingDiagonal_OtherTry_whenShouldBeCorrect() {
        setSymbolsAtPosition(X, 8, 4, 0);

        assertTrue(judgeWithBoardSize9.checkDecreasingDiagonal(gameBoard, 0));
    }

    public void testDecreasingDiagonal_onNotSquaredBoard_whenShouldBeCorrect() {
        Judge judge = new Judge(15, 3);
        GameBoard gameBoard = new GameBoard(new GameConfiguration(3, 5, 3), userInterface);
        gameBoard.setSymbolAtPosition(X, 6);
        gameBoard.setSymbolAtPosition(X, 10);
        gameBoard.setSymbolAtPosition(X, 14);

        assertTrue(judge.checkDecreasingDiagonal(gameBoard, 10));
    }

    public void testIncreasingDiagonal_whenShouldBeCorrect() {
        setSymbolsAtPosition(X, 6, 4, 2);

        assertTrue(judgeWithBoardSize9.checkIncreasingDiagonal(gameBoard, 4));
    }

    public void testIncreasingDiagonal_onNotSquaredBoard_whenShouldBeCorrect() {
        Judge judge = new Judge(15, 3);

        GameBoard gameBoard = new GameBoard(new GameConfiguration(3, 5, 3), userInterface);
        gameBoard.setSymbolAtPosition(X, 9);
        gameBoard.setSymbolAtPosition(X, 7);
        gameBoard.setSymbolAtPosition(X, 5);

        assertTrue(judge.checkIncreasingDiagonal(gameBoard, 7));
    }

    public void testIncreasingDiagonal_onBigBoardWhenIsSmallDiagonalToTest_whenShouldBeCorrect() {
        GameBoard gameBoard = new GameBoard(new GameConfiguration(6, 6, 3), userInterface);
        gameBoard.setSymbolAtPosition(X, 12);
        gameBoard.setSymbolAtPosition(X, 7);
        gameBoard.setSymbolAtPosition(X, 2);

        assertTrue(judgeWithBoardSize9.checkIncreasingDiagonal(gameBoard, 2));
    }

    public void testDecreasingDiagonal_onBigBoardWhenIsSmallDiagonalToTest_whenShouldBeCorrect() {
        GameBoard gameBoard = new GameBoard(new GameConfiguration(6, 6, 3), userInterface);
        gameBoard.setSymbolAtPosition(X, 3);
        gameBoard.setSymbolAtPosition(X, 10);
        gameBoard.setSymbolAtPosition(X, 17);

        assertTrue(judgeWithBoardSize9.checkDecreasingDiagonal(gameBoard, 10));
    }

    public void testIncreasingDiagonal_on_3x3_board_shouldReturnFalse() {
        setSymbolsAtPosition(X, 1, 0);

        assertFalse(judgeWithBoardSize9.checkIncreasingDiagonal(gameBoard, 1));
    }

    public void testDecreasingDiagonal_on_3x3_board_shouldReturnFalse() {
        setSymbolsAtPosition(X, 1, 0);


        assertFalse(judgeWithBoardSize9.checkDecreasingDiagonal(gameBoard, 1));
    }

    public void testDecreasingDiagonal_on_3x3_board_putting_X_onLeftSide_shouldReturnFalse() {
        setSymbolsAtPosition(X, 0, 2);


        assertFalse(judgeWithBoardSize9.checkDecreasingDiagonal(gameBoard, 2));
    }

    public void testIncreasingDiagonal_on_3x3_board_putting_X_onLeftSide_shouldReturnFalse() {
        setSymbolsAtPosition(X, 0, 2);

        assertFalse(judgeWithBoardSize9.checkIncreasingDiagonal(gameBoard, 2));
    }

    public void testIncreasingDiagonal_on_3x3_board_putting_X_onEveryPosition_shouldReturnFalse() {
        for(int i = 0; i < 9; i++) {
            GameBoard gameBoard = new GameBoard(new GameConfiguration(), userInterface);
            gameBoard.setSymbolAtPosition(X, i);
            assertFalse(judgeWithBoardSize9.checkIncreasingDiagonal(gameBoard, i));
        }
    }

    public void testDecreasingDiagonal_on_3x3_board_putting_X_onEveryPosition_shouldReturnFalse() {
        for(int i = 0; i < 9; i++) {
            GameBoard gameBoard = new GameBoard(new GameConfiguration(), userInterface);
            gameBoard.setSymbolAtPosition(X, i);
            assertFalse(judgeWithBoardSize9.checkDecreasingDiagonal(gameBoard, i));

        }
    }

}
