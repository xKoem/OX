package pl.xkoem.userinterface;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.xkoem.userinterface.language.LanguageName;
import pl.xkoem.userinterface.language.LanguageStrings;

@Test
public class LanguageStringsTest {

    String testingString;

    @BeforeMethod
    public void setUp() {
        testingString = "cos";
    }

    public void testAddLanguageString() {
        LanguageStrings languageStrings = new LanguageStrings();
        languageStrings.add(LanguageName.TOO_LOW_NUMBER, testingString);
        Assert.assertEquals(languageStrings.get(LanguageName.TOO_LOW_NUMBER), testingString);
    }
}
