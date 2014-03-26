package devint.bootstrapper;

import devint.KeysKeeper;
import devint.config.Config;
import devint.utils.ConfigXML;

import java.security.Key;
import java.util.*;

/**
 * Created by user on 26/03/14.
 */
public class BootStrapperModel {

    private Map<String, Integer> profilesDescription = new HashMap<String, Integer>();

    private LinkedHashMap<String, Integer> highscores = new LinkedHashMap<String, Integer>();

    public BootStrapperModel() {
        loadProfiles();
        buildHighScores();
    }

    public void loadProfiles() {
        ConfigXML.definirDossier(KeysKeeper.PATH_RESSOURCES);
        profilesDescription = (Map<String, Integer>)ConfigXML.load(KeysKeeper.PATH_PROFILES_FILE,
                KeysKeeper.PATH_PROFILES_VERSION);
    }

    public void storeDefaultProfiles() {
        ConfigXML.definirDossier(KeysKeeper.PATH_RESSOURCES);
        Map<String, Integer> hs = new HashMap<>();
        hs.put("Francky", 1);
        hs.put("Benjamin", 3);
        hs.put("Dorian", 2);

        ConfigXML.store(hs, KeysKeeper.PATH_PROFILES_FILE, KeysKeeper.PATH_PROFILES_VERSION);
    }

    public void buildHighScores() {
        if(profilesDescription == null || profilesDescription.size() == 0) {
            return;
        }

        List<String> cles = new ArrayList<String>(profilesDescription.keySet());
        Collections.sort(cles, new ScoreComparator(profilesDescription));

        System.out.println("HGSC : " + cles);
    }
}