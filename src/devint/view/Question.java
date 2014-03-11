package devint.view;

import java.util.List;

public class Question {

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

    public String getContent() {

        return content;
    }

    private void setContent(String content) {

        this.content = content;
    }

    public List<String> getAnswers() {

        return answers;
    }

    private void setAnswers(List<String> answers) {

        this.answers = answers;
    }

    public Question(Difficulties difficulty, Themes theme, String content,
            List<String> answers) {

        this.setDifficulty(difficulty);
        this.setTheme(theme);
        this.setContent(content);
        this.setAnswers(answers);
    }
}
