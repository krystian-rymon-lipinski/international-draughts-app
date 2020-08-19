package com.krystianrymonlipinski.main_frame;

import draughts.library.boardmodel.Board;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    private static final int BOARD_PANEL_WIDTH = 500;
    private static final int BOARD_PANEL_HEIGHT = 500;

    private static final int TILE_WIDTH = BOARD_PANEL_WIDTH/10;
    private static final int TILE_HEIGHT = BOARD_PANEL_HEIGHT/10;

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
                xPanelCoordinate += TILE_WIDTH;
                if (j<9) isTileWhite = !isTileWhite;
            }
            xPanelCoordinate = 0;
            yPanelCoordinate += TILE_HEIGHT;
        }
    }

    private void paintTile(boolean isTileWhite) {
        if (isTileWhite) boardPanel.setColor(Color.WHITE);
        else             boardPanel.setColor(Color.BLACK);

        boardPanel.fillRect(xPanelCoordinate, yPanelCoordinate, TILE_WIDTH, TILE_HEIGHT);
    }
}
