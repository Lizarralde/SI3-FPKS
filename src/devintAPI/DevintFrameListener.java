package devintAPI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import t2s.SIVOXDevint;

/**
 * classe abstraite pour un JFrame qui réagit aux événements clavier
 * 
 * les menus des jeux DeViNT et les fenétres des jeux DeViNT doivent réagir 
 * aux événements clavier de cette faéon.
 * 
 * vous pouvez surcharger les méthodes  dans les classes filles en ajoutant des
 * touches auxquelles votre jeu réagit.
 * 
 * @author helen
 *
 */
public abstract class DevintFrameListener extends JFrame implements KeyListener {

	// la voix pour annoncer les actions
    protected SIVOXDevint voix; 

	/**
	 * renvoie le nom du fichier wav é lire quand la fenétre s'ouvre
	 * Nota : le chemin est relatif au répertoire bin
	 */
	protected abstract String wavAccueil();

	/**
	 * renvoie le nom du fichier wav é lire pour l'objectif du jeu
	 * qui est lu en activant F1
	 */
	protected abstract String wavRegleJeu();
	
	
	/** 
	 * definit comment la fenétre change de couleur quand on clique sur F3
	 * Cette méthode est appelée dans la classe Preferences, sur toutes les fenétres ouvertes
	 */
	public abstract void changeColor() ;
	
	
	public DevintFrameListener(String title) {
		super(title);
    	// prend toute la taille de la fenétre
    	this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // on ferme la fenétre en cliquant sur la croix 
    	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
     	// écoute les événements clavier
       	addKeyListener(this);
       	//ajoute cette fenétre aux Frame reconnus par Preferences pour gérer 
    	// le changement de couleur/voix
    	Preferences.getData().addDevintFrame(this);
       	// on récupére la voix donnée dans les préférences
    	voix = Preferences.getData().getVoice();
 	}
	
    /** gestion des touches
     * ESC fait sortir de la fenétre courante
     * F1 invoque l'aide
     * Cette méthode peut étre surchargée par héritage pour réagir é d'autres touches
     * (voir un exemple dans la classe Jeu)
     */
    public void keyPressed(KeyEvent e) {
    	// toujours arréter la voix courante quand l'utilisateur fait une action
    	voix.stop();
    	// escape = sortir
    	if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
    		dispose();
    	}
    	// F1 = regle du jeu
    	if (e.getKeyCode()==KeyEvent.VK_F1){
    		voix.playWav(wavRegleJeu());
    	}
		// F3 = on passe é la couleur suivante dans le jeu des 
    	// couleurs défini dans Preferences
		if (e.getKeyCode() == KeyEvent.VK_F3) {
			Preferences.getData().changeColor();
		}
		// F4 = voix suivante défini dans Preferences
		if (e.getKeyCode() == KeyEvent.VK_F4) {
			Preferences.getData().changeVoice();
		}
    }
    
    // méthodes nécessaires pour l'interface KeyListener
    // é redéfinir si besoin dans les classes filles pour gérer les clics souris
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e){} {}
}


