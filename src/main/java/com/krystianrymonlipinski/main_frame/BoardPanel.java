package com.krystianrymonlipinski.main_frame;

import draughts.library.boardmodel.Board;

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

    public BoardPanel() {
        xPanelCoordinate = 0;
        yPanelCoordinate = 0;
        this.setLayout(null);
    }

    public void paintComponent(Graphics panel) {
        boardPanel = (Graphics2D) panel;
        boolean isTileWhite = true;

        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                paintTile(isTileWhite);
                if (i==5 && j==6) paintPiece(false, false);
                xPanelCoordinate += TILE_WIDTH;
                if (j<9) isTileWhite = !isTileWhite;
            }
            xPanelCoordinate = 0;
            yPanelCoordinate += TILE_HEIGHT;
        }
    }

    private void paintTile(boolean isTileWhite) {
        if (isTileWhite) boardPanel.setColor(Color.decode(WHITE_TILE_COLOR));
        else             boardPanel.setColor(Color.decode(BLACK_TILE_COLOR));

        boardPanel.fillRect(xPanelCoordinate, yPanelCoordinate, TILE_WIDTH, TILE_HEIGHT);

        if (!isTileWhite) {
            boardPanel.setColor(Color.decode(TILE_NUMBER_STRING_COLOR));
            boardPanel.drawString("12", xPanelCoordinate, yPanelCoordinate + 10);
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
