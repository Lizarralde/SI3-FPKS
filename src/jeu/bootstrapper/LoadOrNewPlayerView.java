package jeu.bootstrapper;

import devintAPI.MenuAbstrait;

/**
 * Created by user on 29/04/14.
 */
public class LoadOrNewPlayerView extends MenuAbstrait {

    public static final String DEFAULT_TITLE = "Nouveau joueur ? ";
    private BootstrapperController bootstrapperController;

    public LoadOrNewPlayerView(BootstrapperController controller) {
        super(DEFAULT_TITLE);
        this.bootstrapperController = controller;
    }

    @Override
    protected String[] nomOptions() {
        return new String[]{"Nouveau joueur", "Charger joueur", "Scores"};
    }

    @Override
    protected void lancerOption(int i) {
        if(this.optionCourante == 2) {
            this.bootstrapperController.showHighScores();
            return;
        }

        this.bootstrapperController.setLoadOrNewPlayerView(this.optionCourante == 0);
    }

    @Override
    protected String wavAccueil() {
        return null;
    }

    @Override
    protected String wavRegleJeu() {
        return null;
    }
}
