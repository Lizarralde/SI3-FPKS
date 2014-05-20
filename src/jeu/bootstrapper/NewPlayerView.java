package jeu.bootstrapper;

import jeu.KeysKeeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by user on 03/05/14.
 */
public class NewPlayerView extends JFrame implements ActionListener, KeyListener {

    private BootstrapperController bootstrapperController;
    private JLabel labelInput = new JLabel("Nom du nouveau joueur : ");
    private JTextField pseudoTextField = new JTextField();
    private JButton createNewPlayerButton = new JButton("OK");

    public NewPlayerView(BootstrapperController bootstrapperController) {
        super();
        this.bootstrapperController = bootstrapperController;

        labelInput.setHorizontalTextPosition(SwingConstants.CENTER);
        labelInput.setFont(new Font("Georgia", 1, 96));

        pseudoTextField.setFont(new Font("Georgia", 1, 96));
        pseudoTextField.addKeyListener(this);

        createNewPlayerButton.setFont(new Font("Georgia", 1, 96));
        createNewPlayerButton.addActionListener(this);

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridLayout(3,1));
        contentPane.add(labelInput);
        contentPane.add(pseudoTextField);
        contentPane.add(createNewPlayerButton);

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new NewPlayerView(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.bootstrapperController.createNewPlayer(this.pseudoTextField.getText());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if(!this.pseudoTextField.getText().isEmpty()) {
            if(e.getKeyCode() == 10) {
                this.bootstrapperController.createNewPlayer(this.pseudoTextField.getText());
            }
            if(e.getKeyCode() != 8) {
                this.bootstrapperController.getVoix().playShortText("" + this.pseudoTextField.getText().charAt(this.pseudoTextField.getText().length()-1));
            }
        }
    }
}