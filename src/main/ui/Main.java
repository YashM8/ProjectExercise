package ui;

import model.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
//        User u1 = Interface.createUser();
//
//        Interface.createExercises(u1);
//
//        Interface.showExercises(u1);
//
//        Interface.removeExercise(u1);
//
//        Interface.showExercises(u1);
//
//        Interface.showProgress(u1);

        try {
            new Interface();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
