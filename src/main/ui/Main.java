package ui;

import model.*;

public class Main {
    public static void main(String[] args) {
        User u1 = new User("Bob", 185, 165);

        Interface.createExercises(u1);

        Interface.showExercises(u1);

        // Be sure to add an exercise named "squat" before running the next line.

        // Interface.removeExercise("squat", u1);

        // Interface.showExercises(u1);

        // Be sure to add 2 exercise named "bench" before running the next line to see the progress.

        //Interface.showProgress("bench", u1);
    }
}
