package pl.xkoem;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JudgeTest {

    Judge judgeWithBoardSize9;

    @BeforeMethod
    public void setUp() {
        judgeWithBoardSize9 = new Judge(9, 3);
    }

    @Test
    public void testIfMatchIsFinished_WhenAnyCheckingTookPlace_shouldReturnFalse() {
        assertFalse(judgeWithBoardSize9.isMatchFinished());
    }

    @Test
    public void testIfMatchIsFinished_When_9_CheckingsTookPlace_shouldReturnTrue() {
        for(int i = 0; i < 9; i++) {
            judgeWithBoardSize9.checkNewPosition(new GameBoard(new GameConfiguration(), System.out::println));
        }
        assertTrue(judgeWithBoardSize9.isMatchFinished());
    }


      @Test
    public void testCheckVerticalShouldReturnTrue() {

        GameBoard gameBoard = new GameBoard(new GameConfiguration(), System.out::println);
        gameBoard.setSymbolAtPosition(Symbol.X, 1);
        gameBoard.setSymbolAtPosition(Symbol.O, 2);
        gameBoard.setSymbolAtPosition(Symbol.X, 4);
        gameBoard.setSymbolAtPosition(Symbol.O, 5);
        gameBoard.setSymbolAtPosition(Symbol.X, 7);

        assertTrue(judgeWithBoardSize9.checkVertical(gameBoard, 7));
    }


    @Test
    public void testCheckHorizontalShouldReturnTrue() {
        GameBoard gameBoard = new GameBoard(new GameConfiguration(), System.out::println);
        gameBoard.setSymbolAtPosition(Symbol.X, 6);
        gameBoard.setSymbolAtPosition(Symbol.X, 7);
        gameBoard.setSymbolAtPosition(Symbol.X, 8);

        assertTrue(judgeWithBoardSize9.checkHorizontal(gameBoard, 8));
    }


    @Test
    public void testCheckNewPositionWhenIsHorizontal() {

        GameBoard gameBoard = new GameBoard(new GameConfiguration(), System.out::println);
        gameBoard.setSymbolAtPosition(Symbol.X, 6);
        gameBoard.setSymbolAtPosition(Symbol.X, 7);
        gameBoard.setSymbolAtPosition(Symbol.X, 8);

        assertTrue(judgeWithBoardSize9.checkNewPosition(gameBoard));
    }

    @Test
    public void testDecreasingDiagonal_whenShouldBeCorrect() {
        GameBoard gameBoard = new GameBoard(new GameConfiguration(), System.out::println);
        gameBoard.setSymbolAtPosition(Symbol.X, 0);
        gameBoard.setSymbolAtPosition(Symbol.X, 4);
        gameBoard.setSymbolAtPosition(Symbol.X, 8);

        assertTrue(judgeWithBoardSize9.checkDecreasingDiagonal(gameBoard, 8));
    }

    @Test
    public void testDecreasingDiagonal_OtherTry_whenShouldBeCorrect() {
        GameBoard gameBoard = new GameBoard(new GameConfiguration(), System.out::println);
        gameBoard.setSymbolAtPosition(Symbol.X, 8);
        gameBoard.setSymbolAtPosition(Symbol.X, 4);
        gameBoard.setSymbolAtPosition(Symbol.X, 0);

        assertTrue(judgeWithBoardSize9.checkDecreasingDiagonal(gameBoard, 0));
    }




    @Test
    public void testDecreasingDiagonal_onNotSquaredBoard_whenShouldBeCorrect() {
        Judge judge = new Judge(15, 3);
        GameBoard gameBoard = new GameBoard(new GameConfiguration(3, 5, 3), System.out::println);
        gameBoard.setSymbolAtPosition(Symbol.X, 6);
        gameBoard.setSymbolAtPosition(Symbol.X, 10);
        gameBoard.setSymbolAtPosition(Symbol.X, 14);

        assertTrue(judge.checkDecreasingDiagonal(gameBoard, 10));
    }

    @Test
    public void testIncreasingDiagonal_whenShouldBeCorrect() {
        GameBoard gameBoard = new GameBoard(new GameConfiguration(), System.out::println);
        gameBoard.setSymbolAtPosition(Symbol.X, 6);
        gameBoard.setSymbolAtPosition(Symbol.X, 4);
        gameBoard.setSymbolAtPosition(Symbol.X, 2);

        assertTrue(judgeWithBoardSize9.checkIncreasingDiagonal(gameBoard, 4));
    }

    @Test
    public void testIncreasingDiagonal_onNotSquaredBoard_whenShouldBeCorrect() {
        Judge judge = new Judge(15, 3);

        GameBoard gameBoard = new GameBoard(new GameConfiguration(3, 5, 3), System.out::println);
        gameBoard.setSymbolAtPosition(Symbol.X, 9);
        gameBoard.setSymbolAtPosition(Symbol.X, 7);
        gameBoard.setSymbolAtPosition(Symbol.X, 5);

        assertTrue(judge.checkIncreasingDiagonal(gameBoard, 7));
    }


    @Test
    public void testIncreasingDiagonal_onBigBoardWhenIsSmallDiagonalToTest_whenShouldBeCorrect() {
        GameBoard gameBoard = new GameBoard(new GameConfiguration(6, 6, 3), System.out::println);
        gameBoard.setSymbolAtPosition(Symbol.X, 12);
        gameBoard.setSymbolAtPosition(Symbol.X, 7);
        gameBoard.setSymbolAtPosition(Symbol.X, 2);

        assertTrue(judgeWithBoardSize9.checkIncreasingDiagonal(gameBoard, 2));
    }



    @Test
    public void testDecreasingDiagonal_onBigBoardWhenIsSmallDiagonalToTest_whenShouldBeCorrect() {
        GameBoard gameBoard = new GameBoard(new GameConfiguration(6, 6, 3), System.out::println);
        gameBoard.setSymbolAtPosition(Symbol.X, 3);
        gameBoard.setSymbolAtPosition(Symbol.X, 10);
        gameBoard.setSymbolAtPosition(Symbol.X, 17);

        assertTrue(judgeWithBoardSize9.checkDecreasingDiagonal(gameBoard, 10));
    }

    @Test
    public void testIncreasingDiagonal_on_3x3_board_shouldReturnFalse() {

        GameBoard gameBoard = new GameBoard(new GameConfiguration(), System.out::println);
        gameBoard.setSymbolAtPosition(Symbol.X, 1);
        gameBoard.setSymbolAtPosition(Symbol.O, 0);

        assertFalse(judgeWithBoardSize9.checkIncreasingDiagonal(gameBoard, 1));
    }

    @Test
    public void testDecreasingDiagonal_on_3x3_board_shouldReturnFalse() {

        GameBoard gameBoard = new GameBoard(new GameConfiguration(), System.out::println);
        gameBoard.setSymbolAtPosition(Symbol.X, 1);
        gameBoard.setSymbolAtPosition(Symbol.O, 0);

        assertFalse(judgeWithBoardSize9.checkDecreasingDiagonal(gameBoard, 1));
    }



}
