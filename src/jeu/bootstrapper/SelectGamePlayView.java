/*  Classe de menu de lancement de l'exemple de jeu.
 *  Cette classe h�rite de la classe abstraite MenuAbstrait en d�finissant les m�thodes :
 *     - nomOptions qui renvoie la liste des options possibles pour le menu 
 *     - lancerOption qui associe une action � chaque option du menu
 *     - wavAccueil() qui renvoie le nom du fichier wav lu lors de l'accueil dans le menu
 *     - wavAide() qui renvoie le nom du fichier wav lu lors de l'activation de la touche F1
 */

package jeu.bootstrapper;

import devintAPI.MenuAbstrait;
import jeu.KeysKeeper;
import jeu.model.Themes;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectGamePlayView extends MenuAbstrait {

    public static final String DEFAULT_TITLE = "FPKS";
    public static final String PATH_SON = KeysKeeper.PATH_RESSOURCES + KeysKeeper.PATH_SONS + "theme_ini.wav";
    public static final String PATH_RULES = KeysKeeper.PATH_RESSOURCES + KeysKeeper.PATH_SONS + "aideJeu.wav";

    private List<String> themes = new ArrayList<String>();
    private List<String> difficulties = new ArrayList<String>();

    private BootstrapperController controller;

	public SelectGamePlayView(BootstrapperController controller) {
        super(DEFAULT_TITLE + " - " + controller.getNickName());

        themes = new ArrayList<String>();

        for(Themes t : Themes.values()) {
            themes.add(t.toString());
        }

        this.difficulties = new ArrayList(Arrays.asList("Facile", "Moyen", "Difficile"));
        this.controller = controller;
    }

	public SelectGamePlayView(BootstrapperController controller, String title, List<String> themes, List<String> difficulties) {
		super(title);
        this.themes = themes;
        this.difficulties = difficulties;
        this.controller = controller;
	}

	protected String[] nomOptions() {
		String[] noms = {"Jouer","Francais", "Facile", "Aide","Quitter"};
		return noms;
	}

	protected void lancerOption(int i) {
		switch (i){  
		    case 0 :
                String theme = boutonOption[1].getText();
                String difficulty = boutonOption[2].getText();

                controller.launchGame(theme, difficulty);break;
            case 1 :
                System.out.println("Theme");break;
            case 2 :
                System.out.println("Difficulté");break;
            case 3 :
                new AideView();System.out.println("Aide");break;
		    case 4 :
                System.exit(0);
		    default:
                System.err.println("action non d�finie");
		}
	} 

	protected  String wavAccueil() {
		return PATH_SON;
	}

	protected  String wavRegleJeu() {
		return PATH_RULES;
	}

    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        if(optionCourante == 1) {
            this.voix.stop();
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                themes.add(0,themes.get(themes.size()-1));
                themes.remove(themes.size()-1);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                themes.add(themes.get(0));
                themes.remove(0);
            }

            nomOptions[optionCourante] = themes.get(0);
            boutonOption[optionCourante].setText(themes.get(0));
            this.voix.playText(boutonOption[optionCourante].getText());
        }

        else if(optionCourante == 2) {
            this.voix.stop();
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                difficulties.add(0,difficulties.get(difficulties.size()-1));
                difficulties.remove(difficulties.size()-1);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                difficulties.add(difficulties.get(0));
                difficulties.remove(0);
            }

            nomOptions[optionCourante] = difficulties.get(0);
            boutonOption[optionCourante].setText(difficulties.get(0));
            this.voix.playText(boutonOption[optionCourante].getText());

        }


    }
}