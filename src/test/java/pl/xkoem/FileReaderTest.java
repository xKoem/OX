package pl.xkoem;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.xkoem.userinterface.language.LanguageStrings;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@Test
public class FileReaderTest {


    private ByteArrayOutputStream outContent;

    @BeforeMethod
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    public void checkWhenFileNotExist() {
        Assert.assertEquals(FileReader.readLanguageFile("dsasda", System.out::println).isEmpty(), new LanguageStrings().isEmpty());
    }

    public void checkWhenFileExists() {
        Assert.assertFalse(FileReader.readLanguageFile("languages/ENG.lang", System.out::println).isEmpty());
    }
}
