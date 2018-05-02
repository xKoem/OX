package pl.xkoem;

public class Judge {

    private int emptySlotsOnBoard;
    private int signsToWin;

    public Judge(int boardSize, int signsToWin) {
        this.emptySlotsOnBoard = boardSize;
        this.signsToWin = signsToWin;
    }

    public boolean isMatchFinished() {
        return emptySlotsOnBoard < 1;
    }

    public boolean checkNewPosition(GameBoard gameBoard) {
        emptySlotsOnBoard--;
        int newestPosition = gameBoard.getNewestPosition();
        if(newestPosition == -1) {
            return false;
        }

        return checkVertical(gameBoard, newestPosition) ||
                checkHorizontal(gameBoard,newestPosition) ||
                checkDecreasingDiagonal(gameBoard,newestPosition) ||
                checkIncreasingDiagonal(gameBoard,newestPosition);
    }

    boolean checkVertical(GameBoard gameBoard, int newestPosition) {
        Symbol symbol = gameBoard.getSymbolAtPosition(newestPosition);
        int signsCounter = 0;

        for(int position = newestPosition%gameBoard.getWidth(); position < gameBoard.boardSize(); position += gameBoard.getWidth()) {
            if(symbol.equals(gameBoard.getSymbolAtPosition(position))) {
                signsCounter++;
            } else {
                signsCounter = 0;
            }
            if(signsCounter >= signsToWin) {
                return true;
            }
        }
        return false;
    }

    boolean checkHorizontal(GameBoard gameBoard, int newestPosition) {
        Symbol symbol = gameBoard.getSymbolAtPosition(newestPosition);
        int signsCounter = 0;

        int position = newestPosition - (newestPosition%gameBoard.getWidth());

        for(int i = 0; i < gameBoard.getWidth(); i++) {
            if(symbol.equals(gameBoard.getSymbolAtPosition(position))) {
                signsCounter++;
            } else {
                signsCounter = 0;
            }
            if(signsCounter >= signsToWin) {
                return true;
            }
            position++;
        }

        return false;
    }

    boolean checkDecreasingDiagonal(GameBoard gameBoard, int newestPosition) {

        Symbol symbol = gameBoard.getSymbolAtPosition(newestPosition);
        int signsCounter = 0;
        int position = newestPosition;

        while(!(position < gameBoard.getWidth() || position % gameBoard.getWidth() == 0)) {
            position -= (gameBoard.getWidth() + 1);
        }
        while(position <= gameBoard.boardSize()) {
            if (symbol.equals(gameBoard.getSymbolAtPosition(position))) {
                signsCounter++;
            } else {
                signsCounter = 0;
            }
            if (signsCounter >= signsToWin) {
                return true;
            }
            if( position%gameBoard.getWidth() == (gameBoard.getWidth()-1))
                return false;

            position += (gameBoard.getWidth() + 1);
        }

        return false;
    }


    boolean checkIncreasingDiagonal(GameBoard gameBoard, int newestPosition) {
        Symbol symbol = gameBoard.getSymbolAtPosition(newestPosition);
        int signsCounter = 0;
        int position = newestPosition;

        while(!(position < gameBoard.getWidth() || position % gameBoard.getWidth() == (gameBoard.getWidth()-1))) {
            position -= (gameBoard.getWidth() - 1);
        }

        while(position < gameBoard.boardSize()) {
            if (symbol.equals(gameBoard.getSymbolAtPosition(position))) {
                signsCounter++;
            } else {
                signsCounter = 0;
            }
            if (signsCounter >= signsToWin) {
                return true;
            }
            if(position % gameBoard.getWidth() == 0)
                return false;
            position += (gameBoard.getWidth() - 1);
        }

        return false;
    }

}
