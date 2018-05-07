package pl.xkoem;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.security.InvalidParameterException;

@Test
public class GameConfigurationTest {

    GameConfiguration gameConfiguration;
    Boolean isError;
    @BeforeTest
    public void prepareForTests() {
        gameConfiguration = new GameConfiguration();
        isError = false;
    }

    public void setBoardSizeValid() {
        Boolean isError = false;
        try {
            gameConfiguration.setBoardSettings(3, 3, 3);
        } catch (InvalidParameterException e) {
            isError = true;
        }
        Assert.assertTrue(!isError);
    }

    public void setBoardSizeValid_whenCharToWinIsBiggerThanSizeX_shouldBeFalse() {
        Boolean isError = false;
        try {
            gameConfiguration.setBoardSettings(3, 5, 4);
        } catch (InvalidParameterException e) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    public void setBoardSizeValid_whenCharToWinIsBiggerThanSizeY_shouldBeFalse() {
        Boolean isError = false;
        try {
            gameConfiguration.setBoardSettings(5, 3, 4);
        } catch (InvalidParameterException e) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    public void setBoardSizeValid_whenCharToWinIsBiggerThanBothSizes_shouldBeFalse() {
        Boolean isError = false;
        try {
            gameConfiguration.setBoardSettings(3, 3, 4);
        } catch (InvalidParameterException e) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    public void testBoardSizeFromDefaultGameConfiguration() {
        Assert.assertEquals(gameConfiguration.getBoardSize(), new int[]{3,3});
    }

    public void checkSettingBeginner() {
        gameConfiguration.setBeginner(Symbol.X);
        Assert.assertEquals(gameConfiguration.getBeginner(), Symbol.X);
    }

    public void checkChangingBeginner() {
        gameConfiguration.setBeginner(Symbol.X);
        gameConfiguration.changeBeginner();
        Assert.assertEquals(gameConfiguration.getBeginner(), Symbol.O);
    }

}
