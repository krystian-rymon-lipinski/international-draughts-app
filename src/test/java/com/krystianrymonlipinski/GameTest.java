package com.krystianrymonlipinski;

import com.krystianrymonlipinski.algorithm.geneticalgorithm.Specimen;
import com.krystianrymonlipinski.algorithm.playingalgorithm.MainAlgorithm;
import com.krystianrymonlipinski.main_frame.MainFrame;
import draughts.library.managers.GameEngine;
import draughts.library.movemodel.Hop;
import draughts.library.movemodel.Move;
import org.junit.Before;
import org.junit.Test;

import static java.lang.Thread.sleep;

public class GameTest {

    private final static int ALGORITHM_DEPTH = 4;
    private MainAlgorithm mainAlgorithm;
    private GameEngine gameEngine;

    MainFrame frame;

    @Before
    public void setUp() {
        frame = new MainFrame();
        frame.onCreate();

        gameEngine = new GameEngine();
    }

    @Test
    public void playGame_algorithmVersusAlgorithm_randomMoves() throws InterruptedException {
        gameEngine.startGame();
        frame.updateBoard(gameEngine.getBoardManager().getBoard());

        mainAlgorithm = new MainAlgorithm(ALGORITHM_DEPTH, gameEngine);
        mainAlgorithm.calculateTree(null);

        sleep(1000);

        while(gameEngine.getGameState() == GameEngine.GameState.RUNNING) {
            int numberOfMoves = mainAlgorithm.getMoveTree().getCurrentNode().getChildren().size();
            int moveIndex = (int) (numberOfMoves * Math.random());
            Move<? extends Hop> move = mainAlgorithm.getMoveTree().getCurrentNode().getChildren().get(moveIndex).getCondition();

            mainAlgorithm.getMoveTree().moveDown(move);
            mainAlgorithm.getMoveTree().setCurrentNodeAsRoot();
            int levelToCalculate = ALGORITHM_DEPTH + mainAlgorithm.getMoveTree().getRoot().getLevel();
            mainAlgorithm.calculateNextTreeLevel(levelToCalculate, null);

            frame.updateBoard(gameEngine.getBoardManager().getBoard());
            sleep(20);
        }

        sleep(1000);
    }

    @Test
    public void playGame_algorithmVersusAlgorithm_bestMoves() throws InterruptedException {
        gameEngine.startGame();
        frame.updateBoard(gameEngine.getBoardManager().getBoard());

        sleep(1000);

        mainAlgorithm = new MainAlgorithm(ALGORITHM_DEPTH, gameEngine);
        mainAlgorithm.calculateTree(null);

        while(gameEngine.getGameState() == GameEngine.GameState.RUNNING) {
            Move<? extends Hop> move = mainAlgorithm.findBestMove();

            mainAlgorithm.getMoveTree().moveDown(move);
            mainAlgorithm.getMoveTree().setCurrentNodeAsRoot();
            int levelToCalculate = ALGORITHM_DEPTH + mainAlgorithm.getMoveTree().getRoot().getLevel();
            mainAlgorithm.calculateNextTreeLevel(levelToCalculate, null);

            frame.updateBoard(gameEngine.getBoardManager().getBoard());
            sleep(20);
        }
    }

    @Test
    public void playGame_algorithmVersusAlgorithm_bestMoves_withWeights() throws InterruptedException {
        Specimen first = new Specimen(200);
        Specimen second = new Specimen(300);

        GameEngine gameEngine = new GameEngine();
        gameEngine.startGame();
        frame.updateBoard(gameEngine.getBoardManager().getBoard());

        boolean firstToMove = true;

        MainAlgorithm mainAlgorithm = new MainAlgorithm(ALGORITHM_DEPTH, gameEngine);
        mainAlgorithm.calculateTree(first);

        while(gameEngine.getGameState() == GameEngine.GameState.RUNNING) {
            Move<? extends Hop> move = mainAlgorithm.findBestMove();

            mainAlgorithm.getMoveTree().moveDown(move);
            mainAlgorithm.getMoveTree().setCurrentNodeAsRoot();

            firstToMove = !firstToMove;
            int levelToCalculate = ALGORITHM_DEPTH + mainAlgorithm.getMoveTree().getRoot().getLevel();
            if (firstToMove) {
                mainAlgorithm.calculateNextTreeLevel(levelToCalculate, first);
            } else {
                mainAlgorithm.calculateNextTreeLevel(levelToCalculate, second);
            }

            frame.updateBoard(gameEngine.getBoardManager().getBoard());
            sleep(40);
        }
    }
}
