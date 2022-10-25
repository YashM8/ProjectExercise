package model;

    /*
    Creates an exercise with given sets, reps, weight and name.
    */

import org.json.JSONObject;

public class Exercise {
    private int sets;
    private int reps;
    private int weight;
    private String name;


    // REQUIRES: sets > 0; reps > 0; weight > 0 in lbs;
    // EFFECTS: Constructs an exercise with given sets, reps, weight and name.
    public Exercise(String name, int sets, int reps, int weight) {
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.name = name;
    }

    /*
    Getter methods for the attributes of Exercise object.
    */

    public int getSets() {
        return this.sets;
    }

    public int getReps() {
        return this.reps;
    }

    public int getWeight() {
        return this.weight;
    }

    public String getName() {
        return this.name;
    }

    /*
    Methods to edit attributes of Exercise object. They are not used in P1, but I think I'll need them later.
    */

    public void editSets(int newSets) {
        this.sets = newSets;
    }

    public void editReps(int newReps) {
        this.reps = newReps;
    }

    public void editWeight(int newWeight) {
        this.weight = newWeight;
    }

    public void editName(String newName) {
        this.name = newName;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("sets", sets);
        json.put("reps", reps);
        json.put("weight", weight);
        return json;
    }
}
