package ui;

import model.Exercise;
import model.User;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Interface {

    public static void createExercises(User user1) {

        /*
        Creates Exercises by taking user input and adds them to <user1> associated list.
        */

        Scanner myObj = new Scanner(System.in);                                    // Scanner object to take user input.

        boolean next = true;

        while (next) {                                                       // Infinite loop to keep taking user input.
            System.out.println("Enter Exercise Name - ");
            String exerciseName = myObj.nextLine();

            System.out.println("Enter Sets - ");
            int sets = Integer.parseInt(myObj.nextLine());

            System.out.println("Enter Reps - ");
            int reps = Integer.parseInt(myObj.nextLine());

            System.out.println("Enter Weight - ");
            int weight = Integer.parseInt(myObj.nextLine());

            user1.addExercise(exerciseName, sets, reps, weight);    // Adds an exercise to the users list.

            System.out.println("Add more? y/n ");
            String answer = myObj.nextLine();

            if (!answer.equals("y")) {                                      // Terminates loop unless the user enters y.
                next = false;
            }
        }
    }

    public static void showExercises(User user1) {

        // Loops over <user1>'s Exercises and prints them to the console.

        System.out.println("Your Exercises are - ");
        for (Exercise e : user1.getExercises()) {
            System.out.println((user1.getExercises().indexOf(e) + 1) + ") " + e.getName() + " - " + e.getSets()
                    + " X " + e.getReps() + " @ " + e.getWeight() + "lbs");
        }
        System.out.println("=========End of Exercises=========");
    }

    public static void removeExercise(String exerciseName, User u1) {
        /*
        Removes all Exercises with <exerciseName> from <user1>'s associated list
        */
        u1.getExercises().removeIf(exercise -> Objects.equals(exercise.getName(), exerciseName));
    }

    public static void showProgress(String name, User u1) {
        /*
        Shows progress <u1> has made for Exercise with <name>.
        */

        // Creates a list with all instances of Exercise with <name>
        ArrayList<Exercise> progressList = new ArrayList<>();

        for (Exercise exercise : u1.getExercises()) {
            if (Objects.equals(exercise.getName(), name)) {
                progressList.add(exercise);
            }
        }
        Exercise e1 = progressList.get(progressList.size() - 1);  // Takes the last 2 Exercises and calculates progress.
        progressList.remove(progressList.size() - 1);

        Exercise e2 = progressList.get(progressList.size() - 1);
        progressList.remove(progressList.size() - 1);

        int weightProgress = (e1.getWeight() - e2.getWeight()) * 100 / e2.getWeight();            //Percentage increase.

        int volumeDifference = (e1.getReps() * e1.getSets()) - (e2.getReps() * e2.getSets());
        int volumeProgress = volumeDifference * 100 / (e2.getReps() * e2.getSets());

        System.out.println("Percentage increase in weight - ");
        System.out.println(weightProgress);

        System.out.println("Percentage increase in volume - ");
        System.out.println(volumeProgress);
    }
}
