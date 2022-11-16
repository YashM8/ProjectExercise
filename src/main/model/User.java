package model;

import org.json.JSONArray;
import org.json.JSONObject;

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
        if (!(this.exercises.isEmpty())) {
            this.exercises.removeIf(exercise -> exercise.getName().equals(name));
//            for (Exercise exercise: this.exercises) {
//                if (exercise.getName().equals(name)) {
//                    this.exercises.remove(exercise);
//                }
//            }
        }
    }

    // MODIFIES: this
    // REQUIRES: sets > 0; reps > 0; weight > 0;
    // EFFECTS: Adds an instance of Exercise with <name>, <sets>, <reps> and <weight> to this.exercises.
    public void addExercise(Exercise e1) {
        this.exercises.add(e1);
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

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("height", height);
        json.put("weight", weight);
        json.put("name", name);
        json.put("exercises", exercisesToJson());
        return json;
    }

    private JSONArray exercisesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Exercise e1 : exercises) {
            jsonArray.put(e1.toJson());
        }
        return jsonArray;
    }
}
