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

    private ProfilesView profilesView;

    private MenuAbstrait initView;

    public BootstrapperController() {
        bootStrapperModel = new BootStrapperModel();
        ProfilesView.profilesNames = bootStrapperModel.getNickNames();
        profilesView = new ProfilesView(this);
    }

    public void onNickNameSelected(String nickName) {
        this.bootStrapperModel.setNickName(nickName);
        this.profilesView.dispose();
        this.initView = new FPKSBootstrapperInitView(this);
    }

    public void launchGame(String theme, String difficulty) {

        System.out.println("Launch game with : " + theme + " - " + difficulty);

        this.bootStrapperModel.setThemeSelected(Themes.valueOf(theme.toUpperCase()));

        this.bootStrapperModel.setDifficultySelected(Difficulties.valueOf(difficulty.toUpperCase()));

        FPKSController f = new FPKSController(this.bootStrapperModel.getNickName(), this.bootStrapperModel.getThemeSelected(),
                this.bootStrapperModel.getDifficultySelected());

        this.initView.dispose();

        f.launchGame();
    }

    public static void main(String[] args){
        new BootstrapperController();
    }
}
