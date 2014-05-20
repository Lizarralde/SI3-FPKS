package jeu.controller;

import jeu.bootstrapper.BootStrapperView;
import jeu.bootstrapper.BootstrapperController;
import jeu.model.Difficulties;
import jeu.model.GameModel;
import jeu.model.Themes;
import jeu.view.FPKSView;
import jeu.view.GameView;
import jeu.view.RetryView;
import net.miginfocom.swing.MigLayout;
import t2s.SIVOXDevint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FPKSController  {

    private BootstrapperController bootstrapperController;

    private GameModel gameModel;

    private FPKSView fpksView;

    private SIVOXDevint sivox;

    private RetryView retryView;

    public FPKSController(BootstrapperController b, SIVOXDevint sivox, String nickName, Themes themeSelected, Difficulties difficultySelected) {
        this.gameModel = new GameModel(nickName, themeSelected, difficultySelected);
        this.bootstrapperController = b;
        this.sivox = sivox;
    }

    public void launchGame() {
        this.fpksView = new FPKSView(this,sivox, this.gameModel, this.gameModel.getPlayer().getNickname());

    }

    public void endGame() {
        System.out.println("\n++++++ Game Ended +++++++\n");
        this.bootstrapperController.addScore(gameModel.getTheme(), gameModel.getPlayer().getScore());
        this.fpksView.dispose();
        retryView = new RetryView(this);
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

    public void replay() {
        this.bootstrapperController.launchGame("" + this.gameModel.getTheme(), "" + this.gameModel.getDifficulty());
        retryView.dispose();
    }

    public void exit() {
        this.bootstrapperController.exit();
        retryView.dispose();
    }

    public void changeTheme() {
        this.bootstrapperController.onNickNameSelected(this.gameModel.getPlayer().getNickname());
        retryView.dispose();

    }
}
