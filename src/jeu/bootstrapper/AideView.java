package jeu.bootstrapper;

import devintAPI.MenuAbstrait;
import jeu.KeysKeeper;

/**
 * Created by user on 20/05/14.
 */
public class AideView extends MenuAbstrait {
    public static final String PATH_REGLES = KeysKeeper.PATH_RESSOURCES + KeysKeeper.PATH_SONS + "aideJeu.wav";

    public AideView() {
        super("Aide");
        JAnimatedIcon	ai=new JAnimatedIcon(KeysKeeper.PATH_RESSOURCES + KeysKeeper.PATH_IMG + "/demo.gif",2200);
        this.getContentPane().add(ai);

    }

    @Override
    protected String[] nomOptions() {
        return new String[0];
    }

    @Override
    protected void lancerOption(int i) {

    }

    @Override
    protected String wavAccueil() {
        return PATH_REGLES;
    }

    @Override
    protected String wavRegleJeu() {
        return PATH_REGLES;
    }
}
