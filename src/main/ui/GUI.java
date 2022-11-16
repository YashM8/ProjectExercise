package ui;

import model.Exercise;
import model.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI {
    private User u1;
    private Interface inter = new Interface();


    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    GUI() throws IOException {

        // Frame
        JFrame frame = new JFrame();
        frame.setTitle("Exercise Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);
        frame.setLayout(new BorderLayout());

        // User Label
        JLabel label1 = new JLabel("New User?");
        label1.setBounds(10, 0, 200, 200);
        label1.setVerticalAlignment(JLabel.TOP);
        label1.setFont(new Font("courier", Font.BOLD, 25));

        // Exercises Label2
        JLabel label2 = new JLabel("Add Exercises");
        label2.setBounds(10, 75, 200, 200);
        label2.setFont(new Font("courier", Font.BOLD, 25));

        // Text Field User
        JTextField tfUser = new JTextField();
        tfUser.setBounds(10,100, 480,40);
        tfUser.setBackground(Color.LIGHT_GRAY);

        // Text Area Exercises
        JTextArea tfExercises = new JTextArea();
        tfExercises.setBounds(10,200, 480,280);
        tfExercises.setBackground(Color.LIGHT_GRAY);
        JPanel panelE = new JPanel();
        panelE.setBounds(10,200, 480,280);
        panelE.add(tfExercises);

        // Yes button
        JButton yes = new JButton();
        yes.setBounds(20, 40, 100, 50);
        yes.setText("Yes");
        yes.addActionListener(new ActionListener() {
            @Override
            // EFFECTS: Creates new User.
            // REQUIRES: ActionEvent.
            public void actionPerformed(ActionEvent e) {
                if (!tfUser.getText().equals("")) {
                    u1 = inter.parseAndUser(tfUser.getText());
                    tfUser.setText("New user created");
                }
            }
        });

        // No button
        JButton no = new JButton();
        no.setBounds(200, 40, 100, 50);
        no.setText("No");
        no.addActionListener(new ActionListener() {
            @Override
            // EFFECTS: Keeps original User.
            // REQUIRES: ActionEvent.
            public void actionPerformed(ActionEvent e) {
                try {
                    u1 = inter.jsonReader.read();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                inter.loadExercises();
                tfUser.setText("User Loaded");
            }
        });

        JButton add = new JButton();
        add.setBounds(20, 500, 100, 50);
        add.setText("Add");
        add.addActionListener(new ActionListener() {
            @Override
            // EFFECTS: Adds Exercises.
            // REQUIRES: ActionEvent.
            public void actionPerformed(ActionEvent e) {
                if (!tfExercises.getText().equals("")) {
                    Exercise e1 = inter.parseAndExercise(tfExercises.getText());
                    u1.addExercise(e1);
                    inter.saveExercises(u1);
                    tfExercises.setText("Exercise Added and Saved");
                }
            }
        });

        JButton remove = new JButton();
        remove.setText("Remove");
        remove.setBounds(140, 500, 100, 50);
        remove.addActionListener(new ActionListener() {
            @Override
            // EFFECTS: Removes Exercises.
            // REQUIRES: ActionEvent.
            public void actionPerformed(ActionEvent e) {
                if (!tfExercises.getText().equals("")) {
                    inter.removeWithName(u1, tfExercises.getText());
                    inter.saveExercises(u1);
                    tfExercises.setText("Exercise Removed");
                }
            }
        });

        JButton show = new JButton();
        show.setText("Show");
        show.setBounds(260, 500, 100, 50);
        show.addActionListener(new ActionListener() {
            @Override
            // EFFECTS: Shows Exercises.
            // REQUIRES: ActionEvent.
            public void actionPerformed(ActionEvent e) {
                if (u1 != null) {
                    tfExercises.setText(inter.showExercises(u1));
                    System.out.println("Show Button");
                } else {
                    tfExercises.setText("Select User");
                }
            }
        });

        ImageIcon picture = new ImageIcon("./data/MSP.png");
        JLabel picLabel = new JLabel(picture);
        picLabel.setVerticalAlignment(JLabel.BOTTOM);
        picLabel.setHorizontalAlignment(JLabel.CENTER);
        picLabel.setSize(30, 30);

        // Adding buttons
        frame.add(yes);
        frame.add(no);
        frame.add(add);
        frame.add(remove);
        frame.add(show);

        // Adding Labels
        frame.add(label1);
        frame.add(label2);

        // Adding Text Fields
        frame.add(tfUser);
        frame.add(tfExercises);

        // Adding Panel to contain
        frame.add(panelE);
        //frame.add(picLabel);

        frame.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new GUI();
    }
}