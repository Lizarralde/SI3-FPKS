package devint.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import devint.data.Data;

public class GameModel extends Observable {

    private int index;

    private List<Question> questions;
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

        for (int i = 0; i < 10; i++) {

            this.getQuestions().add(
                    (Question) Data.load("./resources/questions/"
                            + theme.toString().toLowerCase() + "/"
                            + difficulty.toString().toLowerCase()
                            + "/question_" + i + ".xml"));
        }

        Collections.shuffle(this.getQuestions());

        this.setDifficulty(difficulty);
        this.setTheme(theme);
    }

    public void next() {

        this.setIndex(this.getIndex() + 1);

        if (this.getIndex() > 9) {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("over", new Boolean(true));
            this.setChanged();
            this.notifyObservers(map);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("question", this.getQuestions().get(this.getIndex())
                .getContent());
        map.put("answers", this.getQuestions().get(this.getIndex())
                .getAnswers());
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

    public void score() {

        this.getPlayer().score(
                this.getQuestions().get(this.getIndex()).getDifficulty()
                        .getBonus());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("score", this.getPlayer().getScore());
        this.setChanged();
        this.notifyObservers(map);
    }
}
