package com.krystianrymonlipinski.main_frame;

import draughts.library.boardmodel.Board;

public interface MainFrameView {

    void showIncorrectMoveDialog();

    void updateBoard(Board board);

    void showBestMove(String move);
}
