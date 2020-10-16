package com.krystianrymonlipinski.main_frame;

import draughts.library.boardmodel.Tile;

public class MainFramePresenter {

    Tile[][] board;

    MainFrameModel mainFrameModel;
    MainFrameView mainFrameView;

    public MainFramePresenter(MainFrameView view) {
        mainFrameModel = new MainFrameModel();
        mainFrameView = view;
    }

    public void onNewGameButtonClicked(boolean isPlayerWhite) {
        board = mainFrameModel.startGame(isPlayerWhite);
        mainFrameView.updateBoard(board);
    }

    public void onLoadMoveButtonClicked() {
        if (mainFrameModel.isMoveLegal()) {
            board = mainFrameModel.updateBoard();
            mainFrameView.updateBoard(board);
        }
        else {
            mainFrameView.showIncorrectMoveDialog();
        }
    }
}
