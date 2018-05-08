package pl.xkoem.userinterface;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class ReplacePatternTest {

    String from;
    String to;

    @BeforeMethod
    public void setUp() {
        from = "cos";
        to = "cosinnego";
    }

    public void testReplacePattern() {
        ReplacePattern replacePattern = new ReplacePattern(from, to);
        Assert.assertEquals(replacePattern.getFrom(), "%" + from +"%");
        Assert.assertEquals(replacePattern.getTo(),   to);
    }
}
