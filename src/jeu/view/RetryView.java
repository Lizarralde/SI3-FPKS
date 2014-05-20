package jeu.view;

import devintAPI.MenuAbstrait;
import jeu.controller.FPKSController;

/**
 * Created by user on 04/05/14.
 */
public class RetryView extends MenuAbstrait {

    private FPKSController fpksController;

    public RetryView(FPKSController fpksController) {
        super("Rejouer ?");
        this.fpksController = fpksController;
    }

    @Override
    protected String[] nomOptions() {
        return new String[]{"Rejouer","Changer thème", "Quitter"};
    }

    @Override
    protected void lancerOption(int i) {
        if(i == 0) {
            this.fpksController.replay();
        }
       if( i ==1) {
           this.fpksController.changeTheme();
        }
        else {
           this.fpksController.exit();
        }
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
