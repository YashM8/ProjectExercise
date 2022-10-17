package ui;

import model.*;

public class Main {
    public static void main(String[] args) {
        User u1 = new User("Bob", 185, 165);

        Interface.createExercises(u1);

        Interface.showExercises(u1);

        Interface.removeExercise("squat", u1);
        Interface.showExercises(u1);

        Interface.showProgress("bench", u1);
    }
}
