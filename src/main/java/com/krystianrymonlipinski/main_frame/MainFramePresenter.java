package com.krystianrymonlipinski.main_frame;

import draughts.library.boardmodel.Tile;
import draughts.library.movemodel.Capture;
import draughts.library.movemodel.Hop;
import draughts.library.movemodel.Move;

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
        if (!isPlayerWhite) {
            Move<? extends Hop> bestMove = mainFrameModel.findBestMoveForAlgorithm();
            mainFrameView.showBestMove(convertBestMove(bestMove));
        }

    }

    public void onLoadMoveButtonClicked() {
        if (mainFrameModel.isMoveLegal()) {
            board = mainFrameModel.updateBoard();
            mainFrameView.updateBoard(board);
            mainFrameModel.updateGameTree();
        }
        else {
            mainFrameView.showIncorrectMoveDialog();
        }

        if (mainFrameModel.isGameRunning()) {
            mainFrameModel.findPossibleMoves();
            if (!mainFrameModel.isPlayerToMove()) {
                Move<? extends Hop> bestMove = mainFrameModel.findBestMoveForAlgorithm();
                String bestMoveString = convertBestMove(bestMove);
                mainFrameView.showBestMove(bestMoveString);
            }
        }
    }

    private String convertBestMove(Move<? extends Hop> bestMove) {
        StringBuilder builder = new StringBuilder();
        String source = String.valueOf(bestMove.getMoveSource().getIndex());
        builder.append(source).append(" \u2192 ");
        String destination = String.valueOf(bestMove.getMoveDestination().getIndex());
        builder.append(destination);
        if (bestMove.isCapture()) {
            builder.append("(");
            for (Hop hop : bestMove.getHops()) {
                Capture capture = (Capture) hop;
                builder.append(capture.getTakenPiece().getPosition().getIndex()).append(" ");
            }
            builder.append(")");
        }

        return builder.toString();
    }
}
