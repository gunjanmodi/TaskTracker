package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testTaskInitialization() {
        Task task = new Task(1, "Test Task", "pending");
        assertEquals(1, task.getId());
        assertEquals("Test Task", task.getDescription());
        assertEquals("pending", task.getStatus());
    }

    @Test
    public void testTaskSetDescription() {
        Task task = new Task(1, "Test Task", "pending");
        task.setDescription("Updated Task");
        assertEquals("Updated Task", task.getDescription());
    }

    @Test
    public void testTaskSetStatus() {
        Task task = new Task(1, "Test Task", "pending");
        task.setStatus("completed");
        assertEquals("completed", task.getStatus());
    }

    @Test
    public void testTaskSetId() {
        Task task = new Task(1, "Test Task", "pending");
        task.setId(2);
        assertEquals(2, task.getId());
    }
}
