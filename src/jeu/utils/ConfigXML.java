package jeu.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Classe d'enregistrement de fichiers de configuration au format XML
 * 
 * @author F. Dechavanne
 * @version 1.0.0
 * 
 */
public abstract class ConfigXML {
    // Dossier de sauvegarde
    //
    private static String dossier = "Config/";

    // XStream
    //
    private static XStream xstream = new XStream(new DomDriver());

    /**
     * Enregistre une liste au format XML
     * 
     * @param liste
     *            : la liste a enregistrer
     * @param nom
     *            : le nom de fichier a utiliser
     * @param version
     *            : la version du fichier
     * @return succ�s de l'op�ration
     */
    public static boolean store(List<?> liste, String nom, String version) {
        // Controler la validit� des param�tres
        //
        if (liste == null || nom == null || version == null) {
            // Indiquer l'erreur
            //
            System.err.println("ConfigXML : Param�tre null !");
            return false;
        }

        // Construire le nom de fichier a utiliser
        //
        String nomCompletFichier = dossier + nom + "-" + version + ".xml";

        verifierExistenceDossier();

        // Cr�er le fichier logique
        //
        File fichier = new File(nomCompletFichier);

        // Cr�er un �crivain
        //
        FileWriter ecrivain;
        try {
            ecrivain = new FileWriter(fichier);
        } catch (Exception e) {
            System.err
                    .println("ConfigXML :  erreur a la creation de l'ecrivain !");
            return false;
        }

        // Enregistrer le XML
        //
        xstream.toXML(liste, ecrivain);

        // Fermer le flux
        //
        try {
            ecrivain.close();
        } catch (Exception e) {
            System.err
                    .println("ConfigXML :  erreur a la fermeture de l'ecrivain !");
            return false;
        }

        // Afficher un message
        //
        System.out.println("ConfigXML : enregistrement du fichier "
                + nomCompletFichier + " OK");
        return true;
    }

    /**
     * Enregistre un dictionnaire au format XML
     * 
     * @param dictionnaire
     *            : le dictionnaire a enregistrer
     * @param nom
     *            : le nom de fichier a utiliser
     * @param version
     *            : la version du fichier
     * @return succ�s de l'op�ration
     */
    public static boolean store(Map<?, ?> dictionnaire, String nom,
            String version) {
        // Controler la validit� des param�tres
        //
        if (dictionnaire == null || nom == null || version == null) {
            // Indiquer l'erreur
            //
            System.err.println("ConfigXML : Param�tre null !");
            return false;
        }

        // Construire le nom de fichier a utiliser
        //
        String nomCompletFichier = dossier + nom + "-" + version + ".xml";

        verifierExistenceDossier();

        // Cr�er le fichier logique
        //
        File fichier = new File(nomCompletFichier);

        // Cr�er un �crivain
        //
        FileWriter ecrivain;
        try {
            ecrivain = new FileWriter(fichier);
        } catch (Exception e) {
            System.err
                    .println("ConfigXML :  erreur a la creation de l'ecrivain !\n" + e);
            return false;
        }

        // Enregistrer le XML
        //
        xstream.toXML(dictionnaire, ecrivain);

        // Fermer le flux
        //
        try {
            ecrivain.close();
        } catch (Exception e) {
            System.err
                    .println("ConfigXML :  erreur a la fermeture de l'ecrivain !");
            return false;
        }

        // Afficher un message
        //
        System.out.println("ConfigXML : enregistrement du fichier "
                + nomCompletFichier + " OK");
        return true;
    }

    /**
     * Charge une liste ou un dictionnaire enregistr� au format XML
     * 
     * @param nom
     *            : le nom de fichier a charger
     * @param version
     *            : la version du fichier
     * @return le dictionnaire ou la liste charg�e, null si une erreur est
     *         survenue
     */
    public static Object load(String nom, String version) {
        // Controler la validit� des param�tres
        //
        if (nom == null || version == null) {
            // Indiquer l'erreur
            //
            System.err.println("ConfigXML : Param�tre null !");
            return false;
        }

        // Construire le nom de fichier a utiliser
        //
        String nomCompletFichier = dossier + nom + "-" + version + ".xml";

        // Cr�er le fichier logique
        //
        File fichier = new File(nomCompletFichier);

        // Cr�er un �crivain
        //
        FileReader lecteur;
        try {
            lecteur = new FileReader(fichier);
        } catch (Exception e) {
            System.err
                    .println("ConfigXML :  erreur a la creation du lecteur !");
            return null;
        }

        // Enregistrer le XML
        //
        Object objet = xstream.fromXML(lecteur);

        // Fermer le flux
        //
        try {
            lecteur.close();
        } catch (Exception e) {
            System.err
                    .println("ConfigXML :  erreur a la fermeture du lecteur !");
            return null;
        }

        // Afficher un message
        //
        System.out.println("ConfigXML : Chargement du fichier "
                + nomCompletFichier + " OK");

        return objet;
    }
    
    public static Object load(String path) {
    	return load(path.split("-")[0], (path.split("-")[1]).substring(0, path.split("-")[1].length() - 4));
    }

    /**
     * D�finit le dossier a utiliser pour sauvegarder
     * 
     * @param nouveauDossier
     *            : le nouveau dossier de sauvegarde
     */
    public static void definirDossier(String nouveauDossier) {
        dossier = nouveauDossier;
    }

    /**
     * Donne le dossier de sauvegarde utilis� par la classe
     * 
     * @return le dossier de sauvegarde actuel
     */
    public static String recupererDossier() {
        return dossier;
    }

    private static void verifierExistenceDossier() {
        File dossierCible = new File(dossier);

        if (!(dossierCible.exists() && dossierCible.isDirectory())) {
            dossierCible.mkdirs();
        }
    }
}
