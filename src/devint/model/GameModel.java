package devint.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class GameModel extends Observable {

    private int index;

    private Difficulties difficulty;
    private Themes theme;

    private List<Question> questions;

    public int getIndex() {

        return index;
    }

    public void setIndex(int index) {

        this.index = index;
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

    public List<Question> getQuestions() {

        return questions;
    }

    public void setQuestions(List<Question> questions) {

        this.questions = questions;
    }

    public GameModel(Themes theme, Difficulties difficulty) {

        this.setIndex(-1);
        this.setDifficulty(difficulty);
        this.setTheme(theme);
        this.setQuestions(null);
    }

    public void next() {

        this.setIndex(this.getIndex() + 1);
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
}
