package devint.bootstrapper;

import devint.KeysKeeper;
import devint.model.Difficulties;
import devint.model.Themes;
import devint.utils.ConfigXML;

import java.util.*;

/**
 * Created by user on 26/03/14.
 */
public class BootStrapperModel {

    private Map<String, Integer> profilesDescription = new HashMap<String, Integer>();

    private LinkedHashMap<String, Integer> highscores = new LinkedHashMap<String, Integer>();

    private String nickName;

    private Themes themeSelected;

    private Difficulties difficultySelected;

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

        List<String> keys = new ArrayList<String>(profilesDescription.keySet());
        Collections.sort(keys, new ScoreComparator(profilesDescription));

        for(String currentKey : keys ) {
            getHighscores().put(currentKey, profilesDescription.get(currentKey));
        }
    }

    public void setDifficultySelected(Difficulties d) {
        difficultySelected = d;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setThemeSelected(Themes themeSelected) {
        this.themeSelected = themeSelected;
    }

    public LinkedHashMap<String, Integer> getHighscores() {
        return highscores;
    }

    public void setHighscores(LinkedHashMap<String, Integer> highscores) {
        this.highscores = highscores;
    }
}