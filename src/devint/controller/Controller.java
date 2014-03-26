package devint.controller;

import devint.model.Difficulties;
import devint.model.GameModel;
import devint.model.Themes;
import devint.view.GameView;

/**
 * Created by user on 26/03/14.
 */
public class Controller {

    private GameModel gameModel;
    private GameView gameView;

    public Controller() {
        gameModel = new GameModel("Popol", Themes.MATH, Difficulties.MEDIUM);
        gameView = new GameView();

        gameModel.addObserver(gameView);
        gameModel.next();
        //gameView.setVisible(true);
    }
}
