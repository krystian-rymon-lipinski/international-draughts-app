package com.krystianrymonlipinski.main_frame;

import draughts.library.boardmodel.Tile;
import draughts.library.exceptions.GameAlreadyEndedException;
import draughts.library.exceptions.WrongMoveException;
import draughts.library.managers.GameEngine;
import draughts.library.movemodel.Hop;
import draughts.library.movemodel.Move;

public class MainFrameModel {

    private GameEngine gameEngine;
    private int moveNumber;

    private MoveFileManager moveFileManager;

    public MainFrameModel() {
        gameEngine = new GameEngine();
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
            gameEngine.checkIfMoveIsCorrect(moveData.getSource(),
                                            moveData.getDestination(),
                                            moveData.getTakenPawns());
            isLegal = true;
        } catch (WrongMoveException | GameAlreadyEndedException e) {
            e.printStackTrace();
            isLegal = false;
        }
        moveFileManager.writeMoveResult(isLegal);
        return isLegal;
    }
}
