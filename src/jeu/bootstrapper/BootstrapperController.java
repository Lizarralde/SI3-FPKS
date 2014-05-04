package jeu.bootstrapper;

import devintAPI.MenuAbstrait;
import jeu.controller.FPKSController;
import jeu.model.Difficulties;
import jeu.model.Themes;

/**
 * Created by user on 19/04/14.
 */
public class BootstrapperController {

    private BootStrapperModel bootStrapperModel;

    private MenuAbstrait loadOrNewPlayerView;
    private HighScoresView highScoresView;
    private ProfilesView profilesView;
    private NewPlayerView newPlayerView;

    private MenuAbstrait selectGamePlay;

    public BootstrapperController() {
        if( bootStrapperModel == null) {
            bootStrapperModel = new BootStrapperModel();
        }

        loadOrNewPlayerView = new LoadOrNewPlayerView(this);
    }

    public void setLoadOrNewPlayerView(boolean newPlayer) {
        this.loadOrNewPlayerView.dispose();
        if(newPlayer) {
            newPlayerView = new NewPlayerView(this);
        }
        else {
            ProfilesView.profilesNames = bootStrapperModel.getNickNames();
            profilesView = new ProfilesView(this);
        }
    }

    public void createNewPlayer(String nickNameOfNewPlayer) {
        this.bootStrapperModel.createNewPlayer(nickNameOfNewPlayer);

        this.newPlayerView.dispose();
        this.loadOrNewPlayerView.dispose();

        onNickNameSelected(nickNameOfNewPlayer);
    }

    public void onNickNameSelected(String nickName) {
        this.bootStrapperModel.setNickName(nickName);

        if(this.loadOrNewPlayerView != null) {
            this.loadOrNewPlayerView.dispose();
        }

        if(this.profilesView != null) {
            this.profilesView.dispose();
        }

        this.selectGamePlay = new SelectGamePlayView(this);
    }

    public void launchGame(String theme, String difficulty) {

        this.selectGamePlay.dispose();

        System.out.println("Launch game with : " + theme + " - " + difficulty + " _ " + getNickName());

        this.bootStrapperModel.setThemeSelected(Themes.valueOf(theme.toUpperCase()));

        this.bootStrapperModel.setDifficultySelected(Difficulties.valueOf(difficulty.toUpperCase()));

        FPKSController f = new FPKSController(this,this.bootStrapperModel.getNickName(), this.bootStrapperModel.getThemeSelected(),
                this.bootStrapperModel.getDifficultySelected());

        this.selectGamePlay.dispose();

        f.launchGame();
    }

    public void showHighScores() {
        this.highScoresView = new HighScoresView(this.bootStrapperModel.sortedNickNames, this.bootStrapperModel.sortedScores);
    }

    public String getNickName() {
        return this.bootStrapperModel.getNickName();
    }

    public void addScore(Themes t, Integer score) {
        this.bootStrapperModel.addScore(t, score);
        this.bootStrapperModel.storeProfiles();
    }

    public void exit() {
        this.bootStrapperModel.storeProfiles();
    }
}
