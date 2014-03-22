package devint.model;

import java.util.List;
import java.util.Observable;

public class Question extends Observable {

    private Difficulties difficulty;
    private Themes theme;

    private List<String> answers;
    private String content;

    public Difficulties getDifficulty() {

        return difficulty;
    }

    private void setDifficulty(Difficulties difficulty) {

        this.difficulty = difficulty;
    }

    public Themes getTheme() {

        return theme;
    }

    private void setTheme(Themes theme) {

        this.theme = theme;
    }

    public List<String> getAnswers() {

        return answers;
    }

    private void setAnswers(List<String> answers) {

        this.answers = answers;
    }

    public String getContent() {

        return content;
    }

    private void setContent(String content) {

        this.content = content;
    }

    public Question(Difficulties difficulty, Themes theme, String content,
            List<String> answers) {

        this.setDifficulty(difficulty);
        this.setTheme(theme);
        this.setAnswers(answers);
        this.setContent(content);
    }
}
