/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.krystianrymonlipinski.main_frame;

import com.krystianrymonlipinski.algorithm.MainAlgorithm;
import com.krystianrymonlipinski.tree.model.Tree;
import draughts.library.managers.BoardManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private static final int MAIN_FRAME_WIDTH = 1000;
    private static final int MAIN_FRAME_HEIGHT = 650;
    private static final int CONTROL_PANEL_WIDTH = 300;
    private static final int CONTROL_PANEL_HEIGHT = 700;

    private static final int MARGIN_WIDTH = 75;
    private static final int MARGIN_HEIGHT = 75;

    JFrame mainFrame;
    BoardPanel boardPanel;
    JPanel controlPanel;
    JButton newGameButton;
    JButton makeMoveButton;
    JButton loadMoveButton;

    public static void main(String[] args) {
        new MainFrame().createFrame();

    }

    private void createFrame() {
        mainFrame = new JFrame();
        mainFrame.setLayout(null);

        JPanel topMargin = new JPanel();
        topMargin.setBounds(0, 0, MAIN_FRAME_WIDTH, MARGIN_HEIGHT);
        topMargin.setBackground(Color.WHITE);

        JPanel westMargin = new JPanel();
        westMargin.setBounds(0, 0, MARGIN_WIDTH, MAIN_FRAME_HEIGHT);
        westMargin.setBackground(Color.WHITE);

        JPanel eastMargin = new JPanel();
        eastMargin.setBounds( (MAIN_FRAME_WIDTH - MARGIN_WIDTH), 0, MARGIN_WIDTH, MAIN_FRAME_HEIGHT);
        eastMargin.setBackground(Color.WHITE);

        JPanel bottomMargin = new JPanel();
        bottomMargin.setBounds(0, (MAIN_FRAME_HEIGHT - MARGIN_HEIGHT), MAIN_FRAME_WIDTH, MARGIN_HEIGHT);
        bottomMargin.setBackground(Color.WHITE);

        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(MARGIN_WIDTH, MARGIN_HEIGHT,
                               MAIN_FRAME_WIDTH - MARGIN_WIDTH,
                               MAIN_FRAME_HEIGHT - MARGIN_HEIGHT);
        contentPanel.setBackground(Color.orange);

        JPanel boardPanel = new BoardPanel();
        boardPanel.setBounds(0, 0, 500, 500);

        JPanel controlPanel = new JPanel();
        controlPanel.setBounds(500, 0, 350, 500);
        controlPanel.setBackground(Color.magenta);



        contentPanel.setLayout(null);
        contentPanel.add(boardPanel);
        contentPanel.add(controlPanel);

        mainFrame.getContentPane().add(topMargin);
        mainFrame.getContentPane().add(westMargin);
        mainFrame.getContentPane().add(eastMargin);
        mainFrame.getContentPane().add(bottomMargin);
        mainFrame.getContentPane().add(contentPanel);



        mainFrame.setSize(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.repaint();

    }


    private JPanel createControlPanel() {
        controlPanel = new JPanel();
        controlPanel.setSize(CONTROL_PANEL_WIDTH, CONTROL_PANEL_HEIGHT);
        controlPanel.setBackground(Color.RED);
        return controlPanel;
    }
}
