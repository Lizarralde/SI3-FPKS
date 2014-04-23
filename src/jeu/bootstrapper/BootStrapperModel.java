package jeu.bootstrapper;

import jeu.KeysKeeper;
import jeu.model.Difficulties;
import jeu.model.Themes;
import jeu.utils.ConfigXML;

import java.util.*;


public class BootStrapperModel {

    private Map<String, Integer> profilesDescription = new HashMap<String, Integer>();

    private LinkedHashMap<String, Integer> highscores = new LinkedHashMap<String, Integer>();

    public String getNickName() {
        return nickName;
    }

    public Themes getThemeSelected() {
        return themeSelected;
    }

    public Difficulties getDifficultySelected() {
        return difficultySelected;
    }

    private String nickName;

    private Themes themeSelected;

    private Difficulties difficultySelected;

    public BootStrapperModel() {
        storeDefaultProfiles();
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
        hs.put("Rachid", 2);
        hs.put("Mohamed", 2);
        hs.put("r", 2);
        hs.put("t", 2);
        hs.put("y", 2);
        hs.put("a", 2);

        ConfigXML.store(hs, KeysKeeper.PATH_PROFILES_FILE, KeysKeeper.PATH_PROFILES_VERSION);
    }

    public List<String> getNickNames() {
        return new ArrayList<String>(this.highscores.keySet());
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