package com.krystianrymonlipinski.main_frame;

import draughts.library.boardmodel.Tile;

public class MainFramePresenter {

    MainFrameModel mainFrameModel;
    MainFrameView mainFrameView;

    public MainFramePresenter(MainFrameView view) {
        mainFrameModel = new MainFrameModel();
        mainFrameView = view;
    }

    public void onNewGameButtonClicked() {
        Tile[][] board = mainFrameModel.startGame();
        mainFrameView.updateBoard(board);
    }

    public void onChosenColorChanged(boolean isWhiteColorSelected) {
        System.out.println(isWhiteColorSelected);
    }

    public void onLoadMoveButtonClicked() {
        if (mainFrameModel.isMoveLegal()) {
            System.out.println("Move is legal");
        }
        else {
            mainFrameView.showIncorrectMoveDialog();
        }
    }
}
