package pl.xkoem;

public class Judge {

    private Integer emptySlotsOnBoard;

    public Judge(Integer boardSize) {
        this.emptySlotsOnBoard = boardSize;
    }

    public boolean isMatchFinished() {
        return emptySlotsOnBoard < 1;
    }

    public void checkNewPosition(GameBoard gameBoard) {
        emptySlotsOnBoard--;
        //todo some magic
        //fake winner
    }
}
