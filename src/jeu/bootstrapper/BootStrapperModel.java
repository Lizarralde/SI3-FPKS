package jeu.bootstrapper;

import jeu.KeysKeeper;
import jeu.model.Difficulties;
import jeu.model.Themes;
import jeu.utils.ConfigXML;

import java.security.Key;
import java.util.*;


public class BootStrapperModel {

    private Map<String, Map<Themes, Integer>> profilesDescription = new HashMap<String, Map<Themes, Integer>>();

    private LinkedHashMap<String, Map<Themes, Integer>> highscores = new LinkedHashMap<String, Map<Themes, Integer>>();
    public Map<Themes, LinkedHashMap<String, Integer>> sortedScores  = new HashMap<>();
    public Map<Themes, List<String>> sortedNickNames = new HashMap<>();

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
        //storeDefaultProfiles();
        loadProfiles();
        buildHighScores();
    }

    public void loadProfiles() {
        ConfigXML.definirDossier(KeysKeeper.PATH_RESSOURCES);
        profilesDescription = (Map<String, Map<Themes, Integer>>)ConfigXML.load(KeysKeeper.PATH_PROFILES_FILE,
                KeysKeeper.PATH_PROFILES_VERSION);
    }

    public void storeDefaultProfiles() {
        ConfigXML.definirDossier(KeysKeeper.PATH_RESSOURCES);
        Map<String, HashMap<Themes, Integer>> hs = new HashMap<>();

        HashMap<Themes, Integer> scoresFrancky = new HashMap<>();
        scoresFrancky.put(Themes.ANGLAIS, 18);
        scoresFrancky.put(Themes.MATHS, 6);
        scoresFrancky.put(Themes.GEOGRAPHIE, 13);
        scoresFrancky.put(Themes.FRANCAIS, 10);

        HashMap<Themes, Integer> scoresBenji = new HashMap<>();
        scoresBenji.put(Themes.ANGLAIS, 8);
        scoresBenji.put(Themes.MATHS, 13);
        scoresBenji.put(Themes.GEOGRAPHIE, 10);
        scoresBenji.put(Themes.FRANCAIS, 11);

        hs.put("Francky", scoresFrancky);
        hs.put("Benjamin", scoresBenji);

        ConfigXML.store(hs, KeysKeeper.PATH_PROFILES_FILE, KeysKeeper.PATH_PROFILES_VERSION);
    }

    public List<String> getNickNames() {
        return new ArrayList<String>(this.profilesDescription.keySet());
    }

    public void buildHighScores() {
        if(profilesDescription == null || profilesDescription.size() == 0) {
            return;
        }

        ScoreComparator.getSortedScores(profilesDescription);
        this.sortedNickNames = ScoreComparator.sortedNN;
        this.sortedScores = ScoreComparator.result;

    }

    public Integer getScore(Themes t, String nickName) {
        return this.sortedScores.get(t).get(nickName);
    }

    public List<String> getSortedNickNames(Themes t) {
        return this.sortedNickNames.get(t);
    }

    public void createNewPlayer(String text) {
        this.profilesDescription.put(text, new HashMap<Themes, Integer>());
        setNickName(text);
    }

    public void addScore(Themes t, Integer score) {
        System.out.println("Ajout score " + score + " au theme " + t + " pour le joueur " + nickName);
        Map<Themes, Integer> tmp = this.profilesDescription.get(nickName);

        if(tmp == null) {
            tmp = new HashMap<>();
        }

        if(tmp.get(t) != null && tmp.get(t) < score) {
            tmp.put(t, score);
        }
        else if(tmp.get(t) == null) {
            tmp.put(t, score);
        }

        this.profilesDescription.put(nickName, tmp);

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

    public LinkedHashMap<String, Map<Themes, Integer>> getHighscores() {
        return highscores;
    }

    public void setHighscores(LinkedHashMap<String, Map<Themes, Integer>> highscores) {
        this.highscores = highscores;
    }

    public void storeProfiles() {
        ConfigXML.definirDossier(KeysKeeper.PATH_RESSOURCES);
        ConfigXML.store(this.profilesDescription, KeysKeeper.PATH_PROFILES_FILE, KeysKeeper.PATH_PROFILES_VERSION);
    }
}