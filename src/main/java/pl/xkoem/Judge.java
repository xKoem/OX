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
        Integer newestPosition = gameBoard.getNewestPosition();
        if(newestPosition == -1) {
            return false;
        }
        return checkVertical(gameBoard, newestPosition) || checkHorizontal(gameBoard, newestPosition)
                || checkDecreasingDiagonal(gameBoard,newestPosition) || checkIncreasingDiagonal(gameBoard,newestPosition);
    }

    boolean checkVertical(GameBoard gameBoard, Integer newestPosition) {
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

    boolean checkHorizontal(GameBoard gameBoard, Integer newestPosition) {
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

    boolean checkDecreasingDiagonal(GameBoard gameBoard, Integer newestPosition) {
        Integer[] position = gameBoard.translatePositionToCoordinates(newestPosition);
        Symbol symbol = gameBoard.getSymbolAtPosition(newestPosition);
        Integer signsCounter = signsToWin;

        Integer minValue = position[0] < position[1]? position[0]:position[1];

        Integer[] startingPosition = new Integer[]{position[0]-minValue, position[1]- minValue};

        for(int i = 0;
            i < ((gameBoard.getWidth() < gameBoard.getHeight())? gameBoard.getWidth(): gameBoard.getHeight());
                i++) {

            if(symbol.equals(gameBoard.getSymbolAtPosition(startingPosition[0], startingPosition[1]))) {
                signsCounter--;
            } else {
                signsCounter = signsToWin;
            }
            if(signsCounter <= 0) {
                return true;
            }
            startingPosition[0]++;
            startingPosition[1]++;
        }
        return false;
    }


    boolean checkIncreasingDiagonal(GameBoard gameBoard, Integer newestPosition) {
        Integer[] position = gameBoard.translatePositionToCoordinates(newestPosition);
        Symbol symbol = gameBoard.getSymbolAtPosition(newestPosition);
        Integer signsCounter = signsToWin;

        Integer diagonalNumber = position[0] + position[1];

        Integer[] startingPosition;
        if(diagonalNumber < gameBoard.getWidth()) {
            startingPosition = new Integer[]{diagonalNumber, 0};
        } else {
            startingPosition = new Integer[]{gameBoard.getWidth() - 1, diagonalNumber - gameBoard.getWidth() + 1};
        }

        for(int i = 0;
            i < ((gameBoard.getWidth() < gameBoard.getHeight())? gameBoard.getWidth(): gameBoard.getHeight());
            i++) {
            if(symbol.equals(gameBoard.getSymbolAtPosition(startingPosition[0], startingPosition[1]))) {
                signsCounter--;
            } else {
                signsCounter = signsToWin;
            }
            if(signsCounter <= 0) {
                return true;
            }
            startingPosition[0]--;
            startingPosition[1]++;
        }
        return false;
    }

}
