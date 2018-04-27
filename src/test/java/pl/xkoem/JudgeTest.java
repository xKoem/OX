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
    public void testIfMatchIsFinished_WhenNineCheckingsTookPlace_shouldReturnTrue() {
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

        assertTrue(judgeWithBoardSize9.checkVertical(gameBoard));
    }


    @Test
    public void testCheckHorizontalShouldReturnTrue() {
        GameBoard gameBoard = new GameBoard(new GameConfiguration(), System.out::println);
        gameBoard.setSymbolAtPosition(Symbol.X, 6);
        gameBoard.setSymbolAtPosition(Symbol.X, 7);
        gameBoard.setSymbolAtPosition(Symbol.X, 8);

        assertTrue(judgeWithBoardSize9.checkHorizontal(gameBoard));
    }


    @Test
    public void testCheckNewPosition() {

        GameBoard gameBoard = new GameBoard(new GameConfiguration(), System.out::println);
        gameBoard.setSymbolAtPosition(Symbol.X, 6);
        gameBoard.setSymbolAtPosition(Symbol.X, 7);
        gameBoard.setSymbolAtPosition(Symbol.X, 8);

        assertTrue(judgeWithBoardSize9.checkNewPosition(gameBoard));
    }



}
