/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.krystianrymonlipinski.main_frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame implements MainFrameView {

    private static final int MAIN_FRAME_WIDTH = 1000;
    private static final int MAIN_FRAME_HEIGHT = 650;
    private static final int BOARD_PANEL_WIDTH = 500;
    private static final int BOARD_PANEL_HEIGHT = 500;
    private static final int CONTROL_PANEL_WIDTH = 350;
    private static final int CONTROL_PANEL_HEIGHT = 500;
    private static final int DIALOG_PANEL_WIDTH = 200;
    private static final int DIALOG_PANEL_HEIGHT = 100;

    private static final int MARGIN_WIDTH = 75;
    private static final int MARGIN_HEIGHT = 75;

    MainFramePresenter mainFramePresenter;

    JFrame mainFrame;
    JPanel contentPanel;
    BoardPanel boardPanel;
    JPanel controlPanel;

    JButton newGameButton;
    JButton loadMoveButton;
    JLabel radioGroupLabel;
    JLabel bestMoveLabel;
    ButtonGroup colorsToChoose;
    JRadioButtonMenuItem whiteColor;
    JRadioButtonMenuItem blackColor;

    public static void main(String[] args) {

        new MainFrame().onCreate();
    }

    private void onCreate() {
        mainFramePresenter = new MainFramePresenter(this);
        createFrame();
    }

    private void createFrame() {
        mainFrame = new JFrame();
        mainFrame.setLayout(null);
        mainFrame.setSize(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);

        createContentPanel();
        createBoardPanel();
        createControlPanel();

        contentPanel.add(boardPanel);
        contentPanel.add(controlPanel);

        addMarginsToFrame();
        mainFrame.getContentPane().add(contentPanel);

        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.repaint();

    }

    private JPanel createTopMargin() {
        JPanel topMargin = new JPanel();
        topMargin.setBounds(0, 0, MAIN_FRAME_WIDTH, MARGIN_HEIGHT);
        return topMargin;
    }

    private JPanel createWestMargin() {
        JPanel westMargin = new JPanel();
        westMargin.setBounds(0, 0, MARGIN_WIDTH, MAIN_FRAME_HEIGHT);
        return westMargin;
    }

    private JPanel createEastMargin() {
        JPanel eastMargin = new JPanel();
        eastMargin.setBounds( (MAIN_FRAME_WIDTH - MARGIN_WIDTH), 0, MARGIN_WIDTH, MAIN_FRAME_HEIGHT);
        return eastMargin;
    }

    private JPanel createBottomMargin() {
        JPanel bottomMargin = new JPanel();
        bottomMargin.setBounds(0, (MAIN_FRAME_HEIGHT - MARGIN_HEIGHT), MAIN_FRAME_WIDTH, MARGIN_HEIGHT);
        return bottomMargin;
    }

    private void addMarginsToFrame() {
        mainFrame.getContentPane().add(createTopMargin());
        mainFrame.getContentPane().add(createWestMargin());
        mainFrame.getContentPane().add(createEastMargin());
        mainFrame.getContentPane().add(createBottomMargin());
    }

    private void createContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setBounds(MARGIN_WIDTH, MARGIN_HEIGHT,
                MAIN_FRAME_WIDTH - MARGIN_WIDTH,
                MAIN_FRAME_HEIGHT - MARGIN_HEIGHT);
        contentPanel.setLayout(null);
    }

    private void createBoardPanel() {
        boardPanel = new BoardPanel();
        boardPanel.setBounds(0, 0, BOARD_PANEL_WIDTH, BOARD_PANEL_HEIGHT);
    }

    private void createControlPanel() {
        controlPanel = new JPanel();
        controlPanel.setBounds(BOARD_PANEL_WIDTH, 0, CONTROL_PANEL_WIDTH, CONTROL_PANEL_HEIGHT);
        controlPanel.setLayout(null);

        addControlPanelComponents();
    }

    private void addControlPanelComponents() {
        newGameButton = new JButton("New game");
        newGameButton.setBounds(75, 50, 200, 100);
        newGameButton.addActionListener(new NewGameButtonClickListener());

        radioGroupLabel = new JLabel("Choose color for player: ");
        radioGroupLabel.setBounds(50, 160, 200, 50);

        colorsToChoose = new ButtonGroup();
        whiteColor = new JRadioButtonMenuItem("white");
        blackColor = new JRadioButtonMenuItem("black");
        colorsToChoose.add(whiteColor);
        colorsToChoose.add(blackColor);
        whiteColor.setSelected(true);

        whiteColor.addActionListener(new ChosenColorChangeListener());
        blackColor.addActionListener(new ChosenColorChangeListener());

        whiteColor.setBounds(50, 200, 100, 40);
        blackColor.setBounds(50, 240, 100, 40);

        loadMoveButton = new JButton("Load move from file");
        loadMoveButton.setBounds(75, 300, 200, 100);
        loadMoveButton.addActionListener(new LoadMoveButtonClickListener());

        bestMoveLabel = new JLabel("Best move for algorithm: ");
        bestMoveLabel.setBounds(50, 425, 200, 50);

        controlPanel.add(newGameButton);
        controlPanel.add(radioGroupLabel);
        controlPanel.add(whiteColor);
        controlPanel.add(blackColor);
        controlPanel.add(loadMoveButton);
        controlPanel.add(bestMoveLabel);
    }

    public class NewGameButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFramePresenter.onNewGameButtonClicked();
        }
    }

    public class ChosenColorChangeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFramePresenter.onChosenColorChanged(whiteColor.isSelected());
        }
    }

    public class LoadMoveButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFramePresenter.onLoadMoveButtonClicked();
        }
    }


    @Override
    public void showIncorrectMoveDialog() {
        JDialog dialog = new JDialog(mainFrame, "Wrong move!", true);
        JLabel dialogLabel = new JLabel("Made move is not allowed!");
        dialog.getContentPane().add(dialogLabel);
        dialogLabel.setHorizontalAlignment(SwingConstants.CENTER);

        dialog.setBounds(MAIN_FRAME_WIDTH/2 - DIALOG_PANEL_WIDTH/2,
                         MAIN_FRAME_HEIGHT/2 - DIALOG_PANEL_HEIGHT/2,
                            DIALOG_PANEL_WIDTH, DIALOG_PANEL_HEIGHT);

        dialog.setVisible(true);

    }
}
