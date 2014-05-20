/** 
Cette classe abstraite est un Frame associé é une instance de voix 
 * SI_VOX et qui implémente KeyListener.
 * Elle peut servir de classe mére é toutes les fenétres de vos jeux :
 * il suffit de définir la méthode "init" pour initialiser les éléments du Frame
 */


package devintAPI;

import java.awt.event.*;


/** Classe abstraite avec un Frame, une instance de SI_VOX pour parler et 
 * qui écoute les événements clavier avec gestion des préférences.
 * Par défaut, un son est lu é l'activation de la fenétre, 
 * on sort de la fenétre par ESC et on obtient la régle du jeu par F1, l'aide par F2
 * 
 * @author helene
 *
 */
public abstract class FenetreAbstraite extends  DevintFrameListener{
   
    /**
     * @param title : titre de la fenétre
     */
    public FenetreAbstraite(String title) {
    	super(title);
       	// méthode init é implémenter, elle construit ce qui est dans le frame
       	init();
	     // visible
    	this.setVisible(true);
    	// a le focus
    	this.requestFocus();
		voix.playWav(wavAccueil());
    }

    /** méthode abstraite é implémenter 
     * pour définir ce qu'il y a dans le Frame
     */
    protected abstract void init();
    
    /** méthode abstraite é implémenter
     *  
     * @return le fichier wav contenant le message d'aide (activé par F2)
     */
    protected abstract String wavAide();

    //////////////////////////////////////////////
    // Gestion des événements clavier
    /////////////////////////////////////////////
    public void keyPressed(KeyEvent e) {
    	// gestion de ESC, F1, F3 et F4 dans la classe mére (DevintFrameListener)
    	super.keyPressed(e);
    	// on ajoute la gestion de l'aide quand on presse F2
    	if (e.getKeyCode()==KeyEvent.VK_F2){
    		voix.playWav(wavAide());
    	}
    }

	/**
	 * Pour modifier les couleurs de fond et de premier plan de la fenétre
	 * Cette fonction est appelée par la fonction "changeColor" de la classe "Preferences"
	 * é chaque fois que l'on presse F3 
	 * 
	 * Cette méthode doit étre réécrite dans les classes filles 
	 * si cela n'a pas de sens pour votre jeu, vous la redéfinissez en la laissant vide
	 **/
	public abstract void changeColor() ;
	
}

