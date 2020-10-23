package com.krystianrymonlipinski.main_frame;

import com.krystianrymonlipinski.algorithm.MainAlgorithm;
import draughts.library.boardmodel.Tile;
import draughts.library.exceptions.GameAlreadyEndedException;
import draughts.library.exceptions.WrongMoveException;
import draughts.library.managers.GameEngine;
import draughts.library.movemodel.Hop;
import draughts.library.movemodel.Move;

public class MainFrameModel {

    private static final int ALGORITHM_DEPTH = 4;

    private MainAlgorithm mainAlgorithm;
    private GameEngine gameEngine;
    private Move<? extends Hop> loadedMove;
    private boolean isPlayerWhite;
    private int moveNumber;

    private MoveFileManager moveFileManager;

    public MainFrameModel() {
        gameEngine = new GameEngine();
        loadedMove = null;
    }

    public Tile[][] startGame(boolean isPlayerWhite) {
        moveFileManager = new MoveFileManager();
        this.isPlayerWhite = isPlayerWhite;

        gameEngine.startGame();
        return gameEngine.getBoardManager().getBoard();
    }

    public boolean isMoveLegal() {
        MoveData moveData = moveFileManager.loadMoveData();
        boolean isLegal;
        try {
            loadedMove = gameEngine.getMoveManager().convertToMove(moveData.getSource(),
                                                         moveData.getDestination(),
                                                         moveData.getTakenPawns());
            isLegal = true;
        } catch (WrongMoveException e) {
            e.printStackTrace();
            isLegal = false;
        }
        //moveFileManager.writeMoveResult(isLegal);
        return isLegal;
    }

    public Tile[][] updateBoard() {
        gameEngine.getBoardManager().makeWholeMove(loadedMove);
        gameEngine.finishMove(loadedMove);
        gameEngine.getMoveManager().findAllCorrectMoves(gameEngine.getBoardManager(),
                gameEngine.getIsWhiteToMove());

        return gameEngine.getBoardManager().getBoard();
    }

    public boolean isGameRunning() {
        return gameEngine.getGameState() == GameEngine.GameState.RUNNING;
    }

    public void calculateBestMove() {
        MainAlgorithm mainAlgorithm = new MainAlgorithm(4);
    }
}
