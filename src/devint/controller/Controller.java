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

        gameView = new GameView();
        frame.getContentPane().add(gameView);

        gameModel.addObserver(gameView);
        gameModel.next();

        frame.setVisible(true);
        frame.pack();
        //gameView.setVisible(true);
    }
}
