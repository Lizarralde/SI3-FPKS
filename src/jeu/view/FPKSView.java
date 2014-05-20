package jeu.view;

import jeu.controller.FPKSController;
import jeu.model.GameModel;
import t2s.SIVOXDevint;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

/**
 * Created by user on 21/04/14.
 */
public class FPKSView extends JFrame {
    public GameView getGameView() {
        return gameView;
    }

    private GameView gameView;

    private FPKSController controller;



    public FPKSView(FPKSController controller, SIVOXDevint sivox, Observable o, String nickName) {
        super("FPKS - " + nickName);

        this.controller = controller;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        AudioPlayerThread.sivox = sivox;

        this.gameView = new GameView(this.controller);
        getContentPane().add(gameView);

        o.addObserver(this.gameView);
        ((GameModel)o).next();

        setVisible(true);
        pack();

        setExtendedState(Frame.MAXIMIZED_BOTH);
        setFocusable(true);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);

        this.gameView.initalizeMouseInput();
        this.gameView.initializeFrameRefreshThread();
    }
}
