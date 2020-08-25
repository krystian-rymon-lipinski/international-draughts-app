package com.krystianrymonlipinski.main_frame;

import draughts.library.boardmodel.Board;
import draughts.library.boardmodel.Tile;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    private static final int BOARD_PANEL_WIDTH = 500;
    private static final int BOARD_PANEL_HEIGHT = 500;

    private static final int TILE_WIDTH = BOARD_PANEL_WIDTH/10;
    private static final int TILE_HEIGHT = BOARD_PANEL_HEIGHT/10;
    private static final int PIECE_SIZE = 30;
    private static final int QUEEN_MARK_SIZE = 15;

    private static final String WHITE_TILE_COLOR = "#dccccc";
    private static final String BLACK_TILE_COLOR = "#976666";
    private static final String WHITE_PIECE_COLOR = "0xFFFFFF";
    private static final String BLACK_PIECE_COLOR = "0x000000";
    private static final String QUEEN_MARK_COLOR = "0xee2222";
    private static final String TILE_NUMBER_STRING_COLOR = "0xFFFFFF";

    private Graphics2D boardPanel;
    private int xPanelCoordinate;
    private int yPanelCoordinate;

    private Tile[][] board;

    public BoardPanel() {
        xPanelCoordinate = 0;
        yPanelCoordinate = 0;

        this.setLayout(null);
    }

    public void setBoard(Tile[][] board) {
        this.board = board;
    }

    public void paintComponent(Graphics panel) {
        boardPanel = (Graphics2D) panel;
        if (board != null) {
            for (int i=0; i<board.length; i++) {
                for (int j=0; j<board[i].length; j++) {
                    switch (board[i][j].getState()) {
                        case WHITE_TILE:
                            paintTile(true, board[i][j].getIndex());
                            break;
                        case EMPTY:
                            paintTile(false, board[i][j].getIndex());
                            break;
                        case WHITE_PAWN:
                            paintTile(false, board[i][j].getIndex());
                            paintPiece(true, false);
                            break;
                        case BLACK_PAWN:
                            paintTile(false, board[i][j].getIndex());
                            paintPiece(false, false);
                            break;
                        case WHITE_QUEEN:
                            paintTile(false, board[i][j].getIndex());
                            paintPiece(true, true);
                            break;
                        case BLACK_QUEEN:
                            paintTile(false, board[i][j].getIndex());
                            paintPiece(false, true);
                            break;
                    }
                    xPanelCoordinate += TILE_WIDTH;
                }
                xPanelCoordinate = 0;
                yPanelCoordinate += TILE_HEIGHT;
            }
        }

    }

    private void paintTile(boolean isTileWhite, int tileNumber) {
        if (isTileWhite) boardPanel.setColor(Color.decode(WHITE_TILE_COLOR));
        else             boardPanel.setColor(Color.decode(BLACK_TILE_COLOR));

        boardPanel.fillRect(xPanelCoordinate, yPanelCoordinate, TILE_WIDTH, TILE_HEIGHT);

        if (!isTileWhite) {
            boardPanel.setColor(Color.decode(TILE_NUMBER_STRING_COLOR));
            boardPanel.drawString(String.valueOf(tileNumber), xPanelCoordinate, yPanelCoordinate + 10);
        }


    }

    private void paintPiece(boolean isPieceWhite, boolean isPieceQueen) {
        if (isPieceWhite) boardPanel.setColor(Color.decode(WHITE_PIECE_COLOR));
        else              boardPanel.setColor(Color.decode(BLACK_PIECE_COLOR));

        boardPanel.fillOval(xPanelCoordinate + TILE_WIDTH/2 - PIECE_SIZE/2,
                            yPanelCoordinate + TILE_HEIGHT/2 - PIECE_SIZE/2,
                               PIECE_SIZE, PIECE_SIZE);


        if (isPieceQueen) {
            boardPanel.setColor(Color.decode(QUEEN_MARK_COLOR));
            boardPanel.fillOval(xPanelCoordinate + TILE_WIDTH/2 - QUEEN_MARK_SIZE/2,
                                yPanelCoordinate + TILE_HEIGHT/2 - QUEEN_MARK_SIZE/2,
                                   QUEEN_MARK_SIZE, QUEEN_MARK_SIZE);
        }
    }
}
