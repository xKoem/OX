package pl.xkoem;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.security.InvalidParameterException;

public class GameConfigurationTest {

    GameConfiguration gameConfiguration;
    Boolean isError;
    @BeforeTest
    public void prepareForTests() {
        gameConfiguration = new GameConfiguration();
        isError = false;
    }

    @Test
    public void setBoardSizeValid() {
        Boolean isError = false;
        try {
            gameConfiguration.setBoardSettings(3, 3, 3);
        } catch (InvalidParameterException e) {
            isError = true;
        }
        Assert.assertTrue(!isError);
    }

    @Test
    public void setBoardSizeValid_whenCharToWinIsBiggerThanSizeX_shouldBeFalse() {
        Boolean isError = false;
        try {
            gameConfiguration.setBoardSettings(3, 5, 4);
        } catch (InvalidParameterException e) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    @Test
    public void setBoardSizeValid_whenCharToWinIsBiggerThanSizeY_shouldBeFalse() {
        Boolean isError = false;
        try {
            gameConfiguration.setBoardSettings(5, 3, 4);
        } catch (InvalidParameterException e) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    @Test
    public void setBoardSizeValid_whenCharToWinIsBiggerThanBothSizes_shouldBeFalse() {
        Boolean isError = false;
        try {
            gameConfiguration.setBoardSettings(3, 3, 4);
        } catch (InvalidParameterException e) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    @Test
    public void testBoardSizeFromDefaultGameConfiguration() {
        Assert.assertEquals(gameConfiguration.getBoardSize(), new Integer[]{3,3});
    }


}
