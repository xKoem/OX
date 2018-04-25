package pl.xkoem;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PlayerTests {

    @Test
    public void testAddPlayer() {
        Players players = new Players();
        players.setPlayers("player", "player2");
        Assert.assertEquals(players.getPlayer(0), new Player("player", Symbol.X));
    }

    @Test
    public void testCompareNullPlayers() {
        Player p1 = null;
        Player p2 = null;
        Assert.assertEquals(p1,p2);
    }


    @Test
    public void testComparePlayersWithNullParameters() {
        Player p1 = new Player(null, null);
        Player p2 = new Player(null, null);
        Assert.assertNotEquals(p1,p2);
    }

    @Test
    public void testCompare_whenOnePlayerIsNullAndTheOtherOneHaveNoNameAndSymbol_shouldBeFalse() {
        Player p1 = null;
        Player p2 = new Player(null,null);
        Assert.assertNotEquals(p1,p2);
    }

    @Test
    public void testCompare_whenOnePlayerHaveNoNameAndSymbolAndTheOtherOneIsNull_shouldBeFalse() {
        Player p1 = new Player(null,null);
        Player p2 = null;
        Assert.assertNotEquals(p1,p2);
    }

}
