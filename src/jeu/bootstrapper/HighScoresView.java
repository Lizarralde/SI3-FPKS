package jeu.bootstrapper;

import jeu.model.Themes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

/**
 * Created by user on 16/04/14.
 */
public class HighScoresView extends JFrame {

    private JTabbedPane tabbedPane;


    public HighScoresView(Map<Themes, List<String>> sortedNickNames, Map<Themes, LinkedHashMap<String, Integer>> sortedScores) {

        this.getContentPane().setLayout(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();

        Iterator<Themes> iterator = sortedNickNames.keySet().iterator();
        while(iterator.hasNext()) {
            Themes currentTheme = iterator.next();
            JPanel container = new JPanel();
            container.setLayout(new GridLayout(sortedNickNames.get(currentTheme).size(),1));

            for(int i = 0 ; i < sortedNickNames.get(currentTheme).size() ; i ++) {
                String currentNickName = sortedNickNames.get(currentTheme).get(i);
                Integer currentScore = sortedScores.get(currentTheme).get(currentNickName);

                JComponent panel = makePanel("" + (i+1) , currentNickName, "" +currentScore);
                container.add(panel);
            }
            tabbedPane.addTab(currentTheme.toString(), null, container,
                    currentTheme.toString());
        }

        //Add the tabbed pane to this panel.
        add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        this.setVisible(true);
        this.pack();
    }

    private JComponent makePanel(String place, String nickName, String score) {
        JComponent panel = new JPanel(false);

        panel.setLayout(new GridLayout(1,3));

        JLabel placeLabel = new JLabel(place);
        JLabel nickNameLabel = new JLabel(nickName);
        JLabel scoreLabel = new JLabel(score);

        placeLabel.setHorizontalAlignment(JLabel.CENTER);
        nickNameLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);

        panel.add(placeLabel);
        panel.add(nickNameLabel);
        panel.add(scoreLabel);

        return panel;
    }

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
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
