package jeu.controller;

import jeu.bootstrapper.BootStrapperView;
import jeu.bootstrapper.BootstrapperController;
import jeu.model.Difficulties;
import jeu.model.GameModel;
import jeu.model.Themes;
import jeu.view.FPKSView;
import jeu.view.GameView;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FPKSController implements ActionListener {

    private BootstrapperController bootstrapperController;

    private GameModel gameModel;

    private FPKSView fpksView;

    public FPKSController(BootstrapperController b, String nickName, Themes themeSelected, Difficulties difficultySelected) {
        this.gameModel = new GameModel(nickName, themeSelected, difficultySelected);
        this.bootstrapperController = b;
    }

    public void launchGame() {
        this.fpksView = new FPKSView(this, this.gameModel, this.gameModel.getPlayer().getNickname());

    }

    public void endGame() {
        System.out.println("\n++++++ Game Ended +++++++\n");
        this.bootstrapperController.addScore(gameModel.getTheme(), gameModel.getPlayer().getScore());
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

        /*
        this.username = this.btv.getUsername();
        this.difficulty = this.btv.getSelectedDifficulty();
        this.theme = this.btv.getSelectedTheme();

        System.out.println("Username : " + username + "\nDifficulty : " + difficulty + "\nTheme : " + theme);

        if (this.username != null && !this.username.isEmpty() && this.difficulty != null && this.theme != null) {
            this.launchGame();
        }
        */
    }

    public void replay() {
        this.bootstrapperController.onNickNameSelected(this.gameModel.getPlayer().getNickname());
    }

    public void exit() {
        this.bootstrapperController.exit();
    }
}
