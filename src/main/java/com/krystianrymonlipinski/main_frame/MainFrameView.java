package com.krystianrymonlipinski.main_frame;

import draughts.library.boardmodel.Tile;

public interface MainFrameView {

    void showIncorrectMoveDialog();

    void updateBoard(Tile[][] board);

    void showBestMove(String move);
}
