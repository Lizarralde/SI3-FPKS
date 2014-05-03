package jeu;

import jeu.bootstrapper.BootstrapperController;

import java.util.Locale;

public class LancementJeu {

    private BootstrapperController bootstrapperController;
    private Object gameController;

    public LancementJeu() {
        bootstrapperController = new BootstrapperController();
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.FRANCE);
        new LancementJeu();
    }
}
