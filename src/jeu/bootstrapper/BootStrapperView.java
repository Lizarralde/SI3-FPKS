package jeu.bootstrapper;

import jeu.model.Difficulties;
import jeu.model.Themes;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Created by user on 26/03/14.
 */
public class BootStrapperView extends JFrame implements KeyListener{
    private String username;
    private Themes theme;
    private Difficulties difficulty;

    private ComboBoxModel<Themes> themeSelector;
    private ComboBoxModel<Difficulties> difficultySelector;
    private JTextField tf;

    private Boolean isReplay;


    public BootStrapperView(ActionListener parent) {
        super();

        this.isReplay = false;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // pad,lbl,usrna,pad,lbld,diff,pad,lblt,them,pad,btng,pad
        setLayout(new MigLayout("fill", "[20][grow][20]", "[10][100][100][10][100][100][10][100][100][10][50][10]"));
        Font f = new Font("Tahoma", 1, 56);
        JLabel jl = new JLabel("NOM D'UTILISATEUR");
        jl.setHorizontalAlignment(SwingConstants.CENTER);
        jl.setFont(f);
        add(jl, "cell 1 1, grow");
        jl = new JLabel("DIFFICULTE");
        jl.setHorizontalAlignment(SwingConstants.CENTER);
        jl.setFont(f);
        add(jl, "cell 1 4");
        jl = new JLabel("THEME");
        jl.setHorizontalAlignment(SwingConstants.CENTER);
        jl.setFont(f);
        add(jl, "cell 1 7");
        this.tf = new JTextField(17);
        this.tf.setText(this.username);
        this.tf.setHorizontalAlignment(SwingConstants.CENTER);
        this.tf.setFont(f);
        add(tf, "cell 1 2, grow");
        JComboBox<Themes> comboBox = new JComboBox<>();
        this.themeSelector = new DefaultComboBoxModel<>(Themes.values());
        this.themeSelector.setSelectedItem(Themes.MATHS);
        comboBox.setModel(this.themeSelector);
        comboBox.setFont(f);
        add(comboBox, "cell 1 8, grow");
        JComboBox<Difficulties> comboBoxDiff = new JComboBox<>();
        this.difficultySelector = new DefaultComboBoxModel<>(Difficulties.values());
        this.difficultySelector.setSelectedItem(Difficulties.MOYEN);
        comboBoxDiff.setModel(this.difficultySelector);
        comboBoxDiff.setFont(f);
        comboBoxDiff.setEnabled(false);
        add(comboBoxDiff, "cell 1 5, grow");

        JButton jouer = new JButton("JOUER");
        jouer.setFont(f);
        jouer.addActionListener(parent);
        add(jouer, "cell 1 10, grow");

        setExtendedState(Frame.MAXIMIZED_BOTH);
        setFocusable(true);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);

        addKeyListener(this);
        comboBoxDiff.addKeyListener(this);
        comboBox.addKeyListener(this);
        tf.addKeyListener(this);
    }


    public String getUsername() {
        return this.tf.getText();
    }

    public Difficulties getSelectedDifficulty() {
        return (Difficulties) this.difficultySelector.getSelectedItem();
    }

    public Themes getSelectedTheme() {
        return (Themes) this.themeSelector.getSelectedItem();
    }

    public void changeColor() {
        //TODO CHANGE COLOR FROM A LIST OF PREFERENCES
        for(Component c : getContentPane().getComponents()) {
            c.setBackground(Color.red);
            c.setForeground(Color.yellow);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_F2) {
            System.out.println("CACA");
            changeColor();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
