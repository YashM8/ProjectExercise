package model;

import java.util.ArrayList;
import java.util.Objects;

    /*
    Creates a User with given name, height, weight and a list of exercises associated with a user.
    */

public class User {
    private String name;
    private int height;
    private int weight;
    private final ArrayList<Exercise> exercises;

    // REQUIRES: height > 0; weight > 0;
    // EFFECTS: Constructs an exercise with given sets, reps, weight and name.
    public User(String name, int height, int weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.exercises = new ArrayList<>();
    }

    // MODIFIES: this.
    // REQUIRES: The exercise name to be in the user's exercise list.
    // EFFECTS: this.exercises and removes all instances of Exercise with <name>.
    public void removeExercise(String name) {
        this.exercises.removeIf(exercise -> Objects.equals(exercise.getName(), name));
    }

    // MODIFIES: this
    // REQUIRES: sets > 0; reps > 0; weight > 0;
    // EFFECTS: Adds an instance of Exercise with <name>, <sets>, <reps> and <weight> to this.exercises.
    public void addExercise(String name, int sets, int reps, int weight) {
        this.exercises.add(new Exercise(name, sets, reps, weight));
    }

    /*
    Getter methods for the attributes of Exercise object. They are not used in P1, but I think I'll need them later.
    */

    public ArrayList<Exercise> getExercises() {
        return this.exercises;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWeight() {
        return this.weight;
    }

    public String getName() {
        return this.name;
    }

    /*
    Methods to edit attributes of User object. They are not used in P1, but I think I'll need them later.
    */

    public void editName(String newName) {
        this.name = newName;
    }

    public void editWeight(int newWeight) {
        this.weight = newWeight;
    }

    public void editHeight(int newHeight) {
        this.height = newHeight;
    }
}
