package com.krystianrymonlipinski.main_frame;

import draughts.library.boardmodel.Tile;
import draughts.library.exceptions.GameAlreadyEndedException;
import draughts.library.exceptions.WrongMoveException;
import draughts.library.managers.GameEngine;
import draughts.library.movemodel.Hop;
import draughts.library.movemodel.Move;

public class MainFrameModel {

    private GameEngine gameEngine;
    private Move<? extends Hop> loadedMove;
    private int moveNumber;

    private MoveFileManager moveFileManager;

    public MainFrameModel() {
        gameEngine = new GameEngine();
        loadedMove = null;
    }

    public Tile[][] startGame() {
        moveFileManager = new MoveFileManager();

        gameEngine.startGame();
        return gameEngine.getBoardManager().getBoard();
    }

    public boolean isMoveLegal() {
        MoveData moveData = moveFileManager.loadMoveData();
        boolean isLegal;
        try {
            loadedMove = gameEngine.checkIfMoveIsCorrect(moveData.getSource(),
                                                         moveData.getDestination(),
                                                         moveData.getTakenPawns());
            isLegal = true;
        } catch (WrongMoveException | GameAlreadyEndedException e) {
            e.printStackTrace();
            isLegal = false;
        }
        //moveFileManager.writeMoveResult(isLegal);
        return isLegal;
    }

    public Tile[][] updateBoard() {
        gameEngine.getBoardManager().makeWholeMove(loadedMove);
        gameEngine.finishMove(loadedMove);
        gameEngine.prepareMove(gameEngine.getIsWhiteToMove());

        return gameEngine.getBoardManager().getBoard();
    }
}
