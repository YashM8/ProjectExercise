package ui;

import model.Exercise;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Interface {
    private static final String JSON_STORE = "./data/workroom.json";
    private Scanner input;
    //private ArrayList<User> users;
    User u1;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public Interface() throws FileNotFoundException {
        u1 = createUser();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runInterface();
    }

    private void runInterface() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add Exercise");
        System.out.println("\tp -> Print Exercises");
        System.out.println("\ts -> Save Exercises");
        System.out.println("\tl -> Load Exercises");
        System.out.println("\tq -> Quit");
    }

    private void processCommand(String command) {
        switch (command) {
            case "a" -> createExercises(u1);
            case "p" -> printExercises();
            case "s" -> saveExercises();
            case "l" -> loadExercises();
            default -> System.out.println("Selection not valid...");
        }
    }

    private void saveExercises() {
        try {
            jsonWriter.open();
            jsonWriter.write(u1);
            jsonWriter.close();
            System.out.println("Saved " + u1.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadExercises() {
        try {
            u1 = jsonReader.read();
            System.out.println("Loaded " + u1.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    private void printExercises() {
        List<Exercise> exercises = u1.getExercises();
        System.out.println("Your Exercises are - ");
        for (Exercise e : exercises) {
            System.out.println((e.getName() + " - " + e.getSets()
                    + " X " + e.getReps() + " @ " + e.getWeight() + "lbs"));
        }
        System.out.println("=========End of Exercises=========");
    }

    // EFFECTS: Creates exercises in User's list of exercises.
    // MODIFIES: User.exercises
    // REQUIRES: A User
    public static void createExercises(User user1) {

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

            user1.addExercise(exerciseName, sets, reps, weight);                  // Adds an exercise to the users list.

            System.out.println("Add more? y/n ");
            String answer = myObj.nextLine();

            if (!answer.equals("y")) {                                      // Terminates loop unless the user enters y.
                next = false;
            }
        }
    }

    // EFFECTS: Constructs a User with given name, height, weight.
    // MODIFIES: User
    public static User createUser() {
        Scanner myObj = new Scanner(System.in);

        System.out.println("Name - ");
        String name = myObj.nextLine();

        System.out.println("Height - ");
        int height = Integer.parseInt(myObj.nextLine());

        System.out.println("Weight- ");
        int weight = Integer.parseInt(myObj.nextLine());

        return new User(name, height, weight);
    }

    // EFFECTS: Shows exercises in User's list of exercises.
    // MODIFIES: User
    // REQUIRES: A User
    public static void showExercises(User user1) {
        System.out.println("Your Exercises are - ");
        for (Exercise e : user1.getExercises()) {
            System.out.println((user1.getExercises().indexOf(e) + 1) + ") " + e.getName() + " - " + e.getSets()
                    + " X " + e.getReps() + " @ " + e.getWeight() + "lbs");
        }
        System.out.println("=========End of Exercises=========");
    }

    // EFFECTS: Removes exercises in User's list of exercises.
    // MODIFIES: User.exercises()
    // REQUIRES: A User
    public static void removeExercise(User u1) {
        Scanner myObj = new Scanner(System.in);

        System.out.println("Exercise to remove - ");
        String removeExerciseName = myObj.nextLine();

        if (!(u1.getExercises().isEmpty())) {
            u1.getExercises().removeIf(exercise -> Objects.equals(exercise.getName(), removeExerciseName));
        }
    }

    // EFFECTS: Shows progress exercises in User's list of exercises.
    // REQUIRES: A User
    public static void showProgress(User u1) {
        /*
        Shows progress <u1> has made for Exercise with <name>.
        */

        Scanner myObj = new Scanner(System.in);

        System.out.println("Show Progress in - ");
        String progressExerciseName = myObj.nextLine();

        ArrayList<Exercise> progressList = new ArrayList<>();

        for (Exercise exercise : u1.getExercises()) {
            if (Objects.equals(exercise.getName(), progressExerciseName)) {
                progressList.add(exercise);
            }
        }
        if (progressList.size() >= 2) {
            Exercise e1 = progressList.get(progressList.size() - 1);
            // Takes the last 2 Exercises and calculates progress.
            progressList.remove(progressList.size() - 1);

            Exercise e2 = progressList.get(progressList.size() - 1);
            progressList.remove(progressList.size() - 1);

            int weightProgress = (e1.getWeight() - e2.getWeight()) * 100 / e2.getWeight();        //Percentage increase.

            int volumeDifference = (e1.getReps() * e1.getSets()) - (e2.getReps() * e2.getSets());
            int volumeProgress = volumeDifference * 100 / (e2.getReps() * e2.getSets());

            System.out.println("Percentage increase in weight - " + weightProgress);
            System.out.println("Percentage increase in volume - " + volumeProgress);
        }
    }
}
