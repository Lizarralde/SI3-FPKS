package jeu.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import jeu.KeysKeeper;
import jeu.data.Data;

public class GameModel extends Observable {

    private int index;

    private List<Question> questions;
    private Map<Question, String> sounds;
    private PlayerModel player;

    private Difficulties difficulty;
    private Themes theme;

    public int getIndex() {

        return index;
    }

    public void setIndex(int index) {

        this.index = index;
    }

    public List<Question> getQuestions() {

        return questions;
    }

    public void setQuestions(List<Question> questions) {

        this.questions = questions;
    }

    public Map<Question, String> getSounds() {

        return sounds;
    }

    public void setSounds(Map<Question, String> sounds) {

        this.sounds = sounds;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {

        this.player = player;
    }

    public Difficulties getDifficulty() {

        return difficulty;
    }

    public void setDifficulty(Difficulties difficulty) {

        this.difficulty = difficulty;
    }

    public Themes getTheme() {

        return theme;
    }

    public void setTheme(Themes theme) {

        this.theme = theme;
    }

    public GameModel(String nickname, Themes theme, Difficulties difficulty) {

        this.setIndex(-1);
        this.setPlayer(new PlayerModel(nickname));
        this.setQuestions(new ArrayList<Question>());
        this.setSounds(new HashMap<Question, String>());

        String path = KeysKeeper.PATH_RESSOURCES
                + KeysKeeper.PATH_QUESTION + theme.getPath().toLowerCase()
                + "/" + difficulty.getPath().toLowerCase();


        System.out.println("Path used : " + path);

        File[] files = new File(path).listFiles();



        for (int i = 0; i < files.length; i++) {

            Question q = (Question) Data.load(files[i].getAbsolutePath());
            this.getQuestions().add(q);
            this.getSounds().put(
                    q,
                    KeysKeeper.PATH_RESSOURCES + KeysKeeper.PATH_SONS
                            + theme.getPath().toLowerCase() + "/"
                            + difficulty.getPath().toLowerCase()
                            + "/question_" + i + ".wav");
        }

        Collections.shuffle(this.getQuestions());

        this.setDifficulty(difficulty);
        this.setTheme(theme);
    }

    public void next() {

        this.setIndex(this.getIndex() + 1);
        Map<String, Object> map = new HashMap<String, Object>();

        if (this.getIndex() > 9 || this.getIndex() >= this.getQuestions().size()) {

            this.setChanged();
            this.notifyObservers();
            return;
        }

        map.put("question", this.getQuestions().get(this.getIndex())
                .getContent());
        map.put("answers", this.getQuestions().get(this.getIndex())
                .getAnswers());
        map.put("path",this.getQuestions().get(this.getIndex()).getContent());
               // this.getSounds().get(this.getQuestions().get(this.getIndex())));
        this.setChanged();
        this.notifyObservers(map);
    }

    public void validate(String answer) {

        Map<String, Object> map = new HashMap<String, Object>();

        if (this.getQuestions().get(this.getIndex()).getAnswers().get(0)
                .equals(answer)) {

            map.put("state", new Boolean(true));
        } else {

            map.put("state", new Boolean(false));
        }

        this.setChanged();
        this.notifyObservers(map);
    }

    public void malus() {

        this.score(this.getQuestions().get(this.getIndex()).getDifficulty()
                .getMalus());
    }

    public void bonus() {

        this.score(this.getQuestions().get(this.getIndex()).getDifficulty()
                .getBonus());
    }

    public void score(int score) {

        this.getPlayer().score(score);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("score", score);
        this.setChanged();
        this.notifyObservers(map);
    }
}
