package devint.controller;

import com.sun.corba.se.spi.orbutil.fsm.ActionBase;
import devint.bootstrapper.BootStrapperView;
import devint.model.Difficulties;
import devint.model.GameModel;
import devint.model.Themes;
import devint.view.GameView;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Controller implements ActionListener {

    private GameModel gameModel;
    private GameView gameView;
    private JFrame frame;

    private String username;
    private Themes theme;
    private Difficulties difficulty;

    private ComboBoxModel<Themes> themeSelector;
    private ComboBoxModel<Difficulties> difficultySelector;
    private JTextField tf;

    private Boolean isReplay;

    private BootStrapperView btv;

    public Controller() {
        this.username = "";
        btv = new BootStrapperView(this);
    }

    public void launchGame() {
        System.out.println("LOIUY");
        this.gameModel = new GameModel(this.username, this.theme, this.difficulty);

        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.gameView = new GameView(this);
        this.frame.getContentPane().add(gameView);

        this.gameModel.addObserver(this.gameView);
        this.gameModel.next();

        this.frame.setVisible(true);
        this.frame.pack();

        this.frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.frame.setFocusable(true);
        this.frame.pack();

        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

        this.gameView.initalizeMouseInput();
        this.gameView.initializeFrameRefreshThread();
    }

    public void endGame() {
        System.out.println("\n++++++ Game Ended +++++++\n");
        //TODO replay
    }

    public void validate(String answer) {
        this.gameModel.validate(answer);
    }

    public void next() {
        this.gameModel.bonus();
        this.gameModel.next();
    }

    public void malus() {
        this.gameModel.malus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.username = this.btv.getUsername();
        this.difficulty = this.btv.getSelectedDifficulty();
        this.theme = this.btv.getSelectedTheme();

        System.out.println("Username : " + username + "\nDifficulty : " + difficulty + "\nTheme : " + theme);

        if (this.username != null && !this.username.isEmpty() && this.difficulty != null && this.theme != null) {
            this.launchGame();
        }
    }
}
