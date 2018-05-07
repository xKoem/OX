package pl.xkoem;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class JudgeTest {

    Judge judgeWithBoardSize9;
    GameBoard gameBoard;

    @BeforeMethod
    public void setUp() {
        judgeWithBoardSize9 = new Judge(9, 3);
        gameBoard = new GameBoard(new GameConfiguration(), System.out::println);
    }

    public void testIfMatchIsFinished_WhenAnyCheckingTookPlace_shouldReturnFalse() {
        assertFalse(judgeWithBoardSize9.isMatchFinished());
    }

    public void testIfMatchIsFinished_When_9_CheckingsTookPlace_shouldReturnTrue() {
        for(int i = 0; i < 9; i++) {
            judgeWithBoardSize9.checkNewPosition(new GameBoard(new GameConfiguration(), System.out::println));
        }
        assertTrue(judgeWithBoardSize9.isMatchFinished());
    }

    public void testCheckVerticalShouldReturnTrue() {

        gameBoard.setSymbolAtPosition(Symbol.X, 1);
        gameBoard.setSymbolAtPosition(Symbol.O, 2);
        gameBoard.setSymbolAtPosition(Symbol.X, 4);
        gameBoard.setSymbolAtPosition(Symbol.O, 5);
        gameBoard.setSymbolAtPosition(Symbol.X, 7);

        assertTrue(judgeWithBoardSize9.checkVertical(gameBoard, 7));
    }

    public void testCheckHorizontalShouldReturnTrue() {
        gameBoard.setSymbolAtPosition(Symbol.X, 6);
        gameBoard.setSymbolAtPosition(Symbol.X, 7);
        gameBoard.setSymbolAtPosition(Symbol.X, 8);

        assertTrue(judgeWithBoardSize9.checkHorizontal(gameBoard, 8));
    }

    public void testCheckNewPositionWhenIsHorizontal() {

        gameBoard.setSymbolAtPosition(Symbol.X, 6);
        gameBoard.setSymbolAtPosition(Symbol.X, 7);
        gameBoard.setSymbolAtPosition(Symbol.X, 8);

        assertTrue(judgeWithBoardSize9.checkNewPosition(gameBoard));
    }

    public void testDecreasingDiagonal_whenShouldBeCorrect() {
        gameBoard.setSymbolAtPosition(Symbol.X, 0);
        gameBoard.setSymbolAtPosition(Symbol.X, 4);
        gameBoard.setSymbolAtPosition(Symbol.X, 8);

        assertTrue(judgeWithBoardSize9.checkDecreasingDiagonal(gameBoard, 8));
    }

    public void testDecreasingDiagonal_OtherTry_whenShouldBeCorrect() {
        gameBoard.setSymbolAtPosition(Symbol.X, 8);
        gameBoard.setSymbolAtPosition(Symbol.X, 4);
        gameBoard.setSymbolAtPosition(Symbol.X, 0);

        assertTrue(judgeWithBoardSize9.checkDecreasingDiagonal(gameBoard, 0));
    }

    public void testDecreasingDiagonal_onNotSquaredBoard_whenShouldBeCorrect() {
        Judge judge = new Judge(15, 3);
        GameBoard gameBoard = new GameBoard(new GameConfiguration(3, 5, 3), System.out::println);
        gameBoard.setSymbolAtPosition(Symbol.X, 6);
        gameBoard.setSymbolAtPosition(Symbol.X, 10);
        gameBoard.setSymbolAtPosition(Symbol.X, 14);

        assertTrue(judge.checkDecreasingDiagonal(gameBoard, 10));
    }

    public void testIncreasingDiagonal_whenShouldBeCorrect() {
        gameBoard.setSymbolAtPosition(Symbol.X, 6);
        gameBoard.setSymbolAtPosition(Symbol.X, 4);
        gameBoard.setSymbolAtPosition(Symbol.X, 2);

        assertTrue(judgeWithBoardSize9.checkIncreasingDiagonal(gameBoard, 4));
    }

    public void testIncreasingDiagonal_onNotSquaredBoard_whenShouldBeCorrect() {
        Judge judge = new Judge(15, 3);

        GameBoard gameBoard = new GameBoard(new GameConfiguration(3, 5, 3), System.out::println);
        gameBoard.setSymbolAtPosition(Symbol.X, 9);
        gameBoard.setSymbolAtPosition(Symbol.X, 7);
        gameBoard.setSymbolAtPosition(Symbol.X, 5);

        assertTrue(judge.checkIncreasingDiagonal(gameBoard, 7));
    }

    public void testIncreasingDiagonal_onBigBoardWhenIsSmallDiagonalToTest_whenShouldBeCorrect() {
        GameBoard gameBoard = new GameBoard(new GameConfiguration(6, 6, 3), System.out::println);
        gameBoard.setSymbolAtPosition(Symbol.X, 12);
        gameBoard.setSymbolAtPosition(Symbol.X, 7);
        gameBoard.setSymbolAtPosition(Symbol.X, 2);

        assertTrue(judgeWithBoardSize9.checkIncreasingDiagonal(gameBoard, 2));
    }

    public void testDecreasingDiagonal_onBigBoardWhenIsSmallDiagonalToTest_whenShouldBeCorrect() {
        GameBoard gameBoard = new GameBoard(new GameConfiguration(6, 6, 3), System.out::println);
        gameBoard.setSymbolAtPosition(Symbol.X, 3);
        gameBoard.setSymbolAtPosition(Symbol.X, 10);
        gameBoard.setSymbolAtPosition(Symbol.X, 17);

        assertTrue(judgeWithBoardSize9.checkDecreasingDiagonal(gameBoard, 10));
    }

    public void testIncreasingDiagonal_on_3x3_board_shouldReturnFalse() {

        gameBoard.setSymbolAtPosition(Symbol.X, 1);
        gameBoard.setSymbolAtPosition(Symbol.O, 0);

        assertFalse(judgeWithBoardSize9.checkIncreasingDiagonal(gameBoard, 1));
    }

    public void testDecreasingDiagonal_on_3x3_board_shouldReturnFalse() {

        gameBoard.setSymbolAtPosition(Symbol.X, 1);
        gameBoard.setSymbolAtPosition(Symbol.O, 0);

        assertFalse(judgeWithBoardSize9.checkDecreasingDiagonal(gameBoard, 1));
    }

    public void testDecreasingDiagonal_on_3x3_board_putting_X_onLeftSide_shouldReturnFalse() {

        gameBoard.setSymbolAtPosition(Symbol.X, 0);
        gameBoard.setSymbolAtPosition(Symbol.X, 2);

        assertFalse(judgeWithBoardSize9.checkDecreasingDiagonal(gameBoard, 2));
    }

    public void testIncreasingDiagonal_on_3x3_board_putting_X_onLeftSide_shouldReturnFalse() {

        gameBoard.setSymbolAtPosition(Symbol.X, 0);
        gameBoard.setSymbolAtPosition(Symbol.X, 2);

        assertFalse(judgeWithBoardSize9.checkIncreasingDiagonal(gameBoard, 2));
    }

    public void testIncreasingDiagonal_on_3x3_board_putting_X_onEveryPosition_shouldReturnFalse() {
        for(int i = 0; i < 9; i++) {
            GameBoard gameBoard = new GameBoard(new GameConfiguration(), System.out::println);
            gameBoard.setSymbolAtPosition(Symbol.X, i);
            assertFalse(judgeWithBoardSize9.checkIncreasingDiagonal(gameBoard, i));
        }
    }

    public void testDecreasingDiagonal_on_3x3_board_putting_X_onEveryPosition_shouldReturnFalse() {
        for(int i = 0; i < 9; i++) {
            GameBoard gameBoard = new GameBoard(new GameConfiguration(), System.out::println);
            gameBoard.setSymbolAtPosition(Symbol.X, i);
            assertFalse(judgeWithBoardSize9.checkDecreasingDiagonal(gameBoard, i));

        }
    }

}
