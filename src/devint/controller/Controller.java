package devint.controller;

import devint.model.Difficulties;
import devint.model.GameModel;
import devint.model.Themes;
import devint.view.GameView;

import javax.swing.*;

/**
 * Created by user on 26/03/14.
 */
public class Controller {

    private GameModel gameModel;
    private GameView gameView;

    public Controller() {
        gameModel = new GameModel("Popol", Themes.MATH, Difficulties.MEDIUM);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gameView = new GameView(this);
        frame.getContentPane().add(gameView);

        gameModel.addObserver(gameView);
        gameModel.next();

        frame.setVisible(true);
        frame.pack();

        gameView.initalizeMouseInput();
        gameView.initializeFrameRefreshThread();
        //gameView.setVisible(true);
    }

    public void validate(String answer){
        this.gameModel.validate(answer);
    }

    public void next() {
        this.gameModel.score();
        this.gameModel.next();
    }

    public void malus() {
        this.gameModel.malus();
    }
}
