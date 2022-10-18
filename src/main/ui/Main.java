package ui;

import model.*;

public class Main {
    public static void main(String[] args) {
        User u1 = Interface.createUser();

        Interface.createExercises(u1);

        Interface.showExercises(u1);

        Interface.removeExercise(u1);

        Interface.showExercises(u1);

        Interface.showProgress(u1);
    }
}
