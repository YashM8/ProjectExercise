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
    private ArrayList<Exercise> exercises;

    public User(String name, int height, int weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.exercises = new ArrayList<>();
    }

    public void removeExercise(String name) {
        this.exercises.removeIf(exercise -> Objects.equals(exercise.getName(), name));
    }

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