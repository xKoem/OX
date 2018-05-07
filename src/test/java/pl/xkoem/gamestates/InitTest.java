package pl.xkoem.gamestates;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.xkoem.GameConfiguration;
import pl.xkoem.Players;

import java.util.Optional;
import java.util.Scanner;

import static org.testng.Assert.assertEquals;
@Test
public class InitTest {

    Init init;

    @BeforeMethod
    public void setUp() {
        init = new Init(new Scanner(System.in)::nextLine, System.out::println, new Players(), new GameConfiguration());
    }


    public void testTryChangeStringToInt_whenNumberIsPresent_shouldReturnThisNumber() {
        assertEquals(init.tryChangeStringToInt("1"), Optional.of(1));
    }

    public void testTryChangeStringToInt_whenLetterIsPresent_shouldReturnOptionalEmpty() {
        assertEquals(init.tryChangeStringToInt("a"), Optional.empty());
    }

    public void testTryChangeStringToInt_withMinValue_whenNumberIsLowerThanMinValue_shouldReturnOptionalEmpty() {
        assertEquals(init.tryChangeStringToIntWithMinValue("5", 6), Optional.empty());
    }

    public void testTryChangeStringToInt_withMinValue_whenNumberISHigherThanMinValue_shouldReturnThisNumber() {
        assertEquals(init.tryChangeStringToIntWithMinValue("5", 2), Optional.of(5));
    }

    public void testTryChangeStringToInt_withMinAndMaxValue_whenNumberIsLowerThanMinValue_shouldReturnOptionalEmpty() {
        assertEquals(init.tryChangeStringToIntBetweenMinAndMaxValue("5", 6, 8), Optional.empty());
    }

    public void testTryChangeStringToInt_withMinAndMaxValue_whenNumberIsHigherThanMinValue_shouldReturnThisNumber() {
        assertEquals(init.tryChangeStringToIntBetweenMinAndMaxValue("5", 2,6), Optional.of(5));
    }

    public void testTryChangeStringToInt_withMinAndMaxValue_whenNumberIsHigherThanMaxValue_shouldReturnOptionalEmpty() {
        assertEquals(init.tryChangeStringToIntBetweenMinAndMaxValue("9", 6, 8), Optional.empty());
    }

}

