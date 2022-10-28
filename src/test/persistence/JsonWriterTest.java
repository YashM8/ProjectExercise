// Much of the code has been borrowed from WorkRoom example provided.

package persistence;

import model.Exercise;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            User u1 = new User("Bob", 170, 135);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            User u1 = new User("Bob", 170, 135);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(u1);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            u1 = reader.read();
            assertEquals("Bob", u1.getName());
            assertEquals(170, u1.getHeight());
            assertEquals(135, u1.getWeight());
            assertTrue(u1.getExercises().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            User u1 = new User("Bob", 170, 135);
            u1.addExercise("bench", 3, 12, 135);
            u1.addExercise("squat", 4, 14, 135);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(u1);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            u1 = reader.read();
            assertEquals("Bob", u1.getName());
            assertEquals(170, u1.getHeight());
            assertEquals(135, u1.getWeight());
            List<Exercise> exercises = u1.getExercises();
            assertEquals(2, exercises.size());
            assertEquals("bench", exercises.get(0).getName());
            assertEquals("squat", exercises.get(1).getName());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
