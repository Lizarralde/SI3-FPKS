package devint.controller;

import com.sun.corba.se.spi.orbutil.fsm.ActionBase;
import devint.model.Difficulties;
import devint.model.GameModel;
import devint.model.Themes;
import devint.view.GameView;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{

    private GameModel gameModel;
    private GameView gameView;
    private JFrame frame;

    private String username;
    private Themes theme;
    private Difficulties difficulty;

    private ComboBoxModel<Themes> themeSelector;
    private ComboBoxModel<Difficulties> difficultySelector;
    private JTextField tf;

    private Boolean isReplay;

    public Controller() {
        this.isReplay = false;

        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                                                                   // pad,lbl,usrna,pad,lbld,diff,pad,lblt,them,pad,btng,pad
        this.frame.setLayout(new MigLayout("fill", "[20][grow][20]", "[10][100][100][10][100][100][10][100][100][10][50][10]"));
        Font f = new Font("Segoe UI", Font.PLAIN, 40);
        JLabel jl = new JLabel("NOM D'UTILISATEUR");
        jl.setFont(f);
        this.frame.add(jl, "cell 1 1, grow");
        jl = new JLabel("DIFFICULTE");
        jl.setFont(f);
        this.frame.add(jl, "cell 1 4");
        jl = new JLabel("THEME");
        jl.setFont(f);
        this.frame.add(jl, "cell 1 7");
        this.tf = new JTextField(17);
        this.tf.setFont(f);
        this.frame.add(tf, "cell 1 2");
        JComboBox<Themes> comboBox = new JComboBox<>();
        this.themeSelector = new DefaultComboBoxModel<>(Themes.values());
        this.themeSelector.setSelectedItem(Themes.MATH);
        comboBox.setModel(this.themeSelector);
        comboBox.setFont(f);
        this.frame.add(comboBox, "cell 1 8, grow");
        JComboBox<Difficulties> comboBoxDiff = new JComboBox<>();
        this.difficultySelector = new DefaultComboBoxModel<>(Difficulties.values());
        this.difficultySelector.setSelectedItem(Difficulties.MEDIUM);
        comboBoxDiff.setModel(this.difficultySelector);
        comboBoxDiff.setFont(f);
        this.frame.add(comboBoxDiff, "cell 1 5, grow");

        JButton jouer = new JButton("JOUER");
        jouer.setFont(f);
        jouer.addActionListener(this);
        this.frame.add(jouer, "cell 1 10, grow");

        this.frame.setSize(450, 700);
        this.frame.setVisible(true);
    }

    public void launchGame(){
        this.gameModel = new GameModel(this.username, this.theme, this.difficulty);

        this.frame.dispose();
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.gameView = new GameView(this);
        this.frame.getContentPane().add(gameView);

        this.gameModel.addObserver(this.gameView);
        this.gameModel.next();

        this.frame.setVisible(true);
        this.frame.pack();

        this.gameView.initalizeMouseInput();
        this.gameView.initializeFrameRefreshThread();
    }

    public void endGame(){
        this.isReplay = Boolean.TRUE;

        this.frame.dispose();
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.frame.setLayout(new MigLayout("fill", "[20][grow][20]", "[10][100][10][100][grow][50][10]"));
        Font f = new Font("Segoe UI", Font.PLAIN, 40);
        JLabel jl = new JLabel(this.username);
        jl.setFont(f);
        this.frame.add(jl, "cell 1 1, grow");
        jl = new JLabel("TODO SCORE points");
        jl.setFont(f);
        this.frame.add(jl, "cell 1 3");
        jl = new JLabel("THEME");
        jl.setFont(f);

        JButton jouer = new JButton("REJOUER");
        jouer.setFont(f);
        jouer.addActionListener(this);
        this.frame.add(jouer, "cell 1 5, grow");

        this.frame.setSize(450, 300);
        this.frame.setVisible(true);
    }

    public void validate(String answer){
        this.gameModel.validate(answer);
    }

    public void next() {
        this.gameModel.bonus();
        this.gameModel.next();
    }

    public void malus() {
        this.gameModel.malus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.isReplay){
            this.launchGame();
        } else {
            this.username = this.tf.getText();
            this.difficulty = (Difficulties) this.difficultySelector.getSelectedItem();
            this.theme = (Themes) this.themeSelector.getSelectedItem();

            if(this.username != null && this.difficulty != null && this.theme != null){
                this.launchGame();
            }
        }
    }
}
