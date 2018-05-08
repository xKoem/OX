package pl.xkoem;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class PlayerTests {
    Players players;
    Player player1;
    Player player2;

    @BeforeMethod
    public void setUp() {
        players = new Players();
        players.setPlayers("","");

        player1 = players.getPlayer(Symbol.O);
        player2 = players.getPlayer(Symbol.X);

    }

    public void testAddPlayer() {
        Players players = new Players();
        players.setPlayers("player", "player2");
        Assert.assertEquals(players.getPlayer(0), new Player("player", Symbol.O));
    }

    public void testCompareNullPlayers() {
        player1 = null;
        player2 = null;
        Assert.assertEquals(player1,player2);
    }

    public void testComparePlayersWithNullParameters() {
        Assert.assertNotEquals(player1,player2);
    }

    public void testCompare_whenOnePlayerIsNullAndTheOtherOneHaveNoNameAndSymbol_shouldBeFalse() {
        Assert.assertNotEquals(player1,player2);
    }

    public void testCompare_whenOnePlayerHaveNoNameAndSymbolAndTheOtherOneIsNull_shouldBeFalse() {
        Assert.assertNotEquals(player1,player2);
    }

    public void testGetOpponentToPlayer_O_shouldReturnPlayer_X() {
        Assert.assertEquals(players.getOppositePlayer(player1), player2);
    }

    public void testGetOpponentToPlayer_X_shouldReturnPlayer_X() {
        Assert.assertEquals(players.getOppositePlayer(player2), player1);
    }


}
