package pl.xkoem.gamestates;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.xkoem.GameConfiguration;
import pl.xkoem.Players;

import java.util.Optional;
import java.util.Scanner;

import static org.testng.Assert.assertEquals;

public class InitTest {

    Init init;

    @BeforeMethod
    public void setUp() {
        init = new Init(new Scanner(System.in)::nextLine, System.out::println, new Players(), new GameConfiguration());
    }

    @Test
    public void testTryChangeStringToIntWhenNumberIsPresent_shouldReturnThisNumber() {
        assertEquals(init.tryChangeStringToInt("1"), Optional.of(1));
    }

    @Test
    public void testTryChangeStringToIntWhenLetterIsPresent_shouldReturnOptionalEmpty() {
        assertEquals(init.tryChangeStringToInt("a"), Optional.empty());
    }

    @Test
    public void testTryChangeStringToIntWithMinValue_whenNumberIsLowerThanMinValue_shouldReturnOptionalEmpty() {
        assertEquals(init.tryChangeStringToIntWithMinValue("5", 6), Optional.empty());
    }

    @Test
    public void testTryChangeStringToIntWithMinValue_whenNumberISHigherThanMinValue_shouldReturnThisNumber() {
        assertEquals(init.tryChangeStringToIntWithMinValue("5", 2), Optional.of(5));
    }


    @Test
    public void testTryChangeStringToIntWithMinAndMaxValue_whenNumberIsLowerThanMinValue_shouldReturnOptionalEmpty() {
        assertEquals(init.tryChangeStringToIntWithMinAndMaxValue("5", 6, 8), Optional.empty());
    }

    @Test
    public void testTryChangeStringToIntWithMinAndMaxValue_whenNumberIsHigherThanMinValue_shouldReturnThisNumber() {
        assertEquals(init.tryChangeStringToIntWithMinAndMaxValue("5", 2,6), Optional.of(5));
    }

    @Test
    public void testTryChangeStringToIntWithMinAndMaxValue_whenNumberIsHigherThanMaxValue_shouldReturnOptionalEmpty() {
        assertEquals(init.tryChangeStringToIntWithMinAndMaxValue("9", 6, 8), Optional.empty());
    }

    @Test
    public void testTryChangeStringToIntWithMinAndMaxValue_whenNumberIsLowerThanMamValue_adnHigherThanMinValue_shouldReturnThisNumber() {
        assertEquals(init.tryChangeStringToIntWithMinAndMaxValue("5", 2,6), Optional.of(5));
    }
}

