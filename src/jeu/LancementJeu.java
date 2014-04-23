package jeu;

import jeu.bootstrapper.BootstrapperController;

public class LancementJeu {

    private BootstrapperController bootstrapperController;
    private Object gameController;

    public LancementJeu() {
        bootstrapperController = new BootstrapperController();
    }

    public static void main(String[] args) {
        new LancementJeu();
    }
}
