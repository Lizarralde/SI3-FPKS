package jeu.model;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class Generator extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JTextField textField_1;

    public Generator() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {

            e.printStackTrace();
        }

        setSize(new Dimension(300, 500));

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new MigLayout("", "[40][80.00][80][grow]",
                "[50.00][][][][][][][][][][][][][][][][]"));

        JLabel lblNewLabel = new JLabel("Th\u00E8me : ");
        panel.add(lblNewLabel, "cell 1 0,alignx trailing");

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(Themes.values()));
        panel.add(comboBox, "cell 2 0,growx");

        JLabel lblNewLabel_1 = new JLabel("Difficult\u00E9 : ");
        panel.add(lblNewLabel_1, "cell 1 1,alignx trailing");

        JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setModel(new DefaultComboBoxModel(Difficulties.values()));
        panel.add(comboBox_1, "cell 2 1,growx");

        JLabel lblNewLabel_2 = new JLabel("Question : ");
        panel.add(lblNewLabel_2, "cell 1 3");

        textField = new JTextField();
        panel.add(textField, "cell 1 4 2 1,growx");
        textField.setColumns(10);

        JLabel lblRponse = new JLabel("R\u00E9ponse : ");
        panel.add(lblRponse, "cell 1 6");

        textField_1 = new JTextField();
        panel.add(textField_1, "cell 1 7 2 1,growx");
        textField_1.setColumns(10);

        JButton btnCrer = new JButton("Cr\u00E9er");
        panel.add(btnCrer, "cell 1 16,growx");

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                System.exit(0);
            }
        });
        panel.add(btnAnnuler, "cell 2 16,growx");
    }

    public static void main(String[] args) {

        new Generator().setVisible(true);
    }
}
