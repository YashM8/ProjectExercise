// Much of the code has been borrowed from WorkRoom example provided.

package persistence;

import model.Exercise;
import model.User;
import org.junit.jupiter.api.Test;
import ui.Interface;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            User u1 = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            User u1 = reader.read();
            assertEquals("Bob", u1.getName());
            assertEquals(170, u1.getHeight());
            assertEquals(135, u1.getWeight());
            assertTrue(u1.getExercises().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            User u1 = reader.read();
            assertEquals("Bob", u1.getName());
            assertEquals(170, u1.getHeight());
            assertEquals(135, u1.getWeight());
            List<Exercise> exercises = u1.getExercises();
            assertEquals(2, exercises.size());
            assertEquals("bench", exercises.get(0).getName());
            assertEquals("squat", exercises.get(1).getName());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReadUserEmpty() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            User u1 = reader.read();
            assertEquals("Bob", u1.getName());
            assertEquals(170, u1.getHeight());
            assertEquals(135, u1.getWeight());
            assertTrue(u1.getExercises().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReadUserGeneral() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            User u1 = reader.readUser();
            assertEquals("Bob", u1.getName());
            assertEquals(170, u1.getHeight());
            assertEquals(135, u1.getWeight());
            assertTrue(u1.getExercises().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReadUserNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            User u1 = reader.readUser();
            fail("IOException expected");

        } catch (IOException e) {
            // pass
        }
    }
}
