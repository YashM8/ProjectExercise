package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseTest {
    Exercise e1;

    @BeforeEach
    void runBefore() {
        e1 = new Exercise("Bench Press", 3, 12, 135);
    }

    @Test
    void testGetName() {
        assertEquals("Bench Press", e1.getName());
    }

    @Test
    void testGetReps() {
        assertEquals(12, e1.getReps());
    }

    @Test
    void testGetSets() {
        assertEquals(3, e1.getSets());
    }

    @Test
    void testGetWeight() {
        assertEquals(135, e1.getWeight());
    }

    @Test
    void testEditName() {
        e1.editName("Shoulder Press");
        assertEquals("Shoulder Press", e1.getName());
    }

    @Test
    void testEditSets() {
        e1.editSets(4);
        assertEquals(4, e1.getSets());
    }

    @Test
    void testEditReps() {
        e1.editReps(10);
        assertEquals(10, e1.getReps());
    }

    @Test
    void testEditWeight() {
        e1.editWeight(95);
        assertEquals(95, e1.getWeight());
    }
}
