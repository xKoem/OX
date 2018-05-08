package pl.xkoem.userinterface;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class LanguageStringsTest {

    String testingString;

    @BeforeMethod
    public void setUp() {
        testingString = "cos";
    }

    public void testAddLanguageString() {
        LanguageStrings languageStrings = new LanguageStrings();
        languageStrings.add(LanguageName.too_low_number, testingString);
        Assert.assertEquals(languageStrings.get(LanguageName.too_low_number), testingString);
    }
}
