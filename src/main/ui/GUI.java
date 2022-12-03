package ui;

import model.Event;
import model.EventLog;
import model.Exercise;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GUI {
    private User u1;
    private Interface interFace = new Interface();

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    GUI() throws IOException {

        // Frame
        JFrame frame = new JFrame();
        frame.setTitle("Exercise Tracker");
        frame.setFont(new Font("San Francisco", Font.PLAIN, 15));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowListener() {
            public void windowOpened(WindowEvent e) {
            }

            public void windowClosing(WindowEvent e) {
                for (Event event: EventLog.getInstance()) {
                    System.out.println(event.toString());
                }
            }

            public void windowClosed(WindowEvent e) {
            }

            public void windowIconified(WindowEvent e) {
            }

            public void windowDeiconified(WindowEvent e) {
            }

            public void windowActivated(WindowEvent e) {
            }

            public void windowDeactivated(WindowEvent e) {
            }
        });

        frame.setSize(500, 1000);
        frame.setLayout(new BorderLayout());

        // User Label
        JLabel label1 = new JLabel("New User?");
        label1.setBounds(10, 0, 200, 100);
        label1.setVerticalAlignment(JLabel.TOP);
        label1.setFont(new Font("San Francisco", Font.PLAIN, 25));

        JLabel label4 = new JLabel("Name, Height(cm), Weight(lbs)");
        label4.setBounds(10, 40, 350, 100);
        label4.setFont(new Font("San Francisco", Font.PLAIN, 15));

        // Exercises Label2
        JLabel label2 = new JLabel("Add Exercises -");
        label2.setBounds(10, 55, 200, 200);
        label2.setFont(new Font("San Francisco", Font.PLAIN, 25));

        JLabel label3 = new JLabel("Name, Sets, Reps, Weight(lbs)");
        label3.setBounds(10, 85, 400, 200);
        label3.setFont(new Font("San Francisco", Font.PLAIN, 15));

        // Text Field User
        JTextField tfUser = new JTextField();
        tfUser.setBounds(10,100, 480,40);
        tfUser.setFont(new Font("San Francisco", Font.PLAIN, 15));
        tfUser.setBackground(Color.LIGHT_GRAY);

        // Text Area Exercises
        JTextArea tfExercises = new JTextArea();
        tfExercises.setBounds(10,200, 480,280);
        tfExercises.setFont(new Font("San Francisco", Font.PLAIN, 15));
        tfExercises.setBackground(Color.LIGHT_GRAY);
        JPanel panelE = new JPanel();
        panelE.setBounds(10,200, 480,280);
        panelE.add(tfExercises);

        // Yes button
        JButton yes = new JButton();
        yes.setBounds(260, 50, 100, 50);
        yes.setText("Yes");
        yes.setFont(new Font("San Francisco", Font.PLAIN, 15));
        yes.addActionListener(new ActionListener() {
            // EFFECTS: Creates new User.
            // REQUIRES: ActionEvent.
            public void actionPerformed(ActionEvent e) {
                if (!tfUser.getText().equals("")) {
                    u1 = interFace.parseAndUser(tfUser.getText());
                    tfUser.setText("New user created");
                } else {
                    tfExercises.setText("Enter User");
                }
            }
        });

        // No button
        JButton no = new JButton();
        no.setBounds(375, 50, 100, 50);
        no.setText("No");
        no.setFont(new Font("San Francisco",Font.PLAIN, 15));
        no.addActionListener(new ActionListener() {
            // EFFECTS: Keeps original User.
            // REQUIRES: ActionEvent.
            public void actionPerformed(ActionEvent e) {
                try {
                    u1 = interFace.jsonReader.read();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                tfUser.setText("User Loaded");
            }
        });

        // Add button
        JButton add = new JButton();
        add.setBounds(20, 500, 100, 50);
        add.setText("Add & Save");
        add.setFont(new Font("San Francisco", Font.PLAIN, 15));
        add.addActionListener(new ActionListener() {
            // EFFECTS: Adds Exercises.
            // REQUIRES: ActionEvent.
            public void actionPerformed(ActionEvent e) {
                if (!tfExercises.getText().equals("")) {
                    Exercise e1 = interFace.parseAndExercise(tfExercises.getText());
                    u1.addExercise(e1);
                    //interFace.saveExercises(u1);
                    tfExercises.setText("Exercise Added");
                } else {
                    tfExercises.setText("Enter Exercise");
                }
            }
        });

        // Remove button
        JButton remove = new JButton();
        remove.setText("Remove");
        remove.setBounds(140, 500, 100, 50);
        remove.setFont(new Font("San Francisco", Font.PLAIN, 15));
        remove.addActionListener(new ActionListener() {
            // EFFECTS: Removes Exercises.
            // REQUIRES: ActionEvent.
            public void actionPerformed(ActionEvent e) {
                if (!tfExercises.getText().equals("")) {
                    if (u1 != null) {
                        interFace.removeWithName(u1, tfExercises.getText().toLowerCase().trim());
                        tfExercises.setText("Exercise Removed");
                    } else {
                        tfExercises.setText("Select New or Existing User");
                    }
                } else {
                    tfExercises.setText("Enter Exercise Name");
                }
            }
        });

        // Save Button
        JButton save = new JButton("Save");
        save.setBounds(140, 575, 100, 50);
        save.setFont(new Font("San Francisco", Font.PLAIN, 15));
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                interFace.saveExercises(u1);
            }
        });


        // Show button
        JButton show = new JButton();
        show.setText("Show");
        show.setBounds(260, 500, 100, 50);
        show.setFont(new Font("San Francisco", Font.PLAIN, 15));
        show.addActionListener(new ActionListener() {
            // EFFECTS: Shows Exercises.
            // REQUIRES: ActionEvent.
            public void actionPerformed(ActionEvent e) {
                if (u1 != null) {
                    tfExercises.setText(interFace.showExercises(u1));
                } else {
                    tfExercises.setText("Select New or Existing User");
                }
            }
        });

        // Save Button
        JButton load = new JButton("Load");
        load.setBounds(260, 575, 100, 50);
        load.setFont(new Font("San Francisco", Font.PLAIN, 15));
        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                interFace.loadExercises();
                tfExercises.setText("Saved");
            }
        });

        // Progress button
        JButton progress = new JButton();
        progress.setText("Progress");
        progress.setBounds(380, 500, 100, 50);
        progress.setFont(new Font("San Francisco", Font.PLAIN, 15));
        progress.addActionListener(new ActionListener() {
            // EFFECTS: Shows progress in Exercises.
            // REQUIRES: ActionEvent.
            public void actionPerformed(ActionEvent e) {
                if (u1 != null) {
                    String name = tfExercises.getText();
                    String toPrint = interFace.showProgress(u1, name.trim());
                    tfExercises.setText(toPrint);
                } else {
                    tfExercises.setText("Select New or Existing User");
                }
            }
        });

        // Picture
        ImageIcon image = new ImageIcon("./data/BabyLifting.jpg");
        JLabel picLabel = new JLabel();
        picLabel.setIcon(image);
        picLabel.setVerticalAlignment(JLabel.BOTTOM);
        picLabel.setHorizontalAlignment(JLabel.CENTER);

        // Adding buttons
        frame.add(yes);
        frame.add(no);
        frame.add(add);
        frame.add(remove);
        frame.add(show);
        frame.add(progress);
        frame.add(save);
        frame.add(load);

        // Adding Labels
        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.add(label4);

        // Adding Text Fields
        frame.add(tfUser);
        frame.add(tfExercises);

        // Adding Panel to contain
        frame.add(panelE);

        // Adding picture
        frame.add(picLabel);

        frame.setVisible(true);
    }
}