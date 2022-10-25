package persistence;

import model.Exercise;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public User read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUser(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

//    // EFFECTS: parses workroom from JSON object and returns it
//    private User parseUser(JSONObject jsonObject) {
//        String name = jsonObject.getString("name");
//        int height = jsonObject.getInt("height");
//        int weight = jsonObject.getInt("height");
//
//        User u1 = new User(name, height, weight);
//
//        JSONArray jsonArrayForExercises = jsonObject.getJSONArray("exercises");
//
//        for (Object jsonExercises: jsonArrayForExercises) {
//            String nameE = jsonObject.getString("name");
//            int setsE = jsonObject.getInt("sets");
//            int repsE = jsonObject.getInt("reps");
//            int weightE = jsonObject.getInt("weight");
//
//            u1.getExercises().add(new Exercise(nameE, setsE, repsE, weightE));
//        }
//        return u1;

    private User parseUser(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int height = jsonObject.getInt("height");
        int weight = jsonObject.getInt("weight");
        User u1 = new User(name, height, weight);
        addThingies(u1, jsonObject);
        return u1;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addThingies(User u1, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("exercises");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addThingy(u1, nextThingy);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addThingy(User u1, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int sets = jsonObject.getInt("sets");
        int reps = jsonObject.getInt("reps");
        int weight = jsonObject.getInt("weight");

        Exercise e = new Exercise(name, sets, reps, weight);
        u1.getExercises().add(e);
    }
}
