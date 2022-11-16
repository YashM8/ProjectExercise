// Some code below has been borrowed from WorkRoom example provided.

package ui;

import model.Exercise;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Interface {
    private static final String JSON_STORE = "./data/workroom.json";
    private Scanner input;
    private User u1;
    JsonWriter jsonWriter;
    JsonReader jsonReader;


    // EFFECTS: constructs Interface and runs application
    public void oldInterface() throws IOException {
        chooseUser();
        runInterface();
    }

    public Interface() throws IOException {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: processes user input
    private void chooseUser() throws IOException {
        System.out.println("New user? y/n");
        String newUser = input.nextLine();

        if (newUser.equals("y")) {
            u1 = createUser();
        } else {
            u1 = jsonReader.read();
            loadExercises();
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input
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
        System.out.println("\n======End of Program======");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add Exercise");
        System.out.println("\tp -> Print Exercises");
        System.out.println("\ts -> Save Exercises");
        System.out.println("\tl -> Load Exercises");
        System.out.println("\tr -> Remove Exercise");
        System.out.println("\tsp -> Show Progress");
        System.out.println("\tq -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            createExercises(u1);
        } else if (command.equals("p")) {
            printExercises();
        } else if (command.equals("s")) {
            saveExercises(u1);
        } else if (command.equals("l")) {
            loadExercises();
        } else if (command.equals("r")) {
            removeExercise(u1);
        } else if (command.equals("sp")) {
            showProgress(u1);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: saves the workroom to file
    void saveExercises(User u1) {
        try {
            jsonWriter.open();
            jsonWriter.write(u1);
            jsonWriter.close();
            System.out.println("Saved " + u1.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    void loadExercises() {
        try {
            u1 = jsonReader.read();
            System.out.println("Loaded " + u1.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: prints all the thingies in workroom to the console
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
    public void createExercises(User user1) {

        Scanner myObj = new Scanner(System.in);

        boolean next = true;

        while (next) {
            System.out.println("Enter Exercise Name - ");
            String exerciseName = myObj.nextLine();

            System.out.println("Enter Sets - ");
            int sets = Integer.parseInt(myObj.nextLine());

            System.out.println("Enter Reps - ");
            int reps = Integer.parseInt(myObj.nextLine());

            System.out.println("Enter Weight - ");
            int weight = Integer.parseInt(myObj.nextLine());

            Exercise e1 = new Exercise(exerciseName, sets, reps, weight);

            user1.addExercise(e1);

            System.out.println("Add more? y/n ");
            String answer = myObj.nextLine();

            if (!answer.equals("y")) {
                next = false;
            }
        }
    }

    // EFFECTS: Constructs a User with given name, height, weight.
    // MODIFIES: User
    public User createUser() {
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
    public String showExercises(User user1) {
        System.out.println("Your Exercises are - ");
        String exe = "";
        for (Exercise e : user1.getExercises()) {
            exe += (user1.getExercises().indexOf(e) + 1) + ") " + e.getName() + " - " + e.getSets()
                    + " X " + e.getReps() + " @ " + e.getWeight() + "lbs" + "\n";
            exe += "\n";
        }
        return exe;
    }

    // EFFECTS: Removes exercises in User's list of exercises.
    // MODIFIES: User.exercises()
    // REQUIRES: A User
    public void removeExercise(User u1) {
        Scanner myObj = new Scanner(System.in);

        System.out.println("Exercise to remove - ");
        String removeExerciseName = myObj.nextLine();

        if (!(u1.getExercises().isEmpty())) {
            u1.getExercises().removeIf(exercise -> Objects.equals(exercise.getName(), removeExerciseName));
            System.out.println("Removed Exercise");
        }
    }

    // EFFECTS: Shows progress exercises in User's list of exercises.
    // REQUIRES: A User
    public void showProgress(User u1) {
        Scanner myObj = new Scanner(System.in);

        System.out.println("Show Progress in - ");
        String progressExerciseName = myObj.nextLine();

        ArrayList<Exercise> progressList = new ArrayList<>();

        for (Exercise exercise : u1.getExercises()) {
            if (Objects.equals(exercise.getName().toLowerCase(), progressExerciseName)) {
                progressList.add(exercise);
            }
        }
        if (progressList.size() >= 2) {
            Exercise e1 = progressList.get(progressList.size() - 1);
            // Takes the last 2 Exercises and calculates progress.
            progressList.remove(progressList.size() - 1);

            Exercise e2 = progressList.get(progressList.size() - 1);
            progressList.remove(progressList.size() - 1);

            int weightProgress = (e1.getWeight() - e2.getWeight()) * 100 / e2.getWeight();

            int volumeDifference = (e1.getReps() * e1.getSets()) - (e2.getReps() * e2.getSets());
            int volumeProgress = volumeDifference * 100 / (e2.getReps() * e2.getSets());

            System.out.println("Percentage increase in weight - " + weightProgress);
            System.out.println("Percentage increase in volume - " + volumeProgress);
        } else {
            System.out.println("Exercise doesn't exist OR Two instances of the same exercise have not been logged yet");
        }
    }

    public User parseAndUser(String str) {
        List<String> parsedList = Arrays.asList(str.split(","));

        String name = parsedList.get(0);
        String strHeight = parsedList.get(1).strip();
        String strWeight = parsedList.get(2).strip();

        return new User(name, Integer.parseInt(strHeight), Integer.parseInt(strWeight));
    }

    public Exercise parseAndExercise(String str) {
        List<String> parsedList = Arrays.asList(str.split(","));

        String name = parsedList.get(0);
        String strSets = parsedList.get(1).strip();
        String strReps = parsedList.get(2).strip();
        String strWeight = parsedList.get(3).strip();

        return new Exercise(name, Integer.parseInt(strSets), Integer.parseInt(strReps), Integer.parseInt(strWeight));
    }

    public void removeWithName(User u1, String name) {
        if (!(u1.getExercises().isEmpty())) {
            u1.getExercises().removeIf(exercise -> Objects.equals(exercise.getName(), name));
            System.out.println("Removed Exercise");
        }
    }
}
