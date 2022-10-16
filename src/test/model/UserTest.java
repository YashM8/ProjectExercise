package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    User u1;

    String E1_NAME = "Bench Press";
    int E1_REPS = 3;
    int E1_SETS = 12;
    int E1_WEIGHT = 135;

    String E2_NAME = "Shoulder Press";
    int E2_REPS = 4;
    int E2_SETS = 10;
    int E2_WEIGHT = 95;

    @BeforeEach
    void runBefore() {
        u1 = new User("Bob", 180, 165);
    }

    @Test
    void testAddExercise() {
        u1.addExercise(E1_NAME, E1_SETS, E1_REPS, E1_WEIGHT);
        u1.addExercise(E2_NAME, E2_SETS, E2_REPS, E2_WEIGHT);

        assertEquals(2, u1.getExercises().size());

        assertEquals("Bench Press", u1.getExercises().get(0).getName());
        assertEquals("Shoulder Press", u1.getExercises().get(1).getName());
    }

    @Test
    void testRemoveExercise() {
        u1.addExercise(E1_NAME, E1_SETS, E1_REPS, E1_WEIGHT);
        u1.addExercise(E2_NAME, E2_SETS, E2_REPS, E2_WEIGHT);

        u1.removeExercise("Bench Press");

        assertEquals(1, u1.getExercises().size());
        assertEquals("Shoulder Press", u1.getExercises().get(0).getName());

        u1.removeExercise("Shoulder Press");
        assertEquals(0, u1.getExercises().size());
    }

    @Test
    void testGetName() {
        assertEquals("Bob", u1.getName());
    }

    @Test
    void testGetHeight() {
        assertEquals(180, u1.getHeight());
    }

    @Test
    void testGetWeight() {
        assertEquals(165, u1.getWeight());
    }

    @Test
    void testGetExercises() {
        assertEquals(0, u1.getExercises().size());
    }

    @Test
    void testEditHeight() {
        u1.editHeight(185);
        assertEquals(185, u1.getHeight());
    }

    @Test
    void testEditWeight() {
        u1.editWeight(170);
        assertEquals(170, u1.getWeight());
    }

    @Test
    void testEditName() {
        u1.editName("Bob Martin");
        assertEquals("Bob Martin", u1.getName());
    }
}
