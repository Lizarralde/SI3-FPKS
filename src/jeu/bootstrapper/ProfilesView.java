package jeu.bootstrapper;

import devintAPI.MenuAbstrait;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProfilesView extends MenuAbstrait {

    public static final String TITLE = "profiles";

    public static List<String> profilesNames = new ArrayList<String>();

    private BootstrapperController bootstrapperController;

    public ProfilesView(BootstrapperController bootstrapperController) {
        super(TITLE);
        this.bootstrapperController = bootstrapperController;
    }

    @Override
    protected String[] nomOptions() {
        if(profilesNames == null) {
            return new String[0];
        }
        String[] res = new String[profilesNames.size()];

        for (int i = 0 ; i < profilesNames.size() ; i ++) {
            res[i] = profilesNames.get(i);
        }

        return res;

    }

    @Override
    protected void lancerOption(int i) {
        this.bootstrapperController.onNickNameSelected(this.nomOptions()[optionCourante]);
    }

    @Override
    protected String wavAccueil() {
        return "../ressources/sons/accueil.wav";
    }

    @Override
    protected String wavRegleJeu() {
        return  "../ressources/sons/accueil.wav";
    }
}
