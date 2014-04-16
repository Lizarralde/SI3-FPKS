package devint.bootstrapper;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;

/**
 * Created by user on 16/04/14.
 */
public class HighScoresView extends JFrame {
    public static final String TITLE_HIGH = "HighScores";
    public static final String TITLE_NICK_NAME = "Pseudonyme";
    public static final String TITLE_THEME = "Thème";
    public static final String TITLE_DIFFICULTY = "Difficulté";

    public HighScoresView() {
        super();

        this.setVisible(true);
        pack();
    }


    public void showHighScoresView(LinkedHashMap<String, Integer> highscores) {
        this.getContentPane().removeAll();
        this.getContentPane().add(buildHighScoresView(highscores));
    }

    private Component buildHighScoresView(LinkedHashMap<String, Integer> highscores) {
        JPanel highScoresView = new JPanel();

        highScoresView.setLayout(new GridLayout(3,1));

        for(String key : highscores.keySet()) {
            highScoresView.add(new JLabel(key + " :" + highscores.get(key)));
        }

        return highScoresView;
    }
}
