package com.krystianrymonlipinski.main_frame;

import draughts.library.boardmodel.Tile;
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

    public void loadMoveMade() {
        MoveData moveData = moveFileManager.loadMoveData();
        System.out.println(moveData);
    }
}
