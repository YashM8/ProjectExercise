// Much of the code has been borrowed from WorkRoom example provided.

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

    // Represents a reader that reads workroom from JSON data stored in file

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads entire user from file and returns it;
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

    // EFFECTS: parses workroom from JSON object and returns it
    private User parseUser(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int height = jsonObject.getInt("height");
        int weight = jsonObject.getInt("weight");
        User u1 = new User(name, height, weight);
        addExercises(u1, jsonObject);
        return u1;
    }

    // MODIFIES: User
    // EFFECTS: parses thingies from JSON object and adds them to user
    private void addExercises(User u1, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("exercises");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addExercise(u1, nextThingy);
        }
    }

    // MODIFIES: User
    // EFFECTS: parses thingy from JSON object and adds it to user
    private void addExercise(User u1, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int sets = jsonObject.getInt("sets");
        int reps = jsonObject.getInt("reps");
        int weight = jsonObject.getInt("weight");

        Exercise e = new Exercise(name, sets, reps, weight);
        u1.getExercises().add(e);
    }
}
