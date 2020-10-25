package com.krystianrymonlipinski.main_frame;

import com.krystianrymonlipinski.algorithm.ChosenLevelAlreadyCalculatedException;
import com.krystianrymonlipinski.algorithm.MainAlgorithm;
import com.krystianrymonlipinski.algorithm.PositionState;
import com.krystianrymonlipinski.tree.model.Node;
import draughts.library.boardmodel.Tile;
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
        mainAlgorithm = new MainAlgorithm(ALGORITHM_DEPTH, gameEngine);
        mainAlgorithm.calculateTree();

        if (isPlayerToMove()) gameEngine.getMoveManager().findAllCorrectMoves(gameEngine.getBoardManager(),
                gameEngine.getIsWhiteToMove());

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
        for (Node<PositionState, Move<? extends Hop>> child : mainAlgorithm.getMoveTree().getCurrentNode().getChildren()) {
            if (child.getCondition().equals(loadedMove)) {
                loadedMove = child.getCondition();
                break;
            }
        }
        mainAlgorithm.getMoveTree().moveDown(loadedMove);
        gameEngine.getBoardManager().printBoard();
        return gameEngine.getBoardManager().getBoard();
    }

    public boolean isGameRunning() {
        return gameEngine.getGameState() == GameEngine.GameState.RUNNING;
    }

    public Move<? extends Hop> findBestMoveForAlgorithm() {
        return mainAlgorithm.findBestMove();
    }

    public void findPossibleMoves() {
        gameEngine.getMoveManager().findAllCorrectMoves(gameEngine.getBoardManager(),
                gameEngine.getIsWhiteToMove());
    }

    public boolean isPlayerToMove() {
        return isPlayerWhite == gameEngine.getIsWhiteToMove();
    }

    public void updateGameTree() {
        mainAlgorithm.getMoveTree().setCurrentNodeAsRoot();
        mainAlgorithm.calculateNextTreeLevel(ALGORITHM_DEPTH);
    }
}
