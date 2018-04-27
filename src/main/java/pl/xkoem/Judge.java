package pl.xkoem;

public class Judge {

    public Integer emptySlotsOnBoard;
    private Integer signsToWin;

    public Judge(Integer boardSize, Integer signsToWin) {
        this.emptySlotsOnBoard = boardSize;
        this.signsToWin = signsToWin;
    }

    public boolean isMatchFinished() {
        return emptySlotsOnBoard < 1;
    }

    public boolean checkNewPosition(GameBoard gameBoard) {
        emptySlotsOnBoard--;
        //todo some magic
        //fake winner
        return checkVertical(gameBoard) || checkHorizontal(gameBoard);
    }

    boolean checkVertical(GameBoard gameBoard) {
        Integer newestPosition = gameBoard.getNewestPosition();
        if(newestPosition == -1) {
            return false;
        }
        Integer[] position = gameBoard.translatePositionToCoordinates(newestPosition);
        Symbol symbol = gameBoard.getSymbolAtPosition(newestPosition);
        Integer signsCounter = signsToWin;

        for(int i = 0; i < gameBoard.getHeight(); i++) {
            if(symbol.equals(gameBoard.getSymbolAtPosition(position[0], i))) {
                signsCounter--;
            } else {
                signsCounter = signsToWin;
            }
            if(signsCounter <= 0) {
                return true;
            }
        }
        return false;
    }

    boolean checkHorizontal(GameBoard gameBoard) {
        Integer newestPosition = gameBoard.getNewestPosition();
        if(newestPosition == -1) {
            return false;
        }
        Integer[] position = gameBoard.translatePositionToCoordinates(newestPosition);
        Symbol symbol = gameBoard.getSymbolAtPosition(newestPosition);
        Integer signsCounter = signsToWin;

        for(int i = 0; i < gameBoard.getWidth(); i++) {
            if(symbol.equals(gameBoard.getSymbolAtPosition(i, position[1]))) {
                signsCounter--;
            } else {
                signsCounter = signsToWin;
            }
            if(signsCounter <= 0) {
                return true;
            }
        }
        return false;
    }

}
