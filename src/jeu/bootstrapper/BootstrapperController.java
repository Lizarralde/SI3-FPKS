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
        bootStrapperModel = new BootStrapperModel();

        loadOrNewPlayerView = new LoadOrNewPlayerView(this);
    }

    public void setLoadOrNewPlayerView(boolean newPlayer) {
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
        onNickNameSelected(nickNameOfNewPlayer);
    }

    public void onNickNameSelected(String nickName) {
        this.bootStrapperModel.setNickName(nickName);

        if(this.profilesView != null) {
            this.profilesView.dispose();
        }
        this.selectGamePlay = new SelectGamePlayView(this);
    }

    public void launchGame(String theme, String difficulty) {

        System.out.println("Launch game with : " + theme + " - " + difficulty);

        this.bootStrapperModel.setThemeSelected(Themes.valueOf(theme.toUpperCase()));

        this.bootStrapperModel.setDifficultySelected(Difficulties.valueOf(difficulty.toUpperCase()));

        FPKSController f = new FPKSController(this.bootStrapperModel.getNickName(), this.bootStrapperModel.getThemeSelected(),
                this.bootStrapperModel.getDifficultySelected());

        this.selectGamePlay.dispose();

        f.launchGame();
    }

    public static void main(String[] args){
        new BootstrapperController();
    }

    public void showHighScores() {
        this.highScoresView = new HighScoresView(this.bootStrapperModel.sortedNickNames, this.bootStrapperModel.sortedScores);
    }
}
